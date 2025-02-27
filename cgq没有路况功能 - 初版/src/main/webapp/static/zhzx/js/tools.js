/*
	-----------------------------------------------------------
	项目工程需要的公用JS功能方法集，与业务模式无关
	ITS项目新增方法在底部，请从底部添加
	-----------------------------------------------------------
*/
/*
function alertDialog(strMsg,flag,width,height){
  var winwidth=420;
  var winheight=280;
  var strtemp="";
  var strMsg2=strMsg;
  var pos=0;
  var x=0;0
  var y=1;

 return showModalDialog("/imis/alertMsg.jsp?flag="+flag,strMsg,"scroll:no;status:0;help:0;resizable:0;dialogHeight:"+winheight+"px;dialogWidth:"+winwidth+"px;unadorne:0;edge:raised;")
}*/

//document.oncontextmenu = function(){return false;}
//window.onerror = function(){return true;}
function alertDialog(strMsg,flag){
	alert(strMsg);
}
//获得当前日期+i天
function fCheckInputInt(){
  if (event.keyCode < 48 || event.keyCode > 57)
    event.returnValue = false;
}
function setformselectvalue(form)
{
  for(i=0;i<form.elements.length;i++)
  {
    if(form.elements[i].type=="select-one")
    {
      if(form.elements[i].options.selectedIndex<0)
      form.elements[i].options.selectedIndex=0;
    }
  }
}

//关闭子窗口
function closesubwin(){
  if(subwinname!=null&&isclosesubwin==1){
    subwinname.close();
  }
}
//关闭窗口
function closewin(){
	window.close();
}
//-------------------------------
//  函数名：isNum(i_field,i_value)
//  功能介绍：检查输入字符串是否为数字
//  参数说明：数据项，输入的对应值
//  返回值  ：1-是数字,0-非数字
//-------------------------------
/*function isNum(i_field,i_value)
{
    re=new RegExp("[^0-9]");
    var s;
    if(s=i_value.match(re))
     {
        alertDialog("'"+i_field+"' 中含有非法字符 '"+s+"' ！");
        return 0;
     }
    return 1;
}
*/
//判断日期类型是否合法
function isDate(i_field,objdate)
{

 //  objdate.value=objdate.value.trim();
	//alert(objdate.value);
  if (objdate.value.indexOf("-")>0) changeDate("-",objdate);
  if (objdate.value.indexOf("/")>0) changeDate("/",objdate);
  if (objdate.value.indexOf(".")>0) changeDate(".",objdate);

  var thedate=objdate.value;
  if (thedate.length==8)//输入8位
  {
  	objdate.value=thedate.substr(0,4)+"-"+thedate.substr(4,2)+"-"+thedate.substr(6,2);
  }
  if(thedate.length==6)/////输入六位
  {
    var theyear=parseInt(thedate.substr(0,2),10);
    if(theyear<=30)
    {
      objdate.value="20"+thedate.substr(0,2)+"-"+thedate.substr(2,2)+"-"+thedate.substr(4,2);
    }
    else
    {
       objdate.value="19"+thedate.substr(0,2)+"-"+thedate.substr(2,2)+"-"+thedate.substr(4,2);
    }
  }
   thedate=objdate.value;

    var reg = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    var r = thedate.match(reg);

     if (r==null)
    {
       alert("请输入正确的'"+i_field+"' ！");
       objdate.focus();
       return 0;

    }
    var d= new Date(r[1],r[3]-1,r[4]);
    var newStr=d.getFullYear()+r[2]+(d.getMonth()+1)+r[2]+d.getDate()
    var newDate=r[1]+r[2]+(r[3]-0)+r[2]+(r[4]-0)
    //alert("----------r:"+r+" d:"+d+" newStr:"+newStr+" newDate:"+newDate);
    if (newStr==newDate)
         {
           //changeDate(objdate);
          return 1;
         }
     alert("'"+i_field+"'日期格式不对！");
     objdate.focus();
     return 0;
}
//-------------------------------
//  函数名：changeDate(thedate)
//  功能介绍：日期yyyymmdd转换成yyyy-mm-dd格式
//  参数说明：输入日期
//  返回值  ：0-不是，1--是
//-------------------------------

function changeDate(strformate,objdate)
{
   var theDate=objdate.value;
   var iIndex1=theDate.indexOf(strformate);
   var iIndex2=theDate.indexOf(strformate,iIndex1+1);
   var newYear = parseFloat(theDate.substring(0,iIndex1));
   var newMonth = parseFloat(theDate.substring(iIndex1+1,iIndex2));
   var newDate = parseFloat(theDate.substring(iIndex2+1));

   var newDay="";
   if (newYear<=30){
     newDay +=2000 + newYear;
   }else{
      if (newYear>30 && newYear<100){
        newDay +=1900 + newYear;
      }else{
        newDay += newYear;
      }
   }

   if (newMonth<10) {
   	newDay +="-" + "0" + newMonth;
   }else{
   	newDay +="-" + newMonth;
   }

   if (newDate<10) {
   	newDay +="-" + "0" + newDate;
   }else{
   	newDay +="-" + newDate;
   }

   objdate.value=newDay;

}

//格式化日期 （将Thu Apr 10 14:06:55 UTC+0800 2014 格式的时间按format格式转化）
function formatDate(date,dateformat){
    if(date==null||date==undefined){
    	return "";
    }
     var paddNum = function(num){
          num += "";
          return num.replace(/^(\d)$/,"0$1");
        }
    //指定格式字符
    var cfg = {
       yyyy : date.getFullYear() //年 : 4位
      ,yy : date.getFullYear().toString().substring(2)//年 : 2位
      ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
      ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
      ,d  : date.getDate()   //日 : 如果1位的时候不补0
      ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
      ,HH : paddNum(date.getHours())  //时
      ,mm : paddNum(date.getMinutes()) //分
      ,ss : paddNum(date.getSeconds()) //秒
    }
    dateformat || (dateformat = "yyyy-MM-dd HH:mm:ss");
    return dateformat.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
}

function isDate1(i_field,thedate)
{
  if(thedate.value.length==0||thedate.value==null){return 0;}
  if (!(thedate.value.length==8 || thedate.value.length==10))
   {    alert("'"+i_field+"'日期格式不对,\n要求为yyyy-mm-dd！");
     return 0;
   }
  if (thedate.value.length==8)
  {
  	thedate.value=thedate.value.substr(0,4)+"-"+thedate.value.substr(4,2)+"-"+thedate.value.substr(6,2);
  }

    var reg = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
    var r = thedate.value.match(reg);

     if (!(r==null))
    {

    var d= new Date(r[1],r[3]-1,r[4]);
    var newStr=d.getFullYear()+r[2]+(d.getMonth()+1)+r[2]+d.getDate()
    var newDate=r[1]+r[2]+(r[3]-0)+r[2]+(r[4]-0)
    if (newStr==newDate)
         {
          return 1;
         }
     alert("'"+i_field+"'日期格式不对,\n要求为yyyymmdd或yyyy-mm-dd！");
     return 0;
    }
    return 0;

}
//判断日期时间是否合法
function isDateTime(i_field,thedate){
 if(thedate.length==0||thedate==null){return 0;}
 if(thedate.length==16)
 {
   thedate=thedate+":00";
 }
  if (!(thedate.length==19))
   {    alertDialog("'"+i_field+"'格式不对,请输入正确的格式：yyyy-mm-dd hh24:mi！",2);
     return 0;
   }
  dateRegexp = new RegExp("^(\\d{4})[-](\\d{2})[-](\\d{2})[ ](\\d{2})[:](\\d{2})[:](\\d{2})$");
  var matched = dateRegexp.exec(thedate);
  if(matched != null) {
  if (!isTrueDateTime(matched[3], matched[2], matched[1],matched[4], matched[5], matched[6]))
  {
    alertDialog("'"+i_field+"'格式不对，请输入正确的格式：yyyy-mm-dd hh24:mi！",2);
    return 0;
  }
  else{
    return 1;
  }
}
return 0;
}
//判断身份证号的合法性
function sfzCheck(hm,obj)
{

      var w=new Array();
      var ll_sum;
      var ll_i;
      var ls_check;

      w[0]=7;
      w[1]=9;
      w[2]=10;
      w[3]=5;
      w[4]=8;
      w[5]=4;
      w[6]=2;
      w[7]=1;
      w[8]=6;
      w[9]=3;
      w[10]=7;
      w[11]=9;
      w[12]=10;
      w[13]=5;
      w[14]=8;
      w[15]=4;
      w[16]=2;
     ll_sum=0;

     for (ll_i=0;ll_i<=16;ll_i++)
     {   //alert("ll_i:"+ll_i+" "+hm.substr(ll_i,1)+"w[ll_i]:"+w[ll_i]+"  ll_sum:"+ll_sum);
        ll_sum=ll_sum+(hm.substr(ll_i,1)-0)*w[ll_i];

     }
     ll_sum=ll_sum % 11;


     switch (ll_sum)
      {
        case 0 :
            ls_check="1";
            break;
        case 1 :
            ls_check="0";
            break;
        case 2 :
            ls_check="X";
            break;
        case 3 :
            ls_check="9";
            break;
        case 4 :
            ls_check="8";
            break;
        case 5 :
            ls_check="7";
            break;
        case 6 :
            ls_check="6";
            break;
        case 7 :
            ls_check="5";
            break;
        case 8 :
            ls_check="4";
            break;
        case 9 :
            ls_check="3";
            break;
        case 10 :
            ls_check="2";
            break;
      }

      if (hm.substr(17,1) != ls_check)
      {
            alertDialog("身份证校验错误！------ 末位应该:"+ls_check+" 实际:"+hm.substr(17,1),2);
            obj.focus();
           // obj.focus();
            return 0;
     }
return 1
}
function check_sfzh(zjhmObj) {
  var birthday = "";
  var zjhm1 = "";
  var zjhm2 = "";
  var s = "";
  var zjhm = zjhmObj.value;
    if (notNull("身份证号", zjhm) == 0) {
      return 0;
    }
    if (! (zjhm.length == 15 || zjhm.length == 18)) {
      alertDialog("身份证长度不对,请检查！",2);
      zjhmObj.focus();
      //zjhmObj.focus();
      return 0;
    }
    zjhm1 = zjhm;
    if (zjhm.length == 18) {
      zjhm1 = zjhm.substr(0, 17);
      zjhm2 = zjhm.substr(17, 1);
    }

    re = new RegExp("[^0-9]");
    if (s = zjhm1.match(re)) {
      alertDialog("输入的值中含有非法字符'" + s + "'请检查！",2);
      zjhmObj.focus();
      // zjhmObj.focus();
      return 0;
    }
    //取出生日期
    if (zjhm.length == 15) {
      birthday = "19" + zjhm.substr(6, 6);
    }
    else {
      re = new RegExp("[^0-9A-Z]");
      if (s = zjhm2.match(re)) { //18位身份证对末位要求数字或字符
        alertDialog("输入的值中含有非法字符'" + s + "'请检查！",2);
        zjhmObj.focus();
        //zjhmObj.focus();
        return 0;
      }
      birthday = zjhm.substr(6, 8);
    }
    birthday = birthday.substr(0, 4) + "-" + birthday.substr(4, 2) + "-" +
        birthday.substr(6, 2)
        //alert("birthday"+birthday)

        if (isDate("身份证号码出生日期", birthday) == 0) { //检查日期的合法性
        zjhmObj.focus();
      return 0;
    }

    //var nl=cal_years(birthday);//求年龄
    /*
             if (nl-0<18 || nl>70)
                {
                 alert("申请年龄要求 18--70 ,当前 "+nl+" ！");
                return 0;
               }*/
    if (zjhm.length == 18) {
      return (sfzCheck(zjhm, zjhmObj)); //对18位长的身份证进行末位校验
    }
    // else{
    //  zjhmObj.value=id15to18(zjhm);
    // }

  return 1;
}
function id15to18(zjhm) {
    var strJiaoYan = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var intQuan = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var ll_sum = 0;
    var i;
    var t;
    zjhm = zjhm.substr(0, 6) + "19" + zjhm.substr(6);
    for (i = 0; i < zjhm.length; i++) {
      ll_sum = ll_sum +
          parseInt(zjhm.substr(i,1)) * intQuan[i];
    }
    ll_sum = ll_sum % 11;
    zjhm = zjhm + strJiaoYan[ll_sum];
    return zjhm;

}

