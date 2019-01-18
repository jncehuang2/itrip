package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripComment implements Serializable {

    private Long id;
    private Long hotelId;
    private Long productId;
    private Long orderId;
    private Integer productType;
    private String content;
    private Long userId;
    private Integer isHavingImg;
    private Integer positionScore;
    private Integer facilitiesScore;
    private Integer serviceScore;
    private Integer hygieneScore;
    private Integer score;
    private String tripMode;
    private Integer isOk;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;

}
