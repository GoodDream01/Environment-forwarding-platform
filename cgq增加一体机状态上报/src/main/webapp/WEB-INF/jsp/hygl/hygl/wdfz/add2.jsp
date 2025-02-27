<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.common.utils.ZiDianUtils"%>
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
        <div style="padding-left:11px;padding-bottom:5px;">
	        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >基本信息</legend>
	            <div style="padding:5px;">
	            	<!-- ////////////////////////////////////////////////////////////// -->
		            <table style="table-layout:fixed;">
		                <tr>
		                    <td class="addBt">标题：</td>
		                    <td style="width:300px;">    
		                        <input name="ztitle" class="mini-textbox" required="true"  style="width: 300px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">会议：</td>
		                    <td style="width:300px;">    
		                    	<input id="zhyid" name="zhyid" textName="hyinfo.zhymc" class="mini-buttonedit" onbuttonclick="onHyButtonEdit" required="true" allowInput="false" style="width:300px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">分组：</td>
		                    <td style="width:300px;">    
		                    	<input id="zfzid" name="zfzid" textName="hyfztl.zfzmc" class="mini-buttonedit" onbuttonclick="onFzButtonEdit" required="true" allowInput="false" style="width:300px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">类型：</td>
		                    <td style="width:300px;">    
								<input name="zwjlx" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_HYWJLX %>"
								 required="true" 
								/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">文件：</td>
		                    <td style="width:300px;">    
								<input id="zwjmc" name="zwjmc" textName="zwjmc" class="mini-fileupload"  required="true" 
							    flashUrl="<%=request.getContextPath() %>/static/scripts/swfupload/swfupload.swf" uploadUrl="<%=request.getContextPath() %>/inc/upload/uploadnew_json.jsp" 
							    onuploadsuccess="onUploadSuccess" onuploaderror="onUploadError" onfileselect="onFileSelect" style="width:235px;"/>
							   <a class="mini-button"  onclick="startUpload()" style="width:60px;">上传</a>
							   <input id="zwjurl" name="zwjurl" class="mini-hidden">
							   <input id="zwjkzmlx" name="zwjkzmlx" class="mini-hidden">
							   <input id="zwjsize" name="zwjsize" class="mini-hidden">
		                    </td>
		                </tr>
		            </table>
		            <!-- ////////////////////////////////////////////////////////////// -->
	            </div>
	        </fieldset> 

        </div>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
        </div>        
    </form>
   
    

    <script type="text/javascript">

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var form = new mini.Form("form1");

    function SaveData() {
        var o = form.getData(); 
        
        form.validate();
        if (form.isValid() == false) return;
        
        var zwjurl = o.zwjurl;
        if(zwjurl==""){
        	mini.alert("请上传文件!","提示");
        	return ;
        }
        
        var json = mini.encode(o);
        console.log(json);
        $.ajax({
            url: HOST_URL+"/hygl/wdfz/wj/save.do",
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
            	url: HOST_URL+"/hygl/wdfz/getFzwjById.do?id="+data.id,
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
            	url: HOST_URL+"/hygl/wdfz/getFzwjById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);

                    form.setChanged(false);
                    
                }
            });
        }else if(data.action == "new"){

        }
    }
    
    //
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

	 function onHyButtonEdit(e) {
		    var btnEdit = this;
		    mini.open({
		        url: HOST_URL + "/hygl/hygl/selDanUi.do",
		        title: "会议选择",
		        width: 550,
		        height: 380,
		        onload: function () {
		            var iframe = this.getIFrameEl();
		        	var zhyid = btnEdit.getValue();
		            var data = [{ zhyid: zhyid}];
		            iframe.contentWindow.SetData(data);
		        },
		        ondestroy: function (action) {
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.GetData();
		                data = mini.clone(data);    //必须
		                if (data) {
		                	/*************************************/
		                	var zhyid = data.zid;
		                	var zhymc = data.zhymc;
		                	
		                	
		    	        	
		    	            btnEdit.setValue(zhyid);
		    	            btnEdit.setText(zhymc);
		    	            btnEdit.doValueChanged();
		    	            /*************************************/
		                }
		            }

		        }
		    });
		}
	 
	 function onFzButtonEdit(e) {
		    var btnEdit = this;
		    //添加个查询参数
		    var zhyid = mini.getByName("zhyid").getValue();
// 		    alert(zhyid);
		    mini.open({
		        url: HOST_URL + "/hygl/wdfz/selDanUi.do",
		        title: "分组选择",
		        width: 550,
		        height: 380,
		        onload: function () {
		            var iframe = this.getIFrameEl();
		        	var zfzid = btnEdit.getValue();
		            var data = [{ zfzid: zfzid}];
		            iframe.contentWindow.SetData(data,zhyid);
		        },
		        ondestroy: function (action) {
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.GetData();
		                data = mini.clone(data);    //必须
		                if (data) {
		                	/*************************************/
		                	var zfzid = data.zid;
		                	var zfzmc = data.zfzmc;
		                	
		                	
		    	        	
		    	            btnEdit.setValue(zfzid);
		    	            btnEdit.setText(zfzmc);
		    	            btnEdit.doValueChanged();
		    	            /*************************************/
		                }
		            }

		        }
		    });
		}

    </script>
<script type="text/javascript">

    function onFileSelect(e) {
       // alert("选择文件");
       //alert(e.serverData);
       console.log(e);
    }
    function onUploadSuccess(e) {

        var data = $.trim(e.serverData);
        data  = mini.decode(data);
        if(data.success){
            var fileName = data.fileName;
            var filePath = data.url;
            var fileSize = data.fileSize;
            var fileType = data.fileType;
            
            fileSize = FormatFileSize(fileSize);

            this.setText(fileName);
            this.setValue(fileName);
            
            mini.get("zwjurl").setValue(filePath);
            mini.get("zwjkzmlx").setValue(fileType);
            mini.get("zwjsize").setValue(fileSize);
            
            mini.alert("上传成功！");
        }else{
        	mini.alert("上传失败！");
        }

    }
    function onUploadError(e) {
        
    }

    function startUpload() {
    	var fileupload = mini.get("zwjmc");
        var url = "<%=request.getContextPath() %>/inc/upload/uploadnew_json.jsp?cz=saveup";
        fileupload.setUploadUrl(url);
        
        fileupload.startUpload();
    }


</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>