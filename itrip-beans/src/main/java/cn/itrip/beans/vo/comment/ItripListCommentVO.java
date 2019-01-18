package cn.itrip.beans.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 返回前端-点评列表VO
 *
 */
@Getter
@Setter
public class ItripListCommentVO{
	private Long id;//评论id
	private String userCode;  //发表评论的用户的姓名
	private Integer hotelLevel;  //酒店的星级
	private Date checkInDate;  //入住时间
	private String roomTitle;  //房型名称
	private String tripModeName;//出游类型中文名称
	private String content;//评论内容
	private Date creationDate;//评论发表时间
	private Integer score;//综合评分
	private Integer isHavingImg;//是否有评论图片

	public ItripListCommentVO(Long id, String userCode, Integer hotelLevel, Date checkInDate, String roomTitle, String tripModeName, String content, Date creationDate, Integer score, Integer isHavingImg) {
		this.id = id;
		this.userCode = userCode;
		this.hotelLevel = hotelLevel;
		this.checkInDate = checkInDate;
		this.roomTitle = roomTitle;
		if (tripModeName.equals("108")){
			this.tripModeName = "商务出差";
		}else if(tripModeName.equals("109")){
			this.tripModeName = "朋友出游";
		}else if(tripModeName.equals("110")){
			this.tripModeName = "情侣出游";
		}else if(tripModeName.equals("111")){
			this.tripModeName = "家庭亲子";
		}else if(tripModeName.equals("112")){
			this.tripModeName = "独自旅行";
		}else if(tripModeName.equals("113")){
			this.tripModeName = "代入预定";
		}else{
			this.tripModeName = "其他";
		}
		this.content = content;
		this.creationDate = creationDate;
		this.score = score;
		this.isHavingImg = isHavingImg;
	}
}
