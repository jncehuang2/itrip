package cn.solr.dao.impl;

import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.Page;
import cn.solr.dao.BaseDao;
import cn.solr.dao.HotelDao;
import cn.solr.entity.ItripHotelVO;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public class HotelDaoImpl implements HotelDao {
    public static String url = "http://localhost:9080/solr/hotel/";

    private BaseDao<ItripHotelVO> hotelBaseDao = new BaseDao<ItripHotelVO>(url);

    public List<ItripHotelVO> queryHotelList(String keyword) {
        SolrQuery solrQuery = new SolrQuery("keyword:" + keyword);
        List<ItripHotelVO> list = hotelBaseDao.queryList(solrQuery, ItripHotelVO.class);
        return list;
    }
    public List<ItripHotelVO> queryHotelList(String keyword,String flword,String fqword,Integer rows) {
        SolrQuery solrQuery = new SolrQuery("cityId:" + keyword);
        solrQuery.setRows(rows);
        List<ItripHotelVO> list = hotelBaseDao.queryList(solrQuery, ItripHotelVO.class);
        return list;
    }

    public Page queryPageInfo(SolrQuery solrQuery,Integer pageSize, Integer pageNo) {
        Page<ItripHotelVO> page = new Page<>();
        PageInfo<ItripHotelVO> pageInfo = hotelBaseDao.queryPage(solrQuery,ItripHotelVO.class,pageSize,pageNo);
        page.setBeginPos(pageInfo.getFirstPage());
        page.setCurPage(pageInfo.getPageNum());
        page.setTotal((int)pageInfo.getTotal());
        page.setPageSize(pageInfo.getPageSize());
        page.setPageCount(pageInfo.getSize());
        page.setRows(pageInfo.getList());
        return page;
    }
}
