package com.dt.taje.mvc.jcsj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt.taje.mvc.base.service.CommonServiceI;

public interface CwglServiceI extends CommonServiceI {
	
	void getList(HttpServletRequest request, HttpServletResponse response);

	void save(HttpServletRequest request, HttpServletResponse response);

	void del(HttpServletRequest request, HttpServletResponse response);

	void getById(HttpServletRequest request, HttpServletResponse response);

	void createCWBH(HttpServletRequest request, HttpServletResponse response);

}
