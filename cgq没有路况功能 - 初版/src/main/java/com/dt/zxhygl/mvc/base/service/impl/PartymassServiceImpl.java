package com.dt.zxhygl.mvc.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.zxhygl.mvc.base.dao.IPartymassDao;
import com.dt.zxhygl.mvc.base.pojo.Partymass;
import com.dt.zxhygl.mvc.base.service.IPartymassService;

@Service
public class PartymassServiceImpl implements IPartymassService{

	@Resource
	private IPartymassDao iPartymassDao;
	@Override
	public Partymass selectByPrimaryKey(String id) {
		return iPartymassDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Partymass> findByPage(PageBounds pageBounds) {
		return iPartymassDao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Partymass> selectAll(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Partymass t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Partymass t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return iPartymassDao.delete(id)>0;
	}

	@Override
	public boolean save(Partymass t) throws Exception {
		if(t.getZid()!=null && !"".equals(t.getZid())){
			return iPartymassDao.update(t)>0;
		}else{
			t.setZid(ComUtils.getUniqueString());
			return iPartymassDao.insert(t)>0;
		}
	}

	@Override
	public boolean updateBatch(List<Partymass> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
