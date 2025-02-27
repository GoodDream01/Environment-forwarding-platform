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
        <input name="zhyid" class="mini-hidden" type="hidden"/>
        <div style="padding-left:11px;padding-bottom:5px;">
           <table style="table-layout:fixed;" cellpadding="0" cellspacing="0">
               <tr>
                   <td valign="top" width="200">
			        <fieldset style="border:solid 0px #aaa;padding:0px; margin: 0px;">
			            <div style="padding:0px;">
				            <table style="table-layout:fixed;" cellpadding="0" cellspacing="0">
				                <tr>
				                    <td valign="top">
				                    <!-- ======================================================================================== -->
							        <fieldset style="border:solid 1px #aaa;padding:3px;display:none;">
							            <legend >政府单位</legend>
							            <div style="padding:5px;">
							            <!-- ////////////////////////////////////////////////////////////// -->
							            <div class="mini-fit" style="width:178px;height:272px;">
							            	<!--
										    <div style="width:100%;">
											    <div class="mini-toolbar" style="padding:2px;">                
		 									        <a class="mini-button" iconCls="icon-add" plain="true" onclick="openCommitteeWin">选择...</a>     
											    </div>
											</div>
											 -->
										    <div id="committeeGrid" class="mini-datagrid" style="width:100%;height:270px;"
										        allowResize="false" showPager="false" idField="zid" 
										        url="<%=request.getContextPath() %>/jcsj/cbdw/getList.do?isLimit=false"
										        allowCellEdit="true" allowCellSelect="true" multiSelect="true"
										    >
										        <div property="columns">
										        	<div type="indexcolumn" width="30"></div>
										        	<div field="" width="30" type="checkcolumn" ></div>
										            <div field="ZCODE" visible="false" ></div>  
										            
										            <div field="ZNAME" width="90" headerAlign="center" align="center" allowSort="false">单位</div>
										            <div field="ZTEL" width="100" headerAlign="center" align="center" allowSort="false" visible="false">电话号码</div>                                                            
										            
						    
										        </div>
										    </div>
										</div>
							            <!-- ////////////////////////////////////////////////////////////// -->
							            </div>
							        </fieldset> 
			        				<!-- ======================================================================================== -->
				                    </td>
				                </tr>
				           </table>     
			            </div>
			        </fieldset>
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
				                <%-- <tr>
				                    <td class="addBt" >是否发送短信：</td>
				                    <td colspan="5">    
										<div name="zisdxtz" class="mini-radiobuttonlist" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
										    textField="text" valueField="value" value="1" onvaluechanged="isdxtzChange"
										    url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_ISDXTZ %>" >
										</div>
				                    </td>
				                </tr> --%>
				                <tr>
				                    <td class="addBt" >短信通知内容：</td>
				                    <td colspan="5">    
				                        <input name="zdxnr" class="mini-textarea" required="true" maxLength="60" style="width: 465px;"/>
				                    </td>
				                </tr>
				                <%-- <tr>
				                    <td class="addBt" >是否发送系统通知：</td>
				                    <td colspan="5">    
										<div name="zisxttz" class="mini-radiobuttonlist" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
										    textField="text" valueField="value" value="1" onvaluechanged="isxttzChange"
										    url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_ISDXTZ %>" >
										</div>
				                    </td>
				                </tr> --%>
				                <tr>
				                    <td class="addBt" >系统通知内容：</td>
				                    <td colspan="5">    
				                        <input name="ztznr" class="mini-textarea" required="true" maxLength="70" style="width: 465px;"/>
				                    </td>
				                </tr>
				                <%-- <tr>
				                    <td class="addBt" >是否发送微信通知：</td>
				                    <td colspan="5">    
										<div name="zisxttz2" class="mini-radiobuttonlist" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
										    textField="text" valueField="value" value="1" onvaluechanged="isxttzChange"
										    url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_ISDXTZ %>" >
										</div>
				                    </td>
				                </tr> --%>
				                <!-- <tr>
				                    <td class="addBt" >微信通知内容：</td>
				                    <td colspan="5">    
				                        <input name="ztznr2" class="mini-textarea" required="true" maxLength="70" style="width: 465px;"/>
				                    </td>
				                </tr> -->
				                  
				            </table>
				            <!-- ////////////////////////////////////////////////////////////// -->
			            </div>
			        </fieldset> 
			        </td>
		        </tr>
	        </table>
	        <fieldset style="border:solid 1px #aaa;padding:3px;display:none;">
	            <legend >其他资料</legend>
	            <div style="padding:5px;">
	            <!-- ////////////////////////////////////////////////////////////// -->
	            <div class="mini-fit" style="width:765px;height:200px;">
				    <div style="width:100%;">
					    <div class="mini-toolbar" style="padding:2px;">                
					        <a id="uploadBtn" class="mini-button" iconCls="icon-fujian" plain="true" onclick="openUploadWin">上传附件</a>     
					    </div>
					</div>
				    <div id="qtzlGrid" class="mini-datagrid" style="width:100%;height:150px;"
				        allowResize="false" showPager="false" idField="id" 
				        allowCellEdit="true" allowCellSelect="true"
				    >
				        <div property="columns">
				        	<div type="indexcolumn"></div>
				            <div field="zwjmc" width="120" headerAlign="center" align="center" allowSort="false">文件名</div>                          
				            <div field="zwjkzmlx" width="50" allowSort="false" headerAlign="center" align="center">类型</div>
				            <div field="zwjsize" width="50" allowSort="false" headerAlign="center" align="center" renderer="onSizeRenderer" >大小</div>                                    
				            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelQtzlRenderer"></div>
				            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDownloadRenderer"></div>   
				            <div field="zwjurl" visible="false"  width="100" headerAlign="center" allowSort="false">文件URL</div>
				            <div field="zid" visible="false" ></div> 
				            <div field="zlrsj" visible="false" ></div>       
				        </div>
				    </div>
			    </div> 
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
	     SaveData();
	 }
	 function onCancel(e) {
	     CloseWindow("close");
	 }


	////////////////////////////////////////////////共享资料：start////////////////////////////////////////////////////
    var qtzlGrid = mini.get("qtzlGrid");
    function openUploadWin(){
		var title="附件上传";
        mini.open({
            url: HOST_PATH + "/common/upload/multFileUpload.do",
            title: title, width: 700, height: 500,
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
                
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
	                		
	                		o.zwjmc = d.name;
	                		o.zwjkzmlx = d.type;
	                		o.zwjsize = FormatFileSize(d.size);
	                		o.zwjurl = d.serUrl;
	                		
	                		temp.push(o);
	                		
	                	}
	                	qtzlGrid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelQtzlRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"delQtzl('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function delQtzl(row_uid){
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
				    	var row = qtzlGrid.getRowByUID(row_uid);
				    	qtzlGrid.removeRow(row,false);
		       		}
   		});
    };
    
    function onDownloadRenderer(e){
    	var fileUrl = e.record.zwjurl;
    	var fileName = e.record.zwjmc;
    	var html = "";
    	    html+= "<span class=\"icon-download\" onclick=\"DownloadFile('"+fileUrl+"','"+fileName+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    
    function setQtzlData(data){
        if (data) {
        	qtzlGrid.addRows(data);	
        }
    }
    function getQtzlData(){
    	var temp = new Array();

       	var rows = GetGridAllRowData(qtzlGrid);
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.zwjmc = row.zwjmc;
       			o.zwjkzmlx = row.zwjkzmlx;
       			o.zwjurl = row.zwjurl;
       			o.zwjsize = row.zwjsize;
       			o.zid = row.zid;
       			o.zlrsj = FormatLongDate(row.zlrsj);
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
	////////////////////////////////////////////////共享资料：end////////////////////////////////////////////////////
	
	////////////////////////////////////////////////政协委员：start////////////////////////////////////////////////////
    var committeeGrid = mini.get("committeeGrid");
    committeeGrid.load();

    function getCommitteeData(){
    	var temp = new Array();

       	var rows = committeeGrid.getSelecteds();
       	
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.zhyid = "";
       			o.zryid = row.zcode;
       			o.zryxm = row.zname;
       			o.zrylx = "2";
       			o.zsfzh = "";
       			o.zdhhm = row.ztel;
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
	////////////////////////////////////////////////政协委员：end////////////////////////////////////////////////////


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
	function ChangeMb(e){
		var obj = this;
		var v = obj.getText();
		mini.getByName("zdxnr").setValue(v);
		mini.getByName("ztznr").setValue(v);
		mini.getByName("ztznr2").setValue(v);
	}
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>