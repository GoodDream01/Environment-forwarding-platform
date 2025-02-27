package com.dt.taje.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class ConfigureUtil {
	
	public static String getString(String string) {
        Configuration config = null;
        String str = null;
		try {
			config = new XMLConfiguration("configue.xml");
			str = config.getString(string);  
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return str;
	} 

	public static String[] getStringArray(String string) {
        Configuration config = null;
        String[] array = null;
		try {
			config = new XMLConfiguration("configue.xml");
			array = config.getStringArray(string);  
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return array;
	}  
}
