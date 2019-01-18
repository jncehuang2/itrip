package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.*;
import cn.itrip.common.*;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.comment.CommentService;
import cn.itrip.service.hotel.HotelService;
import cn.itrip.service.image.ImageService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/comment")
@Slf4j
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private HotelService hotelService;
    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;
    @Resource
    private ImageService imageService;
    @Resource
    private ValidationToken validationToken;

    @RequestMapping(value = "/gethotelscore/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> getHotelScore(@PathVariable Integer cityId) {
        ItripScoreCommentVO itripScoreCommentVO = null;
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                itripScoreCommentVO = commentService.getItripCommentScore(cityId);
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店评分成功",itripScoreCommentVO);
    }
    @RequestMapping(value = "/getcount/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripCountCommentVO> getCount(@PathVariable Integer cityId) {

        ItripCountCommentVO itripCountCommentVO = new ItripCountCommentVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                Map param = new HashMap();
                param.put("hotelId", cityId);
                itripCountCommentVO.setAllcomment(commentService.getCountComment(param));
                param.put("isOk",0);
                log.info("isOk>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+param.get("isOk").toString());
                itripCountCommentVO.setImprove(commentService.getCountComment(param));
                param.put("isOk",1);
                log.info("isOk>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+param.get("isOk").toString());
                itripCountCommentVO.setIsOk(commentService.getCountComment(param));
                param.remove("isOk");
                param.put("isHavingImg",1);
                log.info("isHavingImg>>>>>>>>>>>>>>>>>>>>>>>"+param.get("isHavingImg").toString());
                itripCountCommentVO.setIsHavingImg(commentService.getCountComment(param));
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取评论数量成功",itripCountCommentVO);
    }
    @RequestMapping(value = "/getcommentlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripListCommentVO>> getCommentList(@RequestBody ItripSearchCommentVO itripSearchCommentVO, HttpServletRequest request) {
        //todo
//        String tokenString = CookieUtil.readLoginToken(request);
//        log.info("tokenString>>>>>>>>>>>>>" + tokenString);
//        log.debug("token name is from header : " + tokenString);
//        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripListCommentVO> itripListCommentVOS = new ArrayList<>();
        List<ItripComment> itripComments = new ArrayList<>();
        Page<ItripListCommentVO> page = new Page<>();
        try {
//            if (null != currentUser) {
                ItripComment itripComment  = new ItripComment();
                PageInfo pageInfo = commentService.getItripCommentByComment(itripSearchCommentVO);
                itripComments = pageInfo.getList();
                if (EmptyUtils.isNotEmpty(itripComments)) {
                    itripListCommentVOS = itripComments.stream()
                            .map(e -> new ItripListCommentVO(e.getId(),null,null,new Date(),null,e.getTripMode(),e.getContent(),e.getCreationDate(),e.getScore(),e.getIsHavingImg()))
                            .collect(Collectors.toList());
                }else{
                    return DtoUtil.returnFail("您需要登录", "10205");
                }
                page.setBeginPos(pageInfo.getFirstPage());
                page.setCurPage(pageInfo.getPageNum());
                page.setTotal((int)pageInfo.getTotal());
                page.setPageSize(itripSearchCommentVO.getPageSize());
                page.setPageCount(pageInfo.getSize());
                for (ItripListCommentVO itripListCommentVO : itripListCommentVOS) {
                    log.info(itripListCommentVO.getContent());
                }
                page.setRows(itripListCommentVOS);
//            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取评论内容列表成功",page);
    }
    @RequestMapping(value = "/getimg/{commentId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getImg(@PathVariable Integer commentId) {

        List<ItripImageVO> itripImageVOS = new ArrayList<>();
        try {
            if (EmptyUtils.isNotEmpty(commentId)) {
                List<ItripImage> itripCommentImg = commentService.getItripCommentImg(commentId);
                if (EmptyUtils.isNotEmpty(itripCommentImg)) {
                    itripImageVOS = itripCommentImg.stream()
                            .map(e -> new ItripImageVO(e.getPosition(),e.getImgUrl()))
                            .collect(Collectors.toList());
                }
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取评论附带的图片成功",itripImageVOS);
    }
    @RequestMapping(value = "/gethoteldesc/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelDescVO> getHotelDesc(@PathVariable Integer hotelId) {

        ItripHotelDescVO itripHotelDescVO = new ItripHotelDescVO();
        try {
            if (EmptyUtils.isNotEmpty(hotelId)) {
                ItripHotel itripHotel = hotelService.getItripHotel(hotelId);
                BeanUtils.copyProperties(itripHotel,itripHotelDescVO);
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店详情（评论)成功",itripHotelDescVO);
    }
    @RequestMapping(value = "/gettraveltype", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> getTravelType() {
        List<ItripLabelDicVO> itripAreaDicVOs = new ArrayList<>();
        try {
            Map param = new HashMap();
            param.put("parentId", 16);
            List<ItripLabelDic> itripLabelDics = itripLabelDicService.getItripLabelDicListByMap(param);
            if (EmptyUtils.isNotEmpty(itripLabelDics)) {
                itripAreaDicVOs = itripLabelDics.stream()
                        .map(e -> new ItripLabelDicVO(e.getId(),e.getName(),e.getDescription(),e.getPic()))
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店特色列表成功",itripAreaDicVOs);
    }
    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripAddCommentVO> add(@RequestBody ItripAddCommentVO itripAddCommentVO) {
        ItripComment itripComment = new ItripComment();
        int result = 0;
        try {
            BeanUtils.copyProperties(itripAddCommentVO,itripComment);
            Integer count = commentService.AddItripComment(itripComment);
            Map param = new HashMap();
            param.put("hotelId",itripAddCommentVO.getHotelId());
            param.put("orderId",itripAddCommentVO.getOrderId());
            param.put("productId",itripAddCommentVO.getProductId());
            param.put("content",itripAddCommentVO.getContent());
            itripComment = commentService.getItripCommentByMap(param);
            if (itripAddCommentVO.getItripImages()!=null){
                for (ItripImage image : itripAddCommentVO.getItripImages()) {
                    result =  imageService.addImgUrl(image);
                }
                if (result<1 || count < 1){
                    return DtoUtil.returnSuccess("新增评论失败");
                }
            }else{
                if (count < 1){
                    return DtoUtil.returnSuccess("新增评论失败");
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10202");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("新增评论成功");
    }
}
