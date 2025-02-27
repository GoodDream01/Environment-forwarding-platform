package com.dt.zxhygl.entity;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class AjaxJson {

	private boolean success;// 是否成功
	private String msg;// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	
	
	public AjaxJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AjaxJson(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}


	public AjaxJson(boolean success, String msg, Object obj, Map<String, Object> attributes) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
		this.attributes = attributes;
	}



	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
}
