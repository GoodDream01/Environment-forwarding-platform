package com.dt.cgq.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Ycsj")
public class Ycsj {

	private String id;
	private String sbbh;
	private String mc;
	private String devid;
	private String splj;
	private String xtsj;
	
	public Ycsj() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getSplj() {
		return splj;
	}
	public void setSplj(String splj) {
		this.splj = splj;
	}
	public String getXtsj() {
		return xtsj;
	}
	public void setXtsj(String xtsj) {
		this.xtsj = xtsj;
	}
	
}
