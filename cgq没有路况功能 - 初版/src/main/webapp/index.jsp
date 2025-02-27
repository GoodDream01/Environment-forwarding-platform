<%@page import="com.dt.common.utils.SystemConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/logincheck.jsp" %>
    <%@include file="/logininfo.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8">
    	<title>环境在线综合检测系统</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />



    <jsp:include page="/common_include.jsp" />
    
    <SCRIPT LANGUAGE="javascript">
	　　 
	　　<%-- window.open ("<%=request.getContextPath() %>/daping/index.jsp"); --%>
	   
	</SCRIPT>
    
    <script src="<%=request.getContextPath() %>/static/js/common/core.js?&<%=Math.random()%>" type="text/javascript"></script>

    <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    .logo
    {
        font-family:"YouYuan",	"Helvetica Neue",​Helvetica,​Arial,​sans-serif;
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
        background-color: #0b7ff3;
    }
    </style>
    <style type="text/css">
a {
  text-decoration: none;
}
ul, li 
{
    position:relative;
    list-style: none;
    padding: 0;
    margin: 0;
}
.nav
{
    padding-left:0;
    margin:0;
    list-style:none;
}

.nav > li
{
    position:relative;
    display:block;
    margin:0;
    padding:0;
}

.nav > li > a
{
    position:relative;
    display:block;
    padding:10px 15px;
    line-height:1.4;
    outline:none;
}

.nav > li > a:hover,
.nav > li > a:focus,
.nav > li.active > a,
.navbar-nav li.open a.dropdown-toggle
{
    text-decoration:none;
    background-color:#ff0000;
    box-shadow:none;
}


.navbar
{
    position:absolute;
    right:0px;
    top:0px;
    height:80px;
    margin-bottom:0px;
    border-bottom:solid 0px #e7e7e7;
    width: 800px;

}
.navbar-nav
{
    float:left;    
}
.navbar-nav > li
{
    float:left;
    height:80px;
}
.navbar-nav > li > a
{    
    padding-top: 35px;
    padding-bottom: 35px;
    color:#fff;
    cursor:pointer;
    box-shadow:none;
}
/* icontop */

.navbar-nav > li.icontop > a{
    padding:0 8px;/* 这里改间距 */
}
.icontop i
{
    padding-top: 4px;
    text-align: center;
    display: block;
    font-size: 40px;
    line-height: 40px;
    width: 100%;
    margin: 0;
}
.icontop span {
    text-align: center;
    display: block;
    line-height: 35px;
    width: 100%;
    margin: 0;
}
    </style>
</head>
<body>
<div id="main" class="mini-layout" style="width:100%;height:100%;">
    <div title="north" region="north" class="top" bodyStyle="overflow:hidden;" height="80" showHeader="false" showSplit="false">
        <div class="logog" style="font-size: 40px;margin: 10px 50px 50px 20px;color: #F8F8FF">
            <span>环境在线综合检测系统</span>
        </div>
		<div class="navbar">
		    <ul class="nav navbar-nav" style="display: none;">
		        <li class="icontop"><a href="javascript:changeMenu(1);"><i class="fa fa fa-cubes"></i><span >综合信息平台</span></a></li>
		        <li class="icontop"><a href="javascript:changeMenu(2);"><i class="fa fa-bank"></i><span >会议管理系统</span></a></li>
		        <li class="icontop"><a href="javascript:changeMenu(3);"><i class="fa fa-briefcase"></i><span >提案办理系统</span></a></li>
		        <li class="icontop"><a href="javascript:changeMenu(4);"><i class="fa fa-calendar-check-o"></i><span >委员履职系统</span></a></li>
		        <li class="icontop"><a href="javascript:changeMenu(5);"><i class="fa fa-laptop"></i><span >协同办公系统</span></a></li>
		        <li class="icontop"><a href="javascript:;"><i class="fa fa-location-arrow"></i><span >社情民意系统</span></a></li>
		        <li class="icontop"><a href="javascript:handleFullScreen();"><i class="fa fa-commenting-o"></i><span >微建议系统</span></a></li>
		    </ul>
		</div>

        <div class="topNav">    
        	<span>您好：<font color="yellow"><%=USERINFO_USERXM %></font></span> |
            <a href="index.jsp">首页</a> |
            <a href="javascript:loginout();">退出</a>
        </div>

        <div style="position:absolute;right:12px;bottom:5px;font-size:12px;line-height:25px;font-weight:normal;display: none;">
            <span style="color:Red;font-family:Tahoma"><!-- （推荐Blue） --></span><!-- 选择 -->皮肤：
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
                    <option value="cupertino">cupertino</option>
                    <option value="metro-white"  selected>metro-white</option>
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
    <div  region="west" title="系统导航" showHeader="true" bodyStyle="padding-left:1px;" showSplitIcon="true" width="230" minWidth="100" maxWidth="350"> 
         <ul id="leftTree" class="mini-tree" url="" showTreeIcon="true" style="width:100%;height:100%;"
               showTreeIcon="true" textField="text" idField="id" resultAsTree="false"
               onnodeselect="onNodeSelect" onnodeclick="onNodeSelect" 
         > 

      
  
         </ul>         
    </div> 
    <div title="center" region="center" style="border:0;">
        <!--Tabs-->
        <div id="mainTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;"      
            onactivechanged="//onTabsActiveChanged" contextMenu="#tabsMenu"
        >    
        	<%
        	String url = request.getContextPath()+"/home.jsp";
        	%>
	        <div title="首页" name="first" url="<%=url %>">   
