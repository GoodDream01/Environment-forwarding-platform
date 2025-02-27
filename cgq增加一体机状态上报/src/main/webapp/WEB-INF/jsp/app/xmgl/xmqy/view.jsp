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
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" ><!-- style="position:fixed; top:0; left: 0;" -->
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">项目签约阶段信息</div>
	</header>
</body>
 <div class="aui-content-padded">
        <p>签约项目名称：${xmqy.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目签约基本信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=签约" class="aui-grid-row-item">
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
                   	签约项目编号：${xmqy.xmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                            签约日期：${xmqy.qyrq}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	签约地点：${xmqy.qydd}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	签约甲方名称：${xmqy.qyjfmc}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	甲方签约人：${xmqy.jfqyr}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	签约乙方名称：${xmqy.qyyfmc}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	乙方签约人：${xmqy.yfqyr}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	签约活动类型：${xmqy.qyhdlx}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	签约活动名称：${xmqy.qyhdmc}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目建筑面积(亩)：${xmqy.xmjzmj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目占地面积(亩)：${xmqy.xmzdmj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	总投资(内资-亿元)：${xmqy.ztznz}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	总投资(外资-万美元)：${xmqy.ztzwz}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资(内资-亿元)：${xmqy.ntznz}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资(外资-万美元)：${xmqy.ntzwz}
                </div>
            </li>
           <%--  <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资(内资-亿元)：${xmqy.shxy}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资(内资-亿元)：${xmqy.shxy}
                </div>
            </li> --%>
             
            
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>