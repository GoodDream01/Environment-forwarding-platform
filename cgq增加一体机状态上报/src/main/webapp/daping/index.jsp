<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>风力监测管理系统</title>
    <jsp:include page="/inc/common_js_include.jsp" />
    <link rel="stylesheet" href="./css/anquanchangtong.css" />
    <link href="./css/hover-min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=83P71ouBhB7tLwHoxISNoEIWr86TiXlw"></script>
    <script src="js/customgreen.js"></script>
    <script src="js/vue.min.js"></script>
    <script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/AreaRestriction_min.js"></script>

    <style type="text/css">

.BMap_cpyCtrl

{

display:none;

}
.anchorBL{

display:none;

}

	</style>
</head>
<body>
    <div class="layout-top">
        <div class="top_title">
            <p class="tit">风力监测管理系统</p>
            <div id="clock">
                <p class="date">{{ date }}</p>
                <p class="time">{{ time }}</p>
            </div>
            <script>
                var clock = new Vue({
                    el: '#clock',
                    data: {
                        time: '',
                        date: ''
                    }
                });

                var week = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
                var timerID = setInterval(updateTime, 1000);
                updateTime();
                function updateTime() {
                    var cd = new Date();
                    clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
                    clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
                };

                function zeroPadding(num, digit) {
                    var zero = '';
                    for(var i = 0; i < digit; i++) {
                        zero += '0';
                    }
                    return (zero + num).slice(-digit);
                }
            </script>
        </div>
    </div>
    <div class="layout-main">
        <div class="layout-left">
            <!-- 事故数 -->
            <div class="left_top">
              <div class="common-title">风速异常数据统计</div>
              <div class="tab_box mt-tabpage-count">
                  <div class="ul_cont" id="report-shigushu"></div>
              </div>
            </div>
            <!-- 整改率-->
            <div class="left_middle">
              <div class="common-title">设备不在线数据统计</div>
              <div class="tab_box mt-tabpage-count">
              	<div class="ul_cont" id="report-zhenggailv"></div>
              </div>
            </div>
        </div>
        <div class="layout-center">
            <div class="diqiu-container" id="allmap"></div>
        </div>
        <div class="layout-right">
            <div class="heidianshu-container">
                <div class="common-title">实时风向统计</div>
                <div id="report-ydzs"></div>
            </div>
            <div class="right_middle">
               <div class="common-title">实时风速统计</div>
               <div id="report-wangrenshigu"></div>
            </div>
        </div>
    </div>
</body>

