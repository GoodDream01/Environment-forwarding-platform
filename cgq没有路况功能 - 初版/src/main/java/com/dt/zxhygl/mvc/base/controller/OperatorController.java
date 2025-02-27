package com.dt.zxhygl.mvc.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.dt.zxhygl.mvc.base.pojo.Operator;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IOperatorService;
import com.dt.zxhygl.utils.UserUtils;

@Controller
@RequestMapping("/base/operator")
public class OperatorController {
	private static Logger logger = Logger.getLogger(OperatorController.class);
	
	@Resource
	private IOperatorService iOperatorService;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/hygl//base/operator/list";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询用户列表....");
		DataGridReturn grid = null;
		
		try {
			User currentUser = UserUtils.getCurrentUser();

			//分页参数（固定的）
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			
			Map<String,String> paramMap = new HashMap<String,String>();
			
			paramMap.put("qyid", currentUser.getQyid());
			
			String zcode = ComUtils.ConvNull(request.getParameter("zcode"));
			paramMap.put("zcode", zcode);
			
			String zrole = ComUtils.ConvNull(request.getParameter("zrole"));
			paramMap.put("zrole", zrole);

			pageBounds.setParamMap(paramMap);
			
			List<Operator> list = iOperatorService.findByPage(pageBounds);
			pageBounds.setList(list);
			
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询列用户表出错....");
			logger.error(e.getMessage());
		}
		return grid;
		
	}
	
	
	@RequestMapping("/addUi")
	public String addUi(){
		return "/hygl//base/operator/add";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxJson save(@RequestBody Operator operator){
		logger.info("保存用户信息....");
		boolean success = false;
		try {
			success = iOperatorService.save(operator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存用户失败....");
			logger.error(e.getMessage());
		}
		String msg = "保存失败！";
		if(success){
			msg = "保存成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Operator getById(String id){
		logger.info("查询用户信息....id="+id+"");
		Operator operator = null;
		try {
			operator = iOperatorService.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询用户信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		return operator;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxJson delete(String id){
		logger.info("删除用户信息....id="+id+"");
		boolean success = false;
		try {
			success = iOperatorService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除用户失败....id="+id+"");
			logger.error(e.getMessage());
		}
		String msg = "删除失败！";
		if(success){
			msg = "删除成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	
	@RequestMapping("/checkUsername")
	@ResponseBody
	public Map<String,Object> checkUsername(String username){
		logger.info("添加查询用户名【"+username+"】是否重复....");
		Map<String,Object> result = new HashMap<String,Object>();
		boolean flag = false;
		try {
			Operator user = iOperatorService.selectByUsername(username);
			if(user!=null){
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("添加查询用户名【"+username+"】是否重复失败....");
			logger.error(e.getMessage());
		}
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping("/checkUsernameAndZid")
	@ResponseBody
	public Map<String,Object> checkUsernameAndZid(String username,String zid){
		logger.info("修改查询用户名【"+username+"】是否重复....");
		Map<String,Object> result = new HashMap<String,Object>();
		boolean flag = false;
		try {
			Operator user = iOperatorService.selectByUsernameWithOutZid(username, zid);
			if(user!=null){
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("修改查询用户名【"+username+"】是否重复失败....");
			logger.error(e.getMessage());
		}
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping("/selDuoUi")
	public String selDuoUi(){
		return "/hygl//base/operator/selDuo";
	}
}
