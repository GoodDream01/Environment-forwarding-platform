<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.common.utils.ZiDianUtils"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	    <div class="aui-title">督办意见</div>
	</header>
	<form action="<%=request.getContextPath() %>/app/xmgl/xmdb/add.do" method="post" class="from">
		<div class="aui-content-padded">
	        <input type="hidden" value="${map.zfid}" name="zfid">
		 <input type="hidden" value="${map.jd}" name="jd">
	    </div>
	    <div class="aui-content aui-margin-b-15">
	        <ul class="aui-list aui-list-in">
	            <li class="aui-list-header">
	            	项目督办信息
	            </li>
	        	<li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		 督办编号:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" value="${map.bh}" name="dbbh">
		                </div>
		            </div> 
	            </li>
	           <li class="aui-list-item">
	            <div class="aui-list-item-inner">
	                <div class="aui-list-item-label">
	                    	督办部门:
	                </div>
	                  	<div class="aui-list-item-input">
	                   		<select name="dbdwid" id="dbdwid"  >
	                       		
	                  		 </select>
	               		</div>            	
	                </div>
	            </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label" style="width:500px;">
		                    	回复时限（天）:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="number" name="hfsx" value="1">
		                </div>
		            </div>
	       		 </li>
	            <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		派件人:
		                </div>
		                <div class="aui-list-item-input">
		                    <input type="text" name="pjr"  value="${map.pjr}">
		                </div>
		            </div> 
	            </li>
	             <li class="aui-list-item">
		            <div class="aui-list-item-inner">
		                <div class="aui-list-item-label">
		                   		督办内容:
		                </div>
		                <div class="aui-list-item-input">
		                    <textarea  name="qsnr"></textarea>
		                    <!-- <input type="text-area" style="width:500px;height:50px;" name="qsnr"> -->
		                </div>
		            </div> 
	            </li>           
	     	 </ul>
	     </div>   
	    <div class="aui-content-padded">
	         <input class="aui-btn aui-btn-block aui-btn-info"  type="submit" value="发送">
	    </div>
    </form>
</body>
<script type="text/javascript">
	apiready = function () {
	    api.parseTapmode();
	}
	var dialog = new auiDialog();
	  $(function (){
		var value =$("#dbdwid");
		var o = {};
		var json = o;
		 $.ajax({
	            url: HOST_URL+"/jcsj/cbdw/getList.do?isLimit=false",	           
				type: 'post',
			    dataType: 'json',
			    contentType:'application/x-www-form-urlencoded',
			    data: json,
	            cache: false,	           
	            success: function (data) {
	                if(data.data){
	                	var html ="";
						for(var i = 0;i<data.data.length;i++){
							var d = data.data[i];
							var dwid = d.ZCODE;
							var name = d.ZNAME;
							var tel = d.ZTEL;
							var linkman = d.ZLINKMAN;              	
							console.log(dwid);
							html+="<option value='"+dwid+"-"+name+"-"+tel+"-"+linkman+"'>"+name+"-"+tel+"</option>";
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