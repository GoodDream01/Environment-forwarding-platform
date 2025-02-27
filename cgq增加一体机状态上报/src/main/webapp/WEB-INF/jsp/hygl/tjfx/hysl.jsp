<%@page import="com.dt.common.utils.ZiDianUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		    	  
				<label >年份：</label>
				<input name="zyear" class="mini-combobox" valueField="ZHIMC" textField="ZHIMC"  showNullItem="true"
				 url="<%=request.getContextPath() %>/common/getZiDianZHI.do?ZDID=<%=ZiDianUtils.PUB_NDXX %>&px=desc"
				 required="true" 
				/>

				<a class="mini-button" style="width:60px;" onclick="search()">查询</a>
				<a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		    </div>
	        
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">          
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
			<div class="echart"  id="chart1" _echarts_instance_="ec_1529397871834" style="width:700px;height:300px; margin: 0 auto;" ></div>

	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

    function getSearchParam(){
    	var o={};
        var zyear = mini.getByName("zyear").getValue();
        o.zyear = zyear;
        
        return o;
    }
    function search() {

        jq_reloadData();
    }
    
    function onKeyEnter(e) {
        search();
    }

    
    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();

	

    // 默认数据源
option = {
    title : {
        text: '各类型会议比例',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series : [
        {
            name: '会议类型',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

var myChart;
function initSSJQView(){
	myChart = ScreenCommon.initEchartWithId("chart1");
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
// 定义数据更新函数
var jq_reloadData = function () {

		var param = getSearchParam();
        /** 1.加载断层数据*/
        $.ajax({
            url: HOST_URL + "/tjfx/hysl/getTjcxHyslPieChart1.do?format=json",
            type: 'get',
            data:param,
            dataType:"json",
            success: function (result) {console.log(result);
                if (result) {
                	var op = result.tjcxHysl.option;
                	var xAxis = op.xAxis;
                	
                	var legend = op.legend;
                	var series = op.series;

                	//option.xAxis[0].data = xAxis[0].data;
                	option.legend.data = legend.data;
/*                 	
                	var temp = [];
                	for(var i=0;i<series.length;i++){
                		var serie = series[i];
                		var o = {
               				data:serie.data,
               				type:serie.type,
               				//name:serie.name
                		};
                		temp.push(o);
                	} */
                	option.series[0].data = series[0].data;
                	console.log(option);
                    initSSJQView();
                }
            },
            error: function () {
                
                initSSJQView();
            }
        });
    };

    jq_reloadData();

    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>