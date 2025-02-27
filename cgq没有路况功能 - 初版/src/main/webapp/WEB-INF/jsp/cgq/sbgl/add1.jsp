<%@page import="com.dt.zxhygl.enums.DictTypeEnum"%>
<%@page import="com.dt.zxhygl.mvc.base.pojo.DictType"%>
<%@page import="com.dt.common.db.ConnDataBase"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String jd = request.getParameter("jd");
	String wd = request.getParameter("wd");
	String sbbh = "",mc = "",bj = "1";
	System.out.println("经度："+jd + "维度："+wd);
	
	ConnDataBase conn = new ConnDataBase();
	List list = conn.executeQuery("select * from cgq_sbgl where jd = "+jd+" and wd = "+wd);
	if(list.size()>0){
		Map map = (Map)list.get(0);
		sbbh = map.get("sbbh").toString();
		mc = map.get("mc").toString();
		bj = map.get("bj").toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增</title>
<jsp:include page="/inc/common_js_include.jsp"/>
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="center" region="center" style="border:0;">
        <div style="width:100%;">
        <%=sbbh %>(<%=mc %>)
        </div>
	    <!--撑满页面-->
	    <div class="mini-fit" >
		    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
		      url="<%=request.getContextPath() %>/cgq/sbgl/getList1.do?jd=<%=jd%>&wd=<%=wd%>" idField="id" multiSelect="true" 
			  fitColumns="false" pageSize="20">
		        <div property="columns">
		            <div type="indexcolumn" width="40" headerAlign="center" align="center">序号</div>
		            <%if(bj.equals("1")){%>
		            <div field="xtsj" width="150" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>       
		            <%}else{%>
		            <div field="time" width="150" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">时间</div>       
		            <%}%>
		            <div field="pjz" width="80" headerAlign="center" align="center" allowSort="true" renderer="getFLDJ">风力</div> 
		            <div field="pjd" width="60" headerAlign="center" align="center" allowSort="true">风向</div>
		            <%if(bj.equals("2")){%>
		            <div field="tpbm" width="60" headerAlign="center" align="center" renderer="onCzRenderer">查看</div>
		            <%}%> 
		        </div>
		    </div>
	    </div>
    </div>
</div>    
<script type="text/javascript">
    
	mini.parse();
	var grid = mini.get("datagrid1");
	grid.load(); 
    
    function onCzRenderer(e) {
    	var html = "";
    	if(e.value!="" && e.value!=null){
    		html += "<a class=\"mini-button\" style=\"width:60px;\" onclick=\"view()\">图片</a>";
    	}
        return html;
    }
	
    function view() {    	
        var row = grid.getSelected();   
        var tplj = row.tpbm;
        tplj = tplj.substring(16,tplj.length);
        window.open("http://fsh01.com:9023"+tplj,
                "_blank", "height=500px,width=400px");
        /* if (row) {
            mini.open({
            	url: HOST_PATH + "/cgq/cgqsj/addUi1.do",
                title: "图片", width: 400, height: 500,
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
        } */
    }
</script>
</body>
</html>
<jsp:include page="/inc/common_pojie.jsp"></jsp:include>