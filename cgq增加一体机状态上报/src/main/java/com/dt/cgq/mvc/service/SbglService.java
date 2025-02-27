package com.dt.cgq.mvc.service;

import java.util.List;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.service.IBaseService;

public interface SbglService extends IBaseService<Sbgl>{

	Cgqsj selectCgqsj(String jd, String wd);

	String getSbbh(String jd, String wd);

	List<Cgqsj> findByPage1(PageBounds pageBounds);
	
	List<Sbgl> selectSb(String bj);
	
	List<Cgqsj> selectSfzx(String sbbh);

}
