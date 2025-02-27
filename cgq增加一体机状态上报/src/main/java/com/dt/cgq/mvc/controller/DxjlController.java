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
import com.dt.cgq.mvc.pojo.Dxjl;
import com.dt.cgq.mvc.service.DxjlService;

@Controller
@RequestMapping("/cgq/dxjl")
public class DxjlController {

	private static Logger logger = Logger.getLogger(DxjlController.class);
	
	@Resource
	private DxjlService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/dxjl/list";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询短信记录列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			User currentUser = UserUtils.getCurrentUser();
			paramMap.put("bj1", currentUser.getRole());
			paramMap.put("qyid", currentUser.getQyid());
			paramMap.put("bj", ComUtils.ConvNull(request.getParameter("bj")));
			paramMap.put("ssid", ComUtils.ConvNull(request.getParameter("ssid")));
			paramMap.put("sbbh", ComUtils.ConvNull(request.getParameter("sbbh")));
			pageBounds.setParamMap(paramMap);
			List<Dxjl> list = service.findByPage(pageBounds);
			pageBounds.setList(list);
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询短信记录出错....");
			logger.error(e.getMessage());
		}
		return grid;
	}
	
}
