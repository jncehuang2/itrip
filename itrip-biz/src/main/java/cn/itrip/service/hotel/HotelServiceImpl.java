package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.dao.hotel.HotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Resource
    private HotelMapper hotelMapper;

    @Override
    public ItripHotel getItripHotel(Integer id) {
        return hotelMapper.getItripHotel(id);
    }

    @Override
    public List<ItripSearchDetailsHotelVO> getDetailsHotel(Integer id) {
        return hotelMapper.getDetailsHotel(id);
    }

    @Override
    public List<ItripImage> getItripHotelImg(Integer targetId) throws Exception {
        return hotelMapper.getItripHotelImg(targetId);
    }

    @Override
    public ItripPersonalOrderRoomVO getItripPersonalOrderRoom(Integer orderId) {
        return hotelMapper.getItripPersonalOrderRoom(orderId);
    }


}
