var currentFirstMenuIndex=0;//当前处于菜单第一位置的菜单在menuListsJSON中的索引位置(用于标记菜单重排时放在第一位的菜单)
var beforeFirstMenuIndex=0;//标记上次处于菜单第一位置的菜单在menuListsJSON中的索引位置(用于判断是否处于菜单的两个极端位置)
var currentCanShowMenuNumber=0;//当前可见区域能显示的子菜单的个数
var menuMoveSpeed=100;//定义全局的子菜单运动速度
var childMenuW=0;//子菜单宽度
var menuListsLen=0;//菜单总个数
var markSelectedMenuIndex=0;//标记当前页面被选中的一级菜单的索引值
var winW,winH;//当前页面宽度和高度
var tabIframeChildBoxUlWidth=0;//iframeTab的父级容器宽度随加入的tab的宽度自增
var liBoxWidth=0;//放置tab父级ul的容器在当前页面下的宽度
var marginLeftDif=0;//tabIframeChildBoxUlWidth与liBoxWidth两个变量的差值
var tabLeftRightClickMarginLeft=0;//左右切换按钮下当前ul的margin-left值
//首页框架自适应函数
function indexFrameAdapt(){
		winW=$(window).width();
		winH=$(window).height();
	
		//头部菜单可用宽度
	var systemNameW=$(".header .systemName").width();
	var headerMenuToggleW=$(".headerMenuToggle").width();
	var userW=$(".header .user").width();
	var headerMenuW=winW-systemNameW-userW-headerMenuToggleW-200-15;//菜单容器宽度
		$(".headerMenu").width(headerMenuW);
		
		/*根据当前窗口大小改变显示一级菜单的数量*/
		childMenuW=$(".header .childMenu").width()||68;//单个菜单宽度(设置一个默认值68,因为实际中html部分不存在，所以宽度获取不到)
		currentCanShowMenuNumber=parseInt(headerMenuW/childMenuW);//当前窗口大小下可以显示的菜单数量
		menuShowEffect(currentFirstMenuIndex);//页面初始化显示一级子菜单
		
		/*一级菜单切换结束*/
		
		/*****页面大小改变的时候左右切换tab位置的四个相关变量值开始*/
		tabIframeChildBoxUlWidth=Math.max($(".tabIframeChildBox ul.tabUl .index").outerWidth(),$(".tabIframeChildBox ul.tabUl").outerWidth());
		liBoxWidth=$(".tabIframeChildBox .liBox").outerWidth();
		marginLeftDif=tabIframeChildBoxUlWidth-liBoxWidth;
		tabLeftRightClickMarginLeft=marginLeftDif;
		/*****页面大小改变的时候左右切换tab位置的四个相关变量值结束*/
		
		//弹出换肤按钮live
		$(".header .userName").live("click",function(event){
			if(event.stopPropagation){
				event.stopPropagation();
			}
			else
			{
				window.event.cancelBubble=true;
			}
			//点击用户名称触发上面userOperate点击事件
			userOperateToggle(event);
		});//live
		$(document).live("click",function(){
			$(".popGrid2").hide();
		});
		//框架部分宽度初始化
		movePoint(0);
}
//iframeTab的父级容器宽度随加入的tab的宽度自增
function tabUlAutoAddChangeWidth(newAddTabWidth){
		liBoxWidth=$(".tabIframeChildBox .liBox").outerWidth();
		tabIframeChildBoxUlWidth=tabIframeChildBoxUlWidth+newAddTabWidth;
		tabIframeChildBoxUlWidth=Math.max($(".tabIframeChildBox ul.tabUl .index").outerWidth(),tabIframeChildBoxUlWidth);
		$(".tabIframeChildBox ul.tabUl").width(tabIframeChildBoxUlWidth+1);//console.log("tabIframeChildBoxUlWidth:"+tabIframeChildBoxUlWidth);
		marginLeftDif=tabIframeChildBoxUlWidth-liBoxWidth;
		if(marginLeftDif>0)
		{
			$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":-marginLeftDif+'px'},500);
			showTabMoveAndRemoveAllBtns();
		}
}

