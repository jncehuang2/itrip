package cn.itrip.beans.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDto {

	@ApiModelProperty(value="单一参数传入")
	private String paramString;
	@ApiModelProperty(value="多个参数传入")
	private String[] paramStrings;

}
