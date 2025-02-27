package com.dt.taje.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

public class XmlUtils {
	
	public static String readXMLString(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		try {
			//BufferedReader reader = request.getReader();
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),Charset.forName("UTF-8")));
			String line = null;
			while((line=reader.readLine())!=null){
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
