package com.dt.zxhygl.mvc.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dt.zxhygl.mvc.base.dao.IDemoBarChartDao;
import com.dt.zxhygl.mvc.base.service.IDemoBarChartService;

@Service
public class IDemoBarChartServiceImpl implements IDemoBarChartService {

	@Resource
	private IDemoBarChartDao iDemoBarChartDao;
	@Override
	public int getHyCountByMonth(String year, String month) {
		// TODO Auto-generated method stub
		return iDemoBarChartDao.getHyCountByMonth(year, month);
	}

}
