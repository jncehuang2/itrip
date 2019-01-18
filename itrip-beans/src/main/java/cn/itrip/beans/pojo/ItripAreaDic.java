package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripAreaDic implements Serializable {

    private Long id;
    private String name;
    private String areaNo;
    private Long parent;
    private Integer isActivated;
    private Integer isTradingArea;
    private Integer isHot;
    private Integer level;
    private Integer isChina;
    private String pinyin;
    private Date creationDate;
    private Long createdBy;
    private Date modifyDate;
    private Long modifiedBy;
}
