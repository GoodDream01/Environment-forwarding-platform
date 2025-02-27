package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.dao.IUnitDao;
import com.dt.zxhygl.mvc.base.pojo.Unit;
import com.dt.zxhygl.mvc.base.service.IUnitService;

@Service
public class UnitServiceImpl implements IUnitService {

	@Resource
	private IUnitDao iUnitDao;
	
	@Override
	public Unit selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iUnitDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Unit> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return iUnitDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Unit> selectAll(Map param) {
		// TODO Auto-generated method stub
		return iUnitDao.selectAll(param);
	}

	@Override
	public boolean insert(Unit t) throws Exception {
		// TODO Auto-generated method stub
		return iUnitDao.insert(t)>0;
	}

	@Override
	public boolean update(Unit t) throws Exception {
		// TODO Auto-generated method stub
		return iUnitDao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return iUnitDao.delete(id)>0;
	}

	@Override
	public boolean save(Unit t) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(t.getZid())){
			return update(t);
		}
		return insert(t);
	}

	@Override
	public boolean updateBatch(List<Unit> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Unit> selectByParentXqbh(String xqbh) {
		// TODO Auto-generated method stub
		return iUnitDao.selectByParentXqbh(xqbh);
	}
	@Override
	public List<Unit> selectXqbhByAll(String zxqbh) {
		// TODO Auto-generated method stub
		return iUnitDao.selectXqbhByAll(zxqbh);
	}

}
