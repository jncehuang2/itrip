package cn.itrip.beans.vo.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返回前端-根据订单ID查看个人订单详情-房型相关信息VO
 * Created by hanlu on 2017/5/18.
 */
@Getter
@Setter
public class ItripPersonalOrderRoomVO {

    private Long id;                    //订单ID
    private Long hotelId;               //酒店ID
    private String hotelName;           //酒店名称
    private Integer hotelLevel;         //酒店星级
    private String address;             //酒店位置
    private Long roomId;                //房型ID
    private String roomTitle;           //房型名称
    private Long roomBedTypeId;         //床型
    private Date checkInDate;           //入住时间
    private Date checkOutDate;          //退房时间
    private Integer count;              //预定数量
    private Integer bookingDays;        //预定天数
    private String linkUserName;        //入住人
    private String specialRequirement;  //特殊需求
    private BigDecimal payAmount;       //支付金额
    private Integer roomPayType;
    private Integer isHavingBreakfast;
    private String roomBedTypeName;
    private Integer checkInWeek;
    private Integer checkOutWeek;

}
