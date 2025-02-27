package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.RddbxxServiceI;

@Controller
@RequestMapping("jcsj/rddb")
public class RddbxxController {
	
	@Autowired
	@Qualifier("rddbxxServiceImpl")
	RddbxxServiceI rddbxxServiceI;

	public void setCbdwxxServiceI(RddbxxServiceI rddbxxServiceI) {
		this.rddbxxServiceI = rddbxxServiceI;
	}
	
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		rddbxxServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		rddbxxServiceI.save(request, response);
	} 
	
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		rddbxxServiceI.getById(request, response);
	} 
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		rddbxxServiceI.del(request, response);
	} 
}
