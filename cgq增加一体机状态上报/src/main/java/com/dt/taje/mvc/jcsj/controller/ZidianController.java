package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.ZidianServiceI;

@Controller
@RequestMapping("/jcsj/zidian/")
public class ZidianController {
	
	@Autowired
	@Qualifier("zidianServiceImpl")
	ZidianServiceI zidianServiceI;

	public void setZidianServiceI(ZidianServiceI zidianServiceI) {
		this.zidianServiceI = zidianServiceI;
	}

	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.save(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.getById(request, response);
	} 
	
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.del(request, response);
	} 
	
	@RequestMapping("/up.do")
	public void up(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.up(request, response);
	} 
	
	@RequestMapping("/down.do")
	public void down(HttpServletRequest request,HttpServletResponse response){
		zidianServiceI.down(request, response);
	} 
}
