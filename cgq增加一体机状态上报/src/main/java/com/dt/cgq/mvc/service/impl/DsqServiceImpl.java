package com.dt.cgq.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.DsqDao;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Cgqyc;
import com.dt.cgq.mvc.pojo.Rygl;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.pojo.Ycsj;
import com.dt.cgq.mvc.service.DsqService;
import com.dt.common.utils.ComUtils;

@Service
public class DsqServiceImpl implements DsqService{
	
	@Resource
	private DsqDao dao;

	@Override
	public List<Cgqsj> find30Sj(String sbbh) {
		// TODO Auto-generated method stub
		return dao.find30Sj(sbbh);
	}

	@Override
	public List<Cgqsj> findycSj(String sbbh) {
		// TODO Auto-generated method stub
		return dao.findycSj(sbbh);
	}

	@Override
	public List<Cgqsj> findzcSj(String sbbh) {
		// TODO Auto-generated method stub
		return dao.findzcSj(sbbh);
	}
	
	@Override
	public boolean insert(Cgqyc t) {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public List<Rygl> findPhone(String id) {
		// TODO Auto-generated method stub
		return dao.findPhone(id);
	}

	@Override
	public boolean update(Sbgl t) {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean update1(Sbgl t) {
		// TODO Auto-generated method stub
		return dao.update1(t)>0;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return dao.delete()>0;
	}

	@Override
	public List<Ycsj> findSpxx(String sbbh) {
		// TODO Auto-generated method stub
		return dao.findSpxx(sbbh);
	}

}
