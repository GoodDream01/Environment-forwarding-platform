package com.dt.taje.mvc.jcsj.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.dt.taje.mvc.base.dao.CommonDaoI;

public interface CbdwxxDaoI  extends CommonDaoI {
	
	void insert(HashMap hashMap, Connection conn) throws Exception;
	
	void update(HashMap hashMap, Connection conn) throws Exception ;
	
	HashMap getById(String id);
	
	void del(String id, Connection conn) throws Exception ;
	
	String getMaxSortIndex();
	
	void up(String id, Connection conn) throws Exception ;
	
	void down(String id, Connection conn) throws Exception ;
	
	HashMap getByUnitcode(String zUnitcode);
	
	HashMap getByUnitcode(String id,String zUnitcode);
	
	HashMap getByCode(String zCode);
	
	HashMap getByCode(String id,String zCode);
}
