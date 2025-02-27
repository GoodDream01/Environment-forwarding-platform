<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		    </div>
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">增加</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="treegrid1" class="mini-treegrid"  style="width:100%;height:100%;"  allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/area/getList.do" idField="id" multiSelect="true" 
			  treeColumn="name"  expandOnLoad="true" showTreeIcon="true" parentField="pid" resultAsTree="false" fitColumns="false">
		        <div property="columns">
     				<div type="indexcolumn" width="50" headerAlign="center" align="center">序号</div>
		            <div name="name" field="name" width="300" headerAlign="center" align="left" allowSort="true">名称</div>
		            <div field="bz" width="200" headerAlign="center" align="left" allowSort="true">备注</div>	
		            <div field="" width="330" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>       
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    <script type="text/javascript">

    function search() {
       var o={};
       grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}
    
	function add() {
		var title="添加";
        var row = grid.getSelected(); 
        if(row){
	        mini.open({
	            url: HOST_PATH + "/cgq/area/addUi.do",
	            title: title, width: 500, height: 260,
	            loadOnRefresh: true,
	            showMaxButton: true, 
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = { action: "new", id: row.id};
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                grid.reload();
	            }
	        });
        }else{
        	 mini.open({
 	            url: HOST_PATH + "/cgq/area/addUi.do",
 	            title: title, width: 500, height: 260,
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
    }
	
    function edit() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/area/addUi.do",
                title: "修改", width: 500, height: 260,
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
    
    function deloneDo(){
        var row = grid.getSelected();
        if (row) {
            $.ajax({
                url: HOST_URL+"/cgq/area/delete.do?id="+row.id,
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
    	html += "<a class=\"mini-button\" style=\"width:140px;\" onclick=\"add()\">添加下级信息</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"edit()\">编辑</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delone()\">删除</a>";
        return html;
    }
   
   	mini.parse();
 	var tree = mini.get("leftTree");
 	
    var grid = mini.get("treegrid1");
    grid.load();   

    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>