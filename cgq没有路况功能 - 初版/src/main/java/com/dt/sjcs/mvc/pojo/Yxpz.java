package com.dt.sjcs.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Yxpz")
public class Yxpz {

	private String id;
	private String dwid;
	private String dwmc;
	private String yxlx;
	private String yxzh;
	private String yxmm;
	private String sbbh;
	private String zdbm;
	private String zdmc;

	public Yxpz() {
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

	public String getYxlx() {
		return yxlx;
	}

	public void setYxlx(String yxlx) {
		this.yxlx = yxlx;
	}

	public String getYxzh() {
		return yxzh;
	}

	public void setYxzh(String yxzh) {
		this.yxzh = yxzh;
	}

	public String getYxmm() {
		return yxmm;
	}

	public void setYxmm(String yxmm) {
		this.yxmm = yxmm;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

}
