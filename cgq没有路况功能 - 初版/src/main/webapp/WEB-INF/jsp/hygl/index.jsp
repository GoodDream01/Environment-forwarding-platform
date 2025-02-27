<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>政协会议管理系统</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

    <jsp:include page="/inc/common_js_include.jsp" />
    <script src="<%=request.getContextPath() %>/static/js/common/core.js" type="text/javascript"></script>

    <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    .logo
    {
        font-family:"微软雅黑",	"Helvetica Neue",​Helvetica,​Arial,​sans-serif;
        font-size:28px;font-weight:bold;color:#444;        
        cursor:default;
        position:absolute;top:28px;left:15px;        
        line-height:28px;
    }    
    .topNav
    {
        position:absolute;right:8px;top:10px;        
        font-size:12px;
        line-height:25px;
    }
    .topNav a
    {
        text-decoration:none;
        color:#222;
        font-weight:normal;
        font-size:12px;
        line-height:25px;
        margin-left:3px;
        margin-right:3px;
    }
    .topNav a:hover
    {
        text-decoration:underline;
        color:Blue;
    }   
     .mini-layout-region-south img
    {
        vertical-align:top;
    }
    .top{
    	background: url("<%=request.getContextPath() %>/static/images/top_red.png");
    }
    </style>
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="north" region="north" class="top" bodyStyle="overflow:hidden;" height="80" showHeader="false" showSplit="false">
        <div class="logo"></div>

        <div class="topNav">    
        	<span>你好：<font color="yellow">${currentUser.username }</font></span> |
            <a href="main.do">首页</a> |
            <a href="javascript:loginout();">退出</a>
        </div>

        <div style="position:absolute;right:12px;bottom:5px;font-size:12px;line-height:25px;font-weight:normal;">
            <span style="color:Red;font-family:Tahoma">（推荐Blue）</span>选择皮肤：
            <select id="selectSkin" onchange="onSkinChange(this.value)" style="width:100px;" >
                <optgroup label="传统风格">
                    <option value="default">default</option>
                    <option value="blue">blue</option>
                    <option value="pure">pure</option>
                    <option value="gray">gray</option>                
                    <option value="olive2003">olive2003</option>
                    <option value="blue2003" >blue2003</option>
                    <option value="blue2010" >blue2010</option>
                    <option value="bootstrap">bootstrap</option>   
                    <option value="jqueryui-cupertino">jqueryui-cupertino</option>
                    <option value="jqueryui-smoothness">jqueryui-smoothness</option>                               
                </optgroup>
                <optgroup label="扁平风格">
                    <option value="cupertino" selected>cupertino</option>
                    <option value="metro-white" >metro-white</option>
                    <option value="metro-green">metro-green</option>
                    <option value="metro-orange">metro-orange</option>
                    <option value="metro-gray">metro-gray</option>
                    <option value="metro-blue">metro-blue</option>                    
                </optgroup>
            </select>
        </div>
    </div>
    <div showHeader="false" region="south" style="border:0;text-align:center;" height="25" showSplit="false">
        Copyright © <!-- 石家庄市顶天科技开发有限公司 -->
    </div>
    <div region="west" title="系统导航" showHeader="true" bodyStyle="padding-left:1px;" showSplitIcon="true" width="230" minWidth="100" maxWidth="350">
        <ul id="leftTree" class="mini-tree" url="<%=request.getContextPath() %>/menu/getLeftMenu.do" showTreeIcon="true" style="width:100%;height:100%;"
              showTreeIcon="true" textField="menuName" idField="menuCode" parentField="menuPcode" resultAsTree="false"  
              onnodeselect="onNodeSelect" onnodeclick="onNodeSelect" 
        >

      
  
        </ul>        
    </div>
    <div title="center" region="center" style="border:0;">
        <!--Tabs-->
        <div id="mainTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;"      
            onactivechanged="onTabsActiveChanged" 
        >    
	        <div title="首页">   
	       		<iframe onload="//onIFrameLoad()" src="<%=request.getContextPath() %>/login/home.do" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
	        </div>
        </div> 
    </div>

    
