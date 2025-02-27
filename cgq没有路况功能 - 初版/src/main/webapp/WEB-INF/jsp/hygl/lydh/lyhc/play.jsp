<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.common.utils.ZiDianUtils"%>
<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/static/js/common/host_info.jsp?&<%=Math.random()%>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/static/js/common/common_fun.js?&<%=Math.random()%>" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/static/scripts/jquery.min.js" type="text/javascript"></script>
</head>
<body id="player">
<script type="text/javascript">
playSound("player","${lydhHc.zurl}");
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>