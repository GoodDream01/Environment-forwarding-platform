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
	    <div class="aui-title">施工许可阶段查看</div>
	</header>
	<div class="aui-content-padded">
        <p>事项对应项目名称：${xmspsgxkjd.sxdyxmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	 事项对应阶段编号：${xmspsgxkjd.sxdyjdbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                  	事项对应阶段名称：${xmspsgxkjd.sxdyjdmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项对应项目编号：${xmspsgxkjd.sxdyxmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项名称：${xmspsgxkjd.sxmc}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	事项编号：${xmspsgxkjd.sxbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承若时限：${xmspsgxkjd.cnsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	承诺办结时间：${xmspsgxkjd.cnbjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理单位：${xmspsgxkjd.sldw}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否里程碑事项：${xmspsgxkjd.sflcbsx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	申请时间：${xmspsgxkjd.sqsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	登记时间：${xmspsgxkjd.djsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	受理时间：${xmspsgxkjd.slsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结时间：${xmspsgxkjd.bjsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	办结结果：${xmspsgxkjd.bjjg}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>