package com.dt.zxhygl.mvc.base.service;

import java.util.List;

import com.dt.zxhygl.mvc.base.pojo.Unit;

public interface IUnitService extends IBaseService<Unit> {
	List<Unit> selectByParentXqbh(String xqbh);
	List<Unit> selectXqbhByAll(String zxqbh);
}
