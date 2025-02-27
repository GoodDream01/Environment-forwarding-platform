package com.dt.taje.mvc.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.base.service.LoginServiceI;
import com.dt.taje.utils.ComUtils;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	@Qualifier("loginServiceImpl")
	LoginServiceI loginServiceI;

	public void setLoginServiceI(LoginServiceI loginServiceI) {
		this.loginServiceI = loginServiceI;
	}
	
	@RequestMapping("/login.do")
	public void login(HttpServletRequest request,HttpServletResponse response){
		loginServiceI.login(request, response);
	}  
	
	@RequestMapping("/loginout.do")
	public void loginout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		ComUtils.PrintWrite(response, "1");
	}  
	
}
