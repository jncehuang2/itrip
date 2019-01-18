package cn.itrip.dao.productstore;

import cn.itrip.beans.pojo.ItripProductStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripProductStoreMapper {
    public abstract ItripProductStore getItripProductStoreById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripProductStore> getItripProductStoreListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripProductStoreCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripProductStore(ItripProductStore paramItripProductStore)
            throws Exception;

    public abstract Integer updateItripProductStore(ItripProductStore paramItripProductStore)
            throws Exception;

    public abstract Integer deleteItripProductStoreById(@Param("id") Long paramLong)
            throws Exception;
}
