<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    
	    <!-- ///////////////////////////////////////////////////////////////////////////////// -->  
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="padding:2px;">                
		        <a id="uploadBtn" class="mini-button" iconCls="icon-fujian" plain="true" onclick="openUploadWin">上传附件</a>     
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
		            <div field="type" width="50" allowSort="false" headerAlign="center" align="center">类型</div>
		            <div field="size" width="50" allowSort="false" headerAlign="center" align="center" renderer="onSizeRenderer" >大小</div>                                    
		            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelRenderer"></div>
		            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDownloadRenderer"></div>   
		            <div field="serUrl" visible="false"  width="100" headerAlign="center" allowSort="false">文件URL</div>
		            <div field="id" visible="false"  width="100" headerAlign="center" allowSort="false">ID</div>         
		        </div>
		    </div> 
	    </div>
	    <!-- ///////////////////////////////////////////////////////////////////////////////// --> 
	   
    </div>
</div>  
<script type="text/javascript">
    mini.parse();
    var grid = mini.get("datagrid1");
    function openUploadWin(){
		var title="附件上传";
        mini.open({
            url: HOST_PATH + "/common/upload/multFileUpload.do",
            title: title, width: 700, height: 500,
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
                
            },
            ondestroy: function (action) {
            	if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = mini.clone(data);    //必须
	                if (data) {
	                	var temp = new Array();
	                	for(var i=0;i<data.length;i++){
	                		var d = data[i];
	                		var o = {};
	                		
	                		o.name = d.name;
	                		o.type = d.type;
	                		o.size = FormatFileSize(d.size);
	                		o.serUrl = d.serUrl;
	                		
	                		temp.push(o);
	                		
	                	}
	                	console.log(temp);
	                	grid.addRows(temp);
	                	
	                	
	                }
            	}
            }
        });
    };
    function onDelRenderer(e){
    	var uid = e.record._uid;
    	var html = "";
    	    html+= "<span class=\"icon-delete\" onclick=\"del('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function del(row_uid){
    	var row = grid.getRowByUID(row_uid);
    	grid.removeRow(row,false);
    };
    
    function onDownloadRenderer(e){
    	var fileUrl = e.record.serUrl;
    	var fileName = e.record.name;
    	var html = "";
    	    html+= "<span class=\"icon-download\" onclick=\"downloadFile('"+fileUrl+"','"+fileName+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
    	return html;
    };
    function downloadFile(fileUrl,fileName){
    	fileName = encodeURI(encodeURI(fileName));
    	window.location.href=HOST_PATH + "/common/downloadFile.do?fileUrl="+fileUrl+"&fileName="+fileName+" ";
    }
    
</script>    

</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>