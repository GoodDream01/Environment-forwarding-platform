<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String baseDomain = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
var HOST_URL="<%=basePath %>";
var HOST_PATH="<%=path %>";
var HOST_DOMAIN="<%=basePath %>";
