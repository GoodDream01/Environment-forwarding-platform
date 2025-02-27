<%@page import="com.dt.common.utils.SystemConstant"%>
<%@page import="com.dt.common.utils.ComUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=CcXgr378tQe0sVCGIRC596QWvymxwx8q"></script>

<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<!-- 加载城市列表 -->
<script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/gis/baidu/DrawingManager.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/gis/baidu/DrawingManager_min.css" />
<!--加载检索信息窗口-->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/gis/baidu/SearchInfoWindow.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/gis/baidu/SearchInfoWindow_min.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/gis/baidu/InfoBox.js"></script>

<!--加载公共方法-->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/gis/baidu/CommonJsonStyle.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/gis/baidu/CommonFun.js?_rand=<%=Math.random()%>"></script>

<style type="text/css">
.BMap_cpyCtrl {
    display: none;
}
.anchorBL {
    display: none;
}
</style>