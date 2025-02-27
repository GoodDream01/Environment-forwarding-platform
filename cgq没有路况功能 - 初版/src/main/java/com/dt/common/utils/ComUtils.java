package com.dt.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



@SuppressWarnings("all")
public class ComUtils {

	public static String QWLX_ZQ = "20100922151801";// 执勤
	public static String QWLX_BQ = "20100922151802";// 备勤
	public static String QWLX_XJ = "20100922151803";// 休假
	
	public static String DWMH_ZD = "130000000510";//总队编号
	public static String SES_USER = "currentUser";//session中存的用户登录信息的属性名字

	// 得系统时间
	public static String getTime(int m) {
		String sysTime = "";
		java.text.SimpleDateFormat formatter = null;
		java.util.Date currentTime = null;
		if (m == 0) {
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			currentTime = new java.util.Date(); // 得到当前系统时间
			sysTime = formatter.format(currentTime);
		} else if (m == 1) {
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			currentTime = new java.util.Date(); // 得到当前系统时间
			sysTime = formatter.format(currentTime);
		} else if (m == 2) {
			formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			currentTime = new java.util.Date(); // 得到当前系统时间
			Random ran = new Random();
			int num = ran.nextInt(9999999);
			sysTime = formatter.format(currentTime) + num;
		} else if (m == 3) {
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			currentTime = new java.util.Date(); // 得到当前系统时间
			sysTime = formatter.format(currentTime);
			sysTime = sysTime.replaceFirst("-", "年");
			sysTime = sysTime.replaceFirst("-", "月");
			sysTime = sysTime + "日";
		} else if (m == 4) {
			formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			currentTime = new java.util.Date(); // 得到当前系统时间
			sysTime = formatter.format(currentTime);
		} else if (m == 5) {
			formatter = new java.text.SimpleDateFormat("yyyyMMdd");
			currentTime = new java.util.Date(); // 得到当前系统时间
			sysTime = formatter.format(currentTime);
		}else if (m == 6) {
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			sysTime = formatter.format(cal.getTime());
		}else if (m == 7) {
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,-8);
			sysTime = formatter.format(cal.getTime());
		}
		return sysTime;
	}

	// 验证 字符串 空，和长度
	public static boolean yzStr(String str, int strLength) {
		boolean ret = false;
		if (!"".equals(str) && str != null) {
			if (str.length() <= strLength) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * 字符串类型转时间类型
	 * 
	 * @param pattern
	 *            时间格式
	 * @param test
	 *            字符传
	 * @return
	 */
	public static Date String2Date(String pattern, String test) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = sf.parse(test);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 时间类型转字符传
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String Date2PatternStr(String pattern, Date date) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}


	/**
	 * 字符串比对这则表达式的方法
	 * 
	 * @param pattern
	 * @param test
	 * @return
	 */
	public static boolean strMaches(String pattern, String test) {
		boolean b = false;
		if (test != null && pattern != null)
			b = test.matches(pattern);
		return b;
	}

	/**
	 * 判断字符串是不是正整数
	 * 
	 * @param test
	 * @return
	 */
	public static boolean strIsInt(String test) {
		return strMaches("^(-)?\\d+$", test);
	}

	/**
	 * 判断字符串是不是一个数字
	 * 
	 * @param test
	 * @return
	 */
	public static boolean strIsNum(String test) {
		return strMaches("^(-)?\\d+(\\.\\d+)?$", test);
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param f
	 * @return
	 */
	public static boolean fileIsExist(File f) {
		boolean b = false;
		if (f == null)
			return false;
		if (f.exists())
			b = true;
		return b;
	}

	/**
	 * 删除文件
	 * 
	 * @param f
	 */
	public static void deleteFile(File f) {
		if (fileIsExist(f))
			f.delete();
	}

	/**
	 * 获得唯一字符串的方法（str+"_"+毫秒数+4位随机数）（循环添加的时候不建议用，可能出现重复）
	 * 
	 * @param str
	 * @return
	 */
	public static String getCoustomKeyID(String str) {
		Random rand = new Random();
		return str + "_" + Calendar.getInstance().getTimeInMillis()
				+ (rand.nextInt(9999 - 1000 + 1) + 1000);
	}

	/**
	 * 单例模式生成唯一字符串
	 * 
	 * @return
	 */
	public static synchronized String getUniqueString() {
		if (generateCount > MAX_GENERATE_COUNT)
			generateCount = 0;
		String bh = Integer.toString(generateCount);
	    if (Integer.toString(generateCount).length() == 1) bh = "0000" + bh;
	    else if (Integer.toString(generateCount).length() == 2) bh = "000" + bh;
	    else if (Integer.toString(generateCount).length() == 3) bh = "00" + bh;
	    else if (Integer.toString(generateCount).length() == 4) bh = "0" + bh;
	    else if (Integer.toString(generateCount).length() >= 5) bh = "" + bh;
	    
	    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
		String uniqueNumber = formatter.format(new Date())+ bh;
		generateCount++;
		return uniqueNumber;
	}

	private static final int MAX_GENERATE_COUNT = 99999;
	private static int generateCount = 0;

	/**
	 * 将list转换成xml数据
	 * 
	 * @param list
	 *            数据集合
	 * @param X
	 *            X轴值
	 * @param Y
	 *            Y轴数据
	 * @return
	 */
	public static String getXmlData(List list, String X, String Y) {
		StringBuffer dataXml = new StringBuffer("");
		try {
			if (null != list && list.size() > 0) {
				dataXml.append("<LIST>");
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					String x = map.get(X).toString();
					String y = map.get(Y).toString();
					dataXml.append("<ITEM>");
					dataXml.append("<X>").append(x).append("</X>");
					dataXml.append("<Y>").append(y).append("</Y>");
					dataXml.append("</ITEM>");
				}
				dataXml.append("</LIST>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataXml.toString();
	}

	/**
	 * Oracle生成分页语句的方法
	 * 
	 * @param sql
	 *            普通查询的sql语句
	 * @param offset
	 *            记录偏移量
	 * @param pagesize
	 *            每页显示的记录数
	 * @return
	 */
	public static String getLimitString(String sql, int offset, int pagesize) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= " + (offset + pagesize)
				+ ") where rownum_ > " + offset + "");
		return pagingSelect.toString();
	}

	public static String getLimitString2(String sql, int pageNum, int pagesize) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= " + (pageNum * pagesize)
				+ ") where rownum_ >= " + (pageNum - 1) * pagesize + "");
		return pagingSelect.toString();
	}

	/**
	 * utf-8编码
	 * 
	 * @param aURLFragment
	 * @return
	 */
	public static String encodeUtf8URL(String aURLFragment) {
		String result = "";
		try {
			if(aURLFragment!=null){
				result = URLEncoder.encode(aURLFragment, "UTF-8");
			}
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("UTF-8 not supported", ex);
		}
		return result;
	}

	/**
	 * utf-8解码
	 * 
	 * @param aURLFragment
	 * @return
	 */
	public static String decodeUtf8URL(String aURLFragment) {
		String result = "";
		try {
			if(aURLFragment!=null){
				result = URLDecoder.decode(aURLFragment, "UTF-8");
			}
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("UTF-8 not supported", ex);
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
	    if (ConvNull(str).equals("")) {
	      return defValue;
	    }
	    else {
	      return str.trim(); 
	    }
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
	  public static String ConvNullUTF8(String str) {
	    String tmp = "";
	    try {
	      if (str != null) {
	        tmp = str.trim();
	        tmp = new String(str.getBytes("ISO-8859-1"),"UTF-8");
	      }
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    return tmp;
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
	  public static String ConvNullUTF8(String str, String defValue) {
	    if (ConvNull(str).equals("")) {
	      return defValue;
	    }
	    else {
	      return ConvNullUTF8(str.trim()); 
	    }
	  }
	

	/**
	 * 导出Excle的方法
	 * @param request
	 * @param response
	 * @param fname 文件名
	 * @param table 表格
	 */
	public static void Export(HttpServletRequest request, HttpServletResponse response,String fname,String table) {
		try {
			PrintWriter out = response.getWriter();
			response.reset();// 清空输出流

			response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			String zfname = java.net.URLEncoder.encode(fname, "UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(zfname.getBytes("UTF-8"), "GBK") + ".xls");
			response.setContentType("application/ms-excel");// 定义输出类型

			out.write(table);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断浏览器版本
	 * @param request
	 * @return
	 */
	public String getBrowserName(HttpServletRequest request) {
		String agent=request.getHeader("User-Agent").toLowerCase();
		if (agent.indexOf("msie 6") > 0) {
			return "IE6";
		} else if (agent.indexOf("msie 7") > 0) {
			return "IE7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "IE8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "IE9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "IE10";
		} else if (agent.indexOf("msie") > 0) {
			return "IE";
		} else if (agent.indexOf("opera") > 0) {
			return "Opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "Firefox";
		} else if (agent.indexOf("chrome") > 0) {
			return "Chrome";
		} else if (agent.indexOf("webkit") > 0) {
			return "Webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "IE11";
		} else {
			return "Others";
		}
	}

	
	
	/**
	 * 把List转换成json数据
	 * @param list 数据源
	 * @param field 排除的字段
	 * @return
	 */
	public static String ListToJson(List list,Map field ){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				Set set = map.keySet();
				Iterator it = set.iterator();
				sb.append("{");
				while(it.hasNext()){
					String key = (String)it.next();
					String value = (String)map.get(key);
					if(field!=null && field.containsKey(key.toUpperCase())){continue;}
					value = value.replaceAll("\\\\","");
					value = value.replaceAll("\"","");
					sb.append("\""+key+"\":");
					sb.append("\""+value+"\"");
					if(it.hasNext()){
						sb.append(",");
					}
				}
				sb.append("}");
				if(i!=(list.size()-1)){
					sb.append(",");
				}
			}
		}
		sb.append("]");
		//去除 回车 空格 制表符 等特殊字符串
		String result = sb.toString();
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("\t|\r|\n");
		java.util.regex.Matcher m = p.matcher(result);
		result = m.replaceAll("");

		return result;
	}
	/**
	 * 把List转换成json数据
	 * @param list 数据源
	 * @return
	 */
	public static String ListToJson(List list){
		return ListToJson(list, null);
	}
	
	/**
	 * 取得验证码值
	 * @param 
	 * @return
	 */
	public static String getImageCode(HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
		return code;
	}
	
	/**
	 * 控件类型：easyui
	 * 返回datagrid JSON数据
	 * @param response
	 * @param dataGrid
	 */
	public static void PrintWrite(HttpServletResponse response,String msg) {
		response.setHeader("Cache-Control", "no-store");
		try {
			if(msg==null){
				msg = "";
			}
			PrintWriter pw=response.getWriter();
			pw.write(msg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getFisrtDayOfMonth(int year,int month)
	  {
	    Calendar cal = Calendar.getInstance();
	    //设置年份
	    cal.set(Calendar.YEAR,year);
	    //设置月份
	    cal.set(Calendar.MONTH, month-1);
	    //获取某月最小天数
	    int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	    
	    //设置日历中月份的最小天数
	    cal.set(Calendar.DAY_OF_MONTH, firstDay);
	    //格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String firstDayOfMonth = sdf.format(cal.getTime());
	    return firstDayOfMonth;
	  }
	 public static String getLastDayOfMonth(int year,int month)
	  {
	    Calendar cal = Calendar.getInstance();
	    //设置年份
	    cal.set(Calendar.YEAR,year);
	    //设置月份
	    cal.set(Calendar.MONTH, month-1);
	    
//	    //获取某月最大天数
	    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//	   System.out.println(lastDay);
//	    //设置日历中月份的最大天数
	    cal.set(Calendar.DAY_OF_MONTH, lastDay);
	    cal.add(Calendar.DATE, -1);
	    //格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String lastDayOfMonth = sdf.format(cal.getTime());
	    return lastDayOfMonth;
	  }

}
