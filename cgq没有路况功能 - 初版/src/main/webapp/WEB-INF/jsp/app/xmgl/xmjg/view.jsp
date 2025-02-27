<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
<%
String zfid = request.getParameter("zfid");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" >
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">项目竣工信息查看</div>
	</header>
	<div class="aui-content-padded">
        <p>竣工项目名称*：${xmjg.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目竣工信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=竣工" class="aui-grid-row-item">
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
                   	竣工项目编号*：${xmjg.xmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                                      计划完工日期：${xmjg.jhwgrq}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	竣工状态：${xmjg.jgzt}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>