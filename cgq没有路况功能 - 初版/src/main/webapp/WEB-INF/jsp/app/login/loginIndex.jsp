<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
</head>
<body>
<header class="aui-bar aui-bar-nav " id="aui-header">
    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
        <span class="aui-iconfont aui-icon-left"></span>
    </a>
    <div class="aui-title">富奥通协议转发平台</div>
</header>
<p id='hintDiv'></p>
<div class="aui-content-padded">
	<form class="form1" url="<%=request.getContextPath() %>/login/login.do?sessionId=<%=session.getId() %>">
	<input type="hidden" name="type" value="2">
	<input type="hidden" name="isMobile" value="1">
    <ul class="aui-list aui-form-list">
        <li class="aui-list-item">
            <div class="aui-list-item-inner">
                <div class="aui-list-item-label-icon">
                    <i class="aui-iconfont aui-icon-my"></i>
                </div>
                <div class="aui-list-item-input">
                    <input type="text" name="userName" placeholder="用户名" datatype="*" nullmsg="用户名不能为空！">
                </div>
            </div>
        </li>
        <li class="aui-list-item">
            <div class="aui-list-item-inner">
                <div class="aui-list-item-label-icon">
                    <i class="aui-iconfont aui-icon-lock"></i>
                </div>
                <div class="aui-list-item-input">
                    <input type="password" name="password" placeholder="密码" datatype="*" nullmsg="密码不能为空！">
                </div>
            </div>
        </li>
        <li class="aui-list-item">
            <div class="aui-list-item-inner">
                <div class="aui-list-item-label">
                    <img id="img" src="<%=request.getContextPath() %>/common/getImageCode.do" alt="点击刷新" style="width:120px;height:35px;" onclick="reloadCode()"/>
                </div>
                <div class="aui-list-item-input">
                    <input type="text" name="imageCode" placeholder="验证码" datatype="*" nullmsg="验证码不能为空！">
                </div>
            </div>
        </li>
 	</ul> 
 	<div id="btn_sub" class="aui-btn aui-btn-info aui-btn-block aui-btn-sm aui-padded-l-15 aui-padded-r-15 aui-margin-t-15">登录</div>
 	</form>   
 </div>	  
 <script type="text/javascript">
 var dialog = new auiDialog({});
 var toast = new auiToast();
 $(function () {
     $(".form1").Validform({
         btnSubmit: "#btn_sub",//点击此id按钮时触发
         tipSweep: true,//true是提交表单才验证，离开焦点不验证
         tiptype: function (msg, o, cssctl) {

             //是错误的才提示
             if (o.type == 3) {
                 Hint(msg);
             }

         },
         beforeSubmit: function (form) {
             //在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
             //这里明确return false的话表单将不会提交;
             $.ajax({
                 type: "Post",
                 data: form.serialize(),
                 dataType: "json",
                 url: form.attr("url"),
                 beforeSend:function(){
                	 toast.loading({
                         title:"登录中...",
                         duration:2000
                     });
                 },
                 complete: function () {
                	 toast.hide();
                 },
                 success: function (data, textStatus) {
                     if (data.status == 1) {
                         toast.success({
                             title: data.msg,
                             duration: 2000
                         });
                         window.location.href=HOST_URL+'/app/main/index.do';
                     }
                     else {
                    	 window.location.href=HOST_URL+'/app/main/index.do';
                         /* dialog.alert({
                             title: "错误提示",
                             msg: data.msg,
                             buttons: ['确定'],
                         }, function (ret) {


                         }); */
                     }
                 },
                 error: function (XMLHttpRequest, textStatus, errorThrown) {
                	 /**
                     dialog.alert({
                         title: "错误提示",
                         buttons: ['确定'],
                         msg: '状态：' + textStatus + '错误：' + errorThrown,
                     }, function (ret) {


                     });
                     dialog.alert({
                         title: "错误提示",
                         buttons: ['确定'],
                         msg: '内部错误：'+textStatus,
                     });*/
                 }
             });
             return false;
         }



     })
 })

 //提示
 function Hint(msg) {
     var hintStr = ' <div class="aui-tips aui-text-warning" >';
      hintStr += '<i class="aui-iconfont aui-icon-info"></i>';
    hintStr += '<div class="aui-tips-title ">'+msg+'</div>';
    hintStr += ' <i class="aui-iconfont aui-icon-close"  onclick="clearHint()"></i>';
     hintStr += '</div>';
     $("#hintDiv").html(hintStr);
     setTimeout(function () {
         $("#hintDiv").html('');
     }, 3000);
 }
 //清除提示
 function clearHint() { $("#hintDiv").html(""); }
 
 function reloadCode(){
		$('#img').attr('src','<%=request.getContextPath()%>/common/getImageCode.do?&_rand='+Math.random());
 }
 </script> 
</body>
</html>