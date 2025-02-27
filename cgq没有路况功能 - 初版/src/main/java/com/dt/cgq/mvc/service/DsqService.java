package com.dt.cgq.mvc.service;

import java.util.List;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Cgqyc;
import com.dt.cgq.mvc.pojo.Rygl;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.pojo.Ycsj;

public interface DsqService {
	
	List<Cgqsj> find30Sj(String sbbh);
	List<Cgqsj> findycSj(String sbbh);
	List<Cgqsj> findzcSj(String sbbh);
	List<Ycsj> findSpxx(String sbbh);
	boolean insert(Cgqyc t);
	List<Rygl> findPhone(String id);
	boolean update(Sbgl t);
	boolean update1(Sbgl t);
	boolean delete();
	
}
