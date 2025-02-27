package com.dt.cgq.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.DxjlDao;
import com.dt.cgq.mvc.pojo.Dxjl;
import com.dt.cgq.mvc.service.DxjlService;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;

@Service
public class DxjlServiceImpl implements DxjlService{

	@Resource
	private DxjlDao dao;
	
	@Override
	public Dxjl selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Dxjl> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Dxjl> selectAll(Map param) {
		// TODO Auto-generated method stub
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Dxjl t) throws Exception {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Dxjl t) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Dxjl t) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Dxjl> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
