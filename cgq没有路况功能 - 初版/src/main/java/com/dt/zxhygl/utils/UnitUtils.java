package com.dt.zxhygl.utils;

import java.util.List;
import java.util.Map;

import com.dt.common.utils.CacheUtils;
import com.dt.common.utils.SpringContextHolder;
import com.dt.zxhygl.mvc.base.dao.IUnitDao;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.pojo.Unit;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class UnitUtils {
	private static IUnitDao unitDao = SpringContextHolder.getBean(IUnitDao.class);

	public static final String CACHE_UNIT_LIST = "unitList";
	
	
	public static Unit getUnitByZcode(String zcode){
		List<Unit> unitList = getUnitList();
		for(Unit unit : unitList){
			if(unit.getZcode().equals(zcode)){
				return unit;
			}
		}
		return null;
	}
	
	public static Unit getUnitByXSQ(String xqbh){
		List<Unit> unitList = getUnitList();
		for(Unit unit : unitList){
			if(unit.getZxqbh()!=null && unit.getZxqbh().equals(xqbh)){
				return unit;
			}
		}
		return null;
	}
	
	public static List<Unit> getUnitList_XSQ(){
		List<Unit> unitList = Lists.newArrayList();
		for(Unit unit : getUnitList()){
			if(unit.getZroletype()!=null && unit.getZroletype().equals("4")){
				unitList.add(unit);
			}
		}
		return unitList;
	}
	/**
	 * 根据县市区编号取得下属街道列表
	 */
	public static List<Unit> getUnitList_JD(String xqbh){
		List<Unit> unitList = Lists.newArrayList();
		for(Unit unit : getUnitList()){
			if(unit.getZtype()!=null && unit.getZtype().equals("3") 
					&& unit.getZxqbh()!=null && unit.getZxqbh().indexOf(xqbh)!=-1){
				unitList.add(unit);
			}
		}
		return unitList;
	}
	public static List<DictItem> getUnitList_JD2(String xqbh){
		List<DictItem> unitList = Lists.newArrayList();
		for(Unit unit : getUnitList()){
			if(unit.getZtype()!=null && unit.getZtype().equals("3") 
					&& unit.getZxqbh()!=null && unit.getZxqbh().indexOf(xqbh)!=-1){
				DictItem item = new DictItem();
				item.setText(unit.getZname());
				item.setValue(unit.getZxqbh());
				unitList.add(item);
			}
		}
		return unitList;
	}
	
	
	/**
	 * 根据街道编号取得下属社区列表
	 */
	public static List<Unit> getUnitList_SQ(String xqbh){
		List<Unit> unitList = Lists.newArrayList();
		for(Unit unit : getUnitList()){
			if(unit.getZtype()!=null && unit.getZtype().equals("4") 
					&& unit.getZxqbh()!=null && unit.getZxqbh().indexOf(xqbh)!=-1){
				unitList.add(unit);
			}
		}
		return unitList;
	}
	public static List<DictItem> getUnitList_SQ2(String xqbh){
		List<DictItem> unitList = Lists.newArrayList();
		for(Unit unit : getUnitList()){
			if(unit.getZtype()!=null && unit.getZtype().equals("4") 
					&& unit.getZxqbh()!=null && unit.getZxqbh().indexOf(xqbh)!=-1){
				DictItem item = new DictItem();
				item.setText(unit.getZname());
				item.setValue(unit.getZxqbh());
				unitList.add(item);
			}
		}
		return unitList;
	}
	
	public static List<Unit> getUnitList(){
		@SuppressWarnings("unchecked")
		List<Unit> unitList = (List<Unit>)CacheUtils.get(CACHE_UNIT_LIST);
		if (unitList == null){

			unitList = unitDao.selectAll(null);
			CacheUtils.put(CACHE_UNIT_LIST, unitList);
		}
		return unitList;
	}
}
