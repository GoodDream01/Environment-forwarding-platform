package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("DictType")
public class DictType implements Serializable{

	private static final long serialVersionUID = -5811346237192671496L;
	
	/**主键**/
	private String id;
	/**编码**/
	private String code;
	/**字典类型**/
	private String text;
	/**状态**/
	private String status;
	
	
	
	public DictType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DictType(String id, String code, String text, String status) {
		super();
		this.id = id;
		this.code = code;
		this.text = text;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
