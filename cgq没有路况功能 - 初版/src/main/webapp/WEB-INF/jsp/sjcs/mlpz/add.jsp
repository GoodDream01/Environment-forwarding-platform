<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增</title>
<jsp:include page="/inc/common_js_include.jsp"/>
</head>
<body>
<form id="form1" method="post">
    <input name="id" class="mini-hidden"/>
    <div style="padding-left:10px;padding-bottom:5px;padding-top: 10px">
       <table style="table-layout:fixed;height: 50px">
           <tr>
               <td style="width:100px;">设备编号：</td>
               <td style="width:380px;">
                   <input name="sbid" class="mini-textbox" style="width:350px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:100px;">下发命令：</td>
               <td style="width:380px;">
                   <input name="value" class="mini-combobox" valueField="value" textField="text" style="width:350px;" onvaluechanged="OnValue"
                          url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100905" required="true"/>
               </td>
           </tr>
           <!-- 24.2.22修改发邮件指定时间 -->
           <tr>
               <td style="width:100px;">邮件指定时间：</td>
               <td style="width:380px;">
                   <input name="zdsj" class="mini-textbox" style="width:350px;"/>
               </td>
           </tr>
           <tr>
               <td style="width:100px;">域名：</td>
               <td style="width:380px;">
                   <input name="ym" class="mini-textbox" style="width:350px;"/>
               </td>
           </tr>
           <tr>
               <td style="width:100px;">端口：</td>
               <td style="width:380px;">
                   <input name="dk" class="mini-textbox" style="width:350px;"/>
               </td>
           </tr>
       </table>
    </div>
    <div style="text-align:center;padding:10px;">               
       <a name="ok" class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
       <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
    </div>        
</form>
<script type="text/javascript">
    
	mini.parse();
    var form = new mini.Form("form1");

    function OnValue(){
        var value = mini.getByName("value").getValue();
        if(value=="02"){
            mini.getByName("dk").setRequired(true);
        }else{
            mini.getByName("dk").setRequired(false);
        }
    }

    function SaveData() {
        var o = form.getData();
        form.validate();
        o.text = mini.getByName("value").getText();
        if (form.isValid() == false) return;
        var json = mini.encode(o);
        $.ajax({
            url: HOST_URL+"/sjcs/mlpz/save.do",
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
    
    function SetData(data) {
        if (data.action == "edit") {
            data = mini.clone(data);
            $.ajax({
            	url: HOST_URL+"/sjcs/mlpz/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     OnValue();
                }
            });
        }else if(data.action == "view"){
            data = mini.clone(data);
            $.ajax({
            	url: HOST_URL+"/sjcs/mlpz/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     mini.getbyName("ok").setEnabled(false);
                     OnValue();
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