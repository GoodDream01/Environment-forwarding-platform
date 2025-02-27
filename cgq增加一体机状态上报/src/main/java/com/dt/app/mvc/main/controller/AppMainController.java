package com.dt.app.mvc.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/main")
public class AppMainController {

	@RequestMapping("/index")
	public String index(){
		return "/app/main/index";
	}
	
	@RequestMapping("/indexsj")
	public String indexsj(){
		return "/app/main/indexsj";
	}
	
	@RequestMapping("/indexksh")
	public String indexksh(){
		return "/app/main/indexksh";
	}
	
}
