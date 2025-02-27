package com.dt.zxhygl.mvc.base.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Operator")
public class Operator {

	/**主键ID*/
	private String zid; 
	/**编号*/
	private String zcode; 
	/**姓名*/
	private String zname; 
	/**密码*/
	private String zpassword; 
	/**角色（0管理员，1其他）*/
	private String zrole; 
	/**区域id*/
	private String zqyid;
	/**区域名称*/
	private String zqymc;
	/**显示字段*/
	private String zxszd;
	
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
	public String getZpassword() {
		return zpassword;
	}
	public void setZpassword(String zpassword) {
		this.zpassword = zpassword;
	}
	public String getZrole() {
		return zrole;
	}
	public void setZrole(String zrole) {
		this.zrole = zrole;
	}
	public String getZqyid() {
		return zqyid;
	}
	public void setZqyid(String zqyid) {
		this.zqyid = zqyid;
	}
	public String getZqymc() {
		return zqymc;
	}
	public void setZqymc(String zqymc) {
		this.zqymc = zqymc;
	}
	public String getZxszd() {
		return zxszd;
	}
	public void setZxszd(String zxszd) {
		this.zxszd = zxszd;
	}

}
