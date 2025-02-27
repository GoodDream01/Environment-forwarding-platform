package com.dt.cgq.mvc.pojo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Dxjl")
public class Dxjl {
	
	private String id;
	private Date time;
	private String sbbh;
	private String fsrxm;
	private String fsrdh;
	private String ycyy;
	private String fszt;
	
	public Dxjl() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public String getFsrxm() {
		return fsrxm;
	}
	public void setFsrxm(String fsrxm) {
		this.fsrxm = fsrxm;
	}
	public String getFsrdh() {
		return fsrdh;
	}
	public void setFsrdh(String fsrdh) {
		this.fsrdh = fsrdh;
	}
	public String getYcyy() {
		return ycyy;
	}
	public void setYcyy(String ycyy) {
		this.ycyy = ycyy;
	}
	public String getFszt() {
		return fszt;
	}
	public void setFszt(String fszt) {
		this.fszt = fszt;
	}
	
}
