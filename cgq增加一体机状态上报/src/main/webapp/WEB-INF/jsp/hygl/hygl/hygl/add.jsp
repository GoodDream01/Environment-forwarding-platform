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
		                    <td class="addBt">会议名称：</td>
		                    <td colspan="5" >    
		                        <input name="zhymc" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt" style="width:100px;">年份：</td>
		                    <td style="width:150px;"> 
								<input name="zyear" class="mini-combobox" valueField="ZHIMC" textField="ZHIMC"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getZiDianZHI.do?ZDID=<%=ZiDianUtils.PUB_NDXX %>&px=desc"
								 required="true" 
								/>
		                    </td>
		                    <td class="addBt" style="width:100px;">界别：</td>
		                    <td style="width:150px;position: relative;"> 
								<input name="zcircles" class="mini-combobox" valueField="ZHIMC" textField="ZHIMC"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getZiDianZHI.do?ZDID=<%=ZiDianUtils.PUB_JIEBIEXX %>&px=desc"
								 required="true" 
								/>
		                    </td>
		                    <td class="addBt" style="width:100px;">类型：</td>
		                    <td style="width:150px;position: relative;">    
								<input name="ztype" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_HYLX %>"
								 required="true" 
								/>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="addBt" >会议议程：</td>
		                    <td colspan="5">    
		                        <input name="zhyyc" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		               
		                <tr>
		                    <td class="addBt" >开始时间：</td>
		                    <td >    
		                        <input name="zkssj" class="mini-datepicker" format="yyyy-MM-dd HH:mm:ss" valueType="string" showTime="true" allowInput="false" required="true" emptyText="请选择开始时间" />
		                    </td>
		                    <td class="addBt" >结束时间：</td>
		                    <td >    
		                        <input name="zjssj" class="mini-datepicker" format="yyyy-MM-dd HH:mm:ss" valueType="string" showTime="true" allowInput="false" required="true" emptyText="请选择开始时间" />
		                    </td>
		                    <td class="addBt" ></td>
		                    <td >    
		                        
		                    </td>
		                </tr>   
		                
		                <tr>
		                    <td class="addBt" >会议地点：</td>
		                    <td colspan="5">    
		                        <input name="zaddress" class="mini-textbox" required="true"  style="width: 665px;"/>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <td class="addBt" >签到类型：</td>
		                    <td >    
								<input name="zqdlx" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_QDLX %>"
								 required="true" 
								/>
		                    </td>
		                    <td class="addBt" >是否需要投影：</td>
		                    <td >    
								<input name="zisty" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
								 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_ISHASTY %>"
								 required="true" 
								/>
		                    </td>
		                    <td class="addBt" ></td>
		                    <td >    
		                        
		                    </td>
		                </tr> 
		                
		                <tr>
		                    <td class="addBt" >主办方：</td>
		                    <td >    
		                        <input name="zzbf" class="mini-textbox" required="true"  />
		                    </td>
		                    <td class="addBt" >主办方联系人：</td>
		                    <td >    
		                        <input name="zzbflxr" class="mini-textbox" required="true"  />
		                    </td>
		                    <td class="addBt" >主办方电话：</td>
		                    <td >    
		                        <input name="zzbflxdh" class="mini-textbox" required="true"  />
		                    </td>
		                </tr>     
		            </table>
		            <!-- ////////////////////////////////////////////////////////////// -->
	            </div>
	        </fieldset> 
	        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >会场信息</legend>
	            <div style="padding:5px;">
	            <!-- ////////////////////////////////////////////////////////////// -->
	            <div class="mini-fit" style="width:765px;height:200px;">
				    <div style="width:100%;">
					    <div class="mini-toolbar" style="padding:2px;">                
					        <a class="mini-button" iconCls="icon-add" plain="true" onclick="openHyhcdyWin">选择...</a>     
					    </div>
					</div>
				    <div id="hyhcdyGrid" class="mini-datagrid" style="width:100%;height:150px;"
				        allowResize="false" showPager="false" idField="id" 
				        allowCellEdit="true" allowCellSelect="true"
				    >
				        <div property="columns">
				        	<div width="30" type="indexcolumn"></div>
				            <div field="zhcmc" width="120" headerAlign="center" align="center" allowSort="false">会场名称</div>                          
				            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelHyhcdyRenderer"></div>
				            <div field="zhcid" visible="false" ></div>
				            <div field="zid" visible="false" ></div> 
				            <div field="zlrsj" visible="false" ></div>       
				        </div>
				    </div>
			    </div> 
	            <!-- ////////////////////////////////////////////////////////////// -->
	            </div>
	        </fieldset>  
	        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >共享资料</legend>
	            <div style="padding:5px;">
	            <!-- ////////////////////////////////////////////////////////////// -->
	            <div class="mini-fit" style="width:765px;height:200px;">
				    <div style="width:100%;">
					    <div class="mini-toolbar" style="padding:2px;">                
					        <a id="uploadBtn" class="mini-button" iconCls="icon-fujian" plain="true" onclick="openUploadWin">上传附件</a>     
					    </div>
					</div>
				    <div id="gxzlGrid" class="mini-datagrid" style="width:100%;height:150px;"
				        allowResize="false" showPager="false" idField="id" 
				        allowCellEdit="true" allowCellSelect="true"
				    >
				        <div property="columns">
				        	<div type="indexcolumn"></div>
				            <div field="zwjmc" width="120" headerAlign="center" align="center" allowSort="false">文件名</div>                          
				            <div field="zwjkzmlx" width="50" allowSort="false" headerAlign="center" align="center">类型</div>
				            <div field="zwjsize" width="50" allowSort="false" headerAlign="center" align="center" renderer="onSizeRenderer" >大小</div>                                    
				            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelGxzlRenderer"></div>
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
	        <fieldset style="border:solid 0px #aaa;padding:0px; margin: 0px;">
	            <div style="padding:0px;">
		            <table style="table-layout:fixed;" cellpadding="0" cellspacing="0">
		                <tr>
		                    <td valign="top">
		                    <!-- ======================================================================================== -->
					        <fieldset style="border:solid 1px #aaa;padding:3px;">
					            <legend >政协委员</legend>
					            <div style="padding:5px;">
					            <!-- ////////////////////////////////////////////////////////////// -->
					            <div class="mini-fit" style="width:365px;height:350px;">
								    <div style="width:100%;">
									    <div class="mini-toolbar" style="padding:2px;">                
									        <a class="mini-button" iconCls="icon-add" plain="true" onclick="openCommitteeWin">选择...</a>     
									    </div>
									</div>
								    <div id="committeeGrid" class="mini-datagrid" style="width:100%;height:300px;"
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
								            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelCommitteeRenderer"></div>
								            <div field="" ></div>
				    
								        </div>
								    </div>
								</div>
					            <!-- ////////////////////////////////////////////////////////////// -->
					            </div>
					        </fieldset> 
	        				<!-- ======================================================================================== -->
		                    </td>
		                    <td valign="top"> 
		                    <!-- ======================================================================================== -->   
					        <fieldset style="border:solid 1px #aaa;padding:3px;">
					            <legend >工作人员</legend>
					            <div style="padding:5px;">
					            <!-- ////////////////////////////////////////////////////////////// -->
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
								            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelCommitteeRenderer"></div>
								            <div field="" ></div>
				    
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
        
        //会场信息
        var hyhcdy = getHyhcdyData();
        o.hyhcdy = hyhcdy;

        //会议共享资料
        var hygxzl = getGxzlData();
        o.hygxzl = hygxzl;
        
        //政协委员
        var committee = getCommitteeData();
        o.committee = committee;
        
        //工作员
        var operator = getOperatorData();
        o.operator = operator;
        
        var json = mini.encode(o);
        console.log(json);
        $.ajax({
            url: HOST_URL+"/hygl/hygl/save.do",
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
                    form.setData(o);
                    
                    //填充会场信息
                    var hyhcdy = o.hyhcdy;
                    setHyhcdyData(hyhcdy);
                    
                    //填充会议共享资料
                    var hygxzl = o.hygxzl;
                    setGxzlData(hygxzl);
                    
                    //填充政协常委
                    var committee = o.committee;
                    setCommitteeData(committee);
                    
                    //填充工作人员
                    var operator = o.operator;
                    setOperatorData(operator);
                    

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

	////////////////////////////////////////////////会场信息：start////////////////////////////////////////////////////
    var hyhcdyGrid = mini.get("hyhcdyGrid");
    function openHyhcdyWin(){
		var title="会场信息选择";
        mini.open({
            url: HOST_PATH + "/hcgl/hcgl/selDuoUi.do",
            title: title, width: 700, height: 500,
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
            	var iframe = this.getIFrameEl();
            	var rows = GetGridAllRowData(hyhcdyGrid);
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
	                		
	                		o.zhcid = d.zid;
	                		o.zhcmc = d.zhcmc;
	                		
	                		temp.push(o);
	                		
	                	}
	                	hyhcdyGrid.clearRows();
	                	hyhcdyGrid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelHyhcdyRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"delHyhcdy('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function delHyhcdy(row_uid){
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
				    	var row = hyhcdyGrid.getRowByUID(row_uid);
				    	hyhcdyGrid.removeRow(row,false);
		       		}
   		});
    };
    function setHyhcdyData(data){
        if (data) {
        	hyhcdyGrid.addRows(data);	
        }
    }
    function getHyhcdyData(){
    	var temp = new Array();

       	var rows = GetGridAllRowData(hyhcdyGrid);
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.zhcid = row.zhcid;
       			o.zhcmc = row.zhcmc;
       			o.zlrsj = FormatLongDate(row.zlrsj);
       			
       			temp.push(o);
       		}
       	}
       
       return temp;

    }
	////////////////////////////////////////////////会场信息：end////////////////////////////////////////////////////
	
	////////////////////////////////////////////////共享资料：start////////////////////////////////////////////////////
    var gxzlGrid = mini.get("gxzlGrid");
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
	                	gxzlGrid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelGxzlRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"delGxzl('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function delGxzl(row_uid){
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
				    	var row = gxzlGrid.getRowByUID(row_uid);
				    	gxzlGrid.removeRow(row,false);
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
    
    function setGxzlData(data){
        if (data) {
        	gxzlGrid.addRows(data);	
        }
    }
    function getGxzlData(){
    	var temp = new Array();

       	var rows = GetGridAllRowData(gxzlGrid);
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

       	var rows = GetGridAllRowData(committeeGrid);
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
       			o.zxtfscs = row.zxtfscs;
       			
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

       	var rows = GetGridAllRowData(operatorGrid);
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
       			o.zxtfscs = row.zxtfscs;
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
	////////////////////////////////////////////////工作人员：end////////////////////////////////////////////////////


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>