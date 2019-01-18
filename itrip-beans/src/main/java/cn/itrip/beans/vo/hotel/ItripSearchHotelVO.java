package cn.itrip.beans.vo.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回前端-酒店的地区VO
 * Created by donghai on 2017/5/11.
 */
@ApiModel(value = "ItripSearchDetailsHotelVO",description = "查询酒店的地区")
@Getter
@Setter
public class ItripSearchHotelVO {
    @ApiModelProperty("[必填]读取ID ")
    private Long id;
    @ApiModelProperty("[必填] 地区名称")
    private String name;


}
