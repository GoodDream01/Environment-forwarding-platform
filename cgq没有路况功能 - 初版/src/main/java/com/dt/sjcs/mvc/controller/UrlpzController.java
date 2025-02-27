package com.dt.sjcs.mvc.controller;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.sjcs.mvc.pojo.Urlpz;
import com.dt.sjcs.mvc.service.UrlpzService;
import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.entity.DataGridReturn;
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
@RequestMapping("/sjcs/urlpz")
public class UrlpzController {

	private static Logger logger = Logger.getLogger(UrlpzController.class);
	
	@Resource
	private UrlpzService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/sjcs/urlpz/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/sjcs/urlpz/add";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询设备管理列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			pageBounds.setParamMap(paramMap);
			List<Urlpz> list = service.findByPage(pageBounds);
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
	public Urlpz getById(String id){
		logger.info("查询人员管理信息....id="+id+"");
		Urlpz t = null;
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
	
	@RequestMapping("save")
	@ResponseBody
	public AjaxJson save(@RequestBody Urlpz t){
		logger.info("保存设备管理信息....");
		boolean success = false;
		try {
			success = service.save(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存设备管理失败....");
			logger.error(e.getMessage());
		}
		String msg = "保存失败！";
		if(success){
			msg = "保存成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxJson delete(String id){
		logger.info("删除设备管理信息....id="+id+"");
		boolean success = false;
		try {
			success = service.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除设备管理信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		String msg = "删除失败！";
		if(success){
			msg = "删除成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	
}
