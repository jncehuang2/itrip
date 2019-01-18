package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.dao.comment.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<ItripComment> getItripComment(Integer id) throws Exception {
        return commentMapper.getItripComment(id);
    }

    @Override
    public ItripComment getItripCommentByMap(Map<String, Object> param) throws Exception {
        return commentMapper.getItripCommentByMap(param);
    }

    @Override
    public Integer getCountComment(Map<String, Object> param) throws Exception {
        return commentMapper.getCountComment(param);
    }

    @Override
    public PageInfo getItripCommentByComment(ItripSearchCommentVO itripSearchCommentVO) throws Exception {
        PageHelper.startPage(itripSearchCommentVO.getPageNo(),itripSearchCommentVO.getPageSize());
        ItripComment itripComment  = new ItripComment();
        itripComment.setHotelId(itripSearchCommentVO.getHotelId());
        if (itripSearchCommentVO.getIsOk()!=-1){
            itripComment.setIsOk(itripSearchCommentVO.getIsOk());
        }else {
            itripComment.setIsOk(null);
        }
        if (itripSearchCommentVO.getIsHavingImg()!=-1){
            itripComment.setIsHavingImg(itripSearchCommentVO.getIsHavingImg());
        }else{
            itripComment.setIsHavingImg(null);
        }
        List<ItripComment> itripComments = commentMapper.getItripCommentByComment(itripComment);
        PageInfo pageInfo = new PageInfo(itripComments);
        return pageInfo;
    }

    @Override
    public List<ItripImage> getItripCommentImg(Integer id) throws Exception {
        return commentMapper.getItripCommentImg(id);
    }

    @Override
    public ItripScoreCommentVO getItripCommentScore(Integer id) throws Exception {
        return commentMapper.getItripCommentScore(id);
    }

    @Override
    public Integer AddItripComment(ItripComment itripComment) {
        return commentMapper.AddItripComment(itripComment);
    }
}
