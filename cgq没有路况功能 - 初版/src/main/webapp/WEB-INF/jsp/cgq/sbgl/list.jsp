<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		    	<label>所属区域：</label>
		    	<input id="ssid" name="ssid" class="mini-treeselect" url="<%=request.getContextPath() %>/cgq/area/getList.do"
    				   textField="name" valueField="id" parentField="pid" checkRecursive="true" 
    				   showFolderCheckBox="false" expandOnLoad="true" showClose="true" oncloseclick="onCloseClick"
                       popupWidth="200" />
		    	<label>名称：</label>
                <input name="mc" class="mini-textbox"/>
                <label>地址：</label>
                <input name="dz" class="mini-textbox"/>
		        <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		        <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		    </div>
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">增加</a>
	                        <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true" tooltip="修改...">修改</a>
	                        <a class="mini-button" iconCls="icon-search" onclick="view()" plain="true" tooltip="查看...">查看</a>
	                        <a class="mini-button" iconCls="icon-delete" onclick="delone()" plain="true" tooltip="删除...">删除</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/sbgl/getList.do" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="sbbh" width="100" headerAlign="center" align="center">编号</div>
		            <div field="mc" width="300" headerAlign="center" align="center">名称</div>        
		            <div field="jd" width="120" headerAlign="center" align="center">经度</div> 
		            <div field="wd" width="120" headerAlign="center" align="center">纬度</div>
		            <div field="fzrxm" width="150" headerAlign="center" align="center">负责人</div>
		            <div field="qymc" width="150" headerAlign="center" align="center">所属</div>
					<div field="fzmc" width="150" headerAlign="center" align="center">分组</div>
		            <div field="dz" width="200" headerAlign="center" align="center">地址</div>
		            <div field="" width="200" headerAlign="center" align="center" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
<script type="text/javascript">

	mini.parse();
	var grid = mini.get("datagrid1");
	grid.load(); 
    
    function search() {
       var o={};
       o.ssid = mini.getByName("ssid").getValue();
       o.mc = mini.getByName("mc").getValue();
       o.dz = mini.getByName("dz").getValue();
       grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}
    
	function add() {
		mini.open({
    		url: HOST_PATH + "/cgq/sbgl/addUi.do",
	        title: "增加", width: 500, height: 400,
	        loadOnRefresh: true,
	        showMaxButton: true, 
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = { action: "new"};
	            iframe.contentWindow.SetData(data);
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
	   });
    }
	
    function edit() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/sbgl/addUi.do",
                title: "修改", width: 500, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.id };
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            });
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function view() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/sbgl/addUi.do",
                title: "查看", width: 500, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "view", id: row.id };
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            });
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function spxx() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/sbgl/spUi.do?id="+row.id,
                title: "视频信息", width: 500, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            });
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function deloneDo(){
        var row = grid.getSelected();
        if (row) {
            $.ajax({
                url: HOST_URL+"/cgq/sbgl/delete.do?id="+row.id,
    			type: 'post',
    		    dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
                    var success = text.success;
                    if(success){
                    	alert("删除成功！");
                    	grid.reload(); 
                    }else{
                    	alert("删除失败！");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function delone() {
		mini.confirm("是否要删除？", "确定？",
	       	 function (action) {
	       		if(action == "ok"){
	       			deloneDo();
	       		}
		});
    }
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"edit()\">编辑</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"view()\">查看</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delone()\">删除</a>";
        return html;
    }
   
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>