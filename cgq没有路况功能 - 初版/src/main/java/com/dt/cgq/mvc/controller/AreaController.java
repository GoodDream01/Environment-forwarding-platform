package com.dt.cgq.mvc.controller;

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

import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import com.dt.cgq.mvc.pojo.Area;
import com.dt.cgq.mvc.service.AreaService;

@Controller
@RequestMapping("/cgq/area")
public class AreaController {

	private static Logger logger = Logger.getLogger(AreaController.class);
	
	@Resource
	private AreaService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/area/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/cgq/area/add";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public List<Area> getList(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<String,String>();
		User currentUser = UserUtils.getCurrentUser();
		paramMap.put("qyid", currentUser.getQyid());
		return service.selectAll(paramMap);
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Area getById(String id){
		logger.info("查询信息....id="+id+"");
		Area t = null;
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
	public AjaxJson save(@RequestBody Area t){
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
