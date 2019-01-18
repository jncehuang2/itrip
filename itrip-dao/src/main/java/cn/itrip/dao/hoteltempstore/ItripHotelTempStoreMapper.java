package cn.itrip.dao.hoteltempstore;

import cn.itrip.beans.pojo.ItripHotelTempStore;
import cn.itrip.beans.vo.store.StoreVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelTempStoreMapper {
    public abstract ItripHotelTempStore getItripHotelTempStoreById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelTempStore> getItripHotelTempStoreListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelTempStoreCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelTempStore(ItripHotelTempStore paramItripHotelTempStore)
            throws Exception;

    public abstract Integer updateItripHotelTempStore(ItripHotelTempStore paramItripHotelTempStore)
            throws Exception;

    public abstract Integer deleteItripHotelTempStoreById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<StoreVO> queryRoomStore(Map<String, Object> paramMap)
            throws Exception;

    public void setMode();

    public abstract void flushStore(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer updateRoomStore(Map<String, Object> paramMap)
            throws Exception;
}
