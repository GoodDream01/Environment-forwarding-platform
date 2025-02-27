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
<form id="form1" method="post">
    <input name="zid" class="mini-hidden" />
    <div style="padding-left:10px;padding-bottom:5px;">
        <table style="table-layout:fixed;">
        	<tr>
                <td style="width:80px;">用户名：</td>
                <td style="width:150px;">    
                    <input name="zname" class="mini-textbox" required="true" />
                </td>
                <td style="width:80px;">账号：</td>
                <td style="width:150px;">
                    <input name="zcode" class="mini-textbox" required="true" onvalidation="onYhmValidation" />
                </td>
                <td style="width:80px;">密码：</td>
                <td style="width:150px;position: relative;">
                    <input name="zpassword" class="mini-textbox" required="true" emptyText="请输入登录密码"/>
                </td>
            </tr>
            <tr>
                <td style="width:100px;">所属单位：</td>
                <td style="width:280px;">
                    <input id="zqyid" name="zqyid" class="mini-treeselect" url="<%=request.getContextPath() %>/cgq/area/getList.do"
                           textField="name" valueField="id" parentField="pid" checkRecursive="true"
                           showFolderCheckBox="false" expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
                           popupWidth="200" />
                </td>
                <td style="width:80px;">用户角色：</td>
                <td style="width:150px;">
                    <input name="zrole" class="mini-combobox" valueField="value" textField="text"
                           url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100901"/>
                </td>
            </tr>
        </table>
        <table style="table-layout:fixed;">
            <tr>
                <td style="width:100px;">显示字段：</td>
                <td>
                    <div id="zxszd" name="zxszd" class="mini-checkboxlist" repeatItems="8" repeatLayout="table"
                         textField="text" valueField="id" url="../../static/xszd.txt" style="width:880px">
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div style="text-align:center;padding:10px;">               
        <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
        <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
    </div>        
</form>
<script type="text/javascript">
    
	mini.parse();
    var form = new mini.Form("form1");
    
    function SaveData() {
        var o = form.getData(); 
        o.zqymc = mini.get("zqyid").getText();
        form.validate();
        if (form.isValid() == false) return;
        var json = mini.encode(o);
        $.ajax({
            url: HOST_URL+"/base/operator/save.do",
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
            	url: HOST_URL+"/base/operator/getById.do?id="+data.id,
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
            	url: HOST_URL+"/base/operator/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    o.zpassword = "";
                    form.setData(o);
                    form.setChanged(false);
                    var zpassword = mini.getByName("zpassword");
                    zpassword.setRequired(false);
                    zpassword.setEmptyText("为空不修改");
                }
            });
        }else if(data.action == "new"){
			
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
	 function onTh(e) {
	 	TuiHui();
	 }
	 function onOk(e) {
	     SaveData();
	 }
	 function onCancel(e) {
	     CloseWindow("close");
	 }

    function onYhmValidation(e) {
        if (e.isValid) {

            e.errorText = "正在后台验证用户名是否重复...";
            e.isValid = false;
            
            var username = mini.getByName("zcode").getValue();
            var url = "base/operator/checkUsername.do?username="+username+"";
            if(czlx=="edit"){
            	var zid = mini.getByName("zid").getValue();
            	url = "base/operator/checkUsernameAndZid.do?username="+username+"&zid="+zid+"";
            }
            
            $.ajax({
            	url: HOST_URL+"/"+url,
                cache: false,
                async: false,
                dataType: "json",
                success: function (o) {
                	if(o){
                    	if(o.flag){
                            e.errorText = "用户名重复，请重新填写！";
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