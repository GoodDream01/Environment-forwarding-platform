package com.dt.sjcs.mvc.service.impl;

import com.dt.sjcs.mvc.dao.SjtjDao;
import com.dt.sjcs.mvc.pojo.Sjtj;
import com.dt.sjcs.mvc.service.SjtjService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SjtjServiceImpl implements SjtjService{

	@Resource
	private SjtjDao dao;

	@Override
	public List<Sjtj> findSbbh() {
		return dao.findSbbh();
	}

	@Override
	public List<Sjtj> TjByDay(String sbid, String kssj, String jssj) {
		return dao.TjByDay(sbid,kssj,jssj);
	}

	@Override
	public List<Sjtj> TjByHour(String sbid,String kssj,String jssj) {
		return dao.TjByHour(sbid,kssj,jssj);
	}

}
