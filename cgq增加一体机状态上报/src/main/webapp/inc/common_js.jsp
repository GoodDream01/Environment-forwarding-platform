<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--_ 引入host_info -->
<jsp:include page="/static/js/host_info.jsp"></jsp:include>
<!--/_ 引入host_info -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common_fun.js?&_rand=<%=Math.random()%>"></script>

<!--_核心js引入-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_核心js引入-->

<!--三方公共js引入-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.ajax.extend.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/jquery.validation/1.14.0/messages_zh.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/h-ui/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/h-ui/lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<!--/_三方公共js引入-->

<!-- highcharts引入 -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/h-ui/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Highcharts-6.0.7/code/modules/oldie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/h-ui/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script> --%>

<!-- echarts引入 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/hbHome/js/echarts/echarts-3.7.2.min.js"></script>