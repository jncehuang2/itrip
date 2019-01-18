package cn.itrip.beans.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zezhong.shang on 17-5-16.
 */
@ApiModel(value = "PreAddOrderVO",description = "生成订单前获取预订信息的VO")
@Getter
@Setter
@NoArgsConstructor
public class PreAddOrderVO implements Serializable{

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

    private String hotelName;
    private Integer store;
    private BigDecimal price;

    public PreAddOrderVO(Long hotelId, Long roomId, Date checkInDate, Date checkOutDate, Integer count, String hotelName, BigDecimal price) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.count = count;
        this.hotelName = hotelName;
        this.price = price;
    }
}
