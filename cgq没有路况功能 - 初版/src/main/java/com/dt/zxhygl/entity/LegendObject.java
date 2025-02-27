package com.dt.zxhygl.entity;

public class LegendObject<T> {

	private String key;
	private T	value;
	
	public LegendObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LegendObject(String key, T value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
	
	
}
