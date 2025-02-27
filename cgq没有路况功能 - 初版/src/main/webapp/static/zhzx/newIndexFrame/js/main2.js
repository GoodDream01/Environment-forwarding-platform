var openTabIndex=0;//全局变量，标识当前被打开菜单按照顺序分配的索引值，用以后面一一对应
//期待生成菜单的数据格式(name菜单名称，url菜单链接地址,icon菜单class名称用于显示不同的图标)

var menuListsJSON=[
		{icon:'icon0',name:'首页',url:'',mkdm:'AAAA',qxdm:'main'}
		
/*		{icon:'icon01',name:'基础信息2',url:'',hastab:false,openTabIndex:null},
		{icon:'icon02',name:'驾驶人绑定3',url:'',hastab:false,openTabIndex:null},
		{icon:'icon03',name:'报警信息4',url:'',hastab:false,openTabIndex:null},
		{icon:'icon04',name:'交流互动5',url:'',hastab:false,openTabIndex:null},
		{icon:'icon05',name:'综合查询6',url:'',hastab:false,openTabIndex:null},
		{icon:'icon06',name:'重点管理统7',url:'',hastab:false,openTabIndex:null},
		{icon:'icon07',name:'系统管理8',url:'',hastab:false,openTabIndex:null},
		{icon:'icon08',name:'基础信息9',url:'',hastab:false,openTabIndex:null},
		{icon:'icon09',name:'驾驶人绑定10',url:'',hastab:false,openTabIndex:null},
		{icon:'icon10',name:'报警信息11',url:'',hastab:false,openTabIndex:null},
		{icon:'icon11',name:'交流互动12',url:'',hastab:false,openTabIndex:null},
		{icon:'icon12',name:'综合查询13',url:'',hastab:false,openTabIndex:null},
		{icon:'icon13',name:'重点管理14',url:'',hastab:false,openTabIndex:null},
		{icon:'icon14',name:'系统管理15',url:'',hastab:false,openTabIndex:null},
		{icon:'icon15',name:'驾驶人绑定16',url:'',hastab:false,openTabIndex:null},
		{icon:'icon16',name:'报警信息17',url:'',hastab:false,openTabIndex:null},
		{icon:'icon17',name:'交流互动18',url:'',hastab:false,openTabIndex:null},
		{icon:'icon18',name:'综合查询19',url:'',hastab:false,openTabIndex:null},
		{icon:'icon19',name:'重点管理统20',url:'',hastab:false,openTabIndex:null},
		{icon:'icon20',name:'系统管理21',url:'',hastab:false,openTabIndex:null},
		{icon:'icon21',name:'基础信息22',url:'',hastab:false,openTabIndex:null},
		{icon:'icon22',name:'驾驶人绑定23',url:'',hastab:false,openTabIndex:null},
		{icon:'icon23',name:'报警信息24',url:'',hastab:false,openTabIndex:null},
		{icon:'icon24',name:'交流互动25',url:'',hastab:false,openTabIndex:null},
		{icon:'icon25',name:'综合查询26',url:'',hastab:false,openTabIndex:null},
		{icon:'icon26',name:'重点管理27',url:'',hastab:false,openTabIndex:null},
		{icon:'icon27',name:'系统管理28',url:'',hastab:false,openTabIndex:null}*/
	];
/**
 * 获取一级菜单列表
 */
var getMenuList = function(callback) {
	//var url = contextPath + "/newIndexMange.do?act=getMenuList";
	//var url = "menu.json";
	var url = contextPath + "/menu/getTopMenu.do";
	$.ajax({
		type:"post",
		url:url,
		dataType:"json",
		async: false,
		success: function(data){
			if (isListNotNull(data)) {
				callback(data);
			}
		},
		error: function(e){console.log(e);
			alert("获取菜单数据异常！");
		}
	});
}

