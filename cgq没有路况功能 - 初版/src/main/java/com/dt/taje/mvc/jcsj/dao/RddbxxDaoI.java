package com.dt.taje.mvc.jcsj.dao;

import java.sql.Connection;
import java.util.HashMap;

import com.dt.taje.mvc.base.dao.CommonDaoI;

public interface RddbxxDaoI extends CommonDaoI {
	
	void insert(HashMap hashMap, Connection conn) throws Exception;
	
	void update(HashMap hashMap, Connection conn) throws Exception ;
	
	HashMap getById(String id);
	
	void del(String id, Connection conn) throws Exception ;
	
	

}
