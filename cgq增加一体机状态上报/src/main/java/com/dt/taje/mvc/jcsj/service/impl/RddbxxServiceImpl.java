package com.dt.taje.mvc.jcsj.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.base.service.impl.CommonServiceImpl;
import com.dt.taje.mvc.jcsj.dao.CbdwxxDaoI;
import com.dt.taje.mvc.jcsj.dao.RddbxxDaoI;
import com.dt.taje.mvc.jcsj.service.RddbxxServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;

@Service
public class RddbxxServiceImpl extends CommonServiceImpl implements RddbxxServiceI{
	
	
	@Autowired
	@Qualifier("rddbxxDaoImpl")
	RddbxxDaoI rddbxxDaoI;
	
	public void setRddbxxDaoI(RddbxxDaoI rddbxxDaoI) {
		this.rddbxxDaoI = rddbxxDaoI;
	}
	
	
	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";
		String ZCODE = Tools.convNull(request.getParameter("ZCODE"));//编码
		String ZNAME = Tools.convNull(request.getParameter("ZNAME"));//姓名
		String ZSEX = Tools.convNull(request.getParameter("ZSEX"));//性别
		String ZPOST = Tools.convNull(request.getParameter("ZPOST"));//现任职务
		String ZAREA = Tools.convNull(request.getParameter("ZAREA"));//地区
		String ZTYPE = Tools.convNull(request.getParameter("ZTYPE"));//级别
		
		sql = "select * from t_deputies where 1=1  ";
		if(!StringUtil.isNullOrEmpty(ZCODE)){
			sql+=" and ZCODE like '%"+ZCODE+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZNAME)){
			sql+=" and ZNAME like '%"+ZNAME+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZSEX)){
			sql+=" and ZSEX like '%"+ZSEX+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZPOST)){
			sql+=" and ZPOST like '%"+ZPOST+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZAREA)){
			sql+=" and ZAREA like '%"+ZAREA+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZTYPE)){
			sql+=" and ZTYPE like '%"+ZTYPE+"%' ";
		}
		
		sql += " order by ZCODE ";
		
		DataGridReturn gridReturn = getDataGridReturn(sql);
		ComUtils.datagrid2(response, gridReturn);
	}

	@Override
	public void save(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String json = Tools.convNull(request.getParameter("data"));
		//System.out.println(json);
		HashMap hashMap = (HashMap)JSON.Decode(json);
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);
			
			String ZID = Tools.convNull((String)hashMap.get("ZID"));
			
			if(ZID.equals("")){
				ZID = ComUtils.getUniqueString();
				hashMap.put("ZID", ZID);
				
				
				rddbxxDaoI.insert(hashMap, conn);
			}else{
				rddbxxDaoI.update(hashMap, conn);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	

	@Override
	public void getById(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ZID = Tools.convNull(request.getParameter("ZID"));
		HashMap hashMap=rddbxxDaoI.getById(ZID);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}


	@Override
	public void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);

		    String idStr = request.getParameter("id");       
		    if (StringUtil.isNullOrEmpty(idStr)) return;
		    String[] ids = idStr.split(",");
		    for (int i = 0, l = ids.length; i < l; i++)
		    {
		        String id = ids[i]; 
		        rddbxxDaoI.del(id, conn);
		    } 
		    
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	

}
