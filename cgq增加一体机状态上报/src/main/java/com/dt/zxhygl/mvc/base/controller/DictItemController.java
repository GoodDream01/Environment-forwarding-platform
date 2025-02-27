package com.dt.zxhygl.mvc.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.entity.DataGridReturn;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IDictItemService;
import com.dt.zxhygl.mvc.base.service.IDictTypeService;

@Controller
@RequestMapping("/base/dictItem")
public class DictItemController {
	
	@Resource
	private IDictTypeService iDictTypeService ;
	
	@Resource
	private IDictItemService iDictItemService;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/hygl//base/dictItem/list";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		//分页参数（固定的）
		String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
		String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
		boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
		
		PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
		
		Map<String,String> paramMap = new HashMap<String,String>();
		
		String typeCode = ComUtils.ConvNull(request.getParameter("typeCode"));
		paramMap.put("typeCode", typeCode);
		
		pageBounds.setParamMap(paramMap);
		
		List<DictItem> list = iDictItemService.findByPage(pageBounds);
		pageBounds.setList(list);
		
		DataGridReturn grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		return grid;
		
	}
	
	@RequestMapping("/addUi")
	public String addUi(){
		return "/hygl//base/dictItem/add";
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public DictItem getById(String id){
		
		return iDictItemService.selectByPrimaryKey(id);
		
	}
	
	@RequestMapping("/getSort")
	@ResponseBody
	public String getSort(String typeCode){
		
		return iDictItemService.getSort(typeCode);
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxJson save(@RequestBody DictItem dictItem){
		boolean success = iDictItemService.save(dictItem);
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
		boolean success = iDictItemService.delete(id);
		String msg = "删除失败！";
		if(success){
			msg = "删除成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
}
