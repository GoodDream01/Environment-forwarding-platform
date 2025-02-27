<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.Item"%>
<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.Subtopic"%>
<%@page import="java.util.List"%>
<%@page import="com.dt.zxhygl.mvc.dcwj.pojo.Topic"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.common.utils.ZiDianUtils"%>
<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %> 

<%
	Topic topic = (Topic)request.getAttribute("topic");
// 	request.setAttribute("topic", topic);
	
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
    <form id="form1" method="post" action="<%=request.getContextPath()%>/dcwj/dcwjye/save.do">
    <%if(topic!=null){ %>
    	<input type="hidden" id="topicid" name="topicid" value="<%=topic.getZid()%>"/>
 		<table width="95%">
 			<tr>
 				<td colspan="3" align="center" style="font-size: 20px;"><strong><%=topic.getZtitle() %></strong>  </td>
 			</tr>
 			<tr>
 				<td colspan="3" align="center" style="font-size: 14px;"><strong>(<%=topic.getZnrms() %>)</strong>  </td>
 			</tr>
 			<%
			List<Subtopic> subtopics = topic.getSubtopic();
 			if(subtopics!=null && subtopics.size()>0){
 				for(int i=0;i<subtopics.size();i++){
 					Subtopic subtopic = subtopics.get(i);
 					String lx = subtopic.getZtype();//类型（1单选，2多选，3文本）
 					%>
 					<tr>
 						<td width="3%"></td>
 						<td><strong><%=i+1+"、"+subtopic.getZtitle() %></strong></td>
 					</tr>
 					<%
 					List<Item> items = subtopic.getItem();
 					if(items!=null && items.size()>0){
 						for(int n=0;n<items.size();n++){
 							Item item = items.get(n);
 							%>
 							<tr>
 								<td></td>
 								
 							<%
 							if("1".equals(lx)){
 								%>
 								<td>&nbsp;&nbsp;<input type="radio" name="<%=item.getSubtopicid() %>" value="<%=item.getZid()%>"/><%=item.getZtitle()%></td>
 								<td><input type="hidden" name="<%=item.getZid()%>"/></td>
 								<%
 							}else if("2".equals(lx)){
 								%>
 								<td>&nbsp;&nbsp;<input type="checkbox" name="<%=item.getSubtopicid() %>" value="<%=item.getZid()%>"/><%=item.getZtitle()%></td>
 								<td><input type="hidden" name="<%=item.getZid()%>"/></td>
 								<%
 							}else if("3".equals(lx)){
 								%>
 								<td>&nbsp;&nbsp;<%=item.getZtitle() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="<%=item.getSubtopicid()%>"/></td>
 								<td></td>
 								<%
 							}
 							%>
 								
 							</tr>
 							<%
 						}
 					}
 					
 				}
 			}
 			%>
 			<tr>
 				<td colspan="3" align="center">
 				<input type="submit" value="提交"/>
<!--  				<button onclick="tj()">提交</button> -->
				<input type="button" onclick="jg()" value="查看投票结果"/>
 				</td>
 			</tr>
 		</table>  
 		<%}else{ %>
 		
 		暂无问卷
 		<%} %>      
    </form>
   
    

<script type="text/javascript">

    //////////////////////////////////////////////////////////////////////////////
    function jg(){
    	
    	document.getElementById("form1").action = HOST_URL+"/dcwj/dcwjye/getResult.do";
    	document.getElementById("form1").submit();
    }
    
    
    function tj(){
    	alert(123);
    	var form = ${"#form1"};
    	
    	document.getElementById("form1").action = HOST_URL+"/dcwj/dcwjye/save.do";
    	document.getElementById("form1").submit();
// 		var json = {};
// 		var topic = document.getElementById("tm").value;
// 		json.topic = topic;
// 		console.log(json);
//     	$.ajax({
//             url: HOST_URL+"/dcwj/dcwjye/save.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
//             data: json,
//             cache: false,
//             success: function (text) {
                
//                 var success = text.success;
//                 if(success){
//                 	alert("保存成功！");
//                 	CloseWindow("save");
//                 }else{
//                 	alert("保存失败！");
//                 }
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 alert(jqXHR.responseText);
//                 CloseWindow();
//             }
//         });
    	
    }


</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>