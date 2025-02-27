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
	    <div class="aui-title">项目建设信息查看</div>
	</header>
	<div class="aui-content-padded">
        <p>建设项目名称*：${xmjs.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目建设信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=建设" class="aui-grid-row-item">
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
                   	建设项目编号*：${xmjs.xmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                                      截止到上年底完成投资（亿元）：${xmjs.jzdsndwctz}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	当年工作目标：${xmjs.dngzmb}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	新增建设用地面积(亩)：${xmjs.xzjsydmj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	往年已落实用地指标(亩)：${xmjs.wnylsydzb}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	当年急需用地(亩)：${xmjs.dnjxyd}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	当年省安排用地(亩)：${xmjs.dnsapyd}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	当年所在县市配套安排用地(亩)：${xmjs.dnszxsptapyd}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	能落实占补平衡指标数量：${xmjs.nlszbphzbsl}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	自年初累计完成投资（亿元）：${xmjs.zncljwctz}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	占年计划百分比（%）：${xmjs.znjhbfb}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	完成围墙围建：${xmjs.wcwqwj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	完成场地平整：${xmjs.wccdpz}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	完成施工设计：${xmjs.wcsgsj}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	完成招投标：${xmjs.wcztb}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	竣工状态：${xmjs.jgzt}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>