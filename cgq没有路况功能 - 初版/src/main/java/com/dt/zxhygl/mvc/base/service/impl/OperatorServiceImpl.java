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
import com.dt.common.utils.md5.Md5Encode;
import com.dt.common.utils.srping.ContextHolderUtils;
import com.dt.zxhygl.mvc.base.dao.IOperatorDao;
import com.dt.zxhygl.mvc.base.pojo.Operator;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.IOperatorService;

@Service
public class OperatorServiceImpl implements IOperatorService {

	@Resource
	private IOperatorDao iOperatorDao;

	@Override
	public Operator selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iOperatorDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Operator> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return iOperatorDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Operator> selectAll(Map param) {
		// TODO Auto-generated method stub
		return iOperatorDao.selectAll(param);
	}

	@Override
	public boolean insert(Operator t)  throws Exception{
		// TODO Auto-generated method stub
		String zid = ComUtils.getUniqueString();
		t.setZid(zid);
		
		Md5Encode te=new Md5Encode();
		String zpassword = t.getZpassword();
		zpassword = te.Encrypt(zpassword,"MD5");
		t.setZpassword(zpassword);
		
		return iOperatorDao.insert(t)>0;
	}

	@Override
	public boolean update(Operator t)  throws Exception{
		// TODO Auto-generated method stub
		String zpassword = t.getZpassword();
		if(StringUtils.isNotBlank(zpassword)){
			Md5Encode te=new Md5Encode();
			zpassword = te.Encrypt(zpassword,"MD5");
			t.setZpassword(zpassword);
		}else{
			t.setZpassword(null);
		}
		return iOperatorDao.update(t)>0;
	}

	@Override
	public boolean delete(String id)  throws Exception{
		// TODO Auto-generated method stub
		return iOperatorDao.delete(id)>0;
	}

	@Override
	public boolean save(Operator t)  throws Exception{
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getZid())){
			return this.insert(t);
		}
		return this.update(t);
	}

	@Override
	public boolean updateBatch(List<Operator> list)  throws Exception{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User loginByUserNameAndPassword(User user) {
		// TODO Auto-generated method stub
		return iOperatorDao.loginByUserNameAndPassword(user);
	}

	@Override
	public List<Operator> selectByGzry() {
		// TODO Auto-generated method stub
		return iOperatorDao.selectByGzry();
	}

	@Override
	public Operator selectByUsernameWithOutZid(String username, String zid) {
		// TODO Auto-generated method stub
		return iOperatorDao.selectByUsernameWithOutZid(username, zid);
	}

	@Override
	public Operator selectByUsername(String username) {
		// TODO Auto-generated method stub
		return iOperatorDao.selectByUsername(username);
	}


}
