package cn.itrip.dao.hoteltradingarea;

import cn.itrip.beans.pojo.ItripHotelTradingArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelTradingAreaMapper {
    public abstract ItripHotelTradingArea getItripHotelTradingAreaById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelTradingArea> getItripHotelTradingAreaListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelTradingAreaCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelTradingArea(ItripHotelTradingArea paramItripHotelTradingArea)
            throws Exception;

    public abstract Integer updateItripHotelTradingArea(ItripHotelTradingArea paramItripHotelTradingArea)
            throws Exception;

    public abstract Integer deleteItripHotelTradingAreaById(@Param("id") Long paramLong)
            throws Exception;
}
