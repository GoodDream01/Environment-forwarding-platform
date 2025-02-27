package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.SxglServiceI;

@Controller
@RequestMapping("/jcsj/sxgl/")
public class SxglController {

	@Autowired
	@Qualifier("sxglServiceImpl")
	SxglServiceI sxglServiceI;

	public void setSxglServiceI(SxglServiceI sxglServiceI) {
		this.sxglServiceI = sxglServiceI;
	}
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		sxglServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		sxglServiceI.save(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		sxglServiceI.getById(request, response);
	} 
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		sxglServiceI.del(request, response);
	} 
}
