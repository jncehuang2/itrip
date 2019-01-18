package cn.itrip.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回前端-Token相关VO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ItripTokenVO",description="用户认证凭据信息")
public class ItripTokenVO implements Serializable {
	/**
	 * 用户认证凭据
	 */
	@ApiModelProperty("用户认证凭据")
	private String token;
	/**
	 * 过期时间
	 */
	@ApiModelProperty("过期时间，单位：毫秒")
	private long expTime;
	/**
	 * 生成时间
	 */
	@ApiModelProperty("生成时间，单位：毫秒")
	private long genTime;
}
