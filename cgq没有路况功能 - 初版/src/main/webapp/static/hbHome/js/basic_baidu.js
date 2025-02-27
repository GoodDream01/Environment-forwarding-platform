/**
 * 补充：遇到问题的一些随笔记录
 * 1.关于坐标的转换。。
 *   因百度版本的坐标加密需要仔细研究，数据库中的点位在落实时，需要参考明确版本api的加密方式，这个问题较为特别
 *   百度开放的map.js源码中有干扰代码，日后若要参考算法时务必小心留意，尤其是在询问提供者是要着重注意这个问题
 * 2.关于此2.1离线版本：api中仍存在许多应用上的bug,目前添加的方法中，
 *   一：地图加载完后的监听函数，是与图层缩放的监听时间有交叉的，即地图瓦片加载完后触发，缩放图层也会触发
 *   二：监听事件 必须要加try catch，有些莫名其妙的异常，但不影响运行
 *   三：图形编辑 "lineupdate"：当属性改变时触发，已知bug：添加图形时就会触发
 *   四：测量面积：追踪绘制工具 有在线的js可查阅
 */

/**********************百度地图常量定义开始*************************/
//-----------定义地图有关静态常量-------------
var midNightMapType;
var MAPCONSTANT = {
    //地图事件（主要用于图层，图形、图标对象触发事件）
    MAPEVENT: {
        CLICK: "click",//左键单击
        DBCLICK: "dblclick",//左键双击
        MOUSEOVER: "mouseover",//鼠标移入
        MOUSEOUT: "mouseout"//鼠标移出
    },
    //画图类型（主要用于添加图形对象、图形绘画或编辑后触发图形选择等应用）
    DRAWSYMBOL: {
        POINT: "marker",//点
        MULTIPOINT: "multipoint",//多点
        POLYLINE: "polyline",//线
        CIRCLE: "circle",//圆
        RECTANGLE: "rectangle",//矩形
        POLYGON: "polygon"//多边形
    },
    MAPTYPE: {
        normal: BMAP_NORMAL_MAP,
        midNight: midNightMapType
    },
    QUERYURL: {
        TRAJECTORYSEARCH: "cloudsearch?index=directionaaabbb&action=route&waypoints=&origin=startPoint&destination=endPoint&output=json&mode=CAR"
    },
    //常量 线条样式
    LINESTYLE: {
        STYLE_DASH: "dashed",
        STYLE_SOLID: "solid"
    }
}
var map;//全局用地图
var top_left_scale;//左上角比例尺
var top_left_navigation;//左上角平移按钮  默认添加
var myDrag;//鼠标拉框放大
var overView;//缩略图
var myDis;//鼠标测距
var drawingManager;//鼠标绘制工具
var mapvLayer;//遮罩层
var skinType = 1;
var lushuObjGloble;//轨迹移动全局变量（测试使用）
var mapvLayerOptions = {//遮罩层配置，默认为暗色主题
    fillStyle: 'rgba(25,26,27, 0)',
    strokeStyle: 'rgba(117, 145, 164, 1)',
    // strokeStyle: 'rgba(250, 250, 55, 1)',
    shadowColor: 'rgba(25,26,27, 0)',
    shadowBlur: 20,
    lineWidth: 2,
    globalAlpha: 0.9,
    methods: { // 一些事件回调函数
        mousemove: function (item) { // 鼠标移动事件，对应鼠标经过的元素对象值
            mapvLayer.canvasLayer.adjustSize();
            mapvLayer.canvasLayer._draw();
        }
    },
    draw: 'clip'
}

/**********************百度地图常量定义结束*************************/

/**
 * 实例化地图
 * @param {Number} jd
 * @param {Number} wd
 * @param {Number} zoom
 */
function initMap(jd, wd, zoom) {
	/**
    map = new BMap.Map("map");
    map.centerAndZoom(new BMap.Point(jd, wd), zoom);
    map.enableScrollWheelZoom();
    map.disableDoubleClickZoom();//因和图标监听事件冲突 ，默认禁用图层双击放大事件
//    addScale();//增加比例尺
//    addNavigation();//导航控件
    mapLoaded(addMapGraphicsListener);

    //-------------------------------------增加午夜蓝色地图动图类型开始-------------------------------------
    // 创建图层
    var layer = new BMap.TileLayer();
    layer["getTilesUrl"] = function (tileCoord, zoom, style) {
        var x = (tileCoord.x + "").replace(/-/gi, "M");
        var y = (tileCoord.y + "").replace(/-/gi, "M");
        var z = zoom;
        //  return 'http://localhost:8080/lsdt_hb/' + z + '/' + ~~(x / 10) + '/' + ~~(y / 10) + '/' + x + '_' + y + '.png';
        return arcgisDarkMapUrl + z + '/' + ~~(x / 10) + '/' + ~~(y / 10) + '/' + x + '_' + y + '.png';
        // return 'http://10.31.141.220:8319/custom-maptile/' + z + '/' + ~~(x / 10) + '/' + ~~(y / 10) + '/' + x + '_' + y + '.png';
    }

    // 创建地图类型
    midNightMapType = new BMap.MapType('地图', layer, {
        "tips": '显示午夜蓝地图',
        "maxZoom": 15
    });
    MAPCONSTANT.MAPTYPE.midNight = midNightMapType;
    //-------------------------------------增加午夜蓝色地图动图类型结束-------------------------------------
    // tt();
    
    */
}

/**禁用/启用双击放大地图功能
 * @param boo true:禁用 false:启用
 */
function disableDoubleClickZoom(boo) {
    if (boo) {
        map.disableDoubleClickZoom();
    } else {
        map.enableDoubleClickZoom();
    }
}

/**
 * 设置中心点
 * @param {Array} center 经纬度数组
 * @param {String} center 城市名  现发布离线版本设置字符串不会显示效果，在线示例可以，注意验证
 */
function centerAt(center) {
    if (Array.isArray(center)) {
        map.setCenter(new BMap.Point(center[0], center[1]));
    } else if (typeof(center) == "string") {
        map.setCenter(center);
    } else {
        return;
    }
}

/**
 * 设置缩放等级参数(map.js中默认做了与最大最小等级的判断)
 * @param {Number} zoom
 */
function setZoom(zoom) {
    map.setZoom(zoom);
}

/**
 * 设置中心点和缩放等级
 * @param {Number} jd
 * @param {Number} wd
 * @param {Number} zoom
 */
function centerAndZoom(jd, wd, zoom) {
    map.centerAndZoom(new BMap.Point(jd, wd), zoom);
}


/**
 * 重置数据库默认的经纬度和zoom等级
 */
function resetMapPositionAndZoom() {
    centerAndZoom(originJd, originWd, originZoom);
}

/**
 * 更改地图中心点(适合同等级下重新以某个自定义图标为中心)
 * @param {Number} jd
 * @param {Number} wd
 */
function panTo(jd, wd) {
    var panD = new BMap.Point(jd, wd);
    map.panTo(panD);
}

/*****************************地图事件监听方法开始*******************************/
/**
 * 地图图块完全加载完毕后的监听事件
 * @param {Function} backName
 * @description 地图图块加载完毕时机(监听事件触发时机)：初始化地图时 地图层级发生变化时
 */
function mapLoaded(backName) {
    try {
        if (typeof(backName) == "function") {
            map.addEventListener("tilesloaded", backName);
        } else {
            console.log("请输入一个返回函数");
        }
    } catch (e) {

    }
}

/**
 * mapLoaded模拟函数
 */
function showLoadInfo() {
    alert("地图加载完成");
}

/**
 *
 * @param {Object} backName
 */
function zoomLoaded(backName) {

}

/*****************************地图事件监听方法结束*****************************/

/**
 * 添加左上角比例尺
 * @description:原basic.js功能中独自使用
 */
function addScale() {
    top_left_scale = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
    map.addControl(top_left_scale);
}

/**
 * 删除比例尺
 */
function removeScale() {
    if (isObjectNotNull(top_left_scale)) {
        map.removeControl(top_left_scale);
    }
}

