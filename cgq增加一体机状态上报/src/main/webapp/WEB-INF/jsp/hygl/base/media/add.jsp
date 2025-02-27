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
                    <td style="width:100px;" align="right">编号**：</td>
                    <td>    
                        <input id="zcode" name="zcode" value="" class="mini-textbox" required="true"  style="width:200px;" />
                    </td>
                </tr>
                <tr>
                    <td style="width:100px;" align="right">名称**：</td>
                    <td>    
	                    <input id="zname" name="zname" class="mini-textbox" value="" required="true" style="width:200px;"/>
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
                    <td style="width:100px;" align="right">联系人：</td>
                    <td>    
	                    <input id="zlinkman" name="zlinkman" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">电话：</td>
                    <td>    
	                    <input id="ztel" name="ztel" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">地址：</td>
                    <td>    
	                    <input id="zaddress" name="zaddress" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">登录名：</td>
                    <td>    
	                    <input id="zunitcode" name="zunitcode" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
                <tr>
                    <td style="width:100px;" align="right">密码：</td>
                    <td>    
	                    <input id="zpassword" name="zpassword" class="mini-textbox" value=""  style="width:200px;"/>
                    </td>
                 </tr>
      
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

            var json = mini.encode(o);
            
            $.ajax({
                url: HOST_URL+"base/media/save.do",
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
                	url: HOST_URL+"/base/media/getById.do?id="+data.id,
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
                	url: HOST_URL+"/base/media/getById.do?id="+data.id,
                	contentType:'application/json;charset=UTF-8',
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        
                        mini.get("zcode").setEnabled(false);
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
        



    </script>


    



</body>
</html>
<jsp:include page="/common_pojie.jsp"></jsp:include>