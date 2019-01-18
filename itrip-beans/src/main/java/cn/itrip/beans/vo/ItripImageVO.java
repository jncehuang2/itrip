package cn.itrip.beans.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回前端-图片对象VO
 * Created by hanlu on 2017/5/10.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripImageVO {

    private Integer position;//页面图片展现顺序
    private String imgUrl;//图片的URL访问路径


}