function isTrueDateTime(day, month, year,hour,min,second) {
      if (month < 1 || month > 12) {
          return false;
          }
      if (day < 1 || day > 31) {
          return false;
          }
      if ((month == 4 || month == 6 || month == 9 || month == 11) &&
          (day == 31)) {
          return false;
          }
      if (month == 2) {
          var leap = (year % 4 == 0 &&
         (year % 100 != 0 || year % 400 == 0));
          if (day>29 || (day == 29 && !leap)) {
          return false;
          }
         }
      if(hour<0||hour>23){
         return false;
         }
      if(min<0||min>59){
         return false;
         }
      if(second<0||second>59){
         return false;
         }
      return true;
}

//去除空格
String.prototype.trim = function(){
     return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.lTrim = function(){
     return this.replace(/(^[\s]*)/g, "");
}
String.prototype.rTrim = function(){
     return this.replace(/([\s]*$)/g, "");
}


//显示分行信息
function displayInfo(strinfo,flag)
{
  if(strinfo==null) strinfo="";
  while(strinfo.indexOf("A~A~")>0) {
	  strinfo = strinfo.replace("A~A~", "\n");
  }
  if(flag!=null)
    alertDialog(strinfo,flag);
  else
    alertDialog(strinfo,1);
}
//改变form的状态 0-可写 1-编辑只读 disabled 2-查看只读readonly
function changeformstate(form,iZt){
  switch (iZt){
    case 0:
      for(i=0;i<form.elements.length;i++)
        form.elements[i].disabled = false;
      break;
    case 1:
      for(i=0;i<form.elements.length;i++){
        form.elements[i].disabled = true;
      }
      break;
    case 2:
      for(j=0;j<form.elements.length;j++)
      {
        if(form.elements[j].type=="select-one"&&form.elements[j].size<=1){
          if(form.elements[j].options.selectedIndex>=0){
            sValue = form.elements[j].value;
            sCaption = form.elements[j].options[form.elements[j].options.selectedIndex].innerText;
            //clearFieldOptions(form.elements[j]);
            form.elements[j].innerHTML = "";
            changecomboxvalue(form.elements[j],sValue,sCaption);
          }else{
            //clearFieldOptions(form.elements[j]);
            form.elements[j].innerHTML = "";
          }
        }else if(form.elements[j].type=="checkbox"||form.elements[j].type=="radio"){
          form.elements[j].disabled = true;
        }else if(form.elements[j].type=="button"){
          form.elements[j].style.visibility = 'hidden';
        }else{
          form.elements[j].readOnly = true;
        }
      }
      break;
  }

}

function setformtextvalue(form,value){
  for(i=0;i<form.elements.length;i++)
    if(form.elements[i].type=="text"||form.elements[i].type=="select-one"||form.elements[i].type=="hidden")
    	form.elements[i].value = value;
}

//根据select对象,获取select选中项的值
function getSelectedText(obj){
	if(obj==null||obj==undefined){
		return "";
	}
	for(i=0;i<obj.options.length;i++){
		if(obj[i].selected==true){
			return obj[i].innerText;
		}
	}
	return "";
}

function invalidLetter(m_fields,obj,strLetter){
   // alert("aaaaa");
    var m_Value=obj.value;
    var i, c;
    for (i = 0; i < m_Value.length; i++) {
      c = m_Value.charAt(i); //字符串s中的字符
      if (strLetter.indexOf(c) == -1) {
        alert(m_fields + "含有非法字符‘" + c + "’!");
        obj.focus();
        return false;
      }
    }
    return true;
}
//替换A~A~为'\n'
function replaceEnter(strinfo)
{
  if(strinfo==null) strinfo="";
  while(strinfo.indexOf("A~A~")>=0) {
	  strinfo = strinfo.replace("A~A~", "\r\n");
  }
  return strinfo;
}

//取字符串的字节长度
function strlen(str)
{
var len;
var i;
len = 0;
for (i=0;i<str.length;i++)
{
if (str.charCodeAt(i)>255) len+=2; else len++;
}
return len;
}

//页面指定宽度的字符输出
function check1(b_width, Str){
      t_Str = "";
      if(b_width <Str.length){
              t_Str = "<font color =#00B5B5 style='font-size: 9pt'><a title = '"+Str+"'>"
              t_Str = t_Str + Str.substr(0,b_width);
              t_Str = t_Str+"</a></font>"
      }else{
      t_Str = "<font style='font-size: 9pt'>" + Str +"</font>";
      }
      document.write(t_Str.fontsize('-1'));
}


function getMonthLastDate(iYear,iMonth){
	var dateArray= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	var days=dateArray[iMonth-1];
	if ((((iYear % 4 == 0) && (iYear % 100 != 0)) || (iYear % 400 == 0)) && iMonth==2){
		days=29;
	}
	return days;
}

function DateAddMonth(strDate,iMonths){
   var thisYear = parseFloat(strDate.substring(0,4));
   var thisMonth = parseFloat(strDate.substring(5,7));
   var thisDate = parseFloat(strDate.substring(8,10));
   var d =thisYear *12 + thisMonth + parseFloat(iMonths);

   var newMonth = d % 12;
   if (newMonth==0) {
   	newMonth=12;
   }
   var newYear = (d - newMonth) /12;
   var newDate = thisDate;
   var iMonthLastDate=getMonthLastDate(newYear,newMonth)
   if (newDate>iMonthLastDate) newDate=iMonthLastDate;
   var newDay="";

   newDay += newYear;
   if (newMonth<10) {
   	newDay +="-" + "0" + newMonth;
   }else{
   	newDay +="-" + newMonth;
   }

   if (newDate<10) {
   	newDay +="-" + "0" + newDate;
   }else{
   	newDay +="-" + newDate;
   }
   return(newDay);                                // 返回日期。
}

function getNyr(date)
{
  if(date.length==10)//类似2005-02-01
  {
    return date.substring(0,4)+"年"+date.substring(5,7)+"月"+date.substring(8,10)+"日";
  }else if(date.length==16)//类似2005-02-01 01:01
  {
    return date.substring(0,4)+"年"+date.substring(5,7)+"月"+date.substring(8,10)+"日"+date.substring(11,13)+"时"+date.substring(14,16)+"分";
  }else if(date.length==18)//18位身份证号
  {
    return date.substring(6,10)+"年"+date.substring(10,12)+"月"+date.substring(12,14)+"日";
  }else if(date.length==15)//15位身份证号
  {
    return "19"+date.substring(6,8)+"年"+date.substring(8,10)+"月"+date.substring(10,12)+"日";
  }else
  {
    return "";
  }
}

//获取时间
function riqi(rq){
 // alert('获取时间');
  gfPop.fPopCalendar(document.all[rq]);
  return false;
}

function formatMm(mm){
  var month=mm;
  if(month.length<2) {
    month="0" + month;
  }
     return month;
}

function getStatsBeginDay2(iStartDay){
  var today = new Date();
  var day=today.getDate();
//  today.setDate(day-10);
  day = today.getDate();
  var month=today.getMonth() + 1;
  var year=today.getFullYear();
  if(day>=iStartDay){
    if(month>=2){
      if(month<10)
         r = year + "-0" + (month - 1) + "-" + iStartDay;
      else
         r = year + "-" + (month - 1) + "-" + iStartDay;
    }else{
          r = (year-1) + "-" + "12" + "-" + iStartDay;
    }
  }
  //当天小于21日
  else{
    //当月大于等于2月
     if(month>=3){
        if(month-1<10)
          r = year + "-0" + (month-2) + "-" + iStartDay;
    	else
          r = year + "-" + (month-2) + "-" + iStartDay;
       }else{//当月等于1月
          if(month==1) r = (year-1) + "-" + "11" + "-" + iStartDay;
          else r = (year-1) + "-" + "12" + "-" + iStartDay;
      }
    }
  return r;
}
function getStatsEndDay2(iStartDay){
  var today = new Date();
  var day=today.getDate();
  day = day - 10;
  today.setDate(day);
  var month=today.getMonth() + 1;
  day=today.getDate();
  if(month<10) sMonth="0" + month;
  else sMonth = month;

  if((day<iStartDay-1&&day>10)||day>iStartDay) day = iStartDay-1;
  else day = day +10;
  if(day<10) sDay="0" + day;
  else sDay = day;
  r = today.getFullYear() + "-" + sMonth + "-" + sDay;
  return r;
}


//滚动条
  function echo() {
    var a, t = document.getElementById("scrollbar");
    t.wrap = 'off';
    a = t.offsetHeight;
    t.wrap = 'soft';
    a -= t.offsetHeight;
    a ? a : 0;
    return a
  }

//强制把大写转换成小写
function fCheckInputLow(){
  if(event.keyCode>=65 && event.keyCode<=90)
    event.keyCode=event.keyCode+32;
}
/*
控制整个系统外框界面
*/
function file_title(title){
var s="";
s+='<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">';
s+='	<tr>';
s+='		<td>';
s+='			<div style="font:0px;line-height:normal;font-weight:normal;height:8px;"></div>';
s+='			<table width="100%" border="0" cellpadding="0" cellspacing="0">';
s+='				<tr>';
s+='					<td width="6" background="image/panel_1.gif"><span style="font:1px;line-height:normal;font-weight:normal;">&nbsp;</span></td>';
s+='					<td height="27" background="image/panel_2.gif" style="font:12px;color:white;font-weight:bold;">&nbsp;'+title+'</td>';
s+='					<td width="6" background="image/panel_3.gif"><span style="font:1px;line-height:normal;font-weight:normal;">&nbsp;</span></td>';
s+='				</tr>';
s+='			</table>';
s+='			<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#F5F5F5" style="border: 1px solid #CFCFCF;">';
s+='				<tr>';
s+='					<td align="center" style="font:12px;padding:6px 9px 6px 9px">';
document.write(s);
}

function file_down(){
var s="";
s+='					</td>';
s+='				</tr>';
s+='			</table>';
s+='		</td>';
s+='	</tr>';
s+='</table>';
document.write(s);
}


/*
控制整个系统查询条件及内容
fi=file inside
*/
function fi_top(title){
var s="";
s+='<fieldset style="width:98%; border-width :thin;">';
s+='<legend><b>'+title+'</b></legend>';
s+='<table border="0" cellspacing="0" cellpadding="0"><tr><td height="2"></td></tr></table>';
document.write(s);
}
function fi_mid(title){
var s="";
//s+='<table border="0" cellspacing="0" cellpadding="0"><tr><td height="3"></td></tr></table>';
//s+='</fieldset>';
s+='<table border="0" cellspacing="0" cellpadding="0">';
s+='<tr><td height="8"></td></tr></table>';
s+='<fieldset style="width:98%; border-width :thin;">';
s+='<legend><b>'+title+'</b></legend>';
s+='<table border="0" cellspacing="0" cellpadding="0"><tr><td height="6"></td></tr></table>';
document.write(s);
}
function fi_down(){
var s="";
s+='<table border="0" cellspacing="0" cellpadding="0"><tr><td height="9"></td></tr></table>';
s+='</fieldset>';
document.write(s);
}
//
var pos=0,count=0;
var u=location.href;
while(count<4){
	pos=u.indexOf('/',pos+1);
	count++;}
u=location.href.substr(0,pos)+"/";
document.write('<style type=text/css>');
document.write('.sub_image { background: url('+u+'/theme/gray/images/submit.gif);BORDER-TOP-WIDTH:0px; background-position:0px 0px;BORDER-LEFT-WIDTH:0px;border-bottom-width:0px;width:75px;color:#464646;line-height:17px;border-right-width:0px;font-size: 12px;color: #FFFFFF;	text-decoration: none;');
document.write('}');
document.write('</style>');

//????????????????????????????????????
function onMouseOverShow(obj)
{
	with (document.all.div_show)
 	{
  	  innerText ="????:"+obj+"";
	  if (innerText.length > 0)
	  {
	   innerText = " " + innerText + "  ";
	   style.display = "block";
	   style.left = event.clientX+5;
	   style.top = event.clientY+10;
	   style.zIndex="1000";
	  }
 	}
}

//select ????????????????????    
function onMouseOutShow(obj)
{
 with (document.all.div_show)
 {
  style.display = "none"
 }
} 

//????????????????
  var oldBK;
  function mouse_over(obj)
  {
    oldBK=obj.style.backgroundColor;
    obj.style.backgroundColor="#ffffcc"
    obj.style.cursor="hand";
  }

  function mouse_out(obj)
  {
   // obj.style.backgroundColor="#e8e8ff"
    //var aaa=(obj.sectionRowIndex%2==0)? '#BFCCE1':'#F0F0F0';
    // obj.style.backgroundColor=aaa;
     obj.style.backgroundColor=oldBK;
  }
/*jsl@2010-01-08:禁用
  function open_win(url,name)
{
   window.open(url,name,"width=1020,height=600, top=50, left=0,toolbar=no, menubar=no, scrollbars=yes, resizable=none, location=no, status=yes")
   //window.location=url;
}
	function newWindow(mypage,myname,w,h,scroll){//注意高度和宽度
		LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
		TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
		settings ='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no';
		win = window.open(mypage,myname,settings);
		win.focus();
	}
	
	function newWin(mypage,myname,w,h,scroll){//注意高度和宽度
		LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
		TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
		settings ='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=yes';
		win = window.open(mypage,myname,settings);
		win.focus();
	}
*/	
	
	function openWin(mypage,myname,w,h,scroll){//注意高度和宽度
		LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
		TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
		settings ='height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no';
		win = window.open(mypage,myname,settings);
		if(win!=null)
		{
			win.focus();
		}
	}
	
	//????????????????????
function truncStr(s){
		s=s.substring(s.indexOf(":")+1,s.length);

		document.write(s);

	}
		//????????????????????
function truncDate(s){
		s=s.substring(0,16);

		document.write(s);

	}
	
	//var text='正在查询中，请稍候......';
	var theCurrentLength;
	var theCharacterTimeout;
    var theNewsTimeout;
    
	function startTicker(){
		theCharacterTimeout = 50;//字符间隔时间
		theNewsTimeout = 200;//新闻间隔时间
		theCurrentLength=0;
		theNewsState=1;
		runTicker();
	}

	function runTicker(){
		if(theCurrentLength != text.length){
		   drawNews();
		}else{
		   closeNews();
		}
	}

	// --- 滚动新闻
	function drawNews(){
		document.all.ticker.innerHTML =  text.substring(0,theCurrentLength);
	    theCurrentLength++;
	    setTimeout("runTicker()", theCharacterTimeout);
	}
	// --- 结束新闻循环
	function closeNews(){
	    document.all.ticker.innerHTML =  text ;
	    theCurrentLength = 0;
	    setTimeout("runTicker()", theNewsTimeout);
	}
  
  function truncDate(d){
  	d=d.substring(0,19);
  	document.write(d);
  }
	function showContent(i){
	 showid = "content" + i;
	 var target = document.getElementById(showid);
	 target.style.display = "block";
	 target.style.position = "absolute";
	 target.style.top = event.clientY + 20;
	 target.style.left = event.clientX - 20;
	}
	
	function hiddenContent(i){
	 hiddenid = "content" + i;
	 document.getElementById(hiddenid).style.display = "none";
	}
	
	/*
	-----------------------------------------------------------
	--以下为ITS工程需要的公用方法集
	--依据样本添加必须写好注释
	
	--注释样本：
	--函数功能：打开全屏对话框
	--参数1： url / 新对话框路径
	--参数2： sTitle / 标题名
	--创建者:jsl
	-----------------------------------------------------------
	*/
	//定义全局新窗口变量名
	var newWindow;
	var newOtherWindow;
	//定义全局图片查看变量名
	var newImgWin;
	
	
/*
* 函数功能：打开全屏对话框
* 参数1： url / 新对话框路径
* 参数2： sTitle / 标题名
* 创建者:jsl
*/     
function openFullWindow(url,sTitle)      
{      
    if (url==''){
      return false;
    }
    if (newWindow && !newWindow.closed){      
       newWindow.close();      
    }      
  	    
    sWidth  = screen.availWidth;
    sHeight = screen.availHeight-40;
    
    //newWindow = window.open(url,sTitle,'fullscreen= yes,left=0,top=0,width='+sWidth+',height='+sHeight+',status=no,toolbar=no, menubar=no, scrollbars=yes, resizable=no');      
    //newWindow.focus();
    
    //alert(url);
    newWindow=window.open(url,sTitle,'left=0,top=0,width='+sWidth+',height='+sHeight+',status=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=yes');      
    newWindow.focus();
   // newWindow = window.open(url,sTitle,'left=0,top=0,width='+sWidth+',height='+sHeight+',status=no,toolbar=no, menubar=no, scrollbars=yes, resizable=yes');      
   // newWindow.focus();
}
/*
* 函数功能：打开指定大小窗口 没有滚动栏
* 参数1：url / 指定URL
* 参数2：sTitle / 窗口名
* 参数3：sWidth / 窗口宽度
* 参数4：sHeight / 窗口高度
* 参数5：scrollFlag / 是否有滚动栏
*/
function openMidWindow(url,sTitle,sWidth,sHeight,scrollFlag)      
{      
    if (url===''){
      return false;
    }
    if (newWindow && !newWindow.closed){      
       newWindow.close();      
    }
  	if (sWidth === undefined || sHeight === undefined) {
       return false;
    }
    var l = ( screen.availWidth - sWidth ) / 2;
    var t = ( screen.availHeight - sHeight ) / 2;
    
//    if(scrollFlag){
//   	newWindow = window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',toolbar=no, menubar=no, scrollbars=yes, resizable=yes');      
//    }else{
//    	newWindow = window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',toolbar=no, menubar=no, scrollbars=no, resizable=yes');      
//    }
//    newWindow.focus();
    //去掉'-'符号，换成'一'
    if(sTitle!=null&&sTitle!=undefined&&sTitle.indexOf('-')>-1){
    	var arr=sTitle.split('-');
    	sTitle='';
    	for(var i=1;arr!=undefined&&arr!=null&&i<arr.length;i++){
    		sTitle+='一'+arr[i];
    	}
    	sTitle=arr[0]+sTitle;
    }
    if(scrollFlag){
    	return window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',status=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=yes');      
    }else{
    	return window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',status=yes,toolbar=no, menubar=no, scrollbars=no, resizable=yes');      
    }
}


