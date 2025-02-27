<%@page import="com.dt.common.utils.ComUtils"%>
<%@page import="com.dt.zdxmdb.mvc.xmgl.pojo.Sgxkjdb"%>
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
Sgxkjdb sgxkjdb = (Sgxkjdb)request.getAttribute("sgxkjdb");
%>
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
	    <div class="aui-title">施工许可阶段查看</div>
	</header>
	<div class="aui-content-padded">
        <p>项目名称：${sgxkjdb.xmmc}</p>
    </div>
    <div class="aui-content-padded">
        <p>项目编号：${sgxkjdb.xmbh}</p>
    </div>
    <div class="aui-content aui-margin-b-15">
        <ul class="aui-list aui-list-in">
            <li class="aui-list-header">
            	施工许可信息
            	<a href="<%=request.getContextPath() %>/app/xmgl/xmdb/getAdd.do?zfid=<%=zfid %>&jd=施工许可" class="aui-grid-row-item">
            	 <i></i>
            	 <%
            	 if(USERINFO_ROLE.equals("2")){
        		 %>
	                    <div class="aui-grid-label">督办此阶段</div>
	             <%} %>
            	 </a>
            </li>
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

function getListData(){
	var wrap = document.getElementById("demo")
	var lis = wrap.querySelectorAll('.aui-card-list');
	var length = lis.length;
	var value ='<%=sgxkjdb.getZfid()%>';
	var o = {};
	var json = o;
    $.ajax({
    	url: "<%=request.getContextPath() %>/app/xmgl/sgxkjd/getSxList.do?zfid="+value,
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
                	var html ="";
                	
                	html += "<div class=\"aui-card-list\">";
					html += '<a href=\"<%=request.getContextPath() %>/app/xmgl/sgxkjd/getBId.do?zid='+zid+'\" class=\"aui-grid-row-item\">'
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