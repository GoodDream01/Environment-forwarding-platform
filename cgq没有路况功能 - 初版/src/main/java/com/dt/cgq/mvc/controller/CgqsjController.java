package com.dt.cgq.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.zxhygl.entity.DataGridReturn;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.service.CgqsjService;

@Controller
@RequestMapping("/cgq/cgqsj")
public class CgqsjController {

	private static Logger logger = Logger.getLogger(CgqsjController.class);
	
	@Resource
	private CgqsjService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/cgqsj/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/cgq/cgqsj/add";
	}
	@RequestMapping("/addUi1")
	public String addUi1(){
		return "/cgq/cgqsj/add1";
	}
	@RequestMapping("/listycUi")
	public String listycUi(){
		return "/cgq/cgqsj/listyc";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询传感器数据列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			User currentUser = UserUtils.getCurrentUser();
			paramMap.put("bj", currentUser.getRole());
			paramMap.put("qyid", currentUser.getQyid());
			paramMap.put("ssid", ComUtils.ConvNull(request.getParameter("ssid")));
			paramMap.put("yc", ComUtils.ConvNull(request.getParameter("yc")));
			paramMap.put("sbbh", ComUtils.ConvNull(request.getParameter("sbbh")));
			paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
			paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
			pageBounds.setParamMap(paramMap);
			List<Cgqsj> list = service.findByPage(pageBounds);
			pageBounds.setList(list);
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询传感器数据列表出错....");
			logger.error(e.getMessage());
		}
		return grid;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Cgqsj getById(String id){
		logger.info("查询信息....id="+id+"");
		Cgqsj t = null;
		try {
			t = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		return t;
	}
	
}