</div>
<!-- ============================================弹框：Start=============================================== -->
<div id="win1" class="mini-window" title="新的会议通知" style="width:300px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <div property="footer" style="text-align:right;padding:5px;padding-right:15px;">
        <input type='button' value='确定' onclick="jsTz()" style='vertical-align:middle;'/>
    </div>

    <div id="hydxtzMsg" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->
</body>
</html>
<script type="text/javascript">
    mini.parse();
    var tree = mini.get("leftTree");

    function showTab(node) {
        var tabs = mini.get("mainTabs");

        var tab = tabs.getTab(0);
        if (tab) {
            tab._nodeid = node.menuCode;
            tab.title = node.menuName;
            var url =  HOST_URL + node.menuPath;
            if(url.indexOf("?")==-1){
            	url+="?";
            }
            url+="&_rand="+Math.random();
            
            $("#mainframe").attr("src",url);
            tabs.updateTab(tab);
        }  
    }

    function onNodeSelect(e) {
    
        var node = e.node;
        var isLeaf = e.isLeaf;

        if (isLeaf) {
            showTab(node);
        }
    }

    function onClick(e) {
    	 onNodeSelect(e);
    }
    function onQuickClick(e) {
        tree.expandPath("datagrid");
        tree.selectNode("datagrid");
    }

    function onTabsActiveChanged(e) {
        var tabs = e.sender;
        var tab = tabs.getActiveTab();
        if (tab && tab._nodeid) {

            var node = tree.getNode(tab._nodeid);
            if (node && !tree.isSelectedNode(node)) {
                tree.selectNode(node);
            }
        }
    }

    function onBeforeExpand(e) {
        var tree = e.sender;
        var nowNode = e.node;
        var level = tree.getLevel(nowNode);

        var root = tree.getRootNode();        
        tree.cascadeChild(root, function (node) {
            if (tree.isExpandedNode(node)) {
                var level2 = tree.getLevel(node);
                if (node != nowNode && !tree.isAncestor(node, nowNode) && level == level2) {
                    tree.collapseNode(node, true);
                }
            }
        });

    }
    function loginout(){
    	mini.confirm("确定要退出吗？", "确定？",
           	 function (action) {
           		if(action == "ok"){
		    		$.get(HOST_URL+'/login/loginout.do',function(text){
		    			if(text=="1"){
		    				window.location.href="<%=request.getContextPath() %>/login/login.do";
		    			}
		    		});
           		}
    	});
    }
    
    function showHyxttzWin(msg) {
        var win = mini.get("win1");

        var x = "right";
        var y = "bottom";

        $("#hydxtzMsg").html(msg);
        win.showAtPos(x, y);
    }
    function closeHyxttzWin() {
        var win = mini.get("win1");
        win.hide();
    }
    var hyxttzid;
    function jsTz(){
        $.ajax({
            url: HOST_URL+"/hygl/hyxttz/jstz.do?id="+hyxttzid,
			type: 'post',
		    dataType: 'json',
		    contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (data) {
            	closeHyxttzWin();
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
    var hyxttz_Reload = function(){
        $.ajax({
            url: HOST_URL+"/hygl/hyxttz/getWjsLastOne.do",
			type: 'post',
		    dataType: 'json',
		    contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (data) {
                if(data){
                	hyxttzid = data.zid;
                	var zryxm = data.zryxm;
                	var ztznr = data.ztznr;
                	var zfssj = data.zfssj;
                	var msg = zryxm+"，您好！您现在有一条系统通知：<br/>";
                	msg+=ztznr+"（"+zfssj+"）。";
                	showHyxttzWin(msg);
                }
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }  
    hyxttz_Reload();
    
    // 定时器
    setInterval(hyxttz_Reload, 30000);
</script>

<jsp:include page="/inc/common_pojie.jsp"></jsp:include>