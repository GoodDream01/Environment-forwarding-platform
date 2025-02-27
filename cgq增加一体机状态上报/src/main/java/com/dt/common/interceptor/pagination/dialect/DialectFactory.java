package com.dt.common.interceptor.pagination.dialect;

import java.lang.reflect.Constructor;  
import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * @author reacher 
 * 
 */  
public final class DialectFactory {  
      
    private static Map<String, Dialect> DIALECTS = new HashMap<String, Dialect>();  
      
    public static Dialect getDialect(Class<?> clazz) throws Exception {  
        Dialect dialect = DIALECTS.get(clazz.getSimpleName());  
        if (dialect == null) {  
            dialect = newInstance(clazz);  
            if(null != dialect) {  
                DIALECTS.put(clazz.getSimpleName(), dialect);  
            }  
        }  
        return dialect;  
    }  
      
    private static Dialect newInstance(Class<?> clazz) throws Exception {  
        if(null == clazz) {  
            return null;  
        }  
        Constructor<?> constructor = clazz.getConstructor();  
        constructor.setAccessible(true);  
        Object object = constructor.newInstance();  
        Dialect dialect = null;  
        if(object instanceof Dialect) {  
            dialect = (Dialect)object;  
        }  
        return dialect;  
          
    }  
}  