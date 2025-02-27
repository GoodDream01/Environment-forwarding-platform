package com.dt.zxhygl.mvc.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dt.common.interceptor.pagination.model.PageBounds;

public interface IBaseDao<T> {

	T selectByPrimaryKey(String id);
	
	List<T> findByPage(@Param("pageBounds") PageBounds pageBounds,@Param("param") Map param);

	List<T> findByPage2(@Param("pageBounds") PageBounds pageBounds,@Param("param") Map param);
	
	List<T> selectAll(@Param("param") Map param);
	
	int insert(T t);
	
	int insertBatch(List<T> list);
	
	int update(T t);
	
	int updateBatch(List<T> list);
	
	int delete(String id);
}
