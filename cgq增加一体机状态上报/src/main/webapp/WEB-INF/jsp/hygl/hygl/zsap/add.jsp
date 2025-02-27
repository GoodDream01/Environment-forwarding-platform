<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <jsp:include page="/common_include.jsp" />
    <style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>

</head>
     
<body style="position: relative;overflow-y:scroll;">
    <form id="form1" method="post">
    	<div style="display:none;">
        	<input id="zid" name="zid" class="mini-hidden" />
        	<input id="zsortindex" name="ZSORTINDEX" class="mini-hidden" />
        </div>
        <fieldset style="border:solid 0px #aaa;padding:3px;">
            <table>
                <tr>
                    <td style="width:100px;" align="right">入住日期：</td>
                    <td>    
                        <input id="zrzrq" name="zrzrq" value="" class="mini-datepicker" required="true"  style="width:200px;" />
                    </td>
                </tr>
                <tr>
                    <td style="width:100px;" align="right">离开日期：</td>
                    <td>    
	                    <input id="zlkrq" name="zlkrq" class="mini-datepicker" value="" required="true" style="width:200px;"/>
                    </td>
                 </tr>
<!--                 <tr> -->
<!--                     <td style="width:100px;" align="right">隶属：</td> -->
<!--                     <td>     -->
<!-- 	                     <input id="ZTYPE" name="ZTYPE" class="mini-combobox" valueField="ZHIID" textField="ZHIMC"  -->
<%-- 	                         url="<%=request.getContextPath() %>/common/getZiDianZHI.do?ZDID=<%=ZiDianUtils.JCSJ_CBDW_LSLX %>" --%>
<!-- 	                        /> -->
<!--                     </td> -->
<!--                  </tr> -->
                <tr>
                    <td style="width:100px;" align="right">宾馆名称：</td>
                    <td>    
	                    <input id="zhotel" name="zhotel" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">房间号：</td>
                    <td>    
	                    <input id="zroomnum" name="zroomnum" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">相关会议：</td>
                    <td>    
		          		<input id="zhyid" name="zhyid" textName="hyinfo.zhymc" class="mini-buttonedit" onbuttonclick="onHyButtonEdit" required="true" allowInput="false" style="width:300px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">入住人员：
                    	<input id="zrynames" name="zrynames" class="mini-hidden"/>
                    	<input id="zryids" name="zryids" class="mini-hidden"/>
                    </td>
                    <td>    
<!-- 	                    <input id="zryid" name="zryid" textName="committee.zname" class="mini-buttonedit" onbuttonclick="onGzryButtonEdit" required="true" allowInput="false" style="width:300px;"/> -->
                    	 <fieldset style="border:solid 1px #aaa;padding:3px;">
					            <legend >政协委员</legend>
					            <div style="padding:5px;">
					            <!-- ////////////////////////////////////////////////////////////// -->
					            <div class="mini-fit" style="width:365px;height:350px;">
								    <div style="width:100%;">
									    <div class="mini-toolbar" style="padding:2px;">                
									        <a class="mini-button" iconCls="icon-add" plain="true" onclick="openCommitteeWin">选择...</a>     
									    </div>
									</div>
								    <div id="committeeGrid" class="mini-datagrid" style="width:100%;height:300px;"
								        allowResize="false" showPager="false" idField="id" 
								        allowCellEdit="true" allowCellSelect="true"
								    >
								        <div property="columns">
								        	<div type="indexcolumn" width="30"></div>
								            <div field="zid" visible="false" ></div> 
								            <div field="zhyid" visible="false" ></div> 
								            <div field="zryid" visible="false" ></div>
								            <div field="zrylx" visible="false" ></div>  
								            <div field="zsfzh" visible="false" ></div>
								            <div field="zdhhm" visible="false" ></div> 
								            <div field="zisdxtz" visible="false" ></div>  
								            <div field="zdxtzsj" visible="false" ></div>  
								            <div field="zdxfscs" visible="false" ></div>  
								            <div field="zlrsj" visible="false" ></div>
								            <div field="zisxttz" visible="false" ></div> 
								            <div field="zxttzsj" visible="false" ></div> 
								            <div field="zxtfscs" visible="false" ></div>
											<div field="zisqd" visible="false" ></div> 
											<div field="zqdsj" visible="false" ></div>    
								            
								            <div field="zryxm" width="50" headerAlign="center" align="center" allowSort="false">姓名</div>                                                            
								            <div field="" width="50" headerAlign="center" align="center" allowSort="false" renderer="onDelCommitteeRenderer"></div>
								            <div field="" ></div>
				    
								        </div>
								    </div>
								</div>
					            <!-- ////////////////////////////////////////////////////////////// -->
					            </div>
					        </fieldset> 
                    </td>
                 </tr>
