var CPJC_LIST = {};
var MZDM = {"01":"汉族","02":"蒙古族","03":"回族","04":"藏族","05":"维吾尔族","06":"苗族","07":"彝族","08":"壮族","09":"布依族","10":"朝鲜族",
	    "11":"满族","12":"侗族","13":"瑶族","14":"白族","15":"土家族","16":"哈尼族","17":"哈萨克族","18":"傣族","19":"黎族","20":"傈傈族",
	    "21":"佤族","22":"畲族","23":"高山族","24":"拉祜族","25":"水族","26":"东乡族","27":"纳西族","28":"景颇族","29":"柯尔克孜族","30":"土族",
	    "31":"达斡尔族","32":"仫佬族","33":"姜族","34":"布朗族","35":"撒拉族","36":"毛难族","37":"仡佬族","38":"锡伯族","39":"阿昌族","40":"普米族",
	    "41":"塔吉克族","42":"怒族","43":"乌孜别克族","44":"俄罗斯族","45":"鄂温克族","46":"崩龙族","47":"保安族","48":"裕固族","49":"京族","50":"塔塔尔族",
	    "51":"独龙族","52":"鄂伦春族","53":"赫哲族","54":"门巴族","55":"珞巴族","56":"基诺族","97":"其他","98":"外国血统"
	   };
var XBDM={"1":"男","2":"女"};

var CSYS_DM={"A":"白色" , "B":"灰色" , "C":"黄色" , "D":"粉色" , "E":"红色" , "F":"紫色" , "G":"绿色" , "H":"蓝色" , "I":"棕色" , "J":"黑色" , "Z":"其他" };
var HPZL_DM={"01":"大型汽车" , "02":"小型汽车" , "03":"使馆汽车" , "04":"领馆汽车" , "05":"境外汽车" , "06":"外籍汽车" , "07":"两、三轮摩托车" , "08":"轻便摩托车" , "09":"使馆摩托车" , "10":"领馆摩托车" , 
	     "11":"境外摩托车" , "12":"外籍摩托车" , "13":"农用运输车" , "14":"拖拉机" , "15":"挂车" , "16":"教练汽车" , "17":"教练摩托车" , "18":"试验汽车" , "19":"试验摩托车" , "20":"临时人境汽车" , 
	     "21":"临时人境摩托车" , "22":"临时行驶车" , "23":"警用汽车" , "24":"警用摩托" , "31":"武警车辆" , "32":"军用车辆" , "99":"其他"
            };
var HPZL_DM2={"大型汽车":"01" , "小型汽车":"02"};
/**
 * 根据车牌号取得车牌简称
 * @param HPHM
 * @returns {String}
 */
function getCPJC(HPHM){
	var CPJC = "";
	try{
		HPHM = HPHM.toUpperCase();
		var temp = HPHM.substring(0,2);
		if(temp!="WJ"){
			temp = HPHM.substring(0,1)
		}
		CPJC = temp;
	}catch(e){}
	return CPJC;
}
/**
 * 去除车牌简称
 * @param HPHM
 * @param CPJC
 * @returns {String}
 */
