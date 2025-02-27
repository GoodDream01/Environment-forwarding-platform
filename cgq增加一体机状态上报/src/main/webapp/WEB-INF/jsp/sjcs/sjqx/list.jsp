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
    <label>设备编号：</label>
    <input name="sbid" class="mini-combobox" valueField="name" textField="name"/>
    <label>时间：</label>
    <input name="kssj" class="mini-datepicker"/>~<input name="jssj" class="mini-datepicker"/>
    <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
</div>
<div id="main" style="text-align:center;height:700px;width:1400px;"></div>
<script type="text/javascript">
	var chartDom = document.getElementById('main');
	var myChart = echarts.init(chartDom);
    var colors = ['#5470C6', '#91CC75', '#EE6666', '#d92fee'];

	mini.parse();
	$.ajax({
		url: HOST_URL+"/sjcs/sjqx/getSb.do",
		type: 'post',
		dataType: 'json',
		contentType:'application/json;charset=UTF-8',
		cache: false,
		success: function (res) {
			mini.getbyName("sbid").setData(res);
		}
	});

	function search() {
		var sbid = mini.getByName("sbid").getValue();
		var kssj = mini.getByName("kssj").getFormValue();
		var jssj = mini.getByName("jssj").getFormValue();
		$.ajax({
			url: HOST_URL+"/sjcs/sjqx/getTj.do?sbid="+sbid+"&kssj="+kssj+"&jssj="+jssj,
			type: 'get',
			dataType: 'json',
			contentType:'application/json;charset=UTF-8',
            cache: false,
            async: false,
			success: function (res) {
			    console.log(res)
                var option = {
                    color: colors,
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross'
                        }
                    },
                    grid: {
                        right: '20%'
                    },
                    toolbox: {
                        feature: {
                            dataView: { show: true, readOnly: false },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    legend: {
                        data: ['温度', '湿度', '压力', '风速']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                alignWithLabel: true
                            },
                            data: res.names
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '温度',
                            position: 'right',
                            alignTicks: true,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[0]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} °C'
                            }
                        },
                        {
                            type: 'value',
                            name: '湿度',
                            position: 'right',
                            alignTicks: true,
                            offset: 80,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[1]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} °C'
                            }
                        },
                        {
                            type: 'value',
                            name: '压力',
                            position: 'left',
                            alignTicks: true,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[2]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} pa'
                            }
                        },
                        {
                            type: 'value',
                            name: '风速',
                            position: 'left',
                            alignTicks: true,
                            offset: 80,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[3]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} m/s'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '温度',
                            type: 'line',
                            data: res.values1
                        },
                        {
                            name: '湿度',
                            type: 'line',
                            yAxisIndex: 1,
                            data: res.values2
                        },
                        {
                            name: '压力',
                            type: 'line',
                            yAxisIndex: 2,
                            data: res.values3
                        },
                        {
                            name: '风速',
                            type: 'line',
                            yAxisIndex: 3,
                            data: res.values4
                        }
                    ]
                };
                option && myChart.setOption(option);
			}
		});
	}

</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>