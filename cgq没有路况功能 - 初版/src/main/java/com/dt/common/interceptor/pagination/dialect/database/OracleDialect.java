package com.dt.common.interceptor.pagination.dialect.database;

import com.dt.common.interceptor.pagination.dialect.Dialect;
import com.dt.common.interceptor.pagination.model.PageBounds;

public class OracleDialect implements Dialect {

	@Override
	public boolean supportsLimit() {
		// TODO Auto-generated method stub
		return true; 
	}

	@Override
	public String getCountSql(String sql) {
		// TODO Auto-generated method stub
		return "SELECT COUNT(*) FROM (" + sql.replaceAll(";", "") + ") temp"; 
	}

	@Override
	public String getLimitSql(String sql, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		sql = sql.replaceAll(";", "");  
    	if(!pageBounds.getIsLimit()){
    		return sql;
    	}
        StringBuilder pageSql = new StringBuilder(100);  
        String beginrow = String.valueOf((pageBounds.getPageNo() - 1) * pageBounds.getPageSize());    
        String endrow = String.valueOf(pageBounds.getPageNo() * pageBounds.getPageSize());    
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");    
        pageSql.append(sql);    
        pageSql.append(" ) temp where rownum <= ").append(endrow);  
        pageSql.append(") where row_id > ").append(beginrow);  
        return pageSql.toString();  
	}

}
