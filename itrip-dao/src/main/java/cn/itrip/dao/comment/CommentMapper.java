package cn.itrip.dao.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    List<ItripComment> getItripComment(@Param("hotelId") Integer id) throws Exception;
    Integer getCountComment(Map<String, Object> param) throws Exception;
    List<ItripComment> getItripCommentByComment(ItripComment itripComment) throws Exception;
    List<ItripImage> getItripCommentImg(@Param("targetId") Integer targetId) throws Exception;
    ItripScoreCommentVO getItripCommentScore(@Param("hotelId") Integer id) throws Exception;
    Integer AddItripComment(ItripComment itripComment);
    ItripComment getItripCommentByMap(Map<String, Object> param) throws Exception;
}
