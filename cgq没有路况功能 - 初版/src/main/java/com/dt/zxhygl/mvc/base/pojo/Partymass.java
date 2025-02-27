package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Partymass")
public class Partymass implements Serializable{
	
	private static final long serialVersionUID = 7633276227178169675L;
	
	/**ID*/
	private String zid;
	/**编号*/
	private String zcode;
	/**单位名称*/
	private String zname;
	/**电话*/
	private String ztel;
	/**地址*/
	private String zaddress;
	/**姓名*/
	private String zlinkman;
	/**登录密码*/
	private String zpassword;
	/**0单位1团体*/
	private String ztype;
	/**用户名*/
	private String zunitcode;
	/**排序*/
	private String zsortindex;
	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getZcode() {
		return zcode;
	}
	public void setZcode(String zcode) {
		this.zcode = zcode;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	public String getZtel() {
		return ztel;
	}
	public void setZtel(String ztel) {
		this.ztel = ztel;
	}
	public String getZaddress() {
		return zaddress;
	}
	public void setZaddress(String zaddress) {
		this.zaddress = zaddress;
	}
	public String getZlinkman() {
		return zlinkman;
	}
	public void setZlinkman(String zlinkman) {
		this.zlinkman = zlinkman;
	}
	public String getZpassword() {
		return zpassword;
	}
	public void setZpassword(String zpassword) {
		this.zpassword = zpassword;
	}
	public String getZtype() {
		return ztype;
	}
	public void setZtype(String ztype) {
		this.ztype = ztype;
	}
	public String getZunitcode() {
		return zunitcode;
	}
	public void setZunitcode(String zunitcode) {
		this.zunitcode = zunitcode;
	}
	public String getZsortindex() {
		return zsortindex;
	}
	public void setZsortindex(String zsortindex) {
		this.zsortindex = zsortindex;
	}
	

}
