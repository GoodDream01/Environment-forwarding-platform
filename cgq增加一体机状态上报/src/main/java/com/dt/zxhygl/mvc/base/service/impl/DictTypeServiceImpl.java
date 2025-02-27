package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.zxhygl.mvc.base.dao.IDictItemDao;
import com.dt.zxhygl.mvc.base.dao.IDictTypeDao;
import com.dt.zxhygl.mvc.base.pojo.DictType;
import com.dt.zxhygl.mvc.base.service.IDictTypeService;

@Service
public class DictTypeServiceImpl implements IDictTypeService {
	@Resource
	private IDictTypeDao iDictTypeDao;

	@Override
	public DictType selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iDictTypeDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DictType> selectAll() {
		// TODO Auto-generated method stub
		return iDictTypeDao.selectAll();
	}

}
