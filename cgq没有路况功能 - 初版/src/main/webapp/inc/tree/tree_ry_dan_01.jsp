<%@page import="com.dt.common.utils.ComUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/common_userinfo.jsp" %>    
<%
String rootId = ComUtils.ConvNull(request.getParameter("rootId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc/common_js_include.jsp" />
<style type="text/css">
.mini-layout-border {
     border-width: 0px; 
}
</style>
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div region="center"  showHeader="false" >
        <ul id="leftTree" class="mini-tree" url="<%=request.getContextPath() %>/jggl/getRyTree.do" showTreeIcon="true" style="width:100%;height:100%;"
              showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false"  
              onnodeselect="onNodeSelect"  onbeforeload="onBeforeTreeLoad"
               expandOnLoad="true"
        >

        </ul>        
    </div>
</div>    
    

    <script type="text/javascript">
    
    //////////////////////////////////////////////////////////////////////////////
    
		mini.parse();
	 	var tree = mini.get("leftTree"); 

        function GetData() {
            var row = tree.getSelectedNode();
            return row;
        }
        //////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }

        function onOk() {
            CloseWindow("ok");
        }
        function onCancel() {
            CloseWindow("cancel");
        }
        function onRowDblClick(e) {
            onOk();
        }
        
        function onNodeSelect(e){
        	onOk();
        }
        function onBeforeTreeLoad(e){//动态加载树
            var tree = e.sender;    //树控件
            var node = e.node;      //当前节点
            var params = e.params;  //参数对象
            var levelnode =tree.getAncestors(e.node);//获取该节点所有父节点
            var nodepath="";
            if(levelnode.length==0){nodepath=e.node.text}
            else{//对路径进行拼接 ，用来为后台判断节点位置
                for(i=0;i<levelnode.length;i++){
                nodepath=nodepath+levelnode[i].text+"/";
                }
                nodepath=nodepath+e.node.text;
            }

            //可以传递自定义的属性
            var id=null;
            if(nodepath){id=node.id;}
            else{id="<%=rootId%>";}
            params.id = id; //后台获取数据源id
            params.nodepath=nodepath;//后台获取nodepath
        }


    </script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>