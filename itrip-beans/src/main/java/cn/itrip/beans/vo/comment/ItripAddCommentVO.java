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
public class ItripAddCommentVO {

    @ApiModelProperty("[必填] 酒店ID")
    private Long hotelId;
    @ApiModelProperty("[必填] 订单ID")
    private Long orderId;
    @ApiModelProperty("[必填] 房型ID")
    private Long productId;
    @ApiModelProperty("[必填，注：接收数字类型] 订单类型(0:旅游产品 1:酒店产品 2:机票产品)")
    private Integer productType;
    @ApiModelProperty("[必填，注：接收数字类型] 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片")
    private Integer isHavingImg;
    @ApiModelProperty("[必填] 位置评分")
    private Integer positionScore;
    @ApiModelProperty("[必填] 设施评分")
    private Integer facilitiesScore;
    @ApiModelProperty("[必填] 服务评分")
    private Integer serviceScore;
    @ApiModelProperty("[必填] 卫生评分")
    private Integer hygieneScore;
    @ApiModelProperty("[必填，注：接收数字类型] 出游类型（数据取自下拉列表）")
    private String tripMode;
    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;
    @ApiModelProperty("[非必填] 评论内容")
    private String content;
    @ApiModelProperty("[非必填] 评论图片，根据用户是否上传图片（图片网络路径数组）")
    private ItripImage[] itripImages;

}
