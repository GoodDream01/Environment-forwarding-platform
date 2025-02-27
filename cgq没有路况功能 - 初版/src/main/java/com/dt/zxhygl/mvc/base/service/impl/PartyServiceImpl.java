package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.zxhygl.mvc.base.dao.IPartyDao;
import com.dt.zxhygl.mvc.base.pojo.Party;
import com.dt.zxhygl.mvc.base.service.IPartyService;

@Service
public class PartyServiceImpl implements IPartyService{

	@Resource
	private IPartyDao iPartyDao;
	@Override
	public Party selectByPrimaryKey(String id) {
		return iPartyDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Party> findByPage(PageBounds pageBounds) {
		return iPartyDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Party> selectAll(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Party t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Party t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return iPartyDao.delete(id)>0;
	}

	@Override
	public boolean save(Party t) throws Exception {
		if(t.getZid()!=null && !"".equals(t.getZid())){
			return iPartyDao.update(t)>0;
		}else{
			t.setZid(ComUtils.getUniqueString());
			return iPartyDao.insert(t)>0;
		}
	}

	@Override
	public boolean updateBatch(List<Party> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
