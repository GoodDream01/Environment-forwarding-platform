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
		                    <td colspan="5" >    
		                        <input name="ztitle" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">会议：</td>
		                    <td style="width:300px;">    
		                    	<input id="zhyid" name="zhyid" textName="hyinfo.zhymc" class="mini-buttonedit" onbuttonclick="onHyButtonEdit" required="true" allowInput="false" style="width:665px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt" >录音合成内容：</td>
		                    <td colspan="5">    
		                        <input name="ztext" class="mini-textarea" required="true" maxLength="120" style="width: 665px;"/>
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
        
        var json = mini.encode(o);
        console.log(json);
        $.ajax({
            url: HOST_URL+"/hygl/lydhhc/save.do",
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

        }else if (data.action == "edit") {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);
			
            $.ajax({
            	url: HOST_URL+"/hygl/lydhhc/getById.do?id="+data.id,
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
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>