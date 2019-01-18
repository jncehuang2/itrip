package cn.itrip.beans.vo.order;

import cn.itrip.beans.pojo.ItripOrderLinkUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 前端输入-生成订单VO
 * Created by donghai on 2017/5/16.
 */
@Getter
@Setter
@ApiModel(value = "ItripAddHotelOrderVO",description = "生成订单VO")
public class ItripAddHotelOrderVO {
    @ApiModelProperty("[修改订单时为必填，注:订单id]")
    private Long id;
    @ApiModelProperty("[必填，注：接收数字类型] 订单类型(0:旅游产品 1:酒店产品 2：机票产品)")
    private Integer orderType;
    @ApiModelProperty("[必填] 酒店ID")
    private Long hotelId;
    @ApiModelProperty("[必填] 酒店名称")
    private String hotelName;
    @ApiModelProperty("[必填] 房间ID")
    private Long roomId;
    @ApiModelProperty("[必填] 消耗数量")
    private Integer count;
    @ApiModelProperty("[必填] 入住时间")
    private Date checkInDate;
    @ApiModelProperty("[必填] 退房时间")
    private Date checkOutDate;
    @ApiModelProperty("[非必填] 联系手机号")
    private String noticePhone;
    @ApiModelProperty("[非必填] 联系邮箱")
    private String noticeEmail;
    @ApiModelProperty("[非必填] 特殊需求")
    private String specialRequirement;
    @ApiModelProperty("[非必填，注：接收数字类型] 是否需要发票（0：不需要 1：需要）")
    private Integer isNeedInvoice;
    @ApiModelProperty("[非必填，注：接收数字类型] 发票类型（0：个人 1：公司）")
    private Integer invoiceType;
    @ApiModelProperty("[非必填] 发票抬头")
    private String invoiceHead;
   /* @ApiModelProperty("[必填] 入住人id，多个id之间用逗号隔开")
    private String linkUserIds;
    @ApiModelProperty("[必填] 入住人名称，多个名称之间用逗号隔开")
    private String linkUserName;*/
    @ApiModelProperty("[必填] 入住人(只传递：id、linkUserName)")
    private List<ItripUserLinkUser> linkUser;

}
