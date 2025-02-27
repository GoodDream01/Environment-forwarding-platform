package com.dt.cgq.mvc.controller;

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
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.service.SbglService;

@Controller
@RequestMapping("/cgq/sbgl")
public class SbglController {

	private static Logger logger = Logger.getLogger(SbglController.class);
	
	@Resource
	private SbglService service;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/sbgl/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/cgq/sbgl/add";
	}
	@RequestMapping("/addUi1")
	public String addUi1(){
		return "/cgq/sbgl/add1";
	}
	@RequestMapping("/spUi")
	public String spUi(){
		return "/cgq/sbgl/spxx";
	}
	@RequestMapping("/viewUi")
	public String viewUi(){
		return "/cgq/sbgl/view";
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
			User currentUser = UserUtils.getCurrentUser();
			paramMap.put("bj", currentUser.getRole());
			paramMap.put("qyid", currentUser.getQyid());
			paramMap.put("ssid", ComUtils.ConvNull(request.getParameter("ssid")));
			paramMap.put("mc", ComUtils.ConvNull(request.getParameter("mc")));
			paramMap.put("dz", ComUtils.ConvNull(request.getParameter("dz")));
			pageBounds.setParamMap(paramMap);
			List<Sbgl> list = service.findByPage(pageBounds);
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
	
	@RequestMapping("/getList1")
	@ResponseBody
	public DataGridReturn getList1(HttpServletRequest request,Model model){
		logger.info("查询设备管理列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			String jd = ComUtils.ConvNull(request.getParameter("jd"));
			String wd = ComUtils.ConvNull(request.getParameter("wd"));
			paramMap.put("sbbh", service.getSbbh(jd, wd));
			pageBounds.setParamMap(paramMap);
			List<Cgqsj> list = service.findByPage1(pageBounds);
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
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Sbgl> selectAll(HttpServletRequest request,Model model){
		Map<String,String> paramMap = new HashMap<String,String>();
		User currentUser = UserUtils.getCurrentUser();
		paramMap.put("bj", currentUser.getRole());
		paramMap.put("qyid", currentUser.getQyid());
		return service.selectAll(paramMap);
	}
	
	@RequestMapping("/selectSb")
	@ResponseBody
	public List<Sbgl> selectSb(HttpServletRequest request){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("bj", ComUtils.ConvNull(request.getParameter("bj")));
		List<Sbgl> list = service.selectAll(paramMap);
		List<Cgqsj> sj = service.selectSfzx(ComUtils.ConvNull(request.getParameter("bj")));
		for(int i=0;i<list.size();i++) {
			list.get(i).setBj("1");
			for(int j=0;j<sj.size();j++) {
				if(list.get(i).getSbbh().equals(sj.get(j).getSbbh())) {
					list.get(i).setBj("0");
					list.get(i).setFldj(sj.get(j).getFldj());
				}
			}
		}		
		return list;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Sbgl getById(String id){
		logger.info("查询人员管理信息....id="+id+"");
		Sbgl t = null;
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
	
	@RequestMapping("/getByJwd")
	@ResponseBody
	public Cgqsj getByJwd(String jd,String wd){
		Cgqsj t = null;
		try {
			t = service.selectCgqsj(jd,wd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return t;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public AjaxJson save(@RequestBody Sbgl t){
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
