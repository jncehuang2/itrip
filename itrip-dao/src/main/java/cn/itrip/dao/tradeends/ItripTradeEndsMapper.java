package cn.itrip.dao.tradeends;

import cn.itrip.beans.pojo.ItripTradeEnds;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripTradeEndsMapper {
    public abstract ItripTradeEnds getItripTradeEndsById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripTradeEnds> getItripTradeEndsListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripTradeEndsCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripTradeEnds(ItripTradeEnds paramItripTradeEnds)
            throws Exception;

    public abstract Integer updateItripTradeEnds(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer deleteItripTradeEndsById(@Param("id") Long paramLong)
            throws Exception;
}
