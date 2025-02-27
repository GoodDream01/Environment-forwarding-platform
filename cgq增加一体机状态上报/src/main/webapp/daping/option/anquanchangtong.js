var anquanchangtongOption = {
    shigushuOption: function() {
        return {
            grid: {
                show: true,
                top: 20,
                left: 30,
                bottom: 50,
                right: 40,
                borderWidth: 0,
            },
            tooltip : {
                show:true,
                triggerOn:'mousemove',
                trigger: 'axis',
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        type: 'dashed',
                        color: '#315b95',
                        width: 1,
                    }
                },
                axisLabel: {
                    rotate: 35,
                    color: "#FFFFFF"
                },
                
                data: anquanchangtongData.shigushu.nameArray,
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.1
                    }
                },
                axisLabel: {
                    margin: 10,
                    textStyle: {
                        fontSize: 14
                    },
                    textStyle: {
                        color:'#fff',
                    },
                },
                splitLine: {
                    lineStyle: {
                        type: "dashed",
                        color: '#315b95',

                        
                    }
                }
            }],
            series: [
                {
                    name: '异常数',
                    type: 'line',
                    smooth: true, //false为折线，true为曲线
                    lineStyle: {
                        normal: {
                            width: 2
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgb(137,189,27)'
                        }
                    },
                    data: anquanchangtongData.shigushu.zongValueArray,
                },
            ]
        }
    },
    
    zhenggailvOption: function() {
        return {
        	grid: {
                show: true,
                top: 20,
                left: 30,
                bottom: 50,
                right: 40,
                borderWidth: 0,
            },
            tooltip : {
                show:true,
                triggerOn:'mousemove',
                trigger: 'axis',
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        type: 'dashed',
                        color: '#315b95',
                        width: 1,
                    }
                },
                axisLabel: {
                    rotate: 35,
                    color: "#FFFFFF"
                },
                
                data: anquanchangtongData.zhenggailv.nameArray,
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.1
                    }
                },
                axisLabel: {
                    margin: 10,
                    textStyle: {
                        fontSize: 14
                    },
                    textStyle: {
                        color:'#fff',
                    },
                },
                splitLine: {
                    lineStyle: {
                        type: "dashed",
                        color: '#315b95',

                        
                    }
                }
            }],
            series: [
                {
                    name: '不在线次数',
                    type: 'line',
                    smooth: true, //false为折线，true为曲线
                    lineStyle: {
                        normal: {
                            width: 2
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgb(45,140,236)'
                        }
                    },
                    data: anquanchangtongData.zhenggailv.valueArray,
                },
            ]
        }
    },
    
    yongduzhishuOption: function() {
        return {
            grid: {
                show: true,
                top: 20,
                left: 30,
                bottom: 50,
                right: 40,
                borderWidth: 0,
            },
            tooltip : {
                show:true,
                triggerOn:'mousemove',
                trigger: 'axis',
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        type: 'dashed',
                        color: '#315b95',
                        width: 1,
                    }
                },
                axisLabel: {
                    rotate: 35,
                    color: "#FFFFFF"
                },
                data: anquanchangtongData.yongduzhishu.nameArray,
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.1
                    }
                },
                axisLabel: {
                    margin: 10,
                    textStyle: {
                        fontSize: 14
                    },
                    textStyle: {
                        color:'#fff',
                    },
                },
                splitLine: {
                    lineStyle: {
                        type: "dashed",
                        color: '#315b95',
                    }
                }
            }],
            series: [
                {
                    name: '当前度',
                    type: 'line',
                    smooth: true, //false为折线，true为曲线
                    lineStyle: {
                        normal: {
                            width: 2
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgb(137,189,27)'
                        }
                    },
                    data: anquanchangtongData.yongduzhishu.dqdValueArray,
                },
                {
                    name: '平均度',
                    type: 'line',
                    smooth: true, //false为折线，true为曲线
                    lineStyle: {
                        normal: {
                            width: 2
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgb(45,140,236)'
                        }
                    },
                    data: anquanchangtongData.yongduzhishu.pjdValueArray,
                },
            ]
        }
    },
    
    wangrenshiguOption: function() {
        return {
            grid: {
                show: false,
                top: 30,
                left: 30,
                bottom: 35,
                right: 10,
                borderWidth: 0,
            },
            tooltip : {
                show:true,
                triggerOn:'mousemove',
                trigger: 'axis',
            },
            xAxis: {
                type: 'category',
                axisLabel: {
                    rotate: 40,
                    color: "#2482d5"
                },
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.1
                    }
                },
                data: anquanchangtongData.wangrenshigu.nameArray,
            },
            yAxis: {
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#2482d5'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.1
                    }
                },
            },
            series: [
                {
                    name: "当前值",
                    type: 'bar',
                    barWidth: '10',
                    barGap: "20%",
                    barCategoryGap: "20%",
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#8A95BB'},
                                {offset: 0.5, color: '#7A89BE'},
                                {offset: 1, color: '#6477BD'}
                            ]
                        )
                    },
                    data: anquanchangtongData.wangrenshigu.dqzValueArray
                },
                {
                    name: "平均值",
                    type: 'bar',
                    barWidth: '10',
                    barGap: "20%",
                    barCategoryGap: "20%",
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#9ABB9A'},
                                {offset: 0.5, color: '#699B68'},
                                {offset: 1, color: '#4F9D4E'}
                            ]
                        )
                    },
                    data: anquanchangtongData.wangrenshigu.pjzValueArray
                },
            ]
        }
    }
   
}