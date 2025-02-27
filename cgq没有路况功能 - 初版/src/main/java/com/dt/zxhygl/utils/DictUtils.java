package com.dt.zxhygl.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.dt.common.utils.srping.ApplicationContextUtil;
import com.dt.taje.utils.ui.StringUtil;
import com.dt.common.mapper.JsonMapper;
import com.dt.common.utils.CacheUtils;
import com.dt.common.utils.ListUtils;
import com.dt.common.utils.SpringContextHolder;
import com.dt.zxhygl.mvc.base.dao.IDictItemDao;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.pojo.DictType;
import com.dt.zxhygl.mvc.base.service.IDictItemService;
import com.dt.zxhygl.mvc.base.service.IDictTypeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DictUtils {
	
	private static IDictItemDao dictDao = SpringContextHolder.getBean(IDictItemDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (DictItem dict : getDictList(type)){
				if (type.equals(dict.getTypeCode()) && value.equals(dict.getValue())){
					return dict.getText();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (DictItem dict : getDictList(type)){
				if (type.equals(dict.getTypeCode()) && label.equals(dict.getText())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<DictItem> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<DictItem>> dictMap = (Map<String, List<DictItem>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (DictItem dict : dictDao.findAllList()){
				List<DictItem> dictList = dictMap.get(dict.getTypeCode());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getTypeCode(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<DictItem> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	public static List<DictItem> getDictList(String type,String px){
		if(StringUtil.isNullOrEmpty(px) || (!px.toLowerCase().equals("asc") && !px.toLowerCase().equals("desc"))){
			px="asc";
		}
		List<DictItem> list = DictUtils.getDictList(type);
		try {
			if(list!=null){
				if(px.toLowerCase().equals("desc")){
					List<DictItem> listCopy =  ListUtils.deepCopy(list);
					Collections.sort(listCopy,new Comparator<DictItem>() {
						@Override
						public int compare(DictItem u1, DictItem u2) {
							int diff = u1.getSort() - u2.getSort();
							if (diff > 0) {
								return -1;
							}else if (diff < 0) {
								return 1;
							}
							return 0;//相等为0
						}
					});
					return listCopy;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
		
	/**
	 * 返回字典{值:标签}的对象
	 * @param type
	 * @return
	 */
	public static String ItemToKeyValue(String type){
		String json = "{";
		if (StringUtils.isNotBlank(type)){
			int i = 0;
			List<DictItem> list = getDictList(type);
			for (DictItem dict : list){
				String key = dict.getValue();
				String lable = dict.getText();
				json += "\""+key+"\":\""+lable+"\"";
				if(i!=list.size()-1){
					json+=",";
				}
				i++;
			}
		}
		
		json+="}";
		return json;
	}
	/**
	 * 返回字典{值:标签}的对象
	 * @param type
	 * @return
	 */
	public static String ItemToValueKey(String type){
		String json = "{";
		if (StringUtils.isNotBlank(type)){
			int i = 0;
			List<DictItem> list = getDictList(type);
			for (DictItem dict : list){
				String key = dict.getValue();
				String lable = dict.getText();
				json += "\""+lable+"\":\""+key+"\"";
				if(i!=list.size()-1){
					json+=",";
				}
				i++;
			}
		}
		
		json+="}";
		return json;
	}
	
//////////////////////////////////////////////////////////////////////////
	public static String ItemToKeyValue2(String typeCode){
		String json = "{";
		IDictItemService iDictItemService = (IDictItemService)ApplicationContextUtil.getContext().getBean(IDictItemService.class);
		List<DictItem> list = iDictItemService.selectDictItemByTypeCode(typeCode);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				DictItem item = (DictItem)list.get(i);
				String ZHIID = item.getValue();
				String ZHIMC = item.getText();
				json+="\""+ZHIID+"\":\""+ZHIMC+"\"";
				if(i!=list.size()-1){
					json+=",";
				}
			}
		}
		json+="}";
		return json;
	}
	
	public static String ItemToArray(String typeCode){
		String json = "[";
		IDictItemService iDictItemService = (IDictItemService)ApplicationContextUtil.getContext().getBean(IDictItemService.class);
		List<DictItem> list = iDictItemService.selectDictItemByTypeCode(typeCode);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				DictItem item = (DictItem)list.get(i);
				String ZHIID = item.getValue();
				String ZHIMC = item.getText();
				
				json += "{";
				json+="\"text\":\""+ZHIMC+"\",";
				json+="\"value\":\""+ZHIID+"\"";
				json+="}";
				
				if(i!=list.size()-1){
					json+=",";
				}
			}
		}
		json+="]";
		return json;
	}
	
	public static String TypeToKeyValue(){
		String json = "{";
		IDictTypeService iDictTypeService = (IDictTypeService)ApplicationContextUtil.getContext().getBean(IDictTypeService.class);
		List<DictType> list = iDictTypeService.selectAll();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				DictType item = (DictType)list.get(i);
				String ZHIID = item.getCode();
				String ZHIMC = item.getText();
				json+="\""+ZHIID+"\":\""+ZHIMC+"\"";
				if(i!=list.size()-1){
					json+=",";
				}
			}
		}
		json+="}";
		return json;
	}
}
