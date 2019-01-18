package cn.itrip.beans.vo.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回前端-酒店政策VO（酒店详情页）
 * Created by donghai on 2017/5/11.
 */
@ApiModel(value = "ItripSearchPolicyHotelVO",description = "查询酒店的政策")
@Getter
@Setter
public class ItripSearchPolicyHotelVO {
    @ApiModelProperty("[必填] 酒店政策")
    private String hotelPolicy;

}
