package com.dt.pmkz.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.pmkz.mvc.dao.PmDao;
import com.dt.pmkz.mvc.pojo.Pm;
import com.dt.pmkz.mvc.service.PmService;

@Service
public class PmServiceImpl implements PmService{

	@Resource
	private PmDao dao;
	
	@Override
	public Pm selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Pm> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Pm> selectAll(Map param) {
		// TODO Auto-generated method stub
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Pm t) throws Exception {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Pm t) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Pm t) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Pm> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
