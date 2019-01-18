package cn.itrip.beans.vo.order;

import cn.itrip.beans.pojo.ItripOrderLinkUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 前端输入-修改订单VO
 * Created by donghai on 2017/5/18.
 */
@Getter
@Setter
@ApiModel(value = "ItripModifyHotelOrderVO", description = "修改订单VO")
public class ItripModifyHotelOrderVO implements Serializable {
    @ApiModelProperty("[必填，主键]")
    private Long id;
    @ApiModelProperty("[必填，注：接收数字类型] 支付方式(:1:支付宝 2:微信 3:到店付)")
    private Integer payType;
    private Integer orderType;
    private String orderNo;
    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private Integer count;
    private Integer bookingDays;
    private Date checkInDate;
    private Date checkOutDate;
    private String noticePhone;
    private String noticeEmail;
    private String specialRequirement;
    private Integer isNeedInvoice;
    private Integer invoiceType;
    private String invoiceHead;
    private String linkUserName;
    private Integer bookType;
    private List<ItripOrderLinkUserVo> itripOrderLinkUserList;

}
