<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<%String zid = request.getAttribute("topicid").toString(); System.out.println(zid+":1111111111111111111111111111");%>
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
<!-- 		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;"> -->
		    	  
<!-- 		          <label >调查问卷题目类型：</label> -->
<!--                   <input name="typeCode" class="mini-combobox" valueField="code" textField="text"  showNullItem="true" -->
<%--                    url="<%=request.getContextPath() %>/common/getDictType.do" --%>
<!--                    required="false"  -->
<!--                   /> -->
	
<!-- 		           <a class="mini-button" style="width:60px;" onclick="search()">查询</a> -->
<!-- 		           <a class="mini-button" style="width:60px;" onclick="cz()">重置</a> -->
<!-- 		           <a class="mini-button" iconCls="icon-excel" onclick="expExcel()">导出Excel</a> -->
<!-- 		    </div> -->
	        
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
		      url="<%=request.getContextPath() %>/dcwj/dcwjsub/getList.do?topicid=<%=zid %>" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20"
		    >
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="ztitle" width="360" headerAlign="center" align="center" allowSort="true" >标题</div>        
		            <div field="ztype" width="80" headerAlign="center" align="center" allowSort="true" >内容描述</div> 
		            <div field="" width="200" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   

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
		var title="调查问卷题目添加";
        mini.open({
            url: HOST_PATH + "/dcwj/dcwjsub/addUi.do",
            title: title, width: '95%', height: '95%',
            loadOnRefresh: true,
            showMaxButton: true, 
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "new",topicid:'<%=zid%>'};
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
            	url: HOST_PATH + "/dcwj/dcwjsub/addUi.do",
                title: "调查问卷题目修改", width: '95%', height: '95%',
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
                url: HOST_URL+"/dcwj/dcwjsub/delete.do?id="+row.zid,
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
    	if(e.value=="0"){
    		return "<font color='red'>不禁用</font>";
    	}
    	return "禁用";
    }

    
    function addSubTopic(){
    	var row = grid.getSelected();
    	
   		var title="调查问卷主表添加";
   	  mini.open({
          url: HOST_PATH + "/dcwj/dcwjitem/listUi.do?subtopicid="+row.zid+"&topicid=<%=zid%>",
          title: title, width: 700, height: 630,
//           loadOnRefresh: true,
//           showMaxButton: true, 
//           onload: function () {
//               var iframe = this.getIFrameEl();
<%--               var data = { action: "new",topicid:'<%=zid%>',subtopicid:row.zid}; --%>
//               iframe.contentWindow.SetData(data);
//           },
//           ondestroy: function (action) {
//               grid.reload();
//           }
      });
//            mini.open({
//                url: HOST_PATH + "/dcwj/dcwjitem/addUi.do",
//                title: title, width: 500, height: 430,
//                loadOnRefresh: true,
//                showMaxButton: true, 
//                onload: function () {
//                    var iframe = this.getIFrameEl();
<%--                    var data = { action: "new",topicid:'<%=zid%>',subtopicid:row.zid}; --%>
//                    iframe.contentWindow.SetData(data);
//                },
//                ondestroy: function (action) {
//                    grid.reload();
//                }
//            });
    	
//         var row = grid.getSelected();
// //      alert(row.zid);
// //          return;
//          $.ajax({
<%--              url: HOST_URL+"/dcwj/dcwjitem/addUi.do?topicid=<%=zid%>&subtopicid="+row.zid, --%>
//  			type: 'post',
//  		    dataType: 'json',
//  		    contentType:'application/json;charset=UTF-8',
//              cache: false,
//              success: function (text) {
                 
//                  var success = text.success;
//                  if(success){
//                  	alert("删除成功！");
//                  	grid.reload(); 
//                  }else{
//                  	alert("删除失败！");
//                  }
//              },
//              error: function (jqXHR, textStatus, errorThrown) {
//                  alert(jqXHR.responseText);
//                  CloseWindow();
//              }
//          });
         
         
    }
    
    function SetData(data) {
    	czlx = data.action;
        if (data.action == "view") {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);

            $.ajax({
            	url: HOST_URL+"/gggl/xtgg/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    form.setChanged(false);

                    mini.getbyName("ok").setEnabled(false);

                }
            });
        }else if (data.action == "edit") {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);
			
            $.ajax({
            	url: HOST_URL+"/dcwj/dcwjzb/getById.do?id="+data.id,
                cache: false,
                success: function (text) {
                    var o = mini.decode(text);
                    form.setData(o);
                    form.setChanged(false);
                    
                }
            });
        }else if(data.action == "new"){
        	grid.load();
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	 	var tree = mini.get("leftTree");
	 	
        var grid = mini.get("datagrid1");
        grid.load();

	
        function expExcel(){
        	var flac = "字典信息";

        	ExportExcel(grid,flac);
        }


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>