<%@page import="com.dt.common.utils.ComUtils"%>
<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
    <header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" ><!-- style="position:fixed; top:0; left: 0;" -->
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">项目搜索</div>
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
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		 时间段:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="date" name="minda">---<input type="date" name="maxda">
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		 所属县区:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="ssxq" id="ssxq"  >
	                       		<option value=""></option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		是否"4+4"产业:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="issjscybh" id="issjscybh"  >
	                       		<option value=""></option>
	                       		<option value="1">是</option>
	                       		<option value="0">否</option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		所属项目阶段:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="xmjdzt" id="xmjdzt"  >
	                       		<option value=""></option>
	                       		<option value="1101">谋划</option>
	                       		<option value="1201">签约</option>
	                       		<option value="1301">立项</option>
	                       		<option value="1401">规划许可阶段</option>
	                       		<option value="1402">建设许可阶段</option>
	                       		<option value="1403">施工许可阶段</option>
	                       		<option value="1501">开工</option>
	                       		<option value="1601">建设</option>
	                       		<option value="1701">竣工验收阶段</option>
	                       		<option value="1702">竣工</option>
	                       		<option value="1801">入统</option>
	                       		<option value="1901">自评</option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		是否市重点:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="isshizdxmbh" id="isshizdxmbh"  >
	                       		<option value=""></option>
	                       		<option value="1">是</option>
	                       		<option value="0">否</option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		是否省重点:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="isshengzdxmbh" id="isshengzdxmbh"  >
	                       		<option value=""></option>
	                       		<option value="1">是</option>
	                       		<option value="0">否</option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		预警状态:
		                </div>
		                <div class="aui-list-item-input">
		                    <select name="yjzt" id="yjzt"  >
	                       		<option value=""></option>
	                       		<option value="1">已办理完结</option>
	                       		<option value="3">接近办理期限</option>
	                       		<option value="4">已超过办理期限</option>
	                  		 </select>
		                </div>
		            </div> 
	            </li>
	     	 </ul>
	     </div>   
	    <div class="aui-content-padded">
	         <input class="aui-btn aui-btn-block aui-btn-info"  type="submit" value="搜索">
	    </div>
    </form> 

</body>
<script type="text/javascript">
	
$(function (){
	var value =$("#ssxq");
	var o = {};
	var json = o;
	 $.ajax({
            url: "<%=request.getContextPath() %>/base/unit/getXsqList.do",	           
			type: 'post',
		    dataType: 'json',
		    contentType:'application/x-www-form-urlencoded',
		    data: json,
            cache: false,	           
            success: function (data) {
            	console.log(data);
                if(data){
                	var html ="<option value=''></option>";
					for(var i = 0;i<data.length;i++){
						var d = data[i];
						var name = d.zname;
						alert(name);
						html+="<option value='"+name+"'>"+name+"</option>";
					}
					value.empty().html(html);
                }
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                
            }
        });

})
	
</script>
</html>