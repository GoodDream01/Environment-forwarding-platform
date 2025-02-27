package com.dt.sjcs.mvc.service.impl;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.dao.YxpzDao;
import com.dt.sjcs.mvc.pojo.Yxpz;
import com.dt.sjcs.mvc.service.YxpzService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class YxpzServiceImpl implements YxpzService{

	@Resource
	private YxpzDao dao;

	@Override
	public Yxpz selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Yxpz> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public List<Yxpz> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Yxpz t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Yxpz t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Yxpz t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Yxpz> list) throws Exception {
		return false;
	}

	@Override
	public List<Yxpz> findList() {
		// TODO Auto-generated method stub
		return dao.findList();
	}

	@Override
	public String findSbbh(String dwid) {
		return dao.findSbbh(dwid);
	}

}
