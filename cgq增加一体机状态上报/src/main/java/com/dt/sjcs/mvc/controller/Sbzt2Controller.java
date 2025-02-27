package com.dt.sjcs.mvc.controller;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.sjcs.mvc.pojo.Sbzt;
import com.dt.sjcs.mvc.service.SbztService;
import com.dt.sjcs.mvc.service.UrlpzService;
import com.dt.zxhygl.entity.DataGridReturn;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sjcs/sbzt2")
public class Sbzt2Controller {

	private static Logger logger = Logger.getLogger(Sbzt2Controller.class);

	@Resource
	private SbztService service;

	@RequestMapping("/listUi")
	public String listUi(){
		return "/sjcs/sbzt2/list";
	}

	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request){
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("sbid", ComUtils.ConvNull(request.getParameter("sbid")));
			paramMap.put("kssj", ComUtils.ConvNull(request.getParameter("kssj")));
			paramMap.put("jssj", ComUtils.ConvNull(request.getParameter("jssj")));
			pageBounds.setParamMap(paramMap);
			List<Sbzt> list = service.findByPage2(pageBounds);
			pageBounds.setList(list);
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询设备状态2列表出错....");
			logger.error(e.getMessage());
		}
		return grid;
	}
}
