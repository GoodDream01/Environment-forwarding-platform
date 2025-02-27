package com.dt.zxhygl.mvc.base.dao;

import java.util.List;

import com.dt.zxhygl.mvc.base.pojo.Unit;

public interface IUnitDao extends IBaseDao<Unit> {

	List<Unit> selectByParentXqbh(String xqbh);
	List<Unit> selectXqbhByAll(String zxqbh);
}
