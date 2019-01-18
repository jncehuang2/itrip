package cn.itrip.dao.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderMapper {
    public abstract ItripHotelOrder getItripHotelOrderById(@Param("id") Long paramLong)
            throws Exception;

    public abstract ItripPersonalOrderRoomVO getItripHotelOrderRoomInfoById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelOrder> getItripHotelOrderListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelOrderCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelOrder(ItripHotelOrder paramItripHotelOrder)
            throws Exception;

    public abstract Integer updateItripHotelOrder(ItripHotelOrder paramItripHotelOrder)
            throws Exception;

    public abstract Integer deleteItripHotelOrderById(@Param("id") Long paramLong)
            throws Exception;

    public abstract Integer updateHotelOrderStatus(@Param("id") Long paramLong1, @Param("modifiedBy") Long paramLong2)
            throws Exception;

    public abstract int getRoomNumByRoomIdTypeAndDate(Integer paramInteger, String paramString1, String paramString2)
            throws Exception;

    public abstract int updateRoomStore(ItripHotelOrder paramItripHotelOrder)
            throws Exception;

    public abstract List<ItripListHotelOrderVO> getOrderListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getOrderCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer flushCancelOrderStatus()
            throws Exception;

    public abstract Integer flushSuccessOrderStatus()
            throws Exception;
}
