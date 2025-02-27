<%@page import="com.dt.common.utils.ComUtils"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.common.utils.ZiDianUtils"%>
<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<%
String id = ComUtils.ConvNull(request.getParameter("id"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
<link href="<%=request.getContextPath() %>/static/css/daping/daping.css?&<%=Math.random()%>" rel="stylesheet" type="text/css" />
<style>

</style>
</head>
<body>
<div id="container">
	<jsp:include page="/inc/daping/toolbar.jsp"></jsp:include>
	<div id="title">请用智慧政协手机APP扫码签到</div>
	<div id="qrcode">
		<img alt="二维码信息" src="<%=request.getContextPath()%>/hygl/hygl/getQrCode.do?id=<%=id %>" width="270" height="270">
	</div>
</div>
<script type="text/javascript">
//
function CloseWindow(action) {      
	if (action == "close") {
		mini.confirm("是否关闭本页面？", "确定？",
	       	 function (action) {
	       		if(action == "ok"){
	    			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	       			else window.close(); 
	       		}else{

	       		}
	       		  
		});
	}else{
		if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
			else window.close(); 
	}
    
 }
 function onCancel(e) {
     CloseWindow("close");
 }
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>