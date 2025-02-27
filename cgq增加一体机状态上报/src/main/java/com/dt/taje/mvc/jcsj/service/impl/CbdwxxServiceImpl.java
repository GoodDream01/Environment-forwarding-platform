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

import com.dt.common.utils.CacheUtils;
import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.base.service.impl.CommonServiceImpl;
import com.dt.taje.mvc.jcsj.dao.CbdwxxDaoI;
import com.dt.taje.mvc.jcsj.service.CbdwxxServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;
import com.dt.zxhygl.utils.UnitUtils;

@Service
public class CbdwxxServiceImpl extends CommonServiceImpl implements CbdwxxServiceI {

	@Autowired
	@Qualifier("cbdwxxDaoImpl")
	CbdwxxDaoI cbdwxxDaoI;
	
	public void setCbdwxxDaoI(CbdwxxDaoI cbdwxxDaoI) {
		this.cbdwxxDaoI = cbdwxxDaoI;
	}

	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";
		String ZCODE = Tools.convNull(request.getParameter("ZCODE"));
		String ZNAME = Tools.convNull(request.getParameter("ZNAME"));
		String ZLINKMAN = Tools.convNull(request.getParameter("ZLINKMAN"));
		String ZTEL = Tools.convNull(request.getParameter("ZTEL"));
		
		sql = "select * from t_unit where 1=1  ";
		
		if(!StringUtil.isNullOrEmpty(ZCODE)){
			sql+=" and ZCODE = '"+ZCODE+"' ";
		}
		if(!StringUtil.isNullOrEmpty(ZNAME)){
			sql+=" and ZNAME like '%"+ZNAME+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZLINKMAN)){
			sql+=" and ZLINKMAN like '%"+ZLINKMAN+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZTEL)){
			sql+=" and ZTEL like '%"+ZTEL+"%' ";
		}
		
		sql += " order by ztype,zsortindex ";
		
		
		
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
			
			String ZID = Tools.convNull((String)hashMap.get("ZID"));
			String ZCODE = Tools.convNull((String)hashMap.get("ZCODE"));
			String ZUNITCODE = Tools.convNull((String)hashMap.get("ZUNITCODE"));
			
			if(ZID.equals("")){
				
				unitMap = cbdwxxDaoI.getByCode(ZCODE);
				if(unitMap!=null){
					msg = "编号重复!";
					throw new Exception();
				}
				
				if(!ZUNITCODE.equals("")){
					unitMap = cbdwxxDaoI.getByUnitcode(ZUNITCODE);
					if(unitMap!=null){
						msg = "登录名重复!";
						throw new Exception();
					}
				}
				
				ZID = ComUtils.getUniqueString();
				hashMap.put("ZID", ZID);
				
				String ZSORTINDEX = cbdwxxDaoI.getMaxSortIndex();
				hashMap.put("ZSORTINDEX", ZSORTINDEX);
				
				cbdwxxDaoI.insert(hashMap, conn);
			}else{
				unitMap = cbdwxxDaoI.getByCode(ZID,ZCODE);
				if(unitMap!=null){
					msg = "编号重复!";
					throw new Exception();
				}
				if(!ZUNITCODE.equals("")){
					unitMap = cbdwxxDaoI.getByUnitcode(ZID,ZUNITCODE);
					if(unitMap!=null){
						msg = "登录名重复!";
						throw new Exception();
					}
				}
				
				cbdwxxDaoI.update(hashMap, conn);
			}
			conn.commit();
			successFalg = true;
			
			CacheUtils.remove(UnitUtils.CACHE_UNIT_LIST);
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
		        cbdwxxDaoI.del(id, conn);
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
		HashMap hashMap=cbdwxxDaoI.getById(ZID);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}

	@Override
	public void up(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);

		    String id = request.getParameter("id");       

		    cbdwxxDaoI.up(id, conn);
		    
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
	public void down(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);

		    String id = request.getParameter("id");       

		    cbdwxxDaoI.down(id, conn);
		    
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
