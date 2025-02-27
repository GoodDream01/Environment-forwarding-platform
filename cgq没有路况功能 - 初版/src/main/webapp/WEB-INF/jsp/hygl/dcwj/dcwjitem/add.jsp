<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>


	<fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >调查问卷选项添加</legend>
	        
    <form id="form1" method="post">
        <input name="zid" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">标题：</td>
                    <td style="width:360px;">    
		                <input name="ztitle" class="mini-textbox" style="width:330px;" required="true"/>
<!-- 		                <input name="ztitle" class="mini-textbox" style="width:330px;" required="true" onvalidation="checkByname"/> -->
                    </td>
                </tr>
                <tr>
                    <td >排序号：</td>
                    <td >    
                        <input name="zsort" class="mini-spinner" required="true" />
                        <input name="topicid" id="topicid" class="mini-textbox" visible="false" required="true"/>
                        <input name="subtopicid" id="subtopicid" class="mini-textbox" visible="false" required="true"/>
                        <input name="zvotenum" class="mini-textbox" visible="false" required="true"/>
                    </td>
                </tr>
               

            </table>
        </div>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
        </div>        
    </form>
   
    </fieldset>

    <script type="text/javascript">

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var form = new mini.Form("form1");
    
    var grid = mini.get("datagrid1");
    
    var topicid = "";
    var subtopicid = "";

    function SaveData() {
        var o = form.getData(); 
        
        form.validate();
        if (form.isValid() == false) return;

        var json = mini.encode(o);
        
        $.ajax({
            url: HOST_URL+"/dcwj/dcwjitem/save.do",
			type: 'post',
		    dataType: 'json',
		    contentType:'application/json;charset=UTF-8',
            data: json,
            cache: false,
            success: function (text) {
                
                var success = text.success;
                if(success){
                	alert("保存成功！");
                	CloseWindow("save");
                }else{
                	alert("保存失败！");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
//             	alert(text);
                alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }
    var czlx = "";
    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	czlx = data.action;
        if (data.action == "view") {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);

            $.ajax({
            	url: HOST_URL+"/gggl/xtgg/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    form.setChanged(false);

                    mini.getbyName("ok").setEnabled(false);

                }
            });
        }else if (data.action == "edit") {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);
			
            $.ajax({
            	url: HOST_URL+"/dcwj/dcwjitem/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    form.setChanged(false);
                    
                }
            });
        }else if(data.action == "new"){
        	 data = mini.clone(data);
//         	alert(data.topicid);alert(data.subtopicid);
        	mini.getByName("topicid").setValue(data.topicid);
        	mini.getByName("subtopicid").setValue(data.subtopicid);
        	getSort();
        }
    }
    
    //
// 	function CloseWindow(action) {      
// 		if (action == "close" && form.isChanged()) {
// 			mini.confirm("数据被修改了，是否先保存？", "确定？",
// 		       	 function (action) {
// 		       		if(action == "ok"){
		       			
// 		       		}else{
// 		    			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
// 		       			else window.close(); 
// 		       		}
		       		  
// 			});
// 		}else{
// 			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
//    			else window.close(); 
// 		}
        
// 	 }
	 function onTh(e) {
	 	TuiHui();
	 }
	 function CloseWindow(action) {
	        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	        else window.close();
	    }

	    function onOk() {
	        CloseWindow("ok");
	    }
	 function onCancel(e) {
	     CloseWindow("close");
	 }
    
	    function GetData(){
			var o = form.getData();
	    	return o;
	    }
	 
	 
	 
	
    function changeTypeCode(e){
    	getSort();
    }
    
    
    
    function getSort(){
    	var typeCode = mini.getByName("zsort").getValue();
    	
    	var sortObj = mini.getByName("zsort");
    	if(typeCode =="" || typeCode =="0"){
            $.ajax({
                url: HOST_URL+"/gggl/xtgg/getSort.do",
    			type: 'post',
    		    dataType: 'html',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
               		sortObj.setValue(Number(text)+1);
                }
            });
    	}else{
    		sortObj.setValue(0);
    	}
    	

    }
    
    
	////////////////////////////

    function getRows(){
//     	var rows = grid.findRows(function(row){
//     	    if(row.ztitle!='') return true;
//     	});
// 		var rows = grid.findRow(function(row){
//     	    if(row.ztitle!='') return true;
//     	});
		var rows = form.getData();
    	return rows;
    } 
    
    
    function checkByname(e){
//     	alert("getByName");
    	if (e.isValid) {

            e.errorText = "正在后台验证用户名是否重复...";
            e.isValid = false;
            
            var username = mini.getByName("ztitle").getValue();
            var url = "gggl/xtgg/checkTitle.do?ztitle="+username+"";
            if(czlx=="edit"){
            	var zid = mini.getByName("zid").getValue();
            	url = "user/checkTitlenameAndZid.do?username="+username+"&zid="+zid+"";
            }
            
            $.ajax({
            	url: HOST_URL+"/"+url,
                cache: false,
                async: false,
                dataType: "json",
                success: function (o) {
                	
                	if(o){
                    	if(o.flag){
                            e.errorText = "标题重复，请重新填写！";
                            e.isValid = false;
                    	}else{
                    		e.errorText = "";
                            e.isValid = true;
                    	}
                	}
                }
            });
        }
    }
    
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>