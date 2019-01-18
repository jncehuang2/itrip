package cn.itrip.beans.vo.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返回前端-根据订单ID查询出个人订单详情VO
 * Created by hanlu on 2017/5/18.
 */
@Getter
@Setter
public class ItripPersonalHotelOrderVO {

    private Long id;//订单ID

    private String orderNo;//订单号

    private Integer orderStatus;//订单状态（0：待支付 1:已取消 2:支付成功 3:已消费）

    private BigDecimal payAmount;//支付金额

    private Integer payType;//支付方式:1:支付宝 2:微信 3:到店付

    private Date creationDate;//预定时间

    private Integer bookType;//预定方式（0:WEB端 1:手机端 2:其他客户端）

    private Integer roomPayType;//房间支持的支付方式

    /**
     * 订单流程:
     * 1、待付款、待评价（已消费）、未出行（支付成功）
     * 流程: 已提交-->待支付-->支付成功-->已入住-->已点评
     * 2、已取消
     * 流程: 已提交-->待支付-->已取消
     */
    private Object orderProcess;

    private String processNode;

    private String noticePhone;//联系电话

}
