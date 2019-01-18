package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.beans.vo.userinfo.ItripAddUserLinkUserVO;
import cn.itrip.beans.vo.userinfo.ItripModifyUserLinkUserVO;
import cn.itrip.beans.vo.userinfo.ItripSearchUserLinkUserVO;
import cn.itrip.beans.vo.userinfo.ItripUserVO;
import cn.itrip.common.*;
import cn.itrip.service.user.ItripUserService;
import cn.itrip.service.userlinkuser.ItripUserLinkUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 用户信息Controller
 * <p>
 * 包括API接口：
 * 1、根据UserId、联系人姓名查询常用联系人接口
 * 2、指定用户下添加联系人
 * 3、修改指定用户下的联系人信息
 * 4、删除指定用户下的联系人信息
 * <p>
 * 注：错误码（100401 ——100500）
 * <p>
 * Created by hanlu on 2017/5/9.
 */

@RestController
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/userinfo")
@Slf4j
public class UserInfoController {

    @Resource
    private ValidationToken validationToken;
    @Resource
    private ItripUserService itripUserService;
    @Resource
    private ItripUserLinkUserService itripUserLinkUserService;

    /**
     * 根据UserId,联系人姓名查询常用联系人-add by donghai
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询常用联系人信息(可根据联系人姓名进行模糊查询)" +
            "<p>若不根据联系人姓名进行查询，不输入参数即可 | 若根据联系人姓名进行查询，须进行相应的入参，比如：{\"linkUserName\":\"张三\"}</p>" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100401 : 获取常用联系人信息失败 </p>" +
            "<p>100000 : token失效，请重登录</p>")
    //@RequestMapping(value = "/queryuserlinkuser",method= RequestMethod.POST)
    @RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
    public Dto<ItripUserLinkUser> queryUserLinkUser(@RequestBody ItripSearchUserLinkUserVO itripSearchUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        log.debug("token name is from header : " + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();
        String linkUserName = (null == itripSearchUserLinkUserVO) ? null : itripSearchUserLinkUserVO.getLinkUserName();
        Dto dto = null;
        if (null != currentUser) {
            Map param = new HashMap();
            param.put("userId", currentUser.getId());
            param.put("linkUserName", linkUserName);
            try {
                userLinkUserList = itripUserLinkUserService.getItripUserLinkUserListByMap(param);
                log.debug("userLinkUserList size " + userLinkUserList.size());
                return DtoUtil.returnSuccess("获取常用联系人信息成功", userLinkUserList);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @ApiOperation(value = "新增常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "新增常用联系人信息(可根据联系人姓名进行新增一条记录)")
    @RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
    public Dto<ItripUserLinkUser> addUserLinkUser(@RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        log.debug("token name is from header : " + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        Dto dto = null;
        if (null != currentUser) {
            try {
                ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
                BeanUtils.copyProperties(itripAddUserLinkUserVO,itripUserLinkUser);
                itripUserLinkUser.setUserId(currentUser.getId());
                Integer result = itripUserLinkUserService.addItripUserLinkUser(itripUserLinkUser);
                log.debug("userLinkUserList size " + result);
                return DtoUtil.returnSuccess("新增常用联系人信息成功", itripUserLinkUser);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("新增常用联系人信息失败", "100402");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @ApiOperation(value = "修改常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "修改常用联系人信息(可根据联系人姓名进行更改一条记录)")
    @RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
    public Dto<ItripUserLinkUser> modifyUserLinkUser(@RequestBody ItripModifyUserLinkUserVO itripModifyUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        log.debug("token name is from header : " + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        Dto dto = null;
        if (null != currentUser) {
            try {
                ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
                BeanUtils.copyProperties(itripModifyUserLinkUserVO,itripUserLinkUser);
                Integer result = itripUserLinkUserService.modifyItripUserLinkUser(itripUserLinkUser);
                log.debug("userLinkUserList size " + result);
                return DtoUtil.returnSuccess("修改常用联系人信息成功", itripUserLinkUser);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("修改常用联系人信息失败", "100402");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @ApiOperation(value = "删除常用联系人接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "删除常用联系人信息(可根据联系人姓名进行删除一条记录)")
    @RequestMapping(value = "/deluserlinkuser", method = RequestMethod.GET,produces = "application/json")
    public Dto<ItripUserLinkUser> delUserLinkUser(@RequestParam Long[] ids, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        log.debug("token name is from header : " + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        Dto dto = null;
        if (null != currentUser) {
            try {
                Integer result = itripUserLinkUserService.deleteItripUserLinkUserByIds(ids);
                log.debug("userLinkUserList size " + result);
                return DtoUtil.returnSuccess("删除常用联系人信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("删除常用联系人信息失败", "100402");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @RequestMapping(value = "/queryItripUserLinkUserPageByMap", method = RequestMethod.POST, produces = "application/json")
    public Dto<ItripUserLinkUser> queryItripUserLinkUserPageByMap(@RequestBody(required = false) ItripSearchUserLinkUserVO itripSearchUserLinkUserVO,@RequestBody(required = false) Integer pageNum, @RequestBody(required = false) Integer pageSize, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        log.debug("token name is from header : " + tokenString);
        if (pageNum==null){
            pageNum = 1;
        }
        if (pageSize==null){
            pageSize= 5;
        }
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        String linkUserName = (null == itripSearchUserLinkUserVO) ? null : itripSearchUserLinkUserVO.getLinkUserName();
        Dto dto = null;
        if (null != currentUser) {
            Map param = new HashMap();
            param.put("userId", currentUser.getId());
            param.put("linkUserName", linkUserName);
            try {
                Page<ItripSearchUserLinkUserVO> page = itripUserLinkUserService.queryItripUserLinkUserPageByMap(param,pageNum,pageSize);
                log.debug("userLinkUserList page " + page);
                return DtoUtil.returnSuccess("获取常用联系人信息成功",page);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取常用联系人信息失败", "100402");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
}
