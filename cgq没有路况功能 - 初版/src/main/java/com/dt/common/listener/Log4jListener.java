package com.dt.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Log4jListener implements ServletContextListener {
	public static String log4jdirkey = "log4jdir";
	public static String contextPathkey = "contextPath";
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		System.getProperties().remove(log4jdirkey);
		System.getProperties().remove(contextPathkey);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
        String log4jdir = servletContextEvent.getServletContext().getRealPath("/");
        String contextPath = servletContextEvent.getServletContext().getContextPath();
        
        log4jdirkey = contextPath+log4jdirkey;
        contextPathkey = contextPath+contextPathkey;

        //System.out.println("log4jdir:"+log4jdir);

        System.setProperty(log4jdirkey, log4jdir);
        System.setProperty(contextPathkey, contextPath);
	}

}
