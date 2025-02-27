<%@page import="java.util.*"%>
<%@page import="com.dt.common.utils.SystemConstant"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/logincheck.jsp" %>
<%@include file="/logininfo.jsp" %>
<%@page import="com.dt.common.db.ConnDataBase"%>
<%@page import="com.dt.pmkz.mvc.controller.Snippet"%>
<%
	ConnDataBase conn = new ConnDataBase();
	String id = request.getParameter("id");
	List list = conn.executeQuery("select id,splj from cgq_spxx where devid in (select deviceid from cgq_sbgl where id = '"+id+"') order by xtsj desc");
	String splj = "/uploadfiles/files/111.mp4";
	if(list.size()>0){
		Map map = (Map)list.get(0);
		splj = map.get("splj").toString();
	}
	String spxs = request.getContextPath()+splj;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <video width="470" height="340" controls="controls">
   <source src="<%=spxs %>" type="video/mp4" />
 </video> 
</body>
</html>