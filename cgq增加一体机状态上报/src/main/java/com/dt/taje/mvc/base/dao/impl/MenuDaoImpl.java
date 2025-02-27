package com.dt.taje.mvc.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dt.taje.mvc.base.dao.MenuDaoI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;

@Repository
public class MenuDaoImpl extends CommonDaoImpl implements MenuDaoI {

	@Override
	public void insertMenu(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ROLEID = Tools.convNull((String)hashMap.get("ROLEID"));
		String ROLECODE = Tools.convNull((String)hashMap.get("ROLECODE"));		
		String ROLENAME = Tools.convNull((String)hashMap.get("ROLENAME"));
		String CJSJ = Tools.ConvNull((String)hashMap.get("CJSJ"),ComUtils.getTime(0));

		
		String sql = "";
		String sqls= "";
		sql="insert into TA_MENU (ROLEID,ROLECODE,ROLENAME,CJSJ)values(";
		sql+="?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS')";
		sql+=")";
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ROLEID);
		ps.setString(2, ROLECODE);
		ps.setString(3, ROLENAME);
		ps.setString(4, CJSJ);

		
		
		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}


	@Override
	public void updateMenu(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String ZID = hashMap.get("ZID").toString();
		
		HashMap db_zzjg = getMenuById(ZID);
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            
            db_zzjg.put(key, val);
        }   
        delMenu(ZID, conn);
        insertMenu(db_zzjg, conn);
	}

	@Override
	public HashMap getMenuById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_menu t where t.zid='"+id+"'";
		
		List list = executeJDBCQuery(sql);
		if(list!=null && list.size()>0){
			
			return (HashMap)list.get(0);
		}
		return null;
	}

	@Override
	public void delMenu(String id, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

		String sql = "";
		String sqls= "";
		sql="delete from TA_MENU where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}
	
	@Override
	public List getMenuTree() {
		// TODO Auto-generated method stub
		String sql = "select * from TA_MENU where 1=1 order by MLEVE,MORDER ";
		List list = executeJDBCQuery(sql);
		
		List tree = new ArrayList();
		
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				String ZID = map.get("ZID").toString();
				String MNAME = map.get("MNAME").toString();
				String PID = map.get("PID").toString();
				
				HashMap hashMap = new HashMap();
				hashMap.put("id", ZID );
				hashMap.put("text", MNAME);
				hashMap.put("pid", PID);
				
				tree.add(hashMap);
			}
		}
		return tree;
	}


	@Override
	public List getUserMenuTree(String uSERID) {
		// TODO Auto-generated method stub
		//String sql = "select * from TA_MENU where 1=1 and ZID in (select menuid from tb_role_menu where roleid in (select roleid from tb_role_user where userid='"+uSERID+"')) order by MLEVE,MORDER ";
		String sql = "select * from TA_MENU where 1=1  and roleid like '%"+uSERID+"%' and isshow='1'  order by MLEVE,MORDER ";
		List list = executeJDBCQuery(sql);
		
		List tree = new ArrayList();
		
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				String ZID = map.get("ZID").toString();
				String MNAME = map.get("MNAME").toString();
				String PID = map.get("PID").toString();
				String MURL = map.get("MURL").toString();
				
				HashMap hashMap = new HashMap();
				hashMap.put("id", ZID );
				hashMap.put("text", MNAME);
				hashMap.put("pid", PID);
				hashMap.put("url", MURL);
				
				tree.add(hashMap);
			}
		}
		return tree;
	}

}
