package com.dt.sjcs.mvc.service.impl;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.dao.MlpzDao;
import com.dt.sjcs.mvc.pojo.Mlpz;
import com.dt.sjcs.mvc.service.MlpzService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MlpzServiceImpl implements MlpzService{

	@Resource
	private MlpzDao dao;

	@Override
	public Mlpz selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Mlpz> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public List<Mlpz> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Mlpz t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Mlpz t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Mlpz t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Mlpz> list) throws Exception {
		return false;
	}

}
