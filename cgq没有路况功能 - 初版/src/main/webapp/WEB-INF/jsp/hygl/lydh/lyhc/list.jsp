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
		    	  
		          <label >会议名称：</label>
		          <input id="zhymc" class="mini-textbox" />
	
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
	                        <a class="mini-button" iconCls="icon-call" onclick="call()" plain="true" tooltip="拨打录音电话...">拨打录音电话</a>
	                        <a class="mini-button" iconCls="icon-voice" onclick="lyhc()" plain="true" tooltip="录音合成...">录音合成</a>   
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
	
		    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath() %>/hygl/lydhhc/getList.do" idField="zid" multiSelect="true" 
			  fitColumns="false" pageSize="20"
		    >
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="ztitle" width="150" headerAlign="center" align="center" allowSort="true" >标题</div>
		            <div field="hyinfo.zhymc" width="200" headerAlign="center" align="center" allowSort="true" >会议名称</div>        
		            <div field="ztext" width="320" headerAlign="center" align="center" allowSort="true" >内容</div> 
		            <div field="zlrsj" width="150" headerAlign="center" align="center" allowSort="true" renderer="onGxsjRenderer">合成时间</div>
		            <div field="" width="100" headerAlign="center" align="center" allowSort="true" renderer="onLyRenderer">录音</div>
		            <div field="" width="130" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

    function search() {
    	var o={};
        var zhymc = mini.get("zhymc").getValue();
        o.zhymc = zhymc;
        
        grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    
    function cz(){
		location.reload();
	}
    
    function lyhc() {
        mini.open({
        	url: HOST_PATH + "/hygl/lydhhc/addUi.do",
            title: "录音合成", width: '95%', height: '95%',
            loadOnRefresh: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "new" };
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
            	url: HOST_PATH + "/hygl/lydhhc/addUi.do",
                title: "修改录音电话", width: '95%', height: '95%',
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
    
    function call() {
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/hygl/lydhcall/addUi.do",
                title: "拨打录音电话", width: '95%', height: '95%',
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.zid, zhyid: row.hyinfo.zhyid, zhymc: row.hyinfo.zhymc };
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
    
    function play() {
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/hygl/lydhhc/play.do?id="+row.zid,
                title: "播放录音电话", width: 400, height: 100,
                loadOnRefresh: true,
                onload: function () {
                    
                },
                ondestroy: function (action) {

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
                url: HOST_URL+"/hygl/lydhhc/delete.do?id="+row.zid,
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
    
    
   
    function onLyRenderer(e) {
    	var html = "";
    	html+= "<span class=\"icon-play\" onclick=\"play()\" style=\"display:inline-block;cursor: pointer;width:60px;height:16px;\">播放</span>";
        return html;
    }
    
    function onCzRenderer(e) {
    	var html = "";
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
		var flac = "录音电话信息";
		ExportExcel(grid,flac);
	}

    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>