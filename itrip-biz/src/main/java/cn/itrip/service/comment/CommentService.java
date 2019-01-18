package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<ItripComment> getItripComment(Integer id) throws Exception;
    ItripComment getItripCommentByMap(Map<String, Object> param) throws Exception;
    Integer getCountComment(Map<String, Object> param) throws Exception;
    PageInfo getItripCommentByComment(ItripSearchCommentVO itripSearchCommentVO) throws Exception;
    List<ItripImage> getItripCommentImg(Integer id) throws Exception;
    ItripScoreCommentVO getItripCommentScore(Integer id) throws Exception;
    Integer AddItripComment(ItripComment itripComment);
}
