package cn.solr.service;

import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.solr.dao.HotelDao;
import cn.solr.dao.impl.HotelDaoImpl;
import cn.solr.entity.ItripHotelVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
public class HotelServiceImpl implements HotelService{

    private HotelDao hotelDao = new HotelDaoImpl();

    @Override
    public List<ItripHotelVO> queryHotelList(String keyword) {
        return hotelDao.queryHotelList(keyword);
    }

    @Override
    public List<ItripHotelVO> queryHotelList(String keyword, String flword, String fqword,Integer rows) {
        return hotelDao.queryHotelList(keyword, flword, fqword,rows);
    }

    @Override
    public Page<ItripHotelVO> getItripSearchHotel(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        StringBuffer tempQuery = new StringBuffer();
        int tempFlag = 0;
        if (EmptyUtils.isNotEmpty(searchHotelVO.getDestination())){
            tempQuery.append("and destination :"+ searchHotelVO.getDestination());
//            query.addFilterQuery("destination :"+ searchHotelVO.getDestination());
            tempFlag = 1;
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())){
            tempQuery.append("and hotelLevel :"+ searchHotelVO.getHotelLevel());
//            query.addFilterQuery("hotelLevel :"+ searchHotelVO.getHotelLevel());
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())){
            tempQuery.append("and keyword :"+ searchHotelVO.getKeywords());
//            query.addFilterQuery("keyword :"+ searchHotelVO.getKeywords());
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getFeatureIds())){
            StringBuffer buffer = new StringBuffer();
            int flag = 0;
            String featureIdArray[] = searchHotelVO.getFeatureIds().split(",");
            for (String featureIds : featureIdArray) {
                if (flag == 0){
                    buffer.append("and featureIds:,*"+featureIds+",*");
                }else{
                    buffer.append("and featureIds:,"+featureIds);
                }
            }
            tempQuery.append(buffer);
//            query.addFilterQuery(buffer.toString());
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getMaxPrice())|| EmptyUtils.isNotEmpty(searchHotelVO.getMinPrice())){
            // 组合价格区间条件
            if (searchHotelVO.getMaxPrice().intValue() != 0 || searchHotelVO.getMaxPrice() != 0) {
                tempQuery.append(" AND maxPrice:[" + searchHotelVO.getMinPrice() + " TO "
                        + searchHotelVO.getMaxPrice() + "]");
            }
//            query.addFilterQuery("maxPrice :"+ searchHotelVO.getMaxPrice());
        }
//        log.info(tempQuery.toString());
        query.setQuery(tempQuery.toString());
        if (EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())){
            query.setSort(searchHotelVO.getAscSort(),SolrQuery.ORDER.asc);
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getDescSort())){
            query.setSort(searchHotelVO.getDescSort(),SolrQuery.ORDER.desc);
        }
        Page<ItripHotelVO> page  = hotelDao.queryPageInfo(query,pageSize,pageNo);
        return page;
    }
}
