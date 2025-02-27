package com.dt.sjcs.mvc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.sjcs.mvc.dao.SjpzDao;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.sjcs.mvc.service.SjpzService;

@Service
public class SjpzServiceImpl implements SjpzService{

	@Resource
	private SjpzDao dao;

	@Override
	public Sjpz selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Sjpz> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public List<Sjpz> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Sjpz t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Sjpz t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Sjpz t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Sjpz> list) throws Exception {
		return false;
	}

	@Override
	public List<Sjpz> findList(String sbid) {
		// TODO Auto-generated method stub
		return dao.findList(sbid);
	}

	@Override
	public List<Sjpz> findYxList(String sbid) {
		return dao.findYxList(sbid);
	}

	@Override
	public boolean updateYx(Sjpz t) {
		return dao.updateYx(t)>0;
	}

	@Override
	public List<Sjpz> findlsByPage(PageBounds pageBounds) {
		return dao.findlsByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public boolean insertcopy() {
		return dao.insertcopy()>0;
	}

	@Override
	public boolean deletesjpz() {
		return dao.deletesjpz()>0;
	}

	@Override
	public List<Sjpz> findJyList(String sbid, Date date) {
		return dao.findJyList(sbid, date);
	}

	@Override
	public List<Sjpz> findDc(Map param) {
		return dao.findDc(param);
	}

	@Override
	public List<Sjpz> findlsDc(Map param) {
		return dao.findlsDc(param);
	}

}
