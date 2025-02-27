<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>  
<%String zhyid = request.getAttribute("id").toString(); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
<style>
       
        .mini-panel.max
        {
            position:fixed !important;
            width:100% !important;
            height:100% !important;
            left:0 !important;
            top:0 !important;
            z-index:10000;
        }
        
    </style>
</head>
<body>
<div style="">
    <table style="width:100%;table-layout:fixed;">
        <tr>
            <td style="width:50%;">
                <div id="p1" class="mini-panel" title="会议公告" style="width:100%;height:300px;"
                    buttons="" onbuttonclick="onbuttonclick"
                >

		<!-- 会议公告  列表 开始 -->
					<div class="mini-layout" style="width:100%;height:100%;">
					    <div title="center" region="center" style="border:0;">
						    <!--撑满页面-->
						    <div class="mini-fit" >
						
							    <div id="datagrid2" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="false" 
									showColumns="false" showPager="false" virtualScroll="false" onrowclick="ckHyggxq()" showHGridLines="false" showVGridLines="false"
							      url="<%=request.getContextPath() %>/hygl/wdhy/getHyggList.do?id=<%=zhyid %>" idField="id" multiSelect="true" 
								  fitColumns="false" pageSize="20"
							    >
							        <div property="columns">
							                    
							            <div field="zid" type="checkcolumn" visible="false" ></div>        
							            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
							            <div field="ztitle" width="600" headerAlign="center" align="left" allowSort="true" >公告标题</div>        
<!-- 							            <div field="hyidd" width="600" headerAlign="center" align="left" allowSort="true" visible="false" >会议ID</div>         -->
							        </div>
							    </div>
						    </div>
					    </div>
					</div>
		
		<!-- 会议公告  列表 结束 -->	
					
                </div>
            </td>
            <td style="width:50%;">
                <div id="p2" class="mini-panel" title="系统公告" style="width:100%;height:300px;"
                    buttons="" onbuttonclick="onbuttonclick"
                >
		<!-- 系统公告  列表 开始 -->
					<div class="mini-layout" style="width:100%;height:100%;">
					    <div title="center" region="center" style="border:0;">
						    <!--撑满页面-->
						    <div class="mini-fit" >
						
							    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="false" 
									showColumns="false" showPager="false" virtualScroll="false" onrowclick="ckXtggxq()" showHGridLines="false" showVGridLines="false"
							      url="<%=request.getContextPath() %>/hygl/wdhy/getXtggList.do" idField="id" multiSelect="true" 
								  fitColumns="false" pageSize="20"
							    >
							        <div property="columns">
							                    
							            <div field="zid" type="checkcolumn" visible="false" ></div>        
							            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
							            <div field="ztitle" width="600" headerAlign="center" align="left" allowSort="true" >公告标题</div>        
							        </div>
							    </div>
						    </div>
					    </div>
					</div>
		
		<!-- 系统公告  列表 结束 -->
                </div>
            </td>
        </tr>
        <tr>
            <td >
                <div title="会议文件" class="mini-panel"  style="width:100%;height:300px;"
                    buttons="" onbuttonclick="onbuttonclick"
                >
                
               <!-- 会议文件  列表 开始 -->
					<div class="mini-layout" style="width:100%;height:100%;">
					    <div title="center" region="center" style="border:0;">
						    <!--撑满页面-->
						    <div class="mini-fit" >
						
							    <div id="datagrid3" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="false" 
									showColumns="false" showPager="false" virtualScroll="false"  showHGridLines="false" showVGridLines="false"
							      url="<%=request.getContextPath() %>/hygl/wdhy/getHywjList.do?id=<%=zhyid %>" idField="id" multiSelect="true" 
								  fitColumns="false" pageSize="20"
							    >
							        <div property="columns">
							                    
							            <div field="zid" type="checkcolumn" visible="false" ></div>        
							            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
							            <div field="zwjmc" width="600" headerAlign="center" align="left" allowSort="true" renderer="onDownloadRenderer">文件名称</div> 
							            <div field="zwjurl" visible="false"  width="100" headerAlign="center" allowSort="false" visible="false">文件URL</div>       
							        </div>
							    </div>
						    </div>
					    </div>
					</div>
		
		<!-- 会议文件  列表 结束 -->
                
                </div>
            </td>
            <td >
                <div title="共享资料" class="mini-panel" style="width:100%;height:300px;"
                    buttons="" onbuttonclick="onbuttonclick"
                >
                
                 <!-- 共享资料  列表 开始 -->
					<div class="mini-layout" style="width:100%;height:100%;">
					    <div title="center" region="center" style="border:0;">
						    <!--撑满页面-->
						    <div class="mini-fit" >
						
							    <div id="datagrid4" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="false" 
									showColumns="false" showPager="false" virtualScroll="false"  showHGridLines="false" showVGridLines="false"
							      url="<%=request.getContextPath() %>/hygl/wdhy/getHygxzlList.do?id=<%=zhyid %>" idField="id" multiSelect="true" 
								  fitColumns="false" pageSize="20"
							    >
							        <div property="columns">
							                    
							            <div field="zid" type="checkcolumn" visible="false" ></div>        
							            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
							            <div field="zwjmc" width="600" headerAlign="center" align="left" allowSort="true" renderer="onDownloadRenderer">文件名称</div> 
							            <div field="zwjurl" visible="false"  width="100" headerAlign="center" allowSort="false" visible="false">文件URL</div>       
							        </div>
							    </div>
						    </div>
					    </div>
					</div>
		
		<!-- 共享资料  列表 结束 -->
                
                </div>
            </td>
 
        </tr>
    </table>

