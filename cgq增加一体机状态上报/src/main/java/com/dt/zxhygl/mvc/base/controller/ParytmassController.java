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
import com.dt.zxhygl.mvc.base.pojo.Committee;
import com.dt.zxhygl.mvc.base.pojo.Party;
import com.dt.zxhygl.mvc.base.pojo.Partymass;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IPartyService;
import com.dt.zxhygl.mvc.base.service.IPartymassService;
import com.dt.zxhygl.utils.UserUtils;

@Controller
@RequestMapping("/base/partymass")
public class ParytmassController {

	private static final Logger logger = Logger.getLogger(ParytmassController.class);
	
	@Resource
	private IPartymassService iPartymassService;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/hygl/base/partymass/list";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request,Model model){
		logger.info("查询党群系统列表....");
		DataGridReturn grid = null;
		
		try {
//			User currentUser = UserUtils.getCurrentUser();

			//分页参数（固定的）
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			
			Map<String,String> paramMap = new HashMap<String,String>();
			
			String zname = ComUtils.ConvNull(request.getParameter("zname"));
			paramMap.put("zname", zname);
			
			String zlinkman = ComUtils.ConvNull(request.getParameter("zlinkman"));
			paramMap.put("zlinkman", zlinkman);
			String ztel = ComUtils.ConvNull(request.getParameter("ztel"));
			paramMap.put("ztel", ztel);

			pageBounds.setParamMap(paramMap);
			
			List<Partymass> list = iPartymassService.findByPage(pageBounds);
			pageBounds.setList(list);
			
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询党群系统列表出错....");
			logger.error(e.getMessage());
		}
		return grid;
		
	}
	
	
	@RequestMapping("/addUi")
	public String addUi(){
		return "/hygl/base/partymass/add";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxJson save(@RequestBody Partymass partymass){
		logger.info("保存党群系统信息....");
		boolean success = false;
		try {
			success = iPartymassService.save(partymass);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存党群系统失败....");
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
	public Partymass getById(String id){
		logger.info("查询党群系统信息....id="+id+"");
		Partymass partymass = null;
		try {
			partymass = iPartymassService.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询党群系统信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		return partymass;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxJson delete(String id){
		logger.info("删除党群系统信息....id="+id+"");
		boolean success = false;
		try {
			success = iPartymassService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除党群系统失败....id="+id+"");
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
		logger.info("添加查询政协委员名【"+username+"】是否重复....");
		Map<String,Object> result = new HashMap<String,Object>();
		boolean flag = false;
		try {
//			Committee user = iCommitteeService.selectByUsername(username);
//			if(user!=null){
//				flag = true;
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("添加查询政协委员名【"+username+"】是否重复失败....");
			logger.error(e.getMessage());
		}
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping("/checkUsernameAndZid")
	@ResponseBody
	public Map<String,Object> checkUsernameAndZid(String username,String zid){
		logger.info("修改查询政协委员名【"+username+"】是否重复....");
		Map<String,Object> result = new HashMap<String,Object>();
		boolean flag = false;
		try {
//			Committee user = iCommitteeService.selectByUsernameWithOutZid(username, zid);
//			if(user!=null){
//				flag = true;
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("修改查询政协委员名【"+username+"】是否重复失败....");
			logger.error(e.getMessage());
		}
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping("/selDuoUi")
	public String selDuoUi(){
		return "/hygl//base/committee/selDuo";
	}
	
	@RequestMapping("/zhglUi")
	public String zhglUi(){
		return "/hygl//base/committee/zhgl";
	}
}
