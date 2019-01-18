package cn.itrip.beans.vo.store;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 验证房间库存时，返回的库存列表VO
 * Created by XX on 17-5-17.
 */
@Getter
@Setter
public class StoreVO {

    private Long roomId;

    private Date date;

    private Integer store;

}

