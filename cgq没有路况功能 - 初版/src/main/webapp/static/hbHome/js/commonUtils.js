/*
* 判断字符串、数组、对象、集合是否为空的公共工具类
* 工具集用途：提供一些常用的判断字符串、数组、对象、集合是否为空的函数
* siliangliang 创建 2013-6-27
* version 1.0.0.0
*/

//去除前后空格   
String.prototype.trim = function()   
{   
   return this.replace(/(^\s*)|(\s*$)/g, "");   
}

// 判断字符串是否为空。
// 如果字符串为空，返回true;
// 如果字符串不为空，返回false;
var isStrNull = function(str) 
{ 
	return "undefined" == typeof(str) || null == str || "" == str || "null" == str ||  0 == str.length;
}

var isNull = function(str) 
{
	if("undefined" == typeof(str) || null == str || "" == str || "null" == str ||  0 == str.length){
		return "";
	} else {
		return str;
	}
	
}


// 判断字符串是否不为空。
// 如果字符串不为空，返回true;
// 如果字符串为空，返回false;
var isStrNotNull = function(str) 
{ 
	return !isStrNull(str);
}

// 判断两个字符串的值是否相等。
// 如果两个字符串的值相等，返回true;
// 如果两个字符串的值不相等，返回false;
var isTwoStrTheSame = function(firstStr, secondStr)
{
	if (isStrNull(firstStr) && isStrNull(secondStr))
	{
		return true;
	}
	else if (firstStr === secondStr)
	{
		return true;
	}
	else
	{
		return false;
	}
}

// 判断double类型值是否为空。
// 如果double类型值为空，返回true;
// 如果double类型值不为空，返回false;
var isDoubleNull = function(obj)
{
	return "undefined" == typeof(obj) || null == obj || "" == obj || 0 >= obj;
}

// 判断double类型值是否不为空。
// 如果double类型值不为空，返回true;
// 如果double类型值为空，返回false;
var isDoubleNotNull = function(obj)
{
	return !isDoubleNull(obj);
}

// 判断数组是否为空。
// 如果数组为空，返回true;
// 如果数组不为空，返回false;		
var isArrayNull = function(array)
{
	return null == array || 0 == array.length;
}

// 判断数组是否不为空。
// 如果数组不为空，返回true;
// 如果数组为空，返回false;		
var isArrayNotNull = function(array)
{
	return !isArrayNull(array);
}

// 判断对象是否为空。
// 如果对象为空，返回true;
// 如果对象不为空，返回false;
var isObjectNull = function(object)
{
	return "undefined" == typeof(object) || null == object || "" == object;
}

// 判断对象是否不为空。
// 如果对象不为空，返回true;
// 如果对象为空，返回false;
var isObjectNotNull = function(object)
{
	return !isObjectNull(object);
}

// 判断集合是否为空。
// 如果集合为空，返回true;
// 如果集合不为空，返回false;	
var isListNull = function(list)
{
	return null == list || "" == list || "undefined" == typeof(list) || 0 == list.length;
}

// 判断集合是否不为空。
// 如果集合不为空，返回true;
// 如果集合为空，返回false;	
var isListNotNull = function(list)
{
	return !isListNull(list);
}

// 判断字符串的值是否是数字。
// 如果字符串的值是数字，返回true;
// 如果字符串的值不是数字，返回false;	
var isStrNumber = function(str)
{
    if (isStrNull(str))
    {
    	return false;
    }
    
	// 是否为数字的正则表达式
	var pattern = /^\d+(\.\d+)?$/;
	
	return pattern.test(str);
}

// 判断字符串的值是否是数字。
// 如果字符串的值是数字，返回true;
// 如果字符串的值不是数字，返回false;	
var isStrNotNumber = function(str)
{
	return !isStrNumber(str);
}

// 显示或者关闭div
// divId1 div1的id
// divId2 div2的id
var showOrHideDiv = function(divId1, divId2)
{
	var divId1 = document.getElementById(divId1);
	var divId2 = document.getElementById(divId2);
	
	if(divId1.style.display=="none" && divId2.style.display=="none")
	{
		divId1.style.display="block";
	}
	else if(divId1.style.display=="block" && divId2.style.display=="none")
	{
		divId1.style.display="none";
		divId2.style.display="block";
	}
	else
	{
		divId1.style.display="block";
		divId2.style.display="none";
	}
}

