<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <style>
	
	body{	
        background:url(images/bg.jpg) no-repeat;
		background-size:cover;
		width:100%;
		height:100%;
		margin:0 auto;
		overflow-y: hidden;
		overflow-x:hidden;
}
</style> -->

<style>
	*{
      padding:0;
      margin:0;
    }
	body{
	  background:#E6E6E8;/**6fa5e8*/
	  overflow-x:hidden; 
	  overflow-y:hidden;
	  
	}
    #container{
	  width:100%;
	  height:550px;		  
	  background:url(<%=request.getContextPath() %>/images/login/background.jpg) center top no-repeat;
	  background-size:100% 100%;
	  margin:0 auto;
	  margin-top:0px;
   }
</style>
<body>
  <div id="container"></div>
</body>
</html>