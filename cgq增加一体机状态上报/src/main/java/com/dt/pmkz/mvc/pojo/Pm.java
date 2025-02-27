package com.dt.pmkz.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Pm")
public class Pm {
	
	private String id;
	private String ip;
	private int dk;
	private String dz;
	private String bzwz;
	private String pmlx;
	
	public Pm() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getDk() {
		return dk;
	}
	public void setDk(int dk) {
		this.dk = dk;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getBzwz() {
		return bzwz;
	}
	public void setBzwz(String bzwz) {
		this.bzwz = bzwz;
	}
	public String getPmlx() {
		return pmlx;
	}
	public void setPmlx(String pmlx) {
		this.pmlx = pmlx;
	}
	
}
