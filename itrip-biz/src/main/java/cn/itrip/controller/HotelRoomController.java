package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.common.DateUtil;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.hotelroom.ItripHotelRoomService;
import cn.itrip.service.image.ImageService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelroom")
@Slf4j
public class HotelRoomController {

    @Resource
    private ImageService imageService;

    @Resource
    private ItripHotelRoomService itripHotelRoomService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    @RequestMapping(value = "/getimg/{roomId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getImg(@PathVariable Integer roomId) {

        List<ItripImageVO> itripImageVOS = new ArrayList<>();
        try {
            if (EmptyUtils.isNotEmpty(roomId)) {
                Map param = new HashMap();
                param.put("type", 1);
                param.put("targetId",roomId);
                List<ItripImage> itripImages = imageService.getImgUrlByMap(param);
                if (EmptyUtils.isNotEmpty(itripImages)) {
                    itripImageVOS = itripImages.stream()
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
        return DtoUtil.returnSuccess("获取酒店房型的图片成功",itripImageVOS);
    }
    @ApiOperation(value = "查询所有床型", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取所有床型(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelroombed", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryotelroombed() {
        List<ItripLabelDicVO> itripAreaDicVOs = new ArrayList<>();
        try {
            Map param = new HashMap();
            param.put("parentId", 1);
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
        return DtoUtil.returnSuccess("获取所有床型成功",itripAreaDicVOs);
    }
    @ApiOperation(value = "查询酒店房间列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店房间列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100303 : 酒店id不能为空,酒店入住及退房时间不能为空,入住时间不能大于退房时间</p>" +
            "<p>100304 : 系统异常</p>")
    @RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Dto<List<ItripHotelRoomVO>> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO vo) {
        List<List<ItripHotelRoomVO>> hotelRoomVOList = null;
        try {
            Map<String, Object> param = new HashMap();
            if (EmptyUtils.isEmpty(vo.getHotelId())) {
                return DtoUtil.returnFail("酒店ID不能为空", "100303");
            }
            if (EmptyUtils.isEmpty(vo.getStartDate()) || EmptyUtils.isEmpty(vo.getEndDate())) {
                return DtoUtil.returnFail("必须填写酒店入住及退房时间", "100303");
            }
            if (EmptyUtils.isNotEmpty(vo.getStartDate()) && EmptyUtils.isNotEmpty(vo.getEndDate())) {
                if (vo.getStartDate().getTime() > vo.getEndDate().getTime()) {
                    return DtoUtil.returnFail("入住时间不能大于退房时间", "100303");
                }
                List<Date> dates = DateUtil.getBetweenDates(vo.getStartDate(), vo.getEndDate());
                param.put("timesList", dates);
            }

            vo.setIsHavingBreakfast(EmptyUtils.isEmpty(vo.getIsHavingBreakfast()) ? null : vo.getIsHavingBreakfast());
            vo.setIsBook(EmptyUtils.isEmpty(vo.getIsBook()) ? null : vo.getIsBook());
            vo.setIsTimelyResponse(EmptyUtils.isEmpty(vo.getIsTimelyResponse()) ? null : vo.getIsTimelyResponse());
            vo.setRoomBedTypeId(EmptyUtils.isEmpty(vo.getRoomBedTypeId()) ? null : vo.getRoomBedTypeId());
            vo.setIsCancel(EmptyUtils.isEmpty(vo.getIsCancel()) ? null : vo.getIsCancel());
            vo.setPayType(EmptyUtils.isEmpty(vo.getPayType()) ? null : vo.getPayType());

            param.put("hotelId", vo.getHotelId());
            param.put("isBook", vo.getIsBook());
            param.put("isHavingBreakfast", vo.getIsHavingBreakfast());
            param.put("isTimelyResponse", vo.getIsTimelyResponse());
            param.put("roomBedTypeId", vo.getRoomBedTypeId());
            param.put("isCancel", vo.getIsCancel());
            param.put("payType", vo.getPayType()==3?null:vo.getPayType());

            List<ItripHotelRoomVO> originalRoomList = itripHotelRoomService.getItripHotelRoomListByMap(param);
            hotelRoomVOList = new ArrayList();
            for (ItripHotelRoomVO roomVO : originalRoomList) {
                List<ItripHotelRoomVO> tempList = new ArrayList<ItripHotelRoomVO>();
                tempList.add(roomVO);
                hotelRoomVOList.add(tempList);
            }
            return DtoUtil.returnSuccess("获取成功", hotelRoomVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取酒店房型列表失败", "100304");
        }
    }
}
