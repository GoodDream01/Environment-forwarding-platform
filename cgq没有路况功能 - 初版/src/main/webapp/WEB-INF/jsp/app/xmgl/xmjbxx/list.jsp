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
	<section class="aui-refresh-content">
	    <div class="aui-searchbar" id="search">
	        <div class="aui-searchbar-input aui-border-radius">
	            <i class="aui-iconfont aui-icon-search"></i>
	            <input name="xmmc" value="<%=xmmc %>" type="search" placeholder="请输入搜索内容" id="search-input">
	            <div class="aui-searchbar-clear-btn">
	                <i class="aui-iconfont aui-icon-close"></i>
	            </div>
	             
	        </div>
			<div class="aui-searchbar-btn" tapmode>取消</div>
	    </div>
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
	apiready = function(){
	    api.parseTapmode();
	}
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
                	if(data.length==0){
                		var html ="";
                		html += "<div>无相关数据项目";
                		html += "</div>";
                		wrap.insertAdjacentHTML('afterbegin', html);
                	}else{                	
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
							//wrap.insertAdjacentHTML('afterbegin', html);
							if(flag && flag=="1"){
								wrap.insertAdjacentHTML('beforeend', html);
							}else{
								wrap.insertAdjacentHTML('afterbegin', html);
							}
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
    
    /***********************/
    var searchBar = document.querySelector(".aui-searchbar");
    var searchBarInput = document.querySelector(".aui-searchbar input");
    var searchBarBtn = document.querySelector(".aui-searchbar .aui-searchbar-btn");
    var searchBarClearBtn = document.querySelector(".aui-searchbar .aui-searchbar-clear-btn");
    if(searchBar){
        searchBarInput.onclick = function(){
            searchBarBtn.style.marginRight = 0;
        }
        searchBarInput.oninput = function(){
            if(this.value.length){
                searchBarClearBtn.style.display = 'block';
                searchBarBtn.classList.add("aui-text-info");
                searchBarBtn.textContent = "搜索";
            }else{
                searchBarClearBtn.style.display = 'none';
                searchBarBtn.classList.remove("aui-text-info");
                searchBarBtn.textContent = "取消";
            }
        }
    }
    searchBarClearBtn.onclick = function(){
        this.style.display = 'none';
        searchBarInput.value = '';
        searchBarBtn.classList.remove("aui-text-info");
        searchBarBtn.textContent = "取消";
    }
    searchBarBtn.onclick = function(){
        var keywords = searchBarInput.value;
        if(keywords.length){
        	if(searchBarBtn.textContent == "取消"){
        		keywords = '';
        	}
            searchBarInput.blur();
            //document.getElementById("search-keywords").textContent = keywords;
            var url ="<%=request.getContextPath() %>/app/xmgl/xmjbxx/listUi.do?xmmc="+keywords;
            window.location.href=url;
        }else{
            this.style.marginRight = "-"+this.offsetWidth+"px";
            searchBarInput.value = '';
            searchBarInput.blur();
        }
    }
    /***********************/
</script>
</body>
</html>