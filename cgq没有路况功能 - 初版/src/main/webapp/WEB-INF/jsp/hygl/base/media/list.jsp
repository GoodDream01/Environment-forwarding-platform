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
		    	  
		          <label >名称：</label>
		          <input id="zname" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
		          <label >联系人：</label>
		          <input id="zlinkman" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
		          <label >电话：</label>
		          <input id="ztel" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
	
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
		      url="<%=request.getContextPath() %>/base/media/getList.do"    idField="ZID" multiSelect="true" 
		    >
		        <div property="columns">
		            <!--<div type="indexcolumn"></div>        -->
		            <div field="zid" type="checkcolumn" ></div>        
		            <div field="zcode" width="20" headerAlign="center" align="center" allowSort="true">编号</div>    
		            <div field="zname" width="120" headerAlign="center" align="center" allowSort="true">名称</div>    
<!-- 		            <div field="ztype" width="50" headerAlign="center" align="center" allowSort="true" renderer="onLslxRenderer">隶属</div> -->
		            <div field="zlinkman" width="50" headerAlign="center" align="center" allowSort="true">联系人</div>
		            <div field="ztel" width="80" headerAlign="center" align="center" allowSort="true">电话</div>
		            <div field="zaddress" width="150" headerAlign="center" align="center" allowSort="true">地址</div>
		            <div field="" width="180" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

    function search() {
    	var o={};
        var zname = mini.get("zname").getValue();
        o.zname = zname;
        var zlinkman = mini.get("zlinkman").getValue();
        o.zlinkman = zlinkman;
        var ztel = mini.get("ztel").getValue();
        o.ztel = ztel;
        
        grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    
    function cz(){
		location.reload();
	}
    
	function add() {
		var title="单位信息维护";
        mini.open({
            url: HOST_PATH + "/base/media/addUi.do",
            title: title, width: 400, height: 400,
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
            	url: HOST_PATH + "/base/media/addUi.do",
                title: "媒体信息修改", width: 400, height: 400,
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
    function view() {
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/base/media/addUi.do",
                title: "媒体信息查看", width: 400, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "view", id: row.zid };
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
                url: HOST_URL+"/base/media/delete.do?id="+row.zid,
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
    
    function qrcode() {
        var row = grid.getSelected();
        if (row) {//
            mini.open({
            	url: HOST_PATH + "/hygl/hygl/toQrCodeUi.do?id="+row.zid,
                title: "二维码", width: 280, height: 310,
                loadOnRefresh: true,
                onload: function () {
                    
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            });
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
        
    } 
    function qdcode() {
        var row = grid.getSelected();
        if (row) {//
            mini.open({
            	url: HOST_PATH + "/hygl/hygl/toQdCodeUi.do?id="+row.zid,
            	showHeader:false,	
                title: "二维码", width: 280, height: 310,
                loadOnRefresh: true,
                onload: function () {
                    
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            }).max();
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
        
    }
    function hyqd() {
        var row = grid.getSelected();
        if (row) {//
            mini.open({
            	url: HOST_PATH + "/hygl/hygl/toHyqdUi.do?id="+row.zid,
            	showHeader:false,
                title: "二维码", width: 280, height: 310,
                loadOnRefresh: true,
                onload: function () {
                    
                },
                ondestroy: function (action) {
                    grid.reload(); 
                }
            }).max();
            
        } else {
        	mini.alert("请选中一条记录", "提示");
        }
        
    } 
   
    
    function onCzRenderer(e) {
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"view()\">查看</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"edit()\">编辑</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delone()\">删除</a>";
        return html;
    }
    
    function onGxsjRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }

    var yhzts = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_HY_HYLX.toString())%>;
    function onTypeRenderer(e){

    	return yhzts[e.value];
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	var tree = mini.get("leftTree");
	 	
	var grid = mini.get("datagrid1");
	grid.load();   
	
	function expExcel(){
		var flac = "媒体信息";
		ExportExcel(grid,flac);
	}

    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>