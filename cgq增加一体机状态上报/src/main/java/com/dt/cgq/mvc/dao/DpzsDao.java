package com.dt.cgq.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Dpzs;

public interface DpzsDao {
	
	List<Cgqsj> findZxsj();
	List<Dpzs> findYcsj(@Param("state") String state);
	
	List<Dpzs> findFlsj(@Param("sbbh") String sbbh,@Param("kssj") String kssj,@Param("jssj") String jssj);
	
}