/**
 * 添加左上角导航控件
 * @description 原basic.js实在地图初始化时添加的，无具体函数
 */
function addNavigation() {
    top_left_navigation = new BMap.NavigationControl();
    map.addControl(top_left_navigation);
}

/**
 * 移除导航控件
 */
function removeNavigation() {
    if (isObjectNotNull(top_left_navigation)) {
        map.removeControl(top_left_navigation);
    }
}

/**
 * 鼠标拉框放大
 */
function izoomIn() {
    myDrag = new BMapLib.RectangleZoom(map, {
        followText: "拖拽鼠标进行操作"
    });
    myDrag.open();  //开启拉框放大
}

//工具方法之间的切换
/**
 * 鼠标拉框缩小
 * 此方法待实现 需要配合绘制矩形实现
 */
function izoomOut() {

}

/**
 * 关闭鼠标拉框放大
 */
function closeZoom() {
    if (isObjectNotNull(myDrag)) {
        myDrag.close();
    }
}

/**
 * 全图功能
 * @description 原basic.js的理解,全图功能是指重置地图为初始化时地图中心点和缩放等级
 */
function ifullExtent() {
    map.reset();
}

/**
 * 初始化鹰眼
 */
function addOverview() {
    overView = new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}); //缩略图位置
    map.addControl(overView);
}

/**
 * 鹰眼显示与隐藏
 * @description 项目中有部分引用是在地图初始化时
 */
function showOverView() {
    if (isObjectNotNull(overView)) {
        overView.show();
    }
}

function hideOverView() {
    if (isObjectNotNull(overView)) {
        overView.hide();
    }
}

//长度 面积测量方法 目前仅有测距离 还要求抽象成要给测量工具类
/**
 * 鼠标测距(多点长度测量)
 */
function distanceTool() {
    closeZoom();
    myDis = new BMapLib.DistanceTool(map);
    myDis.open();  //开启鼠标测距
}

function closeDisTool() {
    if (isObjectNotNull(myDis)) {
        myDis.close();
    }
}

/**
 *鼠标测面积(多边形面积测量)
 * 此方法待实现 需要配合绘制矩形实现
 */
function areaMeasureTool() {

}

/**
 * @description 原来的平移方法，实现就是恢复原来图标的样式，将之前的控件全都删掉（原来默认就是鼠标拖拽地图）
 */
function ipan() {
    closeZoom();
    closeDisTool();
}

/**
 *增加图层
 * @description 图层定义 验证方式未知？？？
 */
function addGraphicsLayer() {
    var tileLayer = new BMap.TileLayer({isTransparentPng: true});
    tileLayer.getTilesUrl = function (tileCoord, zoom) {
        var x = tileCoord.x;
        var y = tileCoord.y;
        return 'http://lbsyun.baidu.com/jsdemo/demo/tiles/' + zoom + '/tile' + x + '_' + y + '.png';  //根据当前坐标，选取合适的瓦片图
    }
    map.addTileLayer(tileLayer);
}

/**
 * 移除图层
 * @param {TileLayer} layer
 */
function removeMapLayer(layer) {
    if (isObjectNotNull(layer)) {
        map.removeTileLayer(layer);
    }
}

/**************************************************地图覆盖物对象相关操作开始***********************************/

/*0.图标对象统一添加，删除，显示与隐藏，监听事件方法*/
/**
 * 添加单个图标对象到地图
 * @param {Graphic} graphic
 * @description 项目中有直接引用map添加的原生方法
 */
function addGraphicOnMap(graphic) {
    map.addOverlay(graphic);
}

/**
 * 从地图删除单个图标对象
 * @param {Graphic} graphic
 */
function removeSingalMarkerOnMap(graphic) {
    if (isObjectNotNull(graphic)) {
        map.removeOverlay(graphic);
    }
}

/**
 * 从地图上删除一组图标对象
 * @param {Array[Graphic]} graphic
 */
function removeMarkerOnMap(graphic) {
    if (isObjectNotNull(graphic)) {
        if (graphic.length) {//判断是数组
            for (var i = 0; i < graphic.length; i++) {
                if (isObjectNotNull(graphic[i])) {
                    map.removeOverlay(graphic[i]);
                }
            }
        } else {
            removeSingalMarkerOnMap(graphic);
        }
    }
}

/**
 * 清空所有图标对象
 */
function iclear() {
    map.clearOverlays();
}

/**
 * 通用隐藏图标对象
 * @param {Graphic} graphic
 * @description 暂不含隐藏图标title,批量隐藏自己实现
 */
function hideGraphicTitles(graphic) {
    if (isObjectNotNull(graphic)) {
        graphic.hide();
    }
}

/**
 * 通用显示图标对象
 * @param {Graphic} graphic
 * @暂不含显示图标title,批量显示自己实现
 */
function showGraphicTitles(graphic) {
    graphic.show();
}

/**
 * 给图标对象增加监听事件(适合自定义图标以及基础图标对象)
 * @param {Graphic} graphic
 * @param {String} event  参考常量
 * @param {Function} funName
 * @description
 */
function addGraphicEventListener(graphic, event, funName) {
    try {
        if (typeof(funName) == "function") {
            //自定义构造函数
            function backFunction(e) {
                this.attr = e.target.attributes;
                this.graphic = e.target;
                this.fun = funName(this.attr, this.graphic);
                return this.fun;
            }

            graphic.addEventListener(event, backFunction);
        } else {
            console.log("你需要添加一个监听事件");
        }
    } catch (e) {

    }
}

/*1.图标对象基础操作：添加、删除、设置可编辑*/
/**
 * 初始化某一类图标对象并添加到地图上
 * @param {String} graType 覆盖物对象类型
 * @param {Array} graVariable  初始化对象时所需参数(初定为数组)
 * @param {String?} color 边框颜色，其余考虑设置参数
 * @description:
 *     1.原basic.js将单个图标添加方法拆开了
 *     2.原basic.js参数
 *        点：属于自定义图标
 *        线：以逗号分割的字符串 “120.23,23.34,120.45,24.56……”
 *        多边形：polyGonMarkInfo：color、borderColor、coordinates（坐标数组）可类比json
 *        圆：circleMarkerInfo：jd、wd、radius  可类比json
 */
