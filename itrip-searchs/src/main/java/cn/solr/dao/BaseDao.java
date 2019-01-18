package cn.solr.dao;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.dom4j.io.DocumentSource;

import java.io.IOException;
import java.util.List;

public class BaseDao<T> {
    private HttpSolrClient httpSolrClient = null;
    private QueryResponse queryResponse = null;

    public BaseDao(String url) {
        //初始化HttpSolrClient
        httpSolrClient = new HttpSolrClient(url);
        // 设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        // 建立连接的最长时间
        httpSolrClient.setConnectionTimeout(500);
    }

    public List<T> queryList(SolrQuery query, Class clazz) {
        List<T> list = null;
        try {
            queryResponse = httpSolrClient.query(query);
            list = queryResponse.getBeans(clazz);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //分页查询
//    @Override
//    public PageInfo getItripCommentByComment(ItripSearchCommentVO itripSearchCommentVO) throws Exception {
//        PageHelper.startPage(itripSearchCommentVO.getPageNo(),itripSearchCommentVO.getPageSize());
//        ItripComment itripComment  = new ItripComment();
//        itripComment.setHotelId(itripSearchCommentVO.getHotelId());
//        itripComment.setIsOk(itripSearchCommentVO.getIsOk());
//        itripComment.setIsHavingImg(itripSearchCommentVO.getIsHavingImg());
//        List<ItripComment> itripComments = commentMapper.getItripCommentByComment(itripComment);
//        PageInfo pageInfo = new PageInfo(itripComments);
//        return pageInfo;
//    }
    public PageInfo<T> queryPage(SolrQuery query, Class clazz, Integer pageSize, Integer pageNo) {
        List<T> list = null;
        PageInfo<T> pageInfo = null;
        try {
            query.setStart(pageNo);
            query.setRows(pageSize);
            queryResponse = httpSolrClient.query(query);
            PageHelper.startPage(pageNo,pageSize);
            list = queryResponse.getBeans(clazz);
            pageInfo = new PageInfo<>(list);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