//iframeTab的父级容器宽度随加入的tab的宽度自减
function tabUlAutoReduceChangeWidth(removeTabWidth){
		liBoxWidth=$(".tabIframeChildBox .liBox").outerWidth();
		tabIframeChildBoxUlWidth=tabIframeChildBoxUlWidth-removeTabWidth;
		tabIframeChildBoxUlWidth=Math.max($(".tabIframeChildBox ul.tabUl .index").outerWidth(),tabIframeChildBoxUlWidth);
		//console.log("tabIframeChildBoxUlWidth:"+tabIframeChildBoxUlWidth);
		$(".tabIframeChildBox ul.tabUl").width(tabIframeChildBoxUlWidth+1);
		marginLeftDif=tabIframeChildBoxUlWidth-liBoxWidth;
		if(marginLeftDif>=0)
		{
			$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":-marginLeftDif+'px'},500);
			showTabMoveAndRemoveAllBtns();
		}
		else{
			$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":0+'px'},500);
			hideTabMoveAndRemoveAllBtns();
		}
}
	//movePoint位置
	function movePoint(targetLeftPosition){
		var movePoint=$(".menu_iframe .movePoint");
		var tab_iframe=$(".menu_iframe .tab_iframe");
			movePoint.stop().animate({"left":targetLeftPosition+'px'},{
				speed:400,
				step:function(n,fx){
					tab_iframe.css({"width":(winW-n)+'px'});
				}
			});
	}
	//隐藏tab左右切换按钮和全部移除按钮
		function hideTabMoveAndRemoveAllBtns(){
				//$(".tabIframeChildBox .leftBtn").addClass("hide").siblings(".rightBtn").addClass("hide").siblings(".removeAll").addClass("hide");
				$(".tabIframeChildBox .leftBtn").addClass("hide").siblings(".rightBtn").addClass("hide");
				liBoxWidth=$(".tabIframeChildBox .liBox").css({"left":0,"right":0}).outerWidth();
				marginLeftDif=tabIframeChildBoxUlWidth-liBoxWidth;
				tabLeftRightClickMarginLeft=marginLeftDif;
				$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":0+'px'},500);
		}
		//显示tab左右切换按钮和全部移除按钮
		function showTabMoveAndRemoveAllBtns(){
			var leftBtnWidth=$(".tabIframeChildBox .leftBtn").outerWidth();
			var removeAllWidth=$(".tabIframeChildBox .removeAll").outerWidth();
				//$(".tabIframeChildBox .leftBtn").removeClass("hide").siblings(".rightBtn").removeClass("hide").siblings(".removeAll").css({"right":leftBtnWidth+'px'});
			$(".tabIframeChildBox .leftBtn").removeClass("hide").siblings(".rightBtn").removeClass("hide").siblings(".removeAll").css({"right":0+'px'});
				liBoxWidth=$(".tabIframeChildBox .liBox").css({"left":leftBtnWidth+'px',"right":(leftBtnWidth+removeAllWidth)+"px"}).width();
				marginLeftDif=tabIframeChildBoxUlWidth-liBoxWidth;
				tabLeftRightClickMarginLeft=marginLeftDif;
				$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":-marginLeftDif+'px'},500);
		}
		//tab左边切换按钮点击事件
		function tabLeftBtnClick(){
			if(marginLeftDif<tabLeftRightClickMarginLeft)
			{
				marginLeftDif=marginLeftDif+120;
				if(marginLeftDif>tabLeftRightClickMarginLeft)
				{
					marginLeftDif=tabLeftRightClickMarginLeft;
				}
			}
			$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":-marginLeftDif+'px'},500);
		}
		//tab右边切换按钮点击事件
		function tabrightBtnClick(){
			if(marginLeftDif>0)
			{
				marginLeftDif=marginLeftDif-120;
				if(marginLeftDif<0)
				{
					marginLeftDif=0;
				}
			}
			$(".tabIframeChildBox ul.tabUl").stop().animate({"margin-left":-marginLeftDif+'px'},500);
		}
		
		//全部移除按钮
		function removeAllTabAndTabIframes(){
			$(".tabIframeChildBox .tabUl li").each(function(){
				if (!$(this).hasClass("index")) {
					//放置tab的ul宽度自减
					var newTabWidth=$(this).outerWidth();
					tabUlAutoReduceChangeWidth(newTabWidth);
					$(this).remove();
					
					var id=this.id;//id为对应的iframe的模块代码id值(需要开发拼接处理，这里只是做个样式)
					var frameId = id.replace("tab","frame");
					$("#"+frameId).remove();//选择对应的iframe的模块代码id值(需要开发拼接处理，这里只是做个样式)
				} else {
					$(this).addClass("selected");
				}
			});
			$("#mapFrame").removeClass("hide");
			hideTabMoveAndRemoveAllBtns();
		}
		/*一级菜单切换开始*/
			//向上切换live
		$(".headerMenuToggle .upBtn").live("click",function(){
			if(currentFirstMenuIndex-currentCanShowMenuNumber>=0){
				currentFirstMenuIndex=currentFirstMenuIndex-currentCanShowMenuNumber;
			}
			else
			{
				currentFirstMenuIndex=0;
				if(beforeFirstMenuIndex==currentFirstMenuIndex){
					return false;
				}
			}
			menuShowEffect(currentFirstMenuIndex);
		});
			//向下切换//live
		$(".headerMenuToggle .downBtn").live("click",function(){
			if(currentFirstMenuIndex+currentCanShowMenuNumber>=menuListsLen){
				currentFirstMenuIndex=currentFirstMenuIndex;
				if(beforeFirstMenuIndex==currentFirstMenuIndex){
					return false;
				}
			}
			else
			{
				currentFirstMenuIndex=currentFirstMenuIndex+currentCanShowMenuNumber;
			}
			menuShowEffect(currentFirstMenuIndex);
		});
