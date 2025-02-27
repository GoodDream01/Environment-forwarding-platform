<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>富奥通协议转发平台</title>
    <jsp:include page="/common_include.jsp" />	
    <link href="<%=request.getContextPath() %>/css/login.css?<%=Math.random() %>" rel="stylesheet" type="text/css">
    <!--[if lte IE 6]>
       <style type="text/css">
       body { behavior:url("css/csshover.htc"); }
       </style>
     <![endif]-->
     <style>
     img{
     	height: auto;
     }
     </style>
</head>

<body>
    <div class="div-100"> 
		<div class="div-title" style="text-align: center;font-size: 50px;margin: 100px 0 100px 0">
			<span>富奥通协议转发平台</span>
		</div>
	  <div class="login-body">
		<div class="login-box">
		 <form name="form1" action="<%=request.getContextPath() %>/login/login.do?sessionId=<%=session.getId() %>" method="post" target="F1">
			<ul>
			  <li class="loginuser">用户名:<input type="text" id="userName" name="userName" value="" /></li>
		      <li class="loginpwd" >密&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" id="password" name="password" value=""  onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"/></li>
			  <li class="loginbtn"><button  type="button" name="button" value=""  onclick="checkform();" ></button></li>
			  <li class="resetbtn"><button  type="button" name="reset" value="" onclick="re();"></button></li>
			</ul>
		 </form>
		</div>
	  </div>
    </div>
<iframe name="F1" style="display: none;"></iframe>
<script type="text/javascript">

	/*回车事件*/
	function EnterPress(e){ //传入 event 
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
	    // var imageCode=document.getElementById("imageCode").value;
	    // if($.trim(imageCode).length==0){
	    //   alert("验证码不能为空。                               ");
	    //   return false;
	    // }
	    form1.submit();
	    return true;
	}

	function re(){
		location.reload();
	}
	
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>