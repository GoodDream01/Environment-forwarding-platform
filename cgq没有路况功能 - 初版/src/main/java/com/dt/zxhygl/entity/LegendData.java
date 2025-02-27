package com.dt.zxhygl.entity;

public class LegendData {

	private String name;
	private String filed;
	
	
	
	public LegendData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LegendData(String name, String filed) {
		super();
		this.name = name;
		this.filed = filed;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	
	
}
