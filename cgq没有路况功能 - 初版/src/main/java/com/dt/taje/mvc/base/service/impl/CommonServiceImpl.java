package com.dt.taje.mvc.base.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dt.taje.mvc.base.dao.CommonDaoI;
import com.dt.taje.mvc.base.modle.bean.PageBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.base.service.CommonServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.ContextHolderUtils;



//@Component("commonService")
//@Service("commonService")
@Service
public class CommonServiceImpl implements CommonServiceI {
	//@Resource(name="CommonDaoImpl")
	@Autowired
	@Qualifier("commonDaoImpl")
	public CommonDaoI commonDao = null;
	
	public void setCommonDao(CommonDaoI commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 返回easyui datagrid模型 
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(String sql) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		PageBean pageBean = ComUtils.getPageBean2(request, sql);
		return commonDao.getDataGridReturn(pageBean);
	}

}
