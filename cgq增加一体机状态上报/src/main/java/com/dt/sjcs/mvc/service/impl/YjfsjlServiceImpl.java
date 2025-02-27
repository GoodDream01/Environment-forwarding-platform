package com.dt.sjcs.mvc.service.impl;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.dao.YjfsjlDao;
import com.dt.sjcs.mvc.pojo.Yjfsjl;
import com.dt.sjcs.mvc.service.YjfsjlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class YjfsjlServiceImpl implements YjfsjlService{

	@Resource
	private YjfsjlDao dao;

	@Override
	public Yjfsjl selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Yjfsjl> findByPage(PageBounds pageBounds) {
		return dao.findByPage(pageBounds,pageBounds.getParamMap());
	}

	@Override
	public List<Yjfsjl> selectAll(Map param) {
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Yjfsjl t) throws Exception {
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Yjfsjl t) throws Exception {
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Yjfsjl t) throws Exception {
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Yjfsjl> list) throws Exception {
		return false;
	}

}
