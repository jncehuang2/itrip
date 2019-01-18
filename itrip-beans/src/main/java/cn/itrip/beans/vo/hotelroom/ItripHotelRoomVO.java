package cn.itrip.beans.vo.hotelroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返回前端-酒店房型列表VO（酒店详情页）
 * Created by hanlu on 2017/5/11.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripHotelRoomVO implements Serializable{

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

}
