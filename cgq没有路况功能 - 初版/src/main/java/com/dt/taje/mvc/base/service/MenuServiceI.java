package com.dt.taje.mvc.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MenuServiceI extends CommonServiceI {

	void getMenuList(HttpServletRequest request, HttpServletResponse response);

	void saveMenu(HttpServletRequest request, HttpServletResponse response);

	void delMenu(HttpServletRequest request, HttpServletResponse response);

	void getMenuByID(HttpServletRequest request, HttpServletResponse response);

	void getMenuTree(HttpServletRequest request, HttpServletResponse response);

	void getUserMenuTree(HttpServletRequest request, HttpServletResponse response);

}
