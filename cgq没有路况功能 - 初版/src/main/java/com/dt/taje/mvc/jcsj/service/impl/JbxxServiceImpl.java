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
import com.dt.taje.mvc.jcsj.dao.JbxxDaoI;
import com.dt.taje.mvc.jcsj.service.JbxxServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;

@Service
public class JbxxServiceImpl extends CommonServiceImpl implements JbxxServiceI {

	@Autowired
	@Qualifier("jbxxDaoImpl")
	JbxxDaoI jbxxDaoI;
	public void setCbdwxxDaoI(JbxxDaoI jbxxDaoI) {
		this.jbxxDaoI = jbxxDaoI;
	}


	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";
		String ZNAME = Tools.convNull(request.getParameter("ZNAME"));
		String ZNOTE = Tools.convNull(request.getParameter("ZNOTE"));
		
		sql = "select * from T_CIRCLES where 1=1  ";
		if(!StringUtil.isNullOrEmpty(ZNAME)){
			sql+=" and ZNAME like '%"+ZNAME+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZNOTE)){
			sql+=" and ZNOTE like '%"+ZNOTE+"%' ";
		}
		
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
				
				
				jbxxDaoI.insert(hashMap, conn);
			}else{
				jbxxDaoI.update(hashMap, conn);
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
	public void update(HttpServletRequest request, HttpServletResponse response) {
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

			jbxxDaoI.update(hashMap, conn);
		    
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
		        jbxxDaoI.del(id, conn);
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
		String ZID = Tools.convNull(request.getParameter("ZID"));
		HashMap hashMap=jbxxDaoI.getById(ZID);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}

}
