package com.dt.zxhygl.mvc.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dt.zxhygl.mvc.base.pojo.Menu;

public interface IMenuService {
	
	Menu selectByPrimaryKey(String id);
	
	List<Menu> selectMenuByName(String value);
	
	List<Menu> selectMenuByPcode(String value);
	
	int insertMenu(Menu menu);
	
	int updateMenu(Menu menu);
	
	int deleteMenu(Menu menu);

	List<Menu> getLeftMenu(HttpServletRequest request);
	
	List<Menu> getAllMenu();
	
	boolean updateMenuRole(List<Menu> list) throws Exception;
	
	
}
