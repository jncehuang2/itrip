package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ItripHotel implements Serializable {

            private Long id;
            private String hotelName;
            private Long countryId;
            private Long provinceId;
            private Long cityId;
            private String address;
            private String details;
            private String facilities;
            private String hotelPolicy;
            private Integer hotelType;
            private Integer hotelLevel;
            private Integer isGroupPurchase;
            private String redundantCityName;
            private String redundantProvinceName;
            private String redundantCountryName;
            private Integer redundantHotelStore;
            private Date creationDate;
            private Long createdBy;
            private Date modifyDate;
            private Long modifiedBy;

}
