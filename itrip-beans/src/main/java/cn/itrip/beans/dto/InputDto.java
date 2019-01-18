package cn.itrip.beans.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据传输对象（输入对象）
 */
@Getter
@Setter
public class InputDto {

	@ApiModelProperty(value="单一参数传入")
	private String paramString;
	@ApiModelProperty(value="多个参数传入")
	private String[] paramStrings;
}
