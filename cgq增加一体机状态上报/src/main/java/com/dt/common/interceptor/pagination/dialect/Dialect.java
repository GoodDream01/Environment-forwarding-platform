package com.dt.common.interceptor.pagination.dialect;

import com.dt.common.interceptor.pagination.model.PageBounds;

/** 
 * @author reacher 
 * 
 */  
public interface Dialect {  
      
    /** 
     * 检查数据库是否支持分页 
     */  
    public abstract boolean supportsLimit();  
      
    /** 
     * 得到获取数据总条数的SQL语句 
     */  
    public String getCountSql(String sql);    
    /** 
     * 得到分页的SQL语句 
     */  
    public abstract String getLimitSql(String sql, PageBounds pageBounds);  
}  
