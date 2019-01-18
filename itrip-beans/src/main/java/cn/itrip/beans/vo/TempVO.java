package cn.itrip.beans.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 用于给前端返回统一数据的VO 目前只有TestController再用
 * Created by XX on 17-5-23.
 */
@Getter
@Setter
public class TempVO {

    private String rootName;

    private List<Map<String,Object>> leafs;

}
