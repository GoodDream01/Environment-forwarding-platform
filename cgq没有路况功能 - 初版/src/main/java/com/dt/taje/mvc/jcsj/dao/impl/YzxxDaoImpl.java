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
import com.dt.taje.mvc.jcsj.dao.YzxxDaoI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;

@Repository
public class YzxxDaoImpl extends CommonDaoImpl implements YzxxDaoI {

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ZID = Tools.convNull((String)hashMap.get("ZID"));
		String ZCODE = Tools.convNull((String)hashMap.get("ZCODE"));		
		String ZNAME = Tools.convNull((String)hashMap.get("ZNAME"));
		String ZTEL = Tools.convNull((String)hashMap.get("ZTEL"));
		String ZADDRESS = Tools.convNull((String)hashMap.get("ZADDRESS"));
		String ZLINKMAN = Tools.convNull((String)hashMap.get("ZLINKMAN"));
		String ZPASSWORD = Tools.convNull((String)hashMap.get("ZPASSWORD"));
		String ZTYPE = Tools.convNull((String)hashMap.get("ZTYPE"));
		String ZUNITCODE = Tools.convNull((String)hashMap.get("ZUNITCODE"));
		String ZSORTINDEX = Tools.convNull((String)hashMap.get("ZSORTINDEX"));

		String sql= "";
		sql="insert into TB_YZXX (ZID,ZCODE,ZNAME,ZTEL,ZADDRESS,ZLINKMAN,ZPASSWORD,ZTYPE,ZUNITCODE,ZSORTINDEX)values(";
		sql+="?,?,?,?,?,?,?,?,?,?";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ZID);
		ps.setString(2, ZCODE);
		ps.setString(3, ZNAME);
		ps.setString(4, ZTEL);
		ps.setString(5, ZADDRESS);
		ps.setString(6, ZLINKMAN);
		ps.setString(7, ZPASSWORD);
		ps.setString(8, ZTYPE);
		ps.setString(9, ZUNITCODE);
		ps.setString(10, ZSORTINDEX);

		
		
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
		String sql = "select * from TB_YZXX t where t.zid='"+id+"'";
		
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
		sql="delete from TB_YZXX where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public String getMaxSortIndex() {
		// TODO Auto-generated method stub
		ConnDataBase bean0 = new ConnDataBase();
		String ZSORTINDEX = "0";
		String sql = "select nvl(max(zsortindex),0)+1 as zsortindex from TB_YZXX ";
		
		try {
			List indxList = bean0.executeQuery(sql);
			if(indxList!=null && indxList.size()>0){
				Map indxMap = (HashMap)indxList.get(0);
				ZSORTINDEX = indxMap.get("ZSORTINDEX").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ZSORTINDEX;
	}

	@Override
	public void up(String id, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = "";
		String sqls= "";
		sql="update TB_YZXX set zsortindex=nvl(zsortindex,0)-1 where ZID='"+id+"'";

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
		sql="update TB_YZXX set zsortindex=nvl(zsortindex,0)+1 where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

	@Override
	public HashMap getByUnitcode(String zUnitcode) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_YZXX t where zunitcode='"+zUnitcode+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public HashMap getByUnitcode(String id, String zUnitcode) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_YZXX t where t.zid<>'"+id+"' and zunitcode='"+zUnitcode+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public HashMap getByCode(String zCode) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_YZXX t where zcode='"+zCode+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public HashMap getByCode(String id, String zCode) {
		// TODO Auto-generated method stub
		String sql = "select * from TB_YZXX t where t.zid<>'"+id+"' and zcode='"+zCode+"' ";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}



}
