package cn.itrip.service.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface HotelOrdeService {
    List<ItripHotelOrder> getHotelOrderByMap(Map<String, Object> param) throws Exception;
    Integer getHotelOrderStore(Map<String, Object> param) throws Exception;
    PageInfo getHotelOrederListByPage(Map<String,Object> param,Integer pageNo,Integer pageSize) throws Exception;
}
