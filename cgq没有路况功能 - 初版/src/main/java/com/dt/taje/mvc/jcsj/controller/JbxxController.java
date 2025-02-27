package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.JbxxServiceI;



@Controller
@RequestMapping("/jcsj/jbxx/")

public class JbxxController {
	@Autowired
	@Qualifier("jbxxServiceImpl")
	JbxxServiceI jbxxServiceI;
	
	
	public void setCbdwxxServiceI(JbxxServiceI jbxxServiceI) {
		this.jbxxServiceI = jbxxServiceI;
	}
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		jbxxServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		jbxxServiceI.save(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		jbxxServiceI.getById(request, response);
	} 
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		jbxxServiceI.del(request, response); 
	} 
}
