package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelService {
    ItripHotel getItripHotel(Integer id);
    List<ItripSearchDetailsHotelVO> getDetailsHotel(Integer id);
    List<ItripImage> getItripHotelImg(Integer targetId) throws Exception;
    ItripPersonalOrderRoomVO getItripPersonalOrderRoom(Integer orderId);

}
