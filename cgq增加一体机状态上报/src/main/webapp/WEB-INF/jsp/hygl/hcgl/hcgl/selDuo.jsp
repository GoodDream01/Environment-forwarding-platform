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
    <div style="width:100%;">
	    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
	          <label >名称：</label>
	          <input id="zhcmc" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
	          <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
	    </div>
    </div>
    <!--撑满页面-->
    <div class="mini-fit" >

	    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	      url="<%=request.getContextPath() %>/hcgl/hcgl/getList.do?isLimit=false"    idField="zid" multiSelect="true"
	      showPager = "false"
	    >
	        <div property="columns">
	            <!--<div type="indexcolumn"></div>        --> 
	            <div field="zid" width="50" type="checkcolumn" ></div>    
	            <div field="zhcmc" width="50" headerAlign="center" align="center" allowSort="true">会场名称</div>    
	            <div field="zaddress" width="100" headerAlign="center" align="center" allowSort="true">地点</div>
	            <div field="zqdlx" width="100" headerAlign="center" align="center" allowSort="true" renderer="onQdlxRenderer">签到类型</div>
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
    				var zhcmc = row.zhcmc;
    				selStr+=zhcmc;
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
     				var zhcmc = row.zhcmc;
     				selStr+=zhcmc;
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
        
        function SetData(data){
        	var codeMap = {};
        	var ds = data;
        	if(ds!=null && ds.length>0){
    			for(var i = 0;i<ds.length;i++){
    				var code = ds[i].zhcid;
    				codeMap[code]=code;
    			}
    		}	
         	grid.load(grid.getLoadParams(),function(){
            	var rows = grid.findRows(function(row){
            	    if(codeMap[row.zid]) {
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
            	var zhcmc = mini.get("zhcmc").getValue();
            	var a=row.zhcmc;
            	var bool=a.indexOf(zhcmc);
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
        
        var yhzts = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_HY_QDLX.toString())%>;
        function onQdlxRenderer(e){

        	return yhzts[e.value];
        }


    </script>

</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>