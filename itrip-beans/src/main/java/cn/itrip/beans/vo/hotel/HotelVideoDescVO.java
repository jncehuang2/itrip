package cn.itrip.beans.vo.hotel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 返回前端-酒店详情页视频文字描述（酒店特色、商圈、酒店名称）VO
 * Created by hanlu on 2017/5/24.
 */
@Getter
@Setter
@ApiModel(value = "HotelVideoDescVO",description = "酒店特色、商圈、酒店名称（视频文字描述）")

public class HotelVideoDescVO {

    private String hotelName;   //酒店名称

    private List<String> tradingAreaNameList; //酒店所属商圈

    private List<String> hotelFeatureList; //酒店特色

}
