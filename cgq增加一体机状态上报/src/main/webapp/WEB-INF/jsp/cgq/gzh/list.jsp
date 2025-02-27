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
            </div>
        </div>
        <!--撑满页面-->
        <div class="mini-fit" >
            <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
                 url="<%=request.getContextPath() %>/gzh/fzxx/getList.do" idField="id" multiSelect="true"
                 fitColumns="false" pageSize="100">
                <div property="columns">
                    <div field="id" type="checkcolumn" ></div>
                    <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
                    <div field="id" width="200" headerAlign="center" align="center">分组编号</div>
                    <div field="name" width="400" headerAlign="center" align="center">分组名称</div>
                    <div field="count" width="200" headerAlign="center" align="center">分组下人员数量</div>
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
        o.ssid = mini.getByName("ssid").getValue();
        o.sbbh = mini.getByName("sbbh").getValue();
        grid.load(o);
    }

    function onKeyEnter(e) {
        search();
    }

    function cz(){
        location.reload();
    }

</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>