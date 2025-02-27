package com.dt.cgq.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.dao.IBaseDao;

public interface SbglDao extends IBaseDao<Sbgl>{

	Cgqsj selectCgqsj(@Param("sbbh")String sbbh);

	String selectSbbh(@Param("jd")String jd, @Param("wd")String wd);

	List<Cgqsj> findByPage1(@Param("pageBounds") PageBounds pageBounds,@Param("param") Map param);
	
	List<Sbgl> selectSb(@Param("bj")String bj);
	
	List<Cgqsj> selectSfzx(@Param("bj")String bj);

}
