package com.dt.sjcs.mvc.service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.zxhygl.mvc.base.service.IBaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SjpzService extends IBaseService<Sjpz> {
	
	List<Sjpz> findList(String sbid);

	List<Sjpz> findYxList(String sbid);

	boolean updateYx(Sjpz t);

	List<Sjpz> findlsByPage(PageBounds pageBounds);

	boolean insertcopy();
	boolean deletesjpz();

	List<Sjpz> findJyList(String sbid, Date date);

	List<Sjpz> findDc(Map param);
	List<Sjpz> findlsDc(Map param);

}
