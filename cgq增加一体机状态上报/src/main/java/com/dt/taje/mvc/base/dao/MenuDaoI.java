package com.dt.taje.mvc.base.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;



public interface MenuDaoI extends CommonDaoI {
	void insertMenu(HashMap hashMap, Connection conn) throws Exception;
	void updateMenu(HashMap hashMap, Connection conn) throws Exception ;
	HashMap getMenuById(String id);
	void delMenu(String id, Connection conn) throws Exception ;
	List getMenuTree();
	List getUserMenuTree(String uSERID);

}
