package com.dt.cgq.mvc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.dt.cgq.mvc.dao.SbglDao;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.service.SbglService;
import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;

@Service
public class SbglServiceImpl implements SbglService{

	@Resource
	private SbglDao dao;
	
	@Override
	public Sbgl selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Sbgl> findByPage(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Sbgl> selectAll(Map param) {
		// TODO Auto-generated method stub
		return dao.selectAll(param);
	}

	@Override
	public boolean insert(Sbgl t) throws Exception {
		// TODO Auto-generated method stub
		t.setId(ComUtils.getUniqueString());
		return dao.insert(t)>0;
	}

	@Override
	public boolean update(Sbgl t) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(t)>0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id)>0;
	}

	@Override
	public boolean save(Sbgl t) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtils.isNotEmpty(t.getId())) {
			return insert(t);
		}else{
			return update(t);
		}
	}

	@Override
	public boolean updateBatch(List<Sbgl> list) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cgqsj selectCgqsj(String jd, String wd) {
		// TODO Auto-generated method stub
		String sbbh = dao.selectSbbh(jd,wd);
		return dao.selectCgqsj(sbbh);
	}

	@Override
	public String getSbbh(String jd, String wd) {
		// TODO Auto-generated method stub
		return dao.selectSbbh(jd,wd);
	}

	@Override
	public List<Cgqsj> findByPage1(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findByPage1(pageBounds, pageBounds.getParamMap());
	}

	@Override
	public List<Sbgl> selectSb(String bj) {
		// TODO Auto-generated method stub
		return dao.selectSb(bj);
	}

	@Override
	public List<Cgqsj> selectSfzx(String sbbh) {
		// TODO Auto-generated method stub
		return dao.selectSfzx(sbbh);
	}

}
