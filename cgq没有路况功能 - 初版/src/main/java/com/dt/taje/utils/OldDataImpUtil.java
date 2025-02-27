package com.dt.taje.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.db.ConnDataBase_AMBOW;
import com.dt.taje.db.ConnDataBase_MSSQL_HBTA;

public class OldDataImpUtil {
	public static String getZiDianZHI(String ZDID){
		String result = "";
		ConnDataBase_AMBOW bean0 = new ConnDataBase_AMBOW();
		try {
			String sql = "select * from ti_commondictinfo where listid='"+ZDID+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("MINGCHENG").toString();
				String classify = map.get("classify").toString();
				
				if(result.equals("满意")){
					result = "A";
				}else if(result.equals("基本满意")){
					result = "B";
				}else if(result.equals("不满意")){
					result = "C";
				}else if(result.equals("未明确不满意但提出异议")){
					result = "D";
				}
				
				if(classify.equals("jiebie")){
					result = "第"+result;
				}
				
				if(classify.equals("cibie")){
					result = "第"+result;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String getDwbh(String DWMC){
		String result = "";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from t_unit where zname='"+DWMC+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("zcode").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String getDwbhs(String DWMCs){
		String result = "";
		String[] dws = DWMCs.split("；");
		if(dws!=null && dws.length>0){
			for(int i=0;i<dws.length;i++){
				String dwmc = dws[i];
				String dwbh=getDwbh(dwmc);
				result+=dwbh;
				if(i!=dws.length-1){
					result+=",";
				}
			}
		}
		return result;
	}
	
	public static String getMSSQLDwmc(String DWBH){ 
		String result = "";
		ConnDataBase_MSSQL_HBTA bean0 = new ConnDataBase_MSSQL_HBTA();
		try {
			String sql = "select * from TUnit where Code='"+DWBH+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("Name".toLowerCase()).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	} 
}
