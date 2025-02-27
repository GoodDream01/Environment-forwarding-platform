<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/app/inc/common_inc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/app/inc/common_head.jsp" />
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/app/inc/common_js.jsp" />
</head>
<body>
	<header class="aui-bar aui-bar-nav fix-status-bar" id="aui-header" ><!-- style="position:fixed; top:0; left: 0;" -->
	    <a class="aui-btn aui-pull-left" onclick="javascript:history.go(-1)">
	        <span class="aui-iconfont aui-icon-left"></span>
	    </a>
	    <div class="aui-title">设置</div>
	</header>

    <div class="aui-content-padded">
        <ul class="aui-list aui-form-list">
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                    <div class="aui-list-item-title aui-font-size-14">账号：<%=USERINFO_USERNAME %></div>
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                    <div class="aui-list-item-title aui-font-size-14">姓名：<%=USERINFO_USERXM %></div>
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                    <div class="aui-list-item-title aui-font-size-14">部门：<%=USERINFO_DWMC %></div>
                </div>
            </li>
            <li class="aui-list-item">
                <div class="aui-list-item-inner">
                    <div class="aui-list-item-title aui-font-size-14 goods-title">版本信息：v1.0</div>
                </div>
            </li>
         </ul>
         <div id="btn_sub" class="aui-btn aui-btn-danger aui-btn-block aui-btn-sm aui-padded-l-15 aui-padded-r-15 aui-margin-t-15">退出当前账号登录</div>
    </div>        
    <jsp:include page="/WEB-INF/jsp/app/inc/footer.jsp">
    	<jsp:param name="bj" value="my"/>
    </jsp:include>
</body>
<script type="text/javascript">
var dialog = new auiDialog({});
var toast = new auiToast();
function loginOut(){
    dialog.alert({
        title: "提示",
        msg: "确定要退出登录？",
        buttons: ['取消','确定'],
    }, function (ret) {
        if(ret){
            if(ret.buttonIndex==2){
            	loginOut_handler();
            }
        }

    });
}
function loginOut_handler(){
	$.ajax({
        type: "get",
        dataType: "html",
        url: HOST_URL+'/login/loginout.do',
        beforeSend:function(){
       	 	toast.loading({
                title:"正在退出登录...",
                duration:2000
            });
        },
        complete: function () {
       	 	toast.hide();
        },
        success: function (data, textStatus) {
            if (data && (data == "1"||data == "loseSession")) {

                window.location.href=HOST_URL+'/app/login/loginIndex.do';
            }
            else {
                dialog.alert({
                    title: "提示！",
                    msg: "退出登录失败！",
                    buttons: ['确定'],
                }, function (ret) {


                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            dialog.alert({
                title: "错误提示",
                buttons: ['确定'],
                msg: '内部错误：'+textStatus,
            });
        }
    });
}
$(function(){
	
	$("#btn_sub").click(function(){
		loginOut();
	});
})
</script>
</html>