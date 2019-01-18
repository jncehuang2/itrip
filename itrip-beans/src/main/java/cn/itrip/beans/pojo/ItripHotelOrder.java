package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class ItripHotelOrder implements Serializable {

    private Long id;
    private Long userId;
    private Integer orderType;
    private String orderNo;
    private String tradeNo;
    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private Integer count;
    private Integer bookingDays;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer orderStatus;
    private BigDecimal payAmount;
    private Integer payType;
    private String noticePhone;
    private String noticeEmail;
    private String specialRequirement;
    private Integer isNeedInvoice;
    private Integer invoiceType;
    private String invoiceHead;
    private String linkUserName;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;
    private Integer bookType;

}
