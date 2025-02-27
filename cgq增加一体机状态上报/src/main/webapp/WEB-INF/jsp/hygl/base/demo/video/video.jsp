<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div id="videoPlayer"></div>
<script type="text/javascript">
playVideo({
	id:"videoPlayer",
	vID:"c1",
	vWidth:"600",
	vHeight:"400",
	vAutoPlay:"no",
	vMp4url:"https://cloud.video.taobao.com/play/u/10654505/p/1/e/6/t/1/50032658929.mp4",
});
</script>
<%-- 
<!-- 酷播V4.05跨平台代码/开始 -->
<div class="video" id="CuPlayer">
<script type="text/javascript">
<!--
var vRootPath = "<%=request.getContextPath() %>/static/js/video";
var vID        = "c1";                   //vID
var vWidth     = "600";                  //宽度设定，配合CSS实现
var vHeight    = "400";                  //高度设定，配合CSS实现
var vFile      = vRootPath+"/CuSunV4set.xml";       //配置文件地址:支持五种广告设定
var vPlayer    = vRootPath+"/player.swf?v=4.0";     //播放器文件地址
//var vPic       = vRootPath+"/pic/pic01.jpg";        //视频缩略图
var vPic = "";
var vCssurl    = vRootPath+"/css/videos.min.css";   //CSS文件
var vAutoPlay  = "yes";                  //是否自动播放
var vEndTime   = 0;                      //预览时间(秒数),默认为0
//var vLogoPath  = vRootPath+"/images/logo.png";      //Logo地址
var vLogoPath="";
var vPlayMod   = 0;                      //播放模式优先级,默认=0,html5优先=1,flash优先=2
var vMp4url    = "https://cloud.video.taobao.com/play/u/10654505/p/1/e/6/t/1/50032658929.mp4";//视频文件地址推荐用mp4文件(h264编码)
//-->
</script>
<script class="CuPlayerVideo" data-mce-role="CuPlayerVideo" type="text/javascript" src="<%=request.getContextPath() %>/static/js/video/js/player.min.js"></script>
</div>
<!-- 酷播V4.05跨平台代码/结束 --> 
--%>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>