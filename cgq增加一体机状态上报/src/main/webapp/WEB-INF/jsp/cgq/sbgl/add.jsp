<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
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
               <td style="width:120px;">编号：</td>
               <td style="width:360px;">    
                   <input name="sbbh" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
            <tr>
               <td style="width:120px;">名称：</td>
               <td style="width:360px;">    
                   <input name="mc" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">经度：</td>
               <td style="width:360px;">    
                   <input name="jd" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">纬度：</td>
               <td style="width:360px;">    
                   <input name="wd" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">地址：</td>
               <td style="width:360px;">    
                   <input name="dz" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">所属：</td>
               <td style="width:360px;">
                   <input id="qyid" name="qyid" class="mini-treeselect" url="<%=request.getContextPath() %>/cgq/area/getList.do"
    				   textField="name" valueField="id" parentField="pid" checkRecursive="true" required="true" 
    				   showFolderCheckBox="false" expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
                       popupWidth="200" style="width:300px;"/>   
               </td>
           </tr>
           <tr>
               <td style="width:120px;">推送分组：</td>
               <td style="width:360px;">
                   <input id="fz" name="fz" class="mini-combobox" url="<%=request.getContextPath() %>/gzh/fzxx/getList.do"
                          textField="name" valueField="id"  multiSelect="true"  style="width:300px;"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">负责人：</td>
               <td style="width:360px;">    
                   <input name="fzr" class="mini-combobox" url="<%=request.getContextPath() %>/cgq/rygl/selectAll.do"
                   style="width:300px;" textField="name" valueField="id" multiSelect="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">摄像头卡号：</td>
               <td style="width:360px;">    
                   <input name="deviceid" class="mini-textbox" style="width:300px;"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">设备标记：</td>
               <td style="width:360px;">    
                   <input name="bj" class="mini-combobox" valueField="value" textField="text" style="width:300px;"
                          url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100903"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">地图显示范围：</td>
               <td style="width:360px;">    
                   <input name="xsfw" class="mini-textbox" style="width:300px;"/>
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
    
    function ChangeQy(){
    	var qyid = mini.getByName("qyid").getValue();
    	var url = "<%=request.getContextPath() %>/cgq/rygl/selectAll.do?qyid="+qyid;
    	var fzr = mini.getByName("fzr");
    	fzr.setValue("");
    	fzr.setUrl(url);
    	fzr.select(0);
    }
    
    function SaveData() {
        var o = form.getData();
        o.fzrxm = mini.getByName("fzr").getText();
        o.fzmc = mini.getByName("fz").getText();
        o.qymc = mini.get("qyid").getText();
        form.validate();
        if (form.isValid() == false) return;
        var json = mini.encode(o);
        $.ajax({
            url: HOST_URL+"/cgq/sbgl/save.do",
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
            	url: HOST_URL+"/cgq/sbgl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     var url = "<%=request.getContextPath() %>/cgq/rygl/selectAll.do?qyid="+o.qyid;
                 	 var fzr = mini.getByName("fzr");
                 	 fzr.setUrl(url);
                }
            });
        }else if(data.action == "view"){
            data = mini.clone(data);
            $.ajax({
            	url: HOST_URL+"/cgq/sbgl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     mini.getbyName("ok").setEnabled(false);
                     var url = "<%=request.getContextPath() %>/cgq/rygl/selectAll.do?qyid="+o.qyid;
                 	 var fzr = mini.getByName("fzr");
                 	 fzr.setUrl(url);
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