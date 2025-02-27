package com.dt.cgq.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.AreaDao;
import com.dt.cgq.mvc.pojo.Area;
import com.dt.cgq.mvc.service.AreaService;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;

@Service
public class AreaServiceImpl implements AreaService{

	@Resource
	private AreaDao dao;
	
	@Override
	public Area selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Area> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Area> selectAll(Map param) {
		// TODO Auto-generated method stub
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Area t) throws Exception {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Area t) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Area t) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Area> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
