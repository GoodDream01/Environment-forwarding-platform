package com.dt.taje.mvc.base.modle.bean;

public class UserBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 7628270304218529589L;
	
	private String ZUID = "";//主键ID
	private String ZUNAME = "";//用户名
	private String ZUXM = "";//用户姓名
	private String ZUPWD = "";//密码
	private String ZROLE = "";//角色类型 
	private String ZQYID = "";//区域id
	private String ZQYMC = "";//区域名称
	
	public String getZUID() {
		return ZUID;
	}
	public void setZUID(String zUID) {
		ZUID = zUID;
	}
	public String getZUNAME() {
		return ZUNAME;
	}
	public void setZUNAME(String zUNAME) {
		ZUNAME = zUNAME;
	}
	public String getZUPWD() {
		return ZUPWD;
	}
	public void setZUPWD(String zUPWD) {
		ZUPWD = zUPWD;
	}
	public String getZROLE() {
		return ZROLE;
	}
	public void setZROLE(String zROLE) {
		ZROLE = zROLE;
	}
	public String getZUXM() {
		return ZUXM;
	}
	public void setZUXM(String zUXM) {
		ZUXM = zUXM;
	}
	public String getZQYID() {
		return ZQYID;
	}
	public void setZQYID(String zQYID) {
		ZQYID = zQYID;
	}
	public String getZQYMC() {
		return ZQYMC;
	}
	public void setZQYMC(String zQYMC) {
		ZQYMC = zQYMC;
	}
	
}
