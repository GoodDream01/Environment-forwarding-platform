<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
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
	    <div class="aui-title">项目信息查看</div>
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
                                                           项目负责人：${xmjbxx.xmfzr}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	所属县区：${xmjbxx.ssxq}
                </div>
            </li>
              <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	县市、（区）区责任人：
                </div>
            </li> 
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主管部门责任人：${xmjbxx.zgbmzrr}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	主管部门责任人电话：${xmjbxx.zgbmzrrdh}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目单位责任人：${xmjbxx.xmdwzrr}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目单位责任人联系电话：${xmjbxx.xmdwzrrdh}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否工程建设类项目：${xmjbxx.isgcjslxmbh}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否为开发区项目：${xmjbxx.isgcjslxm}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否为京津项目：${xmjbxx.isjjxm}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	投资来源：${xmjbxx.tzly}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	产业：${xmjbxx.chanye}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否为省重点项目：${xmjbxx.sfzdxm}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	是否为市重点项目：${xmjbxx.sfzdxm}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	所在项目计划：${xmjbxx.szxmjhids}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	总投资金额（亿元）：${xmjbxx.ztzje}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	拟投资金额：${xmjbxx.ntzje}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	建设年限：${xmjbxx.jsnx}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目内容：${xmjbxx.xmnr}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目进度：${xmjbxx.xmjdzt}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	项目特点：${xmjbxx.xmtd}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	建设地点：${xmjbxx.jsdd}
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	建设规模：${xmjbxx.jsgm}
                </div>
            </li>
             <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	业主单位情况简介：${xmjbxx.yzdwxx}
                </div>
            </li>
        </ul>
    </div>
<script type="text/javascript">
</script>
</body>
</html>