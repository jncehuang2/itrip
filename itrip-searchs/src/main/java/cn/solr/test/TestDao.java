package cn.solr.test;

import cn.solr.dao.HotelDao;
import cn.solr.dao.impl.HotelDaoImpl;
import cn.solr.entity.Hotel;
import cn.solr.entity.ItripHotelVO;

import java.util.List;

public class TestDao {
     public static void main(String[] args) {
        HotelDao hotelDao = new HotelDaoImpl();
        List<ItripHotelVO> list = hotelDao.queryHotelList("北京");
        for (ItripHotelVO hotel : list) {
            System.out.println(hotel.getId() + " " + hotel.getAddress());
        }
    }
}
