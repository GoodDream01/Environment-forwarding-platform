package com.dt.sjcs.mvc.dao;

import com.dt.sjcs.mvc.pojo.Yxpz;
import com.dt.zxhygl.mvc.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YxpzDao extends IBaseDao<Yxpz> {
	
	List<Yxpz> findList();

	String findSbbh(@Param("dwid") String dwid);
	
}
