package com.dt.sjcs.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.sjcs.mvc.dao.UrlpzDao;
import com.dt.sjcs.mvc.pojo.Urlpz;
import com.dt.sjcs.mvc.service.UrlpzService;

@Service
public class UrlpzServiceImpl implements UrlpzService{

	@Resource
	private UrlpzDao dao;

	@Override
	public Urlpz selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Urlpz> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public List<Urlpz> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Urlpz t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Urlpz t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Urlpz t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Urlpz> list) throws Exception {
		return false;
	}

	@Override
	public List<Urlpz> findList() {
		// TODO Auto-generated method stub
		return dao.findList();
	}

	@Override
	public String findSbbh(String dwid) {
		return dao.findSbbh(dwid);
	}

}
