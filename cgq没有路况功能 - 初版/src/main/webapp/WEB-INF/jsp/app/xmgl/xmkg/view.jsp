<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
<%
String zfid = request.getParameter("zfid");
%>
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" >
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">项目开工信息查看</div>
	</header>
	<div class="aui-content-padded">
        <p>开工项目名称*：${xmkg.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目开工信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=开工" class="aui-grid-row-item">
            	 <i></i>
            	 <%
            	 if(USERINFO_ROLE.equals("2")){
        		 %>
	                    <div class="aui-grid-label">督办此阶段</div>
	             <%} %>
            	 </a>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                   	开工项目编号*：${xmkg.xmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                                      开工日期：${xmkg.kgrq}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	未按计划开工原因：${xmkg.wajhkgyy}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	当年计划投资（亿元）：${xmkg.jhnddzje}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	计划开工日期：${xmkg.jhkgrq}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	群众工作及土地补偿情况：${xmkg.qzgzjtdbcqk}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟采取措施：${xmkg.ncqcs}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>