package cn.itrip.dao.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.PreAddOrderVO;

import java.util.List;
import java.util.Map;

public interface HotelOrderMapper {
    List<ItripHotelOrder> getHotelOrderByMap(Map<String, Object> param) throws Exception;
    Integer getHotelOrderStore(Map<String, Object> param) throws Exception;
}
