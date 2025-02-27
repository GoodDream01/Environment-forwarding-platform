package com.dt.cgq.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.DpzsDao;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Dpzs;
import com.dt.cgq.mvc.service.DpzsService;

@Service
public class DpzsServiceImpl implements DpzsService{

	@Resource
	private DpzsDao dao;

	@Override
	public List<Cgqsj> findZxsj() {
		// TODO Auto-generated method stub
		return dao.findZxsj();
	}

	@Override
	public List<Dpzs> findYcsj(String state) {
		// TODO Auto-generated method stub
		return dao.findYcsj(state);
	}

	@Override
	public List<Dpzs> findFlsj(String sbbh,String kssj,String jssj) {
		// TODO Auto-generated method stub
		return dao.findFlsj(sbbh,kssj,jssj);
	}

}
