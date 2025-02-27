<%@page import="java.util.*"%>
<%@page import="com.dt.common.utils.SystemConstant"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/logincheck.jsp" %>
<%@include file="/logininfo.jsp" %>
<%@page import="com.dt.common.db.ConnDataBase"%>
<%
   ConnDataBase conn = new ConnDataBase();
   String lc = (String)session.getAttribute("LCGL");

   int[] Wdxsjd = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
   List listWdxsjd = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '0' and ((year(CURRENT_DATE) = year(zxsbsj) and month(CURRENT_DATE) > month(zxsbsj)) or year(CURRENT_DATE) > year(zxsbsj) or zxsbsj is null) group by b.sjmc");
   if(listWdxsjd.size()>0){
	   for(int i=0;i<listWdxsjd.size();i++){
		  Map map = (HashMap)listWdxsjd.get(i);
		  String bmmc = map.get("sjmc").toString();
		  String SL = map.get("sl").toString();
		  if("石家庄市".equals(bmmc)){
			  Wdxsjd[0] = Integer.parseInt(SL);
	      }
		  if("保定市".equals(bmmc)){
			  Wdxsjd[1] = Integer.parseInt(SL);
	      }
		  if("邯郸市".equals(bmmc)){
			  Wdxsjd[2] = Integer.parseInt(SL);
	      }
		  if("邢台市".equals(bmmc)){
			  Wdxsjd[3] = Integer.parseInt(SL);
	      }
		  if("沧州市".equals(bmmc)){
			  Wdxsjd[4] = Integer.parseInt(SL);
	      }
		  if("衡水市".equals(bmmc)){
			  Wdxsjd[5] = Integer.parseInt(SL);
	      }
		  if("张家口市".equals(bmmc)){
			  Wdxsjd[6] = Integer.parseInt(SL);
	      }
		  if("秦皇岛市".equals(bmmc)){
			  Wdxsjd[7] = Integer.parseInt(SL);
	      }
		  if("承德市".equals(bmmc)){
			  Wdxsjd[8] = Integer.parseInt(SL);
	      }
		  if("唐山市".equals(bmmc)){
			  Wdxsjd[9] = Integer.parseInt(SL);
	      }
		  if("廊坊市".equals(bmmc)){
			  Wdxsjd[10] = Integer.parseInt(SL);
	      }
		  if("辛集市".equals(bmmc)){
			  Wdxsjd[11] = Integer.parseInt(SL);
	      }
		  if("定州市".equals(bmmc)){
			  Wdxsjd[12] = Integer.parseInt(SL);
	      }
		}
   }
   
   int[] Ydxsjd = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
   List listYdxsjd = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '0' and year(CURRENT_DATE) = year(zxsbsj) and month(CURRENT_DATE) = month(zxsbsj) group by b.sjmc");
   if(listYdxsjd.size()>0){
	   for(int i=0;i<listYdxsjd.size();i++){
		  Map map = (HashMap)listYdxsjd.get(i);
		  String bmmc = map.get("sjmc").toString();
		  String SL = map.get("sl").toString();
		  if("石家庄市".equals(bmmc)){
			  Ydxsjd[0] = Integer.parseInt(SL);
	      }
		  if("保定市".equals(bmmc)){
			  Ydxsjd[1] = Integer.parseInt(SL);
	      }
		  if("邯郸市".equals(bmmc)){
			  Ydxsjd[2] = Integer.parseInt(SL);
	      }
		  if("邢台市".equals(bmmc)){
			  Ydxsjd[3] = Integer.parseInt(SL);
	      }
		  if("沧州市".equals(bmmc)){
			  Ydxsjd[4] = Integer.parseInt(SL);
	      }
		  if("衡水市".equals(bmmc)){
			  Ydxsjd[5] = Integer.parseInt(SL);
	      }
		  if("张家口市".equals(bmmc)){
			  Ydxsjd[6] = Integer.parseInt(SL);
	      }
		  if("秦皇岛市".equals(bmmc)){
			  Ydxsjd[7] = Integer.parseInt(SL);
	      }
		  if("承德市".equals(bmmc)){
			  Ydxsjd[8] = Integer.parseInt(SL);
	      }
		  if("唐山市".equals(bmmc)){
			  Ydxsjd[9] = Integer.parseInt(SL);
	      }
		  if("廊坊市".equals(bmmc)){
			  Ydxsjd[10] = Integer.parseInt(SL);
	      }
		  if("辛集市".equals(bmmc)){
			  Ydxsjd[11] = Integer.parseInt(SL);
	      }
		  if("定州市".equals(bmmc)){
			  Ydxsjd[12] = Integer.parseInt(SL);
	      }
		}
   }
	
   int[] Ywcwxh = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
   List listYwcwxh = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '1' group by b.sjmc");
   if(listYwcwxh.size()>0){
	   for(int i=0;i<listYwcwxh.size();i++){
		  Map map = (HashMap)listYwcwxh.get(i);
		  String bmmc = map.get("sjmc").toString();
		  String SL = map.get("sl").toString();
		  if("石家庄市".equals(bmmc)){
			  Ywcwxh[0] = Integer.parseInt(SL);
	      }
		  if("保定市".equals(bmmc)){
			  Ywcwxh[1] = Integer.parseInt(SL);
	      }
		  if("邯郸市".equals(bmmc)){
			  Ywcwxh[2] = Integer.parseInt(SL);
	      }
		  if("邢台市".equals(bmmc)){
			  Ywcwxh[3] = Integer.parseInt(SL);
	      }
		  if("沧州市".equals(bmmc)){
			  Ywcwxh[4] = Integer.parseInt(SL);
	      }
		  if("衡水市".equals(bmmc)){
			  Ywcwxh[5] = Integer.parseInt(SL);
	      }
		  if("张家口市".equals(bmmc)){
			  Ywcwxh[6] = Integer.parseInt(SL);
	      }
		  if("秦皇岛市".equals(bmmc)){
			  Ywcwxh[7] = Integer.parseInt(SL);
	      }
		  if("承德市".equals(bmmc)){
			  Ywcwxh[8] = Integer.parseInt(SL);
	      }
		  if("唐山市".equals(bmmc)){
			  Ywcwxh[9] = Integer.parseInt(SL);
	      }
		  if("廊坊市".equals(bmmc)){
			  Ywcwxh[10] = Integer.parseInt(SL);
	      }
		  if("辛集市".equals(bmmc)){
			  Ywcwxh[11] = Integer.parseInt(SL);
	      }
		  if("定州市".equals(bmmc)){
			  Ywcwxh[12] = Integer.parseInt(SL);
	      }
		}
   }

   int[] Ywcyxh = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
   List listYwcyxh = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '2' group by b.sjmc");
   if(listYwcyxh.size()>0){
	   for(int i=0;i<listYwcyxh.size();i++){
		  Map map = (HashMap)listYwcyxh.get(i);
		  String bmmc = map.get("sjmc").toString();
		  String SL = map.get("sl").toString();
		  if("石家庄市".equals(bmmc)){
			  Ywcyxh[0] = Integer.parseInt(SL);
	      }
		  if("保定市".equals(bmmc)){
			  Ywcyxh[1] = Integer.parseInt(SL);
	      }
		  if("邯郸市".equals(bmmc)){
			  Ywcyxh[2] = Integer.parseInt(SL);
	      }
		  if("邢台市".equals(bmmc)){
			  Ywcyxh[3] = Integer.parseInt(SL);
	      }
		  if("沧州市".equals(bmmc)){
			  Ywcyxh[4] = Integer.parseInt(SL);
	      }
		  if("衡水市".equals(bmmc)){
			  Ywcyxh[5] = Integer.parseInt(SL);
	      }
		  if("张家口市".equals(bmmc)){
			  Ywcyxh[6] = Integer.parseInt(SL);
	      }
		  if("秦皇岛市".equals(bmmc)){
			  Ywcyxh[7] = Integer.parseInt(SL);
	      }
		  if("承德市".equals(bmmc)){
			  Ywcyxh[8] = Integer.parseInt(SL);
	      }
		  if("唐山市".equals(bmmc)){
			  Ywcyxh[9] = Integer.parseInt(SL);
	      }
		  if("廊坊市".equals(bmmc)){
			  Ywcyxh[10] = Integer.parseInt(SL);
	      }
		  if("辛集市".equals(bmmc)){
			  Ywcyxh[11] = Integer.parseInt(SL);
	      }
		  if("定州市".equals(bmmc)){
			  Ywcyxh[12] = Integer.parseInt(SL);
	      }
		}
   }

   String[] Rwzs = new String[13];
   String[] Rwmc = new String[13];
   List listRwzs = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' group by b.sjmc order by sl desc");
   if(listRwzs.size()>0){
	   for(int i=0;i<listRwzs.size();i++){
		  Map map = (HashMap)listRwzs.get(i);
		  Rwmc[i] = map.get("sjmc").toString();
		  Rwzs[i] = map.get("sl").toString();
		}
   }
   
   String[] Yjzs = new String[13];
   String[] Yjmc = new String[13];
   List listYjzs = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.zgsx < NOW() and (a.wcbj != '2' or a.xhsj > a.zgsx) group by b.sjmc order by sl desc");
   if(listYjzs.size()>0){
	   for(int i=0;i<listYjzs.size();i++){
		  Map map = (HashMap)listYjzs.get(i);
		  Yjmc[i] = map.get("sjmc").toString();
		  Yjzs[i] = map.get("sl").toString();
		}
   }
   
   String sl1="0",sl2="0",sl3="0",sl4="0",sl5="0";
   List list1 = conn.executeQuery("select count(*) as sl from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"'");
   for(int i=0;i<list1.size();i++){
	  Map map = (HashMap)list1.get(i);
	  sl1 = map.get("sl").toString();
   }
   List list2 = conn.executeQuery("select count(*) as sl from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '0' and ((year(CURRENT_DATE) = year(zxsbsj) and month(CURRENT_DATE) > month(zxsbsj)) or year(CURRENT_DATE) > year(zxsbsj) or zxsbsj is null)");
   for(int i=0;i<list2.size();i++){
	  Map map = (HashMap)list2.get(i);
	  sl2 = map.get("sl").toString();
   }
   List list3 = conn.executeQuery("select count(*) as sl from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '0' and year(CURRENT_DATE) = year(zxsbsj) and month(CURRENT_DATE) = month(zxsbsj)");
   for(int i=0;i<list3.size();i++){
	  Map map = (HashMap)list3.get(i);
	  sl3 = map.get("sl").toString();
   }
   List list4 = conn.executeQuery("select count(*) as sl from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '1'");
   for(int i=0;i<list4.size();i++){
	  Map map = (HashMap)list4.get(i);
	  sl4 = map.get("sl").toString();
   }
   List list5 = conn.executeQuery("select count(*) as sl from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' and a.wcbj = '2'");
   for(int i=0;i<list5.size();i++){
	  Map map = (HashMap)list5.get(i);
	  sl5 = map.get("sl").toString();
   }
   String path=request.getContextPath();
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="zh">

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
		<title>河北省生态环境保护督察支撑系统</title>
		<link rel="stylesheet" type="text/css" href="css/qbyp.css" />
		<script src="js/vue.min.js"></script>
		<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
    	<script src="js/echarts.min.js"></script>
	</head>

	<body>
		<div class="i_main">
			<div class="i_header">
				<div class="i_header_left">
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li>&nbsp;</li>
					</ul>
				</div>
				<div class="i_header_cont">
					<p class="tit">河北省生态环境保护督察支撑系统</p>
		            <div id="clock">
		                <p class="date">{{ date }}</p>
		                <p class="time">{{ time }}</p>
		            </div>
		            <script>
		                var clock = new Vue({
		                    el: '#clock',
		                    data: {
		                        time: '',
		                        date: ''
		                    }
		                });

		                var week = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
		                var timerID = setInterval(updateTime, 1000);
		                updateTime();
		                function updateTime() {
		                    var cd = new Date();
		                    clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
		                    clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
		                };

		                function zeroPadding(num, digit) {
		                    var zero = '';
		                    for(var i = 0; i < digit; i++) {
		                        zero += '0';
		                    }
		                    return (zero + num).slice(-digit);
		                }
		            </script>
				</div>
				<div class="i_header_right">
					<ul>
<!-- 			            <li><a href="javascript:;">总</a></li> -->
<!-- 			            <li><a href="javascript:;">石</a></li> -->
<!-- 			            <li><a href="javascript:;">承</a></li> -->
<!-- 			            <li><a href="javascript:;">张</a></li> -->
<!-- 			            <li><a href="javascript:;">秦</a></li> -->
<!-- 			            <li><a href="javascript:;">唐</a></li> -->
<!-- 			            <li><a href="javascript:;">廊</a></li> -->
<!-- 			            <li><a href="javascript:;">保</a></li> -->
<!-- 			            <li><a href="javascript:;">沧</a></li> -->
<!-- 			            <li><a href="javascript:;">衡</a></li> -->
<!-- 			            <li><a href="javascript:;">邢</a></li> -->
<!-- 			            <li><a href="javascript:;">邯</a></li> -->
					</ul>
				</div>
				<div class="top_right_home">
            		<a href="<%=path%>/index.jsp"><img src="./img/home.png" /></a>  
               </div>
			</div>
			<div class="map" id="main">
				<div class="m_top">
					<div class="m_topl d">
						<div class="section-title">
			                <label>未达序时进度</label>
			                <!-- <div class="xq">
			                    <div class="container">
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup(0)"><img src="images/icon.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup(1)"><img src="images/icon02.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup(2)"><img src="images/icon03.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup(3)"><img src="images/icon04.png"/></a>
			                        <div class="popup">
			                            <div id="popup_name" class="popup_block rightTop border">
			                                <div id="main01"></div>
			                                <div id="msg"></div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <script src="js/qbyp-eltq.js" type="text/javascript"></script> -->
			            </div>
						<div id="m_topl">
						</div>
					</div>
					<div class="m_top_title d">
						<ul>
							<li>
								<div class="m_top_cont">
									任务总数
									<p><%=sl1 %></p>
								</div>
							</li>
							<li>
								<div class="m_top_cont">
									未达序时进度
									<p><%=sl2 %></p>
								</div>
							</li>
							<li>
								<div class="m_top_cont">
									已达序时进度
									<p><%=sl3 %></p>
								</div>
							</li>
							<li>
								<div class="m_top_cont">
									已完成未销号
									<p><%=sl4 %></p>
								</div>
							</li>
							<li>
								<div class="m_top_cont">
									已完成已销号
									<p><%=sl5 %></p>
								</div>
							</li>
						</ul>
					</div>
					<div class="m_topl d" style="float: right;">
						<p class="title">任务总数</p>
						<div class="ranking">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>单位</th>
									<th>总数</th>
								</tr>
								<tr class="tr">
									<td>1</td>
									<td class="r_title"><%=Rwmc[0] ==null ? '无': Rwmc[0] %></td>
									<td><%=Rwzs[0]==null ? 0:Rwzs[0]%></td>
								</tr>
								<tr class="tr">
									<td>2</td>
									<td class="r_title"><%=Rwmc[1] ==null ? '无': Rwmc[1] %></td>
									<td><%=Rwzs[1]==null ? 0:Rwzs[1]%></td>
								</tr>
								<tr class="tr">
									<td>3</td>  
									<td class="r_title"><%=Rwmc[2] ==null ? '无': Rwmc[2] %></td>
									<td><%=Rwzs[2]==null ? 0:Rwzs[2]%></td>
								</tr>
								<tr class="tr">
									<td>4</td>
									<td class="r_title"><%=Rwmc[3] ==null ? '无': Rwmc[3] %></td>
									<td><%=Rwzs[3]==null ? 0:Rwzs[3]%></td>
								</tr>
								<tr class="tr">
									<td>5</td>
									<td class="r_title"><%=Rwmc[4] ==null ? '无': Rwmc[4] %></td>
									<td><%=Rwzs[4]==null ? 0:Rwzs[4]%></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="m_middle">
					<div class="m_topl d">
						<div class="section-title">
			                <label>已达序时进度</label>
			                <!-- <div class="xq">
			                    <div class="container">
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup02(0)"><img src="images/icon.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup02(1)"><img src="images/icon02.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup02(2)"><img src="images/icon03.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup02(3)"><img src="images/icon04.png"/></a>
			                        <div class="popup">
			                            <div id="popup_name02" class="popup_block rightTop border">
			                                <div id="main02"></div>
			                                <div id="msg02"></div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <script src="js/qbyp-jtzx.js" type="text/javascript"></script> -->
			            </div>
						<div id="order">
						</div>
					</div>
					<div class="m_topl d" style="float: right;">
						<div class="section-title">
			                <label>已完成未销号</label>
			                <!-- <div class="xq">
			                    <div class="container">
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup04(0)"><img src="images/icon.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup04(1)"><img src="images/icon02.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup04(2)"><img src="images/icon03.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup04(3)"><img src="images/icon04.png"/></a>
			                        <div class="popup popup_right">
			                            <div id="popup_name04" class="popup_block rightTop border">
			                                <div id="main04"></div>
			                                <div id="msg04"></div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <script src="js/qbyp-clgf.js" type="text/javascript"></script> -->
			            </div>
						<div id="consume_pie">
						</div>
					</div>
				</div>
				<div class="m_bottom">
					<div class="m_topl d">
						<div class="section-title">
			                <label>超期预警数量</label>
			                <!-- <div class="xq">
			                    <div class="container">
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup03(0)"><img src="images/icon.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup03(1)"><img src="images/icon02.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup03(2)"><img src="images/icon03.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup03(3)"><img src="images/icon04.png"/></a>
			                        <div class="popup popup_right">
			                            <div id="popup_name03" class="popup_block rightTop border">
			                                <div id="main03"></div>
			                                <div id="msg03"></div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <script src="js/qbyp-jtts.js" type="text/javascript"></script> -->
			            </div>
						<div class="situation">
							<div class="situation_list">
								<span>排名第一</span>
								<span><%=Yjmc[0]==null ? '无': Yjmc[0]%></span>
								<span style="color:#eada1c;"><%=Yjzs[0]==null ? 0:Yjzs[0]%></span>
							</div>
							<div class="situation_list">
								<span>排名第二</span>
								<span><%=Yjmc[1]==null ? '无': Yjmc[1]%></span>
								<span style="color:#eada1c;"><%=Yjzs[1]==null ? 0:Yjzs[1]%></span>
							</div>
							<div class="situation_list">
								<span>排名第三</span>
								<span><%=Yjmc[2]==null ? '无': Yjmc[2]%></span>
								<span style="color:#eada1c;"><%=Yjzs[2]==null ? 0:Yjzs[2]%></span>
							</div>
							<div class="situation_list">
								<span>排名第四</span>
								<span><%=Yjmc[3]==null ? '无': Yjmc[3]%></span>
								<span style="color:#eada1c;"><%=Yjzs[3]==null ? 0:Yjzs[3]%></span>
							</div>
						</div>
					</div>
					<div class="m_bottom_middle d">
						<table border="0" cellspacing="0" cellpadding="0" class="d">
							<tr>
								<th style="width: 10%;">地市</th>
								<th style="width: 50%;">问题描述</th>
								<th style="width: 20%;">督导单位</th>
								<th style="width: 20%;">整改时限</th>
							</tr>
							<%
							List cxwt=conn.executeQuery("select a.wtms,a.dddw,a.zgsx,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' limit 4");
							if(cxwt.size()>0){
								for(int a=0;a<cxwt.size();a++){
									Map map=(HashMap)cxwt.get(a);
									String shi=map.get("sjmc").toString();
									String wt=map.get("wtms").toString();
									if(wt.length()>35){
										wt=wt.substring(0, 35)+"...";
									}
									String dddw=map.get("dddw").toString();
									String zgsx=map.get("zgsx").toString();


							%>
							<tr class="m_bottom_title">
								<td><%=shi %></td>
								<td><%=wt %></td>
								<td><%=dddw %></td>
								<td><%=zgsx %></td>
							</tr>
							
							<%}} %>
<!-- 							<tr class="m_bottom_title"> -->
<!-- 								<td>18:45</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>18:45</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="m_bottom_title"> -->
<!-- 								<td>18:45</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>18:45</td> -->
<!-- 							</tr> -->
<!-- 							<tr class="m_bottom_title"> -->
<!-- 								<td>18:45</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>指令内容指令内容指令内容</td> -->
<!-- 								<td>18:45</td> -->
<!-- 							</tr> -->
						</table>

					</div>
					<div class="m_topl d" style="float: right;">
						<div class="section-title">
			                <label>已完成已销号</label>
			                <!-- <div class="xq">
			                    <div class="container">
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup05(0)"><img src="images/icon.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup05(1)"><img src="images/icon02.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup05(2)"><img src="images/icon03.png"/></a>
			                        <a rel="popup_name" class="btn btn-success btn-lg" href="#" role="button" onclick="popup05(3)"><img src="images/icon04.png"/></a>
			                        <div class="popup popup_right">
			                            <div id="popup_name05" class="popup_block rightTop border">
			                                <div id="main05"></div>
			                                <div id="msg05"></div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			                <script src="js/qbyp-jtsg.js" type="text/javascript"></script> -->
			            </div>
						<div id="accident"></div>
					</div>
				</div>
				<div class="ditu">
					<div id="ditu">
					</div>
					<div class="pos">
						<div class="d_bubble">
							<ul>
								<!--<div class="element animated wobble">wobble</div>-->
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
								<li class="element animated wobble"></li>
							</ul>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</body>
	<script src="js/jquery-3.6.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	//未达序时进度
	$(function() {
		var chartDom = document.getElementById('m_topl');
		var myChart = echarts.init(chartDom);
		var option;
		option = {
		    tooltip: {
			    trigger: 'axis',
			    axisPointer: {
			      type: 'shadow'
			    },
				formatter: "{c}个"
			},
			xAxis: {
				type: 'category',
				data: ['石家庄', '保定', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '承德', '唐山', '廊坊', '辛集', '定州'],
				axisLabel: {
					rotate: 40,
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: '8',
					}
				},
			},
			yAxis: {
				type: 'value',
				axisLabel: {
					show: true,
					textStyle: {
						color: '#fff',
					}
				},
				splitLine: {
					show: true,
					lineStyle: {
						color: ['#315070'],
						width: 1,
						type: 'solid'
					}　　
				}
			},
			series: [{
				barWidth: 15,
				data: [<%=Wdxsjd[0]%>,<%=Wdxsjd[1]%>,<%=Wdxsjd[2]%>,<%=Wdxsjd[3]%>,<%=Wdxsjd[4]%>,<%=Wdxsjd[5]%>,<%=Wdxsjd[6]%>,<%=Wdxsjd[7]%>,<%=Wdxsjd[8]%>,<%=Wdxsjd[9]%>,<%=Wdxsjd[10]%>,<%=Wdxsjd[11]%>,<%=Wdxsjd[12]%>],
				type: 'bar',
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
							offset: 0,
							color: "#364834" // 0% 处的颜色
						}, {
							offset: 0.4,
							color: "#aaaa2f" // 60% 处的颜色
						}, {
							offset: 1,
							color: "#d6cb2d" // 100% 处的颜色
						}], false)
					}
				},
			}, ]
		};
		option && myChart.clear();
		option && myChart.setOption(option);
		var timer = window.setInterval(function() {
			myChart.clear();
			refreshData(option);
		}, 5000);

		function refreshData(data) {
			option && myChart.setOption(data);
		}
	})
	//已达序时进度
	$(function() {
		var chartDom = document.getElementById('order');
		var myChart = echarts.init(chartDom);
		var option;
		option = {
		   tooltip: {
			    trigger: 'axis',
			    axisPointer: {
			      type: 'shadow'
			    }
		,
		formatter: "{c}个"
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data: ['石家庄', '保定', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '承德', '唐山', '廊坊', '辛集', '定州'],
				axisLabel: {
					rotate: 40,
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: '8',
					}
				},
			},
			yAxis: {
				type: 'value',
				axisLabel: {
					show: true,
					textStyle: {
						color: '#fff',
					}
				},
				splitLine: {
					show: true,
					lineStyle: {
						color: ['#315070'],
						width: 1,
						type: 'solid'
					}　　
				}
			},
			series: [{
				data: [<%=Ydxsjd[0]%>,<%=Ydxsjd[1]%>,<%=Ydxsjd[2]%>,<%=Ydxsjd[3]%>,<%=Ydxsjd[4]%>,<%=Ydxsjd[5]%>,<%=Ydxsjd[6]%>,<%=Ydxsjd[7]%>,<%=Ydxsjd[8]%>,<%=Ydxsjd[9]%>,<%=Ydxsjd[10]%>,<%=Ydxsjd[11]%>,<%=Ydxsjd[12]%>],
				type: 'line',
				areaStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{

							offset: 0,
							color: 'rgba(43,204,255,0.39)'
						}, {
							offset: .34,
							color: 'rgba(43,204,255,0.5)'
						}, {
							offset: 1,
							color: 'rgba(43,204,255,1)'
						}])
					}
				},

				itemStyle: {
					normal: {
						color: '#15678e', //改变折线点的颜色
						lineStyle: {
							color: '#15678e' //改变折线颜色
						}
					}
				},
			}]
		};
		option && myChart.clear();
		option && myChart.setOption(option);
		var timer = window.setInterval(function() {
			myChart.clear();
			refreshData(option);
		}, 5000);

		function refreshData(data) {
			option && myChart.setOption(data);
		}

	})

	//已完成未销号
	$(function() {
		loadRingChart(); //加载半圆环形图
		//加载半圆环形图
		function loadRingChart() {
			var myChart = echarts.init(document.getElementById('consume_pie')); //图表的ID
			option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c}个"
				},
				legend: {
					x: 'center',
					y: '15%',
					data: ['石家庄', '保定', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '承德', '唐山', '廊坊', '辛集', '定州'],
					icon: 'circle',
					textStyle: {
						color: '#fff',
					}
				},
				calculable: true,
				series: [{
					name: '',
					type: 'pie',
					//起始角度，支持范围[0, 360]
					startAngle: 0,
					//饼图的半径，数组的第一项是内半径，第二项是外半径
					radius: [41, 100.75],
					//支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
					center: ['50%', '40%'],
					//是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
					// 'radius' 面积展现数据的百分比，半径展现数据的大小。
					//  'area' 所有扇区面积相同，仅通过半径展现数据大小
					roseType: 'area',
					//是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
					avoidLabelOverlap: false,
					label: {
						normal: {
							show: true,
							formatter: '{c}'
						},
						emphasis: {
							show: true
						}
					},
					labelLine: {
						normal: {
							show: true,
							length2: 1,
						},
						emphasis: {
							show: true
						}
					},
					data: [{
							value: <%=Ywcwxh[0]%>,
							name: '石家庄',
							itemStyle: {
								normal: {
									color: '#4997F3'
								}
							}
						},
						{
							value: <%=Ywcwxh[1]%>,
							name: '保定',
							itemStyle: {
								normal: {
									color: '#72BCBE'
								}
							}
						},
						{
							value: <%=Ywcwxh[2]%>,
							name: '邯郸',
							itemStyle: {
								normal: {
									color: '#7AB666'
								}
							}
						},
						{
							value: <%=Ywcwxh[3]%>,
							name: '邢台',
							itemStyle: {
								normal: {
									color: '#EFCA56'
								}
							}
						},
						{
							value: <%=Ywcwxh[4]%>,
							name: '沧州',
							itemStyle: {
								normal: {
									color: '#D0656E'
								}
							}
						},
						{
							value: <%=Ywcwxh[5]%>,
							name: '衡水',
							itemStyle: {
								normal: {
									color: '#6F60D5'
								}
							}
						},
						{
							value: <%=Ywcwxh[6]%>,
							name: '张家口',
							itemStyle: {
								normal: {
									color: '#103777'
								}
							}
						},
						{
							value: <%=Ywcwxh[7]%>,
							name: '秦皇岛',
							itemStyle: {
								normal: {
									color: '#3AAA4A'
								}
							}
						},
						{
							value: <%=Ywcwxh[8]%>,
							name: '承德',
							itemStyle: {
								normal: {
									color: '#19A9D4'
								}
							}
						},
						{
							value: <%=Ywcwxh[9]%>,
							name: "唐山",
							itemStyle: {
								normal: {
									color: '#FFE822'
								}
							}
						},
						{
							value: <%=Ywcwxh[10]%>,
							name: "廊坊",
							itemStyle: {
								normal: {
									color: '#456296'
								}
							}
						},
						{
							value: <%=Ywcwxh[11]%>,
							name: "辛集",
							itemStyle: {
								normal: {
									color: '#5555FF '
								}
							}
						},
						{
							value: <%=Ywcwxh[12]%>,
							name: "定州",
							itemStyle: {
								normal: {
									color: '#33FFFF'
								}
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
						{
							value: 0,
							name: "",
							itemStyle: {
								normal: {
									color: 'transparent'
								}
							},
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
					]
				}]
			};
			myChart.setOption(option);
			window.addEventListener("resize", function() {
				myChart.resize();
			});
		}

	})
	$(function() {
		<%
		   List listDtrwzs = conn.executeQuery("select count(*) as sl,b.sjmc from t_hbdb_zgfamx a,t_hbdb_zgfa b where a.glid = b.id and b.lc = '"+lc+"' group by b.sjmc order by sl desc");
		   String SJZ="0";
		   String CD="0";
		   String ZJK="0";
		   String QHD="0";
		   String TS="0";
		   String LF="0";
		   String BD="0";
		   String CZ="0";
		   String HS="0";
		   String XT="0";
		   String HD="0";
		   if(listDtrwzs.size()>0){
			   for(int i=0;i<listDtrwzs.size();i++){
				  Map map = (HashMap)listDtrwzs.get(i);
				  String bmmc = map.get("sjmc").toString();
				  String SL = map.get("sl").toString();
				  if("石家庄市".equals(bmmc)){
					  SJZ=SL;
			      }
				  if("保定市".equals(bmmc)){
					  BD=SL;
			      }
				  if("邯郸市".equals(bmmc)){
					  HD=SL;
			      }
				  if("邢台市".equals(bmmc)){
					  XT=SL;
			      }
				  if("沧州市".equals(bmmc)){
					  CZ=SL;
			      }
				  if("衡水市".equals(bmmc)){
					  HS=SL;
			      }
				  if("张家口市".equals(bmmc)){
					  ZJK=SL;
			      }
				  if("秦皇岛市".equals(bmmc)){
					  QHD=SL;
			      }
				  if("承德市".equals(bmmc)){
					  CD=SL;
			      }
				  if("唐山市".equals(bmmc)){
					  TS=SL;
			      }
				  if("廊坊市".equals(bmmc)){
					  LF=SL;
			      }
			   }
		   }
		%>
		var chart = echarts.init(document.getElementById('ditu'));
		$.getJSON('css/hebei.json', function(data) {
			echarts.registerMap("河北省", data);
			var d = [];
			for(var i = 0; i < data.features.length; i++) {
				var zhen="0";
				if("保定市"==data.features[i].properties.name){
					zhen=<%=BD%>;
				}
				if("石家庄市"==data.features[i].properties.name){
					zhen=<%=SJZ%>;
				}
				if("承德市"==data.features[i].properties.name){
					zhen=<%=CD%>;
				}
				if("张家口市"==data.features[i].properties.name){
					zhen=<%=ZJK%>;
				}
				if("秦皇岛市"==data.features[i].properties.name){
					zhen=<%=QHD%>;
				}
				if("唐山市"==data.features[i].properties.name){
					zhen=<%=TS%>;
				}
				if("沧州市"==data.features[i].properties.name){
					zhen=<%=CZ%>;
				}
				if("衡水市"==data.features[i].properties.name){
					zhen=<%=HS%>;
				}
				if("邢台市"==data.features[i].properties.name){
					zhen=<%=XT%>;
				}
				if("邯郸市"==data.features[i].properties.name){
					zhen=<%=HD%>;
				}
				if("廊坊市"==data.features[i].properties.name){
					zhen=<%=LF%>;
				}
				d.push({
					name: data.features[i].properties.name,
					value:zhen
				})
			}
			renderMap("河北省", d)
		});
		var option = {
			title: {
				left: 'center',
				textStyle: {
					color: '#fff',
					fontSize: 16,
					fontWeight: 'normal',
					fontFamily: "Microsoft YaHei"
				},
				subtextStyle: {
					color: '#ccc',
					fontSize: 13,
					fontWeight: 'normal',
					fontFamily: "Microsoft YaHei"
				}
			},
			tooltip: {
				trigger: 'item',
				formatter: '{b}:{c}个'
			},
// 			toolbox: {
// 				show: true,
// 				orient: 'vertical',
// 				left: 'right',
// 				top: 'center',
// 				feature: {
// 					dataView: {
// 						readOnly: false
// 					}
// 				},
// 				iconStyle: {
// 					normal: {
// 						color: '#fff'
// 					}
// 				}
// 			},
		};

		function renderMap(map, data) {
			option.title.subtext = map;
			option.geo = {
					map: '河北省',
					aspectScale: 0.65, //长宽比
					zoom: 1.1,
					roam: false,
					itemStyle: {
						normal: {
							areaColor: '#1b4a88',
							shadowColor: '#082c65',
							shadowOffsetX: 0,
							shadowOffsetY: 25,
						},
						emphasis: {
							areaColor: '#2AB8FF',
							borderWidth: 0,
							color: 'green',
							label: {
								show: false,
							},
						},
					},
				},
				option.series = [{
					name: map,
					type: 'map',
					mapType: map,
					roam: false,
					nameMap: {},
					label: {
						normal: {
							show: true,
							textStyle: {
								color: '#fff',
								fontSize: 13
							}
						},
						emphasis: {
							show: true,
							textStyle: {
								color: '#fff',
								fontSize: 13
							}
						}
					},
					itemStyle: {
						normal: {
							areaColor: '#0c274b',
							borderColor: '#1ccdfa',
							shadowOffsetx: 0,
						},
						emphasis: {
							areaColor: 'darkorange'
						}
					},
					data: data
				}];
			chart.clear();
			chart.setOption(option);
			chart.on('click', function (params) {
				var sjmc = params.data.name;
				var iWidth = 1400; //弹出窗口的宽度
				var iHeight = 700; //弹出窗口的高度
				var iTop = (window.screen.availHeight - 30 - iHeight)/2; //获得窗口的垂直位置
				var iLeft = (window.screen.availWidth - 10 - iWidth)/2; //获得窗口的水平位置
				window.open("<%=request.getContextPath()%>/hbdb/zgfa/listsb.do?bs=0&sjmc="+sjmc,
						   "_blank", "height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft);
			});
			var timer = window.setInterval(function() {
				chart.clear();
				refreshDatas(option);
			}, 10000);
			function refreshDatas(data) {
				option && chart.setOption(data);
			}
		}
	})
	//已完成已销号
	$(function() {
		var chart = echarts.init(document.getElementById('accident'));
		option = {
			tooltip: {
			    trigger: 'axis',
			    axisPointer: {
			      type: 'shadow'
			    }
		,
		formatter: "{c}个"
			},
			xAxis: {
				type: 'category',
				data: ['石家庄', '保定', '邯郸', '邢台', '沧州', '衡水', '张家口', '秦皇岛', '承德', '唐山', '廊坊', '辛集', '定州'],
				axisLabel: {
					rotate: 40,
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: '8',
					}
				},
			},
			yAxis: {
				type: 'value',
				axisLabel: {
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: '8',
					}
				},
				splitLine: {
					show: true,
					lineStyle: {
						color: ['#315070'],
						width: 1,
						type: 'solid'
					}　　
				}
			},
			series: [{
				data: [<%=Ywcyxh[0]%>,<%=Ywcyxh[1]%>,<%=Ywcyxh[2]%>,<%=Ywcyxh[3]%>,<%=Ywcyxh[4]%>,<%=Ywcyxh[5]%>,<%=Ywcyxh[6]%>,<%=Ywcyxh[7]%>,<%=Ywcyxh[8]%>,<%=Ywcyxh[9]%>,<%=Ywcyxh[10]%>,<%=Ywcyxh[11]%>,<%=Ywcyxh[12]%>],
				type: 'bar',
				itemStyle: {
					normal: {　　　　　　　　 //这里是重点
						color: '#4997f3'
					}
				}
			}]
		};
		option && chart.setOption(option);
	})
	$(function() {
		var chart = echarts.init(document.getElementById('jindu'));
		option = {
			color: ['#40d738', '#2235d4'],
			tooltip: {
				show: false
			},
			series: [{
				name: '任务进度',
				type: 'pie',
				radius: ['60%', '70%'],
				label: {
					normal: {
						position: 'center'
					}
				},
				hoverAnimation: false,
				data: [{
					value: 20,
					name: '任务进度',
					itemStyle: {
						normal: {
							// color: {
							//     type: 'linear',
							//     x: 0,
							//     y: 0,
							//     x2: 0,
							//     y2: 1,
							//     colorStops: [{
							//         offset: 0, color: '#40d738' // 0% 处的颜色
							//     }, {
							//         offset: 1, color: '#2235d4' // 100% 处的颜色
							//     }],
							//     globalCoord: false // 缺省为 false
							// }
							// }
							//                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
							//                         offset: 0,
							//                         color: '#'
							//                     }, {
							//                         offset: 0,
							//                         color: '#'
							//                     }, {
							//                         offset: 0,
							//                         color: '#40d738'
							//                     }])
						}
					},
					label: {
						normal: {
							formatter: '{d}%',
							textStyle: {
								color: '#15ce56',
								fontSize: 15,
								fontWeight: 'bold'
							}
						}
					},
				}, {
					value: 80,
					label: {
						normal: {
							textStyle: {
								color: '#a5b3ef',
								fontSize: 14
							}
						}
					},
					itemStyle: {
						normal: {
							color: {
								type: 'linear',
								x: 0,
								y: 0,
								x2: 0,
								y2: 1,
								colorStops: [{
									offset: 0,
									color: '#40d738' // 0% 处的颜色
								}, {
									offset: 1,
									color: '#2235d4' // 100% 处的颜色
								}],
								globalCoord: false // 缺省为 false
							}
						}
					},
				}]
			}]
		};
		option && chart.clear();
		option && chart.setOption(option);
		var timer = window.setInterval(function() {
			chart.clear();
			refreshData(option);
		}, 5000);

		function refreshData(data) {
			option && chart.setOption(data);
		}
	})
	</script>

</html>