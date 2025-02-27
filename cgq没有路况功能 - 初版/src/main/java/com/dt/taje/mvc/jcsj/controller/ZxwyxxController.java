package com.dt.taje.mvc.jcsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.taje.mvc.jcsj.service.ZxwyxxServiceI;


@Controller
@RequestMapping("/jcsj/zxwy/")
public class ZxwyxxController {
	
	@Autowired
	@Qualifier("zxwyxxServiceImpl")
	ZxwyxxServiceI zxwyxxServiceI;

	public void setCbdwxxServiceI(ZxwyxxServiceI cbdwxxServiceI) {
		this.zxwyxxServiceI = cbdwxxServiceI;
	}
	
	@RequestMapping("/getList.do")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.getList(request, response); 
	} 
	
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.save(request, response);
	} 
	
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.update(request, response);
	} 
	
	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.getById(request, response);
	} 
	
	@RequestMapping("/up.do")
	public void up(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.up(request, response);
	} 
	@RequestMapping("/del.do")
	public void del(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.del(request, response);
	} 
	
	@RequestMapping("/down.do")
	public void down(HttpServletRequest request,HttpServletResponse response){
		zxwyxxServiceI.down(request, response);
	} 

}
