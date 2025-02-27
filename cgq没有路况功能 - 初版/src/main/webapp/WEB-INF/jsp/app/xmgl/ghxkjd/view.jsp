<%@page import="com.dt.common.utils.ComUtils"%>
<%@page import="com.dt.zdxmdb.mvc.xmgl.pojo.Ghxkjdb"%>
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
<%
Ghxkjdb ghxkjdb = (Ghxkjdb)request.getAttribute("ghxkjdb");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" >
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">规划许可阶段查看</div>
	</header>
	<div class="aui-content-padded">
        <p>项目名称：${ghxkjdb.xmmc}</p>
    </div>
    <div class="aui-content-padded">
        <p>项目编号：${ghxkjdb.xmbh}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	规划许可信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=规划许可" class="aui-grid-row-item">
            	 <i></i>
            	 <%
            	 if(USERINFO_ROLE.equals("2")){
        		 %>
	                    <div class="aui-grid-label">督办此阶段</div>
	             <%} %>
            	 </a>
            </li>
           <%--  <li class="aui-list-item">
                <div class="aui-list-item-inner">
                	 项目编号：${ghxkjdb.xmbh}
                </div>
            </li>  --%>           
        </ul>
    </div>
    <section class="aui-refresh-content">
		<div class="aui-content">
			<div id="demo">
		        
	        </div>
        </div>
    </section>
<script type="text/javascript">
$(function(){
	getListData();
});

/* var dialog = new auiDialog({});
var toast = new auiToast();
var pageSize = 10;
var pageNo = 1;
var pullRefresh = new auiPullToRefresh({
	container: document.querySelector('.aui-refresh-content'),
	triggerDistance: 100
},function(ret){
	if(ret.status=="success"){
		setTimeout(function(){
			getListData();
			
		},1500);
	}
}); */
function getListData(){
	var wrap = document.getElementById("demo")
	var lis = wrap.querySelectorAll('.aui-card-list');
	var length = lis.length;
	var value ='<%=ghxkjdb.getZfid()%>';
	var o = {};
	var json = o;
    $.ajax({
    	url: "<%=request.getContextPath() %>/app/xmgl/ghxkjd/getSxList.do?zfid="+value,
		type: 'post',
	    dataType: 'json',
	    contentType:'application/x-www-form-urlencoded',
        data: json,
        cache: false,
        beforeSend:function(){
       	 	/* toast.loading({
                title:"加载中...",
                duration:2000
            }); */
        },
        complete: function () {
       	 	/* toast.hide(); */
       	 	pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
        },
        success: function (data) {
            if(data){
				for(var i = 0;i<data.data.length;i++){
					var d = data.data[i];
					var zid = d.zid;
					var zfid = d.zfid;
					var sxdyxmmc = d.sxdyxmmc;
					//alert(sxdyxmmc);
                	var html ="";
                	
                	html += "<div class=\"aui-card-list\">";
					html += '<a href=\"<%=request.getContextPath() %>/app/xmgl/ghxkjd/getBId.do?zid='+zid+'\" class=\"aui-grid-row-item\">'
					html += "	<div class=\"aui-card-list-header\">";
					html +=    "事项名称："+sxdyxmmc+"";				
					html += "</div>";
					 html += "</a>"
					html += "</div>";
					wrap.insertAdjacentHTML('afterbegin', html);
				}
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        }
    });
};


	
</script>
</body>
</html>