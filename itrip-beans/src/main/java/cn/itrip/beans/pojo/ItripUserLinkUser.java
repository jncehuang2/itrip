package cn.itrip.beans.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripUserLinkUser implements Serializable {

            private Long id;
            private String linkUserName;
            private Integer linkIdCardType;
            private String linkIdCard;
            private String linkPhone;
            private Long userId;
            //@JSONField(format="yyyy-MM-dd")
            private Date creationDate;
            private Long createdBy;
            private Date modifyDate;
            private Long modifiedBy;

}
