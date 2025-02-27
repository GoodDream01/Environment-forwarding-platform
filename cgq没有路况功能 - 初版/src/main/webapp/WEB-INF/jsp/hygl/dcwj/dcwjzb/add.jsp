<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
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
    <form id="form1" method="post">
        <input name="zid" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:80px;">标题：</td>
                    <td style="width:360px;">    
		                <input name="ztitle" class="mini-textbox" style="width:330px;" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >内容描述：</td>
                    <td >    
                    	<textarea name="znrms" class="mini-textarea" style="width:330px;" required="true" rows="" cols=""></textarea>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">开始时间：</td>
                    <td style="width:360px;">    
		                <input name="zkssj" class="mini-datepicker" style="width:330px;" showTime="true"  format="yyyy-MM-dd HH:mm:ss"  required="true" />
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">结束时间：</td>
                    <td style="width:360px;">    
		                <input name="zjssj" class="mini-datepicker" style="width:330px;" showTime="true"  format="yyyy-MM-dd HH:mm:ss" renderer="ondayRenderer" required="true" />
                    </td>
                </tr>
                <tr>
                    <td >是否禁用：</td>
                    <td >    
<!--                         <input name="zists" class="mini-textbox"  required="true" /> -->
<!--                         <input name="zists" class="mini-combobox" valueField="value" textField="text"  showNullItem="true" -->
<%-- 		                   url="<%=request.getContextPath() %>/gggl/xtgg/ists.do?id=<%=DictTypeEnum.PUB_HY_XTGG_TSZT %>" --%>
<!-- 		                   required="true"  onvaluechanged="changeTypeCode" -->
<!-- 		                /> -->
						 <input name="zdisabled" class="mini-combobox" valueField="value" textField="text"  showNullItem="true"
		                   url="<%=request.getContextPath() %>/common/getDictItemByTypeCode.do?typeCode=<%=DictTypeEnum.PUB_HY_DCWJ_ZDISABLED %>"
		                   required="true" 
		                />
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <td >备注信息：</td> -->
<!--                     <td >     -->
<!--                         <input name="bz" class="mini-textbox" required="false" /> -->
<!--                     </td> -->
<!--                 </tr> -->
               

            </table>
        </div>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>     
        </div>        
    </form>
   
    

    <script type="text/javascript">

    //////////////////////////////////////////////////////////////////////////////
    
	mini.parse();
    var form = new mini.Form("form1");

    function SaveData() {
        var o = form.getData(); 
        var zkssj = mini.getByName("zkssj").getValue();
        form.validate();
        if (form.isValid() == false) return;

        var json = mini.encode(o);
        
        $.ajax({
            url: HOST_URL+"/dcwj/dcwjzb/save.do",
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
//             	alert(text);
                alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }
    var czlx = "";
    ////////////////////
    //标准方法接口定义
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
        	getSort();
        }
    }
    
    //
	function CloseWindow(action) {      
		if (action == "close" && form.isChanged()) {
			mini.confirm("数据被修改了，是否先保存？", "确定？",
		       	 function (action) {
		       		if(action == "ok"){
		       			
		       		}else{
		    			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		       			else window.close(); 
		       		}
		       		  
			});
		}else{
			if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
   			else window.close(); 
		}
        
	 }
	 function onTh(e) {
	 	TuiHui();
	 }
	 function onOk(e) {
	     SaveData();
	 }
	 function onCancel(e) {
	     CloseWindow("close");
	 }
    
	
    function changeTypeCode(e){
    	getSort();
    }
    
    function getSort(){
    	var typeCode = mini.getByName("zsort").getValue();
    	
    	var sortObj = mini.getByName("zsort");
    	if(typeCode =="" || typeCode =="0"){
            $.ajax({
                url: HOST_URL+"/gggl/xtgg/getSort.do",
    			type: 'post',
    		    dataType: 'html',
    		    contentType:'application/json;charset=UTF-8',
                cache: false,
                success: function (text) {
               		sortObj.setValue(Number(text)+1);
                }
            });
    	}else{
    		sortObj.setValue(0);
    	}
    	

    }
    
    function checkByname(e){
//     	alert("getByName");
    	if (e.isValid) {

            e.errorText = "正在后台验证用户名是否重复...";
            e.isValid = false;
            
            var username = mini.getByName("ztitle").getValue();
            var url = "gggl/xtgg/checkTitle.do?ztitle="+username+"";
            if(czlx=="edit"){
            	var zid = mini.getByName("zid").getValue();
            	url = "user/checkTitlenameAndZid.do?username="+username+"&zid="+zid+"";
            }
            
            $.ajax({
            	url: HOST_URL+"/"+url,
                cache: false,
                async: false,
                dataType: "json",
                success: function (o) {
                	
                	if(o){
                    	if(o.flag){
                            e.errorText = "标题重复，请重新填写！";
                            e.isValid = false;
                    	}else{
                    		e.errorText = "";
                            e.isValid = true;
                    	}
                	}
                }
            });
        }
    }
    
    function ondayRenderer(e) {
    	
    	var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>