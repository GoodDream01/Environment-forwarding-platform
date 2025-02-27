package com.dt.zxhygl.entity;

import java.util.List;

/**
 * 后台向前台返回JSON，用于easyui的datagrid
 * 
 * @author 
 * 
 */
public class DataGridReturn {

	public DataGridReturn(Integer total, List data) {
		this.total = total;
		this.data = data;
	}

	private Integer total;// 总记录数
	private List data;// 每行记录
	private List footer;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
