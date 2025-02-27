package com.dt.taje.mvc.base.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.dao.CommonDaoI;
import com.dt.taje.mvc.base.modle.bean.PageBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;


@Repository
public class CommonDaoImpl implements CommonDaoI {
	
	@Override
	public List executeJDBCQuery(String sql) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		List list = null;
		try {
			list = bean0.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int executeJDBCUpdate(String sql) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		int r = -1;
		try {
			r = bean0.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public PageBean getPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		if(pageBean!=null){
			String totalSql = pageBean.getTotalSql();
			String sql = pageBean.getSqlStr();
			List list = this.executeJDBCQuery(totalSql);
			int totalNum = 0;
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String num = map.get("NUM").toString();
				try{
					totalNum = Integer.parseInt(num);
				}catch(Exception e){
					totalNum=0;
				}
			}
			pageBean.setRecordCount(totalNum);
			pageBean.setPageCount(pageBean.getPageCount());
			pageBean.setCurrentPage(pageBean.getCurrentPage());
			if(pageBean.getIsLimit()){
				sql = pageBean.getLimitSql();
			}
			list = this.executeJDBCQuery(sql);
			pageBean.setDataList(list);
		}
		return pageBean;
	}

	@Override
	public DataGridReturn getDataGridReturn(PageBean pageBean) {
		// TODO Auto-generated method stub
		pageBean = this.getPageBean(pageBean);
		DataGridReturn gridReturn = new DataGridReturn(pageBean.getRecordCount(),pageBean.getDataList());
		return gridReturn;
	}

	@Override
	public List executeJDBCQuery(String sql, Connection conn) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		List list = null;
		try {
			list = bean0.executeQuery(sql,conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}




}
