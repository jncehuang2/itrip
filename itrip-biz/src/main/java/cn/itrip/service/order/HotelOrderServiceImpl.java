package cn.itrip.service.order;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.dao.order.HotelOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HotelOrderServiceImpl implements HotelOrdeService{

    @Resource
    private HotelOrderMapper hotelOrderMapper;

    @Override
    public List<ItripHotelOrder> getHotelOrderByMap(Map<String, Object> param) throws Exception {
        return hotelOrderMapper.getHotelOrderByMap(param);
    }

    @Override
    public Integer getHotelOrderStore(Map<String, Object> param) throws Exception {
        return hotelOrderMapper.getHotelOrderStore(param);
    }

    @Override
    public PageInfo getHotelOrederListByPage(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo,pageSize);
        List<ItripHotelOrder> itripHotelOrders = hotelOrderMapper.getHotelOrderByMap(param);
        PageInfo pageInfo = new PageInfo(itripHotelOrders);
        return pageInfo;
    }
}
