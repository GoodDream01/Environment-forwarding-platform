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
	    <div class="aui-title">督办信息</div>
	</header>
	<section class="aui-refresh-content">
		<div class="aui-content">
			<div id="demo">
		        
	        </div>
        </div>
    </section>
   <jsp:include page="/WEB-INF/jsp/app/inc/footer.jsp">
    	<jsp:param name="bj" value="dbxx"/>
    </jsp:include>
</body>
<script type="text/javascript">
	
	var dialog = new auiDialog({});
	var toast = new auiToast();
	var pageSize = 20;
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
	
function getListData(flag){
		var wrap = document.getElementById("demo")
		var lis = wrap.querySelectorAll('.aui-card-list');
		var length = lis.length;
		var o = {};
		o.offset = length;
		o.limit = pageSize;
		var json = o;
        $.ajax({
            url: HOST_URL+"/app/xmgl/xmdb/getList.do",
			type: 'post',
		    dataType: 'json',
		    contentType:'application/x-www-form-urlencoded',
            data: json,
            cache: false,
            beforeSend:function(){
           	 	
            },
            complete: function () {
           	 	pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
            },
            success: function (data) {
            	console.log(data);
                if(data){
					for(var i = 0;i<data.length;i++){
						var d = data[i];
						var xxzid = d.xxzid;
						var zfid = d.zfid;
						var dwmc = d.dwmc;
						var jd = d.jd;
						var xmmc = d.xmmc;
						var qsnr = d.qsnr;
						var dbsj = d.dbsj;
						var stepHtml = getStepsHtml(jd,zfid,"1");
						
	                	var html ="";
	                	
						html += "<div class=\"aui-card-list\">";
						html += '<a href=\"<%=request.getContextPath() %>/app/xmgl/xmdb/getById.do?xxzid='+xxzid+'\" class=\"aui-grid-row-item\">'
						html += "	<div class=\"aui-card-list-header\">";
						html +=   "项目名称："+xmmc+"";
						html += "</div>";
						html += "</a>"
						html += "<div class=\"aui-card-list-content-padded\">";
						html +=     "督办内容："+qsnr+"";
						/* html +=     "内容区域，卡片列表布局样式可以实现APP中常见的各类样式"; */
						/* html += stepHtml; */
						html += "</div>";
						html += "<div class=\"aui-card-list-content-padded\">";
						html +=     "发送单位："+dwmc+"";
						/* html +=     "内容区域，卡片列表布局样式可以实现APP中常见的各类样式"; */
						/* html += stepHtml; */
						html += "</div>";
						html += "<div class=\"aui-card-list-content-padded\">";
						html +=    "督办时间："+dbsj+"";
						/* html +=     "内容区域，卡片列表布局样式可以实现APP中常见的各类样式"; */
						/* html += stepHtml; */
						html += "</div>";
						/* html += "<div class=\"aui-card-list-footer\">"; */
						/* html +=     "底部区域"; */
						/* html += "<div class=\"aui-card-list-content-padded\">";
						
						html += "</div>"; */
						html += "</div>";
						//wrap.insertAdjacentHTML('afterbegin', html);
						if(flag && flag=="1"){
							wrap.insertAdjacentHTML('beforeend', html);
						}else{
							wrap.insertAdjacentHTML('afterbegin', html);
						}
						
					}
                }
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                
            }
        });
	};
	
	$(function(){
    	getListData(1);
    });
</script>
</html>