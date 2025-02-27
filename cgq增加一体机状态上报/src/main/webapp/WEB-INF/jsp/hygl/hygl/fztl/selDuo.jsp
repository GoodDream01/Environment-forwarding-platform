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
    <div style="width:100%;">
	    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
	          <label >名称：</label>
	          <input id="zname" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
	          <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
	    </div>
    </div>
    <!--撑满页面-->
    <div class="mini-fit" >

	    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	      url="<%=request.getContextPath() %>/hygl/fztl/getHyryList.do?isLimit=false"    idField="zid" multiSelect="true"
	      showPager = "false"
	    >
	        <div property="columns">
	            <!--<div type="indexcolumn"></div>        --> 
	            <div field="zryid" width="70" headerAlign="center" align="center" visible="false" allowSort="true">编号</div> 
	            <div field="zid" width="50" type="checkcolumn" >选择</div>    
	            <div field="zryxm" width="50" headerAlign="center" align="center" allowSort="true">名称</div>    
<!-- 	            <div field="zname" width="50" headerAlign="center" align="center" allowSort="true">名称</div>     -->
<!-- 	            <div field="zsex" width="50" headerAlign="center" align="center" allowSort="true">性别</div> -->
<!-- 	            <div field="zmobiletel" width="100" headerAlign="center" align="center" allowSort="true">电话</div> -->
<!-- 	            <div field="zpost" width="100" headerAlign="center" align="center" allowSort="true">职务</div> -->
<!-- 	            <div field="zsfzh" width="100" headerAlign="center" align="center" allowSort="true">身份证号</div> -->
	        </div>
	    </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:1;">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
        	<tr>
        		<td width="50">已选择：</td>
        		<td id="ysz" align="left"></td>
        	</tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
    

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("datagrid1");
        //grid.load();

         var yszObj = $("#ysz");
       
         grid.on("select",function(e){
        	
        	var rows = grid.getSelecteds();
        	var selStr = "";
        	if(rows!=null && rows.length>0){
    			for(var i = 0;i<rows.length;i++){
    				var row = rows[i];
    				var zname = row.zryxm;
    				selStr+=zname;
    				if(i!=rows.length-1){
    					selStr+=",";
    				}
    			}
       				yszObj.html(selStr);
    		}
        	 
        });
         
         grid.on("deselect",function(e){
         	
         	var rows = grid.getSelecteds();
         	var selStr = "";
         	if(rows!=null && rows.length>0){
     			for(var i = 0;i<rows.length;i++){
     				var row = rows[i];
     				var zname = row.ryxm;
     				selStr+=zname;
     				if(i!=rows.length-1){
     					selStr+=",";
     				}
     			}
        		yszObj.html(selStr);
     		}else{
     			yszObj.html("");
     		}
         	
         	
         });
         

        function GetData() {
            var row = grid.getSelecteds();
            return row;
        }
        
        function SetData(data,zhyid){
        	var codeMap = {};
        	var ds = data;
        	if(ds!=null && ds.length>0){
    			for(var i = 0;i<ds.length;i++){
    				var code = ds[i].zryid;
    				codeMap[code]=code;
    			}
    		}
        	var para1 = {};
        	para1.zhyid = zhyid;
         	grid.load(para1,function(){
// 			grid.load(grid.getLoadParams(),function(){
            	var rows = grid.findRows(function(row){
            	    if(codeMap[row.zryid]) {
            	    	return true;
            	    }
            	});
            	grid.selects(rows,true);
        	}); 
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
        function onRowDblClick(e) {
            onOk();
        }
        
		////////////////////////查询方法/////////////////////////
        function search() {
            var rows = grid.findRows(function(row){
            	var zname = mini.get("zname").getValue();
            	var a=row.zname;
            	var bool=a.indexOf(zname);
                if(bool>=0){ 
                	return true;
                }
            });
            for(var i=0;i<rows.length;i++){
            	var row=rows[i];
            	grid.select(row,true); 
            }
           
        }
        function onKeyEnter(e) {
            search();
        }

    </script>

</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>