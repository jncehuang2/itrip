package cn.itrip.beans.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据传输对象（后端输出对象）
 * @param <T>
 * Created by hanlu on 2017/5/7.
 */
@Getter
@Setter
public class Dto<T>{
	private String success; //判断系统是否出错做出相应的true或者false的返回，与业务无关，出现的各种异常
	private String errorCode;//该错误码为自定义，一般0表示无错
	private String msg;//对应的提示信息
	private T data;//具体返回数据内容(pojo、自定义VO、其他)
	
}