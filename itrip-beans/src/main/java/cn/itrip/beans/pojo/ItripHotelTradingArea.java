package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripHotelTradingArea implements Serializable {

    private Long id;
    private Long hotelId;
    private Long areaId;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;

}
