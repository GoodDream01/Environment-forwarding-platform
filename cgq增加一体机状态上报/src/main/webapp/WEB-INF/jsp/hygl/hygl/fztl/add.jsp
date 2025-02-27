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
        
        <!-- 基本信息  开始 -->
        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >基本信息</legend>
	            <div style="padding:5px;">
            <table style="table-layout:fixed;">
            	<tr>
                    <td class="addBt">会议：</td>
                    <td style="width:300px;">    
                    	<input id="zhyid" name="zhyid" textName="hyinfo.zhymc" class="mini-buttonedit" onbuttonclick="onHyButtonEdit" required="true" allowInput="false" style="width:300px;"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">分组名称：</td>
                    <td style="width:360px;">    
		                <input name="zfzmc" class="mini-textbox" style="width:330px;" required="true" />
<!-- 		                <input name="zfzmc" class="mini-textbox" style="width:330px;" required="true" onvalidation="checkByname" /> -->
                    </td>
                </tr>
                                <tr>
                    <td style="width:80px;">讨论议题：</td>
                    <td style="width:360px;">    
		                <input name="ztlyt" class="mini-textarea" style="width:330px;" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >排序号：</td>
                    <td >    
                        <input name="zsort" class="mini-spinner" required="true" />
                    </td>
                </tr>
            </table>
            </div>
            </fieldset>
           <!-- 基本信息  结束 -->
           
           <!-- 讨论人员   开始 -->
           <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >小组人员</legend>
	            <div style="padding:5px;">
	            
	            <div class="mini-fit" style="width:365px;height:350px;">
								    <div style="width:100%;">
									    <div class="mini-toolbar" style="padding:2px;">                
									        <a class="mini-button" iconCls="icon-add" plain="true" onclick="openOperatorWin">选择...</a>     
									    </div>
									</div>
								    <div id="operatorGrid" class="mini-datagrid" style="width:100%;height:300px;"
								        allowResize="false" showPager="false" idField="id" 
								        allowCellEdit="true" allowCellSelect="true"
								    >
								        <div property="columns">
								        	<div type="indexcolumn" width="30"></div>
								            <div field="zid" visible="false" ></div> 
								            <div field="zhyid" visible="false" ></div> 
								            <div field="zryid" visible="false" ></div>
								            <div field="zrylx" visible="false" ></div>  
								            <div field="zsfzh" visible="false" ></div>
								            <div field="zdhhm" visible="false" ></div> 
								            <div field="zisdxtz" visible="false" ></div>  
								            <div field="zdxtzsj" visible="false" ></div>  
								            <div field="zdxfscs" visible="false" ></div>  
								            <div field="zlrsj" visible="false" ></div>
								            <div field="zisxttz" visible="false" ></div> 
								            <div field="zxttzsj" visible="false" ></div> 
								            <div field="zxtfscs" visible="false" ></div>
											<div field="zisqd" visible="false" ></div> 
											<div field="zqdsj" visible="false" ></div>     
								            
								            <div field="zryxm" width="50" headerAlign="center" align="center" allowSort="false">姓名</div>                                                            
								            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelOperatorRenderer"></div>
<!-- 								            <div field="" ></div> -->
				    
								        </div>
								    </div>
								</div>
	            
	            </div>
            </fieldset> 
           <!-- 讨论人员   结束 --> 
            
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
        
        var hyfzry = getOperatorData();
        o.hyfzry = hyfzry;
        
        form.validate();
        if (form.isValid() == false) return;

        var json = mini.encode(o);
        
        
        
        $.ajax({
            url: HOST_URL+"/hygl/fztl/save.do",
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
//             	alert(text);
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
            	url: HOST_URL+"/gggl/xtgg/getById.do?id="+data.id,
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
            	url: HOST_URL+"/hygl/fztl/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    
                    var hyfzry = o.hyfzry;
                    setHyfzryData(hyfzry);
                    
                    form.setChanged(false);
                    
                }
            });
        }else if(data.action == "new"){
        	getSort();
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
    	var typeCode = mini.getByName("zsort").getValue();
    	
    	var sortObj = mini.getByName("zsort");
    	if(typeCode =="" || typeCode =="0"){
            $.ajax({
                url: HOST_URL+"/gggl/xtgg/getSort.do",
    			type: 'post',
    		    dataType: 'html',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
               		sortObj.setValue(Number(text)+1);
                }
            });
    	}else{
    		sortObj.setValue(0);
    	}
    	

    }
    
    function checkByname(e){
//     	alert("getByName");
    	if (e.isValid) {

            e.errorText = "正在后台验证用户名是否重复...";
            e.isValid = false;
            
            var username = mini.getByName("ztitle").getValue();
            var url = "gggl/xtgg/checkTitle.do?ztitle="+username+"";
            if(czlx=="edit"){
            	var zid = mini.getByName("zid").getValue();
            	url = "user/checkTitlenameAndZid.do?username="+username+"&zid="+zid+"";
            }
            
            $.ajax({
            	url: HOST_URL+"/"+url,
                cache: false,
                async: false,
                dataType: "json",
                success: function (o) {
                	
                	if(o){
                    	if(o.flag){
                    		alert(123321);
                            e.errorText = "标题重复，请重新填写！";
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
    //////////////////////分割线//////////////////////////////
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
    
////////////////////////////////////////////////工作人员：start////////////////////////////////////////////////////
	    var operatorGrid = mini.get("operatorGrid");
	    function openOperatorWin(){
			var title="选择小组人员";
	        mini.open({
	            url: HOST_PATH + "/hygl/fztl/selDuoUi.do",
	            title: title, width: 700, height: 500,
	            loadOnRefresh: true,
	            showMaxButton: true, 
	            onload: function () {
	            	var iframe = this.getIFrameEl();
	            	var rows = GetGridAllRowData(operatorGrid);
	            	var zhyid = mini.get("zhyid").getValue();
	            	
	            	iframe.contentWindow.SetData(rows,zhyid);
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
		                		o.zryid = d.zryid;
		                		o.zryxm = d.zryxm;
		                		o.zrylx = "5";
		                		o.zsfzh = d.zsfzh;
		                		o.zdhhm = d.zmobiletel;
// 		                		o.zrylx = d.zrylx;
		                		
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

	       	var rows = GetGridAllRowData(operatorGrid);
	       	if(rows && rows.length>0){
	       		for(var i=0; i<rows.length; i++){
	       			var row = rows[i];
	       			var o = {};
	       			
	       			o.zid = row.zid;
// 	       			o.zhyid = row.zhyid;
	       			o.zryid = row.zryid;
	       			o.zryxm = row.zryxm;
	       			o.zrylx = row.zrylx;
// 	       			o.zsfzh = row.zsfzh;
// 	       			o.zdhhm = row.zdhhm;
// 	       			o.zisdxtz = row.zisdxtz;
// 	       			o.zdxtzsj = FormatLongDate(row.zdxtzsj);
// 	       			o.zdxfscs = row.zdxfscs;
// 	       			o.zlrsj = FormatLongDate(row.zlrsj);
// 	       			o.zisxttz = row.zisxttz;
// 	       			o.zxttzsj = FormatLongDate(row.zxttzsj);
	       			
	       			temp.push(o);
	       		}
	       	}
	       return temp;

	    }
	    
	    function setHyfzryData(data){
	        if (data) {
	        	operatorGrid.addRows(data);	
	        }
	    }
		////////////////////////////////////////////////工作人员：end////////////////////////////////////////////////////
    
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>