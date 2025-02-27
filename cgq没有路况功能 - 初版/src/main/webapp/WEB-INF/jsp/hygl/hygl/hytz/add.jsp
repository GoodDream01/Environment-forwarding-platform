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
        <input name="id" class="mini-hidden" type="hidden"/>
        <input name="sort" class="mini-hidden" type="hidden"/>
 
        <div style="padding-left:11px;padding-bottom:5px;">
           <table style="table-layout:fixed;" cellpadding="0" cellspacing="0">
               <tr>
                   <td valign="top" width="200">

			        </td>
			        <td valign="top">    
			        <fieldset style="border:solid 1px #aaa;padding:3px;">
			            <legend >信息模板内容设置</legend>
			            <div style="padding:5px;">
			            	<!-- ////////////////////////////////////////////////////////////// -->
				            <table style="table-layout:fixed;">
				                <tr>
				                    <td class="addBt">内容模板：</td>
				                    <td colspan="5" >    
										<input name="dxmb" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
										 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=101004" onvaluechanged="ChangeMb"
										 required="true"  style="width:460px;" emptyText="请选择内容模板..."
										/>
				                    </td>
				                </tr>

				                <tr>
				                    <td class="addBt" >通知内容：</td>
				                    <td colspan="5">    
				                        <input name="text" class="mini-textarea" required="true" maxLength="70" style="width: 465px;"/>
				                    </td>
				                </tr>
				                  
				            </table>
				            <!-- ////////////////////////////////////////////////////////////// -->
			            </div>
			        </fieldset> 
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
    
    function SaveData2() {
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
	
    function SaveData() {
        var o = form.getData(); 
        
        form.validate();
        if (form.isValid() == false) return;
        
        //政协委员
        var committee = getCommitteeData();
        o.committee = committee;
        
        if(committee.length==0){
        	//mini.alert("请选择发送单位!","提示");
        	//return;
        }
        
        var json = mini.encode(o);

        $.ajax({
            url: HOST_URL+"/hygl/hytz/save.do",
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
            	url: HOST_URL+"/user/getById.do?id="+data.id,
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
            	url: HOST_URL+"/hygl/hygl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    //form.setData(o);
                    mini.getByName("zhyid").setValue(o.zid);
                    mini.getByName("zhymc").setValue(o.zhymc);
        

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
	     SaveData2();
	 }
	 function onCancel(e) {
	     CloseWindow("close");
	 }


	
	function ChangeMb(e){
		var obj = this;
		var selected = e.selected;
		var v = obj.getText();
		var id = selected.id;
		var sort = selected.sort;
		//mini.getByName("zdxnr").setValue(v);
		mini.getByName("text").setValue(v);
		mini.getByName("id").setValue(id);
		mini.getByName("sort").setValue(sort);
		//mini.getByName("ztznr2").setValue(v);
	}
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>