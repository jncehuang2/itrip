package cn.itrip.beans.vo.order;

import lombok.Getter;
import lombok.Setter;

/**
 * 根据订单查询联系人返回VO
 * Created by zezhong.shang on 17-6-8.
 */
@Getter
@Setter
public class ItripOrderLinkUserVo {

    private Long linkUserId;
    private String linkUserName;
}
