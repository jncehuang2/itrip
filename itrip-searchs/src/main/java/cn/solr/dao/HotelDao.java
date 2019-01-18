package cn.solr.dao;

import cn.itrip.common.Page;
import cn.solr.entity.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public interface HotelDao {

    public List<ItripHotelVO> queryHotelList(String keyword);
    public List<ItripHotelVO> queryHotelList(String keyword,String flword,String fqword,Integer rows);
    public Page queryPageInfo(SolrQuery solrQuery,Integer pageSize, Integer pageNo);
}
