package com.dt.taje.utils;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

public class Tools {

	  public static String trim(String s) {
	    return ConvNull(s).trim();
	  }

	  public static String substr(String str, int len, String sp) {
	    String result = "";
	    try {
	      if (Tools.ConvNull(str).length() < len) {
	        result = Tools.ConvNull(str);
	      }
	      else {
	        result = Tools.ConvNull(str).substring(0, len);
	        result += sp;
	        result += Tools.ConvNull(str).substring(len);
	      }
	    }
	    catch (Exception e) {
	      System.out.println("substr err:" + e);
	    }
	    return result;
	  }

	  public static void alert(String msg, String goUrl, boolean goPage,
	                           HttpServletResponse res) {
	    String tmp = "";
	    if (!Tools.ConvNull(msg).equals("")) {
	      if (goPage) {
	        tmp = "<script language='JavaScript'> alert('" + msg +
	            "'); window.self.location='" + Tools.ConvNull(goUrl) +
	            "'; </script>";
	      }
	      else {
	        tmp = "<script language='JavaScript'> alert('" + msg +
	            "'); window.history.back(1); </script>";
	      }
	    }
	    try {
	      res.getWriter().println(tmp);
	    }
	    catch (Exception e) {
	    }
	  }
	  
	  /**
	   * 格式化小数，到两位----------------------------------------------------------新加
	   */
	  public static String myFD(String dateStr){
		  String result="&nbsp;";
		  try {
			  result=ConvNull(dateStr);
			  if(!"".equals(result)){
				  double douNum=Double.parseDouble(result);
				  result=String.format("%.2f", douNum);
			  }else{
				  result="&nbsp;";
			  }
		    }
		    catch (Exception e) {
		      System.out.println(e);
		    }
		  System.out.println(result);
		  return result;
	  }
	  
	  /**
	   * 得分秒----------------------------------------------------------新加
	   */
	  public static String myFD2(String dateStr){
		  String result="&nbsp;";
		  try {
			  result=ConvNull(dateStr);
			  if(!"".equals(result)){
				  double douNum=Double.parseDouble(result);
				  result=String.format("%.1f", douNum);
				  double dou=Double.parseDouble(result);
				  dou*=60;
				  int zs=(int)Math.floor(dou/60);
				  int ys=(int)dou%60;
				  result=String.valueOf(zs)+"分";
				  if(ys!=0){
					  result+=String.valueOf(ys)+"秒"; 
				  }
			  }else{
				  result="&nbsp;";
			  }
		    }
		    catch (Exception e) {
		      System.out.println(e);
		    }
		  return result;
	  }
	  
	  /**
	   * 根据单位编号得单位名称
	   */
	  public static String getDWMC(String dwbh){
		  String result="&nbsp;";
		  if("100100".equals(dwbh)){
			  result="保定支队";
		  }else if("100200".equals(dwbh)){
			  result="石家庄支队";
		  }else if("100300".equals(dwbh)){
			  result="邢台支队";
		  }else if("100400".equals(dwbh)){
			  result="沧州支队";
		  }else if("100500".equals(dwbh)){
			  result="唐山支队";
		  }else if("100600".equals(dwbh)){
			  result="张家口支队";
		  }else if("100700".equals(dwbh)){
			  result="邯郸支队";
		  }else if("100800".equals(dwbh)){
			  result="承德支队";
		  }else if("100900".equals(dwbh)){
			  result="衡水支队";
		  }else if("101000".equals(dwbh)){
			  result="廊坊支队";
		  }else if("101100".equals(dwbh)){
			  result="秦皇岛支队";
		  }
		  return result;
	  }

