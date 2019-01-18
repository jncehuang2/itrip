package cn.itrip.auth.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import cn.itrip.common.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import cn.itrip.auth.exception.UserLoginFailedException;
import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripTokenVO;
import springfox.documentation.annotations.ApiIgnore;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.HttpStatus;
import ytx.org.apache.http.client.methods.HttpGet;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.util.EntityUtils;

/**
 * 用户登录控制器
 *
 * @author hduser
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
@Api(value = "API", basePath = "http://api.itrap.com/auth")
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private ValidationToken validationToken;
    @ApiOperation(value = "用户登录", httpMethod = "POST",
            protocols = "HTTP",
            response = Dto.class, notes = "根据用户名、密码进行统一认证")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", required = true, value = "用户名", name = "name", defaultValue = "14395202@qq.com"),
            @ApiImplicitParam(paramType = "form", required = true, value = "密码", name = "password", defaultValue = "111111"),
    })
    @RequestMapping(value = "/dologin", method = RequestMethod.POST, produces = "application/json")
    public Dto dologin(@RequestParam(defaultValue = "第三方昵称") String name, @RequestParam(defaultValue = "第三方密码") String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = (JSONObject) tokenService.getValues("jsonObject");
        String tokenForStart = CookieUtil.readLoginToken(request);
        String openId="";
        if (tokenForStart!=null){
            return DtoUtil.returnFail("不可重复登录", ErrorCode.AUTH_AUTHENTICATION_FAILED);
        }
        if (jsonObject!=null){
            openId = jsonObject.getString("openid");
            name = jsonObject.getString("nickname");
        }
        Integer userType = (Integer) tokenService.getValues("userType");
        if (!EmptyUtils.isEmpty(name) && !EmptyUtils.isEmpty(password)) {
            ItripUser user = null;
            try {
                if (!EmptyUtils.isEmpty(openId)) {
                    user = new ItripUser();
                    Map param = new HashMap();
                    param.put("flatID", openId);
                    List<ItripUser> listByMap = userService.getItripUserListByMap(param);
                    if (listByMap.size() == 0) {
                        user.setFlatID(openId);
                        user.setUserType(userType);
                        user.setUserName(name);
                        Dto<ItripUser> itripUserDto = register(user);
                        listByMap = userService.getItripUserListByMap(param);
                    }
                    for (ItripUser itripUser : listByMap) {
                        user = itripUser;
                    }
                } else {
                    System.out.println("MD5.getMd5(password.trim()>>>" + MD5.getMd5(password.trim(), 32));
                    user = userService.login(name.trim(), MD5.getMd5(password.trim(), 32));
                }

            } catch (UserLoginFailedException e) {
                return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_AUTHENTICATION_FAILED);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
            }
            if (EmptyUtils.isNotEmpty(user)) {
                String token = tokenService.generateToken(
                        request.getHeader("user-agent"), user);
                System.out.println("token>>>>>>" + token);
                tokenService.save(token, user);

                //返回ItripTokenVO

                long expTime = Calendar.getInstance().getTimeInMillis() + TokenService.SESSION_TIMEOUT * 1000;
                long genTime = Calendar.getInstance().getTimeInMillis();
                System.out.println("expTime>>>" + expTime + " " + "genTime>>>" + genTime);
                ItripTokenVO tokenVO = new ItripTokenVO(token, expTime, genTime);
                System.out.println("tokenVO>>>>" + tokenVO.getToken());
                if (EmptyUtils.isNotEmpty(openId)) {
                    CookieUtil.writeLoginToken(response,token,"token");
                    CookieUtil.writeLoginToken(response,String.valueOf(expTime),"expTime");
                    CookieUtil.writeLoginToken(response,user.getUserCode(),"user");
                    response.sendRedirect("http://www.itrip.com/#/home");
                }
                return DtoUtil.returnSuccess("登录成功",tokenVO);
            } else {
                return DtoUtil.returnFail("用户名密码错误", ErrorCode.AUTH_AUTHENTICATION_FAILED);
            }
        } else {
            return DtoUtil.returnFail("参数错误！检查提交的参数名称是否正确。", ErrorCode.AUTH_PARAMETER_ERROR);
        }
    }

    @ApiOperation(value = "用户注销", httpMethod = "GET",
            protocols = "HTTP",
            response = Dto.class, notes = "客户端需在header中发送token")
    @ApiImplicitParam(paramType = "header", required = true, name = "token", value = "用户认证凭据", defaultValue = "PC-yao.liu2015@bdqn.cn-8-20170516141821-d4f514")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Dto logout(HttpServletRequest request,HttpServletResponse response) {
        String token = CookieUtil.readLoginToken(request);
        String jsonObject  = CookieUtil.readObject(request,"jsonObject");
        String userType =  CookieUtil.readObject(request,"userType");
        if (!tokenService.validate(request.getHeader("user-agent"), token))
            return DtoUtil.returnFail("token无效", ErrorCode.AUTH_TOKEN_INVALID);
        //删除token和信息
        try {
            tokenService.delete(token);
            if (EmptyUtils.isNotEmpty(jsonObject) && EmptyUtils.isNotEmpty(jsonObject)){
                tokenService.delete(jsonObject);
                tokenService.delete(userType);
            }
            CookieUtil.delLoginToken(request,response,"token");
            CookieUtil.delLoginToken(request,response,"expTime");

            return DtoUtil.returnSuccess("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注销失败", ErrorCode.AUTH_UNKNOWN);
        }
    }

    /**
     * token置换
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/retoken", method = RequestMethod.GET)
    public Dto reloadToken(HttpServletRequest request,HttpServletResponse response) {
        String token= "";
        Long expTime = Calendar.getInstance().getTimeInMillis() * 2 * 60 * 60 * 1000;
        log.info(expTime.toString());
        System.out.println("retoken>>>>>>>>>>>>>>>>>>>>>>");
        try {
            token = this.tokenService.replaceToken(request.getHeader("user-agent"),CookieUtil.readLoginToken(request));
            CookieUtil.delLoginToken(request,response,"token");
            CookieUtil.delLoginToken(request,response,"expTime");
            ItripTokenVO vo = new ItripTokenVO(token, Calendar.getInstance().getTimeInMillis() * 2 * 60 * 60 * 1000, Calendar.getInstance().getTimeInMillis());
            CookieUtil.writeLoginToken(response,token,"token");
            CookieUtil.writeLoginToken(response,expTime.toString(),"expTime");
            return DtoUtil.returnDataSuccess(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getLocalizedMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }

    /**
     * token验证
     *
     * @param request
     * @return
     */
    @ApiOperation(value="验证token",httpMethod = "POST",
            protocols = "HTTP",
            response = Dto.class,notes="验证token ")
    @RequestMapping(value = "/validateToken", method = RequestMethod.GET)
    public Dto validateToken(HttpServletRequest request) {
        String tokenString = CookieUtil.readLoginToken(request);
        System.out.println("tokenString>>>>>>" + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        if (null != currentUser) {
            System.out.println("getId" + currentUser.getId());
            System.out.println("getUserName" + currentUser.getUserName());
            System.out.println("getUserCode" + currentUser.getUserCode());
            System.out.println("getUserPassword" + currentUser.getUserPassword());
            return DtoUtil.returnSuccess("获取登录用户信息成功", currentUser);
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }

    /**
     * ajax获取用户列表
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "ajax获取用户列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "客户端需在header中发送token")
    @ApiImplicitParam(paramType = "header", required = true, name = "token", value = "用户认证凭据", defaultValue = "token:PC-21ec64d6e9cae0917ea4b54bc36809d8-8-20170601100121-699dfc")
    @RequestMapping(value = "/getUserList", produces = "application/json", method = RequestMethod.GET)
    public Dto getUserList(HttpServletRequest request) {
        System.out.println("getUserList>>>>>>>>>>>>>>>>>>");
        String tokenString = CookieUtil.readLoginToken(request);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUser> list = null;
        try {
            if (EmptyUtils.isEmpty(currentUser)) {
                return DtoUtil.returnFail("token失效，请重登录", "100000");
            } else {
                list = userService.findAll();
                return DtoUtil.returnSuccess("获取成功", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }
    @ApiOperation(value = "微信登录的方法", httpMethod = "GET",
            protocols = "HTTP", response = Dto.class, notes = "微信登录的方法")
    @RequestMapping(value = "/wechatlogin", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("=======进入login=========>>");
        String url = "https://open.weixin.qq.com/connect/qrconnect?";
        url += "appid=wxfecb0e6daba78c9f";
        url += "&redirect_uri=" + URLEncoder.encode("http://gtlwechatmall.natapp1.cc/auth/api/callBackLogin", "GBK");
        url += "&response_type=code&scope=snsapi_login";
        url += "&state=" + request.getSession().getId() + "#wechat_redirect";
        System.out.println("url>>>" + url);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiIgnore
    @RequestMapping(value = "/callBackLogin")
    public String callBackLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("callBackLogin....");
        // return "redirect:../loginSuccess.jsp";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code=" + code);
        System.out.println("state=" + state);
        // 获得access_token数据，获得访问令牌。等下要通过令牌去获得用户的信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        url += "appid=wxfecb0e6daba78c9f";
        url += "&secret=226cdf3c5cf5c8ecd845183387cefa5d";
        url += "&code=" + code + "&grant_type=authorization_code";
        // 要去执行这个URL，并通过这个URL获得返回值
        JSONObject jsonObject = this.httpGet(url);
        String at = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        System.out.println("at=" + at);
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + at + "&openid=" + openId;
        jsonObject = this.httpGet(url);
        tokenService.setExValues("jsonObject", JSON.toJSONString(jsonObject),60*30);
        tokenService.setExValues("userType", "1",60*30);
        response.sendRedirect("http://www.itrip.com/auth/api/dologin");
        return "success";
    }

    /**
     * 发送get请求 http://www.cnblogs.com/QQParadise/articles/5020215.html
     *
     * @param url 路径
     * @return
     */
    @ApiIgnore
    public JSONObject httpGet(String url) {
        // get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("strResult..." + strResult);
                String str = new String(strResult.getBytes("ISO-8859-1"), "UTF-8");
                System.out.println("str..." + str);
                /** 把json字符串转换成json对象 **/
                jsonResult = JSON.parseObject(str);
                System.out.println("strResult=" + str);
            } else {
                System.out.println("读取数据失败..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    @ApiOperation(value = "注册的方法", httpMethod = "GET",produces = "application/json",
            protocols = "HTTP", response = Dto.class, notes = "注册的方法")
    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public Dto<ItripUser> register(@RequestBody ItripUser itripUser) {
        Dto dto = null;
        Integer result = 0;
        if (itripUser.getUserType()==null){
            itripUser.setUserType(0);
        }
        try {
            if (itripUser.getUserType() == 0) {
                if (validEmail(itripUser.getUserCode())) {
                    itripUser.setUserPassword(MD5.getMd5(itripUser.getUserPassword(), 32));
                    userService.itriptxCreateUser(itripUser);
                } else if (validPhone(itripUser.getUserCode())) {
                    itripUser.setUserPassword(MD5.getMd5(itripUser.getUserPassword(), 32));
                    userService.itriptxCreateUserByPhone(itripUser);
                } else {
                    return DtoUtil.returnFail("注册失败！", ErrorCode.AUTH_USER_ERRORCODE);
                }
            } else {
                Integer random = MD5.getRandomCode();
                itripUser.setUserCode(MD5.getMd5(random.toString(), 11));
                itripUser.setUserPassword(MD5.getMd5(random.toString(), 32));
                userService.itriptxAddItripUser(itripUser);
                if (result < 1) {
                    return DtoUtil.returnFail("注册失败！", ErrorCode.AUTH_USER_ERRORCODE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注册失败!", ErrorCode.AUTH_UNKNOWN);
        }
        Map param = new HashMap();
        param.put("userCode", itripUser.getUserCode());
        try {
            List<ItripUser> listByMap = userService.getItripUserListByMap(param);
            log.debug("ItripUser size" + listByMap.size());
            for (ItripUser user : listByMap) {
                if (itripUser.getUserType() == 0) {
                    itripUser.setFlatID(user.getId().toString());
                }
                itripUser.setCreatedBy(user.getId());
                itripUser.setId(user.getId());
                log.info("FlatID" + itripUser.getFlatID());
                log.info("CreatedBy()" + itripUser.getCreatedBy());
            }
            userService.updateUser(itripUser);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注册失败!", ErrorCode.AUTH_FLATID_ERROR);
        }
        return DtoUtil.returnSuccess("注册成功！", itripUser);
    }
    @ApiOperation(value = "注册的方法", httpMethod = "GET",produces = "application/json",
            protocols = "HTTP", response = Dto.class, notes = "注册的方法")
    @RequestMapping(value = "/registerbyphone", method = RequestMethod.POST)
    public Dto<ItripUser> registerbyphone(@RequestBody ItripUser itripUser) {
        Dto dto = null;
        Integer result = 0;
        if (itripUser.getUserType()==null){
            itripUser.setUserType(0);
        }
        try {
            if (itripUser.getUserType() == 0) {
                if (validEmail(itripUser.getUserCode())) {
                    itripUser.setUserPassword(MD5.getMd5(itripUser.getUserPassword(), 32));
                    userService.itriptxCreateUser(itripUser);
                } else if (validPhone(itripUser.getUserCode())) {
                    itripUser.setUserPassword(MD5.getMd5(itripUser.getUserPassword(), 32));
                    userService.itriptxCreateUserByPhone(itripUser);
                } else {
                    return DtoUtil.returnFail("注册失败！", ErrorCode.AUTH_USER_ERRORCODE);
                }
            } else {
                Integer random = MD5.getRandomCode();
                itripUser.setUserCode(MD5.getMd5(random.toString(), 11));
                itripUser.setUserPassword(MD5.getMd5(random.toString(), 32));
                userService.itriptxAddItripUser(itripUser);
                if (result < 1) {
                    return DtoUtil.returnFail("注册失败！", ErrorCode.AUTH_USER_ERRORCODE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注册失败!", ErrorCode.AUTH_UNKNOWN);
        }
        Map param = new HashMap();
        param.put("userCode", itripUser.getUserCode());
        try {
            List<ItripUser> listByMap = userService.getItripUserListByMap(param);
            log.debug("ItripUser size" + listByMap.size());
            for (ItripUser user : listByMap) {
                if (itripUser.getUserType() == 0) {
                    itripUser.setFlatID(user.getId().toString());
                }
                itripUser.setCreatedBy(user.getId());
                itripUser.setId(user.getId());
                log.info("FlatID" + itripUser.getFlatID());
                log.info("CreatedBy()" + itripUser.getCreatedBy());
            }
            userService.updateUser(itripUser);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注册失败!", ErrorCode.AUTH_FLATID_ERROR);
        }
        return DtoUtil.returnSuccess("注册成功！", itripUser);
    }
    @ApiOperation(value = "验证邮箱是否可以注册", httpMethod = "POST",
            protocols = "HTTP",
            response = Dto.class, notes = "验证邮箱")
    @ApiParam(name = "name", value = "邮箱", defaultValue = "test@163.com")
    @RequestMapping(value = "/ckusr", method = RequestMethod.GET)
    public Dto validateForItripUser(@RequestParam String name, HttpServletRequest request) {
        try {
            if (!validEmail(name))
                return DtoUtil.returnFail("请使用正确的邮箱地址注册", ErrorCode.AUTH_ILLEGAL_USERCODE);
            ItripUser itripUser = userService.findByUsername(name);
            if (itripUser != null) {
                return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_UNKNOWN);
        }
        return DtoUtil.returnSuccess("success");
    }
    @ApiOperation(value = "验证手机号码是否可以注册", httpMethod = "POST",
            protocols = "HTTP",
            response = Dto.class, notes = "验证手机号码")

    @RequestMapping(value = "/ckusrToPhone", method = RequestMethod.POST)
    @ApiParam(name = "name", value = "手机号码", defaultValue = "13365859052")
    public Dto validatePhoneForItripUser(@RequestParam String name, HttpServletRequest request) {
        try {
            if (!validPhone(name))
                return DtoUtil.returnFail("请使用正确的电话号码注册", ErrorCode.AUTH_ILLEGAL_USERCODE);
            ItripUser itripUser = userService.findByUsername(name);
            if (itripUser != null) {
                return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_UNKNOWN);
        }
        return DtoUtil.returnSuccess("success");
    }

    /**
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     */
    private boolean validEmail(String email) {

        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(regex).matcher(email).find();
    }

    /**
     * 验证是否合法的手机号
     *
     * @param phone
     * @return
     */
    private boolean validPhone(String phone) {
        String regex = "^1[3578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

    @ApiOperation(value = "邮箱注册用户激活", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "邮箱激活")
//    validatephone
    @RequestMapping(value = "/activate", method = RequestMethod.PUT, produces = "application/json")
    public Dto activate(
            @ApiParam(name = "user", value = "注册邮箱地址", defaultValue = "test@bdqn.cn")
            @RequestParam String user,
            @ApiParam(name = "code", value = "激活码", defaultValue = "018f9a8b2381839ee6f40ab2207c0cfe")
            @RequestParam String code) {
        try {
            if (validEmail(user)) {
                if (userService.activate(user, code)) {
                    return DtoUtil.returnSuccess("激活成功");
                } else {
                    return DtoUtil.returnSuccess("激活失败");
                }
            }else if (validPhone(user)) {
                if (userService.validatePhone(user, code)) {
                    return DtoUtil.returnSuccess("激活成功");
                } else {
                    return DtoUtil.returnSuccess("激活失败");
                }
            }else{
                return DtoUtil.returnFail("激活失败", ErrorCode.AUTH_ACTIVATE_FAILED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("激活失败", ErrorCode.AUTH_ACTIVATE_FAILED);
        }
    }
    @ApiOperation(value = "手机注册用户激活", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "邮箱激活")
//    validatephone
    @RequestMapping(value = "/validatephone", method = RequestMethod.PUT, produces = "application/json")
    public Dto validatephone(
            @ApiParam(name = "user", value = "注册邮箱地址", defaultValue = "test@bdqn.cn")
            @RequestParam String user,
            @ApiParam(name = "code", value = "激活码", defaultValue = "018f9a8b2381839ee6f40ab2207c0cfe")
            @RequestParam String code) {
        try {
            if (validEmail(user)) {
                if (userService.activate(user, code)) {
                    return DtoUtil.returnSuccess("激活成功");
                } else {
                    return DtoUtil.returnSuccess("激活失败");
                }
            }else if (validPhone(user)) {
                if (userService.validatePhone(user, code)) {
                    return DtoUtil.returnSuccess("激活成功");
                } else {
                    return DtoUtil.returnSuccess("激活失败");
                }
            }else{
                return DtoUtil.returnFail("激活失败", ErrorCode.AUTH_ACTIVATE_FAILED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("激活失败", ErrorCode.AUTH_ACTIVATE_FAILED);
        }
    }
}
