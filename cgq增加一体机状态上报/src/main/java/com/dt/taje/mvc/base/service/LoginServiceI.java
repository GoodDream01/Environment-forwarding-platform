package com.dt.taje.mvc.base.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceI extends CommonServiceI {

	void login(HttpServletRequest request, HttpServletResponse response);

}
