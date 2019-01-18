package cn.itrip.beans.vo.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返回前端-订单列表页VO
 * Created by hanlu on 2017/5/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripListHotelOrderVO {

    private Long id;                //订单id
    private Long hotelId;           //酒店id
    private String hotelName;	    //酒店的名称
    private String orderNo;         //订单号
    private Integer orderType;      //订单类型
    private String linkUserName;	//旅客的姓名，多个旅客的姓名之间用逗号隔开
    private Date creationDate;      //预定时间
    private Date checkInDate;       //入住时间（行程/有效日期）
    private BigDecimal payAmount;   //订单金额
    private Integer orderStatus;    //订单状态（0：待支付 1:已取消 2:支付成功 3:已消费）

}
