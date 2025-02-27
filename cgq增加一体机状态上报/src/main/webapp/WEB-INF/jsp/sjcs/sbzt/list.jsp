<%@ page import="com.dt.common.utils.ComUtils" %>
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
				<label>设备编号：</label>
				<input name="sbid" class="mini-textbox"/>
				<label>时间：</label>
				<input name="kssj" value="<%=ComUtils.getTime(1) %>" class="mini-datepicker"/>~<input name="jssj" value="<%=ComUtils.getTime(1) %>" class="mini-datepicker"/>
				<a class="mini-button" style="width:60px;" onclick="search()">查询</a>
				<a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
			</div>
		</div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/sjcs/sbzt/getList.do" idField="id" multiSelect="true"
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
					<div field="sbid" width="70" headerAlign="center" align="center">设备ID</div>
		            <div field="snh" width="120" headerAlign="center" align="center">设备SN号</div>
					<div field="rjbbh" width="120" headerAlign="center" align="center">软件版本号</div>
					<div field="xtsj" width="160" headerAlign="center" align="center" renderer="onXtsjRenderer">系统时间</div>
					<div field="zdsc" width="100" headerAlign="center" align="center">站点运行总时长</div>
					<div field="dqsc" width="100" headerAlign="center" align="center">当前运行总时长</div>
					<div field="sbsj" width="160" headerAlign="center" align="center" renderer="onSbsjRenderer">最近上报时间</div>
					<div field="dy" width="70" headerAlign="center" align="center">系统电压</div>
					<div field="kzt" width="100" headerAlign="center" align="center" renderer="onKztRenderer">SD卡状态</div>
					<div field="mnzt" width="200" headerAlign="center" align="center">模拟通道状态</div>
					<div field="szzt" width="200" headerAlign="center" align="center">数字通道状态</div>
					<div field="sbwd" width="70" headerAlign="center" align="center">设备温度</div>
					<div field="time" width="160" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
					<div field="sim" width="120" headerAlign="center" align="center">设备SIM号</div>
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
		o.sbid = mini.getByName("sbid").getValue();
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

	function onXtsjRenderer(e){
		if(e.value==null || e.value.indexOf(",")==-1){
			return '<span style="color:red">日期格式错误</span>';
		}else{
			var arr = e.value.split(",");
			if(Number(arr[1])>10 || Number(arr[1])<-10){
				return '<span style="color:red">'+arr[0]+'</span>';
			}else{
				return arr[0];
			}
		}	
	}

	function onSbsjRenderer(e){
		if(e.value==null || e.value.indexOf(",")==-1){
			return '<span style="color:red">日期格式错误</span>';
		}else{
			var arr = e.value.split(",");
			if(Number(arr[1])>48){
				return '<span style="color:red">'+arr[0]+'</span>';
			}else{
				return arr[0];
			}
		}
	}

	function onKztRenderer(e){
		if(e.value!="正常"){
			return '<span style="color:red">'+e.value+'</span>';
		}else{
			return e.value;
		}	
	}

</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>