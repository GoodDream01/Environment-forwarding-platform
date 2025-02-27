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
	    <div class="aui-title">项目临近预警</div>
	</header>
	<section class="aui-refresh-content">
		<div class="aui-content">
			<div id="demo">
		        
	        </div>
        </div>
    </section>
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
		var json = o;
        $.ajax({
            url: HOST_URL+"/app/xmgl/zddb/zddbQueryWcq.do",
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
						var xmbh = d.xmbh;
						var xmmc = d.xmmc;
						var fssj = d.fssj;
						var jdzt = d.xmjdzt;
						var date = GetCntsNum(d); 
						var date1 = GetLjcqtsNum(d);
						jdzt = GetReJdmc(jdzt);
	                	var html ="";
	                	
						html += "<div class=\"aui-card-list\">";
						html += '<a href=\"<%=request.getContextPath() %>/app/xmgl/xmjbxx/getById.do?zid='+zid+'\" class=\"aui-grid-row-item\">'
						html += "	<div class=\"aui-card-list-header\">";
						html +=    "项目名称："+xmmc+"";
						html += "</div>";
						html += "</a>"
						html += "<div class=\"aui-card-list-content-padded\">";
 						html +=    "进度状态："+jdzt+"";
 						html += "</div>";
 						html += "<div class=\"aui-card-list-content-padded\">";
 						html +=    "承诺时长："+date+"";
 						html += "</div>";
 						html += "<div class=\"aui-card-list-content-padded\">";
 						html +=    "距超期天数："+date1+"";
 						html += "</div>";
 						/* html += "<div class=\"aui-card-list-footer\">";
 						html +=     "底部区域";
 						html += "</div>"; */
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