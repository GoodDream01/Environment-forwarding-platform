<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
<jsp:include page="/inc/common_fileupload_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    
	    <!-- ///////////////////////////////////////////////////////////////////////////////// -->  
		<div style="width:100%;">    
		    <div class="mini-toolbar" style="padding:2px;">                
		        <a id="addFileBtn" class="mini-button" iconCls="add" plain="true" >选择文件...</a>
		        <span class="separator"></span>
		        <a id="uploadBtn" class="mini-button" iconCls="up" plain="true" onclick="upload">上传</a>     
		        <span class="separator"></span>             
		        <a id="removeBtn" class="mini-button" iconCls="trash" plain="true" onclick="remove">移除所有</a>    
		        <span class="separator"></span>             
		        <a id="cancelBtn" class="mini-button" iconCls="cancel" plain="true" onclick="cancel">取消上传</a> 
		        <span class="separator"></span>             
		        <a id="okBtn" class="mini-button" iconCls="icon-ok" plain="true" onclick="onOk">确定保存附件</a>         
		    </div>
	    </div>
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
		        allowResize="false" showPager="false" idField="id" 
		        allowCellEdit="true" allowCellSelect="true"
		    >
		        <div property="columns">
		        	<div type="indexcolumn"></div>
		            <div field="name" width="120" headerAlign="center" align="center" allowSort="false">文件名</div>                
		            <div field="fileName" width="100" allowSort="false" align="center" headerAlign="center">自定义文件名
		            	<input property="editor" class="mini-textbox" style="width:100%;"/>
		            </div>            
		            <div field="type" width="50" allowSort="false" headerAlign="center" align="center">类型</div>
		            <div field="size" width="50" allowSort="false" headerAlign="center" align="center" renderer="onSizeRenderer" >大小</div>                                    
		            <div field="percent" width="120" headerAlign="center" align="center" allowSort="false" renderer="onPercentRenderer">进度</div>
		            <div field="status" width="80" headerAlign="center" align="center" allowSort="false" renderer="onStatusRenderer">状态</div>
		            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelRenderer"></div>   
		            <div field="serUrl" visible="false"  width="100" headerAlign="center" allowSort="false">状态</div>
		            <div field="id" visible="false"  width="100" headerAlign="center" allowSort="false">状态</div>         
		        </div>
		    </div> 
	    </div>
	    <!-- ///////////////////////////////////////////////////////////////////////////////// --> 
    </div>
</div>  
<script type="text/javascript">
    mini.parse();
    function onClick(e) {
    	openUpldWin();
    }
    var uploadWindow = mini.get("uploadWindow");
    function openUpldWin(){
    	uploadWindow.show();
    }
    var grid = mini.get("datagrid1");

    var swfBtn = mini.get("swfBtn");

    var uploadObj = $().SWFupload({
    	grid_id:"datagrid1",
    	addFileBtn_id:"addFileBtn",
    	uploadBtn_id:"uploadBtn",
    	removeBtn_id:"removeBtn",
    	cancelBtn_id:"cancelBtn",
    	button_width:95,
    	button_height:23,
        file_size_limit : 5,//MB  
		file_types : '*.jpg;*.jpeg;*.gif;*.png;*.rar;*.zip;*.txt;*.doc;*.xls;*.ppt;*.docx;*.xlsx;*.pptx;*.pdf;*.JPG;*.JPEG;*.GIF;*.PNG;*.RAR;*.ZIP;*.TXT;*.DOC;*.XLS;*.PPT;*.DOCX;*.XLSX;*.PPTX;*.PDF',//*.jpg;*.JPG;*.gif;*.GIF;*.png;*.PNG
		file_types_description : '文件',
        upload_url : '<%=request.getContextPath() %>/inc/upload/uploadnew_json.jsp?cz=saveup'  ,
    	flash_url : "<%=request.getContextPath() %>/static/js/swfupload/swfupload.swf",
    	flash9_url : "<%=request.getContextPath() %>/static/js/swfupload/swfupload_fp9.swf"
    });
    function onPercentRenderer(e){
		return uploadObj.onPercentRenderer(e);
    }
    function onStatusRenderer(e){
    	return uploadObj.onStatusRenderer(e);
    }
    function onSizeRenderer(e){
    	return uploadObj.onSizeRenderer(e);
    }
    function onDelRenderer(e){
    	return uploadObj.onDelRenderer(e);
    }
    //////////////////////////////////////////
    function upload(e){
    	uploadObj.onUpload();
    }
    
    function remove(e){
    	uploadObj.onRemove();
    }
    
    function cancel(e){
    	uploadObj.onCancelUpload();
    }
    
    function del(row_uid){
    	var row = grid.getRowByUID(row_uid);
		var file_id = row.id;
		uploadObj.swfupload.cancelUpload(file_id,false);
    	grid.removeRow(row,false);
    }
    /////////////////////////////////////////////////////////////////////////////////////
    function getFiles(){
    	var str = uploadObj.getFiles();
    	alert(str);
    }
    
    //////////////////////////////////
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }
    
	////////////////////////////
    function GetData(f){
    	var rows = getRows();
    	return rows;
    }
    function getRows(){
    	var rows = grid.findRows(function(row){
    	    if(row.serUrl!='') return true;
    	});
    	return rows;
    }


    
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>