function addGraphic(graType, graVariable, color) {
    switch (graType) {
        case MAPCONSTANT.DRAWSYMBOL.POINT ://[jd,wd]
            var onePoint = new BMap.Point(graVariable[0], graVariable[1]);
            var oneMarker = new BMap.Marker(onePoint);//[[jd,wd],[jd,wd],[jd,wd],[jd,wd],[jd,wd],……]
            //    BMap.Marker.prototype.attributes=null;//自定义marker属性集合
            map.addOverlay(oneMarker);
            return oneMarker;
            break;
        case MAPCONSTANT.DRAWSYMBOL.MULTIPOINT ://[[jd,wd],[jd,wd],[jd,wd],[jd,wd]……]
            var pointMarkers = graVariable;
            for (var i = 0; i < pointMarkers.length; i++) {
                var onePoint = new BMap.Point(pointMarkers[i][0], pointMarkers[i][1]);
                var oneMarker = new BMap.Marker(onePoint);
                map.addOverlay(oneMarker);
            }
            return pointMarkers;
            break;
        case MAPCONSTANT.DRAWSYMBOL.POLYLINE ://[[jd,wd],[jd,wd],[jd,wd],[jd,wd]……]
            var linePoints = [];
            for (var i = 0; i < graVariable.length; i++) {
                var onePoint = new BMap.Point(graVariable[i][0], graVariable[i][1]);
                linePoints.push(onePoint);
            }
            var onePolyline = new BMap.Polyline(linePoints, {strokeColor: color, strokeWeight: 2, strokeOpacity: 0.8});
            //    BMap.Polyline.prototype.attributes=null;//自定义Polyline属性集合
            map.addOverlay(onePolyline);
            return onePolyline;
            break;
        case MAPCONSTANT.DRAWSYMBOL.CIRCLE ://[jd,wd,radius]
            var circlePoint = new BMap.Point(graVariable[0], graVariable[1]);
            var radius = graVariable[2];
            var oneCircle = new BMap.Circle(circlePoint, radius, {
                strokeColor: color,
                strokeWeight: 2,
                strokeOpacity: 0.8
            });
            //    BMap.Circle.prototype.attributes=null;//自定义Circle属性集合
            map.addOverlay(oneCircle);
            return oneCircle;
            break;
        case MAPCONSTANT.DRAWSYMBOL.RECTANGLE ://[[jd,wd],[jd,wd]]
            var pStart = new BMap.Point(graVariable[0][0], graVariable[0][1]);
            var pEnd = new BMap.Point(graVariable[1][0], graVariable[1][1]);
            var oneRectangle = new BMap.Polygon([
                new BMap.Point(pStart.lng, pStart.lat),
                new BMap.Point(pEnd.lng, pStart.lat),
                new BMap.Point(pEnd.lng, pEnd.lat),
                new BMap.Point(pStart.lng, pEnd.lat)
            ], {strokeColor: color, strokeWeight: 2, strokeOpacity: 0.8});
            //oneRectangle._style="rectangle";//添加自定义属性用以和polygon做区分，方便多种类型时操作
            //	BMap.Polygon.prototype.attributes=null;//自定义Polygon属性集合
            map.addOverlay(oneRectangle);
            return oneRectangle;
            break;
        case MAPCONSTANT.DRAWSYMBOL.POLYGON :
            var polygonPoints = [];
            for (var i = 0; i < graVariable.length; i++) {
                var onePoint = new BMap.Point(graVariable[i][0], graVariable[i][1]);
                polygonPoints.push(onePoint);
            }
            var onePolygon = new BMap.Polygon(polygonPoints, {strokeColor: color, strokeWeight: 2, strokeOpacity: 0.8});
            //    BMap.Polygon.prototype.attributes=null;//自定义Polygon属性集合
            map.addOverlay(onePolygon);
            return onePolygon;
            break;
        default:
            alert("请参考DRAWSYMBOL常量传入正确的图标对象类型参数=_=");
            break;
    }
}

/**
 * 基础点图标对象和自定义图标对象是否可移动  要和图标对象编辑做整合 参考原来basic.js做
 * @param {Marker} pointMarker
 * @param {Boolean} flag
 */
function isMarkerDrag(marker, flag) {
    if (flag) {
        marker.enableDragging();
    } else {
        marker.disableDragging();
    }
}

/**
 * 图标对象可编辑
 * @param {Graphic} graphic
 * @param {Boolean} flag
 * @description 矩形编辑 可否与多边形不同？
 */
function isGraphicEdit(graphic, flag) {
    if (flag) {
        graphic.enableEditing();
    } else {
        graphic.disableEditing();
    }
}

/*2.鼠标绘制、编辑覆盖物动作及动作后的触发事件*/
/**
 * 在地图上画图形及是否触发监听事件
 * @param {String[]} eve  例:[MAPCONSTANT.DRAWSYMBOL.POINT, MAPCONSTANT.DRAWSYMBOL.POLYLINE……]
 * @param {Function} backFun
 */
var drawMarkOnMapBackFunction;//绘制添加图标后的触发事件
function drawMarkOnMap(eve, backFun) {
    var styleOptions = {
        strokeColor: "red",
        //fillColor:"white",
        strokeWeight: 2,
        strokeOpacity: 1,
        fillOpacity: 0.8,
        strokeStyle: 'solid' //边线的样式，solid或dashed
    }
    //实例化鼠标绘制工具
    drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false,
        enableDrawingTool: true,
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            offset: new BMap.Size(5, 5),
            drawingModes: eve
        },
        enableCalculate: false,//绘制是否进行测距(画线时候)、测面(画圆、多边形、矩形)
        circleOptions: styleOptions,
        polylineOptions: styleOptions,
        polygonOptions: styleOptions,
        rectangleOptions: styleOptions
    });

    //监听事件
    try {
        if (typeof(backFun) == "function") {
            drawingManager.addEventListener('overlaycomplete', backFun);
        } else {
            console.log("你需要添加一个监听事件");
        }
    } catch (e) {

    }
}

/**
 * 模拟绘制图标后的回调函数
 * @description "overlaycomplete"  是绘制的总派发接口  绘制工具会返回绘制的图标对象 e.overlay
 */
function overlaycomplete(e) {
    var polygonEdit = e.overlay;
    if (polygonEdit.toString() == "[object Marker]") {
        alert("你绘制了一个点");
    } else if (polygonEdit.toString() == "[object Polyline]") {
        alert("你绘制了一条线");
    } else if (polygonEdit.toString() == "[object Circle]") {
        alert("你绘制了一个圆");
    } else if (polygonEdit.toString() == "[object Polygon]") {
        alert("你绘制了一个多边形");
    }
}

/**
 * 图标图形【编辑】后监听事件
 * @param {Graphic} evt
 * @param {Function} backFun
 * @description 该事件有问题：在对象未添加到地图上时，此事件便已经触发
 */
function finishEdit(evt, backFun) {
    try {
        if (typeof(backFun) == "function") {
            evt.addEventListener("lineupdate", backFun);
        } else {
            console.log("你需要添加一个监听事件");
        }
    } catch (e) {

    }


}

/*3.自定义图标相关:添加自定义图标、title，移除图标，添加图标监听事件*/
/**
 * 添加(复杂)自定义图标对象 暂时不用
 * @param {PictureMarkerInfo} pictureMarkerInfo
 * @param {Title?} title  暂定无
 * @param {String?} color 暂定无
 * @return 返回添加的自定义图标对象
 */
function addPictureMarkerToMap_bak(pictureMarkerInfo, title, color) {
    var CustomMarkerPoint = new BMap.Point(pictureMarkerInfo.jd, pictureMarkerInfo.wd);

    //自定义百度图标对象
    function CustomMarker(point, iconPath, height, width) {
        this._point = point;
        this._iconPath = iconPath;
        this._height = height;
        this._width = width;
    }

    //继承Overlay覆盖物抽象基类
    CustomMarker.prototype = new BMap.Overlay();
    //初始化自定义方法
    CustomMarker.prototype.initialize = function (map) {
        this._map = map;
        //创建div元素 作为图标容器
        var div = this._div = document.createElement("div");
        div.style.position = "absolute";
        div.style.width = this._width + "px";
        div.style.height = this._height + "px";
        //div中放入图片
        var image = this._image = document.createElement("img");
        image.style.width = "100%";
        image.style.height = "100%";
        image.src = this._iconPath;
        div.appendChild(image);
        //将div添加到覆盖物容器中
        map.getPanes().labelPane.appendChild(div);
        return div;
    }
    //绘制覆盖物 实现覆盖方法
    // 实现绘制方法
    CustomMarker.prototype.draw = function () {
        // 根据地理坐标转换为像素坐标，并设置给容器
        var pixel = this._map.pointToOverlayPixel(this._point);
        this._div.style.left = pixel.x - this._height / 2 + "px";
        this._div.style.top = pixel.y - this._width / 2 + "px";
    }
    //添加监听事件
    CustomMarker.prototype.addEventListener = function (event, funName) {
        this._div['on' + event] = funName;
    }

    var oneCustomMarker = new CustomMarker(CustomMarkerPoint, pictureMarkerInfo.imageUrl, pictureMarkerInfo.imageHeight, pictureMarkerInfo.imageWidth);
    map.addOverlay(oneCustomMarker);

    return oneCustomMarker;
}

/**
 * 添加自定义图标对象
 * @param {PictureMarkerInfo} pictureMarkerInfo  原定义的PictureMarkerInfo对象
 * @param {Title} title标注物上方文字说明
 * @param {String} color标注物上方文字颜色
 */
