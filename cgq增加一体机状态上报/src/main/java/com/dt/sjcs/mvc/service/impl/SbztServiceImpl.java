package com.dt.sjcs.mvc.service.impl;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.dao.SbztDao;
import com.dt.sjcs.mvc.pojo.Sbzt;
import com.dt.sjcs.mvc.service.SbztService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SbztServiceImpl implements SbztService{

	@Resource
	private SbztDao dao;

	@Override
	public Sbzt selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Sbzt> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}


	public List<Sbzt> findByPage2(PageBounds pageBounds) {
		return dao.findByPage2(pageBounds,pageBounds.getParamMap());
	}


	@Override
	public List<Sbzt> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Sbzt t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Sbzt t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Sbzt t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Sbzt> list) throws Exception {
		return false;
	}

}
