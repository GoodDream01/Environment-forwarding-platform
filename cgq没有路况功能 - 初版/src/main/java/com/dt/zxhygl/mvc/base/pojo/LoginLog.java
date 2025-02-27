package com.dt.zxhygl.mvc.base.pojo;

import org.apache.ibatis.type.Alias;

@Alias("LoginLog")
public class LoginLog {
	/**主键ID*/
	private String id; 
	/**用户名*/
	private String username; 
	/**客户端IP*/
	private String loginip; 
	/**主机名称*/
	private String hostname; 
	/**浏览器*/
	private String browser; 
	/**操作系统*/
	private String osname; 
	/**登录时间*/
	private String logintime; 
	/**登录结果*/
	private String result;
	
	/**姓名*/
	private String xm;
	/**登录类型*/
	private String type;
	/**角色*/
	private String role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOsname() {
		return osname;
	}
	public void setOsname(String osname) {
		this.osname = osname;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
