package com.dt.zxhygl.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.zxhygl.mvc.base.pojo.LoginLog;
import com.dt.zxhygl.mvc.base.pojo.User;

import eu.bitwalker.useragentutils.UserAgent;

public class LoginLogUtils {

	public static LoginLog getLoginLog(HttpServletRequest request,boolean flag,User user){
		LoginLog log = new LoginLog();
		HttpSession session = request.getSession();
		//User user = (User)session.getAttribute(SystemConstant.SES_USER);
		
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));   
		
		String id = ComUtils.getUniqueString(); 
		String username = user.getUsername(); 
		String loginip = request.getLocalAddr(); 
		String hostname = request.getRemoteHost(); 
		String browser = getBrowserName(request); 
		browser = userAgent.getBrowser().toString()+" "+userAgent.getBrowserVersion();
		String osname = userAgent.getOperatingSystem().toString();
		String logintime = ComUtils.getTime(0); 
		String result = String.valueOf(flag);
		String xm = ComUtils.ConvNull(user.getXm());
		String type = user.getType();
		String role = ComUtils.ConvNull(user.getRole());
		
		log.setId(id);
		log.setUsername(username);
		log.setLoginip(loginip);
		log.setHostname(hostname);
		log.setBrowser(browser);
		log.setBrowser(browser);
		log.setOsname(osname);
		log.setLogintime(logintime);
		log.setResult(result);
		log.setXm(xm);
		log.setType(type);
		log.setRole(role);
		
		return log;
	}
	
	/**
	 * 判断浏览器版本
	 * @param request
	 * @return
	 */
	public static String getBrowserName(HttpServletRequest request) {
		String agent=request.getHeader("User-Agent").toLowerCase();
		if (agent.indexOf("msie 6") > 0) {
			return "IE6";
		} else if (agent.indexOf("msie 7") > 0) {
			return "IE7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "IE8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "IE9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "IE10";
		} else if (agent.indexOf("msie") > 0) {
			return "IE";
		} else if (agent.indexOf("opera") > 0) {
			return "Opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "Firefox";
		} else if (agent.indexOf("chrome") > 0) {
			return "Chrome";
		} else if (agent.indexOf("webkit") > 0) {
			return "Webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "IE11";
		} else {
			return "Others";
		}
	}
}
