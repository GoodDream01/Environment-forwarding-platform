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
import com.dt.taje.mvc.jcsj.dao.ZxwyxxDaoI;
import com.dt.taje.utils.Tools;


@Repository
public  class ZxwyxxDaoImpl extends CommonDaoImpl implements ZxwyxxDaoI{

	@Override
	public void insert(HashMap hashMap, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		
		
		String ZID = Tools.convNull((String)hashMap.get("ZID"));
		String ZCODE = Tools.convNull((String)hashMap.get("ZCODE"));//编号		
		String ZNAME = Tools.convNull((String)hashMap.get("ZNAME"));//姓名
		
		String ZSEX = Tools.convNull((String)hashMap.get("ZSEX"));//性别
		String ZNATION = Tools.convNull((String)hashMap.get("ZNATION"));//民族
		String ZCPPOST = Tools.convNull((String)hashMap.get("ZCPPOST"));//省政协职务
		
		String ZBIRTHDAY = Tools.convNull((String)hashMap.get("ZBIRTHDAY"));//出生年月
		String ZPOST = Tools.convNull((String)hashMap.get("ZPOST"));//现任职务
		String ZADDRESS = Tools.convNull((String)hashMap.get("ZADDRESS"));//通讯地址
		
		String ZZIPCODE = Tools.convNull((String)hashMap.get("ZZIPCODE"));//邮编
		String ZAREA = Tools.convNull((String)hashMap.get("ZAREA"));//地区
		String ZOFFICETEL = Tools.convNull((String)hashMap.get("ZOFFICETEL"));//办公电话
		
		String ZMOBILETEL = Tools.convNull((String)hashMap.get("ZMOBILETEL"));//手机
		String ZHOUSETEL = Tools.convNull((String)hashMap.get("ZHOUSETEL"));//住宅电话
		String ZEMAIL = Tools.convNull((String)hashMap.get("ZEMAIL"));//电子信箱
		
		String ZPARTIAN = Tools.convNull((String)hashMap.get("ZPARTIAN"));//党派
		String ZSECTOR = Tools.convNull((String)hashMap.get("ZSECTOR"));//界别
		String ZEDU = Tools.convNull((String)hashMap.get("ZEDU"));//学历
		
		String ZDEGREE = Tools.convNull((String)hashMap.get("ZDEGREE"));//学位
		String ZTYPE = Tools.convNull((String)hashMap.get("ZTYPE"));//级别
		String ZNOTE = Tools.convNull((String)hashMap.get("ZNOTE"));//备注

		String sql= "";
		sql="insert into T_COMMITTEE (ZID,ZCODE,ZNAME,ZSEX,ZNATION,ZCPPOST,ZBIRTHDAY,ZPOST," +
				"ZADDRESS,ZZIPCODE,ZAREA,ZOFFICETEL,ZMOBILETEL,ZHOUSETEL,ZEMAIL,ZPARTIAN,ZSECTOR,ZEDU,ZDEGREE,ZTYPE,ZNOTE)" +
				"values(";
		sql+="?,?,?,?,?,?,?,?,?,?";
		sql+=",?,?,?,?,?,?,?,?,?,?,?";
		sql+=")";
		
		System.out.println("&&&&&&&&&&&&&"+sql);
	
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, ZID);
		ps.setString(2, ZCODE);
		ps.setString(3, ZNAME);
		ps.setString(4, ZSEX);
		ps.setString(5, ZNATION);
		ps.setString(6, ZCPPOST);
		ps.setString(7, ZBIRTHDAY);
		ps.setString(8, ZPOST);
		ps.setString(9, ZADDRESS);
		ps.setString(10, ZZIPCODE);
		
		ps.setString(11, ZAREA);
		ps.setString(12, ZOFFICETEL);
		ps.setString(13, ZMOBILETEL);
		
		ps.setString(14, ZHOUSETEL);
		ps.setString(15, ZEMAIL);
		ps.setString(16, ZPARTIAN);
		
		ps.setString(17, ZSECTOR);
		ps.setString(18, ZEDU);
		ps.setString(19, ZDEGREE);
		ps.setString(20, ZTYPE);
		ps.setString(21, ZNOTE);
		

		
		
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
        System.out.println("???????????????");
        
        insert(db_zzjg, conn);
	}

	@Override
	public HashMap getById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from T_COMMITTEE t where t.zid='"+id+"'";
		
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
		sql="delete from T_COMMITTEE where ZID='"+id+"'";

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
		String sql = "select nvl(max(zsortindex),0)+1 as zsortindex from T_UNIT ";
		
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
		sql="update T_COMMITTEE set zsortindex=nvl(zsortindex,0)-1 where ZID='"+id+"'";

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
		sql="update T_COMMITTEE set zsortindex=nvl(zsortindex,0)+1 where ZID='"+id+"'";

		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
	}

}