function addPictureMarkerToMap(pictureMarkerInfo, titleStr, titleColor) {
    if (isObjectNull(pictureMarkerInfo) || isObjectNull(pictureMarkerInfo.jd) || isObjectNull(pictureMarkerInfo.wd)) {
        return;
    }
    isStrNull(pictureMarkerInfo.imageUrl) ? "images/arcgis/noteshow.jpg" : pictureMarkerInfo.imageUrl;
    isStrNull(pictureMarkerInfo.imageWidth) ? 25 : pictureMarkerInfo.imageWidth;
    isStrNull(pictureMarkerInfo.imageHeight) ? 25 : pictureMarkerInfo.imageHeight;
    isStrNull(titleStr) ? "" : titleStr;
    isStrNull(titleColor) ? "blue" : titleColor;//默认标注物上方文字颜色为蓝色
    var pt = new BMap.Point(pictureMarkerInfo.jd, pictureMarkerInfo.wd);
    var myIcon = new BMap.Icon(pictureMarkerInfo.imageUrl, new BMap.Size(pictureMarkerInfo.imageHeight, pictureMarkerInfo.imageWidth));
    myIcon.setImageSize(new BMap.Size(pictureMarkerInfo.imageWidth, pictureMarkerInfo.imageHeight));
    var markerOptions;//标注物配置
    isStrNull(titleStr) ? (markerOptions = {icon: myIcon}) : (markerOptions = {icon: myIcon, title: titleStr});//是否加鼠标移上去效果
    var addMarker = new BMap.Marker(pt, markerOptions);  // 创建标注， 带有title(鼠标移上去的效果)
    if (isStrNotNull(titleStr)) {//有title文字时，增加label
        var label = new BMap.Label(titleStr);
        var offsetX = 0 - (getStrLen(titleStr) * 12 - pictureMarkerInfo.imageWidth) / 2;//根据titleStr的长度，设置label的offset偏移量
        var offsetY = -20;
        label.setOffset(new BMap.Size(offsetX, -20));
        var labelStyle = {color: titleColor, border: "1px solid #a09f9e"};
        var newLabelStyle = getLabelStyle(labelStyle, "point");//根据skinType获得对应的label的配色方案（该方法内部使用）
        label.setStyle(newLabelStyle);//默认设置标注物上方的文字的背景边框是灰色
        addMarker.setLabel(label);
        var attr = {"label": label, "type": "point"};
        setAttrForGraphic(addMarker, attr);
    }
    map.addOverlay(addMarker);
    return addMarker;
}

/*4.对象自定义属性*/
/**
 * 添加自定义属性
 * @param {Graphic} graphic
 * @param {JSON} attr
 */
function setAttrForGraphic(graphic, attr) {
    if (isObjectNull(graphic)) {
        return;
    }
    var attrold = graphic.attributes;
    if (attrold != null && attrold != undefined) {
        for (var p in attr) {
            attrold[p] = attr[p];
        }
        attr = attrold;
    }
    graphic.attributes = attr;
}

/**
 * 获取自定义属性
 * @param {Graphic} graphic
 * @return {JSON}
 */
function getAttrForGraphic(graphic) {
    if (isObjectNull(graphic)) {
        return;
    }
    return graphic.attributes;
}


/*5.自定义图标相关:添加自定义图标、title，移除图标，添加图标监听事件*/
/**
 * 添加自定义图标对象
 * @param {textMarkerInfo} textMarkerInfo  原定义的textMarkerInfo对象
 * @param {String} color title文字颜色
 */
function showTextMarkerOnMap(textMarkerInfo, color) {
    if (isObjectNull(textMarkerInfo) || isObjectNull(textMarkerInfo.jd) || isObjectNull(textMarkerInfo.wd) || isObjectNull(textMarkerInfo.displayText)) {
        return;
    }
    var titleStr = textMarkerInfo.displayText;
    var titleColor = color;
    isStrNull(textMarkerInfo.imageUrl) ? "images/arcgis/noteshow.jpg" : textMarkerInfo.imageUrl;
    isStrNull(textMarkerInfo.imageWidth) ? 25 : textMarkerInfo.imageWidth;
    isStrNull(textMarkerInfo.imageHeight) ? 25 : textMarkerInfo.imageHeight;
    isStrNull(titleStr) ? "" : titleStr;
    isStrNull(titleColor) ? "blue" : titleColor;//默认标注物上方文字颜色为蓝色
    var pt = new BMap.Point(textMarkerInfo.jd, textMarkerInfo.wd);
    var myIcon = new BMap.Icon(textMarkerInfo.imageUrl, new BMap.Size(textMarkerInfo.imageWidth, textMarkerInfo.imageHeight));
    myIcon.setImageSize(new BMap.Size(textMarkerInfo.imageWidth, textMarkerInfo.imageHeight));
    var markerOptions;//标注物配置
    isStrNull(titleStr) ? (markerOptions = {icon: myIcon}) : (markerOptions = {icon: myIcon, title: titleStr});//是否加鼠标移上去效果
    var addMarker = new BMap.Marker(pt, markerOptions);  // 创建标注， 带有title(鼠标移上去的效果)
    if (isStrNotNull(titleStr)) {//有title文字时，增加label
        var label = new BMap.Label(titleStr);
        var offsetX = 0 - (getStrLen(titleStr) * 12 - textMarkerInfo.imageWidth) / 2;//根据titleStr的长度，设置label的offset偏移量
        var offsetY = 5;
        label.setOffset(new BMap.Size(offsetX, 5));
        var labelStyle = {color: titleColor, border: "0px solid #a09f9e", backgroundColor: "rgba(255,255,255,0.1)"};
        var newLabelStyle = getLabelStyle(labelStyle, "textPoint");//根据skinType获得对应的label的配色方案（该方法内部使用）
        label.setStyle(newLabelStyle);//默认设置标注物上方的文字的背景边框是灰色
        addMarker.setLabel(label);
        var attr = {"label": label, "type": "textPoint"};
        setAttrForGraphic(addMarker, attr);

    }
    map.addOverlay(addMarker);
    return addMarker;
}

/**改变图标的img路径，大小
 * @param marker  marker对象
 * @param img  img新路径
 * @param width  img宽度
 * @param height img高度
 * @return
 */
function changeGraphicImageUrl(marker, imgUrl, width, height) {
    if (isObjectNotNull(marker)) {
        try {//改变图标大小
            var oldIcon = marker.getIcon();
            var newOffsetY = (isStrNull(height)) ? 0 : (height - oldIcon.imageSize.height) / 2;
            var iconUrl = (isStrNull(imgUrl)) ? oldIcon.imageUrl : imgUrl;
            var iconWidth = (isStrNull(width)) ? (oldIcon.imageSize.width) : width;
            var iconHeight = (isStrNull(height)) ? (oldIcon.imageSize.height) : height;
            oldIcon.setImageUrl(iconUrl);
            oldIcon.setSize(new BMap.Size(iconWidth, iconHeight));
            oldIcon.setImageSize(new BMap.Size(iconWidth, iconHeight));
            marker.setIcon(oldIcon);

            //重算label文字title偏移量
            var label = marker.getLabel();
            if (isObjectNotNull(label)) {
                var titleStr = label.content;
                var offsetX = 0 - (getStrLen(titleStr) * 12 - iconWidth) / 2;//根据titleStr的长度，设置label的offset偏移量
                //	var offsetY = 5-newOffsetY;
                var offsetY = -20;
                label.setOffset(new BMap.Size(offsetX, offsetY));
                addMarker.setLabel(label);
            }
        } catch (e) {
        }
    }
    return marker;
}

/**************************************************地图覆盖物对象相关操作结束***********************************/
/**
 * 地图组件自适应
 * @param width
 */
function adapteMapToolsPosition(width) {
    var newWidth = width + 10;
    if ($(".BMap_stdMpCtrl.BMap_stdMpType0.BMap_noprint.anchorTL").length > 0) {
        $(".BMap_stdMpCtrl.BMap_stdMpType0.BMap_noprint.anchorTL").css("left", newWidth + "px");
    }
    if ($(".BMap_cpyCtrl.BMap_noprint.anchorBL").length > 0) {
        $(".BMap_cpyCtrl.BMap_noprint.anchorBL").css("left", newWidth + "px");
    }
}

