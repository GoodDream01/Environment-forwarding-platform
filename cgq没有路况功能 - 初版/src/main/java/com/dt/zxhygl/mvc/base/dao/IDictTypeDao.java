package com.dt.zxhygl.mvc.base.dao;

import java.util.List;

import com.dt.zxhygl.mvc.base.pojo.DictType;

public interface IDictTypeDao {
	DictType selectByPrimaryKey(String id);
	List<DictType> selectAll();
}
