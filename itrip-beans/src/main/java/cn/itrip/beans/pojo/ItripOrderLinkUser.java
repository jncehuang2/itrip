package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripOrderLinkUser implements Serializable {

    private Long id;
    private Long orderId;
    private Long linkUserId;
    private String linkUserName;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;

}
