package com.dt.sjcs.mvc.pojo;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Yjfsjl")
public class Yjfsjl {

	private String id;
	private String dwid;
	private String dwmc;
	private String yxzh;
	private String wjlj;
	private Date date;

	public Yjfsjl() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDwid() {
		return dwid;
	}

	public void setDwid(String dwid) {
		this.dwid = dwid;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getYxzh() {
		return yxzh;
	}

	public void setYxzh(String yxzh) {
		this.yxzh = yxzh;
	}

	public String getWjlj() {
		return wjlj;
	}

	public void setWjlj(String wjlj) {
		this.wjlj = wjlj;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
