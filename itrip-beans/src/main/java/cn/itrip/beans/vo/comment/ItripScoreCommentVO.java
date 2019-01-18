package cn.itrip.beans.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回前端-酒店各类评分VO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItripScoreCommentVO {
	private float avgPositionScore;//点评查询页面酒店的位置得分
	private float avgFacilitiesScore;//点评查询页面酒店的设施得分
	private float avgServiceScore;//点评查询页面酒店的服务得分
	private float avgHygieneScore;//点评查询页面酒店的卫生得分
	private float avgScore;//点评查询页面酒店的总体得分

	
}
