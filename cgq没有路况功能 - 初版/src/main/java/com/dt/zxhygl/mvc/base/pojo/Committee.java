package com.dt.zxhygl.mvc.base.pojo;

import org.apache.ibatis.type.Alias;

/**
 * 政协常委实体类
 * @author Administrator
 *
 */
@Alias("Committee")
public class Committee {

	/**主键ID*/
	private String zid; 
	/**编号*/
	private String zcode;
	/**姓名*/
	private String zname; 
	/**性别*/
	private String zsex;  
	/**民族*/
	private String znation;  
	/**省政协职务*/
	private String zcppost;  
	/**出生年月*/
	private String zbirthday;  
	/**现任职务*/
	private String zpost;  
	/**通讯地址*/
	private String zaddress;  
	/**邮编*/
	private String zzipcode;  
	/**地区*/
	private String zarea;  
	/**办公电话*/
	private String zofficetel;  
	/**手机*/
	private String zmobiletel;  
	/**住宅电话*/
	private String zhousetel; 
	/**电子信箱*/ 
	private String zemail;  
	/**党派*/
	private String zpartian;  
	/**界别*/
	private String zsector;  
	/**学历*/
	private String zedu;  
	/**学位*/
	private String zdegree;  
	/**级别*/
	private String ztype;  
	/**备注*/
	private String znote;  
	/**身份证号*/
	private String zsfzh;  
	/**用户名*/
	private String username;  
	/**密码*/
	private String password;  
	/**删除状态*/
	private String sczt;
	
	
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
	public String getZsex() {
		return zsex;
	}
	public void setZsex(String zsex) {
		this.zsex = zsex;
	}
	public String getZnation() {
		return znation;
	}
	public void setZnation(String znation) {
		this.znation = znation;
	}
	public String getZcppost() {
		return zcppost;
	}
	public void setZcppost(String zcppost) {
		this.zcppost = zcppost;
	}
	public String getZbirthday() {
		return zbirthday;
	}
	public void setZbirthday(String zbirthday) {
		this.zbirthday = zbirthday;
	}
	public String getZpost() {
		return zpost;
	}
	public void setZpost(String zpost) {
		this.zpost = zpost;
	}
	public String getZaddress() {
		return zaddress;
	}
	public void setZaddress(String zaddress) {
		this.zaddress = zaddress;
	}
	public String getZzipcode() {
		return zzipcode;
	}
	public void setZzipcode(String zzipcode) {
		this.zzipcode = zzipcode;
	}
	public String getZarea() {
		return zarea;
	}
	public void setZarea(String zarea) {
		this.zarea = zarea;
	}
	public String getZofficetel() {
		return zofficetel;
	}
	public void setZofficetel(String zofficetel) {
		this.zofficetel = zofficetel;
	}
	public String getZmobiletel() {
		return zmobiletel;
	}
	public void setZmobiletel(String zmobiletel) {
		this.zmobiletel = zmobiletel;
	}
	public String getZhousetel() {
		return zhousetel;
	}
	public void setZhousetel(String zhousetel) {
		this.zhousetel = zhousetel;
	}
	public String getZemail() {
		return zemail;
	}
	public void setZemail(String zemail) {
		this.zemail = zemail;
	}
	public String getZpartian() {
		return zpartian;
	}
	public void setZpartian(String zpartian) {
		this.zpartian = zpartian;
	}
	public String getZsector() {
		return zsector;
	}
	public void setZsector(String zsector) {
		this.zsector = zsector;
	}
	public String getZedu() {
		return zedu;
	}
	public void setZedu(String zedu) {
		this.zedu = zedu;
	}
	public String getZdegree() {
		return zdegree;
	}
	public void setZdegree(String zdegree) {
		this.zdegree = zdegree;
	}
	public String getZtype() {
		return ztype;
	}
	public void setZtype(String ztype) {
		this.ztype = ztype;
	}
	public String getZnote() {
		return znote;
	}
	public void setZnote(String znote) {
		this.znote = znote;
	}
	public String getZsfzh() {
		return zsfzh;
	}
	public void setZsfzh(String zsfzh) {
		this.zsfzh = zsfzh;
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
	public String getSczt() {
		return sczt;
	}
	public void setSczt(String sczt) {
		this.sczt = sczt;
	}
	
	
}
