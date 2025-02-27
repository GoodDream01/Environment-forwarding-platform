package com.dt.zxhygl.mvc.base.service;

import java.util.List;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.zxhygl.mvc.base.pojo.DictItem;

public interface IDictItemService {
	DictItem selectByPrimaryKey(String id);
	List<DictItem> selectDictItemByTypeCode(String typeCode);
	List<DictItem> findByPage(PageBounds pageBounds);
	
	String getSort(String typeCode);
	
	boolean insert(DictItem t);
	
	boolean update(DictItem t);
	
	boolean delete(String id);
	
	boolean save(DictItem t);

	DictItem selectDictItemCode(String typeCode,String code);

}
