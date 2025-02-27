package com.dt.taje.mvc.base.dao;

import java.sql.Connection;
import java.util.List;

import com.dt.taje.mvc.base.modle.bean.PageBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;


public interface CommonDaoI {
	/**
	 * 执行JDBC查询数据
	 * @param sql
	 * @return
	 */
	public List executeJDBCQuery(String sql);
	
	/**
	 * 执行JDBC查询数据
	 * @param sql
	 * @return
	 */
	public List executeJDBCQuery(String sql,Connection conn);
	
	/**
	 * 执行JDBC查询数据
	 * @param sql
	 * @return
	 */
	public int executeJDBCUpdate(String sql);
	
	/**
	 * 取得分页列表
	 * @param request
	 * @return
	 */
	public PageBean getPageBean(PageBean pageBean);
	
	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(PageBean pageBean);
}
