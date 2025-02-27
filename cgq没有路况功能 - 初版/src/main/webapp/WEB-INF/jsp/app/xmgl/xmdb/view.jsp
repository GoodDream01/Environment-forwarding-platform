<%@page import="com.dt.zdxmdb.mvc.xmgl.pojo.Xmdb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
	Xmdb xmdb = (Xmdb)request.getAttribute("xmdb");
%>
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
	    <div class="aui-title">督办信息查看</div>
	</header>
	<form action="<%=request.getContextPath() %>/app/xmgl/xmdb/hf.do" method="get" class="from">
	<div class="aui-content-padded">
		<input type="hidden" value="${xmdb.xxzid}" name="xxzid">
        <p>督办单位：${xmdb.dwmc}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	督办信息
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		项目名称:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" value="${xmdb.xmmc}" name="xmmc"/>
		                </div>
		            </div> 
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                    	回复时限:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="hfsx" value="<fmt:formatDate value='${xmdb.hfsx}'  type='both' />">
		                </div>
		            </div>
            </li>
            <li class="aui-list-item">
               <div class="aui-list-item-inner">
		             <div class="aui-list-item-label">
		                   		派件人:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="pjr" value="${xmdb.pjr }"/>
		                </div>
		            </div> 
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
		             <div class="aui-list-item-label">
		                   		督办内容:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="qsnr">${xmdb.qsnr }</textarea>
		                </div>
		            </div> 
            </li>
            <%
    	 if(USERINFO_ROLE.equals("3")||USERINFO_ROLE.equals("4")){
    		
    		 if(!xmdb.getQszt().equals("1")){
    	%>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
		             <div class="aui-list-item-label">
		                   		回复时间:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="hfsj" value="${xmdb.hfsj }">
		                </div>
		            </div> 
            </li>
            <%}
    	}
    		%>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
		             <div class="aui-list-item-label">
		                   		回复内容:
		                </div>
		                <div class="aui-list-item-input">
		                    <textarea type="text" name="hfnr" value="${xmdb.hfnr }"></textarea>
		                </div>
		            </div> 
            </li>
        </ul>
    </div>
    	<%
    	 if(USERINFO_ROLE.equals("3")||USERINFO_ROLE.equals("4")){
    		
    		 if(xmdb.getQszt().equals("1")){
    	%>
    		<div class="aui-content-padded">
	        	 <input class="aui-btn aui-btn-block aui-btn-info"  type="submit" value="发送">
			</div>
    	<%}
    	}
    		%>
    </form>	
</body>
</html>