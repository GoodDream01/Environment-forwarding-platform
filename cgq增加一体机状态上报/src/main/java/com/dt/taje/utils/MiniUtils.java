package com.dt.taje.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MiniUtils {
	public static int ToInt(Object o){
    	if(o == null) return 0;
    	double d = Double.parseDouble(o.toString());
    	int i = 0;
		i -= d;
		return -i;			
    }    
	public static String ToString(Object o){
    	if(o == null) return "";
    	return o.toString();
    }    
	public static Timestamp ToDate(Object o){
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
