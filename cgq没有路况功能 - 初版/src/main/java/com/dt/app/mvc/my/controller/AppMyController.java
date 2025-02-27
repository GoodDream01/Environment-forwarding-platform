package com.dt.app.mvc.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/my")
public class AppMyController {
	@RequestMapping("/index")
	public String index(){
		return "/app/my/index";
	}
}
