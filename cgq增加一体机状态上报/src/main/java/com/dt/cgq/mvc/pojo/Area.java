package com.dt.cgq.mvc.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Area")
public class Area {
	
	private String id;
	private String pid;
	private String name;
	private String bz;
	
	public Area() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