// 将元素为数组元素的object转换为数组；
// obj 元素为数组元素的object
// 此方法适用于，子窗口调用父窗口方法时，参数类型为数组。
// 但是，到了父窗口的方法中时，参数类型却变为了object类型的情况。
// 如果符合以上的情况，可以在父窗口的方法中，在使用此参数前，先调用此方法转成数组后，再使用此参数；
var convertArrayObjectToArray = function(obj)
{
	if (isObjectNull(obj))
	{
		return null;
	}
	
	var tempArray = new Array();
	
	var length = obj.length;
	for (var i = 0; i < length; i++)
	{
		tempArray.push(obj[i]);
	}
	
	return tempArray;
}

//获取字符串字符数（中文一个字符，数字/字母0.5个）
function getStrLen(str){
	var l = str.length;   
	var blen = 0;   
	for(i=0; i<l; i++) {   
		if ((str.charCodeAt(i) & 0xff00) != 0) {   
			blen ++;   
		}
		if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
			blen ++;
		}
		blen ++;   
	} 
	return blen/2;
}

/**
 * 标注物对象，包含经纬度，图标路径和大小
 */
var PictureMarkerInfo = function()
{
	this.jd = 0.0;
	this.wd = 0.0;
	this.imageUrl = "";
	this.imageWidth = 0;
	this.imageHeight = 0;
}

/**
 * @Description 图标弹出框属性obj对象
 * @Author zr
 * @Date 2018/04/02 17:18:27
 */
var PictureMarkerTemplateInfo = function()
{
    this.templateTitle = "";
    this.templateContent = "";
    this.templateShow = false;
    this.width = 50;
    this.height = 50;
}

/**
 * 标注物的title对象，包含经纬度、展示文字、标注物图标路径和大小
 */
var TextMarkerInfo = function()
{
	this.jd = 0.0;
	this.wd = 0.0;
	this.displayText = "";
	this.imageUrl = "";
	this.imageWidth = 0;
	this.imageHeight = 0;
}

/**
  * @Author zr
  * @Description 轨迹移动obj对象
  * @param speed 轨迹移动速度m/s（默认100000m/s）
  * @param lineColor 轨迹移动线段颜色（ 默认红色）
  * @param lineWeight 轨迹移动线段宽度（默认2）
  * @param imageUrl 图标地址
  * @param imageWidth 图标地址
  * @param imageHeight 图标地址
  * @Date 2018/03/15 11:56:00
 */
var TrajectoryObj = function(){
	this.speed = 100000;
	this.lineColor = "red";
	this.lineWeight = 2;
    this.imageUrl = "etm/images/car.png";
    this.imageWidth = 20;
    this.imageHeight = 20;
}

/**
 * @Author zr
 * @Description 轨迹移动obj设置默认值（内部调用）
 * @param trajectoryObj
 * @Date 2018/03/15 04:49:28
 */
function setDefaultValue(trajectoryObj){
    if(isObjectNotNull(trajectoryObj)){
        isStrNull(trajectoryObj.speed) ? 100000 : trajectoryObj.speed;
        isStrNull(trajectoryObj.imageUrl) ? "etm/images/car.png" : trajectoryObj.imageUrl;
        isStrNull(trajectoryObj.lineColor) ? "red" : trajectoryObj.lineColor;
        isStrNull(trajectoryObj.lineWeight) ? 2 : trajectoryObj.lineWeight;
        isStrNull(trajectoryObj.imageWidth) ? 20 : trajectoryObj.imageWidth;
        isStrNull(trajectoryObj.imageHeight) ? 20 : trajectoryObj.imageHeight;
    }
    return trajectoryObj;
}

/**
 * @Description 将坐标字符串转换成point集合
 * @param 以逗号分隔的坐标集字符串如（120.23,23.34,120.45,24.56），转换成数组形式的point对象
 * @return
 * @Author zr
 * @Date 2018/03/28 17:36:05
 */
function converStringToPointArr(coordinates){
	var pointArr = new Array();
    if(isStrNotNull(coordinates)){
    	var coordinateArr = new Array();
        var arr = coordinates.split(",");
        for(var k=0;k<arr.length;k++){
            arr[k]=parseFloat(arr[k]);
            if(k%2==1){
                var temp=new Array();
                temp.push(arr[k-1]);
                temp.push(arr[k]);
                coordinateArr.push(temp);
            }
        }
		if(isArrayNotNull(coordinateArr)) {
			for (var i = 0; i < coordinateArr.length; i++) {
				if (isObjectNotNull(coordinateArr[i]) && isObjectNotNull(coordinateArr[i][0]) && isObjectNotNull(coordinateArr[i][1])) {
					var point = new BMap.Point(coordinateArr[i][0], coordinateArr[i][1]);
                    pointArr.push(point);
				}
			}
		}
    }
    return pointArr;
}

var PolygonObj = function(){
	this.coordinates = "";
	this.borderColor = "red";
	this.fillColor = "white";
}