<!--	       	<iframe onload="//onIFrameLoad()" src="<%=request.getContextPath() %>/xmdb/hztj/dtzs.do" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
 	       		<iframe onload="//onIFrameLoad()" src="home.jsp" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe> -->
	        </div>
        </div> 
	    <ul id="tabsMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen" style="display:none;">        
	        <li onclick="closeTab">关闭标签页</li>                
		    <li onclick="closeAllBut">关闭其他标签页</li>
		    <li onclick="closeAll">关闭所有标签页</li>
		    <li onclick="refreshTab()">刷新</li>          
	        <!-- <li onclick="closeAllButFirst">关闭其他[首页除外]</li>  -->  
	    </ul>
    </div>

    
</div>
<!-- ============================================弹框：Start=============================================== -->
<div id="win1" class="mini-window" title="新的系统通知" style="width:300px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <div property="footer" style="text-align:right;padding:5px;padding-right:15px;">
        <input type='button' value='确定' onclick="jsTz()" style='vertical-align:middle;'/>
    </div>

    <div id="hydxtzMsg" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->

<!-- ============================================履职弹框：Start=============================================== -->
<div id="win2" class="mini-window" title="待办提醒" style="width:500px;height:300px;font-size: 24px;line-height: 200%" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
<!--     <div property="footer" style="text-align:right;padding:5px;padding-right:15px;">
         <input type='button' value='查看排名情况' onclick="pm()" style='vertical-align:middle;'/> 
    </div> -->

    <div id="hydxtzMsg1" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->

<!-- ============================================市领导审批弹框：Start=============================================== -->
<div id="win3" class="mini-window" title="市领导审批提醒" style="width:300px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >

    <div id="hydxtzMsg2" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->

<!-- ============================================督办信息提醒弹框：Start=============================================== -->
<div id="win4" class="mini-window" title="督办信息提醒提醒" style="width:300px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >

    <div id="hydxtzMsg3" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->

<!-- ============================================答复信息提醒弹框：Start=============================================== -->
<div id="win5" class="mini-window" title="答复信息提醒提醒" style="width:300px;height:200px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >

    <div id="hydxtzMsg4" style="padding:5px;"></div>