/*
* 函数功能：打开另外的指定大小窗口
* 参数1：url / 指定URL
* 参数2：sTitle / 窗口名
* 参数3：sWidth / 窗口宽度
* 参数4：sHeight / 窗口高度
* 参数5：scrollFlag / 是否有滚动栏
*/
function openOtherMidWindow(url,sTitle,sWidth,sHeight,scrollFlag)      
{      
    if (url===''){
      return false;
    }
    if (newOtherWindow && !newOtherWindow.closed){      
       newOtherWindow.close();      
    }
  	if (sWidth === undefined || sHeight === undefined) {
       return false;
    }
    var l = ( screen.availWidth - sWidth ) / 2;
    var t = ( screen.availHeight - sHeight ) / 2;
    
    if(scrollFlag){
    	newOtherWindow = window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',toolbar=no, menubar=no, scrollbars=yes, resizable=no');      
    }else{
    	newOtherWindow = window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',toolbar=no, menubar=no, scrollbars=no, resizable=no');      
    }
    newOtherWindow.focus();
}

/*
* 函数功能：打开指定大小图片缩放查看窗口 没有滚动栏
* 参数1：url / 指定URL
* 参数2：sTitle / 窗口名
* 参数3：sWidth / 窗口宽度
* 参数4：sHeight / 窗口高度
* 参数5：scrollFlag / 是否有滚动栏
*/
function openImgWindow(url)      
{    
    if (url===''){
      return false;
    }
    if (newImgWin && !newImgWin.closed){      
       newImgWin.close();      
    }
  	
    var sWidth = screen.availWidth;
    var sHeight = screen.availHeight;
    var l = ( screen.availWidth - sWidth ) / 2;
    var t = ( screen.availHeight - sHeight ) / 2;
    
    newImgWin = window.open(url,'zoomImg','left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',toolbar=no, menubar=no, scrollbars=yes, resizable=yes');      
    
    newImgWin.focus();
}

/*
 *页面导航以及查询页面开始部分
 *title 首页导航栏显示
 *top_area 面板简称
 */
 
 function query_head(title,top_area){
 	var s='';
 	//s+='<div class="location fb blue"><span>当前位置：'+title+'</span></div>';
 	s+='<div class="mar20 containr">';
 	s+='<fieldset><legend> '+top_area+'</legend>';
 	s+='<div>';
 	document.write(s);
 }

 /*
  *页面导航结束
  *
  */
 function query_bottom(){
 	var s='';
 	s+='</div></fieldset>';
 	document.write(s);
 }
 
  /*
  *查询列表显示开始部分
  *
  */
 function list_head(title){
 	var s='';	
 	//s+='<div class="All_block">';
 	//s+='<div class="mainTitle">';
 	//s+='<span class="titleBg"><img src="images/011906472.gif" width="12" height="11" /> '+title+'</span>';
 	//s+='</div>';
 	var s='';	
 	s+='<div class="All_block">';
 	s+='<div class="mainTitle">';
 	s+='&nbsp;<img src="images/011906472.gif" width="12" height="11" /> '+title+'</span>';
 	s+='</div>';
 	document.write(s); 	
 }
 
  /*
  *查询列表结束部分
  *
  */
 function list_bottom(){
	var s='';	
 	s+='</div>';
 	//s+='</div>';
 	document.write(s); 
 }
 
 /*
  *使用DWR加载loading提示信息
  */
 function dwrOnLoadingMsg(message,width,height) {
  var loadingMessage;
  if (message) loadingMessage = message;
  else loadingMessage = "Loading";

  dwr.engine.setPreHook(function() {
    var disabledZone = document.getElementById("disabledZone");
    if (!disabledZone) {
      disabledZone = document.createElement('div');
      disabledZone.setAttribute('id', 'disabledZone');
      disabledZone.style.position = "absolute";
      disabledZone.style.zIndex = "1000";
      disabledZone.style.left = "0px";
      disabledZone.style.top = "0px";
      disabledZone.style.width = "100%";
      disabledZone.style.height = "100%";
      document.body.appendChild(disabledZone);
      var messageZone = document.createElement('div');
      messageZone.setAttribute('id', 'messageZone');
      messageZone.style.position = "absolute";
      messageZone.style.top = "10px";//定义显示加载信息层的位置
      //messageZone.style.bottom= "100px";　　//定义显示加载信息层的位置
// messageZone.style.left = "400px";　 //定义显示加载信息层的位置
      messageZone.style.right = "10px";//定义显示加载信息层的位置
      if(width == null || height == null ){
       messageZone.style.width = "100";//定义显示加载信息层的宽度
       messageZone.style.height = "30";    //定义显示加载信息层的高度
      }else{
       messageZone.style.width = width;//定义显示加载信息层的宽度
       messageZone.style.height = height;    //定义显示加载信息层的高度
      }
     
      messageZone.style.background = "#0277d6";//定义显示加载信息层的颜色
      messageZone.style.color = "#fff";
      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
      messageZone.style.padding = "4px";
      disabledZone.appendChild(messageZone);
      var text = document.createTextNode(loadingMessage);
      messageZone.appendChild(text);
    }
    else {
      $('messageZone').innerHTML = loadingMessage;
      disabledZone.style.visibility = 'visible';
    }
  });

  dwr.engine.setPostHook(function() {
    document.getElementById('disabledZone').style.visibility = 'hidden';
  });
}

 /*
  *使用DWR加载loading提示信息(dwr3)
  */
 function loadingMsg(message,width,height) {
  var loadingMessage;
  if (message) loadingMessage = message;
  else loadingMessage = "Loading";

  dwr.engine.setPreHook(function() {
    var disabledZone = $('disabledZone');
    if (!disabledZone) {
       alert('haha');
      disabledZone = document.createElement('div');
      disabledZone.setAttribute('id', 'disabledZone');
      disabledZone.style.position = "absolute";
      disabledZone.style.zIndex = "1000";
      disabledZone.style.left = "0px";
      disabledZone.style.top = "0px";
      disabledZone.style.width = "100%";
      disabledZone.style.height = "100%";
      document.body.appendChild(disabledZone);
      var messageZone = document.createElement('div');
      messageZone.setAttribute('id', 'messageZone');
      messageZone.style.position = "absolute";
      var w=document.body.offsetWidth;
      var h=document.body.offsetHeight;
      messageZone.style.top = h/2-15;//定义显示加载信息层的位置
      messageZone.style.right = w/2-100;//定义显示加载信息层的位置
      if(width == null || height == null ){
       messageZone.style.width = "220";//定义显示加载信息层的宽度
       messageZone.style.height = "30";    //定义显示加载信息层的高度
      }else{
       messageZone.style.width = width;//定义显示加载信息层的宽度
       messageZone.style.height = height;    //定义显示加载信息层的高度
      }
     
      messageZone.style.background = "#0277d6";//定义显示加载信息层的颜色
      messageZone.style.color = "#fff";
      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
      messageZone.style.fontSize = "20px";
      messageZone.style.padding = "4px";
      disabledZone.appendChild(messageZone);
      var text = document.createTextNode(loadingMessage);
      messageZone.appendChild(text);
    }else {
      $('messageZone').innerHTML = loadingMessage;
      alert(disabledZone.innerHtml);
      disabledZone.style.visibility = 'visible';
    }
  });

  dwr.engine.setPostHook(function() {
  	$('messageZone').innerHTML = '';
    $('disabledZone').style.visibility = 'hidden';
    dwr.engine.setPreHook();
  });
}

function logout(){
	if(window.opener != null){
		window.opener.top.location="login.do?act=logout";
		window.close();
	}else if(self.location != top.location)
		top.location="login.do?act=logout";
	else
		window.location="login.do?act=logout";
}

//获取Radio框选中的值
function getRadioValue(radio_name){
	var radios = document.getElementsByName(radio_name);	
	var value= '';			
	for(var i=0;i<radios.length;i++){
		if(radios[i].checked ){
			value = radios[i].value;
			break;
		}
	}
	return value;
}

/**   
*功能:在页面动态显示当天年月日时分秒
*示例:showTheTime();   
*返回:2009-04-14   
*/ 
function showTheHours(theHour){
	if ((theHour > 0 && theHour < 13)){
		if (theHour == "0") theHour = 12;
		return (theHour);
	}
	if (theHour == 0){
		return (12);
	}
	return (theHour-12);
}
function showZeroFilled(inValue){
	if (inValue > 9){
		return "" + inValue;
	}
	return "0" + inValue;
}
function showAmPm(){
	if (now.getHours() < 12){
		return (" 上午");
	}
	return (" 下午");
}

/**   
*功能:获取当天年月日
*示例:getToday();   
*返回:2009-02-17   
*/ 
function getToday(){
  var myDate = new Date();
  var year1 = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
  var month1 = myDate.getMonth()+1;   //获取当前月份(0-11,0代表1月)
  if(month1.toString().length == 1 ){
    month1 = '0'+ month1;
  }
  var date1 = myDate.getDate(); 
  if(date1.toString().length == 1 ){
    date1 = '0'+ date1;
  }
  var nowDay = year1+'-'+month1+'-'+date1;
  return nowDay;
}

/**   
*功能:获取当天时分秒
*示例:getNowTime();   
*返回:02:01:00   
*/ 
function getNowTime(){
  //var myDate = getToday();
  var meizzTheHour		= new Date().getHours();
  var meizzTheMinute	= new Date().getMinutes();
  var meizzTheSecond	= new Date().getSeconds();
  var nowtime = format(meizzTheHour) + ":" + 
							format(meizzTheMinute) + ":" + format(meizzTheSecond); 
  return nowtime;
}

function format(n){	//格式化数字为两位字符表示
	var m=new String();
	var tmp=new String(n);
	if (n<10 && tmp.length<2){
		m="0"+n;
	}else{
		m=n;
	}
	return m;
}

/**   
*功能:获取今天前day天的年月日
*示例:getTodayBefore(1);   
*返回:2009-02-16   
*/ 
function getTodayBefore(day){
  var myDate = new Date();
  myDate.setTime(myDate.getTime() - (day*24*60*60*1000)); 
  var year2 = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
  var month2 = myDate.getMonth()+1;   //获取当前月份(0-11,0代表1月)
  if(month2.toString().length == 1 ){
    month2 = '0'+ month2;
  }
  var date2 = myDate.getDate(); 
  if(date2.toString().length == 1 ){
    date2 = '0'+ date2;
  }
  var lastDay = year2+'-'+month2+'-'+date2;
  return lastDay;
}

/**
 * 获取当前日期+时间
 */
function getFullTime(){   
    //取得当前时间   
    var now= new Date();   
    var year=now.getYear();   
    var month=now.getMonth()+1;   
    var day=now.getDate();   
    var hour=now.getHours();   
    var minute=now.getMinutes();   
    var second=now.getSeconds();   
    var nowdate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;   
    return nowdate;
 } 

/**
*功能:比较日期大小
*实例:compareDate('2007/4/12 12:23:3','2006/4/12 09:22:45')
*返回:>0 晚于 =0等于 <0早于
*/
function compareDate(date1,date2){
   var strdt1=date1.replace("-","/");
   var strdt2=date2.replace("-","/");            
   var dt1=new Date(Date.parse(strdt1));
   var dt2=new Date(Date.parse(strdt2));
   return dt1-dt2;
}

/**   
*功能:判断选择文件是否为图片类型jpg|gif|jpeg|png|bmp
*示例:checkExt(jpg);   
*返回:20010-02-24   
*/ 
function checkExt(ext){
    //这里设置允许的扩展名
    var AllowExt="jpg|gif|jpeg|png|bmp";
    var ExtOK=false;
    var ArrayExt;
    if(AllowExt.indexOf('|')!=-1){
	    ArrayExt=AllowExt.split('|');
	    for(i=0;i<ArrayExt.length;i++){
		    if(ext.toLowerCase()==ArrayExt[i]){
			    ExtOK=true;
			    break;
		    }
    	}
    }else{
	    ArrayExt=AllowExt;
		    if(ext.toLowerCase()==ArrayExt){
		    ExtOK=true;
	    }
    }
   	return ExtOK;
}

/**   
*功能:判断选择文件类型
*示例:checkFormat(jpg,jpg);   
*返回:20013-07-31   
*/ 
function checkFormat(ext,obj){
    //这里设置允许的扩展名
    var AllowExt=obj;
    var ExtOK=false;
    var ArrayExt;
    if(AllowExt.indexOf('|')!=-1){
	    ArrayExt=AllowExt.split('|');
	    for(i=0;i<ArrayExt.length;i++){
		    if(ext.toLowerCase()==ArrayExt[i]){
			    ExtOK=true;
			    break;
		    }
    	}
    }else{
	    ArrayExt=AllowExt;
		    if(ext.toLowerCase()==ArrayExt){
		    ExtOK=true;
	    }
    }
   	return ExtOK;
}

/**
*功能:把光标移动到字母最后
*示例:cursorMoveLast(label);   
*返回:20010-03-25  
*/
function cursorMoveLast(label){
	var e;
    try{
	    e=eval('document.all.'+label);
    }catch(ee){
    	e=label;
    }
    var r=e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse();
	r.select();
}


//////////////////////////////////////////部门js调用相关///////////////////////////////////

function showFrame(obj){
	obj.select();
	var xiframe=document.getElementById("loadDept");
	
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=(document.body.scrollLeft+document.body.clientWidth-xiframe.width-1)+"px";
	}else{
		xiframe.style.left=objleft+"px";
	}
	xiframe.style.top=(objtop+objheight+2)+"px";	
	xiframe.style.display="";
}
//////////////////////////////////////////监控点树js调用相关///////////////////////////////////
function showFrameJKD(obj,id){
	obj.select();
	var xiframe;
	if(isStrNull(id)){
		xiframe=document.getElementById("loadJKD");
	}else{
		xiframe=document.getElementById(id);
	}
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=document.body.scrollLeft+document.body.clientWidth-xiframe.width-1;
	}else{
		xiframe.style.left=objleft;
	}
	xiframe.style.top=objtop+objheight+2;	
	xiframe.style.display="";
}

