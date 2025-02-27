package com.dt.taje.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class KstgJsonUtil {
	public static String getGridJsonByKstgList(String jsonStr){
		int total = 0;
		String arrays = "[";
		if(jsonStr!=null && !jsonStr.equals("")){
			JSONObject t= JSONObject.fromObject(jsonStr);
			JSONArray array = t.getJSONObject("KSTG_S").getJSONObject("kstgs").getJSONArray("entry");
			if(array!=null && array.size()>0){
				total = array.size();
				for(int i=0;i<array.size();i++){
	
					JSONObject tempmx = (JSONObject)array.get(i);
					String value = tempmx.getString("value");
					arrays += value;
					if(i!=array.size()-1){
						arrays += ",";
					}
				}
			}
		}
		arrays += "]";
		
		String result = "{\"total\":\""+total+"\",\"data\":"+arrays+"}";
		System.out.println(result);
		return result;
		
	}
}
