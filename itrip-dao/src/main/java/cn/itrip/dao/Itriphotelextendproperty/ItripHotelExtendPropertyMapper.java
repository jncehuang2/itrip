package cn.itrip.dao.Itriphotelextendproperty;

import cn.itrip.beans.pojo.ItripHotelExtendProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelExtendPropertyMapper {
    public abstract ItripHotelExtendProperty getItripHotelExtendPropertyById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelExtendProperty> getItripHotelExtendPropertyListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelExtendPropertyCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelExtendProperty(ItripHotelExtendProperty paramItripHotelExtendProperty)
            throws Exception;

    public abstract Integer updateItripHotelExtendProperty(ItripHotelExtendProperty paramItripHotelExtendProperty)
            throws Exception;

    public abstract Integer deleteItripHotelExtendPropertyById(@Param("id") Long paramLong)
            throws Exception;
}
