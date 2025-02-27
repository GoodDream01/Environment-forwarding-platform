package com.dt.taje.mvc.jcsj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.dao.impl.CommonDaoImpl;
import com.dt.taje.mvc.base.modle.bean.PageBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.jcsj.dao.CwglDaoI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;

@Repository
public class CwglDaoImpl extends CommonDaoImpl implements CwglDaoI {

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String CWBH = Tools.convNull((String)hashMap.get("CWBH"));
		String XM = Tools.convNull((String)hashMap.get("XM"));		
		String XB = Tools.convNull((String)hashMap.get("XB"));
		String MZ = Tools.convNull((String)hashMap.get("MZ"));
		String ZW = Tools.convNull((String)hashMap.get("ZW"));
		String LX = Tools.convNull((String)hashMap.get("LX"));
		String DBTJB = Tools.convNull((String)hashMap.get("DBTJB"));
		String SCZT = Tools.ConvNull((String)hashMap.get("SCZT"),"0");

		String sql= "";
		sql="insert into TB_CWXX (CWBH,XM,XB,MZ,ZW,LX,DBTJB,SCZT)values(";
		sql+="?,?,?,?,?,?,?,?";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, CWBH);
		ps.setString(2, XM);
		ps.setString(3, XB);
		ps.setString(4, MZ);
		ps.setString(5, ZW);
		ps.setString(6, LX);
		ps.setString(7, DBTJB);
		ps.setString(8, SCZT);

		
		
		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public void update(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String CWBH = hashMap.get("CWBH").toString();
		
		HashMap db_zzjg = getById(CWBH);
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            
            db_zzjg.put(key, val);
        }   
        del(CWBH, conn);
        insert(db_zzjg, conn);
	}

	@Override
	public HashMap getById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_CWXX t where t.CWBH='"+id+"'";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public void del(String id, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = "";
		String sqls= "";
		sql="update TB_CWXX set sczt='1' where CWBH='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}
	
	@Override
	public String getMaxCWBH(String LX) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		String ZHIID = "0";
		String FIX = "";
		if(LX.equals("1")){
			FIX = "RD";
		}else{
			FIX = "ZX";
		}
		String sql = "select replace(lpad((nvl(max(to_number(replace(CWBH,'"+FIX+"'))),0)+1),5),' ','0') as ZHIID from tb_cwxx where LX='"+LX+"' ";
		
		try {
			List indxList = bean0.executeQuery(sql);
			if(indxList!=null && indxList.size()>0){
				Map indxMap = (HashMap)indxList.get(0);
				ZHIID = indxMap.get("ZHIID").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZHIID = FIX+ZHIID;
		
		return ZHIID;
	}


}
