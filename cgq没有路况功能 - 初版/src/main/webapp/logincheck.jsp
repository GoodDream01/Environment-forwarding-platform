<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.dt.taje.utils.*"%>
<%
if(null==session.getAttribute(SysConfig.SESSION_USER_INFO)){
	//response.sendRedirect("login.jsp");
%>
	<script type="text/javascript">
	<!--
		if(opener){//弹出窗口
			window.close();
			if(opener.top!=opener.self){/**如果不是顶层页面，就让顶层页面跳转到登录页面*/
				opener.top.location.href='<%=request.getContextPath()%>/login.jsp';
			}else{
				opener.location.href='<%=request.getContextPath()%>/login.jsp';
			}
		}else{//非弹出窗口
			if(top!=self){/**如果不是顶层页面，就让顶层页面跳转到登录页面*/
				top.location.href='<%=request.getContextPath()%>/login.jsp';
			}else{
				window.location.href='<%=request.getContextPath()%>/login.jsp';
			}
		}
	//-->
	</script>
<%
	return ;
}
%>