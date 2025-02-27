package com.dt.taje.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.mvc.base.modle.bean.KhPxBean;
import com.dt.taje.mvc.base.modle.bean.PageBean;
import com.dt.taje.mvc.base.modle.bean.UserBean;
import com.dt.taje.mvc.base.modle.json.DataGridReturn;


/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class ComUtils {

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
		}else if(m==4){
			formatter = new java.text.SimpleDateFormat("dd");
			currentTime = new java.util.Date(); // 得当前日
			sysTime = formatter.format(currentTime);
		}else if(m==5){
			formatter = new java.text.SimpleDateFormat("yyyy-MM");
			currentTime = new java.util.Date(); // 得当前年月
			sysTime = formatter.format(currentTime);
		}
		else if (m == 6) {
			formatter = new java.text.SimpleDateFormat("yyyyMMdd");
			currentTime = new java.util.Date(); // 得到当前系统时间
			Random ran = new Random();
			int num = ran.nextInt(9999999);
			sysTime = formatter.format(currentTime) ;
		}else if(m==7){
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			currentTime = new java.util.Date(); // 只得年
			sysTime = formatter.format(currentTime);
			sysTime = sysTime.substring(0,4);
		}else if(m==8){
			formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
			currentTime = new java.util.Date(); // 只得月
			sysTime = formatter.format(currentTime);
			sysTime = sysTime.substring(5,7);
		}else if(m==9){//获取上个月第一天
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			sysTime=format.format(calendar.getTime());

		}else if(m==10){//获取上个月最后一天
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1); 
			calendar.add(Calendar.DATE, -1);
			sysTime=sf.format(calendar.getTime());

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
	 * 将时间格式的字符串进行格式化
	 * 
	 * @param pattern
	 * @param test
	 * @return
	 */
	public static String DateStrFormat(String pattern, String test,
			String targetPattern) {
		if (strIsNull(pattern) || strIsNull(test) || strIsNull(targetPattern)) {
			return "";
		}
		return Date2PatternStr(targetPattern, String2Date(pattern, test));
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean strIsNull(String str) {
		if (str != null && !"".equals(str)) {
			return false;
		}
		return true;
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
	 * 根据日期和当前时间，取得的开始时间和结束时间
	 * 当天8点到第二天8点
	 * 或
	 * 昨天8点到当天8点
	 * @param day
	 * @return
	 */
	public static String[] getKssjAndJssj(String day){
		String[] result = new String[]{"",""};
		int nowHour = new Date().getHours();
		String day1 = "";
		String day2 = "";
		//显示今天8点到明天8点
		if((nowHour>8&&nowHour<24)){
			day1 = day;
			day2 = DateUtil.getTomorroyDay(day);
		}else{
		//显示昨天8点到今天8点
			day1 = DateUtil.getDayOfYesterDay(day);
			day2 = day;
		}
		day1 = day1+" 8:00:00";
		day2 = day2+" 7:59:59";
		result = new String[]{day1,day2};
		return result;
	}
	
    /**
     * 取得开始时间
     * @param day
     * @return
     */
    public static String getKssj(String day){
    	String result = "";
    	String[] re = getKssjAndJssj(day);
    	result = re[0];
    	return result;
    }
    /**
     * 取得开始时间
     * @param day
     * @return
     */
    public static String getJssj(String day){
    	String result = "";
    	String[] re = getKssjAndJssj(day);
    	result = re[1];
    	return result;
    }
    
    /**
     * 取得地点信息
     * @param ZDDBZ
     * @param ZGSGL
     * @param ZFX
     * @param ZGL
     * @param ZSFZ
     * @param ZWZMS
     * @return
     */
    public static String getDidianXX(String ZDDBZ,String ZGSGL,String ZFX,String ZGL,String ZSFZ,String ZWZMS,String ZZDZK){
    	String str = "";
    	if(ZDDBZ.equals("0")){
    		if(!ZGSGL.equals("")){
    			str+=ZGSGL;
    		}
    		if(!ZFX.equals("")){
    			str+=","+ZFX;
    		}
    		if(!ZGL.equals("")){
    			str+=","+ZGL+"公里处";
    		}
    	}else if(ZDDBZ.equals("1")){
    		if(!ZGSGL.equals("")){
    			str+=ZGSGL;
    		}
    		if(!ZSFZ.equals("")){
    			str+=","+ZSFZ;
    		}
    		if(!ZZDZK.equals("")){
    			str+=","+ZZDZK;
    		}
    	}else{
    		str = ZWZMS;
    	}
    	return str;
    }
    
    /**
     * 去除 回车  制表符 等特殊字符串
     * @param sb
     * @return
     */
    public static String trimJson(String sb){
    	String result = "";
    	if(sb!=null){
	    	//去除 回车 制表符 等特殊字符串
	    	result = sb.toString();
	    	java.util.regex.Pattern p = java.util.regex.Pattern.compile("\t|\r|\n");
	    	java.util.regex.Matcher m = p.matcher(result);
	    	result = m.replaceAll("");
    	}
    	return result;
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
	 * 取得字典值
	 * @param id
	 * @return
	 */
	public static List getZDZHI(String id){
		List list = null;
		try {
			if(id!=null && !id.trim().equals("")){
				String sql = "select * from tzd where zmid='"+id+"' order by zinx";
				ConnDataBase bean0 = new ConnDataBase();
				list = bean0.executeQuery(sql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 用内部编号 到单位id
	 * @param 
	 * @return
	 */
	public static String  getDwid(String JCZFW,String lx){
		    String nbbh="";
		    String nbbh1="";
		    ConnDataBase bean0 = new ConnDataBase();
		    
		    if(JCZFW!=null){
		    	String[] datas=JCZFW.split(",");
				if(datas!=null && datas.length>0){
				for(int m=0 ;m<datas.length;m++){
						
			String sql = "";
			if("id".equals(lx)){
			sql="select zdwbh as zhi from tb_dwxx t where znbbh='"+datas[m]+"'";}
			if("mc".equals(lx)){
				sql="select zdwmc as zhi from tb_dwxx t where znbbh='"+datas[m]+"'";}
			
			try {
				List list = bean0.executeQuery(sql);
				if(list!=null && list.size()>0){
					Map map = (HashMap)list.get(0);
					
					 nbbh1 = map.get("zhi").toString();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(m<datas.length-1){nbbh1=nbbh1+",";}
			
			nbbh=nbbh+nbbh1;
			}
			}
	     }
			return nbbh;
		}
	
	/**
	 * 取得内部编号
	 * @param 
	 * @return
	 */
	public static String  getNbbh(String JCZFW){
		    String nbbh="";
		    String nbbh1="";
		    ConnDataBase bean0 = new ConnDataBase();
		    
		    if(JCZFW!=null){
		    	String[] datas=JCZFW.split(",");
				if(datas!=null && datas.length>0){
				for(int m=0 ;m<datas.length;m++){
						
			String sql = "select * from tb_dwxx t where zdwbh='"+datas[m]+"'";
			
			try {
				List list = bean0.executeQuery(sql);
				if(list!=null && list.size()>0){
					Map map = (HashMap)list.get(0);
					 nbbh1 = map.get("znbbh").toString();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(m<datas.length-1){nbbh1=nbbh1+",";}
			
			nbbh=nbbh+nbbh1;
			}
			}
	     }
			return nbbh;
		}
	
	/**
	 * 取得查询隶书机构id的sql语句
	 * 
	 * @param jgid
	 * @return
	 */
	public static String getJgidSql(String jgid) {
		if (!strIsNull(jgid)) {
			return "select ZID from (Select * From tblgl  Start With ZID = '"
					+ jgid
					+ "' Connect By Prior ZID=ZSJBMID  order by ZINX)  ";
		}
		return "''";
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
	
	public static UserBean getUserInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SysConfig.SESSION_USER_INFO);
		UserBean code = null;
		if(obj!=null){
			code = (UserBean)obj; 
		}
		return code;
	}
	
	public static String getSinaToken(String zuid){
		ConnDataBase bean0 = new ConnDataBase();
		String token = "";
		String sql = "select * from t_weibo_sina_token where zuid='"+zuid+"'";
		try {
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				token = map.get("ACCESSTOKEN").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	public static byte[] readFileImage(String filename)throws IOException{
        BufferedInputStream bufferedInputStream=new BufferedInputStream(
                        new FileInputStream(filename));
        int len =bufferedInputStream.available();
        byte[] bytes=new byte[len];
        int r=bufferedInputStream.read(bytes);
        if(len !=r){
                bytes=null;
                throw new IOException("读取文件不正确");
        }
        bufferedInputStream.close();
        return bytes;
	}
	public static String getRealPath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		if (realPath == null) {
			try {
				realPath = request.getSession().getServletContext().getResource("/").getPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!realPath.endsWith("/"))realPath = realPath + "/";
		
		return realPath;
	}
	
	public static String fmtCSTDateStr(String dateStr){
		//String dateStr = "Wed Sep 16 11:26:23 CST 2009"; 
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		String formatStr = "";
		try {
			Date date = sdf.parse(dateStr);
			formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return formatStr;
	}
	
	public static String fmtDate(Date date){
		String formatStr = "";
		try {
			formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return formatStr;
	}
	//Mon Mar 27 2017 00:00:00 GMT+0800 (中国标准时间)
	public static String fmtCSTDateStr1(String dateStr){
		dateStr = dateStr.substring(0,dateStr.indexOf("("));
		dateStr = dateStr.substring(0, dateStr.indexOf("+08")+3)+":"+dateStr.substring(dateStr.indexOf("+08")+3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);
		String formatStr = "";
		try {
			Date date = sdf.parse(dateStr);
			formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//System.out.println("122:"+formatStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return formatStr;
		//return null;
	}
	
	public static void saveLginfo(HttpServletRequest request,String user,String result){

		try {
			ConnDataBase bean0 = new ConnDataBase();
			String tsfid = ComUtils.getUniqueString();
			
			 //客户端ip地址
	         String remoteIP=request.getRemoteAddr();
			 
			 //客户端主机名 
	         String remotename=request.getRemoteHost();
			 
	         String Agent = request.getHeader("User-Agent");
	        // StringTokenizer st = new StringTokenizer(Agent,";");
	         //st.nextToken();
	         
			 //客户端浏览器版本
	         String userbrowser = Agent;
	         
			 //客户端操作系统
	         String osname=System.getProperty("os.name");
	         
	         //登录时间
	         String mDateTime = ComUtils.getTime(0);
	          
	         String sql = "insert into t_lginfot(tsfid,tsfuser,tsfip,tsfhost,tsfbrowser,tsfos,tsfdate,tsfresult) values('"+tsfid+"','"+user+"','"+remoteIP+"','"+remotename+"','"+userbrowser+"','"+osname+"',to_date('"+mDateTime+"','YYYY-MM-DD HH24:MI:SS'),'"+result+"')";
			 //提交日志记录
			 bean0.executeUpdate(sql); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 拼装PageBean
	 * @param request
	 * @param sql
	 * @return
	 */
	public static PageBean getPageBean(HttpServletRequest request,String sql){
		//接收参数
		int page=Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page").toString());
		int rows=Integer.parseInt(request.getParameter("rows")==null?"20":request.getParameter("rows").toString());
		boolean isLimit=Boolean.parseBoolean(request.getParameter("isLimit")==null?"true":request.getParameter("isLimit"));
		
		//填充分页模版
		PageBean pageBean = new PageBean();
		pageBean.setIsLimit(isLimit);
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		pageBean.setSqlStr(sql);
		
		return pageBean;
	}
	
	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String listtojson(int total, List<?> list){

		StringBuffer jsonTemp = new StringBuffer();
		jsonTemp.append("{\"total\":" + total + ",\"rows\":").append(ListToJson(list));
		jsonTemp.append("}");
		return jsonTemp.toString();
	}

	/**
	 * 控件类型：easyui
	 * 返回datagrid JSON数据
	 * @param response
	 * @param dataGrid
	 */
	public static void datagrid(HttpServletResponse response,DataGridReturn dataGridReturn) {
		response.setHeader("Cache-Control", "no-store");
		try {
			String json = listtojson(dataGridReturn.getTotal(), dataGridReturn.getRows());
			PrintWriter pw=response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拼装PageBean
	 * @param request
	 * @param sql
	 * @return
	 */
	public static PageBean getPageBean2(HttpServletRequest request,String sql){
		//接收参数
		int page =  Integer.parseInt(Tools.ConvNull(request.getParameter("pageIndex"),"0"));
		int rows = Integer.parseInt(Tools.ConvNull(request.getParameter("pageSize"),"20"));   
		boolean isLimit=Boolean.parseBoolean(request.getParameter("isLimit")==null?"true":request.getParameter("isLimit"));
		
		//填充分页模版
		PageBean pageBean = new PageBean();
		pageBean.setIsLimit(isLimit);
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		pageBean.setSqlStr(sql);
		
		return pageBean;
	}
	
	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String listtojson2(int total, List<?> list){

		StringBuffer jsonTemp = new StringBuffer();
		jsonTemp.append("{\"total\":" + total + ",\"data\":").append(ListToJson(list));
		jsonTemp.append("}");
		return jsonTemp.toString();
	}

	/**
	 * 控件类型：easyui
	 * 返回datagrid JSON数据
	 * @param response
	 * @param dataGrid
	 */
	public static void datagrid2(HttpServletResponse response,DataGridReturn dataGridReturn) {
		response.setHeader("Cache-Control", "no-store");
		try {
			String json = listtojson2(dataGridReturn.getTotal(), dataGridReturn.getRows());
			PrintWriter pw=response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public static String string2Json(String s) {     
	    StringBuffer sb = new StringBuffer ();     
	    for (int i=0; i<s.length(); i++) {     
	   
	        char c = s.charAt(i);     
	        switch (c) {     
	        case '\"':     
	            sb.append("\\\"");     
	            break;     
	        case '\\':     
	            sb.append("\\\\");     
	            break;     
	        case '/':     
	            sb.append("\\/");     
	            break;     
	        case '\b':     
	            sb.append("\\b");     
	            break;     
	        case '\f':     
	            sb.append("\\f");     
	            break;     
	        case '\n':     
	            sb.append("\\n");     
	            break;     
	        case '\r':     
	            sb.append("\\r");     
	            break;     
	        case '\t':     
	            sb.append("\\t");     
	            break;     
	        default:     
	            sb.append(c);     
	        }
	    }
	    return sb.toString();     
	 }
	
	public static String addElt(String xml,Map map){
		String result = xml;
		
		try {
			if(xml!=null && !xml.equals("")){
				Document doc = DocumentHelper.parseText(xml);
				Element rootElt = doc.getRootElement();
				if(map!=null){
					Iterator it = map.keySet().iterator();
					while(it.hasNext()){
						String key = (String)it.next();
						String value = (String)map.get(key);
						Element newElt = rootElt.addElement(key);
						newElt.setText(value);
					}
					result = doc.asXML();

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getResultMsg(String msgNo,String msg){
		String result = "";
		msg = string2Json(msg);
		result="{\"Result_msg\":{\"msgNo\":"+msgNo+",\"msgContent\":\""+msg+"\"}}";
		return result;
	}

	public static String getTimeZD(String ZDBH) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from tb_timezd where zdbh='"+ZDBH+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZDZHI").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static String getDWLEV(String zSSDWID) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select (to_number(ZDWLEV)+1) as ZDWLEV from tb_dwxx where zdwbh='"+zSSDWID+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZDWLEV").toString();
			}else{
				result = "1";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static String getZDWSBH(String zDWBH, String zDWLEV) {
		// TODO Auto-generated method stub
		String result = "";
		result = zDWBH.substring(0, Integer.parseInt(zDWLEV)*2);
		return result;
	}
	
	public static String getDwbhBySBH(String ZDWSBH){
		String result = "";
		int total = 10;
		int len = ZDWSBH.length();
		result = ZDWSBH;
		for(int i=0;i<(total-len);i++){
			result+="0";
		}
		return result;
	}
	public static boolean checkDate(String date,String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try{
            d = df.parse(date);
        }catch(Exception e){
            //如果不能转换,肯定是错误格式
            return false;
        }
        String s1 = df.format(d);
        // 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
        // "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
        // 逻辑上不对.
        return date.equals(s1);
    }
	
	public static String getDWSBH(String zSSDWID) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from tb_dwxx where zdwbh='"+zSSDWID+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZDWSBH").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static String getBmbh(String bmbh,String bhxb){//按部门编号 提取部分数据
		String[] bmbhtemp = bmbh.split(",");
		String bmbhretutn="";
		for(int i=0;i<bmbhtemp.length;i++){
			if(!bmbhtemp[i].equals(bhxb)){
				bmbhretutn+=bmbhtemp[i];
			}
		}
		return bmbhretutn;
	}
	
	public static String getBmmc(String bmName,String mcxb){//按部门名称  提取部分数据
		String[] bmNametemp = bmName.split(",");
		String bmNamereturn="";
		for(int i=0;i<bmNametemp.length;i++){
			if(!bmNametemp[i].equals(mcxb)){
				bmNamereturn+=bmNametemp[i];
			}
		}
		return bmNamereturn;
	}
	
	public static String getFXMC(String str){
		String result="";
		if(str.equals("1")){
			result="第一道防线";
		}else if(str.equals("2")){
			result="第二道防线";
		}else if(str.equals("3")){
			result="第三道防线";
		}
		return result;
	}
	
	public static String[] getYears(String KSSJ,String JSSJ){
		String[] years = null ;
		String year = "";
		int ks_year = Integer.parseInt(KSSJ);
		int js_year = Integer.parseInt(JSSJ);
		int len = js_year-ks_year;
		
		for(int i=0;i<=len;i++){
			String y = (ks_year+i)+"";
			year+=y;
			if(i!=len){
				year+=",";
			}
		}
		years = year.split(",");
		return years;
		
	}
	
	public static String getDWMC(String zcode) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from t_unit where zcode='"+zcode+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZNAME").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String getXQBH(String zcode) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from t_unit where zcode='"+zcode+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZXQBH").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String getXQDW(String zcode) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from t_unit where zcode='"+zcode+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZXQDW").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String getDWID(String zcode) {
		// TODO Auto-generated method stub
		String result="";
		ConnDataBase bean0 = new ConnDataBase();
		try {
			String sql = "select * from t_unit where zcode='"+zcode+"'";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				result = map.get("ZID").toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String EmptyToNull (String str) {
		// TODO Auto-generated method stub
		if(str!=null && str.equals("") ){
			return "null";
		}else {
			return str;
		}
	}
	
	public static Integer strToInteger(String str){
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static Double strToDouble(String str){
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static void psSetDouble(PreparedStatement ps,int index,Double value) throws SQLException{

		if(value!=null){
			ps.setDouble(index, value);
		}else{
			ps.setNull(index, Types.NULL);
		}
	}
	
	public static double getPercent(double x,double y,int scale){
		double result = 0.0;
		try {
			if(y!=0){
				result = Arith.div(x, y, scale)*100;
				result = Arith.div(result, 1, scale);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result; 
	}
	
	public static double getPercent(int x,int y,int scale){
		double result = result = getPercent(x*0.1,y*0.1,scale);

		return result;
	}
	
	public static int getNUM(List list){
		int result = 0;
		if(list!=null && list.size()>0){
			Map map = (HashMap)list.get(0);
			String NUM = map.get("NUM").toString();
			result = strToInteger(NUM);
		}
		return result;
	}
	
	public static List KhPx(List list){
		List result = null;
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size()-1;i++){//外层循环控制排序趟数
				for(int j=0;j<list.size()-1-i;j++){//内层循环控制每一趟排序多少次
					KhPxBean pxBean1 = (KhPxBean)list.get(j);
					double tempD1 = pxBean1.getFen();
					KhPxBean pxBean2 = (KhPxBean)list.get(j+1);
					double tempD2 = pxBean2.getFen();
					if(tempD1>tempD2){
						KhPxBean temp=pxBean1;
						pxBean1=pxBean2;
						pxBean2=temp;
						list.set(j, pxBean1);
						list.set(j+1, pxBean2);
					}
				}
			} 

		}
		result = list;
		return result;
	}
	public static String nyrsfm(String xtsj,int a){
		String fanhuizhi="";
		
		String xtsjsz[]=xtsj.split(" ");
		String nyr=xtsjsz[0];
		String nyrsz[]=nyr.split("-");
		String nian=nyrsz[0];//年
		String yue=nyrsz[1];//月
		String ri=nyrsz[2];//日
		
		
		
		
		if(a==1){
			fanhuizhi=nian;
		}
		if(a==2){
			fanhuizhi=yue;
		}
		if(a==3){
			fanhuizhi=ri;
		}
		
		if(a==123){
			fanhuizhi=nyr;
		}
		
		
		return fanhuizhi;
		
		
		
		
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
	    //获取某月最大天数
	    int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    //设置日历中月份的最大天数
	    cal.set(Calendar.DAY_OF_MONTH, lastDay);
	    //格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String lastDayOfMonth = sdf.format(cal.getTime());
	    return lastDayOfMonth;
	  }


}
