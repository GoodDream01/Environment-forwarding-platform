<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.utils.DictUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<jsp:include page="/inc/common_js_include.jsp" />
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    <div style="width:100%;">
		    <div class="mini-toolbar" style="text-align:center;" borderStyle="border:1;border-bottom:0;">
		       <label>预案等级：</label>
               <input name="yadj" class="mini-combobox" valueField="value" textField="text" required="true"
                      url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=100000"/>
               <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
               <a class="mini-button" style="width:100px;" onclick="qdya()">启动预案</a>     
		    </div>
	    </div>
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/pmkz/yagl/getList.do" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div field="id" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="ip" width="150" headerAlign="center" align="center">ip</div>
		            <div field="bzwz" width="150" headerAlign="center" align="center">布站位置</div>
		            <div field="yamc" width="150" headerAlign="center" align="center">预案名称</div>        
		            <div field="tlsj" width="150" headerAlign="center" align="center">停留时间</div> 
		            <div field="czfs" width="150" headerAlign="center" align="center" renderer="onCzfsRenderer">出字方式</div> 
		            <div field="txsd" width="120" headerAlign="center" align="center">特效速度</div> 
		            <div field="ztys" width="120" headerAlign="center" align="center" renderer="onZtysRenderer">字体颜色</div> 
		            <div field="ztkg" width="120" headerAlign="center" align="center" renderer="onZtkgRenderer">字体宽高</div> 
		            <div field="wznr" width="200" headerAlign="center" align="center">文字内容</div> 
		            <div field="" width="200" headerAlign="center" align="center" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
<script type="text/javascript">

	mini.parse();
	var grid = mini.get("datagrid1");
	grid.load(); 
    
    function search() {
       var o = {};
       o.yadj = mini.getByName("yadj").getValue();
       grid.load(o);
    }
    
    function qdya(){
    	var yadj = mini.getByName("yadj").getValue();
    	$.ajax({
            url: HOST_URL+"/pmkz/yagl/qdya.do?yadj="+yadj,
			type: 'post',
		    dataType: 'json',
		    contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (text) {
                var success = text.success;
                if(success){
                	alert("启动成功！");
                }else{
                	alert("启动失败！");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    function cz(){
		location.reload();
	}
    
    var Czfss = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_PMKZ_CZFS.toString())%>;
    function onCzfsRenderer(e){
    	return Czfss[e.value];
    }
    
    var Ztyss = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_PMKZ_ZTYS.toString())%>;
    function onZtysRenderer(e){
    	return Ztyss[e.value];
    }
    
    var Ztkgs = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_PMKZ_ZTKG.toString())%>;
    function onZtkgRenderer(e){
    	return Ztkgs[e.value];
    }
    
    function edit() {    	
        var row = grid.getSelected();            
        if (row) {
            mini.open({
            	url: HOST_PATH + "/pmkz/pm/yaUi.do",
                title: "修改", width: 500, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.id };
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
        if (row) {
            mini.open({
            	url: HOST_PATH + "/pmkz/pm/yaUi.do",
                title: "查看", width: 500, height: 400,
                loadOnRefresh: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "view", id: row.id };
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
        if (row) {
            $.ajax({
                url: HOST_URL+"/pmkz/yagl/delete.do?id="+row.id,
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
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"view()\">查看</a>";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"delone()\">删除</a>";
        return html;
    }
   
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>