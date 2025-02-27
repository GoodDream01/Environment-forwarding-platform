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
import com.dt.cgq.mvc.pojo.Ycsj;
import com.dt.cgq.mvc.service.YcsjService;

@Controller
@RequestMapping("/cgq/ycsj")
public class YcsjController {

	private static Logger logger = Logger.getLogger(YcsjController.class);
	
	@Resource
	private YcsjService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/ycsj/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/cgq/ycsj/add";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询人员管理列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			User currentUser = UserUtils.getCurrentUser();
			paramMap.put("qyid", currentUser.getQyid());
			paramMap.put("ssid", ComUtils.ConvNull(request.getParameter("ssid")));
			paramMap.put("sbbh", ComUtils.ConvNull(request.getParameter("sbbh")));
			paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
			paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
			pageBounds.setParamMap(paramMap);
			List<Ycsj> list = service.findByPage(pageBounds);
			pageBounds.setList(list);
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询人员管理列表出错....");
			logger.error(e.getMessage());
		}
		return grid;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Ycsj getById(String id){
		logger.info("查询信息....id="+id+"");
		Ycsj t = null;
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
