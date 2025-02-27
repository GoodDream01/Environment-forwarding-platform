<%@page import="com.dt.common.utils.ComUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = ComUtils.ConvNull((String)request.getAttribute("msg"));
String msgstr = "";
if(msg.equals("1")){
	msgstr = "用户名或密码错误!";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>政协会议管理系统</title>	
    <jsp:include page="/inc/common_js_include.jsp" />
    <link href="<%=request.getContextPath() %>/static/images/login2/login.css?&<%=Math.random()%>" rel="stylesheet" type="text/css">
    <!--[if lte IE 6]>
       <style type="text/css">
       body { behavior:url("<%=request.getContextPath()%>/static/css/csshover.htc"); }
       </style>
     <![endif]-->
     <style type="text/css">
		.div-100 .title img{
			padding:0px;
			margin-top:120px;
		}
		.div-100 .title{
			height:100px;
		}
     </style>
</head>

<body>
    <div class="div-100"> 
	   <div class="title">
		<img src="<%=request.getContextPath() %>/static/images/login2/title.png"/>
	   </div>
	  <div class="login-body">
		<div class="login-box" style="position: relative;">
		 <div style="position:absolute;width: 300px;height: 25px;top:50px;left:90px;"><center><%if(!msgstr.equals("")){ %><font color="red"><%=msgstr %></font><%} %></center></div>
		 <form name="form1" action="<%=request.getContextPath() %>/login/check.do?" method="post" >
			  <div id="us"><input type="text" class="login" id="userName" name="userName" value=""/></div>
		      <div id="pw"><input type="password" class="login" id="password" name="password" value=""  onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/></div>
			  <div style="position:absolute;top:220px;left:150px; margin: 0px;padding:0px;height: auto;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="type" name="type" style="width:120px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()">
			  		<option value="0">管理员/工作人员</option>
			  		<option value="1">政协委员</option>
			  </select></div>
			  <div class="loginbtn"><input  type="submit" name="button" value="" onclick="checkform();"></input></div>
		 </form>
		</div>
	  </div>
    </div>
<iframe name="F1" style="display: none;"></iframe>
<script type="text/javascript">

/*回车事件*/
function EnterPress(e){ //传入 event 
	//alert("111");
	var e = e || window.event; 
	if(e.keyCode == 13){ 
		checkform();
	} 
} 



  function checkform(){
    var uname = document.getElementById("userName").value;
    if($.trim(uname).length==0){
      alert("用户名不能为空。                               ");
      return false;
    }
    var pass=document.getElementById("password").value;
    if($.trim(pass).length==0){
      alert("密码不能为空。                               ");
      return false;
    }
    
    form1.submit();
    return true;
}
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>