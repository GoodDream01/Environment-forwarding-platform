package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.zxhygl.mvc.base.dao.IMediaDao;
import com.dt.zxhygl.mvc.base.pojo.Media;
import com.dt.zxhygl.mvc.base.service.IMediaService;

@Service
public class MediaServiceImpl implements IMediaService{

	@Resource
	private IMediaDao iMediaDao;
	@Override
	public Media selectByPrimaryKey(String id) {
		return iMediaDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Media> findByPage(PageBounds pageBounds) {
		return iMediaDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Media> selectAll(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Media t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Media t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return iMediaDao.delete(id)>0;
	}

	@Override
	public boolean save(Media t) throws Exception {
		if(t.getZid()!=null && !"".equals(t.getZid())){
			return iMediaDao.update(t)>0;
		}else{
			t.setZid(ComUtils.getUniqueString());
			return iMediaDao.insert(t)>0;
		}
	}

	@Override
	public boolean updateBatch(List<Media> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