var getChildMenu = function (sjmkdm, src) {
	//var url = contextPath + "/newIndexMange.do?act=getChileMenuHtml";
	//var url = "../../data/childMenu.txt";
	var url = contextPath + "/menu/getChileMenuHtml.do";
	$.ajax({
		type:"post",
		url:url,
		data:{"pcode":sjmkdm},
		dataType:"text",
		async: false,
		success: function(data){
			if (isStrNotNull(data)) {
				$(".menu_iframe .menuBox").html(data);
				if ($(".menu_iframe .menuBox").find(".item").length > 0) {
					if (isStrNull(src) ||"#" == src) {
						if ($(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").length > 0) {
							$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").eq(0).click();
						} else {
							$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").click();
						}
					}
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").addClass("selected");
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").show();
				}
				//alert($(".menu_iframe .menuBox").find(".item").length);
				
			}
		},
		error: function(){
			alert("获取菜单数据异常！");
		}
	});
}

var getChildMenuOfIframe = function (topMkdm) {
	//var url = contextPath + "/newIndexMange.do?act=getChileMenuHtml";
	var url = contextPath + "/menu/getChileMenuHtml.do";
	$.ajax({
		type:"post",
		url:url,
		data:{"pcode":topMkdm},
		dataType:"text",
		async: false,
		success: function(data){
			if (isStrNotNull(data)) {
				$(".menu_iframe .menuBox").html(data);
				if ($(".menu_iframe .menuBox").find(".item").length > 0) {
					/*if (isStrNull(src) ||"#" == src) {
						if ($(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").length > 0) {
							$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").eq(0).click();
						} else {
							$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").click();
						}
					}*/
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").addClass("selected");
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").show();
				}
				//alert($(".menu_iframe .menuBox").find(".item").length);
				
			}
		},
		error: function(){
			alert("获取菜单数据异常！");
		}
	});
}


/**
 * 没有三级菜单的二级菜单，移除三角符号图标
 */
var removeMenuTitleIcon = function (mkdm) {
	$("#childMenu" + mkdm).find(".item").each(function(){
		if (!$(this).find(".menu2").length) {
			$(this).find(".menuTitle").addClass("removeTitleIcon");
		}
	});
}

$(function(){
	
	//获取一级菜单json数据
	getMenuList(function(data){
		if (isListNotNull(data)) {
			for (var i = 0; i < data.length; i++) {
				var menu = data[i];console.log(menu);
				//if (isStrNotNull(menu.qxzl) && "0" === menu.qxzl && (isStrNull(menu.xtlb) || "00" === menu.xtlb)) {
					var menuJson = new Object();
					menuJson.icon = menu.menuIcon||'';
					menuJson.name = menu.menuName||'';
					menuJson.url = menu.menuPath||'';
					menuJson.mkdm = menu.menuCode||'';
					menuJson.qxdm = menu.qxdm||'';
					menuListsJSON.push(menuJson);
				//}
			}
		}
	});
	//载入页面自适应函数
	indexFrameAdapt();
	
	/***头部菜单开始*****/
	
	/***头部菜单结束*****/	
	/*头部时间开始*/
        setInterval(function(){
                var date=new Date();
                var year=date.getFullYear();
                var month=date.getMonth()+1;
                var day=date.getDate();
                var week=date.getDay();
                var time=date.toTimeString().substr(0,8);
                $(".header .systemTime").html(toDouble(year)+"-"+toDouble(month)+"-"+toDouble(day)+" "+time);
            },1000);
	//单数转双数
	function toDouble(str){
			var str=String(str);
				if(str.length==1)
				{
					return "0"+str;
				}
				else
				{
					return str;
				}
	}
	/*头部时间结束*/
	
});
//自适应
window.onresize=indexFrameAdapt;
//一级子菜单单击事件
function menuClick(obj,src,mkdm,qxdm){
	$(obj).addClass("selected").siblings(".childMenu").removeClass("selected");
	markSelectedMenuIndex=$(obj).index();//赋值当前标记被选中一级子菜单全局变量
	
	if (!document.getElementById("childMenu" + mkdm)) {
		//拼接二三级菜单并放入相应的Dom标签内
		getChildMenu(mkdm,src);
		removeMenuTitleIcon(mkdm);
	} else {
		if ($(".menu_iframe .menuBox").find(".item").length > 0) {
			if (isStrNull(src) ||"#" == src) {
				if ($(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").length > 0) {
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").eq(0).click();
				} else {
					$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").click();
				}
			}
			$(".menu_iframe .menuBox").find(".item").eq(0).find(".menuTitle ").addClass("selected");
			$(".menu_iframe .menuBox").find(".item").eq(0).find(".menu2").show();
		}
	}
	
	//左侧菜单显示隐藏
	// if($(obj).hasClass("index")){
	// 	movePoint(0);
	// }
	// else
	// {
	// 	movePoint(200);
	// }
var tabName=$(obj).find(".name").text();
var targetIcon=getClassNameByString(obj,'icon');
	//添加自定义标识属性，标记当前菜单是否生成tab
	if(document.getElementById("tab" + mkdm)){
		$("#tab" + mkdm).trigger("click");
	}
	else
	{	
		if (isStrNotNull(src) && "#" != src) {
			//iframe上方插入tab
			var newTab=$("<li id='tab" + mkdm + "' onclick='iframeTabClick(this,\"" + mkdm + "\")'><span class='tabName'>"+tabName+"</span><span class='tabClose' onclick='removeIframe(this)'></span></li>");
			newTab.addClass("selected").appendTo($(".tabIframeChildBox .liBox ul")).siblings("li").removeClass("selected");
			$('<iframe class="mapFrame" id="frame' + mkdm + '" name="mapFrame" src="'+src+'" allowtransparency="true" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>').appendTo($(".iframeBox")).siblings(".mapFrame").addClass("hide");
			//getSameOpentabindexTabJquery(obj).trigger("click");
			//放置tab的ul宽度自增
			$(".removeAll").css({"right":0}).removeClass("hide");//显示移除全部按钮
			var newTabWidth=String(tabName).length*12+37;
			tabUlAutoAddChangeWidth(newTabWidth);
		}
	}
}

//iframe上方tab点击事件(obj为当前点击的tab对象)
function iframeTabClick(obj, topMkdm){
	var index=$(obj).addClass("selected").siblings("li").removeClass("selected").end().index();
		$(".iframeBox .mapFrame").eq(index).removeClass("downZindex").siblings(".mapFrame").addClass("downZindex");
		if (isStrNotNull(topMkdm) && (!document.getElementById("childMenu" + topMkdm))) {
			//拼接二三级菜单并放入相应的Dom标签内
			getChildMenuOfIframe(topMkdm);
			//removeMenuTitleIcon(mkdm);
		}
		//左侧菜单显示隐藏
		// if($(obj).hasClass("index")){
		// 	movePoint(0);
		// }
		// else
		// {
		// 	movePoint(200);
		// }
}
//删除当前tab及其iframe(obj为当前点击的tab对象里面的tabClose)
function removeIframe(obj){
	//放置tab的ul宽度自减
	var removeTab=$(obj).parent();
	var newTabWidth=removeTab.outerWidth();
	tabUlAutoReduceChangeWidth(newTabWidth);
	
	var id=$(obj).parent().attr("id");//获取tab标签id
	var frameId = id.replace("tab","frame");//转换为ifram标签id
	var index = $(obj).parent().index();
		//当前对应菜单自定义属性恢复初始设置
		//menuAttrReset($(obj).parent()[0]);
		if ($(obj).parent().hasClass("selected")) {
			$(obj).parent().removeClass("selected").prev("li").addClass("selected").end().remove();
			$(".iframeBox .mapFrame").eq(index).remove();
			setTimeout(function(){
				$(".iframeBox .mapFrame").eq(index-1).removeClass("hide");
			}, 10);
		} else {
			$(obj).parent("li").remove();
			$("#" + frameId).remove();
		}
		
}

//获取当前iframe上方tab对象集合中与当前点击的菜单对应的tab的jquery对象(obj为当前点击的菜单对象，包括一二级菜单)
function getSameOpentabindexTabJquery(obj){
		var openTabIndex=$(obj).attr("openTabIndex");
		var openTabJquery=null;//全局变量，当前被选中的tab对象
			$(".tabIframeChildBox li").each(function(){
				if(openTabIndex==$(this).attr("openTabIndex"))
				{
					openTabJquery=$(this);
				}
			});
			return openTabJquery;
}
//获取与当前被点击tab的openTabIndex属性相同的class名称为openTabIndex的对象(obj为当前点击的tab对象)
function getSameOpentabindexMenuJquery(obj){
	var openTabIndex=$(obj).attr("openTabIndex");
	var openMenuJquery=null;//与当前被选中的tab对象的openTabIndex属性相同的class名称为openTabIndex的对象
		$(".openTabIndex").each(function(){
				if(openTabIndex==$(this).attr("openTabIndex"))
				{
					openMenuJquery=$(this);
				}
			});
			return openMenuJquery;
}
//获取含有指定字符串的class名称(obj为当前点击的菜单对象，包括一二级菜单)
function getClassNameByString(obj,str){
	var className=$(obj).attr("class");
	var startIndex=className.indexOf(str);
	var result;
		if(startIndex<0)
		{
			alert("指定对象中未找到含有该字符串的class名称！")
		}
		else
		{
			result=className.substr(startIndex,6);
		}
		return result;
}
/*//菜单自定义属性恢复初始设置(obj为当前点击的tab对象)
function menuAttrReset(obj){
	getSameOpentabindexMenuJquery(obj).attr({"hastab":false,"openTabIndex":null});
	var targetIcon=getClassNameByString(getSameOpentabindexMenuJquery(obj.parent),'icon');
	menuListsJSON[targetIcon].hastab=false;
	menuListsJSON[targetIcon].openTabIndex=null;
}*/

//二级菜单标题点击事件
function menu2Click(obj, url, mkdm, mkmc, qxdm, topMkdm){
	/*$(obj).toggleClass("selected").siblings(".menu2").slideToggle().parents(".item").siblings(".item").each(function(){alert($(this).html();)
		$(this).find(".menuTitle").removeClass("selected").siblings(".menu2").slideUp();
	});*/
	if ($(obj).siblings(".menu2").length) {
		$(obj).toggleClass("selected").siblings(".menu2").slideToggle().parents(".item").siblings(".item").each(function(){
			if ($(this).find(".menuTitle").siblings(".menu2").length) {
				$(this).find(".menuTitle").removeClass("selected").siblings(".menu2").slideUp();
			} else {
				$(this).find(".menuTitle").removeClass("selected");
			}
		});
	} else {
		$(obj).toggleClass("selected").parents(".item").siblings(".item").each(function(){
			if ($(this).find(".menuTitle").siblings(".menu2").length) {
				$(this).find(".menuTitle").removeClass("selected").siblings(".menu2").slideUp();
			} else {
				$(this).find(".menuTitle").removeClass("selected");
			}
		});
		
	}
	/*if (document.getElementById("tab" + mkdm)) {
		$("#tab" + mkdm).trigger("click");
	} else {
        if(strlist.indexOf(qxdm)!=-1){
            openFullWindow("navigate.do?act=detail&qxdm="+qxdm,qxdm);
        }else{
        	//专为‘车辆限行’和'事故黑点'打开窗口，过滤其他一级菜单
        	if (isStrNotNull(url) && ("#" != url || ("#" == url && (qxdm=='${pris_enum.MENU_VLM_LIMIT}' || qxdm=='R01')))) {
    			//iframe上方插入tab
        		url = "navigate.do?act=detail&qxdm=" + qxdm;
    			$("<li id='tab" + mkdm + "' onclick='iframeTabClick(this)'><span class='tabName'>"+mkmc+"</span><span class='tabClose' onclick='removeIframe(this)'></span></li>").addClass("selected").appendTo($(".tabIframeChildBox ul")).siblings("li").removeClass("selected");
    			$('<iframe class="mapFrame" id="mapFrame" name="mapFrame" src="'+url+'" allowtransparency="true" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>').appendTo($(".iframeBox")).siblings(".mapFrame").addClass("hide");
    			//getSameOpentabindexTabJquery(obj).trigger("click");
    		}
        }
	}*/
	openModuleTab(url, mkdm, mkmc, qxdm, topMkdm);
}
//三级菜单点击事件
function menu3Click(obj, url, mkdm, mkmc, qxdm, topMkdm){
	$(obj).addClass("selected").siblings("li").removeClass("selected").parents(".item").siblings(".item").find("li").removeClass("selected");
	openModuleTab(url, mkdm, mkmc, qxdm, topMkdm);
}

/**
 * 打开功能菜单
 */
var openModuleTab = function (url, mkdm, mkmc, qxdm, topMkdm) {
	if (document.getElementById("tab" + mkdm)) {
		$("#tab" + mkdm).trigger("click");
	} else {
        if(strlist.indexOf(qxdm)!=-1){
            openFullWindow("navigate.do?act=detail&qxdm="+qxdm,qxdm);
        }else{return ;
        	//专为‘车辆限行’和'事故黑点'打开窗口，过滤其他一级菜单
        	if (isStrNotNull(url) && ("#" != url || ("#" == url && (qxdm=='${pris_enum.MENU_VLM_LIMIT}' || qxdm=='R01')))) {
    			//iframe上方插入table
        		url = "navigate.do?act=detail&qxdm=" + qxdm;
        		var newTab = $("<li id='tab" + mkdm + "' onclick='iframeTabClick(this,\"" + topMkdm + "\")'><span class='tabName'>"+mkmc+"</span><span class='tabClose' onclick='removeIframe(this)'></span></li>");
        		newTab.addClass("selected").appendTo($(".tabIframeChildBox ul.tabUl")).siblings("li").removeClass("selected");
    			$('<iframe class="mapFrame" id="frame' + mkdm + '" name="mapFrame" src="'+url+'" allowtransparency="true" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>').appendTo($(".iframeTarget")).siblings(".mapFrame").addClass("downZindex");
    			//getSameOpentabindexTabJquery(obj).trigger("click");
    			//放置tab的ul宽度自增
    			$(".removeAll").css({"right":0}).removeClass("hide");//显示移除全部按钮
    			//var newTabWidth=newTab.outerWidth();//alert($("#tab" + mkdm).outerWidth());
    			var newTabWidth=String(mkmc).length*12+37;
    			tabUlAutoAddChangeWidth(newTabWidth);
    		}
        }
	}
}
