package cn.itrip.beans.vo.userinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by donghai on 2017-06-08.
 */
@Getter
@Setter
@ApiModel(value = "ItripSearchUserLinkUserVO",description = "查询常用联系人")
public class ItripSearchUserLinkUserVO {
    @ApiModelProperty("[必填] 常用刚联系人姓名")
    private String linkUserName;
}
