package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("DictItem")
public class DictItem implements Serializable{
	
	private static final long serialVersionUID = 5825100115704060996L;
	/**主键**/
	private String id;
	/**排序号**/
	private int sort;
	/**字典内容**/
	private String text;
	/**值**/
	private String value;
	/**类型编码**/
	private String typeCode;
	/**状态 0有效 1无效**/
	private String status;
	/**备注**/
	private String bz;
	
	public DictItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
