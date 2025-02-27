<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
<jsp:include page="/inc/common_ueditor_include.jsp" />
</head>
<body>
<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    ue.addListener('beforepaste',function(type,html){
    	alert('粘贴前事件触发');
    	alert('内容是：'+html.html);
    	
    });
    ue.addListener('afterpaste',function(){
    	alert('粘贴后事件触发');
    	alert('内容是：'+ue.getContent());
    	
    });
</script>

</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>