package cn.itrip.beans.vo.comment;

import cn.itrip.beans.pojo.ItripImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 页面输入-新增评论VO
 * Created by hanlu on 2017/5/7.
 */
@Getter
@Setter
@ApiModel(value = "ItripAddCommentVO",description = "添加用户评论VO")
public class ItripCountCommentVO {

    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer improve;
    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;
    @ApiModelProperty("[必填，注：接收数字类型] 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片")
    private Integer isHavingImg;
    @ApiModelProperty("[必填，注：接收数字类型] 是否所有的评论")
    private Integer allcomment;

}
