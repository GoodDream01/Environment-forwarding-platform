package com.dt.cgq.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.CgqsjDao;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.service.CgqsjService;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;

@Service
public class CgqsjServiceImpl implements CgqsjService{

	@Resource
	private CgqsjDao dao;
	
	@Override
	public Cgqsj selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Cgqsj> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Cgqsj> selectAll(Map param) {
		// TODO Auto-generated method stub
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Cgqsj t) throws Exception {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Cgqsj t) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Cgqsj t) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Cgqsj> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
