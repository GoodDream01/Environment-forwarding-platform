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
	<div id="hyqd">
		<div>
			<table border="0" cellpadding="0" cellspacing="0" width="800" >
				<tr>
					<td width="200" align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="190" >
							<tr>
								<td>
									<img alt="" height="350" border="1" src="<%=request.getContextPath()%>/static/images/daping/head.jpg">
								</td>
							</tr>
						</table>
					</td>
					<td width="100"></td>
					<td valign="top" align="center">
						<table border="0" cellpadding="0" cellspacing="0" width="500" class="info" style="margin-top: 50px;">
							<tr>
								<td class="title" width="150" align="right" height="50">
									姓名：
								</td>
								<td class="text" width="80" align="left">
									张三
								</td>
								<td class="title" width="80" align="right">
									性别：
								</td>
								<td class="text" align="left">
									女
								</td>
							</tr>
							<tr>
								<td class="title" align="right"  height="50">
									民族：
								</td>
								<td class="text" align="left">
									汉族
								</td>
								<td class="title" align="right">
									党派：
								</td>
								<td class="text" align="left">
									中共
								</td>
							</tr>
							<tr>
								<td class="title" align="right"  height="50">
									历届提案数：
								</td>
								<td class="text" align="left">
									32件
								</td>
								<td class="title" width="120" align="right">
									
								</td>
								<td class="text" align="left">
									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
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