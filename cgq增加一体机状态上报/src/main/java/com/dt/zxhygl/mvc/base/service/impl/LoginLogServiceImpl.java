package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.dao.ILoginLogDao;
import com.dt.zxhygl.mvc.base.pojo.LoginLog;
import com.dt.zxhygl.mvc.base.service.ILoginLogService;

@Service
public class LoginLogServiceImpl implements ILoginLogService {

	@Resource
	private ILoginLogDao iLoginLogDao;
	
	@Override
	public LoginLog selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iLoginLogDao.selectByPrimaryKey(id);
	}

	@Override
	public List<LoginLog> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return iLoginLogDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<LoginLog> selectAll(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(LoginLog t) {
		// TODO Auto-generated method stub
		return iLoginLogDao.insert(t)>0;
	}

	@Override
	public boolean update(LoginLog t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(LoginLog t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatch(List<LoginLog> list) {
		// TODO Auto-generated method stub
		return false;
	}

}
