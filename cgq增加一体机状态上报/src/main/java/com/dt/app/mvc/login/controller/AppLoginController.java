package com.dt.app.mvc.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/login")
public class AppLoginController {

	@RequestMapping("/loginIndex")
	public String loginIndex(){
		return "/app/login/loginIndex";
	}
}