function removeCPJC(HPHM,CPJC){
	var result = "";
	try{
		HPHM = HPHM.toUpperCase();
		var index = HPHM.indexOf(CPJC);
		result = HPHM.substring(index+1,HPHM.length);
	}catch(e){}
	return result;
}
//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 2);
    month = sDate.substring(2, 4);
    day = sDate.substring(4,6);
    year = parseInt(year)+1900;
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}
/**********
 * 将全角字符转成半角字符
 */
 function DBC2SBC(str)
{
    var result="";
    for(var i=0;i<str.length;i++)
    {
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已经各种字符
        { 
            var d=str.charCodeAt(i)-65248;
            result += String.fromCharCode(d);//把全角字符的unicode编码转换为对应半角字符的unicode码
        }
        else if (code == 12288)//空格
        {
            var d=str.charCodeAt(i)-12288+32;
            result += String.fromCharCode(d);
        }
        else
        {
            result += str.charAt(i);
        }
    }
    return result;
}
 function toUpper(e){
	 var obj = e.sender;
	 var v = obj.getValue();
	 v = DBC2SBC(v).toUpperCase()
	 obj.setValue(v);
	 try{obj.setText(v);}catch(e){}
 }
 /************/
 function strToObj(str){
	 var obj = {};
	 if(str && str!=""){
		 var arry = str.split(",");
		 if(arry!=null && arry.length>0){
			 for(var i=0;i<arry.length;i++){
				 var key = arry[i];
				 obj[key]=key;
			 }
			 
		 }
 	 }
	 return obj;
 }
 /**
  * 打印方法
  * @returns
  */
 function preview() 
 { 
	 bdhtml=window.document.body.innerHTML; 
	 sprnstr="<!--startprint-->"; 
	 eprnstr="<!--endprint-->"; 
	 prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17); 
	 prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
	 //window.document.body.innerHTML=prnhtml; 
	 //window.print();
	 var _win=window.open("about:blank","_blank","toolbar= no, menubar=no, location=no, status=no");
	 _win.document.body.innerHTML=prnhtml; 
	 _win.print();
	 setTimeout(function(){_win.close();},3000);
 } 
 
 /**
  * 格式化文件大小
  * @param fileSize
  * @param idx
  * @returns
  */
 function FormatFileSize(fileSize, idx) {
  	var units = ["B", "KB", "MB", "GB"];
  	idx = idx || 0;
  	if (fileSize < 1024 || idx === units.length - 1) {
  		return fileSize.toFixed(1) + units[idx];
  	}
  	return FormatFileSize(fileSize / 1024, ++idx);
  }
 
 /**
  * 下载文件
  * @param fileUrl
  * @param fileName
  */
 function DownloadFile(fileUrl,fileName){
 	fileName = encodeURI(encodeURI(fileName));
 	window.location.href=HOST_PATH + "/common/downloadFile.do?fileUrl="+fileUrl+"&fileName="+fileName+" ";
 }
 
 /**
  * 取得表格的所有行数据
  * @param grid
  * @returns {Array}
  */
 function GetGridAllRowData(grid){
	 var result = [];
	 if(grid){
     	var rows = grid.findRows(function(row){
    	    return true;
    	});
     	result = rows;
	 }
	 return result;
 }
 
 /**
  * 格式化时间
  * @param value
  * @returns
  */
 function FormatLongDate(value){
     if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
     return "";
 }
 
 /**
  * 格式化时间
  * @param value
  * @returns
  */
 function FormatShortDate(value){
     if (value) return mini.formatDate(value, 'yyyy-MM-dd');
     return "";
 }
 
 /**
  * 于查找指定的元素在数组中的位置，即索引
  */
 Array.prototype.indexOf = function(val) { 
	 for (var i = 0; i < this.length; i++) { 
		 if (this[i] == val) return i; 
	 } 
	 return -1; 
 };
 /**
  * 删除数组指定的某个元素
  */
 Array.prototype.remove = function(val) { 
	 var index = this.indexOf(val); 
	 if (index > -1) { 
		 this.splice(index, 1); 
	 } 
 };
 /**
  * 录音播放
  * @param id
  * @param url
  */
 function playSound(id,url,autoPaly)
 {
	var autoPalyFlag = true;
	if(autoPaly != null){
		autoPalyFlag = autoPaly;
	}
	var file = HOST_PATH + "/" + url;
 	var borswer = window.navigator.userAgent.toLowerCase();
 	if ( borswer.indexOf( "ie" ) >= 0 )
 	{
 		var autostart = "false";
 		if(autoPalyFlag){
 			autostart = "true";
 		}
 		//IE内核浏览器
 		var strEmbed = '<embed name="embedPlay" src="'+file+'" autostart="'+autostart+'" hidden="false" loop="false"></embed>';
 		if ( $( "#"+id ).find( "embed" ).length <= 0 )
 			$( "#"+id ).append( strEmbed );
 		var embed = document.embedPlay;

 		//浏览器不支持 audion，则使用 embed 播放
 		embed.volume = 100;
 		if(autoPalyFlag){
 			embed.play();
 		}
 	} else
 	{
 		//非IE内核浏览器
 		var strAudio = "<audio id='audioPlay' src='"+file+"' controls='controls'>";
 		if ( $( "#"+id ).find( "audio" ).length <= 0 )
 			$( "#"+id ).append( strAudio );
 		var audio = document.getElementById( "audioPlay" );

 		//浏览器支持 audion
 		if(autoPalyFlag){
 			audio.play();
 		}
 		
 	}
 }
 
 /**
  * 视频播放
  * @param param
  * @returns {String}
  */
 function playVideo(param){
	 var vRootPath = HOST_PATH+"/static/js/video";
	 param = param||{};
	 var id = param.id;//注入节点ID
	 var vID=param.vID||'c1';//vID
	 var vWidth=param.vWidth||600;//宽度设定，配合CSS实现
	 var vHeight=param.vHeight||400;//高度设定，配合CSS实现
	 var vMp4url=param.vMp4url;//视频文件地址推荐用mp4文件(h264编码)
	 var vAutoPlay=param.vAutoPlay||"yes";//是否自动播放
	 var vPlayMod=param.vPlayMod||0;//播放模式优先级,默认=0,html5优先=1,flash优先=2
	 var vPic=param.vPic||vRootPath+'/pic/pic.jpg';//视频缩略图
	 var vLogoPath=param.vLogoPath||'';//Logo地址
	 
	
	 
	 var html = "";
	 html+="<!-- 酷播V4.05跨平台代码/开始 -->"+"\n";
	 html+="<div class=\"video\">"+"\n";
	 html+="<script type=\"text/javascript\">"+"\n";
	 html+="<!--"+"\n";
	 html+="var vRootPath = \""+HOST_PATH+"/static/js/video\";"+"\n";
	 html+="var vID        = \""+vID+"\";                   //vID"+"\n";
	 html+="var vWidth     = \""+vWidth+"\";                  //宽度设定，配合CSS实现"+"\n";
	 html+="var vHeight    = \""+vHeight+"\";                  //高度设定，配合CSS实现"+"\n";
	 html+="var vFile      = vRootPath+\"/CuSunV4set.xml\";       //配置文件地址:支持五种广告设定"+"\n";
	 html+="var vPlayer    = vRootPath+\"/player.swf?v=4.0\";     //播放器文件地址"+"\n";
	 html+="//var vPic       = vRootPath+\"/pic/pic01.jpg\";        //视频缩略图"+"\n";
	 html+="var vPic = \""+vPic+"\";"+"\n";
	 html+="var vCssurl    = vRootPath+\"/css/videos.min.css\";   //CSS文件"+"\n";
	 html+="var vAutoPlay  = \""+vAutoPlay+"\";                  //是否自动播放"+"\n";
	 html+="var vEndTime   = 0;                      //预览时间(秒数),默认为0"+"\n";
	 html+="//var vLogoPath  = vRootPath+\"/images/logo.png\";      //Logo地址"+"\n";
	 html+="var vLogoPath=\""+vLogoPath+"\";"+"\n";
	 html+="var vPlayMod   = "+vPlayMod+";                      //播放模式优先级,默认=0,html5优先=1,flash优先=2"+"\n";
	 html+="var vMp4url    = \""+vMp4url+"\";//视频文件地址推荐用mp4文件(h264编码)"+"\n";
	 html+="//-->"+"\n";
	 html+="</script>"+"\n";
	 html+="<script class=\"CuPlayerVideo\" data-mce-role=\"CuPlayerVideo\" type=\"text/javascript\" src=\""+HOST_PATH+"/static/js/video/js/player.min.js\"></script>"+"\n";
	 html+="</div>"+"\n";
	 html+="<!-- 酷播V4.05跨平台代码/结束 -->"+"\n";
	 
	 $("#"+id).html(html);
	 return html
 }
 /**********************************全屏JS*************************************/
 var isFullScreen = false;

 var App = function () {
     var setFullScreen = function () {
         var docEle = document.documentElement;
         if (docEle.requestFullscreen) {
             //W3C
             docEle.requestFullscreen();
         } else if (docEle.mozRequestFullScreen) {
             //FireFox
             docEle.mozRequestFullScreen();
         } else if (docEle.webkitRequestFullScreen) {
             //Chrome等
             docEle.webkitRequestFullScreen();
         } else if (docEle.msRequestFullscreen) {
             //IE11
             docEle.msRequestFullscreen();
         } else {
             alert('该浏览器不支持全屏');
         }
     };

     //退出全屏 判断浏览器种类
     var exitFullScreen = function () {
         // 判断各种浏览器，找到正确的方法
         var exitMethod = document.exitFullscreen || //W3C
             document.mozCancelFullScreen ||    //Chrome等
             document.webkitExitFullscreen || //FireFox
             document.msExitFullscreen; //IE11
         if (exitMethod) {
             exitMethod.call(document);
         }
         else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
             var wscript = new ActiveXObject("WScript.Shell");
             if (wscript !== null) {
                 wscript.SendKeys("{F11}");
             }
         }
     };

     return {
         init: function () {

         },
         handleFullScreen: function () {
             if (isFullScreen) {
                 exitFullScreen();
                 isFullScreen = false;
             } else {
                 setFullScreen();
                 isFullScreen = true;
             }
         }
     };
 };
 function handleFullScreen(){
	 App().handleFullScreen();
 }
