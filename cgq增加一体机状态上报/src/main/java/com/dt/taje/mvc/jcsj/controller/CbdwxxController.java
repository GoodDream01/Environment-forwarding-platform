package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.CbdwxxServiceI;

@Controller
@RequestMapping("/jcsj/cbdw/")
public class CbdwxxController {

	@Autowired
	@Qualifier("cbdwxxServiceImpl")
	CbdwxxServiceI cbdwxxServiceI;

	public void setCbdwxxServiceI(CbdwxxServiceI cbdwxxServiceI) {
		this.cbdwxxServiceI = cbdwxxServiceI;
	}
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.save(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.getById(request, response);
	} 
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.del(request, response);
	} 
	
	@RequestMapping("/up.do")
	public void up(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.up(request, response);
	} 
	
	@RequestMapping("/down.do")
	public void down(HttpServletRequest request,HttpServletResponse response){
		cbdwxxServiceI.down(request, response);
	} 
}
