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