<%@page import="com.dt.common.utils.ComUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String bj = ComUtils.ConvNull(request.getParameter("bj"),"shouye");
    %>
    <footer class="aui-bar aui-bar-tab" id="footer">
        <div class="aui-bar-tab-item <%if(bj.equals("shouye")) {%>aui-active<%} %>" tapmode onclick="goLink('<%=request.getContextPath()%>/app/main/index.do');">
            <i class="aui-iconfont aui-icon-home"></i>
            <div class="aui-bar-tab-label">首页</div>
        </div>
        <div class="aui-bar-tab-item <%if(bj.equals("my")) {%>aui-active<%} %>" tapmode onclick="goLink('<%=request.getContextPath()%>/app/my/index.do');">
            <i class="aui-iconfont aui-icon-my"></i>
            <div class="aui-bar-tab-label">我的</div>
        </div>
    </footer>