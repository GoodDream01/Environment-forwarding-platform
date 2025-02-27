<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="toolbar" onmouseover="$('#toolbarBtn').show();" onmouseout="$('#toolbarBtn').hide();">
		<div id="toolbarBtn" style="display: none;">
			<div id="closeBtn"  onclick="onCancel()">[<i class="fa fa-close"></i><span >]关闭</div>
			<div id="fullScreanBtn"  onclick="top.handleFullScreen();">[<i class="fa fa-arrows-alt"></i><span >]全屏</div>
		</div>
	</div>