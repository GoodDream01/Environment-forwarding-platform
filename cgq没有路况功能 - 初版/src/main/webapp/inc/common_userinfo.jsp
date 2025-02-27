<%@page import="com.dt.common.utils.SystemConstant"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 User _curentUser = (User)session.getAttribute(SystemConstant.SES_USER);
 String USERINFO_USERID = _curentUser.getZid();
 String USERINFO_USERNAME = _curentUser.getUsername();
 String USERINFO_PASSWORD = _curentUser.getPassword();
 String USERINFO_USERXM = _curentUser.getXm();
 String USERINFO_TYPE = _curentUser.getType();//登录类型；0管理员或工作，2政府单位，2业主单位
 String USERINFO_ROLE = _curentUser.getRole();//角色类型
 String USERINFO_DWID = _curentUser.getQyid();
 String USERINFO_DWMC = _curentUser.getQymc();
 String USERINFO_XSZD = _curentUser.getXszd();
 %>