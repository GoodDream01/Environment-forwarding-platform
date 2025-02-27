/**
 * 关闭页面的方法
 */
function closePage(){
	layer_close();
}
/**
 * 关闭页面并刷新父页面的方法
 */
function closeAndRefresh(){
	parent.location.reload();
	layer_close();
}
/**
 * 删除确认
 */
function delConfirm(url){
	layer.confirm('是否要删除?', function(index){
		  //do something
		 delDo(url,index);
	}); 
}
/**
 * 删除操作的公共方法
 */
function delDo(url,index){
    $.ajax({  
        url: url,  
        type: "get",   
        dataType: "json", 
        complete:function () {  
        	layer.close(index);
        },  
        success: function (data)  
        {  
            if(data==1){
            	alert("删除成功！");
            	window.location.reload();
            }else{
            	alert("删除失败！");
            }
        }  
    }); 
}
/**
 * 表单提交的公共方法
 * @param form
 */
function submitForm(form){
	var params = form.serialize();
	var index = layer.load();
    $.ajax({  
        url: form.attr("action"),  
        type: form.attr("method"),  
        data: params,  
        dataType: "json",
        complete:function () {  
        	layer.close(index);
        },  
        success: function (data)  
        {  
            if(data==1){
            	alert("保存成功！");
            	closeAndRefresh();
            }else{
            	alert("保存失败！");
            }
        }  
    }); 
}
/**
 * 通过select选择改变文本框中的值
 * @param selectId
 * @param inputId
 */
function changeInputValueBySelect(selectId,inputId){
	var selObj = $("#"+selectId);
	var selOption = selObj.find("option:selected");
	var value = selOption.val();
	var text = selOption.text();
	var hiddenObj = $("#"+inputId);
	if(value==""){
		hiddenObj.val("");
		return false;
	}
	hiddenObj.val(text);
}

function myopenwin(url,w,h,p,wname){
	var per = 0.8;
	if(p&&p!=""){
		per = p;
	}
    var _width = parseInt(screen.width*per);
	var _height = parseInt(screen.height*per);
	if(w&&w!=""){
		_width = w;
	}
	if(h&&h!=""){
		_height = h;
	}
	var _left = parseInt((screen.width-_width)/2);
	var _top = parseInt((screen.height-_height)/2);;
    var _wname = "myopenwin";
    if(wname&&wname!=""){
    	_wname = wname;
    }
	var openwin = window.open(eval("'"+url+"'"),_wname,'scrollbars=yes,resizable=yes,width='+_width+',height='+_height+',left='+_left+',top='+_top+';');
    openwin.focus();
    return openwin;
}

//判断是否为IE
function isIE() { //ie?
	if (!!window.ActiveXObject || "ActiveXObject" in window)
		return true;
	else
		return false;
}