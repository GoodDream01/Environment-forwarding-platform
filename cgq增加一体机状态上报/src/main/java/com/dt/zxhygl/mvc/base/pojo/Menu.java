package com.dt.zxhygl.mvc.base.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Menu")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 2058470903450078160L;
	private String menuId; //菜单ID
	private String menuCode; //菜单编号
	private String menuName; //菜单名称
	private String menuLevel; //菜单级别
	private String menuPcode; //菜单父编号
	private String menuPath;//菜单路径
	private String menuStatus;//菜单状态（0启用，1不启用）
	private String menuOrder; //菜单排序
	private String mneuType;//菜单类型
	private String menuIcon;//菜单图标
	private String roleId;//角色ID
	private String dwbh;//角色ID
	
	public Menu() {

	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuPcode() {
		return menuPcode;
	}

	public void setMenuPcode(String menuPcode) {
		this.menuPcode = menuPcode;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMneuType() {
		return mneuType;
	}

	public void setMneuType(String mneuType) {
		this.mneuType = mneuType;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDwbh() {
		return dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}
	
	
	
	
}
