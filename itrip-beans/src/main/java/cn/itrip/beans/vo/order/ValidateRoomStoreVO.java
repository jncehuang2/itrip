package cn.itrip.beans.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 验证房间库存的传入参数VO
 * Created by XX on 17-5-16.
 */
@ApiModel(value = "ValidateRoomStoreVO",description = "验证房屋库存是否存足的VO")
@Getter
@Setter
public class ValidateRoomStoreVO implements Serializable{

    @ApiModelProperty("[必填，注：接收数字类型 酒店ID")
    private Long hotelId;
    @ApiModelProperty("[必填，注：接收数字类型 房间ID")
    private Long roomId;
    @ApiModelProperty("[必填，注：接收日期类型 入住时间")
    private Date checkInDate;
    @ApiModelProperty("[必填，注：接收日期类型 退房时间")
    private Date checkOutDate;
    @ApiModelProperty("[必填，默认请传1")
    private Integer count;

}
