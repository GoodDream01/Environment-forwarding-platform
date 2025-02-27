package com.dt.pmkz.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.pmkz.mvc.pojo.Pm;
import com.dt.pmkz.mvc.service.PmService;
import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.entity.DataGridReturn;

@Controller
@RequestMapping("/pmkz/pm")
public class PmController {

	private static Logger logger = Logger.getLogger(PmController.class);
	
	@Resource
	private PmService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/pmkz/pm/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/pmkz/pm/add";
	}
	@RequestMapping("/kzUi")
	public String kzUi(){
		return "/pmkz/pm/kz";
	}
	@RequestMapping("/yaUi")
	public String yaUi(){
		return "/pmkz/pm/ya";
	}
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Pm> selectAll(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<String,String>();
		return service.selectAll(paramMap);
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request){
		logger.info("查询短信记录列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			pageBounds.setParamMap(paramMap);
			List<Pm> list = service.findByPage(pageBounds);
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
	
	@RequestMapping("/getById")
	@ResponseBody
	public Pm getById(String id){
		logger.info("查询信息....id="+id+"");
		Pm t = null;
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
	
	@RequestMapping("save")
	@ResponseBody
	public AjaxJson save(@RequestBody Pm t){
		logger.info("保存信息....");
		boolean success = false;
		try {
			success = service.save(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存失败....");
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
		logger.info("删除信息....id="+id+"");
		boolean success = false;
		try {
			success = service.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除信息失败....id="+id+"");
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
