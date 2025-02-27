var currentFirstMenuIndex=0;//当前处于菜单第一位置的菜单在menuListsJSON中的索引位置(用于标记菜单重排时放在第一位的菜单)
var beforeFirstMenuIndex=0;//标记上次处于菜单第一位置的菜单在menuListsJSON中的索引位置(用于判断是否处于菜单的两个极端位置)
var currentCanShowMenuNumber=0;//当前可见区域能显示的子菜单的个数
var menuMoveSpeed=100;//定义全局的子菜单运动速度
var childMenuW=0;//子菜单宽度
var menuListsLen=0;//菜单总个数
var markSelectedMenuIndex=0;//标记当前页面被选中的一级菜单的索引值
var winW,winH;//当前页面宽度和高度
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
		
		//弹出换肤按钮
		$(".header .userName").on("click",function(event){
			if(event.stopPropagation){
				event.stopPropagation();
			}
			else
			{
				window.event.cancelBubble=true;
			}
			//点击用户名称触发上面userOperate点击事件
			userOperateToggle(event);
		});
		$(document).on("click",function(){
			$(".popGrid2").hide();
		});
		//地图上面左右浮动部分开始
		var headerH=$(".header").height();
		var footerH=$(".footerModule").height();
		$(".mapFloatPart .floatPart").height(winH-headerH-footerH+3);
}
		/*一级菜单切换开始*/
			//向上切换
		$(".headerMenuToggle .upBtn").on("click",function(){
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
			//向下切换
		$(".headerMenuToggle .downBtn").on("click",function(){
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
//一级菜单进入效果
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
//用于操作换肤等内容的弹框显示隐藏
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
		var event=event||window.event;//阻止冒泡,这句话必须要有
			if(event.stopPropagation)
			{
				event.stopPropagation();
			}
}
//河北地图底部工具栏显示隐藏切换
function hbToolBarToggle(obj){
	var speed = 300;
	var isShow = $(obj).toggleClass("expand").hasClass("expand");
	var toolBarBox = $(obj).parent().siblings(".toolBarBox");
	var toolBar = toolBarBox.find(".toolBar");
	var toolBarWidth = toolBar.outerWidth();
	var toolBarBoxWidth = 179;
	if(!isShow)
	{
		toolBarBox.stop().animate({"width":toolBarBoxWidth},10,function(){
			toolBar.stop().animate({"right":0+'px'},speed,function(){
				toolBarBox.removeClass("hidden");
			});
		});
	}
	else
	{
		toolBarBox.addClass("hidden");
		toolBar.stop().animate({"right":-toolBarWidth+'px'},speed,function(){
			toolBarBox.width(0).removeClass("hide");//添加hide是为了初始时候由显示改为隐藏
		});
	}
}

//隐藏左右面板
function toggleLRBoard(obj){
    var $obj = $(obj);
    if ($obj.hasClass('selected')) {
        // 展示两侧
        $obj.parents('.verticalAssistRightFloatPartBox').animate({'right': '314px'}, 500);
    } else {
        // 收起两侧
        $obj.parents('.verticalAssistRightFloatPartBox').animate({'right': 0}, 500);
    }
    $obj.toggleClass("selected");
    $(".ctrl-bar",window.parent.document).trigger("click");
}
//隐藏首页头部管理员二级菜单
function hideIndexHeaderAdminMenu2(obj){
	$(document).bind("click",function(){
		var isHasAdminMenu3 = $(".header .popGrid2",window.parent.parent.document).length;
		var isHasAdminMenu2 = $(".header .popGrid2",window.parent.document).length;
		var isHasAdminMenu1 = $(".header .popGrid2",window.document).length;
		if(isHasAdminMenu3)
		{
			$(".header .popGrid2",window.parent.parent.document).hide();
		}
		if(isHasAdminMenu2)
		{
			$(".header .popGrid2",window.parent.document).hide();
		}
		if(isHasAdminMenu1 && $(obj).parents(".popGrid2").length)
		{
			event.stopPropagation();
			$(".header .popGrid2",window.document).hide();
		}
	});
}
hideIndexHeaderAdminMenu2();