//判断字符串是否为空。
//如果字符串为空，返回true;
//如果字符串不为空，返回false;
var isStrNull = function(str) 
{ 
	return null == str || "" == str || "null" == str || "undefined" == typeof(str) || 0 == str.length;
}

function showFrameGIS(obj){
	obj.select();
	var xiframe=document.getElementById("loadDeptGIS");
	
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=document.body.scrollLeft+document.body.clientWidth-xiframe.width-1;
	}else{
		xiframe.style.left=objleft;
	}
	xiframe.style.top=objtop+objheight+2;	
	xiframe.style.display="";
}
function showFrameGISjjd(obj){
	obj.select();
	var xiframe=document.getElementById("loadDeptGISjjd");
	
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=document.body.scrollLeft+document.body.clientWidth-xiframe.width-1;
	}else{
		xiframe.style.left=objleft;
	}
	xiframe.style.top=objtop+objheight+2;	
	xiframe.style.display="";
}
function showFrameGISkd(obj){
	obj.select();
	var xiframe=document.getElementById("loadDeptGISkd");
	
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=document.body.scrollLeft+document.body.clientWidth-xiframe.width-1;
	}else{
		xiframe.style.left=objleft;
	}
	xiframe.style.top=objtop+objheight+2;	
	xiframe.style.display="";
}
function hiddenFrameGISjjd(){
	document.getElementById("loadDeptGISjjd").style.display="none";
}
function hiddenFrameGISkd(){
	document.getElementById("loadDeptGISkd").style.display="none";
}
function hiddenFrameGIS(){
	document.getElementById("loadDeptGIS").style.display="none";
}
function hiddenFrame(){
	document.getElementById("loadDept").style.display="none";
}
function hiddenFrameJkd(){
	document.getElementById("loadJKD").style.display="none";
}
function targetObj_blurGISjjd(){
	if(document.activeElement!=null){
		var xiframe=document.getElementById("loadDeptGISjjd");
		if(document.activeElement!=xiframe){
			xiframe.style.display="none";	
		}
	}
}
function targetObj_blurGISkd(){
	if(document.activeElement!=null){
		var xiframe=document.getElementById("loadDeptGISkd");
		if(document.activeElement!=xiframe){
			xiframe.style.display="none";	
		}
	}
}
function targetObj_blurGIS(){
	if(document.activeElement!=null){
		var xiframe=document.getElementById("loadDeptGIS");
		if(document.activeElement!=xiframe){
			xiframe.style.display="none";	
		}
	}
}
function targetObj_blur(id){
	if(document.activeElement!=null){
		var xiframe=document.getElementById("loadDept");
		var xiframe1;
		if(isStrNull(id)){
			xiframe1=document.getElementById("loadJKD");
		}else{
			xiframe1=document.getElementById(id);
		}
		if(document.activeElement!=xiframe&&xiframe!=null){
			xiframe.style.display="none";	
		}
		if(document.activeElement!=xiframe1&&xiframe1!=null){
			xiframe1.style.display="none";
		}
	}
}
//////////////////////////////////////////部门js调用相关///////////////////////////////////