	  /**
	   * 功能：检查字符串是否为null。如果是null，则将null转换成""返回；如果不是null，则
	   *      将传入的字符串去掉空格返回。
	   * 用法：略
	   * 示例：略
	   *
	   * @param String str
	   * @return String
	   */
	  public static String ConvNull(String str) {
	    String tmp = "";
	    try {
	      if (str != null) {
	        tmp = str.trim();
	      }
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    return tmp;
	  }

	  public static String convNull(String str) {
	    return Tools.ConvNull(str);
	  }

	  /**
	   * 功能：将字符串转换成ISO_8859_1,同时将NULL值转成""字符串。使用该方法时要设定字符串的
	   *      编码方式：GBK 或 UTF-8
	   * 示例：String str2 = Tools.toLocalEncoding(str1,"UTF-8");
	   * @param String str
	   * @param String mode
	   * @return String
	   */
	  public static String encode(String str) throws Exception {
	    if (str == null) {
	      return "";
	    }
	    else {
	      return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	    }
	  }

	  /**
	   * 功能：截取字符串
	   * 示例：String newStr = Tools.substring("asdfghj",2,4); //newStr等于"sdf"
	   * @param String str
	   * @param int start
	   * @param int end
	   * @return String
	   */
	  public static String substring(String str, int start, int end) {
	    String result = null;
	    try {
	      byte[] tmp_old = str.getBytes();
	      int s = start;
	      int e = end;
	      if (tmp_old.length < e) {
	        e = tmp_old.length;
	      }
	      if (s > e) {
	        s = e;
	      }
	      int n = e - s;
	      byte[] tmp_new = new byte[n];
	      System.arraycopy(tmp_old, s, tmp_new, 0, n);
	      result = new String(tmp_new);
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    return result;
	  }

	  /**
	   * 功能： 替换指定的字符串
	   * 说明： s是原字符串，s1是将被替换的字符串，s2是将用来替换旧的字符串（s1）的新字符串
	   * 示例： String newStr = Tools.replace("asdfgh","sd","xx"); //newStr等于"axxfgh"
	   * @param String s
	   * @param String s1
	   * @param String s2
	   * @return String
	   */
	  public static String replace(String s, String s1, String s2) {
	    if (s == null) {
	      return null;
	    }
	    StringBuffer stringbuffer = new StringBuffer("");
	    int i = s1.length();
	    int j = s.length();
	    boolean flag = false;
	    int k;
	    int l;
	    for (l = 0; (k = s.indexOf(s1, l)) >= 0; l = k + i) {
	      stringbuffer.append(s.substring(l, k));
	      stringbuffer.append(s2);
	    }

	    if (l < j) {
	      stringbuffer.append(s.substring(l, j));
	    }
	    return stringbuffer.toString();
	  }

	  /**
	   * 功能： 将字符串切割成数组
	   * 说明： source为原字符串，div为字符串的切割点。
	   * 示例： String arr[] = Tools.split("aaa:bbb:ccc:ddd",":") ;
	   * @param Strig source
	   * @param String div
	   */
	  public static String[] split(String source, String div) {
	    int arynum = 0, intIdx = 0, intIdex = 0, div_length = div.length();
	    if (source.compareTo("") != 0) {
	      if (source.indexOf(div) != -1) {
	        intIdx = source.indexOf(div);
	        for (int intCount = 1; ; intCount++) {
	          if (source.indexOf(div, intIdx + div_length) != -1) {
	            intIdx = source.indexOf(div, intIdx + div_length);
	            arynum = intCount;
	          }
	          else {
	            arynum += 2;
	            break;
	          }
	        }
	      }
	      else {
	        arynum = 1;
	      }
	    }
	    else {
	      arynum = 0;
	    }
	    intIdx = 0;
	    intIdex = 0;
	    String[] returnStr = new String[arynum];

	    if (source.compareTo("") != 0) {
	      if (source.indexOf(div) != -1) {
	        intIdx = (int) source.indexOf(div);
	        returnStr[0] = (String) source.substring(0, intIdx);
	        for (int intCount = 1; ; intCount++) {
	          if (source.indexOf(div, intIdx + div_length) != -1) {
	            intIdex = (int) source.indexOf(div, intIdx + div_length);
	            returnStr[intCount] = (String) source.substring(intIdx + div_length,
	                intIdex);
	            intIdx = (int) source.indexOf(div, intIdx + div_length);
	          }
	          else {
	            returnStr[intCount] = (String) source.substring(intIdx + div_length,
	                source.length());
	            break;
	          }
	        }
	      }
	      else {
	        returnStr[0] = (String) source.substring(0, source.length());
	        return returnStr;
	      }
	    }
	    else {
	      return returnStr;
	    }
	    return returnStr;
	  }

	  /**
	   * 功能：将java.lang.long 型的数字转换成 “xxxx-xx-xx” 形式的时间字符串。
	   * 示例：String strCurTime = Tools.getDateString(System.currentTimeMillis());
	   * @param long Ldate
	   * @return String
	   */
	  public static String getDateString(long Ldate) throws Exception {
	    long dt = Ldate;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String tmpStr = sdf.format(new java.util.Date(dt));
	    return tmpStr;
	  }

	  /**
	   * 功能：将java.lang.long 型的数字转换成 “yyyy-MM-dd HH:MM:ss” 形式的时间字符串。
	   * 示例：String strCurTime = Tools.getDateString(System.currentTimeMillis());
	   * @param long Ldate
	   * @return String
	   */
	  public static String getDateString(long Ldate, String format) throws
	      Exception {
	    long dt = Ldate;
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    String tmpStr = sdf.format(new java.util.Date(dt));
	    return tmpStr;
	  }

	  /**
	   * 功能：整形转成字符形
	   * @param int i
	   * @return String
	   */
	  public static String IntToString(int i) {
	    String tmp = new String("");
	    try {
	      tmp = String.valueOf(i);
	      return tmp;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return new String("");
	    }
	  }

	  /**
	   * 功能：字符串转成整形数字
	   * @param String str
	   * @return int
	   */
	  public static int StringToInt(String str) {
	    try {
	      if (str == null) {
	        return -1;
	      }
	      else if (str.equals("")) {
	        return 0;
	      }
	      else {
	        return Integer.parseInt(str);
	      }
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	    }
	  }

	  /**
	   * 功能：long转成String
	   * @param long i
	   * @return String
	   */
	  public static String LongToString(long i) {
	    return (new Long(i)).toString();
	  }

	  /**
	   * 功能：Double转成String
	   * 说明：i是原数据，precision是精度。
	   * @param double i
	   * @param int precision
	   * @return String
	   */
	  public static String DoubleToString(double i, int precision) {
	    String dts = (new Double(i)).toString();
	    if (dts.indexOf(".") + precision + 1 <= dts.length())
	      return dts.substring(0, dts.indexOf(".") + precision + 1);
	    else
	      return dts;
	  }

	  /**
	   * 功能：检查字符串是否为null。如果是null，则将null转换成defValue返回；如果不是null，则
	   *      将传入的字符串去掉空格返回。
	   * 用法：略
	   * 示例：略
	   *
	   * @param str 检查得查符串
	   * @return 返回字符串
	   */
	  public static String ConvNull(String str, String defValue) {
	    if (str == null) {
	      return defValue;
	    }
	    else {
	      return str.trim();
	    }
	  }

	  /**
	   * 由HashTable中的数据获得Html的Select
	   * @param ht HashTable
	   * @param SelectName select的名字
	   * @return select的html代码
	   */
	  public static String getSelectHtmlByHashtable(Hashtable ht, String SelectName) {
	    String ret = null;
	    ret = "<select name=\"" + SelectName + "\">\n";
	    Enumeration keys = ht.keys();
	    while (keys.hasMoreElements()) {
	      String key = (String) keys.nextElement();
	      String value = (String) ht.get(key);
	      ret += "<option value=\"" + key + "\">" + value + "</option>\n";
	    }
	    ret += "</select>\n";
	    return ret;
	  }

	  public static String ConvNullToHtml(String str, String defValue) {
	    if (str == null || str.trim().length() == 0) {
	      return defValue;
	    }
	    else {
	      return str.trim();
	    }
	  }

	  /**
	   * Check the string is Arabic numerals
	   * @param s string be checked
	   * @return is Arabic numerals return true,else return false
	   * @see otc.common.Const#IS_INT_PREFIX
	   * */
	  public static boolean isInt(String s) {
	    s = ConvNull(s);
	    String IS_INT_PREFIX = "0123456789";
	    String tt = "";
	    for (int i = 0; i < s.length(); i++) {
	      tt = mid(s, i + 1, 1);
	      if (IS_INT_PREFIX.indexOf(tt) < 0 && i != 0) {
	        return false;
	      }
	    }
	    return true;
	  }

	  /**
	   * cut string from a appoint position
	   * @param s string should be cutted
	   * @param st the start position
	   * @param len the length
	   * @return String
	   * */
	  public static String mid(String s, int st, int len) {
	    s = ConvNull(String.valueOf(s));
	    st = st - 1;
	    if (st < 0) {
	      st = 0;
	    }
	    if ( (len + st) > s.length()) {
	      len = s.length() - st;
	    }
	    s = s.substring(st, st + len);
	    return s;
	  }

	  /**
	   * 功能：系统打印出调试信息
	   * @param str
	   */
	  public static void Debug(String str) {
	    System.out.println("===============start=====================");
	    System.out.println("Debug MSG:: " + str);
	    System.out.println("=============== end =====================");
	  }

	  /**
	   * 将 “xxxx-xx-xx” 形式的时间字符串转换成 java.lang.long 型的数字
	   * @param String str
	   * @return Long
	   */
	  public static long getDateLong(String str) throws Exception {
	    long tmpLnum = -1;
	    if (str == null || str.equals("")) {
	      return tmpLnum;
	    }
	    else {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      Date tmpDate = sdf.parse(str, new ParsePosition(0));
	      tmpLnum = tmpDate.getTime();
	      return tmpLnum;
	    }
	  }

	  /**
	   * Get all date that like format parameter between begin_date parameter and end_date parameter,
	   * on the side, the begin_date parameter and end_date parameter must be like "YYYY-MM-DD". The
	   * format parameter is like "DD" or "MM" or "YYYY" or "MM-DD" or "YYYY-MM-DD" or "YYYY-MM-DD HH:MM:SS".
	   * @param begin_date
	   * @param end_date
	   * @param format
	   * @return String[]
	   */
	  public static String[] getStringAllDate(String begin_date, String end_date,
	                                          String format) {
	    String[] result = null;
	    String[] year = null;
	    String[] month = null;
	    String[] day = null;
	    try {
	      long Day = 86400000;
	      long begin = getDateLong(begin_date);
	      long end = getDateLong(end_date) + Day + Day;
	      long days = (end - begin) / Day;

	      result = new String[ (int) days];
	      year = new String[ (int) days];
	      month = new String[ (int) days];
	      day = new String[ (int) days];

	      Date date = null;
	      Calendar calendar = new GregorianCalendar();
	      Vector arrDate = new Vector();

	      for (long i = 0; i < days; i++) {
	        date = new Date(begin + (i * Day));
	        arrDate.addElement(date);
	      }

	      for (long i = 0; i < days; i++) {
	        calendar.setTime( (Date) arrDate.elementAt( (int) i));
	        year[ (int) i] = String.valueOf(calendar.get(calendar.YEAR));
	        month[ (int) i] = String.valueOf(calendar.get(calendar.MONTH) + 1);
	        if(String.valueOf(calendar.get(calendar.DAY_OF_MONTH)).length()==1){
	          day[ (int) i] = "0"+String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
	        }else{
	          day[ (int) i] = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
	        }
	      }

	      int ReturnMode = 0;
	      if (format.trim().equals("YYYY-MM-DD")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = year[i] + "-" + month[i] + "-" + day[i];
	        }
	      }
	      else if (format.trim().equals("YYYY-MM-DD HH:MM:SS")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = year[i] + "-" + month[i] + "-" + day[i] + " 00:00:00";
	        }
	      }
	      else if (format.trim().equals("MM-DD")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = month[i] + "-" + day[i];
	        }
	      }
	      else if (format.trim().equals("YYYY")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = year[i];
	        }
	      }
	      else if (format.trim().equals("MM")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = month[i];
	        }
	      }
	      else if (format.trim().equals("DD")) {
	        for (int i = 0; i < days; i++) {
	          result[i] = day[i];
	        }
	      }
	      else {
	        for (int i = 0; i < days; i++) {
	          result[i] = "";
	        }
	      }

	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

	  /**
	   * 将字符串s转化为html代码
	   * @param s 要转化的字符串
	   * @return 转化之后的字符串
	   */
	  public static String toHtml(String s) {
	    s = replace(s, "&", "&amp;");
	    s = replace(s, "<", "&lt;");
	    s = replace(s, ">", "&gt;");
	    s = replace(s, "\t", "    ");
	    s = replace(s, "\r\n", "\n");
	    s = replace(s, "\n", "<br>");
	    s = replace(s, " ", "&nbsp;");
	    s = replace(s, "'", "&#39;");
//	    s = replace(s, "'", Tools.convEncode("‘","ISO-8859-1","UTF-8"));
	    s = replace(s, "\\", "&#92;");
	    return s;
	  }

	  public static String htmlTo(String s) {
	    s = replace(s, "&amp;", "&");
	    s = replace(s, "&lt;", "<");
	    s = replace(s, "&gt;", ">");
	    s = replace(s, "    ", "\t");
	    s = replace(s, "\n", "\r\n");
	    s = replace(s, "<br>", "\n");
	    s = replace(s, "&nbsp;", " ");
	    s = replace(s, "&#39;", "'");
	    s = replace(s, "&#92;", "\\");
	    return s;
	  }

	  /**
	   * 转换字符编码方式
	   * @param str
	   * @param oldCode 原编码名称
	   * @param newCode 新编码名称
	   * @return
	   */
	  public static String convEncode(String str, String oldCode, String newCode) {
	    String result = "";
	    try {
	      if (str != null) {
	        result = new String( (str.trim()).getBytes(oldCode), newCode);
	      }
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    return result;
	  }
	  /**
	   * 大字段信息内容截取方法
	   * @param str String 大字段内容
	   * @param width int 截取字符数量
	   * @return String 截取后的大字段
	   */
	  public static String infoview(String str,int width){
	    String returnStr = Tools.htmlTo(str);
	    if((returnStr.length())>width) returnStr = returnStr.substring(0,width)+"…";
	    returnStr = Tools.toHtml(returnStr);
	    return returnStr;
	  }

	  public static String SubstrTohtml(String str, int width, int hight) {
	    boolean brk = false;
	    String re_str = "";
	    String[] str_arr = str.split("<br>");
	    int j = 0;
	    int k = 0;
	    for (int i = 0;
	         i < hight && j < hight && i < str_arr.length && j < str_arr.length; i++) {
	      String temp1 = htmlTo(str_arr[i]);
	      String temp2 = "";
	      k = i;
	      j = j + 1;
	      if (length(temp1) <= width) {
	        str_arr[i] = temp1 + "";
	      }
	      else {
	        while (length(temp1) > width) {
	          j = j + 1;
	          String temp = substringByte(temp1, 0, width);
	          temp2 = temp2 + temp + "\n";
	          temp1 = temp1.substring(temp.length(), temp1.length());
	          if (j > hight) {
	            brk = true;
	            str_arr[i] = temp2;
	            temp1 = "";
	          }
	          else {
	            if (length(temp1) <= width) {
	              temp2 = temp2 + temp1 + "";
	              j = j + 1;
	            }
	          }
	        }
	        str_arr[i] = temp2;
	      }
	      if (j > hight) {
	        brk = true;
	        break;
	      }
	    }
	    for (int i = 0; i <= k; i++) {
	      re_str = re_str + str_arr[i];
	    }
	    if (brk || k < str_arr.length - 1) {
	      re_str = substringByte(re_str, 0, length(re_str) - 4) + "……";
	    }
	    return toHtml(re_str);
	  }

	  private static String SubstrTohtmlTEST(String str, int width, int hight) {
	    boolean brk = false;
	    String re_str = "";
	    String[] str_arr = str.split("<br>");
	    int j = 0;
	    int k = 0;
	    for (int i = 0;
	         i < hight && j < hight && i < str_arr.length && j < str_arr.length; i++) {
	      String temp1 = htmlTo(str_arr[i]);
	      String temp2 = "";
	      k = i;
	      j = j + 1;
	      if (length(temp1) <= width) {
	        str_arr[i] = temp1 + "";
	      } else {
	        while (length(temp1) > width) {
	          j = j + 1;
	          String temp = substringByte(temp1,0,width);
	          temp2 = temp2 + temp;
	          temp1 = temp1.substring(temp.length(),temp1.length());
	          if (j > hight) {
	            brk = true;
	            str_arr[i] = temp2;
	            temp1 = "";
	          } else {
	            temp2 = temp2 + "\n";
	            if (length(temp1) <= width) {
	              temp2 = temp2 + temp1 + "";
//	              j = j + 1;
	            }
	          }
	        }
	        str_arr[i] = temp2;
	      }
	      if (j > hight) {
	        brk = true;
	        break;
	      }else if(j<hight){
	        str_arr[i] = str_arr[i]+"\n";
	      }
	    }
	    for (int i = 0; i <= k; i++) {
	      if((brk||k<str_arr.length-1)&&i==k){
	        str_arr[i] = str_arr[i] + "...";
	      }
	      re_str = re_str + str_arr[i];
	    }
	    return toHtml(re_str);
	  }




	  /**
	   * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	   *
	   * @author patriotlml
	   * @param String
	   *            origin, 原始字符串
	   * @param int
	   *            len, 截取长度(一个汉字长度按2算的)
	   * @return String, 返回的字符串
	   */
	  public static String substringByte(String str, int start, int end) {
	    if (str == null || str.equals("") || end - start < 1)
	      return "";
	    byte[] tmp_old = str.getBytes();
	    int s = start;
	    int e = end;
	    if (tmp_old.length < e) {
	      e = tmp_old.length;
	    }
	    if (s > e) {
	      s = e;
	    }
	    int n = e - s;
	    byte[] tmp_new = new byte[n];
	    if (n > length(str)) {
	      return str;
	    }
	    System.arraycopy(tmp_old, s, tmp_new, 0, n);
	    int count = 0;
	    for (int i = 0; i < n; i++) {
	      int value = (int) tmp_new[i];
	      if (value < 0) {
	        count++;
	      }
	    }
	    if (count % 2 != 0) {
	      n = (n == 1) ? ++n : --n;
	    }
	    return substring(str, s, n);
	  }

	  /**
	   * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	   *
	   * @param char
	   *            c, 需要判断的字符
	   * @return boolean, 返回true,Ascill字符
	   */
	  public static boolean isLetter(char c) {
	    int k = 0x80;
	    return c / k == 0 ? true : false;
	  }

	  /**
	   * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	   *
	   * @param String
	   *            s ,需要得到长度的字符串
	   * @return int, 得到的字符串长度
	   */
	  public static int length(String s) {
	    if (s == null)
	      return 0;
	    char[] c = s.toCharArray();
	    int len = 0;
	    for (int i = 0; i < c.length; i++) {
	      len++;
	      if (!isLetter(c[i])) {
	        len++;
	      }
	    }
	    return len;
	  }

	  public static String toUtf8String(String s){
	    return s;
	  }
	  
	 /**
	  * 防止SQL注入
	 * @param str
	 * @return
	 */
	  public static String TransactSQLInjection(String str) 
	  { 
	  return str.replaceAll(".*([';]+|(--)+).*", " "); 
	  } 
	}