/**
 * 地图组件自适应
 * @param width
 */
function hideMapTools() {
    if ($(".BMap_stdMpCtrl.BMap_stdMpType0.BMap_noprint.anchorTL").length > 0) {
        $(".BMap_stdMpCtrl.BMap_stdMpType0.BMap_noprint.anchorTL").css("visibility", "hidden");
    }
    if ($(".BMap_cpyCtrl.BMap_noprint.anchorBL").length > 0) {
        $(".BMap_cpyCtrl.BMap_noprint.anchorBL").css("visibility", "hidden");
    }
    if ($(".BMap_scaleCtrl.BMap_noprint.anchorTL").length > 0) {
        $(".BMap_scaleCtrl.BMap_noprint.anchorTL").css("visibility", "hidden");
    }
    if ($(".anchorBL").length > 0) {
        $(".anchorBL").css("visibility", "hidden");
    }

}

/**
 * @Description 增加 遮罩层
 * @Author zr
 * @Date 2018/06/08 14:33:27
 */
function addMapvLayer() {
    //  var url = contextPath + "/static/baiduJson/hebei.json";

    if (isObjectNotNull(mapvLayer)) {
        mapvLayer.show(); // 显示图层
        setInterval(function () {
            mapvLayer.canvasLayer.adjustSize();
            mapvLayer.canvasLayer._draw();
        }, 1);
    }else{
        var url = contextPath + "/static/baiduJson/hebei_new.json";
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            async: false,
            success: function (geojson) {

                var dataSet = mapv.geojson.getDataSet(geojson);
                var citys = {
                    '河北': Math.random() * 70,

                }
                var data = dataSet.get({
                    filter: function (item) {
                        if (!citys[item.name]) {
                            return false;
                        }

                        item.count = citys[item.name];
                        return true;

                    }
                });
                dataSet = new mapv.DataSet(data);
                mapvLayer = new mapv.baiduMapLayer(map, dataSet, mapvLayerOptions);
            }

        });
    }
}

/**
 * 切换地图底图颜色
 * @param skinType
 */
function changeMapSkin(skinType) {
    if (isStrNull(skinType)) {
        return;
    }
    var obj = new Object();
    if (skinType == 1) {//黑色主题，对应地图是午夜类型
        map.setMapType(MAPCONSTANT.MAPTYPE.midNight);
        // mapvLayerOptions.fillStyle = 'rgba(4, 52,72, 0.8)';
        mapvLayerOptions.fillStyle = 'rgba(1,1,1, 0.4)';
        mapvLayerOptions.shadowColor = 'rgba(1,1,1, 0.4)';
        //  mapvLayerOptions.strokeStyle = 'rgba(117, 145, 164, 1)';
        mapvLayerOptions.strokeStyle = 'rgba(255, 255, 255, 1)';
        // mapvLayerOptions.strokeStyle = 'rgba(250, 250, 55, 1)';
        $("#map").css("backgroundColor", "black");
        provinceLineArr = addProvinceLine("yellow");
    }
    if (skinType == 2) {//白色主题，对应地图是正常类型
        map.setMapType(MAPCONSTANT.MAPTYPE.normal);
        mapvLayerOptions.fillStyle = 'rgba(244, 243, 239, 0.8)';
        mapvLayerOptions.shadowColor = 'rgba(244, 243, 239, 0.8)';
        mapvLayerOptions.strokeStyle = 'rgba(43, 43, 43, 1)';
        $("#map").css("backgroundColor", "white");
        provinceLineArr = addProvinceLine("blue");
    }
    obj.options = mapvLayerOptions;
    if (isObjectNotNull(mapvLayer)) {
        mapvLayer.update(obj);//更新遮罩层配色options
    }
    changeLabelStyle(skinType);//更新图标弹出框颜色
}

/**
 * 手动刷新mavLayer
 */
function redrawMapvLayer() {
    if (isStrNotNull(mapCenterPointStr)) {
        var mapCenterPointStrArray = mapCenterPointStr.split(",");
        var jd = mapCenterPointStrArray[0];
        var wd = mapCenterPointStrArray[1];
        var zoom = mapCenterPointStrArray[2];
        //初始化地图中心点 （石家庄）
        initMap(jd, wd, zoom);
    }
    addMapvLayer();//添加遮罩层
    changeMapSkin(skinType);
}

/**
 * 获取地图上所有marker对象集合，后续增加对其他类型的图标判断
 * 参考 @
 * @returns {any[]}
 */
function getAllMarkerFromMap() {
    var allOverlay = map.getOverlays();
    var markerArray = new Array();
    for (var i = 0; i < allOverlay.length; i++) {
        var overlay = allOverlay[i];
        if (overlay.toString() == "[object Marker]") {
            markerArray.push(overlay)
        }
    }
    return markerArray;
}

/**
 * 根据系统皮肤颜色更新图标弹出框颜色
 * @param skinType
 */
function changeLabelStyle(skinType) {
    if (isStrNotNull(skinType)) {
        var markerArray = getAllMarkerFromMap();
        if (isObjectNotNull(markerArray)) {
            for (var i = 0; i < markerArray.length; i++) {
                var marker = markerArray[i];
                var label = marker.getLabel();
                var graAttr = getAttrForGraphic(marker);
                if (isObjectNotNull(label)) {
                    try {
                        var type = graAttr.type;
                        // var labelStyle = label.z.Rp;//暂时这么写，本地是这样的（获取的就是label的style，百度没有提供对外接口）
                        var labelStyle = label._config.styles;//暂时这么写，现场是这样的(获取的就是label的style，百度没有提供对外接口)
                        var newLabelStyle = getLabelStyle(labelStyle, type);//根据skinType获得对应的label的配色方案（该方法内部使用）
                        label.setStyle(newLabelStyle);//默认设置标注物上方的文字的背景边框是灰色
                    } catch (e) {
                    }
                }
            }
        }
    }

}

/**
 * 根据skinType获得对应的label的配色方案（该方法内部使用）
 * @param labelStyle
 * @param type 区分聚合图标还是普通图标
 * @returns {*}
 */
function getLabelStyle(labelStyle, type) {
    var newLabelStyle = labelStyle;
    //  styleObj = {color: titleColor, border: "0px solid #a09f9e", backgroundColor: "rgba(66,77,95,0.8)"};
    if (skinType == 1) {//黑色主题，对应地图是午夜类型
        if (type == 'point') {
            //    newLabelStyle.backgroundColor = "rgba(66,77,95,0.8)";
            newLabelStyle.backgroundColor = "rgba(66,77,95,1)";
            newLabelStyle.color = "white";
        }
        if (type == 'textPoint') {//聚合图标
            newLabelStyle.backgroundColor = "rgba(66,77,95,0)";
        }
    }
    if (skinType == 2) {//白色主题，对应地图是正常类型
        if (type == 'point') {
            newLabelStyle.backgroundColor = "rgba(255,255,255,0.8)";
            newLabelStyle.color = "black";

        }
        if (type == 'textPoint') {//聚合图标
            newLabelStyle.backgroundColor = "rgba(255,255,255,0)";
        }
    }
    return newLabelStyle;
}

/**
 * 增加 遮罩层
 */
function tt() {
    var pointArray = new Array();

//    return;
    //  var url = contextPath + "/static/baiduJson/hebei.json";
    var url = contextPath + "/static/baiduJson/hebei_new.json";
    var jwdTempArr = new Array();
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        async: false,
        success: function (geojson) {
            console.info(geojson);
            var jwdArray = geojson.features[1].geometry.coordinates[0][0];
            for (var k = 0; k < jwdArray.length; k++) {
                //     jwdArray[k]=parseFloat(jwdArray[k]);
                var temp = new Array();
                temp.push(jwdArray[k][0]);
                temp.push(jwdArray[k][1]);
                jwdTempArr.push(temp);
            }

        }
    });
    console.info(jwdTempArr);

    for (var j = 0; j < jwdTempArr.length; j++) {
        var p = new BMap.Point(jwdTempArr[j][0], jwdTempArr[j][1]);
        pointArray.push(p);
    }