/*
 *页面正在处理中遮罩div
 */
function loadMsg(msg){
	var s='';	
 	s+='<div id="working" style="position:absolute; top:250; left:20; z-index:10; visibility:hidden">';
 	s+='	<table width=100% border=0 cellspacing=0 cellpadding=0>';
 	s+='		<tr>';
 	s+='			<td width=30%></td>';
 	s+='			<td bgcolor="#0085b0">';
 	s+='				<table width=100% height=70 border=0 cellspacing=2 cellpadding=0>';
 	s+='					<tr>';
 	s+='						<td bgcolor="#FFFFFF" align=center>';
 	s+='							<IMG name="wait" align="middle" src="../images/clock.gif" width="64" height="64" border="0"><span id="msg">' + msg + '</span>';
 	s+='						</td>';
 	s+='					</tr>';
 	s+='				</table>';
 	s+='			</td>';
 	s+='			<td width=30%></td>';
 	s+='		</tr>';
 	s+='	</table>';
 	s+='</div>';
 	document.write(s); 
}

/**
*功能:限制只能输入数字
*示例:onlyNum();   
*返回:2010-05-21
*modify 2015-11-19
*/
function onlyNum(){
	if((event.keyCode>=48 && event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)|| event.keyCode==8 || event.keyCode==9)
	{	
		event.returnValue=true;
	} else {
		event.returnValue=false;
	}
}


