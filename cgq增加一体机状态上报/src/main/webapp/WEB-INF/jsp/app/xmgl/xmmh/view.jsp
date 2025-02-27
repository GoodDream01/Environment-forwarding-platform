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
	    <div class="aui-title">项目谋划阶段信息</div>
	</header>
</body>
 <div class="aui-content-padded">
        <p>谋划项目名称：${xmmh.xmmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	项目谋划基本信息
            	
            	 <a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=谋划" class="aui-grid-row-item">
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
                   	谋划项目编号：${xmmh.xmbh}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                                             总投资(亿元)：${xmmh.ztz}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟引进投资(亿元)：${xmmh.nyjzc}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主要资金来源(国家地区)：${xmmh.zyzjlygj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主要资金来源(省份信息)：${xmmh.zyzjlysf}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主要资金来源(地市级信息)：${xmmh.zyzjlydsj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主要资金来源(县市区信息)：${xmmh.zyzjlyxsj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	投资方名称：${xmmh.tzfmc}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资额(内资-亿元)：${xmmh.ntzenz}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资额(外资-万美元)：${xmmh.ntzewz}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	税金(亿元/年)：${xmmh.sj}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	利润(亿元/年)：${xmmh.lr}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目所在县市区：${xmmh.xsq}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	社会效益：${xmmh.shxy}
                </div>
            </li>
             
            
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>