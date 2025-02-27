package com.dt.pmkz.mvc.service;

import java.util.List;

import com.dt.pmkz.mvc.pojo.Yagl;
import com.dt.zxhygl.mvc.base.service.IBaseService;

public interface YaglService extends IBaseService<Yagl>{

	Yagl selectByYaPm(String yadj,String pmid);
	
	List<Yagl> selectByYa(String yadj);
	
}
