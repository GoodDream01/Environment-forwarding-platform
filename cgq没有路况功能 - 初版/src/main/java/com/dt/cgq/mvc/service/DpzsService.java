package com.dt.cgq.mvc.service;

import java.util.List;

import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Dpzs;

public interface DpzsService {

	List<Cgqsj> findZxsj();
	List<Dpzs> findYcsj(String state);
	
	List<Dpzs> findFlsj(String sbbh,String kssj,String jssj);
	
}
