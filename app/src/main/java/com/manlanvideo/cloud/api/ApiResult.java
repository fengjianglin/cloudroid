package com.manlanvideo.cloud.api;

import java.io.Serializable;


public class ApiResult<T> implements Serializable {

	private static final long serialVersionUID = -3399417713990706783L;

	/**
	 * 成功响应
	 */
	public static String OK = "1";

	/**
	 * 错误响应
	 */
	public static String ERROR = "2";

	/**
	 * 未登录
	 */
	public static String NOT_LOGIN = "3";

	/**
	 * 其它地方登陆
	 */
	public static String OTHER_WAS_LOGIN = "4";

	private T data;

	private String msg;

	private String status;

	protected ApiResult() {

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
