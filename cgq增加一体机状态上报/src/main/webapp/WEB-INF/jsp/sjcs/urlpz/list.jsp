<%@ page import="com.dt.zxhygl.enums.DictTypeEnum" %>
<%@ page import="com.dt.zxhygl.utils.DictUtils" %>
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
		      url="<%=request.getContextPath() %>/sjcs/urlpz/getList.do" idField="id" multiSelect="true"
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
					<div field="dwmc" width="150" headerAlign="center" align="center">单位</div>
		            <div field="url" width="400" headerAlign="center" align="center">url</div>
					<div field="msg" width="100" headerAlign="center" align="center">返回字段</div>
					<div field="bj" width="100" headerAlign="center" align="center" renderer="onBjRenderer">通道配置</div>
		            <div field="sbbh" width="200" headerAlign="center" align="center">设备编号</div>
		            <div field="zdmc" width="500" headerAlign="center" align="center">字段名称</div>
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
       grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}

	var Bjs = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_USER_YHZT.toString())%>;
	function onBjRenderer(e){
		return Bjs[e.value];
	}

	function add() {
		mini.open({
    		url: HOST_PATH + "/sjcs/urlpz/addUi.do",
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
            	url: HOST_PATH + "/sjcs/urlpz/addUi.do",
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
            	url: HOST_PATH + "/sjcs/urlpz/addUi.do",
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

    function deloneDo(){
        var row = grid.getSelected();
        if (row) {
            $.ajax({
                url: HOST_URL+"/sjcs/urlpz/delete.do?id="+row.id,
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
   
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>