</div>   
    

    <script type="text/javascript">
    
//     function SetParam(data){
//     	data = mini.clone(data);
//     	mini.get("hyidd").setValue(data.zid);
//     }
    
    function ckXtggxq(){
    	var grid = mini.get("datagrid1");
    	 var row = grid.getSelected();
//     	 var id = mini.get("hyidd").getValue();
    	 
    	 if(row){
    		 mini.open({
             	url: HOST_PATH + "/hygl/wdhy/xtggAddUi.do",
                 title: "系统公告查看", width: 500, height: 260,
                 loadOnRefresh: true,
                 onload: function () {
                     var iframe = this.getIFrameEl();
                     var data = { action: "view", id: row.zid };
                     iframe.contentWindow.SetData(data);
                     
                 },
                 ondestroy: function (action) {
//                      grid.reload(); 
                 }
             });
    	 }
    	
    }
    
    function ckHyggxq(){
    	var grid = mini.get("datagrid2");
    	 var row = grid.getSelected();
    	 if(row){
    		 mini.open({
             	url: HOST_PATH + "/hygl/wdhy/hyggAddUi.do",
                 title: "会议公告查看", width: 500, height: 260,
                 loadOnRefresh: true,
                 onload: function () {
                     var iframe = this.getIFrameEl();
                     var data = { action: "view", id: row.zid };
                     iframe.contentWindow.SetData(data);
                     
                 },
                 ondestroy: function (action) {
//                      grid.reload(); 
                 }
             });
    	 }
    	
    }
    
    function onDownloadRenderer(e){
    	var fileUrl = e.record.zwjurl;
    	var fileName = e.record.zwjmc;
    	var html = "";
    	    html+= "<span  onclick=\"DownloadFile('"+fileUrl+"','"+fileName+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\">"+fileName+"</span>";
    	return html;
    };
    
   
    function getXtgg(){
    	var title="系统公告";
        mini.open({
            url: HOST_PATH + "/hygl/wdhy/xtgg/listUi.do",
            title: title, width: '95%', height: '95%',
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
//                 var iframe = this.getIFrameEl();
//                 var data = { action: "new"};
//                 iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
//                 grid.reload();
            }
        });
    }
    
    function getHygg(){
    	var title="会议公告";
        mini.open({
            url: HOST_PATH + "/hygl/wdhy/hygg/listUi.do",
            title: title, width: '95%', height: '95%',
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
//                 var iframe = this.getIFrameEl();
//                 var data = { action: "new"};
//                 iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
//                 grid.reload();
            }
        });
    }

    function search() {
    	var o={};
        var typeCode = mini.getByName("typeCode").getValue();
        o.typeCode = typeCode;
        
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
		var title="系统公告添加";
        mini.open({
            url: HOST_PATH + "/gggl/xtgg/addUi.do",
            title: title, width: 500, height: 260,
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
            	url: HOST_PATH + "/gggl/xtgg/addUi.do",
                title: "系统公告修改", width: 500, height: 260,
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
                url: HOST_URL+"/gggl/xtgg/delete.do?id="+row.zid,
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
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delone()\">删除</a>";
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
    	if(e.value=="1"){
    		return "<font color='red'>提示</font>";
    	}
    	return "不提示";
    }


    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	 	var tree = mini.get("leftTree");
	 	
        var grid1 = mini.get("datagrid1");
        grid1.load();
        var grid2 = mini.get("datagrid2");
        grid2.load(); 
        var grid3 = mini.get("datagrid3");
        grid3.load();
        var grid4 = mini.get("datagrid4");
        grid4.load(); 
	
        function expExcel(){
        	var flac = "字典信息";

        	ExportExcel(grid,flac);
        }


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>