package com.dt.zxhygl.mvc.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.pojo.DictItem;

public interface IDictItemDao {

	DictItem selectByPrimaryKey(String id);
	List<DictItem> selectDictItemByTypeCode(String typeCode);
	List<DictItem> findByPage(@Param("pageBounds") PageBounds pageBounds,@Param("param") Map param);
	
	String getSort(String typeCode);
	
	int insert(DictItem t);
	
	int update(DictItem t);
	
	int delete(String id);
	
	List<DictItem> findAllList();

	DictItem selectDictItemCode(@Param("typeCode")String typeCode,@Param("code")String code);

}
