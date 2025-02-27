<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" ><!-- style="position:fixed; top:0; left: 0;" -->
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">项目超期预警查看</div>
	</header>
 <div class="aui-content-padded">
        <p>项目名称：${xmjbxx.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目基本信息
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                   	项目编号：${xmjbxx.xmbh}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                                           所属区县：${xmjbxx.ssxq}
                </div>
            </li> 
            <%-- <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	责任单位：${xmjbxx.zrdw}
                </div>
            </li>
              <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	责任人：${xmjbxx.fzr}
                </div>
            </li> 
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	联系方式：${xmjbxx.lxfs}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	预警发送时间：<fmt:formatDate value="${xmjbxx.fssj}"  type="both" />
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	预警内容：${xmjbxx.dbnr}
                </div>
            </li> --%>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>