<script src="./js/echarts.min.js"></script>
<script src="./data/anquanchangtong.js"></script>
<script src="./option/anquanchangtong.js"></script>
<script>
    var dataIndex = 0;
    //当前数据项
    var anquanchangtongData;
	var data_jwd = {};
    //立刻执行
    pagePaint();

    //定时3秒改变数据
    setInterval(pagePaint, 3000);

    //页面渲染方法
    function pagePaint() {
        
    	//数据源切换
        dataIndex = (dataIndex + 1) % 2;
        switch (dataIndex) {
            case 0: anquanchangtongData = anquanchangtongData_0; break;
            case 1: anquanchangtongData = anquanchangtongData_0; break;
        }
        
        //事故数
        var shigushuChart = echarts.init(document.getElementById('report-shigushu'));
        shigushuChart.setOption(anquanchangtongOption.shigushuOption());

        //整改率
        var zhenggailvChart = echarts.init(document.getElementById('report-zhenggailv'));
        zhenggailvChart.setOption(anquanchangtongOption.zhenggailvOption());

        //拥堵指数
        var yongduzhishuChart = echarts.init(document.getElementById('report-ydzs'));
        yongduzhishuChart.setOption(anquanchangtongOption.yongduzhishuOption());

        //亡人事故
        var wangrenshiguChart = echarts.init(document.getElementById('report-wangrenshigu'));
        wangrenshiguChart.setOption(anquanchangtongOption.wangrenshiguOption());

    }
    
	 var map = new BMap.Map("allmap");
	 var districtLoading=0;
	 var blist = [];
     var point = new BMap.Point(116.403765, 39.914850);
     map.centerAndZoom(point, 12);
     map.enableScrollWheelZoom();
     map.addControl(new BMap.ScaleControl());
     //鱼骨图
     map.addControl(new BMap.NavigationControl());
     getBoundary(map);
 
  
     function getBoundary(map) {
        
        addDistrict("赞皇",map);
      }
      function addDistrict(districtName,map) {
        districtLoading++;
        var bdary = new BMap.Boundary();
       
        bdary.get(districtName,function (rs) {
        	console.log(rs)
          var count = rs.boundaries.length;
         /* rs.boundaries[1]=rs.boundaries[1]+";115.29038035874571, 36.48866913222481"*/
          if (count === 0) {
            alert('未能获取当前输入行政区域');
            return;
          }
          for (var i = 0; i < count; i++) {
            blist.push({ points: rs.boundaries[i], name: districtName });
          };
          //加载完成区域点后计数器-1
          districtLoading--;
          if (districtLoading == 0) {
            //全加载完成后画端点
            drawBoundary(map);
          }
        });
      }
      function drawBoundary(map) {
       
        //包含所有区域的点数组
        var pointArray = [];
        var pNW = {lat: 59.0, lng: 73.0}
        var pNE = {lat: 59.0, lng: 136.0}
        var pSE = {lat: 3.0, lng: 136.0}
        var pSW = {lat: 3.0, lng: 73.0}
        //向数组中添加一次闭合多边形，并将西北角再加一次作为之后画闭合区域的起点
        var pArray = [];
        pArray.push(pNW);
        pArray.push(pSW);
        pArray.push(pSE);
        pArray.push(pNE);
        pArray.push(pNW);
        //循环添加各闭合区域
        for (var i = 0; i < blist.length; i++) {
          //添加显示用标签层
          var label = new BMap.Label(blist[i].name, {offset: new BMap.Size(20,-10)});
          label.hide();
          //添加多边形层并显示
          var ply = new BMap.Polygon(blist[i].points, {
            strokeWeight: 5,
            strokeColor: "#FF0000",
            fillOpacity: 0.01,
            fillColor: " #FFFFFF"
          }); //建立多边形覆盖物
          ply.name = blist[i].name;
          ply.label = label;
          //将点增加到视野范围内
          var path = ply.getPath();
          pointArray = pointArray.concat(path);
          //将闭合区域加到遮蔽层上，每次添加完后要再加一次西北角作为下次添加的起点和最后一次的终点
          pArray = pArray.concat(path);
          pArray.push(pArray[0]);
        }
        //限定显示区域，需要引用api库
        var boundply = new BMap.Polygon(pointArray);
        BMapLib.AreaRestriction.setBounds(map, boundply.getBounds());
        map.setViewport(pointArray);    //调整视野
        //添加遮蔽层
        var plyall = new BMap.Polygon(pArray, {
          strokeOpacity: 0.0000001,
          strokeColor: "#020825",
          strokeWeight: 0.00001,
          fillColor: "#020825",
          fillOpacity: 1
        }); //建立多边形覆盖物
        map.addOverlay(plyall);
        //添加传感器
        addCgq();
      }
 	function addCgq(){
 		 $.ajax({
         	url: "<%=request.getContextPath()%>/cgq/sbgl/selectAll.do",
             cache: false,
             success: function (data) {
             	if(data.length>0){
             		data = JSON.parse(data);
             		for(var i = 0; i<data.length; i++){
             			var img_url = "images/cgq.jpg";
             			data_jwd = data[i];
             			var myIcon = new BMap.Icon(img_url, new BMap.Size(16, 16), {
            				anchor: new BMap.Size(16, 16),
            			});
             			
             			var marker = new BMap.Marker(new BMap.Point(data[i].jd, data[i].wd), { icon: myIcon }); //创建标注
             			
             		    marker.addEventListener("click", function(e){
             		    	var jd = e.target.point.lng;
                 			var wd = e.target.point.lat;
             		    	  mini.open({
             		    		url: HOST_PATH + "/cgq/sbgl/addUi1.do?jd="+jd+"&wd="+wd,
             			        title: "记录", width: 800, height: 400,
             			        loadOnRefresh: true,
             			        showMaxButton: true, 
             			        onload: function () {
             			           
             			        },
             			        ondestroy: function (action) {
             			            grid.reload();
             			        }
             			   });  
        				}); 
             			map.addOverlay(marker); //将标注添加到地图中  
             		}
             	}
             }
         });
 	}

 <%-- 	map.addEventListener("click", function(e) {
 		           var url = "<%=request.getContextPath()%>/cgq/sbgl/addUi1.do?jd="+e.point.lng+"&wd="+e.point.lat;
 				   window.open(url);
 				}); --%>
</script>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>
