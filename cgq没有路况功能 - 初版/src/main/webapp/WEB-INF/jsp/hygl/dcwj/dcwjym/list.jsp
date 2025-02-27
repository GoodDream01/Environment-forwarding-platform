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
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		    	  
		          <label >调查问卷标题：</label>
                  <input name="zdcwjmc" class="mini-textbox" />
	
		           <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		           <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
<!-- 		           <a class="mini-button" iconCls="icon-excel" onclick="expExcel()">导出Excel</a> -->
		    </div>
	        
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">增加</a>
<!-- 	                        <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true" tooltip="编辑...">编辑</a> -->
<!-- 	                        <a class="mini-button" iconCls="icon-remove" onclick="delone()" plain="true">删除</a>          -->
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
	
		    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath() %>/dcwj/dcwjzb/getList.do?zdisabled=1" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20"
		    >
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="ztitle" width="360" headerAlign="center" align="center" allowSort="true" >标题</div>        
		            <div field="znrms" width="80" headerAlign="center" align="center" allowSort="true" >内容描述</div> 
		            <div field="zkssj" width="150" headerAlign="center" align="center" allowSort="true" renderer="onGxsjRenderer">开始时间</div> 
		            <div field="zjssj" width="150" headerAlign="center" align="center" allowSort="true" renderer="onGxsjRenderer">结束时间</div> 
<!-- 		            <div field="zdisabled" width="80" headerAlign="center" align="center" allowSort="true" renderer="onStatusRenderer">状态</div> -->
<!-- 		            <div field="zsort" width="50" headerAlign="center" align="center" allowSort="true" >排序</div> -->
<!-- 		            <div field="bz" width="150" headerAlign="center" align="center" allowSort="true" >备注</div>  -->
		            <div field="" width="200" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

    function search() {
    	var o={};
        var zdcwjmc = mini.getByName("zdcwjmc").getValue();
        o.zdcwjmc = zdcwjmc;
        
        grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    
    function cz(){
		location.reload();
	}
    
    function onNodeSelect(e){
    	var node = e.node;
    	var lsjg = node.id;
    	var jgsbh = node.otherParam.jgsbh;
    	mini.get("lsjg").setValue(lsjg);
    	mini.get("jgsbh").setValue(jgsbh);
    	
    	search();
    }
    
	function add() {
		var title="调查问卷主表添加";
        mini.open({
            url: HOST_PATH + "/dcwj/dcwjzb/addUi.do",
            title: title, width: 500, height: 430,
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
            	url: HOST_PATH + "/dcwj/dcwjzb/addUi.do",
                title: "调查问卷主表修改", width: 500, height: 260,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.zid };
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
//         alert(row.zid);
        if (row) {//alert(row.ZID+":"+row.ZFID);
//             return;
            $.ajax({
                url: HOST_URL+"/dcwj/dcwjzb/delete.do?id="+row.zid,
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
    
    function tp(){
    	 var row = grid.getSelected();
         if (row) {//alert(row.ZID+":"+row.ZFID);
             mini.open({
             	url: HOST_PATH + "/dcwj/dcwjym/showUi.do?id="+row.zid ,
                 title: "调查问卷", width: '95%', height: '95%',
                 loadOnRefresh: true,
                 onload: function () {
                     var iframe = this.getIFrameEl();
                     var data = { action: "edit", id: row.zid };
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

   
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"tp()\">投票</a>";
        return html;
    }
    
    function onGxsjRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }

    var types = <%=DictUtils.TypeToKeyValue()%>;
    function onTypeCodeRenderer(e){
    	return types[e.value];
    }
    
    function onStatusRenderer(e){
    	if(e.value=="0"){
    		return "<font color='red'>禁用</font>";
    	}
    	return "不禁用";
    }

    
    function addSubTopic(){
        var row = grid.getSelected();
        var title = "添加调查问卷题目";
        
        mini.open({
            url: HOST_URL+"/dcwj/dcwjsub/listUi.do?topicid="+row.zid,
            title: title, width: '95%', height: '95%',
//             loadOnRefresh: true,
//             showMaxButton: true, 
//             onload: function () {
//                 var iframe = this.getIFrameEl();
//                 var data = { action: "new"};
//                 iframe.contentWindow.SetData(data);
//             },
//             ondestroy: function (action) {
//                 grid.reload();
//             }
        });
        
    }

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	 	var tree = mini.get("leftTree");
	 	
        var grid = mini.get("datagrid1");
        grid.load();   
	
        function expExcel(){
        	var flac = "调查问卷信息";

        	ExportExcel(grid,flac);
        }


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>