/**
 *  功能:限制只能输入数字
 *  limitInputNum()
 *  说明:
 *		1 只能输入0到9和小数点
 *		2 只能粘贴数字
 *		3 不能拖动内容进来
 *		4 禁止使用输入法
 */
function limitInputNum(obj){
	try{
		obj.onkeypress=onlyNum;
		obj.onpaste=function(){return !clipboardData.getData('text').match(/\D/)};
		obj.ondragenter=function(){return false};
		obj.style.imeMode="Disabled";
	}catch(e){}
}

/**
 * 判断是否合法IP
 */
var ErrIPMsg="你输入的是一个非法的IP地址段！\nIP段为：:xxx.xxx.xxx.xxx(xxx为0-255)！"    
function checkIP(sIPAddress){
	var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	var reg = sIPAddress.match(exp);
	if(reg==null){
		return false;
	}
	return true;
}

/**
 * 检测ActiveX控件是否已经安装过
 * CLSID 控件clsid号
 * functionName 控件自带的方法
 */
function detectPlugin(CLSID,functionName){
    var pluginDiv = document.createElement("<div id=\"pluginDiv\" style=\"display:none\"></div>")
    document.body.insertBefore(pluginDiv);
    pluginDiv.innerHTML = '<object id="objectForDetectPlugin" classid="CLSID:'+ CLSID +'"></object>';
    try{
        if(eval("objectForDetectPlugin." + functionName) == undefined){
            pluginDiv.removeNode(true);//删除pluginDiv及其所有的子元素
            return false;
        }else{
            pluginDiv.removeNode(true);//删除pluginDiv及其所有的子元素
            return true;
        }
    }catch(e){
        return false;
    }
}


/**
* 用来检测是否安装指定的插件 
* pluginsName 插件的名称 
* activexObjectName 控件名称，主要针对于IE 
*/
function checkPlugins(pluginsName, activexObjectName) { 
    //通常ActiveXObject的对象名称是两个插件名称的组合 
    if (activexObjectName == '') activexObjectName = pluginsName + "." + pluginsName; 
    var np = navigator.plugins; 
    //针对于FF等非IE 
    if (np && np.length){ 
        for(var i = 0; i < np.length; i ++) { 
            if(np[i].name.indexOf(pluginsName) != -1) return true; 
        } 
        return false; 
    }else if(window.ActiveXObject) {//针对于IE 
        try{
            var axobj =eval("new ActiveXObject(activexObjectName);"); 
            //将对象转化为布尔类型 
            return axobj ? true : false; 
        }catch(e){ 
            return false; 
        } 
    }else{ 
        //以上情况都排除则返回false 
        return false; 
    }
}
//要求的版本号
var needVersion = "10.0";
//============ Flash Player 安装、版本检测 =======================
function chkFlashPlayer() {
	var version = GetSwfVer();
	var msgString = "";
	if (version == "-1") {
		msgString = "您的浏览器还没有安装Flash Player！";
	} else {
		if (isIE) {
			version = version.match(/WIN ([\S.]+)/)[1];
			while (version.indexOf(",") > 0) {
				version = version.replace(",", ".");
			}
		}
		var versionArr = version.split(".");
		version = versionArr[0] + "." + versionArr[1];
		msgString = "您的Flash Player版本为：<strong>" + version + "</strong>";
		if (version != needVersion) {
			msgString += "<br />系统要求版本为：<strong style='color:red'>" + needVersion + "</strong>，请你下载安装最新的Flash Player。";
		} else {
			msgString += "，您的Flash Player版本符合我们的要求。";
		}
	}
	alert(msgString);
}

//=== 以下代码请不要更改 以下代码引用于官方网站===
var isIE = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
var isOpera = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;
function ControlVersion() {
	var version;
	var axo;
	var e;
	try {
		axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
		version = axo.GetVariable("$version");
	}
	catch (e) {
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");
			version = "WIN 6,0,21,0";
			axo.AllowScriptAccess = "always";
			version = axo.GetVariable("$version");
		}
		catch (e) {
		}
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = axo.GetVariable("$version");
		}
		catch (e) {
		}
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
			version = "WIN 3,0,18,0";
		}
		catch (e) {
		}
	}
	if (!version) {
		try {
			axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
			version = "WIN 2,0,0,11";
		}
		catch (e) {
			version = -1;
		}
	}
	return version;
}
function GetSwfVer() {
	var flashVer = -1;
	if (navigator.plugins != null && navigator.plugins.length > 0) {
		if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
			var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
			var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
			var descArray = flashDescription.split(" ");
			var tempArrayMajor = descArray[2].split(".");
			var versionMajor = tempArrayMajor[0];
			var versionMinor = tempArrayMajor[1];
			var versionRevision = descArray[3];
			if (versionRevision == "") {
				versionRevision = descArray[4];
			}
			if (versionRevision[0] == "d") {
				versionRevision = versionRevision.substring(1);
			} else {
				if (versionRevision[0] == "r") {
					versionRevision = versionRevision.substring(1);
					if (versionRevision.indexOf("d") > 0) {
						versionRevision = versionRevision.substring(0, versionRevision.indexOf("d"));
					}
				} else {
					if (versionRevision[0] == "b") {
						versionRevision = versionRevision.substring(1);
					}
				}
			}
			var flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
		}
	} else {
		if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) {
			flashVer = 4;
		} else {
			if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) {
				flashVer = 3;
			} else {
				if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) {
					flashVer = 2;
				} else {
					if (isIE && isWin && !isOpera) {
						flashVer = ControlVersion();
					}
				}
			}
		}
	}
	return flashVer;
}
//============================================================

//鼠标提示信息
function onMouseOverShow(obj){
	with (document.all.div_show){
	  	innerText ="提示:"+obj+"";
		if (innerText.length > 0){
		    innerText = " " + innerText + "  ";
		    style.display = "block";
		    style.left = event.clientX+5;
		    style.top = event.clientY+10;
		    style.zIndex="1000";
		}
 	}
}

//清除鼠标提示信息
function onMouseOutShow(obj){
	with (document.all.div_show){
		style.display = "none"
	}
}

/*是否包含空格*/ 
function isIncSpace(obj,title) {
	var valid=/\s/;
	if(valid.test(obj.value)){
		 alert(title + "包含空格" );
         obj.focus();
         return false;
	}
	return true;
}


/**
 * 判断是否含有非法字符
 * val：检测值
 */