</div>
<!-- ============================================弹框：End=============================================== -->
</body>
</html>
<script type="text/javascript">
    mini.parse();
    var tree = mini.get("leftTree");

	var tabs = mini.get("mainTabs");
    var currentTab = null;

    function onBeforeOpen(e) {
        currentTab = tabs.getTabByEvent(e.htmlEvent);
        if (!currentTab) {
            e.cancel = true;                
        }
        var contextMenu = e.sender;
        if(currentTab){
        	if(currentTab.name=="first"){
        		//console.log(contextMenu.items[0]);
        		contextMenu.items[0].enabled=false;
        	}else{
        		contextMenu.items[0].enabled=true;
        	}
        }
    }

    ///////////////////////////
    function closeTab() {
        tabs.removeTab(currentTab);
    }
    function closeAllBut() {
        //tabs.removeAll(currentTab);
        closeAllButFirst();
    }
    function closeAll() {
        var but = [];            
        but.push(tabs.getTab("first"));
        tabs.removeAll(but);
        //tabs.removeAll();
    }
    function closeAllButFirst() {
        var but = [currentTab];            
        but.push(tabs.getTab("first"));
        tabs.removeAll(but);
    }
    function refreshTab(){
    	tabs.reloadTab(currentTab);
    }

    function showTab(node) {
        var tabs = mini.get("mainTabs");

        var tab = tabs.getTab(0);

        var id = "tab$" + node.id;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab._nodeid = node.id;
            tab.name = id;
            tab.title = node.text;
            var url = node.url;
            tab.showCloseButton = true;
            
        	if(url && url.indexOf("TRACKOA")!=-1){
        		<%--url='<%=SystemConstant.OAURL%>/'+url;--%>
        	}else{
        		url =  HOST_URL + url;
        	}
            if(url.indexOf("?")==-1){
            	url+="?";
            }
            url+="&_rand="+Math.random();

            //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
            tab.url = url;

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }
    
    function showTab2(node) {
        var tabs = mini.get("mainTabs");

        var tab = tabs.getTab(0);

        var id = "tab$" + node.menuCode;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab._nodeid = node.menuCode;
            tab.name = id;
            tab.title = node.menuName;
            var url = node.menuPath;
            tab.showCloseButton = true;
            
        	if(url && url.indexOf("TRACKOA")!=-1){
        		<%--url='<%=SystemConstant.OAURL%>/'+url;--%>
        	}else{
        		url =  HOST_URL + url;
        	}
            if(url.indexOf("?")==-1){
            	url+="?";
            }
            url+="&_rand="+Math.random();

            //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
            tab.url = url;

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);

    }

    function onNodeSelect(e) {
    
        var node = e.node;
        var isLeaf = e.isLeaf;

        if (isLeaf) {
        	if(node.id){
        		showTab(node);
        	}else{
        		showTab2(node);
        	}
            
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

    function pm(){
                mini.open({
                	url: HOST_PATH + "/lzgl/ckpm.do",
                    title: "履职排名", width: 850, height: 600,
                    loadOnRefresh: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                       
                        
                        
                    },
                    ondestroy: function (action) {
                        grid.reload(); 
                    }
                });
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
		    				window.location.href="login.jsp";
		    			}
		    		});
           		}
    	});
    }
    function changeMenu(){
        var title = "系统导航";
        var url = "hygl/menu/getLeftMenu.do";
        changeMenuHandler2(url,title);
    }

    function changeMenuHandler(url,title){
    	var leftTree = mini.get("leftTree");
    	leftTree.setTextField("text");
    	leftTree.setIdField("id");
    	leftTree.setParentField("pid");
    	leftTree.load(HOST_URL+'/'+url);
    	
    	changeTitle(title);
    }
    function changeMenuHandler2(url,title){
    	var leftTree = mini.get("leftTree");
    	leftTree.setTextField("menuName");
    	leftTree.setIdField("menuCode");
    	leftTree.setParentField("menuPcode");
    	leftTree.load(HOST_URL+'/'+url);

    	changeTitle(title);
    }
    function changeTitle(title){
    	if(!title){
    		title = "系统导航";
    	}
    	mini.get("main").updateRegion("west",{title:title});
    }
    /********************************************************/
    function showHyxttzWin(msg) {
        var win = mini.get("win1");

        var x = "right";
        var y = "bottom";

        $("#hydxtzMsg").html(msg);
        win.showAtPos(x, y);
    }
    
    function showHyxttzWin1(msg) {
        var win = mini.get("win2");

        //var x = "right";
        //var y = "bottom";
        var x = "center";
        var y = "middle";

        $("#hydxtzMsg1").html(msg);
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
            url: HOST_URL+"/rjwh/applyMsg/getByStatus1.do",
            type: 'post',
            dataType: 'json',
            contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (data) {
                if(data.flag){
                    hyxttzid = data.zid;
                    var zryxm = "<%=USERINFO_USERXM%>";

                    var msg = zryxm+"，您好！您现在有"+data.sum+"条处理：<br/>";
                    var num = parseInt(data.maintainApply.length);
                   if(num <= 3){
                       for (var i = 0; i < num ; i++) {
                           var projectId = data.maintainApply[i].projectId;
                           var projectName = data.maintainApply[i].projectName;
                           msg+="项目编号："+ projectId+"<br/>";
                           msg+="项目名称："+ projectName+"<br/>";
                       }
                   }else if(num > 3){
                       for (var i = 0; i < 3 ; i++) {
                           var projectId = data.maintainApply[i].projectId;
                           var projectName = data.maintainApply[i].projectName;
                           msg+="项目编号："+ projectId+"<br/>";
                           msg+="项目名称："+ projectName+"<br/>";
                       }
                       msg += "              更多..."
                   }
                    showHyxttzWin1(msg);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }


    function hyxttz_Reload1(){

        $.ajax({
            url: HOST_URL+"/rjwh/applyMsg/getByMsgStatus.do",
            type: 'post',
            dataType: 'json',
            contentType:'application/json;charset=UTF-8',
            cache: false,
            success: function (data) {
                if(data.flag){
                    var zryxm = "<%=USERINFO_USERXM%>";

                    var msg = zryxm+"，您好！您现在新增"+data.sum+"条待处理的审核<br/>";
                    title = "新消息";
                    //alert(msg);
                    showHyxttzWin(msg);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });

    }

//     $(function () {
<%--         if("<%=USERINFO_USERID%>" == "10001" || "<%=USERINFO_USERID%>" == "10002"){ --%>
//             hyxttz_Reload();
//             setInterval("hyxttz_Reload1()",1000*20);
//         }
//     });

//     var hyxttz_Reload = function(){
//         $.ajax({
//             url: HOST_URL+"/hygl/hyxttz/getWjsLastOne.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
//             cache: false,
//             success: function (data) {
//                 if(data){
//                 	hyxttzid = data.zid;
//                 	var zryxm = data.zryxm;
//                 	var ztznr = data.ztznr;
//                 	var zfssj = data.zfssj;
//                 	var msg = zryxm+"，您好！您现在有一条系统自动预警通知：<br/>";
//                 	msg+=ztznr+"（"+zfssj+"）。";
//                 	showHyxttzWin(msg);
//                 }
//
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//             }
//         });
//     }
//
//
//     function hyxttz_Reload1(){
//
//      	$.ajax({
//             url: HOST_URL+"/xmdb/xmgl/xmjbxx/countXmjdzt.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
//             cache: false,
//             success: function (data) {
//             	if(data){
//             		var yblwj = data.yblwj;//已办理完结
//             		var zzblz = data.zzblz;//正在办理
//             		var jjcqyj = data.jjcqyj;//接近超期
//             		var yjcqyj = data.yjcqyj;//已经超期
//             		var dbsj = data.dbsj;//待办事件
//             		var wdsj = data.wdsj;//未读事件
//             		var total = yblwj+zzblz+jjcqyj+yjcqyj;
//             		var jjcqPer = 0;
//             		if(total!=0){
//             			jjcqPer = (jjcqyj/total)*100;
//             		}
//             		jjcqPer = jjcqPer.toFixed(2);
//
//             		var jycqPer = 0;
//             		if(total!=0){
//             			jycqPer = (yjcqyj/total)*100;
//             		}
//             		jycqPer = jycqPer.toFixed(2);
//
// 	            	//var msg = "当前共有重大项目"+total+"件，其中接近超期"+jjcqyj+"件，占"+jjcqPer+"%；已经超期项目"+yjcqyj+"件，占"+jycqPer+"%,当前共有督办事件"+dbsj+"条，其中未读事件"+wdsj+"条";
// 	            	var msg = "";
// 	            	var url = "";
// 	            	var title = "";
//
// 	            	url = "xmdb/xmgl/xmss/listUi.do";
// 	            	title = "项目信息搜索";
// 	            	msg +="1.项目总数"+total+"件（<a href=\"javascript:;\" onclick=\"showWin('"+url+"','"+title+"');\">查看</a>）<br/>";
//
// 	            	url = "xmdb/xmgl/xmjbxx/list_jjcqUi.do";
// 	            	title = "临近预警";
// 	            	msg +="2.临近超期"+jjcqyj+"件，占"+jjcqPer+"%（<a href=\"javascript:;\" onclick=\"showWin('"+url+"','"+title+"');\">查看</a>）<br/>";
//
// 	            	url = "xmdb/xmgl/xmjbxx/list_yjcqUi.do";
// 	            	title = "超期预警";
// 	            	msg +="3.超期项目"+yjcqyj+"件，占"+jycqPer+"%（<a href=\"javascript:;\" onclick=\"showWin('"+url+"','"+title+"');\">查看</a>）<br/>";
//
// 	            	url = "xmdb/xmgl/xmdb/xmdbQue.do?dbck=ckWhf";
// 	            	title = "待回复意见";
// 	            	msg +="4.待回复意见"+data.dhfCount+"条（<a href=\"javascript:;\" onclick=\"showWin('"+url+"','"+title+"');\">查看</a>）<br/>";
//
// 	            	url = "xmdb/xmgl/xmdb/xmdbQue.do?dbck=ckYhf";
// 	            	title = "督办回复意见";
// 	            	msg +="5.督办回复意见"+data.yhfCount+"条（<a href=\"javascript:;\" onclick=\"showWin('"+url+"','"+title+"');\">查看</a>）<br/>";
//
// 	            	//alert(msg);
// 	            	showHyxttzWin1(msg);
//             	}
//
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//
//             }
//         });
//
//     }
//     /////////////////////////////////////////////////
//     function showHyxttzWin2(msg) {
//         var win = mini.get("win3");
//
//         var x = "right";
//         var y = "bottom";
//
//         $("#hydxtzMsg2").html(msg);
//         win.showAtPos(x, y);
//     }
//     function hyxttz_Reload2(){
// 	   	$.ajax({
// 	        url: HOST_URL+"/xmdb/xmgl/ldps/queryTk.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
// 	        cache: false,
// 	        success: function (data) {
// 	        	if(data && data.data && data.data.length>0){
// 	        		var d = data.data[0];
// 		           	var msg = "您有一条领导批示，内容："+d.fbnr+"(发布时间："+d.fbsj+")";
// 		            //alert(msg);
// 		            showHyxttzWin2(msg);
// 	        	}
//
// 	        },
// 	        error: function (jqXHR, textStatus, errorThrown) {
//
// 	        }
//        });
//     }
//     /////////////////////督办信息弹框////////////////////////////
//     function showHyxttzWin3(msg) {
//         var win = mini.get("win4");
//
//         var x = "right";
//         var y = "bottom";
//
//         $("#hydxtzMsg3").html(msg);
//         win.showAtPos(x, y);
//     }
//     function hyxttz_Reload3(){
// 	   	$.ajax({
// 	        url: HOST_URL+"/xmdb/xmgl/xmdb/xmdbQueryAllZt.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
// 	        cache: false,
// 	        success: function (data) {
// 	        	if(data && data.data && data.data.length>0){
// 		        	var d = data.data[0];
// 		           	var msg = "您有一条督办信息，内容："+d.qsnr+"(发布时间："+d.dbsj+")";
// 		            //alert(msg);
// 		            showHyxttzWin3(msg);
// 	        	}
// 	        },
// 	        error: function (jqXHR, textStatus, errorThrown) {
//
// 	        }
//        });
//     }
//
// /////////////////////答复信息弹框////////////////////////////
//     function showHyxttzWin4(msg) {
//         var win = mini.get("win5");
//
//         var x = "right";
//         var y = "bottom";
//
//         $("#hydxtzMsg4").html(msg);
//         win.showAtPos(x, y);
//     }
//     function hyxttz_Reload4(){
// 	   	$.ajax({
// 	   		url: HOST_URL+"/xmdb/xmgl/xmdb/xmdbQueryAllYhf.do",
// 			type: 'post',
// 		    dataType: 'json',
// 		    contentType:'application/json;charset=UTF-8',
// 	        cache: false,
// 	        success: function (data) {
// 	        	if(data && data.data && data.data.length>0){
// 		        	var d = data.data[0];
// 		           	var msg = "您有一条答复信息，内容："+d.hfnr+"(答复时间："+d.hfsj+")";
// 		            //alert(msg);
// 		            showHyxttzWin4(msg);
// 	        	}
//
// 	        },
// 	        error: function (jqXHR, textStatus, errorThrown) {
//
// 	        }
//        });
//     }
    ////////////////////////////////

    
    changeMenu();
    /********************************************************/
    
    /********************************************************/

</script>

<jsp:include page="/common_pojie.jsp"></jsp:include>