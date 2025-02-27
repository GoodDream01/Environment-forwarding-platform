<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片</title>
<jsp:include page="/inc/common_js_include.jsp"/>
</head>
<body>
<form id="form1" method="post">
    <input name="id" class="mini-hidden"/>
    <img id="tp" src="" style="width:780px;height:560px;"/>
</form>
<script type="text/javascript">
    
	mini.parse();
    var form = new mini.Form("form1");
    
    function SetData(data) {
        if (data.action == "view") {
            data = mini.clone(data);
            $.ajax({
            	url: HOST_URL+"/cgq/cgqsj/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     var tplj = o.tpbm;
                     tplj = tplj.substring(16,tplj.length);
                     $("#tp").attr("src", "http://36.139.74.97:9023"+tplj);
                }
            });
        }
    }
    
	function CloseWindow(action) {      
		if (action == "close" && form.isChanged()) {
			mini.confirm("数据被修改了，是否先保存？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
		       			
		       		}else{
		    			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		       			else window.close(); 
		       		}
			});
		}else{
			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
   			else window.close(); 
		}
	}
	
	function onOk(e) {
	    SaveData();
	}
	 
	function onCancel(e) {
	    CloseWindow("close");
	}
	
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>