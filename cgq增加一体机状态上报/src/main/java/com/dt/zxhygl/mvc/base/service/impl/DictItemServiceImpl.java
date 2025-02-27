package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dt.zxhygl.mvc.base.dao.IDictItemDao;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.CacheUtils;
import com.dt.common.utils.ComUtils;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.service.IDictItemService;
import com.dt.zxhygl.utils.DictUtils;

@Service
public class DictItemServiceImpl implements IDictItemService {
	@Resource
	private IDictItemDao iDictItemDao;

	@Override
	public DictItem selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return iDictItemDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DictItem> selectDictItemByTypeCode(String typeCode) {
		// TODO Auto-generated method stub
		return iDictItemDao.selectDictItemByTypeCode(typeCode);
	}

	@Override
	public List<DictItem> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return iDictItemDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public String getSort(String typeCode) {
		// TODO Auto-generated method stub
		return iDictItemDao.getSort(typeCode);
	}

	@Override
	public boolean insert(DictItem t) {
		// TODO Auto-generated method stub
		String id = ComUtils.getUniqueString();
		t.setId(id);
		t.setStatus("0");
		return iDictItemDao.insert(t)>0;
	}

	@Override
	public boolean update(DictItem t) {
		// TODO Auto-generated method stub
		return iDictItemDao.update(t)>0;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		return iDictItemDao.delete(id)>0;
	}

	@Override
	public boolean save(DictItem t) {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())){
			return this.insert(t);
		}
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		return this.update(t);
	}

	@Override
	public DictItem selectDictItemCode(String typeCode, String code) {
		return iDictItemDao.selectDictItemCode(typeCode, code);
	}

}
