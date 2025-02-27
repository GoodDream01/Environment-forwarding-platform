<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String baseDomain = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wx</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
</head>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

img {
	max-width: 100%;
	height: auto;
}

.weixin-tip {
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.8);
	filter: alpha(opacity = 80);
	height: 100%;
	width: 100%;
	z-index: 100;
}

.weixin-tip p {
	text-align: center;
	margin-top: 10%;
	padding: 0 5%;
}
</style>

<body>
	<div class="weixin-tip">
		<p>
			<img src="images/tip.png" alt="在浏览器打开" />
		</p>
	</div>
</body>
<script src="js/jquery-1.11.1.min.js"></script>
<script>

	$(window).on("load", function() {
		var winHeight = $(window).height();
		function is_weixin() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		}
		var isWeixin = is_weixin();
		if (isWeixin) {
			$(".weixin-tip").css("height", winHeight);
			$(".weixin-tip").show();
		}else{
			window.location.href="<%=basePath%>/doc/SjzMzFace_v1.0.0.apk";
		}
	})
</script>

</html>
