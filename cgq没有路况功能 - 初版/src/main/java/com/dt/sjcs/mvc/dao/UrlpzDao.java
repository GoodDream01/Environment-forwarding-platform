package com.dt.sjcs.mvc.dao;

import java.util.List;

import com.dt.sjcs.mvc.pojo.Urlpz;
import com.dt.zxhygl.mvc.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

public interface UrlpzDao extends IBaseDao<Urlpz> {
	
	List<Urlpz> findList();

	String findSbbh(@Param("dwid")String dwid);
	
}
