// 区分当前是否为调试阶段(调试阶段使用假数据)
var g_hbHomeIs_isDEV = true;
//模块tab点击时间
// function moduleTabClick(obj){
// 	$(obj).addClass("selected").siblings(".tab").removeClass("selected");
// 	var index0=$(obj).index();
// 	$(obj).parent().siblings(".tabContent").removeClass("tabContent1").removeClass("tabContent2").removeClass("tabContent3").addClass("tabContent"+(index0+1)).find(".box").each(function(index){
// 		if(index==index0)
// 		{
// 			$(this).removeClass("hide").siblings(".box").addClass("hide");
// 		}
// 	});
// }
//模块tab点击时间
function moduleTabClick(obj){
    $(obj).addClass("selected").siblings(".tab").removeClass("selected");
    var index0=$(obj).index();
    $(obj).parent().siblings(".tabContent").removeClass("tabContent1").removeClass("tabContent2").removeClass("tabContent3").addClass("tabContent"+(index0+1)).find(".box").each(function(index){
        if(index==index0)
        {
            $(this).addClass("zIndexTop").siblings(".box").removeClass("zIndexTop");
        }
    });
}
function beforeDataCreateSpan(className,colorArr){
	$("."+className).find(".value").each(function(index){
		$(this).nextAll(".bdcs").remove();
		// var value=String(parseInt($(this).val()));
		var value=String(parseInt($(this).val())).split("").reverse().join("");
		for(var i=0,len=value.length;i<len;i++)
		{
			$("<span class='bdcs "+colorArr[index]+"'>"+value[i]+"</span>").insertAfter($(this));
		}
	});
}

//子集tab切换
function childTabSiblingsTabFn(obj){
	var index=$(obj).index();
	$(obj).addClass("selected").siblings().removeClass("selected").parents(".tabBox_btnBox").siblings(".childTabSiblingsTabContent").find(".sContent").eq(index).addClass("zIndexTop").siblings().removeClass("zIndexTop");

}