package com.dt.cgq.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Cgqyc;
import com.dt.cgq.mvc.pojo.Rygl;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.pojo.Ycsj;

public interface DsqDao {
	
	List<Cgqsj> find30Sj(@Param("sbbh") String sbbh);
	List<Cgqsj> findycSj(@Param("sbbh") String sbbh);
	List<Cgqsj> findzcSj(@Param("sbbh") String sbbh);
	List<Ycsj> findSpxx(@Param("sbbh") String sbbh);
	int insert(Cgqyc t);
	List<Rygl> findPhone(@Param("id") String id);
	int update(Sbgl t);
	int update1(Sbgl t);
	int delete();
	
}
