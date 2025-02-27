package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Unit")
public class Unit implements Serializable {

	private static final long serialVersionUID = -8445771237072124040L;
	
	/**主键ID*/
	private String zid;
	/**单位编号*/
	private String zcode;
	/**单位名称*/
	private String zname;
	/**电话*/
	private String ztel;
	/**地址*/
	private String zaddress;
	/**联系人*/
	private String zlinkman;
	/**登录密码*/
	private String zpassword;
	/**类型*/
	private String ztype;
	/**登录账号*/
	private String zunitcode;
	/**排序*/
	private String zsortindex;
	/**角色类型*/
	private String zroletype;
	/**辖区编号*/
	private String zxqbh;
	/**辖区单位*/
	private String zxqdw;
	
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
	public String getZroletype() {
		return zroletype;
	}
	public void setZroletype(String zroletype) {
		this.zroletype = zroletype;
	}
	public String getZxqbh() {
		return zxqbh;
	}
	public void setZxqbh(String zxqbh) {
		this.zxqbh = zxqbh;
	}
	public String getZxqdw() {
		return zxqdw;
	}
	public void setZxqdw(String zxqdw) {
		this.zxqdw = zxqdw;
	}
	
	

}
