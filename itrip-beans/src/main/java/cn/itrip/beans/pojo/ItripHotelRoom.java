package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ItripHotelRoom implements Serializable {

    private Long id;
    private Long hotelId;
    private String roomTitle;
    private BigDecimal roomPrice;
    private Long roomBedTypeId;
    private Integer isHavingBreakfast;
    private Integer payType;
    private Double satisfaction;
    private Integer isBook;
    private Integer isCancel;
    private Integer isTimelyResponse;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;

}