//菜单进入效果
function menuShowEffect(currentFirstMenuIndex){
	var menuListsJSONIndex=0;//标识js当前执行到的子菜单的索引值，默认值从0第一个开始
	var menuListsJSONIndex2=0;//同menuListsJSONIndex，辅助获取currentShowMenuNumber的一个变量
	var leftPosIndex=0;//标记当前显示的子菜单的索引值顺序，均为从0开始向右一次递增
	var currentShowMenuNumber=0;//当前页面下显示的子菜单个数
	var menuRel=$(".headerMenu .menuRel");//子菜单待插入到的jquery对象
		menuListsLen=0;//重置菜单总个数
		menuRel.empty();//每执行一次菜单样式，清空上次内容
	for(var b in menuListsJSON){
		if(menuListsJSONIndex2>=currentFirstMenuIndex&&menuListsJSONIndex2<currentFirstMenuIndex+currentCanShowMenuNumber&&$.isNumeric(b))
		{
			currentShowMenuNumber++;//计算出当前页面下显示的子菜单个数
		}
			menuListsJSONIndex2++;
	}
	for(var a in menuListsJSON){
		if ($.isNumeric(a)) {
			menuListsLen++;
		}
		if(menuListsJSONIndex>=currentFirstMenuIndex&&menuListsJSONIndex<currentFirstMenuIndex+currentCanShowMenuNumber&&$.isNumeric(a))
		{
			var createDiv=$("<div id='menu"+menuListsJSON[a].mkdm+"' class='childMenu openTabIndex "+menuListsJSON[a].icon+(menuListsJSONIndex==markSelectedMenuIndex?' selected':'')+(menuListsJSON[a].name=='首页'?' index':'')+"' onclick='menuClick(this,\""+menuListsJSON[a].url+"\",\"" + menuListsJSON[a].mkdm + "\",\""+menuListsJSON[a].qxdm+"\")'><span class='icon'></span><a class='name' href='javascript:void(0);'>"+menuListsJSON[a].name+"</a></div>");
			createDiv.appendTo(menuRel).stop().animate({"right":(currentShowMenuNumber-leftPosIndex-1)*childMenuW+'px'},menuMoveSpeed*((currentShowMenuNumber-leftPosIndex)));
			leftPosIndex++;
			if(menuListsJSONIndex==0)
			{
				//createDiv.attr({"openTabIndex":openTabIndex,"hastab":true});
			}
		}
		menuListsJSONIndex++;
	}
	beforeFirstMenuIndex=currentFirstMenuIndex;//存储本次第一位置菜单索引，用于下次点击切换按钮的时候做是否需要加载菜单的操作
	
	//单数变双数
	function toDouble(number){
		if(String(number).length==1)
		{
			return "0"+number;
		}
		else if(String(number).length==2)
		{
			return ""+number;
		}
	}
}
//用于操作弹框显示隐藏
function userOperateToggle(event){
	$(".popGrid2").stop().slideToggle();
	var event=event||window.event;//阻止冒泡,这句话必须要有
	if(event.stopPropagation)
	{
		event.stopPropagation();
	}
}
//换肤
function changeSkin(obj){
	var className=$(obj).attr("class");
	var typePos=className.indexOf("skinBtn");
	var skinType=className.substr(typePos+7,typePos+8);
	$(obj).addClass("selected").siblings(".skinChange").removeClass("selected");
	$("body").attr("class","").addClass("skin"+skinType);
    window.frames['indexFrame'].frames['baiduMapFrame'].skinType = skinType;
	window.frames['indexFrame'].frames['baiduMapFrame'].changeMapSkin(skinType,"1");
    var event=event||window.event;//阻止冒泡,这句话必须要有
			if(event.stopPropagation)
			{
				event.stopPropagation();
			}
			//window.frames['indexFrame'].frames['mapFrame'].skinObj.skinType = skinType;
			//第一层框架iframe即indexFrame的body的class名称改变
			$("body",window.frames['indexFrame'].document).removeClass("skin1").removeClass("skin2").removeClass("skin3").addClass("skin"+skinType);
			$('.module-box',window.frames['indexFrame'].document).each(function(index){
				$("body",window.frames['indexFrame'].frames[index].document).removeClass("skin1").removeClass("skin2").removeClass("skin3").addClass("skin"+skinType);
			});
			$("body",window.frames['indexFrame'].frames['baiduMapFrame'].document).removeClass("skin1").removeClass("skin2").removeClass("skin3").addClass("skin"+skinType);
}
//tab页上页面操作鼠标经过展开隐藏二级菜单
function tabHoverToggleDown(){
	$(".menu_iframe .iframeTabBox .removeAll .tabHoverToggleMenu").slideDown();
}
//隐藏
function tabHoverToggleUp(){
	$(".menu_iframe .iframeTabBox .removeAll .tabHoverToggleMenu").hide();
}