//    return ;
    //   var c = new BMap.PointCollection(pointArray);
    //  map.addOverlay(c);
    //  map.setViewport(pointArray);
}

/**
 * @Author zr
 * @Description 轨迹移动方法，若在线则需要路径规划后台服务，离线的话则按照既定路径移动图标
 * @param onlineFlag 在线标志（Y：开启，N：关闭，默认N关闭），在线则需要对应的路径规划服务
 * @param pointArrStr 以逗号分隔的坐标集字符串如(120.23,23.34,120.45,24.56)(在线则使用前两个点作为路径规划起终点；离线则根据数组中的点作为路径)
 * @param trajectoryObj 轨迹移动obj对象，其中封装了图标、速度、轨迹等相关配置（其中图标使用2D图标，车头方向为正右）
 * @return lushuObj 返回路径对象lushuObj，业务层保存该对象，在暂停等方法中传入该对象实现其他路径业务操作
 * @Date 2018/03/15 12:05:44
 */
function trajectoryMove(onlineFlag, pointArrStr, trajectoryObj) {
    if (isObjectNull(onlineFlag) || isStrNull(pointArrStr) || isObjectNull(trajectoryObj)) {
        return null;
    }
    var lushuObj;
    var trajectoryPathArrStr = getTrajectoryPath(onlineFlag, pointArrStr);
    if (isStrNotNull(trajectoryPathArrStr)) {
        lushuObj = movementInit(trajectoryPathArrStr, trajectoryObj);
        if (isObjectNotNull(lushuObj)) {
            lushuObj.start();
            move
            lushuObj.showInfoWindow();
        }
    }
    return lushuObj;
}

/**
 * @Author zr
 * @Description 根据在线标志获取路径：在线调用路径规划接口，根据pointArr的起终点获取沿途路径；离线的则直接返回pointArr数组
 * @param onlineFlag 在线标志
 * @param pointArr 路径数组
 * @Date 2018/03/15 15:50:33
 */
function getTrajectoryPath(onlineFlag, pointArr) {
    var trajectoryPathArrStr = "";
    if (isObjectNull(onlineFlag) || isArrayNull(pointArr) || pointArr.length < 1) {
        return "";
    }
    if (onlineFlag == "Y") {
        var newPointArr = pointArr.split(",");
        var startPoint = newPointArr[1] + "," + newPointArr[0];
        var endPoint = newPointArr[3] + "," + newPointArr[2];
        var queryUrl = MAPCONSTANT.QUERYURL.TRAJECTORYSEARCH.replace("startPoint", startPoint).replace("endPoint", endPoint);
        console.info(queryUrl);
        /****测试*******/
            return pointArr;
        $.ajax({
            url:basePath+"/baiduService.do?act=getBaiduService&queryPoints="+startPoint+"-"+endPoint+"&enumType=0800",
            type: "GET",
            dataType: "jsonp",
            jsonpCallback: "getBaiduService",
            async: false,
            success: function (data) {
                console.info("success:" + data);
                if (isObjectNotNull(data)) {
                    if (data.message == 'ok') {
                        if (isArrayNotNull(data.results)) {
                            var resultPathsArr = data.results[0].path;
                            var distance = data.results[0].distance;
                            var queryTimeStr = data.results[0].time;
                            trajectoryPathArrStr = resultPathsArr;
                        }
                    }
                }
            },
            error: function () {
                alert("发生异常");
            }
        });
    } else {
        trajectoryPathArrStr = pointArr;
    }
    return trajectoryPathArrStr;
}


function testJsonp(data) {
    console.info(data);
}

/**
 * @Author zr
 * @Description 根据路径和轨迹移动obj对象生成路书对象
 * @param
 * @Date 2018/03/15 04:45:15
 */
function movementInit(trajectoryPathArrStr, trajectoryObj) {
    var lushuObj;
    if (isStrNotNull(trajectoryPathArrStr)) {
        trajectoryObj = setDefaultValue(trajectoryObj);
        var polyline = addPolyLineOnMap(trajectoryPathArrStr, trajectoryObj.lineColor);
        var linePoints = converStringToPointArr(trajectoryPathArrStr);
        if (isObjectNotNull(polyline) && isArrayNotNull(linePoints)) {
            var moveIcon = new BMap.Icon(trajectoryObj.imageUrl, new BMap.Size(58, 36));
            //   moveIcon.setImageSize(new BMap.Size(40,20));
            //使用离线路书
            lushuObj = new BMapLib.LuShu(map, linePoints, {
                defaultContent: "从北京到石家庄",
                clickFunName: aaa,
                speed: trajectoryObj.speed,//速度m/s
                icon: moveIcon,
                autoView: false,
                enableRotation: true
                // ,
                // landmarkPois: [
                //     {lng:114.62103224,lat:37.93863040,html:'肯德基早餐<div><img src="http://lbsyun.baidu.com/jsdemo/img/car.png"/></div>',pauseTime:2}
                // ]
            });
        }
    }
    return lushuObj;
}

function aaa() {
    alert(1234);
}

/**
 * @Author zr
 * @Description 轨迹移动方法测试
 * @param
 * @Date 2018/03/15 16:59:24
 */
function move() {
    var pointArrStr = "116.46193000,39.87222000,115.7940659521,39.2257526358,115.7940659521,39.2257526358,115.7940659521,39.2257526358,115.5756895427,38.8257089989,114.9976283033,38.4685366087,114.7301879628,38.3427547844,114.5328773128,38.1068126802";
    var trajectoryObj = new TrajectoryObj();
    trajectoryObj.speed = 20000;
    var tempLushuObj = trajectoryMove("Y", pointArrStr, trajectoryObj);
    lushuObjGloble = tempLushuObj;
    setTimeout("rtTest1()", 2000);
    setTimeout("rtTest2()", 5000);
    //   var   lushuObjGloble2 = trajectoryMove("N",pointArr2,trajectoryObj);
    //   trajectoryObj.speed=10000;
    // var   lushuObjGloble2 = trajectoryMove("N",pointArr2,trajectoryObj);
    //   trajectoryObj.speed=1000;
    //   var   lushuObjGloble3 = trajectoryMove("N",pointArr3,trajectoryObj);
}

/**
 * @Description 暂停图标移动测试用例
 * @param
 * @return
 * @Author zr
 * @Date 2018/03/26 20:58:11
 */
function pauseTrajectory() {
    trajectoryPause(lushuObjGloble);
}

/**
 * @Description 隐藏图标移动过程中的信息框
 * @param
 * @return
 * @Author zr
 * @Date 2018/03/26 20:56:56
 */
function hideTrajectoryInfoWindow(lushuObjGloble) {
    if (isObjectNotNull(lushuObjGloble)) {
        lushuObjGloble.hideInfoWindow();
    }
}

/**
 * @Description 显示图标移动过程中的信息框
 * @param
 * @return
 * @Author zr
 * @Date 2018/03/26 20:57:08
 */
function showTrajectoryInfoWindow(lushuObjGloble) {
    if (isObjectNotNull(lushuObjGloble)) {
        lushuObjGloble.showInfoWindow();
    }
}

/**
 * @Author zr
 * @Description 轨迹移动停止
 * @param lushuObj 轨迹开始时返回的轨迹obj对象
 * @return
 * @Date 2018/03/16 11:27:31
 */
function trajectoryStop(lushuObj) {
    if (isObjectNotNull(lushuObj)) {
        lushuObj.stop();
    }
}

/**
 * @Author zr
 * @Description 轨迹移动暂停
 * @param lushuObj 轨迹开始时返回的轨迹obj对象
 * @return
 * @Date 2018/03/16 11:28:20
 */
function trajectoryPause(lushuObj) {
    if (isObjectNotNull(lushuObj)) {
        lushuObj.pause();
    }
}

