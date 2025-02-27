<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
   	<label>时间：</label>
    <input id="kssj" class="mini-datepicker"/>~<input id="jssj" class="mini-datepicker"/>
   	<label>设备：</label>
   	<input id="sbbh" class="mini-combobox" url="<%=request.getContextPath() %>/cgq/sbgl/selectAll.do"
           textField="dz" valueField="sbbh" style="width:300px;"/>
    <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
</div>
<div id="container" style="height: 700px"></div>
</body> 
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js"></script>  
<script type="text/javascript">
	mini.parse();
	
	var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
	var option;
	
	$(function(){
		mini.get("sbbh").select(0);
		search();
	})
	
	function search(){
		var sbbh = mini.get("sbbh").getValue();
		var kssj = mini.get("kssj").getFormValue();
		var jssj = mini.get("jssj").getFormValue();
		$.ajax({
	        url: HOST_URL+"/cgq/sjtj/findFlxx.do?sbbh="+sbbh+"&kssj="+kssj+"&jssj="+jssj,
			type: 'post',
			dataType: 'json',
			contentType:'application/json;charset=UTF-8',
	        cache: false,
	        async: false,
	        success: function (res) {
	        	console.log(res);
	        	option = {
	    			  tooltip: {
	    			    trigger: 'axis'
	    			  },
	       			  grid: {
	       			    left: '3%',
	       			    right: '4%',
	       			    bottom: '3%',
	       			    containLabel: true
	       			  },
	       			  xAxis: {
	       			    type: 'category',
	       			    data:res.names
	       			  },
	       			  yAxis: {
	       			    type: 'value',
		       			min:0,
		                max:10,
		                splitNumber : 10,
		                minInterval:1
	       			  },
	       			  series: [
	       			    {
	       			      data: res.data,
	       			      type: 'line',
	       			      smooth: true
	       			    }
	       			  ]
	       			};
	       			option && myChart.setOption(option);
	         },
	         error: function (jqXHR, textStatus, errorThrown) {
	             alert(jqXHR.responseText);
	         }
	     });
	}
	
</script>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>
