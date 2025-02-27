package com.dt.zxhygl.mvc.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.zxhygl.mvc.base.pojo.Menu;

public interface IMenuDao {

	Menu selectByPrimaryKey(String id);
	
	List<Menu> selectMenuByName(String value);
	
	List<Menu> selectMenuByLevel(String value);
	
	List<Menu> selectMenuByPcode(String value);
	
	List<Menu> selectAllMenu(@Param("jslx") String jslx,@Param("dwbh") String dwbh);
	
	int insertMenu(Menu menu);
	
	int updateMenu(Menu menu);
	
	int deleteMenu(Menu menu);
	
	List<Menu> getAllMenu();
	
	int updateMenuRole(List<Menu> list);
}
