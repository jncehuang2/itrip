package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.hotel.*;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.hotel.HotelService;
import cn.itrip.service.hotel.HotelVideDescService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 酒店信息Controller
 * <p/>
 * 包括API接口：
 * 1、根据酒店id查询酒店拓展属性
 * 2、根据酒店id查询酒店介绍，酒店政策，酒店设施
 * 3、根据酒店id查询酒店特色属性列表
 * 4、根据type 和target id 查询酒店图片
 * 5、查询热门城市
 * 6、查询热门商圈列表（搜索-酒店列表）
 * 7、查询数据字典特色父级节点列表（搜索-酒店列表）
 * 8、根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
 * <p/>
 * 注：错误码（100201 ——100300）
 * <p/>
 * Created by hanlu on 2017/5/9.
 */

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotel")
@Slf4j
public class HotelController {

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    @Resource
    private HotelVideDescService hotelVideDescService;

    @Resource
    private HotelService hotelService;

    /****
     * 查询热门城市的接口
     *
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询热门城市", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryHotCity(@PathVariable Integer type) {
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOs = null;
        try {
            if (EmptyUtils.isNotEmpty(type)) {
                Map param = new HashMap();
                param.put("isHot", 1);
                param.put("isChina", type);
                itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    itripAreaDicVOs = itripAreaDics.stream()
                            .map(e -> new ItripAreaDicVO(e.getId(), e.getName()))
                            .collect(Collectors.toList());
                }

            } else {
                DtoUtil.returnFail("type不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10202");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("",itripAreaDicVOs);
    }


    /***
     * 查询酒店特色列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店特色列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryHotelFeature() {
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
    @ApiOperation(value = "查询城市商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取城市商圈(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/querytradearea/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryTradeArea(@PathVariable Integer cityId) {
        List<ItripAreaDicVO> itripAreaDicVOs = new ArrayList<>();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                Map param = new HashMap();
                param.put("parent", 2);
                param.put("isTradingArea", 1);
                param.put("cityId", cityId);
                List<ItripAreaDic> itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = itripAreaDics.stream()
                            .map(e -> new ItripAreaDicVO(e.getId(), e.getName()))
                            .collect(Collectors.toList());
                }
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取城市商圈成功",itripAreaDicVOs);
    }
    @ApiOperation(value = "查询酒店视频信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店视频信息(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getvideodesc/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<HotelVideoDescVO> getVideoDesc(@PathVariable Integer cityId) {
        HotelVideoDescVO hotelVideoDescVO = new HotelVideoDescVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                ItripHotel itripHotel = hotelVideDescService.getItripHotel(cityId);
                List<String> hotelFeatureList = hotelVideDescService.gethotelFeatureList(cityId);
                List<String> tradingAreaNameList = hotelVideDescService.getTradingAreaNameList(cityId);
                hotelVideoDescVO.setHotelName(itripHotel.getHotelName());
                hotelVideoDescVO.setHotelFeatureList(hotelFeatureList);
                hotelVideoDescVO.setTradingAreaNameList(tradingAreaNameList);
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店视频信息成功",hotelVideoDescVO);
    }
    @RequestMapping(value = "/queryhoteldetails/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchDetailsHotelVO> queryHotelDetails(@PathVariable Integer cityId) {
        List<ItripSearchDetailsHotelVO> voList = null;
        ItripSearchDetailsHotelVO itripSearchDetailsHotelVO = new ItripSearchDetailsHotelVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                ItripHotel itripHotel = hotelService.getItripHotel(cityId);
                voList = hotelService.getDetailsHotel(cityId);
                itripSearchDetailsHotelVO.setName("酒店介绍");
                itripSearchDetailsHotelVO.setDescription(itripHotel.getDetails());
                voList.add(0,itripSearchDetailsHotelVO);
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店描述和特色成功",voList);
    }
    @RequestMapping(value = "/queryhotelfacilities/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryHotelFacilities(@PathVariable Integer cityId) {
        ItripSearchFacilitiesHotelVO itripSearchFacilitiesHotelVO = new ItripSearchFacilitiesHotelVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                ItripHotel itripHotel = hotelService.getItripHotel(cityId);
                itripSearchFacilitiesHotelVO.setFacilities(itripHotel.getFacilities());
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店设施成功",itripSearchFacilitiesHotelVO.getFacilities());
    }
    @RequestMapping(value = "/queryhotelpolicy/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchPolicyHotelVO> queryHotelPolicy(@PathVariable Integer cityId) {
        ItripSearchPolicyHotelVO itripSearchPolicyHotelVO = new ItripSearchPolicyHotelVO();
        try {
            if (EmptyUtils.isNotEmpty(cityId)) {
                ItripHotel itripHotel = hotelService.getItripHotel(cityId);
                itripSearchPolicyHotelVO.setHotelPolicy(itripHotel.getHotelPolicy());
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店设施成功",itripSearchPolicyHotelVO.getHotelPolicy());
    }
    @RequestMapping(value = "/getimg/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getImg(@PathVariable Integer hotelId) {

        List<ItripImageVO> itripImageVOS = new ArrayList<>();
        try {
            if (EmptyUtils.isNotEmpty(hotelId)) {
                List<ItripImage> itripCommentImg = hotelService.getItripHotelImg(hotelId);
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
}
