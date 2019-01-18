package cn.itrip.beans.vo.userinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 接收客户端表单中的用户注册信息VO
 *
 * @author hduser
 *
 */
@Setter
@Getter
@ApiModel(value = "ItripUserVO", description = "用户注册信息")
public class ItripUserVO {

	@ApiModelProperty("[必填] 注册用户名称")
	private String userCode;
	@ApiModelProperty("[必填] 密码")
	private String userPassword;

	@ApiModelProperty("[非必填] 昵称")
	private String userName="";

}
