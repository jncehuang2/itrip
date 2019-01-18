package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper {
    ItripHotel getItripHotel(@Param("id") Integer id);
    List<ItripSearchDetailsHotelVO> getDetailsHotel(Integer id);
    List<ItripImage> getItripHotelImg(@Param("targetId") Integer targetId) throws Exception;
    List<ItripHotel> getItriptradeArea(@Param("cityId") Integer cityId);
    ItripPersonalOrderRoomVO getItripPersonalOrderRoom(Integer orderId);
}
