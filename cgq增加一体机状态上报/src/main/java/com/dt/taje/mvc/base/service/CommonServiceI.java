package com.dt.taje.mvc.base.service;

import com.dt.taje.mvc.base.modle.json.DataGridReturn;




public interface CommonServiceI {
	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(String sql);
}
