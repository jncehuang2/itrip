package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelVideDescService {
    ItripHotel getItripHotel(Integer id);
    List<String> getTradingAreaNameList(Integer id);
    List<String> gethotelFeatureList(Integer id);
}
