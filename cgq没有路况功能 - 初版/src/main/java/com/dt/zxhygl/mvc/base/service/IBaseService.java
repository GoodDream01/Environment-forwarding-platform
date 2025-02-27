package com.dt.zxhygl.mvc.base.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dt.common.interceptor.pagination.model.PageBounds;

public interface IBaseService<T> {
	
	T selectByPrimaryKey(String id);
	
	List<T> findByPage(PageBounds pageBounds);
	
	List<T> selectAll(Map param);
	
	boolean insert(T t) throws Exception;
	
	boolean update(T t) throws Exception;
	
	boolean delete(String id) throws Exception;
	
	boolean save(T t) throws Exception;
	
	boolean updateBatch(List<T> list) throws Exception;
}
