<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/inc/common_js_include.jsp" />
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=83P71ouBhB7tLwHoxISNoEIWr86TiXlw"></script>


<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
    <style type="text/css">
        #classify {
            color: #ffffff !important;
        }
        #classify  a {
            color: #ffffff !important;
        }
	    .bg-dark {
	        background: #333333 !important;
	    }
	    .aui-slide-node img {
	        width: 100%;
	        height: 100%;
	    }
    </style>
</head>
<body>
	<section class="aui-content ">
	    <div id="aui-slide3">
	        <div class="aui-slide-wrap" >
	            <div class="aui-slide-node bg-dark">
	                <img src="<%=request.getContextPath()%>/static/app/images/slide/1.jpg" />
	            </div>
	            <div class="aui-slide-node bg-dark">
	                <img src="<%=request.getContextPath()%>/static/app/images/slide/2.jpg" />
	            </div>
	            <div class="aui-slide-node bg-dark">
	                <img src="<%=request.getContextPath()%>/static/app/images/slide/3.jpg" />
	            </div>
	        </div>
	        <div class="aui-slide-page-wrap"><!--分页容器--></div>
	    </div>
    </section>
    
    <style>
    	#allmap{
    		width: 500px !important; 
    		height: 500px !important; 
    	}
    </style>
        <div class="aui-card-list aui-border-t aui-border-b">
            <div class="aui-info aui-padded-l-15 aui-padded-r-15">
    <div class="diqiu-container" id="allmap"></div>
    </div></div>



           	


    <jsp:include page="/WEB-INF/jsp/app/inc/footer.jsp">
    	<jsp:param name="bj" value="shouye"/>
    </jsp:include>
</body>
<script type="text/javascript">
    apiready = function () {

    }
    var slide3 = new auiSlide({
        container:document.getElementById("aui-slide3"),
         //"width":300,
        "height":140,
        "speed":500,
        "autoPlay": 3000, //自动播放
        "loop":true,
        "pageShow":true,
        "pageStyle":'dot',
        'dotPosition':'center'
    })
    $(function(){
    var map = new BMap.Map("allmap");
	 var districtLoading=0;
	 var blist = [];
    var point = new BMap.Point(114.406756,37.682826);
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
           //addCgq();
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
            			var img_url = "<%=request.getContextPath() %>/images/cgq.jpg";
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
    })
</script>
</html>