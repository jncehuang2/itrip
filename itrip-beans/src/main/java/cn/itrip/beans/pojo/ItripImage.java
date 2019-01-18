package cn.itrip.beans.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripImage implements Serializable {

    private Long id;
    private String type;
    private Long targetId;
    private Integer position;
    private String imgUrl;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;
}
