package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripUser implements Serializable {

            private Long id;
            private String userCode;
            private String userPassword;
            private Integer userType;
            private String flatID;
            private String userName;
            private String weChat;
            private String QQ;
            private String weibo;
            private String baidu;
            private int activated;
            private Date creationDate;
            private Long createdBy;
            private Date modifyDate;
            private Long modifiedBy;


}
