package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.CwglServiceI;

@Controller
@RequestMapping("/jcsj/cwgl/")
public class CwglController {

	@Autowired
	@Qualifier("cwglServiceImpl")
	CwglServiceI cwglServiceI;

	public void setCwglServiceI(CwglServiceI cwglServiceI) {
		this.cwglServiceI = cwglServiceI;
	}
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		cwglServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		cwglServiceI.save(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		cwglServiceI.getById(request, response);
	} 
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		cwglServiceI.del(request, response);
	} 
	
	@RequestMapping("/createCWBH.do")
	public void createCWBH(HttpServletRequest request,HttpServletResponse response){
		cwglServiceI.createCWBH(request, response);
	} 
	
}
