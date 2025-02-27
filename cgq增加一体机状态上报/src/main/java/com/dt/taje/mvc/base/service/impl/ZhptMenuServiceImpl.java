package com.dt.taje.mvc.base.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.dao.ZhptMenuDaoI;
import com.dt.taje.mvc.base.modle.bean.UserBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;
import com.dt.taje.mvc.base.service.ZhptMenuServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.MiniUtils;
import com.dt.taje.utils.SysConfig;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ui.JSON;
import com.dt.taje.utils.ui.StringUtil;


@Service
public class ZhptMenuServiceImpl extends CommonServiceImpl implements ZhptMenuServiceI {

	@Autowired
	@Qualifier("zhptMenuDaoImpl")
	ZhptMenuDaoI menuDaoI;

	public void setMenuDaoI(ZhptMenuDaoI menuDaoI) {
		this.menuDaoI = menuDaoI;
	}

	@Override
	public void getMenuList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sql = "";

		sql = "select * from TB_MENU where 1=1 order by cjsj ";
		
		DataGridReturn gridReturn = getDataGridReturn(sql);
		ComUtils.datagrid2(response, gridReturn);
	}

	@Override
	public void getMenuTree(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		List tree = menuDaoI.getMenuTree();

		String treeJson = JSON.Encode(tree);
		System.out.println(treeJson);
		ComUtils.PrintWrite(response, treeJson);
	}

	@Override
	public void saveMenu(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String json = Tools.convNull(request.getParameter("data"));
		System.out.println(json);
		HashMap hashMap = (HashMap)JSON.Decode(json);
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			ConnDataBase bean0 = new ConnDataBase();
			conn = bean0.getConn();
			conn.setAutoCommit(false);
	
			String ROLEID = MiniUtils.ToString(hashMap.get("ROLEID"));
			if(ROLEID.equals("")){
				ROLEID = ComUtils.getUniqueString();
				hashMap.put("ROLEID", ROLEID);
				menuDaoI.insertMenu(hashMap, conn);
			}else{
				menuDaoI.updateMenu(hashMap, conn);
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
	public void delMenu(HttpServletRequest request, HttpServletResponse response) {
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
		        menuDaoI.delMenu(id, conn);
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
	public void getMenuByID(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ZID = Tools.convNull(request.getParameter("ZID"));
		HashMap hashMap=menuDaoI.getMenuById(ZID);
		String json = JSON.Encode(hashMap);
		ComUtils.PrintWrite(response, json);
	}

	@Override
	public void getUserMenuTree(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute(SysConfig.SESSION_USER_INFO);
		
		//String USERID=userBean.getZUID();
		String USERID=userBean.getZROLE();
		List tree = menuDaoI.getUserMenuTree(USERID);

		String treeJson = JSON.Encode(tree);
		System.out.println(treeJson);
		ComUtils.PrintWrite(response, treeJson);
	}


}
