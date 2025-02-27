package com.dt.taje.mvc.jcsj.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.base.service.impl.CommonServiceImpl;
import com.dt.taje.mvc.jcsj.dao.CwglDaoI;
import com.dt.taje.mvc.jcsj.service.CwglServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;

@Service
public class CwglServiceImpl extends CommonServiceImpl implements CwglServiceI {

	@Autowired
	@Qualifier("cwglDaoImpl")
	CwglDaoI cwglDaoI;
	
	public void setCwglDaoI(CwglDaoI cwglDaoI) {
		this.cwglDaoI = cwglDaoI;
	}

	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";
		String XM = Tools.convNull(request.getParameter("XM"));
		String LX = Tools.convNull(request.getParameter("LX"));
		
		sql = "select * from TB_CWXX where 1=1 and sczt='0' ";
		
		if(!StringUtil.isNullOrEmpty(XM)){
			sql+=" and XM = '"+XM+"' ";
		}
		if(!StringUtil.isNullOrEmpty(LX)){
			sql+=" and LX = '"+LX+"' ";
		}

		sql += " order by cwbh ";
		
		
		
		DataGridReturn gridReturn = getDataGridReturn(sql);
		ComUtils.datagrid2(response, gridReturn);
	}

	@Override
	public void save(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String json = Tools.convNull(request.getParameter("data"));
		//System.out.println(json);
		HashMap hashMap = (HashMap)JSON.Decode(json);
		
		HashMap unitMap = null;
		boolean successFalg = false;
		String msg = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);
			
			String CZ = Tools.convNull((String)hashMap.get("CZ"));
			
			if(CZ.equals("new")){
				cwglDaoI.insert(hashMap, conn);
			}else{		
				cwglDaoI.update(hashMap, conn);
			}
			conn.commit();
			successFalg = true;
		} catch (Exception e) {
			// TODO: handle exception
			successFalg = false;
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
		String result = "{\"success\":\""+successFalg+"\",\"msg\":\""+msg+"\"}";
		ComUtils.PrintWrite(response, result);
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
		        cwglDaoI.del(id, conn);
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
	

	@Override
	public void getById(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String CWBH = Tools.convNull(request.getParameter("CWBH"));
		HashMap hashMap=cwglDaoI.getById(CWBH);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}

	@Override
	public void createCWBH(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String LX = Tools.convNull(request.getParameter("LX"));
		String CWBH = cwglDaoI.getMaxCWBH(LX);
		HashMap hashMap= new HashMap();
		hashMap.put("CWBH", CWBH);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}



}
