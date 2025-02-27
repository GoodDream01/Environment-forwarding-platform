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
				<label>设备编号：</label>
				<input name="sbid" class="mini-combobox" valueField="name" textField="name"/>
				<label>时间：</label>
				<input name="kssj" value="<%=ComUtils.getTime(7) %>" class="mini-datepicker"/>~<input name="jssj" value="<%=ComUtils.getTime(6) %>"class="mini-datepicker"/>
				<a class="mini-button" style="width:60px;" onclick="search()">查询</a>
				<a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
				<a class="mini-button" iconCls="icon-excel" onclick="tableToExcel()">导出Excel</a>
			</div>
		</div>
		<table id="tableExcel" style="display:none">

		</table>
		<!--撑满页面-->
		<div class="mini-fit" >
			<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
				 url="<%=request.getContextPath() %>/sjcs/sjpz/getlsList.do" idField="id" multiSelect="true"
				 fitColumns="false" pageSize="20">
				<div property="columns">
					<div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
					<div field="sbid" width="50" headerAlign="center" align="center">设备id</div>
					<div field="date" width="150" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>
					<%if(USERINFO_XSZD.indexOf("dy")>-1){%>
					<div field="dy" width="50" headerAlign="center" align="center">电压</div>
					<%}if(USERINFO_XSZD.indexOf("dqwdz")>-1){%>
					<div field="dqwdz" width="80" headerAlign="center" align="center">大气温度值</div>
					<%}if(USERINFO_XSZD.indexOf("qwxszdz")>-1){%>
					<div field="qwxszdz" width="80" headerAlign="center" align="center">小时最大值</div>
					<%}if(USERINFO_XSZD.indexOf("qwxszxz")>-1){%>
					<div field="qwxszxz" width="80" headerAlign="center" align="center">小时最小值</div>
					<%}if(USERINFO_XSZD.indexOf("dqsdz")>-1){%>
					<div field="dqsdz" width="80" headerAlign="center" align="center">大气湿度值</div>
					<%}if(USERINFO_XSZD.indexOf("sdxszdz")>-1){%>
					<div field="sdxszdz" width="80" headerAlign="center" align="center">小时最大值</div>
					<%}if(USERINFO_XSZD.indexOf("sdxszxz")>-1){%>
					<div field="sdxszxz" width="80" headerAlign="center" align="center">小时最小值</div>
					<%}if(USERINFO_XSZD.indexOf("dqylz")>-1){%>
					<div field="dqylz" width="80" headerAlign="center" align="center">大气压力值</div>
					<%}if(USERINFO_XSZD.indexOf("ylxszdz")>-1){%>
					<div field="ylxszdz" width="80" headerAlign="center" align="center">小时最大值</div>
					<%}if(USERINFO_XSZD.indexOf("ylxszxz")>-1){%>
					<div field="ylxszxz" width="80" headerAlign="center" align="center">小时最小值</div>
					<%}if(USERINFO_XSZD.indexOf("fsssz")>-1){%>
					<div field="fsssz" width="80" headerAlign="center" align="center">风速瞬时值</div>
					<%}if(USERINFO_XSZD.indexOf("fspjz")>-1){%>
					<div field="fspjz" width="80" headerAlign="center" align="center">风速平均值</div>
					<%}if(USERINFO_XSZD.indexOf("fszdz")>-1){%>
					<div field="fszdz" width="80" headerAlign="center" align="center">风速最大值</div>
					<%}if(USERINFO_XSZD.indexOf("fszxz")>-1){%>
					<div field="fszxz" width="80" headerAlign="center" align="center">风速最小值</div>
					<%}if(USERINFO_XSZD.indexOf("fxssz")>-1){%>
					<div field="fxssz" width="80" headerAlign="center" align="center">风向瞬时值</div>
					<%}if(USERINFO_XSZD.indexOf("fxpjz")>-1){%>
					<div field="fxpjz" width="80" headerAlign="center" align="center">风向平均值</div>
					<%}if(USERINFO_XSZD.indexOf("dqjyz")>-1){%>
					<div field="dqjyz" width="80" headerAlign="center" align="center">当前降雨值</div>
					<%}if(USERINFO_XSZD.indexOf("zfsz")>-1){%>
					<div field="zfsz" width="80" headerAlign="center" align="center">总辐射值</div>
					<%}if(USERINFO_XSZD.indexOf("fs0")>-1){%>
					<div field="fs0" width="80" headerAlign="center" align="center">风速1</div>
					<%}if(USERINFO_XSZD.indexOf("fs1")>-1){%>
					<div field="fs1" width="80" headerAlign="center" align="center">风速2</div>
					<%}if(USERINFO_XSZD.indexOf("fs2")>-1){%>
					<div field="fs2" width="80" headerAlign="center" align="center">风速3</div>
					<%}if(USERINFO_XSZD.indexOf("fs3")>-1){%>
					<div field="fs3" width="80" headerAlign="center" align="center">风速4</div>
					<%}if(USERINFO_XSZD.indexOf("fs4")>-1){%>
					<div field="fs4" width="80" headerAlign="center" align="center">风速5</div>
					<%}if(USERINFO_XSZD.indexOf("fs5")>-1){%>
					<div field="fs5" width="80" headerAlign="center" align="center">风速6</div>
					<%}if(USERINFO_XSZD.indexOf("fs6")>-1){%>
					<div field="fs6" width="80" headerAlign="center" align="center">风速7</div>
					<%}if(USERINFO_XSZD.indexOf("fs7")>-1){%>
					<div field="fs7" width="80" headerAlign="center" align="center">风速8</div>
					<%}if(USERINFO_XSZD.indexOf("fs8")>-1){%>
					<div field="fs8" width="80" headerAlign="center" align="center">风速9</div>
					<%}if(USERINFO_XSZD.indexOf("fs9")>-1){%>
					<div field="fs9" width="80" headerAlign="center" align="center">风速10</div>
					<%}if(USERINFO_XSZD.indexOf("fx0")>-1){%>
					<div field="fx0" width="80" headerAlign="center" align="center">风向1</div>
					<%}if(USERINFO_XSZD.indexOf("fx1")>-1){%>
					<div field="fx1" width="80" headerAlign="center" align="center">风向2</div>
					<%}if(USERINFO_XSZD.indexOf("fx2")>-1){%>
					<div field="fx2" width="80" headerAlign="center" align="center">风向3</div>
					<%}if(USERINFO_XSZD.indexOf("fx3")>-1){%>
					<div field="fx3" width="80" headerAlign="center" align="center">风向4</div>
					<%}if(USERINFO_XSZD.indexOf("fx4")>-1){%>
					<div field="fx4" width="80" headerAlign="center" align="center">风向5</div>
					<%}if(USERINFO_XSZD.indexOf("fx5")>-1){%>
					<div field="fx5" width="80" headerAlign="center" align="center">风向6</div>
					<%}if(USERINFO_XSZD.indexOf("fx6")>-1){%>
					<div field="fx6" width="80" headerAlign="center" align="center">风向7</div>
					<%}if(USERINFO_XSZD.indexOf("fx7")>-1){%>
					<div field="fx7" width="80" headerAlign="center" align="center">风向8</div>
					<%}if(USERINFO_XSZD.indexOf("fx8")>-1){%>
					<div field="fx8" width="80" headerAlign="center" align="center">风向9</div>
					<%}if(USERINFO_XSZD.indexOf("fx9")>-1){%>
					<div field="fx9" width="80" headerAlign="center" align="center">风向10</div>
					<%}if(USERINFO_XSZD.indexOf("wd0")>-1){%>
					<div field="wd0" width="80" headerAlign="center" align="center">大气温度1</div>
					<%}if(USERINFO_XSZD.indexOf("wd1")>-1){%>
					<div field="wd1" width="80" headerAlign="center" align="center">大气温度2</div>
					<%}if(USERINFO_XSZD.indexOf("wd2")>-1){%>
					<div field="wd2" width="80" headerAlign="center" align="center">大气温度3</div>
					<%}if(USERINFO_XSZD.indexOf("wd3")>-1){%>
					<div field="wd3" width="80" headerAlign="center" align="center">大气温度4</div>
					<%}if(USERINFO_XSZD.indexOf("wd4")>-1){%>
					<div field="wd4" width="80" headerAlign="center" align="center">大气温度5</div>
					<%}if(USERINFO_XSZD.indexOf("wd5")>-1){%>
					<div field="wd5" width="80" headerAlign="center" align="center">大气温度6</div>
					<%}if(USERINFO_XSZD.indexOf("wd6")>-1){%>
					<div field="wd6" width="80" headerAlign="center" align="center">大气温度7</div>
					<%}if(USERINFO_XSZD.indexOf("wd7")>-1){%>
					<div field="wd7" width="80" headerAlign="center" align="center">大气温度8</div>
					<%}if(USERINFO_XSZD.indexOf("wd8")>-1){%>
					<div field="wd8" width="80" headerAlign="center" align="center">大气温度9</div>
					<%}if(USERINFO_XSZD.indexOf("wd9")>-1){%>
					<div field="wd9" width="80" headerAlign="center" align="center">大气温度10</div>
					<%}if(USERINFO_XSZD.indexOf("sd0")>-1){%>
					<div field="sd0" width="80" headerAlign="center" align="center">大气湿度1</div>
					<%}if(USERINFO_XSZD.indexOf("sd1")>-1){%>
					<div field="sd1" width="80" headerAlign="center" align="center">大气湿度2</div>
					<%}if(USERINFO_XSZD.indexOf("sd2")>-1){%>
					<div field="sd2" width="80" headerAlign="center" align="center">大气湿度3</div>
					<%}if(USERINFO_XSZD.indexOf("sd3")>-1){%>
					<div field="sd3" width="80" headerAlign="center" align="center">大气湿度4</div>
					<%}if(USERINFO_XSZD.indexOf("sd4")>-1){%>
					<div field="sd4" width="80" headerAlign="center" align="center">大气湿度5</div>
					<%}if(USERINFO_XSZD.indexOf("sd5")>-1){%>
					<div field="sd5" width="80" headerAlign="center" align="center">大气湿度6</div>
					<%}if(USERINFO_XSZD.indexOf("sd6")>-1){%>
					<div field="sd6" width="80" headerAlign="center" align="center">大气湿度7</div>
					<%}if(USERINFO_XSZD.indexOf("sd7")>-1){%>
					<div field="sd7" width="80" headerAlign="center" align="center">大气湿度8</div>
					<%}if(USERINFO_XSZD.indexOf("sd8")>-1){%>
					<div field="sd8" width="80" headerAlign="center" align="center">大气湿度9</div>
					<%}if(USERINFO_XSZD.indexOf("sd9")>-1){%>
					<div field="sd9" width="80" headerAlign="center" align="center">大气湿度10</div>
					<%}if(USERINFO_XSZD.indexOf("qy0")>-1){%>
					<div field="qy0" width="80" headerAlign="center" align="center">大气压力1</div>
					<%}if(USERINFO_XSZD.indexOf("qy1")>-1){%>
					<div field="qy1" width="80" headerAlign="center" align="center">大气压力2</div>
					<%}if(USERINFO_XSZD.indexOf("qy2")>-1){%>
					<div field="qy2" width="80" headerAlign="center" align="center">大气压力3</div>
					<%}if(USERINFO_XSZD.indexOf("qy3")>-1){%>
					<div field="qy3" width="80" headerAlign="center" align="center">大气压力4</div>
					<%}if(USERINFO_XSZD.indexOf("qy4")>-1){%>
					<div field="qy4" width="80" headerAlign="center" align="center">大气压力5</div>
					<%}if(USERINFO_XSZD.indexOf("qy5")>-1){%>
					<div field="qy5" width="80" headerAlign="center" align="center">大气压力6</div>
					<%}if(USERINFO_XSZD.indexOf("qy6")>-1){%>
					<div field="qy6" width="80" headerAlign="center" align="center">大气压力7</div>
					<%}if(USERINFO_XSZD.indexOf("qy7")>-1){%>
					<div field="qy7" width="80" headerAlign="center" align="center">大气压力8</div>
					<%}if(USERINFO_XSZD.indexOf("qy8")>-1){%>
					<div field="qy8" width="80" headerAlign="center" align="center">大气压力9</div>
					<%}if(USERINFO_XSZD.indexOf("qy9")>-1){%>
					<div field="qy9" width="80" headerAlign="center" align="center">大气压力10</div>
					<%}if(USERINFO_XSZD.indexOf("yl0")>-1){%>
					<div field="yl0" width="80" headerAlign="center" align="center">降雨量1</div>
					<%}if(USERINFO_XSZD.indexOf("yl1")>-1){%>
					<div field="yl1" width="80" headerAlign="center" align="center">降雨量2</div>
					<%}if(USERINFO_XSZD.indexOf("yl2")>-1){%>
					<div field="yl2" width="80" headerAlign="center" align="center">降雨量3</div>
					<%}if(USERINFO_XSZD.indexOf("yl3")>-1){%>
					<div field="yl3" width="80" headerAlign="center" align="center">降雨量4</div>
					<%}if(USERINFO_XSZD.indexOf("yl4")>-1){%>
					<div field="yl4" width="80" headerAlign="center" align="center">降雨量5</div>
					<%}if(USERINFO_XSZD.indexOf("yl5")>-1){%>
					<div field="yl5" width="80" headerAlign="center" align="center">降雨量6</div>
					<%}if(USERINFO_XSZD.indexOf("yl6")>-1){%>
					<div field="yl6" width="80" headerAlign="center" align="center">降雨量7</div>
					<%}if(USERINFO_XSZD.indexOf("yl7")>-1){%>
					<div field="yl7" width="80" headerAlign="center" align="center">降雨量8</div>
					<%}if(USERINFO_XSZD.indexOf("yl8")>-1){%>
					<div field="yl8" width="80" headerAlign="center" align="center">降雨量9</div>
					<%}if(USERINFO_XSZD.indexOf("yl9")>-1){%>
					<div field="yl9" width="80" headerAlign="center" align="center">降雨量10</div>
					<%}if(USERINFO_XSZD.indexOf("fsz0")>-1){%>
					<div field="fsz0" width="80" headerAlign="center" align="center">土壤湿度1</div>
					<%}if(USERINFO_XSZD.indexOf("fsz1")>-1){%>
					<div field="fsz1" width="80" headerAlign="center" align="center">土壤湿度2</div>
					<%}if(USERINFO_XSZD.indexOf("fsz2")>-1){%>
					<div field="fsz2" width="80" headerAlign="center" align="center">土壤湿度3</div>
					<%}if(USERINFO_XSZD.indexOf("fsz3")>-1){%>
					<div field="fsz3" width="80" headerAlign="center" align="center">土壤湿度4</div>
					<%}if(USERINFO_XSZD.indexOf("fsz4")>-1){%>
					<div field="fsz4" width="80" headerAlign="center" align="center">土壤湿度5</div>
					<%}if(USERINFO_XSZD.indexOf("fsz5")>-1){%>
					<div field="fsz5" width="80" headerAlign="center" align="center">土壤湿度6</div>
					<%}if(USERINFO_XSZD.indexOf("fsz6")>-1){%>
					<div field="fsz6" width="80" headerAlign="center" align="center">土壤湿度7</div>
					<%}if(USERINFO_XSZD.indexOf("fsz7")>-1){%>
					<div field="fsz7" width="80" headerAlign="center" align="center">土壤湿度8</div>
					<%}if(USERINFO_XSZD.indexOf("fsz8")>-1){%>
					<div field="fsz8" width="80" headerAlign="center" align="center">土壤湿度9</div>
					<%}if(USERINFO_XSZD.indexOf("fsz9")>-1){%>
					<div field="fsz9" width="80" headerAlign="center" align="center">土壤湿度10</div>
					<%}if(USERINFO_XSZD.indexOf("zw0")>-1){%>
					<div field="zw0" width="80" headerAlign="center" align="center">土壤温度1</div>
					<%}if(USERINFO_XSZD.indexOf("zw1")>-1){%>
					<div field="zw1" width="80" headerAlign="center" align="center">土壤温度2</div>
					<%}if(USERINFO_XSZD.indexOf("zw2")>-1){%>
					<div field="zw2" width="80" headerAlign="center" align="center">土壤温度3</div>
					<%}if(USERINFO_XSZD.indexOf("zw3")>-1){%>
					<div field="zw3" width="80" headerAlign="center" align="center">土壤温度4</div>
					<%}if(USERINFO_XSZD.indexOf("zw4")>-1){%>
					<div field="zw4" width="80" headerAlign="center" align="center">土壤温度5</div>
					<%}if(USERINFO_XSZD.indexOf("zw5")>-1){%>
					<div field="zw5" width="80" headerAlign="center" align="center">土壤温度6</div>
					<%}if(USERINFO_XSZD.indexOf("zw6")>-1){%>
					<div field="zw6" width="80" headerAlign="center" align="center">土壤温度7</div>
					<%}if(USERINFO_XSZD.indexOf("zw7")>-1){%>
					<div field="zw7" width="80" headerAlign="center" align="center">土壤温度8</div>
					<%}if(USERINFO_XSZD.indexOf("zw8")>-1){%>
					<div field="zw8" width="80" headerAlign="center" align="center">土壤温度9</div>
					<%}if(USERINFO_XSZD.indexOf("zw9")>-1){%>
					<div field="zw9" width="80" headerAlign="center" align="center">土壤温度10</div>
					<%}if(USERINFO_XSZD.indexOf("zfs1")>-1){%>
					<div field="zfs1" width="80" headerAlign="center" align="center">总辐射1</div>
					<%}if(USERINFO_XSZD.indexOf("yxfs1")>-1){%>
					<div field="yxfs1" width="80" headerAlign="center" align="center">光合有效辐射1</div>
					<%}if(USERINFO_XSZD.indexOf("qwxszdz")>-1){%>
					<div field="bj" width="80" headerAlign="center" align="center" renderer="onBjRenderer">是否传输</div>
					<div field="tsdw" width="100" headerAlign="center" align="center">推送单位</div>
					<div field="tsjg" width="100" headerAlign="center" align="center">推送结果</div>
					<%}%>
					<%--<div field="yxbj" width="80" headerAlign="center" align="center" renderer="onYxbjRenderer">是否发送</div>--%>
					<%--<div field="tsyx" width="100" headerAlign="center" align="center">推送邮箱</div>--%>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	mini.parse();
	var grid = mini.get("datagrid1");
	grid.load();

	$.ajax({
		url: HOST_URL+"/sjcs/sjqx/getSb.do",
		type: 'post',
		dataType: 'json',
		contentType:'application/json;charset=UTF-8',
		cache: false,
		success: function (res) {
			mini.getbyName("sbid").setData(res);
		}
	});

	function search() {
		var o={};
		o.sbid = mini.getByName("sbid").getValue();
		o.kssj = mini.getByName("kssj").getFormValue();
		o.jssj = mini.getByName("jssj").getFormValue();
		grid.load(o);
	}

	function tableToExcel() {
		var sbid = mini.getByName("sbid").getValue();
		var kssj = mini.getByName("kssj").getFormValue();
		var jssj = mini.getByName("jssj").getFormValue();
		$.ajax({
			url: HOST_URL+"/sjcs/sjpz/findDc.do?sbid="+sbid+"&kssj="+kssj+"&jssj="+jssj,
			type: 'post',
			dataType: 'json',
			contentType:'application/json;charset=UTF-8',
			cache: false,
			success: function (res) {
				var html = "<tr>";
				var columns = grid.getColumns();
				var zdmc = "";
				for (var i = 1; i < columns.length; i++) {
					html += "<td>"+columns[i].header+"</td>";
					zdmc += columns[i].field+",";
				}
				html += "</tr>";
				for (var i = 0; i < res.length; i++) {
					html +="<tr><td>"+res[i].sbid+"</td><td>"+res[i].date+"</td>";
					if(zdmc.indexOf("dy")>-1){
						html += "<td>"+res[i].dy+"</td>";
					}if(zdmc.indexOf("dqwdz")>-1){
						html += "<td>"+res[i].dqwdz+"</td>";
					}if(zdmc.indexOf("qwxszdz")>-1){
						html += "<td>"+res[i].qwxszdz+"</td>";
					}if(zdmc.indexOf("qwxszxz")>-1){
						html += "<td>"+res[i].qwxszxz+"</td>";
					}if(zdmc.indexOf("dqsdz")>-1){
						html += "<td>"+res[i].dqsdz+"</td>";
					}if(zdmc.indexOf("sdxszdz")>-1){
						html += "<td>"+res[i].sdxszdz+"</td>";
					}if(zdmc.indexOf("sdxszxz")>-1){
						html += "<td>"+res[i].sdxszxz+"</td>";
					}if(zdmc.indexOf("dqylz")>-1){
						html += "<td>"+res[i].dqylz+"</td>";
					}if(zdmc.indexOf("ylxszdz")>-1){
						html += "<td>"+res[i].ylxszdz+"</td>";
					}if(zdmc.indexOf("ylxszxz")>-1){
						html += "<td>"+res[i].ylxszxz+"</td>";
					}if(zdmc.indexOf("fsssz")>-1){
						html += "<td>"+res[i].fsssz+"</td>";
					}if(zdmc.indexOf("fspjz")>-1){
						html += "<td>"+res[i].fspjz+"</td>";
					}if(zdmc.indexOf("fszdz")>-1){
						html += "<td>"+res[i].fszdz+"</td>";
					}if(zdmc.indexOf("fszxz")>-1){
						html += "<td>"+res[i].fszxz+"</td>";
					}if(zdmc.indexOf("fxssz")>-1){
						html += "<td>"+res[i].fxssz+"</td>";
					}if(zdmc.indexOf("fxpjz")>-1){
						html += "<td>"+res[i].fxpjz+"</td>";
					}if(zdmc.indexOf("dqjyz")>-1){
						html += "<td>"+res[i].dqjyz+"</td>";
					}if(zdmc.indexOf("zfsz")>-1){
						html += "<td>"+res[i].zfsz+"</td>";
					}if(zdmc.indexOf("fs0")>-1){
						html += "<td>"+res[i].fs0+"</td>";
					}if(zdmc.indexOf("fs1")>-1){
						html += "<td>"+res[i].fs1+"</td>";
					}if(zdmc.indexOf("fs2")>-1){
						html += "<td>"+res[i].fs2+"</td>";
					}if(zdmc.indexOf("fs3")>-1){
						html += "<td>"+res[i].fs3+"</td>";
					}if(zdmc.indexOf("fs4")>-1){
						html += "<td>"+res[i].fs4+"</td>";
					}if(zdmc.indexOf("fs5")>-1){
						html += "<td>"+res[i].fs5+"</td>";
					}if(zdmc.indexOf("fs6")>-1){
						html += "<td>"+res[i].fs6+"</td>";
					}if(zdmc.indexOf("fs7")>-1){
						html += "<td>"+res[i].fs7+"</td>";
					}if(zdmc.indexOf("fs8")>-1){
						html += "<td>"+res[i].fs8+"</td>";
					}if(zdmc.indexOf("fs9")>-1){
						html += "<td>"+res[i].fs9+"</td>";
					}if(zdmc.indexOf("fx0")>-1){
						html += "<td>"+res[i].fx0+"</td>";
					}if(zdmc.indexOf("fx1")>-1){
						html += "<td>"+res[i].fx1+"</td>";
					}if(zdmc.indexOf("fx2")>-1){
						html += "<td>"+res[i].fx2+"</td>";
					}if(zdmc.indexOf("fx3")>-1){
						html += "<td>"+res[i].fx3+"</td>";
					}if(zdmc.indexOf("fx4")>-1){
						html += "<td>"+res[i].fx4+"</td>";
					}if(zdmc.indexOf("fx5")>-1){
						html += "<td>"+res[i].fx5+"</td>";
					}if(zdmc.indexOf("fx6")>-1){
						html += "<td>"+res[i].fx6+"</td>";
					}if(zdmc.indexOf("fx7")>-1){
						html += "<td>"+res[i].fx7+"</td>";
					}if(zdmc.indexOf("fx8")>-1){
						html += "<td>"+res[i].fx8+"</td>";
					}if(zdmc.indexOf("fx9")>-1){
						html += "<td>"+res[i].fx9+"</td>";
					}if(zdmc.indexOf("wd0")>-1){
						html += "<td>"+res[i].wd0+"</td>";
					}if(zdmc.indexOf("wd1")>-1){
						html += "<td>"+res[i].wd1+"</td>";
					}if(zdmc.indexOf("wd2")>-1){
						html += "<td>"+res[i].wd2+"</td>";
					}if(zdmc.indexOf("wd3")>-1){
						html += "<td>"+res[i].wd3+"</td>";
					}if(zdmc.indexOf("wd4")>-1){
						html += "<td>"+res[i].wd4+"</td>";
					}if(zdmc.indexOf("wd5")>-1){
						html += "<td>"+res[i].wd5+"</td>";
					}if(zdmc.indexOf("wd6")>-1){
						html += "<td>"+res[i].wd6+"</td>";
					}if(zdmc.indexOf("wd7")>-1){
						html += "<td>"+res[i].wd7+"</td>";
					}if(zdmc.indexOf("wd8")>-1){
						html += "<td>"+res[i].wd8+"</td>";
					}if(zdmc.indexOf("wd9")>-1){
						html += "<td>"+res[i].wd9+"</td>";
					}if(zdmc.indexOf("sd0")>-1){
						html += "<td>"+res[i].sd0+"</td>";
					}if(zdmc.indexOf("sd1")>-1){
						html += "<td>"+res[i].sd1+"</td>";
					}if(zdmc.indexOf("sd2")>-1){
						html += "<td>"+res[i].sd2+"</td>";
					}if(zdmc.indexOf("sd3")>-1){
						html += "<td>"+res[i].sd3+"</td>";
					}if(zdmc.indexOf("sd4")>-1){
						html += "<td>"+res[i].sd4+"</td>";
					}if(zdmc.indexOf("sd5")>-1){
						html += "<td>"+res[i].sd5+"</td>";
					}if(zdmc.indexOf("sd6")>-1){
						html += "<td>"+res[i].sd6+"</td>";
					}if(zdmc.indexOf("sd7")>-1){
						html += "<td>"+res[i].sd7+"</td>";
					}if(zdmc.indexOf("sd8")>-1){
						html += "<td>"+res[i].sd8+"</td>";
					}if(zdmc.indexOf("sd9")>-1){
						html += "<td>"+res[i].sd9+"</td>";
					}if(zdmc.indexOf("qy0")>-1){
						html += "<td>"+res[i].qy0+"</td>";
					}if(zdmc.indexOf("qy1")>-1){
						html += "<td>"+res[i].qy1+"</td>";
					}if(zdmc.indexOf("qy2")>-1){
						html += "<td>"+res[i].qy2+"</td>";
					}if(zdmc.indexOf("qy3")>-1){
						html += "<td>"+res[i].qy3+"</td>";
					}if(zdmc.indexOf("qy4")>-1){
						html += "<td>"+res[i].qy4+"</td>";
					}if(zdmc.indexOf("qy5")>-1){
						html += "<td>"+res[i].qy5+"</td>";
					}if(zdmc.indexOf("qy6")>-1){
						html += "<td>"+res[i].qy6+"</td>";
					}if(zdmc.indexOf("qy7")>-1){
						html += "<td>"+res[i].qy7+"</td>";
					}if(zdmc.indexOf("qy8")>-1){
						html += "<td>"+res[i].qy8+"</td>";
					}if(zdmc.indexOf("qy9")>-1){
						html += "<td>"+res[i].qy9+"</td>";
					}if(zdmc.indexOf("yl0")>-1){
						html += "<td>"+res[i].yl0+"</td>";
					}if(zdmc.indexOf("yl1")>-1){
						html += "<td>"+res[i].yl1+"</td>";
					}if(zdmc.indexOf("yl2")>-1){
						html += "<td>"+res[i].yl2+"</td>";
					}if(zdmc.indexOf("yl3")>-1){
						html += "<td>"+res[i].yl3+"</td>";
					}if(zdmc.indexOf("yl4")>-1){
						html += "<td>"+res[i].yl4+"</td>";
					}if(zdmc.indexOf("yl5")>-1){
						html += "<td>"+res[i].yl5+"</td>";
					}if(zdmc.indexOf("yl6")>-1){
						html += "<td>"+res[i].yl6+"</td>";
					}if(zdmc.indexOf("yl7")>-1){
						html += "<td>"+res[i].yl7+"</td>";
					}if(zdmc.indexOf("yl8")>-1){
						html += "<td>"+res[i].yl8+"</td>";
					}if(zdmc.indexOf("yl9")>-1){
						html += "<td>"+res[i].yl9+"</td>";
					}if(zdmc.indexOf("fsz0")>-1){
						html += "<td>"+res[i].fsz0+"</td>";
					}if(zdmc.indexOf("fsz1")>-1){
						html += "<td>"+res[i].fsz1+"</td>";
					}if(zdmc.indexOf("fsz2")>-1){
						html += "<td>"+res[i].fsz2+"</td>";
					}if(zdmc.indexOf("fsz3")>-1){
						html += "<td>"+res[i].fsz3+"</td>";
					}if(zdmc.indexOf("fsz4")>-1){
						html += "<td>"+res[i].fsz4+"</td>";
					}if(zdmc.indexOf("fsz5")>-1){
						html += "<td>"+res[i].fsz5+"</td>";
					}if(zdmc.indexOf("fsz6")>-1){
						html += "<td>"+res[i].fsz6+"</td>";
					}if(zdmc.indexOf("fsz7")>-1){
						html += "<td>"+res[i].fsz7+"</td>";
					}if(zdmc.indexOf("fsz8")>-1){
						html += "<td>"+res[i].fsz8+"</td>";
					}if(zdmc.indexOf("fsz9")>-1){
						html += "<td>"+res[i].fsz9+"</td>";
					}if(zdmc.indexOf("zw0")>-1){
						html += "<td>"+res[i].zw0+"</td>";
					}if(zdmc.indexOf("zw1")>-1){
						html += "<td>"+res[i].zw1+"</td>";
					}if(zdmc.indexOf("zw2")>-1){
						html += "<td>"+res[i].zw2+"</td>";
					}if(zdmc.indexOf("zw3")>-1){
						html += "<td>"+res[i].zw3+"</td>";
					}if(zdmc.indexOf("zw4")>-1){
						html += "<td>"+res[i].zw4+"</td>";
					}if(zdmc.indexOf("zw5")>-1){
						html += "<td>"+res[i].zw5+"</td>";
					}if(zdmc.indexOf("zw6")>-1){
						html += "<td>"+res[i].zw6+"</td>";
					}if(zdmc.indexOf("zw7")>-1){
						html += "<td>"+res[i].zw7+"</td>";
					}if(zdmc.indexOf("zw8")>-1){
						html += "<td>"+res[i].zw8+"</td>";
					}if(zdmc.indexOf("zw9")>-1){
						html += "<td>"+res[i].zw9+"</td>";
					}if(zdmc.indexOf("zfs1")>-1){
						html += "<td>"+res[i].zfs1+"</td>";
					}if(zdmc.indexOf("yxfs1")>-1){
						html += "<td>"+res[i].yxfs1+"</td>";
					}if(zdmc.indexOf("qwxszdz")>-1){
						if(res[i].bj=="0"){
							html += "<td>未传输</td>";
						}else{
							html += "<td>已传输</td>";
						}
						html += "<td>"+res[i].tsdw+"</td>";
						html += "<td>"+res[i].tsjg+"</td>";
					}
					html+="</tr>";
				}
				$("#tableExcel").html(html);
				//根据id获取表格
				var exportFileContent = document.getElementById("tableExcel").outerHTML;
				var blob = new Blob([exportFileContent], {
					type: "text/plain;charset=utf-8"
				});
				// 放值乱码
				blob = new Blob([String.fromCharCode(65279), blob], {
					type: blob.type
				});
				//设置链接
				var link = window.URL.createObjectURL(blob);
				//创建a标签
				var a = document.createElement("a");
				//传过来的参数（文件名）
				a.download = "数据.xls";
				//设置a标签的链接
				a.href = link;
				//a标签添加到页面
				document.body.appendChild(a);
				//a标签触发单击事件
				a.click();
				//移除a标签
				document.body.removeChild(a)
			}
		});
	}

	function onKeyEnter(e) {
		search();
	}

	function cz(){
		location.reload();
	}

	function view() {
		var row = grid.getSelected();
		if (row) {
			mini.open({
				url: HOST_PATH + "/sjcs/sjpz/addUi.do",
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

	function onBjRenderer(e) {
		if(e.value=="0"){
			return "未传输";
		}else{
			return "已传输";
		}
	}

	function onYxbjRenderer(e) {
		if(e.value=="0"){
			return "未发送";
		}else{
			return "已发送";
		}
	}

</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>