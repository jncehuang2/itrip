package cn.itrip.dao.itriphotelfeature;

import cn.itrip.beans.pojo.ItripHotelFeature;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelFeatureMapper {
    public abstract ItripHotelFeature getItripHotelFeatureById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelFeature> getItripHotelFeatureListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelFeatureCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelFeature(ItripHotelFeature paramItripHotelFeature)
            throws Exception;

    public abstract Integer updateItripHotelFeature(ItripHotelFeature paramItripHotelFeature)
            throws Exception;

    public abstract Integer deleteItripHotelFeatureById(@Param("id") Long paramLong)
            throws Exception;
}
