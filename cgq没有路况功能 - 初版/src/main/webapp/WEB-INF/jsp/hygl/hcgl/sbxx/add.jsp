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
            <table style="table-layout:fixed;">
             <tr>    <td class="addBt" style="width:100px;">设备类型：</td>
		                    <td style="width:150px;position: relative;">    
								<input name="zsblx" class="mini-combobox" valueField="value" textField="text"  required="true"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_SBLX%>"
								 required="true" 
								/>
		                    </td>
		                    </tr>
		        <tr>
                    <td >设备名称：</td>
                    <td >    
                        <input name="zsbmc" class="mini-textbox" style='width:200px;' required="true" allowInput="true" />
                    </td>
                </tr>             
                <tr>
                    <td >设备编号：</td>
                    <td >    
                        <input name="zsbbh" class="mini-textbox" style='width:200px;' required="true" allowInput="true" />
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

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var form = new mini.Form("form1");

    function SaveData() {
        var o = form.getData(); 
        
        form.validate();
        if (form.isValid() == false) return;

        var json = mini.encode(o);
        
        $.ajax({
            url: HOST_URL+"hcgl/sbxx/save.do",
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
            	url: HOST_URL+"/hcgl/sbxx/getById.do?id="+data.zid,
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
            	url: HOST_URL+"/hcgl/sbxx/getById.do?id="+data.zid,
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
    
	
    function changeTypeCode(e){
    	getSort();
    }
    
    function getSort(){
    	var typeCode = mini.getByName("typeCode").getValue();
    	var sortObj = mini.getByName("sort");
    	if(typeCode!=""){
            $.ajax({
                url: HOST_URL+"/hcgl/sbxx/getSort.do?typeCode="+typeCode,
    			type: 'post',
    		    dataType: 'html',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
                	sortObj.setValue(text);
                }
            });
    	}else{
    		sortObj.setValue(0);
    	}
    	

    }
    function onDrawDate(e) {
        var date = e.date;
        var d = new Date();

    }
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>