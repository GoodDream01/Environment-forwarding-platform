package com.dt.taje.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dt.taje.db.ConnDataBase;


public class KhUtils_20180901 {
	
	public static int getZbAjNum(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select zfid from t_proposalzbdw where zdwbh='"+dwbh+"') and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static int getHbAjNum(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select zfid from t_proposalhbdw where zdwbh='"+dwbh+"') and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static int getZbFkANum(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select zfid from t_proposalzbdw where zdwbh='"+dwbh+"' and zid in (select zfid from t_proposaltrack where zfeedbackresult in ('A','B'))) and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static int getZbFkANum_A(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select zfid from t_proposalzbdw where zdwbh='"+dwbh+"' and zid in (select zfid from t_proposaltrack where zfeedbackresult in ('A'))) and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static int getZbFkANum_B(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select zfid from t_proposalzbdw where zdwbh='"+dwbh+"' and zid in (select zfid from t_proposaltrack where zfeedbackresult in ('B'))) and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static int getTotalAjNum(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where (zid in (select zfid from t_proposalzbdw where zdwbh='"+dwbh+"') or zid in (select zfid from t_proposalhbdw where zdwbh='"+dwbh+"')) and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//重点报送时间
	public static String getZdbsSj(String dwbh,String year) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select zsubmitdate from t_focus where zuid='"+dwbh+"' and zfyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("zsubmitdate").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//工作总结报送时间
	public static String getGzzjbsSj(String dwbh,String year) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select ZCOMPLETETIME from t_complete where ZCODEID='"+dwbh+"' and ZYEARID='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZCOMPLETETIME").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//责任分解表报送时间
	public static String getZrfjbsSj(String dwbh,String year) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select zsubmitdate from t_task where zfid='"+dwbh+"' and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("zsubmitdate").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//取得走访比例
	public static double getZfblPer(String dwbh,String year,int totalNum) {
		// TODO Auto-generated method stub
		double result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select ZSCALE from T_VISITSCALE where 1=1 and zyear='"+year+"' and ZMINLIMIT<="+totalNum+" and ZMAXLIMIT>="+totalNum+" ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("ZSCALE").toString();
				result = Double.parseDouble(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//取得走访案件数
	public static int getZfblNum(double zfbl,int totalNum) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			double a = Arith.mul(zfbl/100, totalNum);
			result = (int) Math.ceil(a);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	//取得实际走访案件数
	public static int getZfajNum(String dwbh,String year) {
		// TODO Auto-generated method stub
		int result=0;
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select count(1) as num from t_proposal where zid in (select ZPID from T_PROPOSALTRACK where zdwbh='"+dwbh+"' and ZVISITRESULT in ('A','B','C')) and zyear='"+year+"' ";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String NUM = map.get("NUM").toString();
				result = Integer.parseInt(NUM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
