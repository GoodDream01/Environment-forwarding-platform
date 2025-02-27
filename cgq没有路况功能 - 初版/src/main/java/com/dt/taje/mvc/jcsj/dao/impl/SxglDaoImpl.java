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
import com.dt.taje.mvc.jcsj.dao.SxglDaoI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;

@Repository
public class SxglDaoImpl extends CommonDaoImpl implements SxglDaoI {

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ZID = Tools.convNull((String)hashMap.get("ZID"));
		String ZFLAG = Tools.convNull((String)hashMap.get("ZFLAG"));
		String ZYEAR = Tools.convNull((String)hashMap.get("ZYEAR"));		
		String ZCIRCLES = Tools.convNull((String)hashMap.get("ZCIRCLES"));
		String ZNUMBER = Tools.convNull((String)hashMap.get("ZNUMBER"));
		String ZTIME1 = Tools.convNull((String)hashMap.get("ZTIME1"));
		String ZTIME2 = Tools.convNull((String)hashMap.get("ZTIME2"));
		String ZBZ = Tools.convNull((String)hashMap.get("ZBZ"));
		String ZTJSJ = Tools.convNull((String)hashMap.get("ZTJSJ"));
		
		if(ZTIME1.length()>10){
			ZTIME1 = ZTIME1.substring(0,10);
		}
		
		if(ZTIME2.length()>10){
			ZTIME2 = ZTIME2.substring(0,10);
		}

		String sql= "";
		sql="insert into T_SXGL (ZID,ZYEAR,ZCIRCLES,ZNUMBER,ZTIME1,ZTIME2,ZBZ,ZTJSJ,ZFLAG)values(";
		sql+="?,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'),?";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ZID);
		ps.setString(2, ZYEAR);
		ps.setString(3, ZCIRCLES);
		ps.setString(4, ZNUMBER);
		ps.setString(5, ZTIME1);
		ps.setString(6, ZTIME2);
		ps.setString(7, ZBZ);
		ps.setString(8, ZTJSJ);
		ps.setString(9, ZFLAG);

		
		
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
		String sql = "select * from T_SXGL t where t.zid='"+id+"'";
		
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
		sql="delete from T_SXGL where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public HashMap getByXx(String zYear,String zCircles,String zNumber) {
		// TODO Auto-generated method stub
		String sql = "select * from T_SXGL t where zyear='"+zYear+"' and zcircles='"+zCircles+"' and znumber='"+zNumber+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public HashMap getByXx(String id, String zYear,String zCircles,String zNumber) {
		// TODO Auto-generated method stub
		String sql = "select * from T_SXGL t where t.zid<>'"+id+"' and zyear='"+zYear+"' and zcircles='"+zCircles+"' and znumber='"+zNumber+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}



}
