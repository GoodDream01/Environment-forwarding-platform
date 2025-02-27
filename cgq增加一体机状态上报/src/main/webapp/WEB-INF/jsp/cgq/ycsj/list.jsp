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
		    	<label>设备编号：</label>
                <input name="sbbh" class="mini-textbox"/>
                <label>时间：</label>
                <input name="kssj" class="mini-datepicker"/>~<input name="jssj" class="mini-datepicker"/>
		        <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		        <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		    </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/ycsj/getList.do" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20" sortMode="client">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="sbbh" width="100" headerAlign="center" align="center" allowSort="true">设备编号</div>        
		            <div field="mc" width="300" headerAlign="center" align="center" allowSort="true">设备名称</div> 
		            <div field="xtsj" dataType="date" width="200" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>
		            <div field="" width="100" headerAlign="center" align="center" renderer="onCzRenderer">操作</div> 
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
       var o = {};
       o.ssid = mini.getByName("ssid").getValue();
       o.sbbh = mini.getByName("sbbh").getValue();
       o.kssj = mini.getByName("kssj").getFormValue();
       o.jssj = mini.getByName("jssj").getFormValue();
       grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"view()\">视频</a>";
        return html;
    }
    
    function view() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/ycsj/addUi.do?id="+row.id,
                title: "视频", width: 800, height: 600,
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
    
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>