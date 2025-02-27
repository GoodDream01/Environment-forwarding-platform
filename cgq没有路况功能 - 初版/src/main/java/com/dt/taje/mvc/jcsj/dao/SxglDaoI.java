package com.dt.taje.mvc.jcsj.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.dt.taje.mvc.base.dao.CommonDaoI;

public interface SxglDaoI  extends CommonDaoI {
	
	void insert(HashMap hashMap, Connection conn) throws Exception;
	
	void update(HashMap hashMap, Connection conn) throws Exception ;
	
	HashMap getById(String id);
	
	void del(String id, Connection conn) throws Exception ;
	
	HashMap getByXx(String zYear,String zCircles,String zNumber);
	
	HashMap getByXx(String id,String zYear,String zCircles,String zNumber);
}
