package cn.itrip.beans.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回前端-通用字典VO
 * Created by hanlu on 2017/5/11.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripLabelDicVO {

    private Long id;
    private String name;
    private String description;
    private String pic="";

}