function checkAllowChar(obj,title){
   var iu, iuu, regArray=new Array("","◎","■","●","№","↑","→","↓"+
								   "!","@","#","$","%","^","&","(",")","_","-","+","=","|","'","[","]","？","~","`"+
								   "!","<",">","‰","→","←","↑","↓","¤","§","＃","＆","＆","＼","≡","≠"+
								   "≈","∈","∪","∏","∑","∧","∨","⊥","∥","∥","∠","⊙","≌","≌","√","∝","∞","∮"+
								   "∫","≯","≮","＞","≥","≤","≠","±","＋","÷","×","／","Ⅱ","Ⅰ","Ⅲ","Ⅳ","Ⅴ","Ⅵ","Ⅶ","Ⅷ","Ⅹ","Ⅻ","㈠","㈡"+
								   "╄","╅","╇","┻","┻","┇","┭","┷","┦","┣","┝","┤","┷","┷","┹","╉","╇","【","】"+
								   "㈢","㈣","㈤","㈥","㈦","㈧","㈨","㈩","①","②","③","④","⑤","⑥","⑦","⑧","⑨","⑩","┌","├","┬","┼","┍","┕","┗","┏","┅","─"+
								   "〖","〗","←","〓","☆","§","□","‰","◇","︿","＠","△","▲","＃","℃","※",".","≈","￠"); 
   iuu=regArray.length;
   for(iu=1;iu<=iuu;iu++){
      if (obj.value.indexOf(regArray[iu])!=-1){
         alert(title + "包含非法字符：" + regArray[iu]);
         obj.focus();
         return false;
      }
   }
   return true;
}

/**
 *checkAll 全选或反选记录
 *elName:全选按钮名称
 *id:需要被选的组名
 */
function checkAll(elName,id){
	var objs = document.getElementsByName(elName);
	var obj = document.getElementById(id);
	if(!obj.checked){
		for(var i=0; i<objs.length; i++) {
			if(objs[i].type.toLowerCase() == "checkbox" ){
			    objs[i].checked = false;
			}
		}
	}else{
		for(var i=0; i<objs.length; i++) {
			if(objs[i].type.toLowerCase() == "checkbox" ){
				objs[i].checked = true;
			}
		}
	}
}

/**
 *checkNum 判断选择记录个数
 *elName:需要被选的组名
 */
function checkNum(elName){
	var num=0;
	var objs=document.getElementsByName(elName);
	for(var i=0; i<objs.length; i++) {
		if(objs[i].checked){
			num++;
		}
	}
	return num;
}

/**
 *checkAllEL 选择全部记录
 *obj:需要被选的组名
 */
function checkAllEl(obj){
	for(var i=0;i<obj.length;i++){
		obj[i].checked=true;
	}
}

Array.prototype.contains = function(obj){
	if(null==obj)return;
	for(var i=0;i<this.length;i++){
		if(this[i]==obj) return true;
	}
	return false;
}
Array.prototype.remove = function(obj){
	if(null==obj)return;
	for(var i=0,n=0;i<this.length;i++){
		if(this[i]!==obj) this[n++]=this[i];
	}
	this.length-=1;
}
Array.prototype.removeAt = function(index){
	if(null==this || index>this.length)return;
	for(var i=0,n=0;i<this.length;i++){
		if(this[i]!=this[index]) this[n++]=this[i];
	}
	this.length-=1;
}
Array.prototype.clear = function(){
	this.length=0;	
}





function goFrmWork(qxdm){
	var frameWork=parent.__GetWorkPage__();
		frameWork.location="navigate.do?act=menu&qxdm="+qxdm;
	var frameTopWork=parent.__GetWorkTopPage__();
		frameTopWork.location="navigate.do?act=menuName&qxdm="+qxdm;
}

function isEnter(e){
			var keynum;
			if(window.event) // IE
			{
				keynum = e.keyCode;
			}
			else if(e.which) // Netscape/Firefox/Opera
			{
				keynum = e.which;
			}
			if(keynum==13)
			return true;
			else 
			return false;
}


		/********************** 开始 *******************/
/**
 * 鼠标事件
 * 处理下拉框显示不全   ----开始
 * @param selectObj
 */
function FixWidth(selectObj) {
    var newSelectObj = document.createElement("select");
    newSelectObj = selectObj.cloneNode(true);
    newSelectObj.selectedIndex = selectObj.selectedIndex;
    newSelectObj.id = "newSelectObj";

    var e = selectObj;
    var absTop = e.offsetTop;
    var absLeft = e.offsetLeft;
    while (e = e.offsetParent) {
        absTop += e.offsetTop;
        absLeft += e.offsetLeft;
    }
    with (newSelectObj.style) {
        position = "absolute";
        top = absTop + "px";
        left = absLeft + "px";
        width = "auto";
    }

    var rollback = function() {
        RollbackWidth(selectObj, newSelectObj);
    };
    if (window.addEventListener) {
        newSelectObj.addEventListener("blur", rollback, false);
        newSelectObj.addEventListener("change", rollback, false);
    } else {
        newSelectObj.attachEvent("onblur", rollback);
        newSelectObj.attachEvent("onchange", rollback);
    }

    selectObj.style.visibility = "hidden";
    document.body.appendChild(newSelectObj);

    var newDiv = document.createElement("div");
    with (newDiv.style) {
        position = "absolute";
        top = (absTop - 10) + "px";
        left = (absLeft - 10) + "px";
        width = newSelectObj.offsetWidth + 20;
        height = newSelectObj.offsetHeight + 20;
        background = "transparent";
    }
    document.body.appendChild(newDiv);
    newSelectObj.focus();
    var enterSel = "false";
    var enter = function() {
        enterSel = enterSelect();
    };
    newSelectObj.onmouseover = enter;

    var leavDiv = "false";
    var leave = function() {
        leavDiv = leaveNewDiv(selectObj, newSelectObj, newDiv, enterSel);
    };
    newDiv.onmouseleave = leave;
}

function RollbackWidth(selectObj, newSelectObj) {
    selectObj.selectedIndex = newSelectObj.selectedIndex;
    selectObj.style.visibility = "visible";
    if (document.getElementById("newSelectObj") != null) {
        document.body.removeChild(newSelectObj);
    }
}

function removeNewDiv(newDiv) {
    document.body.removeChild(newDiv);
}

function enterSelect() {
    return "true";
}

function leaveNewDiv(selectObj, newSelectObj, newDiv, enterSel) {
    if (enterSel == "true") {
        RollbackWidth(selectObj, newSelectObj);
        removeNewDiv(newDiv);
    }
}

/************** 结束 ******************/

/*************检查经纬度***************/
function checkJd(){
    var jd = document.getElementById("jd").value;
    var str = new Array();
    str = jd.split(".");
    var num1 = str[0].length;
    if(str.length>1){
    	var num2 = str[1].length;
    	if(num2>8){  //大于7与在数据库定义长度不符
    	      alert("输入的经度有错误,请仔细检查!");
    	    }
    }
    if(num1>7){  //大于7与在数据库定义长度不符
      alert("输入的经度有错误,请仔细检查!");
    }
}
function checkWd(){
   var wd = document.getElementById("wd").value;
    var str = new Array();
    str = wd.split(".");
    var num1 = str[0].length;
    if(str.length>1){
    	var num2 = str[1].length;
    	if(num2>8){  //大于7与在数据库定义长度不符
    	      alert("输入的纬度有错误,请仔细检查!");
    	    }
    }
    if(num1>7){  //大于7与在数据库定义长度不符
      alert("输入的纬度有错误,请仔细检查!");
    }
}
/*************结束********************/


/*******************fineReport中文编码转换****************/
function fineReportEncode(text) {     
	if (text == null) {     
		return "";     
	}     
	var newText = "";     
	for (var i = 0; i < text.length; i++) {     
		var code = text.charCodeAt (i);      
		if (code >= 128 || code == 91 || code == 93) {//91 is "[", 93 is "]".     
			newText += "[" + code.toString(16) + "]";     
		} else {     
			newText += text.charAt(i);     
		}     
	}     
	return newText;     
} 
/*******************fineReport中文编码转换END****************/

/*******************用户权限iframe****************/
function showFrameUserPri(obj){
	obj.select();
	var xiframe=document.getElementById("loadUserRolePri");
	var objheight=obj.offsetHeight;
	var objtop=obj.offsetTop;
	var obj0=obj;
	while (obj0 = obj0.offsetParent){
		objtop+=obj0.offsetTop;
	}		
	var objleft=obj.offsetLeft;
	obj0=obj;
	while (obj0 = obj0.offsetParent){
		objleft+=obj0.offsetLeft;
	}
	if (objleft > document.body.scrollLeft+document.body.clientWidth-xiframe.width){
		xiframe.style.left=document.body.scrollLeft+document.body.clientWidth-xiframe.width-10;
	}else{
		xiframe.style.left=objleft;
	}
	xiframe.style.top=objtop+objheight+20;	
	xiframe.style.display="";
}
//号牌号码前缀div

