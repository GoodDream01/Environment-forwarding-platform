//恶劣天气
$(function() {
	var chartDom = document.getElementById('m_topl');
	var myChart = echarts.init(chartDom);
	var option;
	option = {
		xAxis: {
			type: 'category',
			data: ['石家庄', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '保定', '承德', '唐山', '廊坊'],
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
					fontSize: '8',
				}
			},
		},
		yAxis: {
			type: 'value',
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
				}
			},
			splitLine: {
				show: true,
				lineStyle: {
					color: ['#315070'],
					width: 1,
					type: 'solid'
				}　　
			}
		},
		series: [{
			barWidth: 15,
			data: [120, 200, 150, 80, 70, 110, 130, 100, 50, 122, 130, 10],
			type: 'bar',
			itemStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
						offset: 0,
						color: "#364834" // 0% 处的颜色
					}, {
						offset: 0.4,
						color: "#aaaa2f" // 60% 处的颜色
					}, {
						offset: 1,
						color: "#d6cb2d" // 100% 处的颜色
					}], false)
				}
			},
		}, ]
	};
	option && myChart.clear();
	option && myChart.setOption(option);
	var timer = window.setInterval(function() {
		myChart.clear();
		refreshData(option);
	}, 5000);

	function refreshData(data) {
		console.log(data)
		option && myChart.setOption(data);
	}
})
//交通秩序
$(function() {
	var chartDom = document.getElementById('order');
	var myChart = echarts.init(chartDom);
	var option;
	option = {
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: ['石家庄', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '保定', '承德', '唐山', '廊坊'],
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
					fontSize: '8',
				}
			},
		},
		yAxis: {
			type: 'value',
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
				}
			},
			splitLine: {
				show: true,
				lineStyle: {
					color: ['#315070'],
					width: 1,
					type: 'solid'
				}　　
			}
		},
		series: [{
			data: [100, 200, 300, 200, 400, 300, 300, 200, 205, 263, 123],
			type: 'line',
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{

						offset: 0,
						color: 'rgba(43,204,255,0.39)'
					}, {
						offset: .34,
						color: 'rgba(43,204,255,0.5)'
					}, {
						offset: 1,
						color: 'rgba(43,204,255,1)'
					}])
				}
			},

			itemStyle: {
				normal: {
					color: '#15678e', //改变折线点的颜色
					lineStyle: {
						color: '#15678e' //改变折线颜色
					}
				}
			},
		}]
	};
	option && myChart.clear();
	option && myChart.setOption(option);
	var timer = window.setInterval(function() {
		myChart.clear();
		refreshData(option);
	}, 5000);

	function refreshData(data) {
		console.log(data)
		option && myChart.setOption(data);
	}

})

