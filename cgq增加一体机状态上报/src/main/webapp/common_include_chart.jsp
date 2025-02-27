<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!--///////////////////////////////图表开始///////////////////////////////////////// -->
	<script src="<%=request.getContextPath() %>/js/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/js/amcharts/amcharts/serial.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/js/amcharts/amcharts/pie.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/js/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/themes/dark.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
    <!--[if (!IE) | (gte IE 10)]> -->
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/exporting/amexport.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/exporting/rgbcolor.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/exporting/canvg.js" type="text/javascript"></script>        
    <script src="<%=request.getContextPath() %>/js/amcharts/amcharts/exporting/filesaver.js" type="text/javascript"></script>               
    <!-- <![endif]-->
	<script type="text/javascript">
		AmCharts.theme = AmCharts.themes.light;
		var myExportConfig={
                menuTop: "21px",
                menuBottom: "auto",
                menuRight: "21px",
                
                menuItems: [{
                    icon: HOST_URL+"/js/amcharts/amcharts/images/export.png",
                    textAlign: 'center',
                    onclick: function () {},
                    iconTitle: '导出图片',
                    items: [{
	                    title: 'JPG',
	                    format: 'jpg'
	                    }, {
	                    title: 'PNG',
	                    format: 'png'
	                    }, {
	                    title: 'SVG',
	                    format: 'svg'
                    }]
                }]  
            };
	</script>

