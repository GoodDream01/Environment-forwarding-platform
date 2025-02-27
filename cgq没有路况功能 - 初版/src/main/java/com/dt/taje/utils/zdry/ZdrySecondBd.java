package com.dt.taje.utils.zdry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.utils.ComUtils;

public class ZdrySecondBd {
	public static void SecondBd() throws Exception{
		String sql = "";

		ConnDataBase bean0 = new ConnDataBase();	
		sql = "select * from tb_zdryk where (ylzd1<>'1' or ylzd1 is null) order by drsj ";
		sql = "select * from ("+sql+") where rownum<=100 ";
		List ryList = bean0.executeQuery(sql);

		if(ryList!=null && ryList.size()>0){
			for(int i=0;i<ryList.size();i++){
				Map zdryMap = (HashMap)ryList.get(i);
				String SFZH = zdryMap.get("SFZH").toString();
				String ZDRY_ZID = zdryMap.get("ZID").toString();
				
				String hour = ComUtils.getTimeZD("10005");
				sql = "select * from tb_hcjl_ry where ZJHM='"+SFZH+"' and RHCSJ>=(sysdate-(1/24)*"+hour+" ) ";
				List hcryList = bean0.executeQuery(sql);
				if(hcryList!=null && hcryList.size()>0){

					Map map = (HashMap)hcryList.get(0);
					String ZID = map.get("ZID").toString();
					String ZPID = map.get("ZPID").toString();
					sql = "update tb_hcjl_ry set RHCJG='不正常' where ZID='"+ZID+"' ";
					bean0.executeUpdate(sql);
					
					sql = "update tb_hcjl_cl set CHCJG='不正常' where ZID='"+ZPID+"' ";
					bean0.executeUpdate(sql);
				
				}
				
				sql = "update tb_zdryk set ylzd1='1' where ZID='"+ZDRY_ZID+"' ";
				bean0.executeUpdate(sql);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
