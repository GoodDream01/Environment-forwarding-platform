package com.dt.zxhygl.mvc.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.zxhygl.entity.DataGridReturn;
import com.dt.zxhygl.mvc.base.pojo.LoginLog;
import com.dt.zxhygl.mvc.base.service.ILoginLogService;

@Controller
@RequestMapping("/base/loginLog")
public class LoginLogController {

	@Resource
	private ILoginLogService iLoginLogService;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/hygl//base/loginLog/list";
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
		
		String username = ComUtils.ConvNull(request.getParameter("username"));
		paramMap.put("username", username);
		
		String jgsbh = ComUtils.ConvNull(request.getParameter("jgsbh"));
		paramMap.put("jgsbh", jgsbh);
		
		pageBounds.setParamMap(paramMap);
		
		List<LoginLog> list = iLoginLogService.findByPage(pageBounds);
		pageBounds.setList(list);
		
		DataGridReturn grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		return grid;
		
	}
}
