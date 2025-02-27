package com.dt.sjcs.mvc.controller;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.sjcs.mvc.service.SjpzService;
import com.dt.sjcs.mvc.service.UrlpzService;
import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.entity.DataGridReturn;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sjcs/sjpz")
public class SjpzController {

	private static Logger logger = Logger.getLogger(SjpzController.class);
	
	@Resource
	private SjpzService service;
	@Resource
	private UrlpzService urlpzservice;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/sjcs/sjpz/list";
	}
	@RequestMapping("/listdqUi")
	public String listdqUi(){
		return "/sjcs/sjpz/listdq";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/sjcs/sjpz/add";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request){
		logger.info("查询设备管理列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			User currentUser = UserUtils.getCurrentUser();
			if(!currentUser.getRole().equals("0")){
				paramMap.put("sbbh", urlpzservice.findSbbh(currentUser.getQyid()));
			}
			paramMap.put("sbid", ComUtils.ConvNull(request.getParameter("sbid")));
			paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
			paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
			pageBounds.setParamMap(paramMap);
			List<Sjpz> list = service.findByPage(pageBounds);
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

	@RequestMapping("/getlsList")
	@ResponseBody
	public DataGridReturn getlsList(HttpServletRequest request){
		logger.info("查询设备管理列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			User currentUser = UserUtils.getCurrentUser();
			if(!currentUser.getRole().equals("0")){
				paramMap.put("sbbh", urlpzservice.findSbbh(currentUser.getQyid()));
			}
			paramMap.put("sbid", ComUtils.ConvNull(request.getParameter("sbid")));
			paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
			paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
			pageBounds.setParamMap(paramMap);
			List<Sjpz> list = service.findlsByPage(pageBounds);
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
	public Sjpz getById(String id){
		logger.info("查询人员管理信息....id="+id+"");
		Sjpz t = null;
		try {
			t = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询设备管理信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		return t;
	}

	@RequestMapping("/findDc")
	@ResponseBody
	public List<Sjpz> findDc(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<String,String>();
		User currentUser = UserUtils.getCurrentUser();
		if(!currentUser.getRole().equals("0")){
			paramMap.put("sbbh", urlpzservice.findSbbh(currentUser.getQyid()));
		}
		paramMap.put("sbid", ComUtils.ConvNull(request.getParameter("sbid")));
		paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
		paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
		return service.findDc(paramMap);
	}

	@RequestMapping("/findlsDc")
	@ResponseBody
	public List<Sjpz> findlsDc(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<String,String>();
		User currentUser = UserUtils.getCurrentUser();
		if(!currentUser.getRole().equals("0")){
			paramMap.put("sbbh", urlpzservice.findSbbh(currentUser.getQyid()));
		}
		paramMap.put("sbid", ComUtils.ConvNull(request.getParameter("sbid")));
		paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
		paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
		return service.findlsDc(paramMap);
	}

}
