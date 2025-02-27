package com.dt.zxhygl.mvc.base.service;

import java.util.List;

import com.dt.zxhygl.mvc.base.pojo.DictType;

public interface IDictTypeService {
	DictType selectByPrimaryKey(String id);
	List<DictType> selectAll();
}
