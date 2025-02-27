package com.dt.sjcs.mvc.dao;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.zxhygl.mvc.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SjpzDao extends IBaseDao<Sjpz> {

	List<Sjpz> findList(@Param("sbid") String sbid);

	List<Sjpz> findYxList(@Param("sbid") String sbid);

	int updateYx(Sjpz t);

	List<Sjpz> findlsByPage(@Param("pageBounds") PageBounds pageBounds, @Param("param") Map param);

	int insertcopy();
	int deletesjpz();

	List<Sjpz> findJyList(@Param("sbid") String sbid,@Param("date") Date date);

	List<Sjpz> findDc( @Param("param") Map param);
	List<Sjpz> findlsDc( @Param("param") Map param);
	
}
