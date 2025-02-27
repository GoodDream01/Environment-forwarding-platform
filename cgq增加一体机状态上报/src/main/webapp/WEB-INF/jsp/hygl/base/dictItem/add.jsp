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
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">字典类型：</td>
                    <td style="width:150px;">    
		                <input name="typeCode" class="mini-combobox" valueField="code" textField="text"  showNullItem="true"
		                   url="<%=request.getContextPath() %>/common/getDictType.do"
		                   required="true"  onvaluechanged="changeTypeCode"
		                />
                    </td>
                </tr>
                <tr>
                    <td >字典值：</td>
                    <td >    
                        <input name="value" class="mini-textbox" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >字典文本：</td>
                    <td >    
                        <input name="text" class="mini-textbox" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >排序号：</td>
                    <td >    
                        <input name="sort" class="mini-spinner" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >状态：</td>
                    <td >    
		                <input name="status" class="mini-combobox" valueField="code" textField="text"  showNullItem="true"
		                   data="[{code:'0',text:'显示'},{code:'1',text:'不显'}]" value="0"
		                   required="true" 
		                />
                    </td>
                </tr>
                <tr>
                    <td >备注信息：</td>
                    <td >    
                        <input name="bz" class="mini-textbox" required="false" />
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
            url: HOST_URL+"/base/dictItem/save.do",
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
            	url: HOST_URL+"/base/dictItem/getById.do?id="+data.id,
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
            	url: HOST_URL+"/base/dictItem/getById.do?id="+data.id,
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
                url: HOST_URL+"/base/dictItem/getSort.do?typeCode="+typeCode,
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
    
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>