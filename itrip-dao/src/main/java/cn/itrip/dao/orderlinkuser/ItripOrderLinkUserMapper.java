package cn.itrip.dao.orderlinkuser;

import cn.itrip.beans.pojo.ItripOrderLinkUser;
import cn.itrip.beans.vo.order.ItripOrderLinkUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripOrderLinkUserMapper {
    public abstract ItripOrderLinkUser getItripOrderLinkUserById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripOrderLinkUserVo> getItripOrderLinkUserListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripOrderLinkUserCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripOrderLinkUser(ItripOrderLinkUser paramItripOrderLinkUser)
            throws Exception;

    public abstract Integer updateItripOrderLinkUser(ItripOrderLinkUser paramItripOrderLinkUser)
            throws Exception;

    public abstract Integer deleteItripOrderLinkUserById(@Param("id") Long paramLong)
            throws Exception;

    public abstract Integer deleteItripOrderLinkUserByOrderId(@Param("orderId") Long paramLong)
            throws Exception;

    public abstract List<Long> getItripOrderLinkUserIdsByOrder()
            throws Exception;
}