/**
 * @Description 图标继续移动轨迹方法
 * @param lushuObj首次初始化移动轨迹返回的obj对象
 * @return
 * @Author zr
 * @Date 2018/03/26 20:59:29
 */
function goOnTrajectory(lushuObj) {
    if (isObjectNotNull(lushuObj)) {
        lushuObj.start();
    }
}

/**
 * @Description 添加折线到地图
 * @param coordinates 以逗号分隔的坐标集字符串如（120.23,23.34,120.45,24.56）
 * @param color 线段颜色
 * @return
 * @Author zr
 * @Date 2018/03/28 17:22:32
 */
function addPolyLineOnMap(coordinates, lineColor) {
    var lineGraphic;
    if (isStrNotNull(coordinates)) {
        var linePoints = converStringToPointArr(coordinates);
        if (isArrayNotNull(linePoints)) {
            isStrNull(lineColor) ? "red" : lineColor;
            lineGraphic = new BMap.Polyline(linePoints, {strokeColor: lineColor, strokeWeight: 2, strokeOpacity: 0.8});
            if (isObjectNotNull(lineGraphic)) {
                map.addOverlay(lineGraphic)
            }
        }
    }
    return lineGraphic;
}


function openMarkerPopup(graphic) {
    if (isObjectNotNull(graphic)) {
        var popupObj = getAttrForGraphic(graphic);
        if (isObjectNotNull(popupObj)) {
            var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts);  // 创建信息窗口对象
            var point = graphic.point;
            map.openInfoWindow(infoWindow, point); //开启信息窗口
        }
    }
}

/**
 * @Description 改变线段颜色
 * @param lineGra 已有线段对象
 * @param lineColor 要更新的线段颜色，默认为红色
 * @return lineGra 颜色改变后的线段对象
 * @Author zr
 * @Date 2018/04/10 16:22:20
 */
function changePolyineColor(lineGra, lineColor) {
    if (isObjectNotNull(lineGra) && (lineGra.toString() == "[object Polyline]")) {
        var newLineColor = isStrNull(lineColor) ? "red" : lineColor;
        lineGra.setStrokeColor(newLineColor);
        return lineGra;
    } else {
        return null;
    }
}

/**
 * @Description 获取两点距离
 * @param jd1 第一个点经度
 * @param wd1 第一个点纬度
 * @param jd2 第二个点经度
 * @param wd2 第一个点纬度
 * @return 两点间距离，单位是米。若异常，则返回null
 * @Author zr
 * @Date 2018/04/23 21:57:18
 */
function getTwoPointDistance(jd1, wd1, jd2, wd2) {
    var resultDistance;
    if (isObjectNotNull(jd1) && isObjectNotNull(wd1) && isObjectNotNull(jd2) && isObjectNotNull(wd2)) {
        try {
            resultDistance = map.getDistance(new BMap.Point(jd1, wd1), new BMap.Point(jd2, wd2));
        } catch (e) {
            resultDistance = null;
        }
    } else {
        resultDistance = null;
    }
    return resultDistance;
}

/**
 * @Description 实时移动轨迹方法，不用考虑是否暂停，内部已处理
 * @param currLushuObj当前路书移动obj
 * @param coordinates下一个点或多个点的字符串（形如“116.46193000,39.87222000,115.7940659521,39.2257526358”）
 * @param speed移动物体当前速度（可能速度改变有延迟）
 * @return currLushuObj返回新的移动路书obj
 * @Author zr
 * @Date 2018/04/24 23:15:41
 */
function realTimeMoving(currLushuObj, coordinates, speed) {
    if (isObjectNotNull(currLushuObj) && isObjectNotNull(coordinates)) {
        var pointArrStr = "116.46193000,39.87222000,115.7940659521,39.2257526358";
        //    coordinates = pointArrStr;
        var newRoadPoints = converStringToPointArr(coordinates);
        currLushuObj.goPath(newRoadPoints);
    }
    if (isStrNumber(speed)) {
        currLushuObj.changeSpeed(speed);
    }
    return currLushuObj;
}

function rtTest1() {
    lushuObjGloble = realTimeMoving(lushuObjGloble, "119.4190,32.3999,120.3189,31.4967", "1ds");
}

function rtTest2() {
    lushuObjGloble = realTimeMoving(lushuObjGloble, "120.1617,30.2799,120.5866,30.0365,121.5566,29.8802", 80000);
}

/**
 * @Description 自动调整地图视野
 * @param coordinates经纬度字符串（形如“116.46193000,39.87222000,115.7940659521,39.2257526358”）
 * @return void
 * @Author zr
 * @Date 2018/04/24 23:35:30
 */
function adjustMapView(coordinates) {
    if (isObjectNotNull(map) && isStrNotNull(coordinates)) {
        var pointsArr = converStringToPointArr(coordinates);
        map.setViewport(pointsArr);
    }
}

/**
 * @Description 根据线段类型和颜色画线
 * @param type 线段类型, 参考LINESTYLE常量，默认solid实线
 * @param coordinates 以逗号分隔的坐标集字符串如（120.23,23.34,120.45,24.56）
 * @param color 线段颜色
 * @example var lineObj = addPolyLineOnMapByType(MAPCONSTANT.LINESTYLE.STYLE_SOLID,"120.23,23.34,120.45,24.56","red");
 * @return 返回线段类型obj
 * @Author zr
 * @Date 2018/05/10 10:48:29
 */
function addPolyLineOnMapByType(lineType, coordinates, lineColor) {
    var lineGraphic;
    if (isStrNotNull(lineType) && isStrNotNull(coordinates)) {
        var linePoints = converStringToPointArr(coordinates);
        if (isArrayNotNull(linePoints)) {
            lineColor = isStrNull(lineColor) ? "red" : lineColor;
            lineGraphic = new BMap.Polyline(linePoints, {
                strokeStyle: lineType,
                strokeColor: lineColor,
                strokeWeight: 2,
                strokeOpacity: 0.8
            });
            if (isObjectNotNull(lineGraphic)) {
                map.addOverlay(lineGraphic)
            }
        }
    }
    return lineGraphic;
}


/**
 * 设置弹出框
 * @param graphic 图标对象
 * @param pictureMarkerTemplateInfo 弹出框对象PictureMarkerTemplateInfo
 * @param width  宽度 若没有则会自适应
 * @param height 高度 若没有则会自适应
 * @return
 */
function setPopupTemplateForGraphic(graphic, pictureMarkerTemplateInfo, width, height) {
    if (isObjectNull(graphic) || isObjectNull(pictureMarkerTemplateInfo)||isStrNull(pictureMarkerTemplateInfo.templateContent)) {
        return;
    }
    BMap.Marker.prototype.openMyInfoWindow=function(){
        for(key in this){
            if($(this[key]).hasClass("BMap_noprint")){
                $(this[key]).trigger("click");
                break;
            }
        }
    };


    pictureMarkerTemplateInfo.templateTitle =  isStrNull(pictureMarkerTemplateInfo.templateTitle) ? "" : pictureMarkerTemplateInfo.templateTitle;
    width =  isStrNull(width) ? "" : width;
    height =  isStrNull(height) ? "" : height;
    var opts = {
        width : width,     // 信息窗口宽度
        height: height,     // 信息窗口高度
        title : pictureMarkerTemplateInfo.templateTitle , // 信息窗口标题
        enableMessage:true//设置允许信息窗发送短息
    };

    var infoWindow = new BMap.InfoWindow(pictureMarkerTemplateInfo.templateContent,opts);
    graphic.addEventListener("click", function(){
        this.openInfoWindow(infoWindow);
    });
    if (pictureMarkerTemplateInfo.templateShow) {
        graphic.openMyInfoWindow();
    }

    return graphic;// TODO 需要实现，此处先return
}

/**
 * @Description 隐藏弹出框
 * @Author zr
 * @Date 2018/05/23 11:02:30
 */
function hideInfoWindow() {
    return;//TODO 需实现百度版本
    map.infoWindow.hide();
}

