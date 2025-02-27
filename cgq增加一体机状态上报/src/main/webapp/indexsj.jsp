<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/inc/common_js_include.jsp" />
<jsp:include page="/inc/common_head.jsp" />
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=83P71ouBhB7tLwHoxISNoEIWr86TiXlw"></script>
<title>数据系统</title>
<jsp:include page="/inc/common_js_app.jsp" />
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
	    #allmap{
    		width: 500px !important; 
    		height: 500px !important; 
    	}
    	.sp-color-list{
		    width:30px;
		    height:23px;
		    vertical-align:middle;
		    margin:0px;
		    display: inline;
		}
    	.sp-icon-list{
		    width:15px;
		    height:15px;
		    vertical-align:middle;
		    margin-right:2px;
		    display: inline;
		}
		a{
		   color:black;
		}
    </style>
</head>
<body>
    <div class="aui-card-list aui-border-t aui-border-b">
        <div class="aui-info aui-padded-l-15 aui-padded-r-15">
    		<div class="diqiu-container" id="allmap"></div>
    	</div>
    </div>
    <div id="wznr" style="font-size:16px;margin-left:10px;"></div>
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
    var map = null;
    $(function(){
	    map = new BMap.Map("allmap");
		var districtLoading=0;
		var blist = [];
	    var point = new BMap.Point(114.186756,37.582826);
	    map.centerAndZoom(point, 12);
	    map.enableScrollWheelZoom();
	    map.addControl(new BMap.ScaleControl());
	    //鱼骨图
	    map.addControl(new BMap.NavigationControl());
	    //getBoundary(map);
	    //添加传感器
	    addCgq();
    })
    
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
    }
     
	function addCgq(){
		 $.ajax({
        	url: "<%=request.getContextPath()%>/cgq/sbgl/selectSb.do?bj=1",
            cache: true,
            success: function (data) {
            	if(data.length>0){
            		data = JSON.parse(data);
            		var num=0,num1=0,num2=0,num3=0,num4=0,num5=0,num6=0,num7=0;
            		var str="",str1="",str2="",str3="",str4="",str5="",str6="",str7="";
            		for(var i = 0; i<data.length; i++){
            			if(data[i].bj=="0"){
            				if(Number(data[i].fldj)<=2){
                    			var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
                        				{ fillColor: "green", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
                      			map.addOverlay(circle);
                      			num1 = num1 + 1;
                      			str1 = str1 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
                    		}else if(Number(data[i].fldj)>=3 && Number(data[i].fldj)<=4){
	                   			var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       		        { fillColor: "#7183F8", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     		map.addOverlay(circle);
                    			num2 = num2 + 1;
                    		    str2 = str2 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}else if(Number(data[i].fldj)==5){
	                   			var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       				{ fillColor: "#287AEE", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     	    map.addOverlay(circle);
	                     	    num3 = num3 + 1;
	                         	str3 = str3 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}else if(Number(data[i].fldj)==6){
	                   			var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       				{ fillColor: "#3A00D7", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     	    map.addOverlay(circle);
	                     	    num7 = num7 + 1;
	                         	str7 = str7 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}else if(Number(data[i].fldj)>=7 && Number(data[i].fldj)<=8){
	                   			var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       				{ fillColor: "#D7EF2D", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     		map.addOverlay(circle);
	                     		num4 = num4 + 1;
	                     		str4 = str4 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}else if(Number(data[i].fldj)>=9 && Number(data[i].fldj)<=10){
	                   			 var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       				{ fillColor: "#FF9F2C", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     		 map.addOverlay(circle);
	                     		 num5 = num5 + 1;
	                     		 str5 = str5 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}else if(Number(data[i].fldj)>=11 && Number(data[i].fldj)<=12){
	                   			 var circle = new BMap.Circle(new BMap.Point(data[i].jd, data[i].wd), data[i].xsfw,
	                       				 { fillColor: "#DF2F30", strokeWeight: 1, fillOpacity: 0.2, strokeOpacity: 0.2 });
	                     		 map.addOverlay(circle);
	                     		 num6 = num6 + 1;
	                     		 str6 = str6 + "</br><a onclick='view("+data[i].jd+","+data[i].wd+")'><image src='<%=request.getContextPath() %>/images/cgq.jpg' class='sp-icon-list'>"+data[i].sbbh+"("+data[i].mc+")</a>";
	                   		}
	               		}else{
	               			num = num + 1;
	                 		str = str + data[i].sbbh + "&nbsp;";
	               		}
                		
                		//var html = "<p>不在线的有"+num+"个："+str+"</p>";
                		var html = "<div style='font-size:18px;'>当前风力数据监测统计</div>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl1.jpg' class='sp-color-list'>(1-2级)风力监测区域："+str1+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl2.jpg' class='sp-color-list'>(3-4级)风力监测区域："+str2+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl7.jpg' class='sp-color-list'>5级风力监测区域："+str3+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl3.jpg' class='sp-color-list'>6级风力监测区域："+str7+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl4.jpg' class='sp-color-list'>(7-8级)风力监测区域："+str4+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl5.jpg' class='sp-color-list'>(9-10级)风力监测区域："+str5+"</br>";
                		html = html + "<image src='<%=request.getContextPath() %>/images/fl6.jpg' class='sp-color-list'>(11-12级)风力监测区域："+str6+"</br>";
                		$("#wznr").html(html);
            	
	            		var img_url = "<%=request.getContextPath() %>/images/wzx.png";
	        			if(data[i].bj=="0"){
	        				img_url = "<%=request.getContextPath() %>/images/zx.png";
	        			}
	        			var label = new BMap.Label(data[i].sbbh, {position: new BMap.Point(data[i].jd, data[i].wd)})
            			map.addOverlay(label); 
            			var myIcon = new BMap.Icon(img_url, new BMap.Size(12, 12), {anchor: new BMap.Size(12, 12),});
            			var marker = new BMap.Marker(new BMap.Point(data[i].jd, data[i].wd), { icon: myIcon });
            			marker.addEventListener("click", function(e){
            		    	var jd = e.target.point.lng;
                			var wd = e.target.point.lat;
            		    	mini.open({
            		    		url: HOST_PATH + "/cgq/sbgl/addUi1.do?jd="+jd+"&wd="+wd,
            			        title: "记录", width: 400, height: 700,
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
	
	function view(jd,wd){
		mini.open({
    		url: HOST_PATH + "/cgq/sbgl/addUi1.do?jd="+jd+"&wd="+wd,
	        title: "记录", width: 400, height: 700,
	        loadOnRefresh: true,
	        showMaxButton: true, 
	        onload: function () {
	           
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
	   });  
	}
</script>
</html>