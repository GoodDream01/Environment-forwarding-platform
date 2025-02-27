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
<script src="<%=request.getContextPath() %>/static/scripts/jquery.min.js?&<%=Math.random()%>" type="text/javascript"></script>
</head>
<body>
    <form id="form1" method="post">
        <input name="zid" class="mini-hidden" />
        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >调查问卷题目</legend>
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">标题：</td>
                    <td style="width:360px;">    
		                <input name="ztitle" class="mini-textbox" style="width:330px;" required="true" />
		                <input name="topicid" class="mini-textbox" style="width:330px;" required="true" visible="false"/>
                    </td>
                </tr>
                <tr>
                    <td >题目类型：</td>
                    <td >    
                    	<input name="ztype" class="mini-combobox" valueField="value" textField="text"  showNullItem="false"
						 url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_DCWJ_TMLX %>"
						 required="true" 
						/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">排序：</td>
                    <td style="width:360px;">    
		                <input name="zsort" class="mini-textbox" style="width:330px;" required="true" />
                    </td>
                </tr>

            </table>
            
        </div>
        </fieldset>
        <fieldset style="border:solid 1px #aaa;padding:3px;">
	            <legend >调查问卷选项</legend>

			<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="openUploadWin()" plain="true" tooltip="增加...">增加</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>

		    <div id="datagrid1" class="mini-datagrid" style="width:56%;height:300px;" 
		        allowResize="false" showPager="false" idField="id" 
		        allowCellEdit="true" allowCellSelect="true"
		    >
		        <div property="columns">
		        	<div type="indexcolumn" width="10"></div>
		            <div field="ztitle" width="120" headerAlign="center" align="center" allowSort="false">标题</div> 
		            <div field="zvotenum" width="120" headerAlign="center" align="center" allowSort="false" visible="false">投票数量</div>               
		            <div field="zsort" width="15" allowSort="false" align="center" headerAlign="center">排序
		            	<input property="editor" class="mini-textbox" style="width:100%;"/>
		            </div>  
		            <div field="" width="30" headerAlign="center" align="center" allowSort="false" renderer="onCzRenderer">操作</div>          
<!-- 		            <div field="type" width="50" allowSort="false" headerAlign="center" align="center">类型</div> -->
<!-- 		            <div field="size" width="50" allowSort="false" headerAlign="center" align="center" renderer="onSizeRenderer" >大小</div>                                     -->
<!-- 		            <div field="percent" width="120" headerAlign="center" align="center" allowSort="false" renderer="onPercentRenderer">进度</div> -->
<!-- 		            <div field="status" width="80" headerAlign="center" align="center" allowSort="false" renderer="onStatusRenderer">状态</div> -->
<!-- 		            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelRenderer"></div>    -->
<!-- 		            <div field="serUrl" visible="false"  width="100" headerAlign="center" allowSort="false">状态</div> -->
<!-- 		            <div field="id" visible="false"  width="100" headerAlign="center" allowSort="false">状态</div>          -->
		        </div>
		    </div> 
	    </fieldset> 
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
        </div>        
    </form>
   
    

    <script type="text/javascript">

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var form = new mini.Form("form1");
    
    //添加topicid和subtopcid

    function SaveData() {
        var o = form.getData(); 
        form.validate();
        if (form.isValid() == false) return;
        
        var item = getGxzlData();
        o.item = item;

        var json = mini.encode(o);
        
        $.ajax({
            url: HOST_URL+"/dcwj/dcwjsub/save.do",
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
            	url: HOST_URL+"/dcwj/dcwjsub/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    
                    var item = o.item;
                    setGxzlData(item);
                    
                    form.setChanged(false);
                    
                }
            });
        }else if(data.action == "new"){
        	data = mini.clone(data);
        	topicid = data.topicid;
        	mini.getbyName("topicid").setValue(topicid);
        }
    }
    
    function setGxzlData(data){
        if (data) {
        	gxzlGrid.addRows(data);	
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
    
    
    var gxzlGrid = mini.get("datagrid1");
    
    function openUploadWin(){
		var title="题目选项添加";
        mini.open({
            url: HOST_PATH + "/dcwj/dcwjitem/addUi.do",
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

                	var temp = new Array();
                    var o = {};
                		
                	o.ztitle = data.ztitle;
                	o.zsort = data.zsort;
                	o.zvotenum = data.zvotenum;

                		
                	temp.push(o);

                	gxzlGrid.addRows(temp);

            	}
            }
        });
    };
    
    function getGxzlData(){
    	var temp = new Array();

       	var rows = GetGridAllRowData(gxzlGrid);
       	if(rows && rows.length>0){
       		for(var i=0; i<rows.length; i++){
       			var row = rows[i];
       			var o = {};
       			
       			o.zid = row.zid;
       			o.ztitle = row.ztitle;
       			o.zsort = row.zsort;
       			o.zvotenum = row.zvotenum;
//        			o.zwjurl = row.zwjurl;
//        			o.zwjsize = row.zwjsize;
//        			o.zid = row.zid;
//        			o.zlrsj = FormatLongDate(row.zlrsj);
       			
       			temp.push(o);
       		}
       	}
       return temp;

    }
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"editsub()\">编辑</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delonesub()\">删除</a>";
        return html;
    }
    
    function editsub(){
        var row = gxzlGrid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/dcwj/dcwjitem/addUi.do",
                title: "调查问卷选项修改", width: '95%', height: '95%',
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.zid };
                    iframe.contentWindow.SetData(data);
                    
                },
                ondestroy: function (action) {
                	if (action == "ok") {
    	                var iframe = this.getIFrameEl();
    	                var data = iframe.contentWindow.GetData();
    	                data = mini.clone(data);    //必须

    	                //在这里判断zid是不是存在
    	                var isrow = gxzlGrid.findRow(function(row){
    	                	if(row.zid == data.zid) return true;
    	                });
    	                if(isrow){
    	                	gxzlGrid.updateRow(isrow,data);
    	                }else{
    	                
	                    	var temp = new Array();
	                        var o = {};
	                    		
	                    	o.ztitle = data.ztitle;
	                    	o.zsort = data.zsort;
	                    	o.zvotenum = data.zvotenum;
	
	                    		
	                    	temp.push(o);
	
	                    	gxzlGrid.addRows(temp);
    	                }

                	} 
                }
            });
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function delonesub(){
        var row = gxzlGrid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
        	gxzlGrid.removeRow(row,false);
	     } else {
	     	mini.alert("请选中一条记录", "提示");
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
    
    function ondayRenderer(e) {
    	
    	var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>