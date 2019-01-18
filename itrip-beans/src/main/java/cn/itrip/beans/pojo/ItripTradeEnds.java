package cn.itrip.beans.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单支付完成后，系统需对该订单进行后续处理，如减库存等。<br/>
 * 本类映射一条等待处理的支付结果。
 * @author hduser
 *
 */
@Getter
@Setter
public class ItripTradeEnds {

	private Long id;		//订单ID
	private String orderNo;	//订单编号
	private Integer flag;	//处理标识 0：未处理；1：处理中
	
}
