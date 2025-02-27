<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
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
	    <div class="aui-title">竣工验收阶段查看</div>
	</header>
	<div class="aui-content-padded">
        <p>事项对应项目名称：${xmjgjgycjd.sxdyxmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	 事项对应阶段编号：${xmjgjgycjd.sxdyjdbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                  	事项对应阶段名称：${xmjgjgycjd.sxdyjdmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项对应项目编号：${xmjgjgycjd.sxdyxmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项名称：${xmjgjgycjd.sxmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项编号：${xmjgjgycjd.sxbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承若时限：${xmjgjgycjd.cnsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承诺办结时间：${xmjgjgycjd.cnbjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理单位：${xmjgjgycjd.sldw}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否里程碑事项：${xmjgjgycjd.sflcbsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	申请时间：${xmjgjgycjd.sqsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	登记时间：${xmjgjgycjd.djsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理时间：${xmjgjgycjd.slsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结时间：${xmjgjgycjd.bjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结结果：${xmjgjgycjd.bjjg}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>