//车流高峰
$(function() {
	loadRingChart(); //加载半圆环形图
	//加载半圆环形图
	function loadRingChart() {
		var myChart = echarts.init(document.getElementById('consume_pie')); //图表的ID
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c}万元"
			},
			legend: {
				x: 'center',
				y: '15%',
				data: ['张家口', '承德', '衡水', '邢台', '邯郸', '保定', '秦皇岛', '石家庄', '唐山', '廊坊', '沧州'],
				icon: 'circle',
				textStyle: {
					color: '#fff',
				}
			},
			calculable: true,
			series: [{
				name: '',
				type: 'pie',
				//起始角度，支持范围[0, 360]
				startAngle: 0,
				//饼图的半径，数组的第一项是内半径，第二项是外半径
				radius: [41, 100.75],
				//支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
				center: ['50%', '40%'],
				//是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
				// 'radius' 面积展现数据的百分比，半径展现数据的大小。
				//  'area' 所有扇区面积相同，仅通过半径展现数据大小
				roseType: 'area',
				//是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: true,
						formatter: '{c}'
					},
					emphasis: {
						show: true
					}
				},
				labelLine: {
					normal: {
						show: true,
						length2: 1,
					},
					emphasis: {
						show: true
					}
				},
				data: [{
						value: 90,
						name: '张家口',
						itemStyle: {
							normal: {
								color: '#4997F3'
							}
						}
					},
					{
						value: 85,
						name: '承德',
						itemStyle: {
							normal: {
								color: '#72BCBE'
							}
						}
					},
					{
						value: 89,
						name: '衡水',
						itemStyle: {
							normal: {
								color: '#7AB666'
							}
						}
					},
					{
						value: 92,
						name: '邢台',
						itemStyle: {
							normal: {
								color: '#EFCA56'
							}
						}
					},
					{
						value: 95,
						name: '邯郸',
						itemStyle: {
							normal: {
								color: '#D0656E'
							}
						}
					},
					{
						value: 91,
						name: '保定',
						itemStyle: {
							normal: {
								color: '#6F60D5'
							}
						}
					},
					{
						value: 88,
						name: '秦皇岛',
						itemStyle: {
							normal: {
								color: '#103777'
							}
						}
					},
					{
						value: 82,
						name: '石家庄',
						itemStyle: {
							normal: {
								color: '#3AAA4A'
							}
						}
					},
					{
						value: 83,
						name: '唐山',
						itemStyle: {
							normal: {
								color: '#19A9D4'
							}
						}
					},
					{
						value: 80,
						name: "廊坊",
						itemStyle: {
							normal: {
								color: '#FFE822'
							}
						},
					},
					{
						value: 90,
						name: "沧州",
						itemStyle: {
							normal: {
								color: '#456296'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
					{
						value: 0,
						name: "",
						itemStyle: {
							normal: {
								color: 'transparent'
							}
						},
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					},
				]
			}]
		};
		myChart.setOption(option);
		window.addEventListener("resize", function() {
			myChart.resize();
		});
	}

})
$(function() {
	var chart = echarts.init(document.getElementById('ditu'));
	// $.getJSON('css/hebei.json', function(data) {
		// echarts.registerMap("河北省", data);
		// var d = [];
		// for(var i = 0; i < data.features.length; i++) {
			// d.push({
				// name: data.features[i].properties.name
			// })
		// }
		// renderMap("河北省", d)
	// });
	var option = {
		title: {
			left: 'center',
			textStyle: {
				color: '#fff',
				fontSize: 16,
				fontWeight: 'normal',
				fontFamily: "Microsoft YaHei"
			},
			subtextStyle: {
				color: '#ccc',
				fontSize: 13,
				fontWeight: 'normal',
				fontFamily: "Microsoft YaHei"
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{b}'
		},
		toolbox: {
			show: true,
			orient: 'vertical',
			left: 'right',
			top: 'center',
			feature: {
				dataView: {
					readOnly: false
				}
			},
			iconStyle: {
				normal: {
					color: '#fff'
				}
			}
		},
		geo:{
			map: '河北',
			aspectScale: 0.65, //长宽比
			zoom: 1.1,
			roam: false,
			itemStyle: {
				normal: {
					areaColor: '#1b4a88',
					shadowColor: '#082c65',
					shadowOffsetX: 0,
					shadowOffsetY: 25,
				},
				emphasis: {
					areaColor: '#2AB8FF',
					borderWidth: 0,
					color: 'green',
					label: {
						show: false,
					},
				},
			},
		},
		series:[{
			name: "河北省",
			type: 'map',
			//mapType: map,
			roam: false,
			nameMap: {},
			label: {
				normal: {
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: 13
					}
				},
				emphasis: {
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: 13
					}
				}
			},
			itemStyle: {
				normal: {
					areaColor: '#0c274b',
					borderColor: '#1ccdfa',
					shadowOffsetx: 0,
				},
				emphasis: {
					areaColor: 'darkorange'
				}
			},
			data: [
				{name:'石家庄',value:'20'},
				{name:'保定',value:'30'},
			]
		}]
	};

	
	chart.clear();
	chart.setOption(option)
	var timer = window.setInterval(function() {
		chart.clear();
		refreshDatas(option);
	}, 10000);

	function refreshDatas(data) {
		console.log(data)
		option && chart.setOption(data);
	}
})
$(function() {
	var chart = echarts.init(document.getElementById('accident'));
	option = {
		xAxis: {
			type: 'category',
			data: ['张家口', '承德', '衡水', '邢台', '邯郸', '保定', '秦皇岛', '石家庄', '唐山', '廊坊', '沧州'],
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
					fontSize: '8',
				}
			},
		},
		yAxis: {
			type: 'value',
			axisLabel: {
				show: true,
				textStyle: {
					color: '#fff',
					fontSize: '8',
				}
			},
			splitLine: {
				show: true,
				lineStyle: {
					color: ['#315070'],
					width: 1,
					type: 'solid'
				}　　
			}
		},
		series: [{
			data: [100, 200, 300, 200, 400, 300, 300, 200, 205, 263, 123],
			type: 'bar',
			itemStyle: {
				normal: {　　　　　　　　 //这里是重点
					color: '#4997f3'
				}
			}
		}]
	};
	option && chart.setOption(option);
})
$(function() {
	var chart = echarts.init(document.getElementById('jindu'));
	option = {
		color: ['#40d738', '#2235d4'],
		tooltip: {
			show: false
		},
		series: [{
			name: '任务进度',
			type: 'pie',
			radius: ['60%', '70%'],
			label: {
				normal: {
					position: 'center'
				}
			},
			hoverAnimation: false,
			data: [{
				value: 20,
				name: '任务进度',
				itemStyle: {
					normal: {
						// color: {
						//     type: 'linear',
						//     x: 0,
						//     y: 0,
						//     x2: 0,
						//     y2: 1,
						//     colorStops: [{
						//         offset: 0, color: '#40d738' // 0% 处的颜色
						//     }, {
						//         offset: 1, color: '#2235d4' // 100% 处的颜色
						//     }],
						//     globalCoord: false // 缺省为 false
						// }
						// }
						//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						//                         offset: 0,
						//                         color: '#'
						//                     }, {
						//                         offset: 0,
						//                         color: '#'
						//                     }, {
						//                         offset: 0,
						//                         color: '#40d738'
						//                     }])
					}
				},
				label: {
					normal: {
						formatter: '{d}%',
						textStyle: {
							color: '#15ce56',
							fontSize: 15,
							fontWeight: 'bold'
						}
					}
				},
			}, {
				value: 80,
				label: {
					normal: {
						textStyle: {
							color: '#a5b3ef',
							fontSize: 14
						}
					}
				},
				itemStyle: {
					normal: {
						color: {
							type: 'linear',
							x: 0,
							y: 0,
							x2: 0,
							y2: 1,
							colorStops: [{
								offset: 0,
								color: '#40d738' // 0% 处的颜色
							}, {
								offset: 1,
								color: '#2235d4' // 100% 处的颜色
							}],
							globalCoord: false // 缺省为 false
						}
					}
				},
			}]
		}]
	};
	option && chart.clear();
	option && chart.setOption(option);
	var timer = window.setInterval(function() {
		chart.clear();
		refreshData(option);
	}, 5000);

	function refreshData(data) {
		console.log(data)
		option && chart.setOption(data);
	}
})