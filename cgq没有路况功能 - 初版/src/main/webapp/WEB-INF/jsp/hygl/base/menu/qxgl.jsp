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
    <style>
    .function-item 
    {
        margin-left:5px;
        margin-right:5px;
    }
    .function-item input
    {
        vertical-align:bottom;
    }
    </style>
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
	    <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-save" onclick="save()" plain="true">保存</a>         
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="treegrid1" class="mini-treegrid"  style="width:100%;height:100%;"  allowResize="true"
	
		      url="<%=request.getContextPath() %>/hygl/menu/getAllMenu.do" idField="menuCode" multiSelect="true" 
			  treeColumn="menuName"  expandOnLoad="true" showTreeIcon="true"
			  parentField="menuPcode" resultAsTree="false"
			  allowCellEdit="true" allowCellSelect="true"
			  ondrawcell="ondrawcell"
			  fitColumns="false"
		    >
		        <div property="columns">
     				<div type="indexcolumn" width="50" headerAlign="center" align="center">序号</div>
		            <div field="menuCode" width="100" headerAlign="center" align="center">编号</div>
		            <div name="menuName" field="menuName" width="200" headerAlign="center" align="left" allowSort="true" >菜单名称</div>        
		            <div field="roleId" width="300" headerAlign="center" align="center" allowSort="true" >权限</div>
		 			<!--ComboBox：远程数据-->
		            <div type="comboboxcolumn" field="dwbh" width="380" headerAlign="center" >单位权限
		                <%-- <input property="editor" class="mini-combobox" valueField="ZCODE" textField="ZNAME" style="width:100%;" url="<%=request.getContextPath() %>/jcsj/cbdw/getList.do?isLimit=false" /> --%>
						<div property="editor" class="mini-combobox" style="width:250px;"  popupWidth="400" textField="ZNAME" valueField="ZCODE" 
						    url="<%=request.getContextPath() %>/jcsj/cbdw/getList.do?isLimit=false"  multiSelect="true"  showClose="true"  >     
						    <div property="columns">
						        <div header="ID" field="ZCODE"></div>
						        <div header="单位名称" field="ZNAME"></div>
						    </div>
						</div>             
		            </div>   
		        </div>
		    </div>
	    </div>
    </div>
</div>    
    

    <script type="text/javascript">
   
    function saveDo(){
        var o = getFuns();   
        var json = mini.encode(o);
        $.ajax({
            url: HOST_URL+"/hygl/menu/qxglSave.do?",
			type: 'post',
			data: json,
		    dataType: 'json',
		    contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (text) {
                
                var success = text.success;
                if(success){
                	alert("保存成功！");
                	tree.reload();
                }else{
                	alert("保存失败！");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
            
    }
    
    function save() {
		mini.confirm("是否要保存？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
		       			saveDo();
		       		}
		});

        
    }


    var Jslxs = <%=DictUtils.ItemToKeyValue(DictTypeEnum.PUB_USER_JSLX.toString())%>;
    var roles = <%=DictUtils.ItemToArray(DictTypeEnum.PUB_USER_JSLX.toString())%>;
    
    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var tree = mini.get("treegrid1");


    function ondrawcell(e) {
        var tree = e.sender,
            record = e.record,            
            column = e.column,
            field = e.field,
            id = record[tree.getIdField()],
            funs = record.roleId,
        	_uid = record._uid;

        var inputName = "qx_"+_uid+"_"+id;
        
        function checkSel(v,funs){
        	var flag = false;

        	var temp = funs.split(",");
        	if(temp!=null && temp.length>0){
        		for(var i=0;i<temp.length;i++){
        			if(v==temp[i]){
        				flag = true;
        				break;
        			}
        		}
        		
        	}
        	
        	return flag;
        }
        
        function createCheckboxs(funs) {
            if (!funs) return "";
            var html = "";
            
            for (var i = 0, l = roles.length; i < l; i++) {
                var fn = roles[i];
                var clickFn = 'checkFunc(\'' + id + '\',\'' + fn.value + '\', this.checked)';
                var checked = checkSel(fn.value,funs) ? 'checked' : '';
                var disabled = fn.value=="0"?'disabled':'';
                html += '<label class="function-item"><input onclick="' + clickFn + '" ' + checked + ' type="checkbox" '+disabled+' name="'
                        + inputName + '" value="'+ fn.value + '" hideFocus/>' + fn.text + '</label>';
            }
            return html;
        }

        if (field == 'roleId') {
            e.cellHtml = createCheckboxs(funs);
        }
    }

    function getFuns(){
    	var data = [];
        var rows = tree.findRows(function(){return true;});
       // var json = mini.encode(data);
        //alert(json);
        if(rows!=null && rows.length>0){
        	for(var i=0;i<rows.length;i++){
        		var row = rows[i];
        		var o = {};
        		o.menuId=row.menuId;
        		o.menuCode=row.menuCode;
        		o.menuName=row.menuName;
        		o.menuLevel=row.menuLevel;
        		o.menuPcode=row.menuPcode;
        		o.menuStatus=row.menuStatus;
        		o.menuOrder=row.menuOrder;
        		o.mneuType=row.mneuType;
        		o.roleId=row.roleId;
        		o.dwbh=row.dwbh;
        		
        		data.push(o);

        	}
        }
        //console.log(data);
        return data;
    }
    function checkFunc(id, action, checked) {
        var record = tree.getRecord(id);
        if(!record) return;
        
    	var _uid = record._uid;
        var inputName = "qx_"+_uid+"_"+id;

        function getAction(action) {
        	var objs = $(":checkbox[name='"+inputName+"']:checked");
        	
        	var temp = "";
            for (var i = 0, l = objs.size(); i < l; i++) {
                var o = objs.eq(i);
                var v = o.val();
                temp += v;
                
                if(i!=l-1){
                	temp += ",";
                }
            }
            return temp;
        }
        record.roleId = getAction(action);
        //console.log(record.roleId);
    }
        



    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>