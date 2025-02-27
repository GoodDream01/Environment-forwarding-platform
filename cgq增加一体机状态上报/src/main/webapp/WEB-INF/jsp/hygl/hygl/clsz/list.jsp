<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath() %>/js/jquery-1.8.2.js" type="text/javascript"></script>
<link href="/static/js/cron-quartz/easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/static/js/cron-quartz/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/static/js/cron-quartz/cron.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cron表达式生成器123</title>
</head>
<body style="margin:0">
	<div class="easyui-layout"
		style="width: 630px; height: 520px; border: 1px rgb(202, 196, 196) solid; border-radius: 5px;">
		<div style="height: 100%;">
			<a href="javascript:location=location" style="position:absolute;top:7px;right:10px;z-index:100;font-size:12px">刷新</a>
			<div class="easyui-tabs" data-options="fit:true,border:false">
				<div title="日">
					<div class="line">
						<input type="radio" checked="checked" name="day" onclick="everyTime(this)"> 日 允许的通配符[,
						- * / L W]
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="unAppoint(this)"> 不指定
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="cycle(this)"> 周期从 <input class="numberspinner"
							style="width: 60px;" data-options="min:1,max:31" value="1" id="dayStart_0">- <input
							class="numberspinner" style="width: 60px;" data-options="min:2,max:31" value="2"
							id="dayEnd_0">日
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="startOn(this)"> 从 <input class="numberspinner"
							style="width: 60px;" data-options="min:1,max:31" value="1" id="dayStart_1">日开始,每 <input
							class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
							id="dayEnd_1">天执行一次
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="workDay(this)"> 每月 <input
							class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
							id="dayStart_2">号最近的那个工作日
					</div>
					<div class="line">
						<input type="radio" name="day" onclick="lastDay(this)"> 本月最后一天
					</div>
					<div class="line">
						<input type="radio" name="day" id="day_appoint"> 指定
					</div>
					<div class="imp dayList">
						<input type="checkbox" value="1">1 <input type="checkbox" value="2">2 <input
							type="checkbox" value="3">3 <input type="checkbox" value="4">4 <input
							type="checkbox" value="5">5 <input type="checkbox" value="6">6 <input
							type="checkbox" value="7">7 <input type="checkbox" value="8">8 <input
							type="checkbox" value="9">9 <input type="checkbox" value="10">10 <input
							type="checkbox" value="11">11 <input type="checkbox" value="12">12 <input
							type="checkbox" value="13">13 <input type="checkbox" value="14">14 <input
							type="checkbox" value="15">15 <input type="checkbox" value="16">16
					</div>
					<div class="imp dayList">
						<input type="checkbox" value="17">17 <input type="checkbox" value="18">18 <input
							type="checkbox" value="19">19 <input type="checkbox" value="20">20 <input
							type="checkbox" value="21">21 <input type="checkbox" value="22">22 <input
							type="checkbox" value="23">23 <input type="checkbox" value="24">24 <input
							type="checkbox" value="25">25 <input type="checkbox" value="26">26 <input
							type="checkbox" value="27">27 <input type="checkbox" value="28">28 <input
							type="checkbox" value="29">29 <input type="checkbox" value="30">30 <input
							type="checkbox" value="31">31
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 245px">
			<fieldset style="border-radius: 3px; height: 200px;">
				<legend>表达式</legend>
				<table style="width: 100%">
					<tbody>
						<tr>
							<td></td>
							<td align="center">秒</td>
							<td align="center">分钟</td>
							<td align="center">小时</td>
							<td align="center">日</td>
							<td align="center">月 <br /></td>
							<td align="center">星期</td>
							<td align="center">年</td>
						</tr>
						<tr>
							<td>表达式字段:</td>
							<td><input type="text" name="v_second" class="col" value="*" readonly="readonly" /></td>
							<td><input type="text" name="v_min" class="col" value="*" readonly="readonly" /></td>
							<td><input type="text" name="v_hour" class="col" value="*" readonly="readonly" /></td>
							<td><input type="text" name="v_day" class="col" value="*" readonly="readonly" /></td>
							<td><input type="text" name="v_mouth" class="col" value="*" readonly="readonly" /></td>
							<td><input type="text" name="v_week" class="col" value="?" readonly="readonly" /></td>
							<td><input type="text" name="v_year" class="col" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>Cron 表达式:</td>
							<td colspan="5"><input type="text" name="cron" style="width: 98%;" value="" id="cron" /></td>
							<td colspan="2"><input type="button" value="表达式反解析到界面" id="btnFan" onclick="btnFan()" /></td>
						</tr>
						<tr>
							<td colspan="8"><div id="runTime" style="height: 120px; overflow: auto;"></div></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</div>
	</div>
</body>
</html>