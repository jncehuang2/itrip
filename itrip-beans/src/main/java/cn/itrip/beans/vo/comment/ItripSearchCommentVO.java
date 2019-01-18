package cn.itrip.beans.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 前端输入-评论搜索条件VO
 * Created by hanlu on 2017/5/10.
 */
@Getter
@Setter
@ApiModel(value = "ItripSearchCommentVO",description = "搜索评论VO")
public class ItripSearchCommentVO {

    @ApiModelProperty("[必填] 酒店ID")
    private Long hotelId;

    @ApiModelProperty("[必填，注：接收数字类型] 是否有评论照片（0 无图片 1 有图片）")
    private Integer isHavingImg;//是否有评论图片（0 无图片 1 有图片）

    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;//是否满意（0：有待改善 1：值得推荐）

    @ApiModelProperty("[必填] 页面容量")
    private Integer pageSize;

    @ApiModelProperty("[必填] 页码）")
    private Integer pageNo;

}
