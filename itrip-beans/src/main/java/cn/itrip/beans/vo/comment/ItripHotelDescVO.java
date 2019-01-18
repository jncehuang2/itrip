package cn.itrip.beans.vo.comment;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回前端-酒店相关信息（酒店名称、酒店星级）VO-（新增评论信息页面中使用）
 * Created by hanlu on 2017/5/15.
 */
@Getter
@Setter
public class ItripHotelDescVO {

    private Long id;//酒店id
    private String hotelName;//酒店名称
    private Integer hotelLevel;//酒店级别(1:经济酒店  2:二星级酒店  3:三星级酒店 4:四星级酒店 5:五星级酒店)

}
