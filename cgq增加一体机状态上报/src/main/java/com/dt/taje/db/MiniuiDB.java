package com.dt.taje.db;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MiniuiDB {
    /////////////////////////////////////////////////////////////////
	private Connection getConn() throws Exception{		
		Connection conn = null;
		conn=new ConnDataBase().getConn();
		return conn;
	}	    
	public ArrayList DBSelect(String sql) throws Exception{
    	Connection conn = getConn();		
		Statement stmt = conn.createStatement();
    	
        ResultSet rst = stmt.executeQuery(sql);		
		ArrayList list = ResultSetToList(rst);
		
		rst.close();
		stmt.close();
		conn.close();
		
        return list;
	}
	
    private static ArrayList ResultSetToList(ResultSet   rs) throws Exception{    	
    	ResultSetMetaData md = rs.getMetaData();
    	int columnCount = md.getColumnCount();
    	ArrayList list = new ArrayList();
    	Map rowData;
    	while(rs.next()){
	    	rowData = new HashMap(columnCount);
	    	for(int i = 1; i <= columnCount; i++)   {	 	    		
	    		Object v = rs.getObject(i);	    		
	    		
	    		if(v != null && (v.getClass() == Date.class || v.getClass() == java.sql.Date.class)){
	    			Timestamp ts= rs.getTimestamp(i);
	    			v = new java.util.Date(ts.getTime());
	    			//v = ts;
	    		}else if(v != null && v.getClass() == Clob.class){
	    			v = clob2String((Clob)v);
	    		}
	    		rowData.put(md.getColumnName(i),   v);
	    	}
	    	list.add(rowData);	    	
    	}
    	return list;
	} 	
    private static String clob2String(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    }  		    
    private int ToInt(Object o){
    	if(o == null) return 0;
    	double d = Double.parseDouble(o.toString());
    	int i = 0;
		i -= d;
		return -i;			
    }    
    private String ToString(Object o){
    	if(o == null) return "";
    	return o.toString();
    }    
    private Timestamp ToDate(Object o){
    	try{
    		if(o.getClass() == String.class){


    			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			o = format.parse(o.toString());  
    			return new java.sql.Timestamp(((Date)o).getTime());
    		}
    		return o != null ? new java.sql.Timestamp(((Date)o).getTime()) : null;
		}catch(Exception ex){
			return null;
		}
    }
}
