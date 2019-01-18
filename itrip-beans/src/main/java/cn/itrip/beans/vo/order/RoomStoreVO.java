package cn.itrip.beans.vo.order;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房间的库存VO
 * Created by XX on 17-5-16.
 */
@Getter
@Setter
public class RoomStoreVO implements Serializable{

    private Long hotelId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer count;
    private String hotelName;
    private Integer store;
    private BigDecimal price;

}
