<%@ page import="com.dt.common.utils.SystemConstant"%>
<%@ page import="com.dt.zxhygl.mvc.base.pojo.User"%>
<%@ page import="com.dt.taje.utils.SysConfig"%>
<%@ page import="com.dt.taje.mvc.base.modle.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserBean userBean = (UserBean)session.getAttribute(SysConfig.SESSION_USER_INFO);
	User user = (User)session.getAttribute(SystemConstant.SES_USER);
	String USERINFO_USERID = userBean.getZUID();//用户ID
	String USERINFO_USERNAME = userBean.getZUNAME();//用户名
	String USERINFO_USERXM = userBean.getZUXM();//用户姓名
	String USERINFO_ROLE = userBean.getZROLE();//角色类型
	String USERINFO_DWBH = userBean.getZQYID();//区域id
	String USERINFO_DWMC = userBean.getZQYMC();//区域名称
%>