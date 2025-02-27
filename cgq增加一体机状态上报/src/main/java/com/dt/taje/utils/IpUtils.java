package com.dt.taje.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IpUtils {
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (isBlank(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isBlank(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isBlank(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (isBlank(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (isBlank(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip) && StringUtils.indexOf(ip, ",") > 0) {
			String[] ipArray = StringUtils.split(ip, ",");
			ip = ipArray[0];
		}
		if(ip.equals("0:0:0:0:0:0:0:1")){
			ip = "127.0.0.1";
		}
		return ip;
	}

	private static boolean isBlank(String ip) {
		if (StringUtils.isBlank(ip) || "unkown".equalsIgnoreCase(ip) || ip.split("\\.").length != 4) {
			return true;
		}
		return false;
	}
	
	
}
