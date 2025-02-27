<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.SubResult"%>
<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.Result"%>
<%@page import="java.util.List"%>
<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.Presult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Presult presult = (Presult)request.getAttribute("presult");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="100%">
	<tr>
		<td align="center" width="95%"><h2><%=presult.getTitle() %></h2></td>
	</tr>
</table>
<table>
	<tr><td>
<%
List<Result> results = presult.getResult();
if(results!=null && results.size()>0){
	for(int i=0;i<results.size();i++){
		Result result = results.get(i);
		String result_title = result.getTitle();
		
%>
		<table width="800">
			<tr><td width="95%"><%=i+1+"、"+result_title %></td></tr>
			<tr><td>
			<table width="95%">
			<%
			List<SubResult> subResults = result.getSubResult();
			if(subResults!=null && subResults.size()>0){
				for(int n=0;n<subResults.size();n++){
					SubResult subResult = subResults.get(n);
			%>
				<tr><td width="90%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=n+1+"、"+subResult.getTitle()+" - ("+subResult.getNum()+"票)" %></td></tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="<%=request.getContextPath()%>/static/images/dcwj/vote_bar.gif" width="<%=Double.parseDouble(subResult.getBfb())*0.9%>%" height="10px"/> <%=subResult.getBfb()%>%</td></tr>
			<%
				}
			}
			%>
			</table>
			</td></tr>
		</table>
<%		
	}
}
%>
	</td></tr>
</table>
</body>
</html>