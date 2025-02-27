<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<a href="<%=request.getContextPath()%>/doc/help.rar" target="_blank" style="color: yellow;font-size:14px;">操作手册下载</a>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/doc/ppt.zip" target="_blank" style="color: yellow;font-size:14px;">操作演示下载</a>
</div>
<div><span>推荐使用谷歌浏览器、猎豹浏览器</span></div>
<!-- 2019-11-13添加 -->
<!-- ////////////////////////////////////////飘窗：开始////////////////////////////////////////// -->
<script src="<%=request.getContextPath() %>/static/js/jquery-plug/jquery.bay-window.js?&<%=Math.random()%>" type="text/javascript"></script>
<style>
#piao img{
width:200px;
height: 150px;
padding:0px;
display: block;
margin-top: 2px;
}
</style>
<div id="piao">
	<a href="<%=request.getContextPath()%>/doc/help.rar" target="_blank" alt="操作手册下载">
		<img alt="操作手册下载" width="200" height="150" src="<%=request.getContextPath()%>/static/images/piao/piao_czsc.jpg"  border="0"/>
	</a>
	<a href="<%=request.getContextPath()%>/doc/ppt.zip" target="_blank" alt="操作演示下载">
		<img alt="操作演示下载" width="200" height="150" src="<%=request.getContextPath()%>/static/images/piao/piao_czys.jpg"  border="0"/>
	</a>
</div>
<script>
$(function(){
	$('#piao').autoMove({angle:-Math.PI/4, speed:100});
});
</script>
<!-- ////////////////////////////////////////飘窗：结束////////////////////////////////////////// -->
<!-- ////////////////////////////////////////浏览器判断：开始////////////////////////////////////////// -->
<script src="<%=request.getContextPath() %>/static/js/browser/browser.js" type="text/javascript"></script>
<!--[if lt IE 8]> 
<script src="<%=request.getContextPath() %>/static/js/browser/json2.min.js"></script>
<![endif]-->
<script>
$(function(){
	browser();
});
function tips(){
	mini.showTips({
		content: '请使用360浏览器急速模式，或谷歌浏览器！',
		timeout: 10000
	});
}
function browser(){
	var browser = whyun.browser || {};
	var name = browser.browser.name;
	if(!(name && name.toLowerCase().indexOf("chrome")!=-1)){
		tips();
	}
}
</script>
<!-- ////////////////////////////////////////浏览器判断：结束////////////////////////////////////////// -->