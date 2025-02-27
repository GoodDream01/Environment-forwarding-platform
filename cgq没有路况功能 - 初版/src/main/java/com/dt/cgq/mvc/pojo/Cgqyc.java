package com.dt.cgq.mvc.pojo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Cgqyc")
public class Cgqyc {
	
	private String id;
	private String sbbh;
	private Date time;
	private String state;
	private int count;
	
	public Cgqyc() {
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
