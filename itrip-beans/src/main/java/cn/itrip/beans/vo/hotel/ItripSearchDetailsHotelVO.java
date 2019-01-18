package cn.itrip.beans.vo.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回前端-酒店特色和介绍VO
 * Created by donghai on 2017/5/11.
 */
@ApiModel(value = "ItripSearchDetailsHotelVO",description = "查询酒店的特色和介绍")
@Getter
@Setter
public class ItripSearchDetailsHotelVO {
    @ApiModelProperty("[必填] 特色名称")
    private String name;
    @ApiModelProperty("[必填] 特色描述")
    private String description;

}
