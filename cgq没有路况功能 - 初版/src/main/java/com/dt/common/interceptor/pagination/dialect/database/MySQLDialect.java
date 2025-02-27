package com.dt.common.interceptor.pagination.dialect.database;

import com.dt.common.interceptor.pagination.dialect.Dialect;
import com.dt.common.interceptor.pagination.model.PageBounds;

/** 
 * @author reacher 
 * 
 */  
public class MySQLDialect implements Dialect {  
      
    @Override  
    public boolean supportsLimit() {  
        return true;  
    }  
      
    @Override  
    public String getCountSql(String sql) {  
        return "SELECT COUNT(*) FROM (" + sql.replaceAll(";", "") + ") temp";  
    }  
  
    @Override  
    public String getLimitSql(String sql, PageBounds pageBounds) {  
    	if(!pageBounds.getIsLimit()){
    		return sql;
    	}
    	long offset = pageBounds.getPageSize() * (pageBounds.getPageNo() - 1) ;
    	long limit = pageBounds.getPageSize();
        StringBuffer stringBuffer = new StringBuffer(sql.replaceAll(";", ""));
        stringBuffer.append(" LIMIT ").append(offset).append(", ").append(limit);  
        return stringBuffer.toString();  
    }  
  
}  
