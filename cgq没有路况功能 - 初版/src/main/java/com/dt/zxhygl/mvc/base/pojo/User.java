package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6094784281130576204L;
	
	/**用户id*/
	private String zid;
	/**用户名*/
	private String username; 
	/**密码*/
	private String password; 
	/**角色类型*/
	private String role;
	/**登录类型*/
	private String type;
	/**姓名*/
	private String xm;
	/**人员类型（0,管理员；2政府单位；3，业主单位）*/
	private String rylx;
	/**区域ID*/
	private String qyid;
	/**区域名称*/
	private String qymc;
	/**登录平台*/
	private String flat;
	/**显示字段*/
	private String xszd;

	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getRylx() {
		return rylx;
	}
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	public String getQyid() {
		return qyid;
	}
	public void setQyid(String qyid) {
		this.qyid = qyid;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getFlat() {
		return flat;
	}
	public void setFlat(String flat) {
		this.flat = flat;
	}
	public String getXszd() {
		return xszd;
	}
	public void setXszd(String xszd) {
		this.xszd = xszd;
	}

}
