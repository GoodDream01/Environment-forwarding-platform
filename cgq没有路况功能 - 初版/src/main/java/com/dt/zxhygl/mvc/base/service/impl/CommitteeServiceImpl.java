package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.common.utils.srping.ContextHolderUtils;
import com.dt.zxhygl.mvc.base.dao.ICommitteeDao;
import com.dt.zxhygl.mvc.base.pojo.Committee;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.ICommitteeService;

@Service
public class CommitteeServiceImpl implements ICommitteeService {

	@Resource
	private ICommitteeDao iCommitteeDao;

	@Override
	public Committee selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iCommitteeDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Committee> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return iCommitteeDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Committee> selectAll(Map param) {
		// TODO Auto-generated method stub
		return iCommitteeDao.selectAll(param);
	}

	@Override
	public boolean insert(Committee t)  throws Exception{
		// TODO Auto-generated method stub
		return iCommitteeDao.insert(t)>0;
	}

	@Override
	public boolean update(Committee t)  throws Exception{
		// TODO Auto-generated method stub
		return iCommitteeDao.update(t)>0;
	}

	@Override
	public boolean delete(String id)  throws Exception{
		// TODO Auto-generated method stub
		return iCommitteeDao.delete(id)>0;
	}

	@Override
	public boolean save(Committee t)  throws Exception{
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getZid())){
			return this.insert(t);
		}
		return this.update(t);
	}

	@Override
	public boolean updateBatch(List<Committee> list)  throws Exception{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User loginByUserNameAndPassword(User user) {
		// TODO Auto-generated method stub
		return iCommitteeDao.loginByUserNameAndPassword(user);
	}

	@Override
	public Committee selectByUsernameWithOutZid(String username, String zid) {
		// TODO Auto-generated method stub
		return iCommitteeDao.selectByUsernameWithOutZid(username, zid);
	}

	@Override
	public Committee selectByUsername(String username) {
		// TODO Auto-generated method stub
		return iCommitteeDao.selectByUsername(username);
	}


}
