<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %> 
<%
	String bj = request.getParameter("bj");
%>   
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
		        <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		        <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		    </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/dxjl/getList.do?bj=<%=bj %>" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="sbbh" width="100" headerAlign="center" align="center">设备编号</div>        
		            <div field="time" width="150" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>
 					<div field="fsrxm" width="150" headerAlign="center" align="center">负责人姓名</div>        
		            <div field="fsrdh" width="150" headerAlign="center" align="center">负责人电话</div>
		            <%if(bj.equals("0")){%>
		            <div field="fszt" width="150" headerAlign="center" align="center">发送状态</div>  
		            <%}%>
		            <div field="ycyy" width="500" headerAlign="center" align="center">异常原因</div>        
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
       grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}
    
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>