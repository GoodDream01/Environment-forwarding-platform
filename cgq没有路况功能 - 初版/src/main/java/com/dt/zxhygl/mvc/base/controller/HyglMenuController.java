package com.dt.zxhygl.mvc.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt.sjcs.mvc.pojo.Sjtj;
import com.dt.sjcs.mvc.service.SjtjService;
import com.dt.sjcs.mvc.service.UrlpzService;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IDictItemService;
import com.dt.zxhygl.utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.mvc.base.pojo.Menu;
import com.dt.zxhygl.mvc.base.pojo.Operator;
import com.dt.zxhygl.mvc.base.service.IMenuService;

@Controller
@RequestMapping("/hygl/menu")
public class HyglMenuController {
	private static Logger logger = Logger.getLogger(HyglMenuController.class);

	@Resource
	private IMenuService iMenuService;
	@Resource
	private SjtjService service;
	@Resource
	private UrlpzService urlpzservice;
	@Resource
	private IDictItemService idictitemservice;
	
	/**
	 * 取得左侧菜单的方法
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getLeftMenu")
	@ResponseBody
	public List<Menu> getLeftMenu(HttpServletRequest request){
		List<Menu> mlist = iMenuService.getLeftMenu(request);
		List<Sjtj> list = new ArrayList<>();
		User currentUser = UserUtils.getCurrentUser();
		if(!currentUser.getRole().equals("0")){
			String sbbh[] = urlpzservice.findSbbh(currentUser.getQyid()).split(",");
			for (int i = 0; i < sbbh.length; i++) {
				Sjtj t = new Sjtj();
				t.setName(sbbh[i]);
				list.add(t);
			}
		}else{
			list = service.findSbbh();
		}
		for (int i = 0; i < mlist.size(); i++) {
			if(mlist.get(i).getMenuName().equals("当前数据")){
				for (int j = 0; j < list.size(); j++) {
					Menu menu = new Menu();
					menu.setMenuId(list.get(j).getName());
					menu.setMenuCode(list.get(j).getName());
					menu.setMenuPcode(mlist.get(i).getMenuId());
					DictItem dictItem = idictitemservice.selectDictItemCode("100904",list.get(j).getName());
					if(dictItem!=null){
						menu.setMenuName(dictItem.getText());
					}else{
						menu.setMenuName(list.get(j).getName());
					}
					menu.setMenuPath("/sjcs/sjpz/listdqUi.do?sbid="+list.get(j).getName());
					mlist.add(menu);
				}
			}
		}
		return mlist;

	}
	
	@RequestMapping("/qxglUi")
	public String qxglUi(){
		return "/hygl//base/menu/qxgl";
	}
	
	@RequestMapping(value="/getAllMenu")
	@ResponseBody
	public  List<Menu> getAllMenu(HttpServletRequest request,HttpServletResponse response){
		logger.info("查询菜单权限列表....");
		List<Menu> result = null;
		try {
			result =  iMenuService.getAllMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询列菜单权限表出错....");
			logger.error(e.getMessage(),e);
		}
		
		return result;

	}
	
	@RequestMapping("/qxglSave")
	@ResponseBody
	public AjaxJson qxglSave(@RequestBody List<Menu> list){
		logger.info("保存菜单权限信息....");
		boolean success = false;
		try {
			iMenuService.updateMenuRole(list);
			success = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存菜单权限失败....");
			logger.error(e.getMessage(),e);
		}
		String msg = "保存失败！";
		if(success){
			msg = "保存成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	

}
