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
    <input name="pmid" class="mini-hidden"/>
    <div style="padding-left:10px;padding-bottom:5px;padding-top: 10px">
       <table style="table-layout:fixed;height: 50px">
           <tr>
               <td style="width:120px;">预案等级：</td>
               <td style="width:360px;"> 
                   <input name="yadj" class="mini-combobox" valueField="value" textField="text" style="width:300px;"
                      url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100000" required="true"/>     
               </td>
           </tr>
           <tr>
               <td style="width:120px;">停留时间：</td>
               <td style="width:360px;">    
                   <input name="tlsj" class="mini-spinner" style="width:300px;" minValue="2" maxValue="30000" required="true"/>
               </td>
           </tr>
            <tr>
               <td style="width:120px;">出字方式：</td>
               <td style="width:360px;">  
               	   <input name="czfs" class="mini-combobox" valueField="value" textField="text" style="width:300px;"
                      url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100001" required="true"/>  
               </td>
           </tr>
           <tr>
               <td style="width:120px;">特效速度：</td>
               <td style="width:360px;">    
                   <input name="txsd" class="mini-spinner" style="width:300px;" minValue="0" maxValue="49"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">字体颜色：</td>
               <td style="width:360px;">    
                    <input name="ztys" class="mini-combobox" valueField="value" textField="text" style="width:300px;"
                      url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100002" required="true"/>  
               </td>
           </tr>
           <tr>
               <td style="width:120px;">字体宽高：</td>
               <td style="width:360px;">    
                    <input name="ztkg" class="mini-combobox" valueField="value" textField="text" style="width:300px;"
                      url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100003" required="true"/>  
               </td>
           </tr>
           <tr>
               <td style="width:120px;">文字内容：</td>
               <td style="width:360px;">    
                    <input name="wznr" class="mini-textbox" style="width:300px;" required="true"/>
               </td>
           </tr>
           <tr>
               <td style="width:120px;">文件上传：</td>
               <td style="width:360px;">    
               	   <span style="font-size: 14px;font-weight: bold;margin-left: 2px">附件：</span>
                   <input class="mini-hidden" name="upload"/>
                   <input class="mini-textbox" name="uploadname" style="margin-left: 17px;"/>
                   <input name="file" type="file" id="file" style="width:100px;display: none;" onchange="uploadFile(this)"/>
                   <a class="mini-button" onclick="sc()" id = "sc" >上传文件</a>
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
    
    function SaveData() {
        var o = form.getData();
        o.yamc = mini.getByName("yadj").getText();
        form.validate();
        if (form.isValid() == false) return;
        
        var obj = new Object();
        obj.tlsj = o["tlsj"];
        obj.czfs = o["czfs"];
        obj.txsd = o["txsd"];
        obj.txsd = o["txsd"];
        obj.ztys = o["ztys"];
        obj.ztkg = o["ztkg"];
        obj.wznr = o["wznr"];
        o.yanr = JSON.stringify(obj);
        
        var json = mini.encode(o);
        $.ajax({
            url: HOST_URL+"/pmkz/yagl/save.do",
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
            	url: HOST_URL+"/pmkz/yagl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                }
            });
        }else if(data.action == "view"){
            data = mini.clone(data);
            $.ajax({
            	url: HOST_URL+"/pmkz/yagl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                	 var o = mini.decode(text);
                     form.setData(o);
                     form.setChanged(false);
                     mini.getbyName("ok").setEnabled(false);
                }
            });
        }else if(data.action == "yabc"){
        	mini.getByName("pmid").setValue(data.id);
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
	
	function sc() {
        document.getElementById("file").click();
    }
    function uploadFile(obj) {
        var f=$(obj).val();
        if(f == null || f ==undefined || f == ''){
            return false;
        }
        var data = new FormData();
        for (var i = 0; i < obj.files.length; i++){
            data.append('file',obj.files[i]);
        }
        $.ajax({
            type: "POST",
            url: HOST_URL+"/upload/uploadfile.do",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            success: function(res) {
                var a =res.split(",");
                mini.getByName("upload").setValue(a[0]);
                mini.getByName("uploadname").setValue(a[1]);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
            }
        });
    }
    
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>