package com.dt.taje.utils;

import java.util.*;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.utils.ui.StringUtil;

public class ZiDianUtils {
	/**
	 * 公共数据类型
	 */
	public final static String PUB_SEX="10002";
	
	/**
	 * 级别信息
	 */
	public final static String PUB_JBXX="10003";
	
	/**
	 * 次别信息
	 */
	public final static String PUB_CBXX="10004";
	
	/**
	 * 年度信息
	 */
	public final static String PUB_NDXX="10005";
	
	/**
	 * 届别信息
	 */
	public final static String PUB_JIEBIEXX="10006";
	
	/**
	 * 类别信息
	 */
	public final static String PUB_LBXX="10007";
	
	/**
	 * 选区信息
	 */
	public final static String PUB_XQXX="10008";
	
	/**
	 * 界别信息
	 */
	public final static String PUB_JIEBIE="10009";
	
	/**
	 * 办理结果
	 */
	public final static String PUB_BLJG="10010";
	
	/**
	 * 复查结果
	 */
	public final static String PUB_FCJG="10011";
	
	/**
	 * 走访结果
	 */
	public final static String PUB_ZFJG="10012";
	
	/**
	 * 反馈结果
	 */
	public final static String PUB_FKJG="10013";
	
	/**
	 * 承办单位隶属类型
	 */
	public final static String JCSJ_CBDW_LSLX="10001";

	/**
	 * 用户管理
	 */
	public final static String PUB_XTGL_YHGL="10014";
	
	/**
	 * 是否会上
	 */
	public final static String PUB_XXWH_SFHS="10015";
	
	/**
	 * 是否重点
	 */
	public final static String PUB_XXWH_SFZD="10016";
	
	/**
	 * 政协  人大 来源信息
	 */
	public final static String PUB_LY="10017";
	
	/**
	 * 案件类型
	 */
	public final static String PUB_AJLX="10018";
	/**
	 * 是否参阅
	 */
	public final static String PUB_XXWH_CYBS="10019";
	

	/**
	 * 完成情况
	 */
	public final static String PUB_WCQK="10020";
	

	
	public static List getZiDianZHI(String ZDID){
		List list = null;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			list = bean0.executeQuery("select * from tb_zidianzhi where zdid='"+ZDID+"' order by zpx ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List getZiDianZHI_PX(String ZDID,String px){
		List list = null;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			if(StringUtil.isNullOrEmpty(px) || (!px.toLowerCase().equals("asc") && !px.toLowerCase().equals("desc"))){
				px="asc";
			}
			list = bean0.executeQuery("select * from tb_zidianzhi where zdid='"+ZDID+"' order by zpx "+px+" ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getZIDIAN_MC(String ZDID){
		String ZDMC = "";
		List list = null;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			list = bean0.executeQuery("select * from TB_ZIDIAN where ZDID='"+ZDID+"' ");
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				ZDMC = map.get("ZDMC").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ZDMC;
	}
	
	public static String getZiDianZHIToJson(String ZDID){
		List list = getZiDianZHI(ZDID);
		String json = ComUtils.ListToJson(list);
		return json;
	}
	
	public static String getZiDianZHIToJson(String ZDID,String px){
		List list = getZiDianZHI_PX(ZDID,px);
		String json = ComUtils.ListToJson(list);
		return json;
	} 
	
	public static String getZiDianZHI(String ZDID,String ZHID){
		String result="";
		List list = null;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			list = bean0.executeQuery("select * from tb_zidianzhi where ZDID='"+ZDID+"' and ZHIID='"+ZHID+"' order by ZPX ");
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZHIMC").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getZiDianZHIToKeyValue(String ZDID){
		List list = getZiDianZHI(ZDID);
		String json = "{";
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				String ZHIID = map.get("ZHIID").toString();
				String ZHIMC = map.get("ZHIMC").toString();
				json+="\""+ZHIID+"\":\""+ZHIMC+"\"";
				if(i!=list.size()-1){
					json+=",";
				}
			}
			
		}
		json+="}";
		return json;
	}
	
	
	
	
	public static String getZiDianZHIToArray(String ZDID){
		List list = getZiDianZHI(ZDID);
		String json = "[";
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				String ZHIID = map.get("ZHIID").toString();
				String ZHIMC = map.get("ZHIMC").toString();
				json+="{\""+ZHIID+"\":\""+ZHIMC+"\"}";
				if(i!=list.size()-1){
					json+=",";
				}
			}
			
		}
		json+="]";
		return json;
	}

}