<!--                 <tr> -->
<!--                     <td style="width:100px;" align="right">密码：</td> -->
<!--                     <td>     -->
<!-- 	                    <input id="zpassword" name="zpassword" class="mini-textbox" value=""  style="width:200px;"/> -->
<!--                     </td> -->
<!--                  </tr> -->
      
            </table>
        </fieldset>

        <div style="text-align:center;padding:10px;">
            <a class="mini-button" name="ok" onclick="onOk" style="width:60px;margin-right:20px;">保存</a>       
            <a class="mini-button" name="cancel" onclick="onCancel" style="width:60px;">关闭</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");
        
        function SaveData() {
            var o = form.getData(); 
            
            form.validate();
            if (form.isValid() == false) return;

        	////工作人员
//             var committee = committeeGrid.getData();
//         	o.committee = committee;
//         	var zryids = 
        
            var json = mini.encode(o);
//             console.log(json);
            $.ajax({
                url: HOST_URL+"hygl/zsap/save.do",
				type: 'post',
				dataType: 'json',
				contentType:'application/json;charset=UTF-8',
                data: json,
                cache: false,
                success: function (text) {
                    
                	var success = text.success;
                    if(success){
                    	alert("保存成功！");
                    	CloseWindow("save");
                    }else{
                    	alert("保存失败！");
                    }
                    
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "view") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);

                $.ajax({
                	url: HOST_URL+"/hygl/zsap/getById.do?id="+data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        
                        var committee = o.committee;
                        if(committee){
                        	for(var i=0;i<committee.length;i++){
                        		committee[i].zryxm = committee[i].zname;
                        		committee[i].zryid = committee[i].zcode;
                        	}
                        	var committeeGrid1 = mini.get("committeeGrid");
                        	committeeGrid1.addRows(o.committee);
                        }
                        
                        mini.getbyName("ok").setEnabled(false);
                    }
                });
            }else if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
				
                $.ajax({
                	url: HOST_URL+"/hygl/zsap/getById.do?id="+data.id,
                	contentType:'application/json;charset=UTF-8',
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        var committee = o.committee;
                        if(committee){
                        	for(var i=0;i<committee.length;i++){
                        		committee[i].zryxm = committee[i].zname;
                        		committee[i].zryid = committee[i].zcode;
                        	}
                        	var committeeGrid1 = mini.get("committeeGrid");
                        	committeeGrid1.addRows(o.committee);
                        }
                        
//                         mini.get("zcode").setEnabled(false);
                    }
                });
            }else if (data.action == "new") {
    	
//             	 mini.get("ZTYPE").setValue("0");

            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
            	mini.confirm("数据被修改了，是否先保存？", "确定？",
                   	 function (action) {
                   		if(action == "ok"){
                   			return false;
                   		}
            	});
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
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

////////////////////////////////////////////////政协委员：start////////////////////////////////////////////////////
var committeeGrid = mini.get("committeeGrid");
function openCommitteeWin(){
	var title="选择政协委员";
	var zhyid = mini.get("zhyid").getValue();
// 	alert(zhyid);
    mini.open({
        url: HOST_PATH + "/hygl/zsap/committeeUi.do?zhyid="+zhyid,
        title: title, width: 700, height: 500,
        loadOnRefresh: true,
        showMaxButton: true, 
        onload: function () {
        	var iframe = this.getIFrameEl();
        	var rows = GetGridAllRowData(committeeGrid);
        	iframe.contentWindow.SetData(rows);
        },
        ondestroy: function (action) {
	         if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = mini.clone(data);    //必须
	                if (data) {
	                	var temp = new Array();
	                	var zrynames = "";
	                	var zryids = "";
	                	for(var i=0;i<data.length;i++){
	                		var d = data[i];
	                		var o = {};
	                		
	                		o.zryid = d.zcode;
	                		o.zryxm = d.zname;
	                		o.zrylx = "1";
	                		o.zsfzh = d.zsfzh;
	                		o.zdhhm = d.zmobiletel;
	                		
	                		temp.push(o);
	                		
	                		if(i!=0){
	                			zrynames += "，";
	                			zryids += ",";
	                		}
	                		zrynames += d.zname;
                			zryids += d.zcode;
	                	}
	                	committeeGrid = mini.get("committeeGrid");
	                	committeeGrid.clearRows();
	                	committeeGrid.addRows(temp);
	                	
	                	mini.get("zrynames").setValue(zrynames);
	                	mini.get("zryids").setValue(zryids);

	                }
            	}
        }
    });
};
function onDelCommitteeRenderer(e){
	var uid = e.record._uid;
	var html = "";
	    html+= "<span class=\"icon-delete\" onclick=\"delCommittee('"+uid+"')\" style=\"display:inline-block;cursor: pointer;width:16px;height:16px;\"></span>";
	return html;
};
function delCommittee(row_uid){
	mini.confirm("是否要删除？", "确定？",
	       	 function (action) {
	       		if(action == "ok"){
			    	var row = committeeGrid.getRowByUID(row_uid);
			    	committeeGrid.removeRow(row,false);
	       		}
		});
};

function setCommitteeData(data){
    if (data) {
    	committeeGrid.addRows(data);	
    }
}
function getCommitteeData(){
	var temp = new Array();

   	var rows = GetGridAllRowData(committeeGrid);
   	if(rows && rows.length>0){
   		for(var i=0; i<rows.length; i++){
   			var row = rows[i];
   			var o = {};
   			
   			o.zid = row.zid;
   			o.zhyid = row.zhyid;
   			o.zryid = row.zryid;
   			o.zryxm = row.zryxm;
   			o.zrylx = row.zrylx;
   			o.zsfzh = row.zsfzh;
   			o.zdhhm = row.zdhhm;
   			o.zisdxtz = row.zisdxtz;
   			o.zdxtzsj = FormatLongDate(row.zdxtzsj);
   			o.zdxfscs = row.zdxfscs;
   			o.zlrsj = FormatLongDate(row.zlrsj);
   			o.zisxttz = row.zisxttz;
   			o.zxttzsj = FormatLongDate(row.zxttzsj);
   			o.zxtfscs = row.zxtfscs;
   			
   			temp.push(o);
   		}
   	}
   return temp;

}
////////////////////////////////////////////////政协委员：end////////////////////////////////////////////////////
</script>

    



</body>
</html>
<jsp:include page="/common_pojie.jsp"></jsp:include>