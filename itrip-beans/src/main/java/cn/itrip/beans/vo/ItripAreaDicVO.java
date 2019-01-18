package cn.itrip.beans.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回前端-区域VO
 * Created by XX on 17-5-11.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripAreaDicVO implements Serializable{

    private Long id;

    private String name;


}
