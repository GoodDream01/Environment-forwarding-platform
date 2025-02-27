package com.dt.pmkz.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.pmkz.mvc.pojo.Yagl;
import com.dt.zxhygl.mvc.base.dao.IBaseDao;

public interface YaglDao extends IBaseDao<Yagl>{
	
	Yagl selectByYaPm(@Param("yadj") String yadj,@Param("pmid") String pmid);
	
	List<Yagl> selectByYa(@Param("yadj") String yadj);

}
