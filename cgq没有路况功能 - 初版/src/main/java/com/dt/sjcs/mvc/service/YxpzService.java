package com.dt.sjcs.mvc.service;

import com.dt.sjcs.mvc.pojo.Yxpz;
import com.dt.zxhygl.mvc.base.service.IBaseService;

import java.util.List;

public interface YxpzService extends IBaseService<Yxpz> {
	
	List<Yxpz> findList();

	String findSbbh(String dwid);

}
