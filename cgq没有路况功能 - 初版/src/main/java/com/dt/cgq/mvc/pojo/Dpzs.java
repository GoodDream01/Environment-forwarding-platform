package com.dt.cgq.mvc.pojo;

public class Dpzs {
	
	private String name;
	private String type;
	private String count;
	
	private String names[];
	private String data[];
	private String value1s[];
	private String value2s[];
	
	public Dpzs() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public String[] getValue1s() {
		return value1s;
	}
	public void setValue1s(String[] value1s) {
		this.value1s = value1s;
	}
	public String[] getValue2s() {
		return value2s;
	}
	public void setValue2s(String[] value2s) {
		this.value2s = value2s;
	}
	
}
