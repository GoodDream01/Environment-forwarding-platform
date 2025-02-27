<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@page import="com.dt.zxhygl.utils.UserUtils"%>
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
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		    	  
		          <label >会场名称：</label>
                  <input id="zhcmc" name="zhcmc" class="mini-textbox" />
	
		           <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		           <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		           <a class="mini-button" iconCls="icon-excel" onclick="expExcel()">导出Excel</a>
		    </div>
	        
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">添加</a>
	                        <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true" tooltip="编辑...">编辑</a>
	                        <a class="mini-button" iconCls="icon-remove" onclick="delone()" plain="true">删除</a>         
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
	
		    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath()%>/hcgl/hcgl/getList.do" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="zhcmc" width="120" headerAlign="center" align="center" allowSort="true"  >会场名称</div> 
		            <div field="zaddress" width="120" headerAlign="center" align="center" allowSort="true"  >地点</div> 
		            <div field="zqdlx" width="80" headerAlign="center" align="center" allowSort="true" renderer="onQdlxRenderer">签到类型</div>    
		            <div field="ztjsj" width="150" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" >添加时间</div> 
		            <div field="" width="150" headerAlign="center" align="center" allowSort="true"     renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

    function search() {
    	var o={};
        var zhcmc = mini.getByName("zhcmc").getValue();
        o.zhcmc = zhcmc;
        
        grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }

	function add() {
		var title="会场添加";
        mini.open({
            url: HOST_PATH + "/hcgl/hcgl/addUi.do",
            title: title, width: '65%', height: '70%',
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "new"};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }
    function edit() {
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/hcgl/hcgl/addUi.do",
                title: "会场修改", width: '65%', height: '70%',
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                   
                    var data = { action: "edit", zid: row.zid };
                    iframe.contentWindow.SetData(data);
                    
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            });
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
        
    } 
    
    function deloneDo(){
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            
            $.ajax({
                url: HOST_URL+"/hcgl/hcgl/delete.do?id="+row.zid,
    			type: 'post',
    		    dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
                    
                    var success = text.success;
                    if(success){
                    	alert("删除成功！");
                    	grid.reload(); 
                    }else{
                    	alert("删除失败！");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
            
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
    }
    
    function delone() {
		mini.confirm("是否要删除？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
		       			deloneDo();
		       		}
		});

        
    }

   
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"edit()\">编辑</a>";
        return html;
    }
    
    function onGxsjRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
    var yhzts = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_HY_QDLX.toString())%>;
    function onQdlxRenderer(e){

    	return yhzts[e.value];
    }

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	 	
    var grid = mini.get("datagrid1");
    grid.load();   

    function expExcel(){
    	var flac = "设备信息";

    	ExportExcel(grid,flac);
    }


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>