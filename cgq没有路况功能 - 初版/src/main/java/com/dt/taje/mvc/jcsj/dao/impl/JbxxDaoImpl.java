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
import com.dt.taje.mvc.jcsj.dao.JbxxDaoI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;

@Repository
public class JbxxDaoImpl extends CommonDaoImpl implements JbxxDaoI {

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ZID = Tools.convNull((String)hashMap.get("ZID"));
		String ZNAME = Tools.convNull((String)hashMap.get("ZNAME"));
		String ZNOTE = Tools.convNull((String)hashMap.get("ZNOTE"));

		String sql= "";
		sql="insert into T_CIRCLES (ZID,ZNAME,ZNOTE)values(";
		sql+="?,?,?";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ZID);
		ps.setString(2, ZNAME);
		ps.setString(3, ZNOTE);

		
		
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
		String sql = "select * from T_CIRCLES t where t.zid='"+id+"'";
		
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
		sql="delete from T_CIRCLES where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	
	



}
