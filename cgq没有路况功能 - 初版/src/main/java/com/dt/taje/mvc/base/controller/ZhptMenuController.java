package com.dt.taje.mvc.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.base.service.ZhptMenuServiceI;

@Controller
@RequestMapping("/zhpt/menu")
public class ZhptMenuController {
	@Autowired
	@Qualifier("zhptMenuServiceImpl")
	ZhptMenuServiceI menuServiceI;

	public void setMenuServiceI(ZhptMenuServiceI menuServiceI) {
		this.menuServiceI = menuServiceI;
	}
	
	@RequestMapping("/getMenuList.do")
	public void getMenuList(HttpServletRequest request,HttpServletResponse response){
		menuServiceI.getMenuList(request, response); 
	} 
	
	@RequestMapping("/getMenuTree.do")
	public void getMenuTree(HttpServletRequest request,HttpServletResponse response){
		menuServiceI.getMenuTree(request, response); 
	} 
	
	@RequestMapping("/getUserMenuTree.do")
	public void getUserMenuTree(HttpServletRequest request,HttpServletResponse response){
		menuServiceI.getUserMenuTree(request, response); 
	}
}
