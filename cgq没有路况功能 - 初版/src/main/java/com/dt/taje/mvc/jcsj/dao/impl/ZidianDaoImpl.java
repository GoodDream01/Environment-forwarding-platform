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
import com.dt.taje.mvc.jcsj.dao.ZidianDaoI;
import com.dt.taje.utils.Tools;

@Repository
public class ZidianDaoImpl extends CommonDaoImpl implements ZidianDaoI {

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ZID = Tools.convNull((String)hashMap.get("ZID"));
		String ZHIID = Tools.convNull((String)hashMap.get("ZHIID"));		
		String ZHIMC = Tools.convNull((String)hashMap.get("ZHIMC"));
		String ZDID = Tools.convNull((String)hashMap.get("ZDID"));
		String ZPX = Tools.convNull((String)hashMap.get("ZPX"));
		String ZBZ = Tools.convNull((String)hashMap.get("ZBZ"));


		String sql= "";
		sql="insert into TB_ZIDIANZHI (ZID,ZHIID,ZHIMC,ZDID,ZPX,ZBZ)values(";
		sql+="?,?,?,?,?,?";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ZID);
		ps.setString(2, ZHIID);
		ps.setString(3, ZHIMC);
		ps.setString(4, ZDID);
		ps.setString(5, ZPX);
		ps.setString(6, ZBZ);
		
		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public void update(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String ZID = hashMap.get("ZID").toString();
		
		HashMap db_zzjg = getById(ZID);
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            db_zzjg.put(key, val);
        }   
        del(ZID, conn);
        insert(db_zzjg, conn);
	}

	@Override
	public HashMap getById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_ZIDIANZHI t where t.zid='"+id+"'";
		
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
		sql="delete from TB_ZIDIANZHI where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}
	
	@Override
	public String getMaxZHIID(String ZDID) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		String ZHIID = "0";
		String sql = "select nvl(max(to_number(ZHIID)),0)+1 as ZHIID from TB_ZIDIANZHI where ZDID='"+ZDID+"' ";
		
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
		
		return ZHIID;
	}

	@Override
	public String getMaxZPX(String ZDID) {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		String ZPX = "0";
		String sql = "select nvl(max(ZPX),0)+1 as ZPX from TB_ZIDIANZHI where ZDID='"+ZDID+"' ";
		
		try {
			List indxList = bean0.executeQuery(sql);
			if(indxList!=null && indxList.size()>0){
				Map indxMap = (HashMap)indxList.get(0);
				ZPX = indxMap.get("ZPX").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ZPX;
	}

	@Override
	public void up(String id, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = "";
		String sqls= "";
		sql="update TB_ZIDIANZHI set ZPX=nvl(ZPX,0)-1 where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public void down(String id, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = "";
		String sqls= "";
		sql="update TB_ZIDIANZHI set ZPX=nvl(ZPX,0)+1 where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}



}
