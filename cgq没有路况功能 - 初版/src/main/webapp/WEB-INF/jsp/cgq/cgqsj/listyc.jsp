<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.common.utils.ComUtils"%>
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
                <input name="kssj" value="<%=ComUtils.getTime(6) %>" class="mini-datepicker"/>~<input name="jssj" value="<%=ComUtils.getTime(1) %>"class="mini-datepicker"/>
		        <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		        <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		    </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/cgqsj/getList.do?yc=1" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20" sortMode="client">
		       <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="sbbh" width="100" headerAlign="center" align="center" allowSort="true">设备编号</div>        
		            <div field="name" width="300" headerAlign="center" align="center" allowSort="true">设备名称</div> 
		            <div field="xtsj" dataType="date" width="150" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>
		            <div field="pjz" dataType="float" width="120" headerAlign="center" align="center" allowSort="true" renderer="getFLDJ">平均值（m/s）</div> 
		            <%if(!USERINFO_ROLE.equals("2") && !USERINFO_ROLE.equals("4")){%>
		            <div field="dqz" dataType="float" width="120" headerAlign="center" align="center" allowSort="true" renderer="getFLDJ">当前值（m/s）</div>  
		            <div field="zdz" dataType="float" width="150" headerAlign="center" align="center" allowSort="true" renderer="getFLDJ">小时最大值（m/s）</div>        
		            <div field="zxz" dataType="float" width="150" headerAlign="center" align="center" allowSort="true" renderer="getFLDJ">小时最小值（m/s）</div> 
		            <div field="dqd" dataType="float" width="120" headerAlign="center" align="center" allowSort="true">当前值（°）</div> 
		            <%}%>     
		            <div field="pjd" dataType="float" width="120" headerAlign="center" align="center" allowSort="true">平均值（°）</div> 
		            <div field="tpbm" width="100" headerAlign="center" align="center" renderer="onCzRenderer">查看</div> 
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
    
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>