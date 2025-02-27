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
import com.dt.taje.mvc.jcsj.dao.SxglDaoI;
import com.dt.taje.mvc.jcsj.service.SxglServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;

@Service
public class SxglServiceImpl extends CommonServiceImpl implements SxglServiceI {

	@Autowired
	@Qualifier("sxglDaoImpl")
	SxglDaoI sxglDaoI;
	
	public void setSxglDaoI(SxglDaoI sxglDaoI) {
		this.sxglDaoI = sxglDaoI;
	}

	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";
		String ZYEAR = Tools.convNull(request.getParameter("ZYEAR"));
		
		String ZFLAG = Tools.convNull(request.getParameter("ZFLAG"));
		String ZCIRCLES = Tools.convNull(request.getParameter("ZCIRCLES"));
		String ZNUMBER = Tools.convNull(request.getParameter("ZNUMBER"));
		String ZBZ = Tools.convNull(request.getParameter("ZBZ"));
		
		sql = "select * from T_SXGL where 1=1  ";
		
		if(!StringUtil.isNullOrEmpty(ZYEAR)){
			sql+=" and ZYEAR = '"+ZYEAR+"' ";
		}
		if(!StringUtil.isNullOrEmpty(ZCIRCLES)){
			sql+=" and ZCIRCLES = '"+ZCIRCLES+"' ";
		}
		if(!StringUtil.isNullOrEmpty(ZNUMBER)){
			sql+=" and ZNUMBER = '"+ZNUMBER+"' ";
		}
		if(!StringUtil.isNullOrEmpty(ZBZ)){
			sql+=" and ZBZ like '%"+ZBZ+"%' ";
		}
		if(!StringUtil.isNullOrEmpty(ZFLAG)){
			sql+=" and ZFLAG = '"+ZFLAG+"' ";
			
		}
		
		sql += " order by ZYEAR desc,ZCIRCLES,ZNUMBER ";
		
		
		
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
			String ZYEAR = Tools.convNull((String)hashMap.get("ZYEAR"));
			String ZCIRCLES = Tools.convNull((String)hashMap.get("ZCIRCLES"));
			String ZNUMBER = Tools.convNull((String)hashMap.get("ZNUMBER"));
			
			if(ZID.equals("")){
				
				unitMap = sxglDaoI.getByXx(ZYEAR,ZCIRCLES,ZNUMBER);
				if(unitMap!=null){
					msg = "您已添加过该时限信息!";
					throw new Exception();
				}
				
				ZID = ComUtils.getUniqueString();
				hashMap.put("ZID", ZID);
				hashMap.put("ZTJSJ", ComUtils.getTime(0));
				
				sxglDaoI.insert(hashMap, conn);
			}else{
				unitMap = sxglDaoI.getByXx(ZID,ZYEAR,ZCIRCLES,ZNUMBER);
				if(unitMap!=null){
					msg = "您已添加过该时限信息!";
					throw new Exception();
				}
				
				sxglDaoI.update(hashMap, conn);
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
		        sxglDaoI.del(id, conn);
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
		HashMap hashMap=sxglDaoI.getById(ZID);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}


}
