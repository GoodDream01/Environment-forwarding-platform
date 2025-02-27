package com.dt.cgq.mvc.pojo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Cgqsj")
public class Cgqsj {
	
	private String id;
	private String sbbh;
	private Date time;
	private String dqz;
	private String pjz;
	private String zdz;
	private String zxz;
	private String dqd;
	private String pjd;
	private Date xtsj;
	private String name;
	private String fldj;
	private String tpbm;
	
	public Cgqsj() {
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDqz() {
		return dqz;
	}
	public void setDqz(String dqz) {
		this.dqz = dqz;
	}
	public String getPjz() {
		return pjz;
	}
	public void setPjz(String pjz) {
		this.pjz = pjz;
	}
	public String getZdz() {
		return zdz;
	}
	public void setZdz(String zdz) {
		this.zdz = zdz;
	}
	public String getZxz() {
		return zxz;
	}
	public void setZxz(String zxz) {
		this.zxz = zxz;
	}
	public String getDqd() {
		return dqd;
	}
	public void setDqd(String dqd) {
		this.dqd = dqd;
	}
	public String getPjd() {
		return pjd;
	}
	public void setPjd(String pjd) {
		this.pjd = pjd;
	}
	public Date getXtsj() {
		return xtsj;
	}
	public void setXtsj(Date xtsj) {
		this.xtsj = xtsj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFldj() {
		return fldj;
	}
	public void setFldj(String fldj) {
		this.fldj = fldj;
	}
	public String getTpbm() {
		return tpbm;
	}
	public void setTpbm(String tpbm) {
		this.tpbm = tpbm;
	}

}
