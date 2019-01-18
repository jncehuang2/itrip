package cn.solr.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.common.ValidationToken;
import cn.solr.entity.ItripHotelVO;
import cn.solr.service.HotelService;
import cn.solr.service.HotelServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotellist")
@Slf4j
public class HotelController {

    private HotelService hotelService = new HotelServiceImpl();

    @Resource
    private ValidationToken validationToken;

    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO) {
        List<ItripHotelVO> itripHotelVOS = null;
        try {
            if (EmptyUtils.isNotEmpty(searchHotCityVO)) {
                itripHotelVOS =  hotelService.queryHotelList(searchHotCityVO.getCityId().toString(),null,null,6);
            }else{
                DtoUtil.returnFail("cityId不能为空", "10201");
            }

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取热门城市6个酒店成功",itripHotelVOS);
    }
    @RequestMapping(value = "/searchItripHotelPage", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelVO> searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO) {
        Page<ItripHotelVO> page = null;
        if (searchHotelVO.getPageNo()==null || searchHotelVO.getPageSize()==null){
            searchHotelVO.setPageNo(1);
            searchHotelVO.setPageSize(5);
        }
        if (EmptyUtils.isEmpty(searchHotelVO) || EmptyUtils.isEmpty(searchHotelVO.getDestination())){
            return DtoUtil.returnFail("目的地不能为空","20002");
        }
        try {
            page = hotelService.getItripSearchHotel(searchHotelVO,searchHotelVO.getPageNo(),searchHotelVO.getPageSize());

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常,获取失败", "20001");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店（分页）成功",page);
    }
}
