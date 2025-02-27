package com.dt.common.utils.ui;

import flexjson.*;
import flexjson.transformer.*;
import flexjson.ObjectBinder;
import flexjson.JSONException;
import flexjson.ObjectFactory;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.lang.reflect.Type;

public class DateTransformer extends AbstractTransformer implements ObjectFactory {

    SimpleDateFormat simpleDateFormatter;

    public DateTransformer(String dateFormat) {
        simpleDateFormatter = new SimpleDateFormat(dateFormat);
    }
   
    public void transform(Object value) {
        getContext().writeQuoted(simpleDateFormatter.format(value));
    }

    public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
        try {        	
            //return simpleDateFormatter.parse( value.toString() );   
        	Date d = simpleDateFormatter.parse( value.toString() );
        	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	return fmt.format(d);
        } catch (Exception e) {
            //throw new JSONException(String.format( "Failed to parse %s with %s pattern.", value, simpleDateFormatter.toPattern() ), e );
        	return value;
        }
    }
}