/**********************************************************************/
 /**
  * 取得步骤条的方法
  */
 /**
 var _steps1=[];
 _steps1.push({step:"1",name:"洽谈"});
 _steps1.push({step:"2",name:"签约"});
 _steps1.push({step:"3",name:"规划"});
 _steps1.push({step:"4",name:"核准"});
 _steps1.push({step:"5",name:"审批"});
 _steps1.push({step:"6",name:"履约"});
 _steps1.push({step:"7",name:"建设"});
 _steps1.push({step:"8",name:"竣工"});
 _steps1.push({step:"9",name:"验收"});
 _steps1.push({step:"10",name:"入统"});
 var _status1={"101":"1", "102":"2" ,"201":"3" ,"211":"4" ,"221":"5", "222":"5", "223":"5", "224":"5", "225":"5", "301":"6", "401":"7", "411":"8", "421":"9", "501":"10"};
 
 var _steps2=[];
 _steps2.push({step:"1",name:"洽谈"});
 _steps2.push({step:"2",name:"签约"});
 _steps2.push({step:"3",name:"核准"});
 _steps2.push({step:"4",name:"履约"});
 _steps2.push({step:"5",name:"入统"});
 var _status2={"101":"1", "102":"2", "211":"3", "301":"4", "501":"5"};
 */
 var _steps1=[];
 _steps1.push({step:"1",code:"1101",name:"谋划",nameFmt:"谋划",url:"/xmdb/xmgl/xmmh/viewUi.do?zfid=",url2:"/app/xmgl/xmmh/getById.do?zfid="});
 _steps1.push({step:"2",code:"1201",name:"签约",nameFmt:"签约",url:"/xmdb/xmgl/xmqy/viewUi.do?zfid=",url2:"/app/xmgl/xmqy/getById.do?zfid="});
 _steps1.push({step:"3",code:"1301",name:"立项",nameFmt:"立项",url:"/xmdb/xmgl/xmlx/viewUi.do?zfid=",url2:"/app/xmgl/xmlx/getById.do?zfid="});
 _steps1.push({step:"4",code:"1401",name:"开工前审批",nameFmt:"开工<br/>前审批",url:"/xmdb/xmgl/xmsp/viewUi.do?zfid=",url2:"/app/xmgl/xmsp/getById.do?zfid="});
 _steps1.push({step:"5",code:"1501",name:"开工",nameFmt:"开工",url:"/xmdb/xmgl/xmkg/viewUi.do?zfid=",url2:"/app/xmgl/xmkg/getById.do?zfid="});
 _steps1.push({step:"6",code:"1601",name:"建设",nameFmt:"建设",url:"/xmdb/xmgl/xmjs/viewUi.do?zfid=",url2:"/app/xmgl/xmjs/getById.do?zfid="});
 _steps1.push({step:"7",code:"1701",name:"竣工",nameFmt:"竣工",url:"/xmdb/xmgl/xmjg/viewUi.do?zfid=",url2:"/app/xmgl/xmjg/getById.do?zfid="});
 _steps1.push({step:"8",code:"1801",name:"入统",nameFmt:"入统",url:"/xmdb/xmgl/xmrt/viewUi.do?zfid=",url2:"/app/xmgl/xmrt/getById.do?zfid="});
 _steps1.push({step:"9",code:"1901",name:"自评",nameFmt:"自评",url:"/xmdb/xmgl/xmzp/viewUi.do?zfid=",url2:"/app/xmgl/xmzp/getById.do?zfid="});
 var _status1={"1101":"1", "1201":"2" ,"1301":"3" ,"1401":"4" ,"1501":"5", "1601":"6", "1701":"7", "1801":"8", "1901":"9"};

 function getStepsHtml(statusCode,zid,isMobile){
	 var width = "300px";
	 var isMoblieFlag = false;
	 if(isMobile && isMobile=="1"){
		 isMoblieFlag = true;
	 }
	 var steps = _steps1;
	 var status = _status1;
/*	 if(isHasTd && isHasTd=="无"){
		 steps = _steps2;
		 status = _status2;
		 width = 100;
	 }*/
	 var lableStyle = "";
	 if(isMoblieFlag){
		 width = "250px";
	 }
	 var html = "<div class=\"my sui-steps-round  steps-round-auto \" style=\"width:"+width+";margin:5px 0px; \">";
	 var nowStep = status[statusCode];
	 if(steps!=null && steps.length>0){
		 for(var i=0;i<steps.length;i++){
			 var step = steps[i];
			 var clas = "todo";
			 var st = step.step;
			 var round = st;
			 var currentStep = parseInt(nowStep)+1;
			 var onclick = "";
			 if(parseInt(st)<currentStep){
				 clas = "finished";
				 round = "<i class=\"sui-icon icon-pc-right\"></i>";
			 }else if(parseInt(st)==currentStep){
				 clas = "current";
			 }
			 if(i==steps.length-1){
				 clas += " last";
			 }
			 
			 if(clas.indexOf("finished")!=-1 || clas.indexOf("current") !=-1 ){
				 if(isMoblieFlag){
					 onclick = " onclick=\"openStatusDetail_Mobile('"+step.code+"','"+zid+"');\" style='cursor:pointer;' ";
				 }else{
					 onclick = " onclick=\"openStatusDetail('"+step.code+"','"+zid+"');\" style='cursor:pointer;' ";
				 } 
			 }
			 var lableStyle = "";
			 if(st=="4"){
				 if(isMoblieFlag){
					 lableStyle = " style=\"width: 38px;height: 38px;left:8px;\" ";
				 }else{
					 lableStyle = " style=\"width: 50px;height: 24px;\" ";
				 } 
			 }
			 
			 html+="<div class=\""+clas+"\">";
			 html+=" <div class=\"wrap\">";
			 html+="  <div class=\"round\" "+onclick+">"+round+"</div>";
			 if(i!=steps.length-1){
			 html+="  <div class=\"bar\"></div>";
			 }
			 html+=" </div>";
			 html+=" <label "+lableStyle+">"+step.nameFmt+"</a></label>";
			 html+="</div>";
		 }
		 
	 }
	 
	 html+="</div>";
	 return html;
 }
 function onStepsRenderer(e){
 	var zt = e.value;
 	var record = e.record;
	var zid = record.zid;
	if(record.xmjbxx){
		isHasTd = record.xmjbxx.zid;
	}
	var html = getStepsHtml(zt,zid);
	return html;
 }
 function getStepObj(statusCode){
	 var result;
	 var steps = _steps1;
	 var status = _status1;
	 var nowStep = status[statusCode];
	 if(steps!=null && steps.length>0){
		 for(var i=0;i<steps.length;i++){
			 var step = steps[i];
			 if(nowStep == step.step){
				 result = step;
				 break;
			 }
			 
		 }
	 }
	 return result;
 }
 function getStepObjBySetpCode(setpCode){
	 var result;
	 var steps = _steps1;
	 if(steps!=null && steps.length>0){
		 for(var i=0;i<steps.length;i++){
			 var step = steps[i];
			 if(setpCode == step.step){
				 result = step;
				 break;
			 }
			 
		 }
	 }
	 return result;
 }
 function openStatusDetail(statusCode,id){
	 var url = "";
	 var title = "";
	 var step = getStepObj(statusCode);
	 if(step){
		 url = step.url+id;
		 title = step.name;
		 
		 openStatusDetailWin(url,title);
	 } 
 }
 function openStatusDetailWin(url,title){
     if(url.indexOf("?")==-1){
     	url+="?";
     }
     url+="&_rand="+Math.random();
	 mini.open({
     	 url: HOST_PATH + "/"+url,

     	 title: "项目"+title+"阶段信息", width: 1200, height: 600,
         loadOnRefresh: true,
         onload: function () {
             var iframe = this.getIFrameEl();
         },
         ondestroy: function (action) {
             //grid.reload(); 
         }
     });
 }
 //手机
 function openStatusDetail_Mobile(statusCode,id){
	 var url = "";
	 var title = "";
	 var step = getStepObj(statusCode);
	 if(step){
		 url = step.url2+id;
		 title = step.name;
		 
		 openStatusDetailWin_Mobile(url,title);
	 } 
 }
 function openStatusDetailWin_Mobile(url,title){
     if(url.indexOf("?")==-1){
     	url+="?";
     }
     url+="&_rand="+Math.random();
     url=HOST_PATH + "/"+url;
     
     window.location.href = url;
 }
 /**********************************************************************/
 /**
  * 预警灯
  */
 var JYDZT={"1":"已办理完结","2":"正在办理中","3":"接近办理期限","4":"已超过办理期限"};
 function getYujingDeng(yjzt){
	 var alt = JYDZT[yjzt];
	 return "<img src=\""+HOST_PATH+"/static/images/yujing/yujing_"+yjzt+".png\" border='0' width='26' alt='"+alt+"' title='"+alt+"' />";
 }
 function onYujingRenderer(e){
 	var yjzt = e.value;
	var html = getYujingDeng(yjzt);
	return html;
}
 /**********************************************************************/
 function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
}
function getPercentBar(v){
	var stml = "";
	stml += '<div style="position:relative;">';
    stml += 	'<div style="border:1px solid #008000;height:12px;width:115px;margin:2px 0px 1px 0px;float:left;">';		
    stml += 		'<div style="float:left;background:#FFCC66;width:'+v+';height:10px;"><div></div></div>';
    stml += 	'</div>';
    stml +=   '<div style="position:absolute;width:40px;height:10px;left:45px;top:0px;margin:0px;padding:0px;">'+v+'</div>';	
	//stml +=   '<div style="text-align:center;float:right;width:40px;margin:3px 0px 1px 0px;height:10px;font-size:12px;">'+v+'</div>';			
    stml += '</div>';
	return stml;
}
function onZijinRenderer(e){
 	var record = e.record;
 	var total = record.ztzje;
 	var num = record.zjdwje;
 	if(record.xmjbxx){
 		total = record.xmjbxx.ztzje;
 		num = record.xmjbxx.zjdwje;
 	}
	var per = GetPercent(num, total);
	var html = getPercentBar(per);
	return html;
}
/*****************督办**********************/
function OnSysDb(zfid,stepCode){
	//var zfid = mini.getbyName("zfid").value;
	var step = getStepObjBySetpCode(stepCode);
	if(!step){
		alert('未知的阶段');
		return;
	}
	var jdbh = step.code;
	var jdmc = step.name;
	var encode_jcmc = encodeURI(encodeURI(jdmc));
	var title="督办通知录入";
    mini.open({
        url: HOST_URL+"/xmdb/xmgl/xmdb/viewUi.do?zfid="+zfid+"&jd="+encode_jcmc,
        title: title, width: 800, height: 600,
        loadOnRefresh: true,
        showMaxButton: true, 
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
            grid.reload();
        }
    });
} 
/*****************审批**********************/
function OnSysPs(zfid,stepCode){
	//var zfid = mini.getbyName("zfid").value;
	var step = getStepObjBySetpCode(stepCode);
	if(!step){
		alert('未知的阶段');
		return;
	}
	var jdbh = step.code;
	var jdmc = step.name;
	var encode_jcmc = encodeURI(encodeURI(jdmc));
	var title="批示信息添加窗口";
    mini.open({
        url: HOST_URL+"/xmdb/xmgl/ldps/addUi.do?zfid="+zfid+"&jd="+encode_jcmc,
        title: title, width: 800, height: 600,
        loadOnRefresh: true,
        showMaxButton: true, 
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
            grid.reload();
        }
    });
} 
/*************************附件填充*******************************/
function FillFileGrid(fileId,grid){
	var url=HOST_URL+"/xmdb/xmgl/xmsp/getFile.do?id="+fileId;
	grid.url=url;
  	grid.load();
}
/*****************************遮罩**********************************/
function OnMiniMask(msg){
	var html = msg||'加载中';
    mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: html+'...'
    });
}
function UnMiniMask(){
	mini.unmask(document.body);
}
/*****************************************/
function goLink(url){
    if(url.indexOf("?")==-1){
     	url+="?";
     }
     url+="&_rand="+Math.random();
     
     window.location.href = url;
}