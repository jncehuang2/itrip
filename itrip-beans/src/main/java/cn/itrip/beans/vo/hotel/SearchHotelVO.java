package cn.itrip.beans.vo.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 前端输入-查询酒店搜索条件VO
 * Created by XX on 17-5-10.
 */
@Getter
@Setter
@ApiModel(value = "SearchHotelVO",description = "搜索酒店VO")
public class SearchHotelVO implements Serializable {

    @ApiModelProperty("[必填] 目的地")
    private String destination;
    @ApiModelProperty("[非必填] 酒店级别(1-5)")
    private Integer hotelLevel;
    @ApiModelProperty("[非必填] 关键词")
    private String keywords;
    @ApiModelProperty("[非必填] 商圈id(每次只能选一个)")
    private String tradeAreaIds;
    @ApiModelProperty("[非必填] 最低价")
    private Double minPrice;
    @ApiModelProperty("[非必填] 最高价")
    private Double maxPrice;
    @ApiModelProperty("[非必填] 酒店特色id(每次只能选一个)")
    private String featureIds;
    @ApiModelProperty("[非必填] 按照某个字段升序,取值为(isOkCount或avgScore或minPrice或hotelLevel)")
    private String ascSort;
    @ApiModelProperty("[非必填] 按照某个字段降序,取值为(isOkCount或avgScore或minPrice或hotelLevel)")
    private String  descSort;
    @ApiModelProperty("[非必填] 入住日期")
    private Date checkInDate;
    @ApiModelProperty("[非必填] 退房日期")
    private Date checkOutDate;

    private Integer pageSize;

    private Integer pageNo;

}
