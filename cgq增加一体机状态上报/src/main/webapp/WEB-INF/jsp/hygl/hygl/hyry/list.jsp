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
		          <input id="zhyid" name="zhyid" textName="hyinfo.zhymc" class="mini-buttonedit" onbuttonclick="onHyButtonEdit" required="true" allowInput="false" style="width:300px;"/>
	
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
<!-- 	                        <a class="mini-button" iconCls="icon-add" onclick="add()" plain="true" tooltip="增加...">增加</a>
	                        <a class="mini-button" iconCls="icon-edit" onclick="edit()" plain="true" tooltip="编辑...">编辑</a>
	                        <a class="mini-button" iconCls="icon-remove" onclick="delone()" plain="true">删除</a>       -->   
	                    </td>
	                </tr>
	            </table>           
	        </div>
	    </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
	
		    <div id="datagrid1" class="mini-datagrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath() %>/hygl/hyry/getList.do" idField="zid" multiSelect="true" 
			  fitColumns="false" pageSize="20"
		    >
		        <div property="columns">
		                    
		            <div field="zid" type="checkcolumn" ></div>        
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <div field="zryxm" width="200" headerAlign="center" align="center" allowSort="true" >姓名</div>
		            <div field="hyinfo.zhymc" width="200" headerAlign="center" align="center" allowSort="true" >会议名称</div>                                  
		            <div field="" width="150" headerAlign="center" align="center" allowSort="true" renderer="onCzRenderer">操作</div>
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   
    function search() {
    	var o={};
        var zhyid = mini.get("zhyid").getValue();
        o.zhyid = zhyid;
        
        grid.load(o);
    }
    
    function onKeyEnter(e) {
        search();
    }
    
    
    function cz(){
		location.reload();
	}
    
    function guiji() {
        var row = grid.getSelected();
        if (row) {//alert(row.ZID+":"+row.ZFID);
            mini.open({
            	url: HOST_PATH + "/hygl/hyry/guijiUi.do?id="+row.zid,
                title: "轨迹查看", width: '95%', height: '95%',
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
    
    function onCzRenderer(e) {
    	var fileUrl = e.record.zwjurl;
    	var fileName = e.record.zwjmc;
    	var html = "";
    	html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"guiji()\">查看轨迹</a>";
    	//html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"DownloadFile('"+fileUrl+"','"+fileName+"')\">下载</a>";
        return html;
    }
    
    function onGxsjRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }

    var yhzts = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_HY_HYWJLX.toString())%>;
    function onTypeRenderer(e){

    	return yhzts[e.value];
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
	var tree = mini.get("leftTree");
	 	
	var grid = mini.get("datagrid1");
	grid.load();   
	
	function expExcel(){
		var flac = "会议人员信息";
		ExportExcel(grid,flac);
	}
	
	 function onHyButtonEdit(e) {
		    var btnEdit = this;
		    mini.open({
		        url: HOST_URL + "/hygl/hygl/selDanUi.do",
		        title: "会议选择",
		        width: 550,
		        height: 380,
		        onload: function () {
		            var iframe = this.getIFrameEl();
		        	var zhyid = btnEdit.getValue();
		            var data = [{ zhyid: zhyid}];
		            iframe.contentWindow.SetData(data);
		        },
		        ondestroy: function (action) {
		            if (action == "ok") {
		                var iframe = this.getIFrameEl();
		                var data = iframe.contentWindow.GetData();
		                data = mini.clone(data);    //必须
		                if (data) {
		                	/*************************************/
		                	var zhyid = data.zid;
		                	var zhymc = data.zhymc;
		                	
		                	
		    	        	
		    	            btnEdit.setValue(zhyid);
		    	            btnEdit.setText(zhymc);
		    	            btnEdit.doValueChanged();
		    	            /*************************************/
		                }
		            }

		        }
		    });
		}


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>