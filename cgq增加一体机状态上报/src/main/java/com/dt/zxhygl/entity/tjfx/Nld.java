package com.dt.zxhygl.entity.tjfx;

/**
 * 年龄段类
 * @author Administrator
 *
 */
public class Nld {

	/**开始年龄*/
	private int ksNl;
	/**结束年龄*/
	private int jsNl;
	
	public Nld() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Nld(int ksNl, int jsNl) {
		super();
		this.ksNl = ksNl;
		this.jsNl = jsNl;
	}
	
	public int getKsNl() {
		return ksNl;
	}
	public void setKsNl(int ksNl) {
		this.ksNl = ksNl;
	}
	public int getJsNl() {
		return jsNl;
	}
	public void setJsNl(int jsNl) {
		this.jsNl = jsNl;
	}
	
	
}
