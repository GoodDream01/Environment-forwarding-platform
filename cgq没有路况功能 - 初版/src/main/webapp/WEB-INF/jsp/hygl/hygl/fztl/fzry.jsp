<%@page import="com.dt.zxhygl.mvc.hygl.pojo.Hyfztl"%>
<%@page import="java.util.Random"%>
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
</head/head01>
<body>
<div id="container">
	<jsp:include page="/inc/daping/toolbar.jsp"></jsp:include>
	<div id="title">分组名称：${hyfztl.zfzmc }</div>
	<div id="fzry">
		<div>
			<table border="0" cellpadding="0" cellspacing="0" width="800" >
				<tr>
					<td valign="top" align="center" >
						<%
							int totalCount = 27;
							int pageSize = 10;
							int totalPage = (totalCount-1)/pageSize+1;//总页数=(总数-1)/每页数量+1
							for(int i=0;i<totalPage;i++){
								int indx = i+1;
								int startIndex = i*pageSize;
								int endIndex = (i+1)*pageSize;
								if(endIndex>totalCount){
									endIndex = totalCount;
								}
								
								String display = "";
								if(i>0){
									display = "display:none;";
								}
		
						%>
						<!-- ///////////////////////////////////人员<%=indx%>页：开始//////////////////////////////////////// -->
						<table id="page_<%=indx%>" border="0" cellpadding="0" cellspacing="0" width="800" class="info" style="margin-top: 10px;<%=display%>">
							<tr>
								<%
								for(int j=startIndex,n=0;j<endIndex;j++,n++){ 
									if(n!=0&& (n)%5==0){
										out.println("</tr><tr>");
									}
									 int number = new Random().nextInt(10) + 1;
									 String headNum = number+"";
									 if(number<10){
										 headNum = "0"+number;
									 }
								%>
								<td align="center">
									<!-- 单个人：开始 -->
									<table>
										<tr>
											<td>
												<img alt="" class="head" border="1" src="<%=request.getContextPath()%>/static/images/daping/head/head<%=headNum %>.jpg">
											</td>
										</tr>
										<tr>
											<td class="text" width="80" align="center">
												张三
											</td>
										</tr>
									</table>
									<!-- 单个人：结束 -->
								</td>
								<%} %>
							</tr>
						</table>
						<!-- ///////////////////////////////////人员<%=indx%>页：结束//////////////////////////////////////// -->
						<%} %>
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
/******切换分页：Start********/ 
var totalPage = <%=totalPage %>;
var page = 2;
function changePage(){
	var obj = $("#page_"+page);
	$("[id^='page_']").slideUp("fast"); 
	obj.slideDown();
	page++;
	if(page>totalPage){
		page=1;
	}
}
setInterval(changePage,1000*5);
/******切换分页：End********/ 
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>