package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.dao.hotel.HotelVideoDesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelVideDescServiceImpl implements HotelVideDescService{
    @Resource
    private HotelVideoDesMapper hotelVideoDesMapper;
    @Override
    public ItripHotel getItripHotel(Integer id) {
        return hotelVideoDesMapper.getItripHotel(id);
    }

    @Override
    public List<String> getTradingAreaNameList(Integer id) {
        return hotelVideoDesMapper.getTradingAreaNameList(id);
    }

    @Override
    public List<String> gethotelFeatureList(Integer id) {
        return hotelVideoDesMapper.gethotelFeatureList(id);
    }
}
