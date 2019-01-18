package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelVideoDesMapper {
    ItripHotel getItripHotel(@Param("id") Integer id);
    List<String> getTradingAreaNameList(Integer id);
    List<String> gethotelFeatureList(Integer id);
}
