package com.dt.sjcs.mvc.service;

import java.util.List;

import com.dt.sjcs.mvc.pojo.Urlpz;
import com.dt.zxhygl.mvc.base.service.IBaseService;

public interface UrlpzService extends IBaseService<Urlpz> {
	
	List<Urlpz> findList();

	String findSbbh(String dwid);

}
