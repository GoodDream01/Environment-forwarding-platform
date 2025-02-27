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
        <input name="zhyid" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
	        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >基本信息</legend>
	            <div style="padding:5px;">
	            	<!-- ////////////////////////////////////////////////////////////// -->
		            <table style="table-layout:fixed;">
		            	<tr>
		                    <td class="addBt">姓名：</td>
		                    <td colspan="5" >    
		                        <input name="zryxm" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt">会议名称：</td>
		                    <td colspan="5" >    
		                        <input name="hyinfo.zhymc" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt" >短信内容：</td>
		                    <td colspan="5">    
		                        <input name="zdxnr" class="mini-textarea" required="true" maxLength="60" style="width: 665px;"/>
		                    </td>
		                </tr>
		                  
		            </table>
		            <!-- ////////////////////////////////////////////////////////////// -->
	            </div>
	        </fieldset> 


        </div>
        <div style="text-align:center;padding:10px;">               
<!--             <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>        -->
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
        
        //政协委员
        var committee = getCommitteeData();
        o.committee = committee;
        
        //工作员
        var operator = getOperatorData();
        o.operator = operator;
        
        if(committee.length==0 && operator.length==0){
        	mini.alert("请选择发送人员!","提示");
        	return;
        }
        
        var json = mini.encode(o);
        console.log(json);
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
            	url: HOST_URL+"/hygl/hydxtz/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
//                     mini.getByName("zhyid").setValue(o.zid);
//                     mini.getByName("zhymc").setValue(o.zhymc);
                    
                    //填充政协常委
//                     var committee = o.committee;
//                     setCommitteeData(committee);
                    
                    //填充工作人员
//                     var operator = o.operator;
//                     setOperatorData(operator);
                    

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

	
	////////////////////////////////////////////////政协委员：start////////////////////////////////////////////////////
    var committeeGrid = mini.get("committeeGrid");
    function openCommitteeWin(){
		var title="选择政协委员";
        mini.open({
            url: HOST_PATH + "/base/committee/selDuoUi.do",
            title: title, width: 700, height: 500,
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
            	var iframe = this.getIFrameEl();
            	var rows = GetGridAllRowData(committeeGrid);
            	iframe.contentWindow.SetData(rows);
            },
            ondestroy: function (action) {
            	if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = mini.clone(data);    //必须
	                if (data) {
	                	var temp = new Array();
	                	for(var i=0;i<data.length;i++){
	                		var d = data[i];
	                		var o = {};
	                		
	                		o.zryid = d.zcode;
	                		o.zryxm = d.zname;
	                		o.zrylx = "1";
	                		o.zsfzh = d.zsfzh;
	                		o.zdhhm = d.zmobiletel;
	                		
	                		temp.push(o);
	                		
	                	}
	                	committeeGrid.clearRows();
	                	committeeGrid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelCommitteeRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"delCommittee('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function delCommittee(row_uid){
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
				    	var row = committeeGrid.getRowByUID(row_uid);
				    	committeeGrid.removeRow(row,false);
		       		}
   		});
    };
    
    function setCommitteeData(data){
        if (data) {
        	committeeGrid.addRows(data);	
        }
    }
    function getCommitteeData(){
    	var temp = new Array();

       	var rows = committeeGrid.getSelecteds();
       	
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.zhyid = row.zhyid;
       			o.zryid = row.zryid;
       			o.zryxm = row.zryxm;
       			o.zrylx = row.zrylx;
       			o.zsfzh = row.zsfzh;
       			o.zdhhm = row.zdhhm;
       			o.zisdxtz = row.zisdxtz;
       			o.zdxtzsj = FormatLongDate(row.zdxtzsj);
       			o.zdxfscs = row.zdxfscs;
       			o.zlrsj = FormatLongDate(row.zlrsj);
       			o.zisxttz = row.zisxttz;
       			o.zxttzsj = FormatLongDate(row.zxttzsj);
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
	////////////////////////////////////////////////政协委员：end////////////////////////////////////////////////////

	////////////////////////////////////////////////工作人员：start////////////////////////////////////////////////////
    var operatorGrid = mini.get("operatorGrid");
    function openOperatorWin(){
		var title="选择工作人员";
        mini.open({
            url: HOST_PATH + "/base/operator/selDuoUi.do",
            title: title, width: 700, height: 500,
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
            	var iframe = this.getIFrameEl();
            	var rows = GetGridAllRowData(operatorGrid);
            	iframe.contentWindow.SetData(rows);
            },
            ondestroy: function (action) {
            	if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = mini.clone(data);    //必须
	                if (data) {
	                	var temp = new Array();
	                	for(var i=0;i<data.length;i++){
	                		var d = data[i];
	                		var o = {};
	                		
	                		o.zryid = d.zcode;
	                		o.zryxm = d.zname;
	                		o.zrylx = "2";
	                		o.zsfzh = d.zsfzh;
	                		o.zdhhm = d.zmobiletel;
	                		
	                		temp.push(o);
	                		
	                	}
	                	operatorGrid.clearRows();
	                	operatorGrid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelOperatorRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"delOperator('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function delOperator(row_uid){
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
				    	var row = operatorGrid.getRowByUID(row_uid);
				    	operatorGrid.removeRow(row,false);
		       		}
   		});
    };
    
    function setOperatorData(data){
        if (data) {
        	operatorGrid.addRows(data);	
        }
    }
    function getOperatorData(){
    	var temp = new Array();

       	var rows = operatorGrid.getSelecteds();
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.zhyid = row.zhyid;
       			o.zryid = row.zryid;
       			o.zryxm = row.zryxm;
       			o.zrylx = row.zrylx;
       			o.zsfzh = row.zsfzh;
       			o.zdhhm = row.zdhhm;
       			o.zisdxtz = row.zisdxtz;
       			o.zdxtzsj = FormatLongDate(row.zdxtzsj);
       			o.zdxfscs = row.zdxfscs;
       			o.zlrsj = FormatLongDate(row.zlrsj);
       			o.zisxttz = row.zisxttz;
       			o.zxttzsj = FormatLongDate(row.zxttzsj);
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
	////////////////////////////////////////////////工作人员：end////////////////////////////////////////////////////

	function isdxtzChange(){
		var obj = this;
		if(obj.getValue()=="1"){
			mini.getByName("zdxnr").required=true;
		}else{
			mini.getByName("zdxnr").required=false;
		}
	}
	
	function isxttzChange(){
		var obj = this;
		if(obj.getValue()=="1"){
			mini.getByName("ztznr").required=true;
		}else{
			mini.getByName("ztznr").required=false;
		}
	}
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>