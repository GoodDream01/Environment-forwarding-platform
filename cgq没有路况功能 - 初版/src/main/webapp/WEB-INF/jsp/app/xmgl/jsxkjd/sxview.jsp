<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<%
String zfid = request.getParameter("zfid");
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" >
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">规划许可阶段查看</div>
	</header>
	<div class="aui-content-padded">
        <p>事项对应项目名称：${jsxkjd.sxdyxmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	 事项对应阶段编号：${jsxkjd.sxdyjdbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                  	事项对应阶段名称：${jsxkjd.sxdyjdmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项对应项目编号：${jsxkjd.sxdyxmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项名称：${jsxkjd.sxmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项编号：${jsxkjd.sxbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承若时限：${jsxkjd.cnsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承诺办结时间：${jsxkjd.cnbjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理单位：${jsxkjd.sldw}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否里程碑事项：${jsxkjd.sflcbsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	申请时间：${jsxkjd.sqsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	登记时间：${jsxkjd.djsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理时间：${jsxkjd.slsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结时间：${jsxkjd.bjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结结果：${jsxkjd.bjjg}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>