/**
 * @Description 清楚地图上所有覆盖物
 * @Author zr
 * @Date 2018/05/23 11:15:06
 */
function iclear() {
    map.clearOverlays();
}

/**
 * 添加箭头到地图
 * @param lineType 线段类型, 参考LINESTYLE常量，默认solid实线
 * @param coordinates 以逗号分隔的坐标集字符串如（120.23,23.34,120.45,24.56）;
 * @param lineColor 线段颜色颜色，默认蓝色
 * @param arrowColor 箭头颜色，默认红色
 * @return 含有箭头的折线对象；
 */
function addPolyLineAndArrowOnMap(lineType,coordinates, lineColor, arrowColor) {
    var arrowLineGraphic;

    if (isStrNotNull(coordinates)) {
        lineType =  isStrNull(lineType) ? MAPCONSTANT.LINESTYLE.STYLE_SOLID : lineType;
        lineColor =  isStrNull(lineColor) ? "blue" : lineColor;
        arrowColor =  isStrNull(arrowColor) ? "red" : arrowColor;
        var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
            scale: 0.6,//图标缩放大小
            strokeColor: arrowColor,//设置矢量图标的线填充颜色
            strokeWeight: '2',//设置线宽
        });
        var icons = new BMap.IconSequence(sy, '5%', '5%');
        // 创建polyline对象
        var linePoints = converStringToPointArr(coordinates);
        arrowLineGraphic = new BMap.Polyline(linePoints, {
            strokeStyle: lineType,
            enableEditing: false,//是否启用线编辑，默认为false
            enableClicking: true,//是否响应点击事件，默认为true
            icons: [icons],
            strokeWeight: '8',//折线的宽度，以像素为单位
            strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
            strokeColor: lineColor //折线颜色
        });

        map.addOverlay(arrowLineGraphic);
    }
    return arrowLineGraphic;
}

/**
 * @Description 隐藏遮罩层
 * @Author zr
 * @Date 2018/06/08 14:35:29
 */
function hideMapvLayer(){
    if(isObjectNotNull(mapvLayer)){
        mapvLayer.hide();
    }
}

/**
 * @Description 销毁遮罩层
 * @return
 * @Author zr
 * @Date 2018/06/08 14:31:58
 */
function destroyMapvLayer(){
    if(isObjectNotNull(mapvLayer)){
        mapvLayer.destroy();
    }
}

/**
 * @Description 百度路径规划
 * @return
 * @Author lm
 * @Date 2018/06/11 18:31:58
 */
function baiduRoadPlan(pointArr){
    var newPointArr = pointArr.split(",");
    var startPoint = newPointArr[1] + "," + newPointArr[0];
    var endPoint = newPointArr[3] + "," + newPointArr[2];
    var queryUrl = MAPCONSTANT.QUERYURL.TRAJECTORYSEARCH.replace("startPoint", startPoint).replace("endPoint", endPoint);
    console.info(queryUrl);
    //    return pointArr;
    $.ajax({
        url:basePath+"/baiduService.do?act=getBaiduService&queryPoints="+startPoint+"-"+endPoint+"&enumType=0800",
        type: "GET",
        dataType: "jsonp",
        jsonpCallback: "getBaiduService",
        async: false,
        success: function (data) {
            console.info("success:" + data);
            if (isObjectNotNull(data)) {
                if (data.message == 'ok') {
                    if (isArrayNotNull(data.results)) {
                        var resultPathsArr = data.results[0].path;
                        var distance = data.results[0].distance;
                        var queryTimeStr = data.results[0].time;
                        trajectoryPathArrStr = resultPathsArr;
                    }
                }
            }
        },
        error: function () {
            alert("发生异常");
        }
    });
}

/**
 * @Description 百度接口 点到线的距离
 * @param pointArr 点坐标
 * @example 116.369199,40.002759
 * @param lineArr 线段起点终点坐标
 * @return 116.318606,39.966935;116.445663,39.96782
 * @Author lm
 * @Date 2018/06/12 18:00:00
 */
function poinToLineDistance(pointArr,lineArr){
    var distance = null;
    $.ajax({
        url:basePath+"/baiduService.do?act=getBaiduService&queryPoints="+lineArr+"-"+pointArr+"-distance&enumType=0400",
        type: "GET",
        dataType: "jsonp",
        jsonpCallback: "getBaiduService",
        async: false,
        success: function (data) {
            console.info("success:" + data);
            if (isObjectNotNull(data)) {
                if (data.message == 'ok') {
                    if (isArrayNotNull(data.results)) {
                        distance = data.results[0].distance;
                    }
                }
            }
        },
        error: function () {
            alert("发生异常");
        }
    });
    return distance;
}

/**
 * @Description 增加自定义多边形
 * @param PolygonObj多边形配置对象
 * @return polygonGra返回多边形地图对象
 * @Author zr
 * @Date 2018/06/09 15:21:37
 */
function addAreaOnMapCustomize(polygon){
    var polygonGra;
    if(isObjectNotNull(polygon)){
        var polygonCoordinates = polygon.coordinates;
        if(isStrNotNull(polygonCoordinates)){
            var borderColor = polygon.borderColor;
            var fillColor = polygon.fillColor;
            var pointsArr = converStringToPointArr(polygonCoordinates);
            polygonGra = new BMap.Polygon(pointsArr, {strokeColor:borderColor, fillOpacity:"0.2",fillColor:fillColor,strokeWeight:2, strokeOpacity:0.5});  //创建多边形
            map.addOverlay(polygonGra); //增加多边形
        }
    }
    return polygonGra;
}

/**
 * @Description 用多边形模拟添加省级边界
 * @Author zr
 * @Date 2018/06/09 10:50:14
 */
function addProvincialLineByPolygon(){
    var url = contextPath + "/static/baiduJson/hebei_new.json";
    var coordinatesArr = new Array();
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        async: false,
        success: function (geojson) {
            var featuresArr = geojson.features;
            if (isArrayNotNull(featuresArr)) {
                var obj = featuresArr[1];
                var objLen = obj.geometry.coordinates.length;
                for(var i=0;i<objLen;i++){
                    var coordinatesStr = "";
                    var coordinatesLen = obj.geometry.coordinates[i][0].length;
                    for (var j = 0; j < coordinatesLen; j++) {
                        coordinatesStr = coordinatesStr + obj.geometry.coordinates[i][0][j][0] + "," + obj.geometry.coordinates[i][0][j][1] + ",";
                    }
                    coordinatesStr = (coordinatesStr.substring(coordinatesStr.length - 1) == ',') ? coordinatesStr.substring(0, coordinatesStr.length - 1) : coordinatesStr;
                    var polygonInfo = new PolygonObj();
                    polygonInfo.coordinates = coordinatesStr;
                    polygonInfo.borderColor = "blue";
                    polygonInfo.fillColor = "yellow";
                    addAreaOnMapCustomize(polygonInfo);
                }
            }
        }
    });
}

/**
 * @Description 根据颜色添加省市边界分割线
 * @param lineColor 边界线颜色
 * @return 边界线地图对象数组
 * @Author zr
 * @Date 2018/06/14 21:06:02
 */
function addProvinceLine(lineColor){
    if(isArrayNotNull(provinceLineArr)){
        removeMarkerOnMap(provinceLineArr);
        provinceLineArr = new Array();
    }
    var url = contextPath + "/static/baiduJson/hebei_province_line.json";
    lineColor = (isStrNotNull(lineColor)?lineColor:"blue");
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        async: false,
        success: function (geojson) {
            var result = geojson;
            for(var i=0;i<result.length;i++){
                var coornates = result[i].coordinates;
              //  addPolyLineOnMap(coornates,"blue");
                var lineGra = addPolyLineOnMapByType(MAPCONSTANT.LINESTYLE.STYLE_DASH,coornates,lineColor);
                provinceLineArr.push(lineGra);
            }
        },
        error: function (error) {
          //  console.info(error);
        }
    });
    return provinceLineArr;
}