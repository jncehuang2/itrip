package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripProductStore implements Serializable {

    private Long id;
    private Integer productType;
    private Long productId;
    private Integer store;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;
}