var hphmInput='';
function getHphmDivHtml(){
	

	var ccookie = document.cookie;
	var ccookies = ccookie.split("; ");
	var sft = '';
	var cysft = '';
	for(i = 0; i < ccookies.length; i++){
		if(ccookies[i].substring(0,4) == 'sft='){
			sft = unescape(ccookies[i].substr(4));
		}else if(ccookies[i].substring(0,6) == 'cysft='){
			cysft = ccookies[i].substr(6);
		}
	}
	var cysftAry = new Array('','','','','','','','');
	if(cysft != ''){
		var tmpCysftAry = cysft.split("-");
		var idx = tmpCysftAry.length < 8 ? tmpCysftAry.length : 8;
		for(i = 0; i < idx; i++){
			cysftAry[i] = unescape(tmpCysftAry[i]);
		}
	}
	var hphm_div = '';
	hphm_div = hphm_div + '<table width="200px" border="0" cellpadding="0" cellspacing="1" class="border">';
	hphm_div = hphm_div + '<tr><td class="key_normal" onclick="toV(\'' + sft + '\',1)">' + sft + '</td>';
	for(i = 0; i < 8; i++){
		if(cysftAry[i] != '' && cysftAry[i] != undefined){
			hphm_div = hphm_div + '<td class="key_normal" onclick="toV(\'' + cysftAry[i] + '\',1)">' + cysftAry[i] + '</td>';
		}else{
			hphm_div = hphm_div + '<td class="key_normal">&nbsp;&nbsp;&nbsp;&nbsp;</td>';
		}
	}

	hphm_div = hphm_div + '</tr>';
	hphm_div = hphm_div + '<table width="200px" border="0" cellpadding="0" cellspacing="1" class="border">';
	hphm_div = hphm_div + '<tr><td class="key_1" onclick="toV(\'京\',1)">京</td><td class="key_1" onclick="toV(\'津\',1)">津</td><td class="key_1" onclick="toV(\'冀\',1)">冀</td><td class="key_1" onclick="toV(\'晋\',1)">晋</td><td class="key_1" onclick="toV(\'蒙\',1)">蒙</td><td class="key_1" onclick="toV(\'辽\',1)">辽</td><td class="key_1" onclick="toV(\'吉\',1)">吉</td><td class="key_1" onclick="toV(\'黑\',1)">黑</td><td class="key_2" onclick="toV(\'警\',2)">警</td></tr>';
	hphm_div = hphm_div + '<tr><td class="key_1" onclick="toV(\'沪\',1)">沪</td><td class="key_1" onclick="toV(\'苏\',1)">苏</td><td class="key_1" onclick="toV(\'浙\',1)">浙</td><td class="key_1" onclick="toV(\'皖\',1)">皖</td><td class="key_1" onclick="toV(\'闽\',1)">闽</td><td class="key_1" onclick="toV(\'赣\',1)">赣</td><td class="key_1" onclick="toV(\'鲁\',1)">鲁</td><td class="key_3" onclick="toV(\'V\',1)">军</td><td class="key_2" onclick="toV(\'学\',2)">学</td></tr>';
	hphm_div = hphm_div + '<tr><td class="key_1" onclick="toV(\'豫\',1)">豫</td><td class="key_1" onclick="toV(\'鄂\',1)">鄂</td><td class="key_1" onclick="toV(\'湘\',1)">湘</td><td class="key_1" onclick="toV(\'粤\',1)">粤</td><td class="key_1" onclick="toV(\'桂\',1)">桂</td><td class="key_1" onclick="toV(\'琼\',1)">琼</td><td class="key_3" onclick="toV(\'Z\',1)">总</td><td class="key_3" onclick="toV(\'K\',1)">空</td><td class="key_2" onclick="toV(\'挂\',2)">挂</td></tr>';
	hphm_div = hphm_div + '<tr><td class="key_1" onclick="toV(\'渝\',1)">渝</td><td class="key_1" onclick="toV(\'川\',1)">川</td><td class="key_1" onclick="toV(\'贵\',1)">贵</td><td class="key_1" onclick="toV(\'云\',1)">云</td><td class="key_1" onclick="toV(\'藏\',1)">藏</td><td class="key_3" onclick="toV(\'H\',1)">海</td><td class="key_3" onclick="toV(\'E\',1)">炮</td><td class="key_3" onclick="toV(\'WJ\',1)">武</td><td class="key_2" onclick="toV(\'使\',2)">使</td></tr>';
	hphm_div = hphm_div + '<tr><td class="key_1" onclick="toV(\'陕\',1)">陕</td><td class="key_1" onclick="toV(\'甘\',1)">甘</td><td class="key_1" onclick="toV(\'青\',1)">青</td><td class="key_1" onclick="toV(\'宁\',1)">宁</td><td class="key_1" onclick="toV(\'新\',1)">新</td><td class="key_1" onclick="toV(\'港\',2)">港</td><td class="key_1" onclick="toV(\'澳\',2)">澳</td><td class="key_1" onclick="toV(\'台\',2)">台</td><td class="key_2" onclick="toV(\'领\',2)">领</td></tr>';      
	hphm_div = hphm_div + '</table>';
	return hphm_div;
}

var isHphmDivMouseOn = false;

function showHphmDiv(arg0){
	hphmInput="#"+arg0;
	var isshow = $("#hphm_div").css("display");
	if(isshow == 'none'){
		var offset = $(hphmInput).offset();
		var he = $(hphmInput).height();
		$("#hphm_div").html(getHphmDivHtml());
		$("#hphm_div").css("top",offset.top+he+4);
		$("#hphm_div").css("left",offset.left);
		$("#hphm_div").css("display","block");
		//$("#hphm").focus();
	}else{
		$("#hphm_div").css("display","none");
	}
}
function hiddenHphmDiv(){
	$("#hphm_div").css("display","none");
}
function toV(key1, key2){
	if(key2 == 1){
		tmpVal = $(hphmInput).val();
		if(tmpVal.length != ''){
			tmpPreHphm = tmpVal.substring(0,1);
			if(!(tmpPreHphm >= 'A' && tmpPreHphm <= 'Z') && !(tmpPreHphm >= '0' && tmpPreHphm <= '9')){
				$(hphmInput).val(key1 + tmpVal.substring(1));
			}else{
				$(hphmInput).val(key1 + tmpVal);
			}
		}else{
			$(hphmInput).val(key1 + tmpVal);
		}
		setCysft(key1);
	}else{
		$(hphmInput).val($(hphmInput).val() + key1);
	}
	$("#hphm_div").css("display","none");
	$(hphmInput).focus();
}


function setIsHphmDivMouseOn(isMouseOn){
	isHphmDivMouseOn = isMouseOn;
}

function setCysft(s){
	tcysft_new = '';
	cm = document.cookie;
	cms = cm.split("; ");
	csft = '';
	tcysft = '';
	for(i = 0; i < cms.length; i++){
		if(cms[i].substring(0,6) == 'cysft='){
			tcysft = cms[i].substr(6);
		}else if(cms[i].substring(0,4) == 'sft='){
			csft = cms[i].substr(4);
		}
	}
	if(s == unescape(csft)){
		return;
	}
	if(tcysft != '' && typeof(tcysft) != undefined){
		tcysfts = tcysft.split("-");
		if(typeof(tcysfts) != undefined){
			cidx = tcysfts.length < 7 ? tcysfts.length : 7;
			for(i = 0; i < cidx; i++){
				if(unescape(tcysfts[i]) != s){
					tcysft_new += '-' + unescape(tcysfts[i]);
				}
			}
		}
	}
	var vdate = new Date();
	vdate.setDate(vdate.getDate() + 30);
	tcysft_new = 'cysft=' + escape(s + tcysft_new)+ '; expires=' + vdate.toGMTString();
	document.cookie = tcysft_new;
}
//-------------------------------------------------
/**
 * 
 * 动态引入css,js  ----开始
 * @param selectObj
 */
var dynamicLoading = {
	    css: function(path){
			if(!path || path.length === 0){
				throw new Error('argument "path" is required !');
			}
			var head = document.getElementsByTagName('head')[0];
	        var link = document.createElement('link');
	        link.href = path;
	        link.rel = 'stylesheet';
	        link.type = 'text/css';
	        head.appendChild(link);
	    },
	    js: function(path){
			if(!path || path.length === 0){
				throw new Error('argument "path" is required !');
			}
			var head = document.getElementsByTagName('head')[0];
	        var script = document.createElement('script');
	        script.src = path;
	        script.type = 'text/javascript';
	        head.appendChild(script);
	    }
}



 function checkIsJwd(s)
{
    s=s.replace(/\s/g,""); //剔除输入中包含的空白字符（可能由复制粘贴等原因引入）
    if(isNaN(s)){
	return false;
    }
    s=s+"";
    if(s.indexOf(".")>-1) //首先必须存在小数点？
    {
            var pb=s.split(".");
            var p=pb[0];
            var b=pb[1];
            var np=Number(p);
            if(p="")np=0;//（0.xx也能写作.xx，因此允许小数点前是空的）
            //然后判断小数点前后是否是数字（精度必须是小数点后5位数？），再判断是否符合数字范围。
            if(np==np && !isNaN(Number(b)) && np>=-180 && np<=180)
            {
                    return true
            }
    }else{
    	var np=Number(s);
    	 if(np==np && !isNaN(Number(np)) && np>=-180 && np<=180)
         {
                 return true
         }
    }
    return false
}

//实现winF11效果
function hideWinTab(){
	var WsShell = new ActiveXObject('WScript.Shell');
	if(isObjectNotNull(WsShell)){
		WsShell.SendKeys('{F11}'); 
	}
}

//实现列表js聚合
function collectColumn(tableId, startRow, endRow, col) {
	var tb = document.getElementById(tableId); 
		if (col >= tb.rows[0].cells.length) { 
		return; 
	}
	if (col == 0) {
		endRow = tb.rows.length-1;
	} 
	for (var i = startRow; i < endRow; i++) {
		if (tb.rows[startRow].cells[col].innerHTML == tb.rows[i + 1].cells[0].innerHTML) {
			tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[0]); 
			tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1; 
			//if (i == endRow - 1 && startRow != endRow) { 
			//	collectColumn(tableId, startRow, endRow, col + 1); 
			//} 
		} else {
			//collectColumn(tableId, startRow, i + 0, col + 1); 
			startRow = i + 1; 
		} 
	} 
} 