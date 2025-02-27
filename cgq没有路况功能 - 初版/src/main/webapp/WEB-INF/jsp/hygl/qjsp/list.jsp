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
		    	  
		          <%-- <label >字典类型：</label>
                  <input name="typeCode" class="mini-combobox" valueField="code" textField="text"  showNullItem="true"
                   url="<%=request.getContextPath() %>/common/getDictType.do"
                   required="false" 
                  />
	
		           <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
		           <a class="mini-button" style="width:60px;" onclick="cz()">重置</a>
		           <a class="mini-button" iconCls="icon-excel" onclick="expExcel()">导出Excel</a> --%>
		    </div>
	        
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                       <!--  <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">请假申请</a> -->
	                        <!-- <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true" tooltip="编辑...">审核</a> -->
	                        <a class="mini-button" iconCls="icon-remove" onclick="delone()" plain="true">删除</a>         
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
	
		    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath()%>/qjgl/qj/getList1.do" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20"
		    >
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="zryxm" width="80" headerAlign="center" align="center" allowSort="true" >姓名</div>
		            <div field="zrylx" width="80" headerAlign="center" align="center" allowSort="true"renderer="onStatusRenderer1" >人员类型</div>   
		            <div field="zqjyy" width="80" headerAlign="center" align="center" allowSort="true"  renderer="onTypeRenderer">请假原因</div>  
		              <div field="zqjnr" width="80" headerAlign="center" align="center" allowSort="true" >请假内容</div>    
		            <div field="zkssj" width="150" headerAlign="center" align="center" allowSort="true" renderer="onGxsjRenderer">开始时间</div> 
		            <div field="zjssj" width="150" headerAlign="center" align="center" allowSort="true" renderer="onGxsjRenderer">结束时间</div> 
		            <div field="zshzt" width="80" headerAlign="center" align="center" allowSort="true" renderer="onStatusRenderer" >审批状态</div>
		            <div field="zshyy" width="80" headerAlign="center" align="center" allowSort="true" >审批原因</div>
		             <div field="" width="100" headerAlign="center" align="center" allowSort="true"     renderer="onCzRenderer">操作</div>
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
    var yhzts = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_HY_QJ_QJZT.toString())%>;
    function onTypeRenderer(e){

    	return yhzts[e.value];
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
		var title="请假申请";
        mini.open({
            url: HOST_PATH + "/qjgl/qj/addUi.do",
            title: title, width: 350, height:300,
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
            	url: HOST_PATH + "/qjgl/qj/shengPi.do",
                title: "审核", width: 350, height: 300,
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
                url: HOST_URL+"/qjgl/qj/delete.do?zid="+row.zid,
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
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"edit()\">审核</a>";
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
    		return "<font color='black'>未审批 </font>";
    	}
    	if(e.value=="1"){
    		return "<font color='green'>同意  </font>";
    	}
    	if(e.value=="2"){
    		return "<font color='red'>不同意  </font>";
    	}
    }//管理员，1操作员，3承办单位，4工作员，5，政协常委）
    function onStatusRenderer1(e){
    	
    	if(e.value=="1"){
    		return "<font color='black'>政协委员 </font>";
    	}
    	if(e.value=="2"){
    		return "<font color='black'>工作人员</font>";
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