<%@page import="com.dt.common.utils.ComUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<%
request.setCharacterEncoding("UTF-8");
String xmjdzt = ComUtils.ConvNull(request.getParameter("xmjdzt"));
String xmmc = ComUtils.ConvNull(request.getParameter("xmmc"));
String minda = ComUtils.ConvNull(request.getParameter("minda"));
String maxda = ComUtils.ConvNull(request.getParameter("maxda"));
String ssxq = ComUtils.ConvNull(request.getParameter("ssxq"));
String issjscybh = ComUtils.ConvNull(request.getParameter("issjscybh"));
String isshizdxmbh = ComUtils.ConvNull(request.getParameter("isshizdxmbh"));
String isshengzdxmbh = ComUtils.ConvNull(request.getParameter("isshengzdxmbh"));
String yjzt = ComUtils.ConvNull(request.getParameter("yjzt"));
%>
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
	    <div class="aui-title">项目信息</div>
	</header>
	<form action="<%=request.getContextPath() %>/app/xmgl/xmjbxx/listUi.do" method="get" class="from">
	    <div class="aui-content aui-margin-b-15">
	        <ul class="aui-list aui-list-in">
	        	<li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		 项目名称:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="xmmc">
		                </div>
		            </div> 
	            </li>
	     	 </ul>
	     </div>   
	    <div class="aui-content-padded">
	         <input class="aui-btn aui-btn-block aui-btn-info"  type="submit" value="搜索">
	    </div>
    </form> 
	<section class="aui-refresh-content">
		<div class="aui-content">
			<div id="demo">
		        
	        </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/jsp/app/inc/footer.jsp">
    	<jsp:param name="bj" value="xmxx"/>
    </jsp:include>
</body>
<script type="text/javascript">
	var dialog = new auiDialog({});
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
	});
	function getListData(){
		
		var wrap = document.getElementById("demo")
		var lis = wrap.querySelectorAll('.aui-card-list');
		var length = lis.length;
		var o = {};
		o.offset = length;
		o.limit = pageSize;
		o.xmjdzt = '<%=xmjdzt%>';
		o.xmmc = '<%=xmmc%>';
		o.minda = '<%=minda%>';
		o.maxda = '<%=maxda%>';
		o.ssxq = '<%=ssxq%>';
		o.issjscybh = '<%=issjscybh%>';
		o.isshizdxmbh = '<%=isshizdxmbh%>';
		o.isshengzdxmbh = '<%=isshengzdxmbh%>';
		o.yjzt = '<%=yjzt%>';
		var json = o;
        $.ajax({
            url: HOST_URL+"/app/xmgl/xmjbxx/getList.do",
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
					for(var i = 0;i<data.length;i++){
						var d = data[i];
						var zid = d.zid;
						var xmbh = d.xmbh;
						var xmmc = d.xmmc;
						var xmdm = d.xmdm;
						var xmjdzt = d.xmjdzt;
						var xqmc = d.xqmc;
						var ssxq = d.ssxq;
						var xmnr = d.xmnr;
						var xmnrStr = xmnr||"";
						if(xmnr && xmnr.length>100){
							xmnrStr = xmnr.substring(0,100)+"...";
						}
						
						var stepHtml = getStepsHtml(xmjdzt,zid,"1");
						
						var contentHtml = "";
						contentHtml+="所属县区："+ssxq;
						contentHtml+="<br/>项目内容："+xmnrStr;
						
	                	var html ="";
	                	
						html += "<div class=\"aui-card-list\">";
						html += "<a href=\"<%=request.getContextPath() %>/app/xmgl/xmjbxx/getById.do?zid="+zid+"\" class=\"aui-grid-row-item\">";
						html += "	<div class=\"aui-card-list-header\">";
						html +=    ""+xmmc+"";
						html += "</div>";
						html += "</a>"
						html += "<div class=\"aui-card-list-content-padded\">";
						html += contentHtml;
						html += "<div style=\"width:100%;overflow-x:scroll;\">";
						html += stepHtml;
						html += "</div>";
						html += "</div>";
						//html += "<div class=\"aui-card-list-footer\">";
						//html +=     "底部区域";
						//html += "</div>";
						html += "</div>";
						wrap.insertAdjacentHTML('afterbegin', html);
					}
                }
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                
            }
        });
	};
    $(function(){
    	getListData();
    });
</script>
</body>
</html>