package cn.itrip.beans.vo.userinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 前端输入-新增常用联系人VO
 * Created by donghai on 2017/5/10.
 */
@Getter
@Setter
@ApiModel(value = "ItripAddUserLinkUserVO",description = "添加常用联系人")
public class ItripAddUserLinkUserVO {

    @ApiModelProperty("[必填] 常用刚联系人姓名")
    private String linkUserName;
    @ApiModelProperty("[必填] 证件类型")
    private Integer linkIdCardType;
    @ApiModelProperty("[必填] 证件号码")
    private String linkIdCard;
    @ApiModelProperty("[非必填] 联系电话")
    private String linkPhone;
    @ApiModelProperty("[必填] 用户ID")
    private Long userId;

}
