package com.dt.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static String getMondayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (dayofweek == 0)
	   dayofweek = 7;
	  c.add(Calendar.DATE, -dayofweek + 1);
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  return sdf.format(c.getTime());
	 }

	/**
	  *得到本周周日 
	  * @return yyyy-MM-dd
	  */
	 public static String getSundayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (dayofweek == 0)
	   dayofweek = 7;
	  c.add(Calendar.DATE, -dayofweek + 7);
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  return sdf.format(c.getTime());
	 }

	/**
	  * 得到本月最后一天
	  * 
	  * @return
	  */
	 public static String getLastDateOfMonth()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(dt);
	  int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, days);
	  String result = format.format(cal.getTime());
	  //System.out.println("一个月最后一天" + result);
	  return result;
	 }

	 private static Date getDate() {
		// TODO Auto-generated method stub
		return new Date();
	}

	/**
	  * 得到本月第一天
	  * 
	  * @return
	  */
	 public static String getFristDateOfMonth()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(dt);
	  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, days);
	  String result = format.format(cal.getTime());
	  //System.out.println("一个月第一天" + result);
	  return result;
	 }
	/**
	  * 得到本年最后一天
	  * 
	  * @return
	  */
	 public static String getLastDateOfYear()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(dt);
	  int days = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	  cal.set(Calendar.DAY_OF_YEAR, days);
	  String result = format.format(cal.getTime());
	  //System.out.println("本年最后一天" + result);
	  return result;
	 }
	/**
	  * 得到本年第一天
	  * 
	  * @return
	  */
	 public static String getFristDateOfYear()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(dt);
	  int days = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
	  cal.set(Calendar.DAY_OF_YEAR, days);
	  String result = format.format(cal.getTime());
	  //System.out.println("本年第一天" + result);
	  return result;
	 }
	/**
	  * 得到昨天日期
	  * 
	  * @return
	  */
	 public static String getYesterDay()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
      cal.setTime(dt);																																								  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	  cal.add(Calendar.DATE, -1);
	  String result = format.format(cal.getTime());
	  return result;
	 }
	/**
	  * 得到当前时间的上一个月是那一个月
	  * 
	  * @return
	  */
	 public static String getLastMonth(){
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
      cal.setTime(dt);																																								  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	  cal.add(Calendar.MONTH, -1);
	  String result = cal.get(Calendar.MONTH)+1+"";
	  return result;
	 }
	/**
	  * 得到当前时间的上一个月是那一年
	  * 
	  * @return
	  */
	 public static String getLastMonthIsWhichYear(){
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
      cal.setTime(dt);																																								  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	  cal.add(Calendar.MONTH, -1);
	  String result = cal.get(Calendar.YEAR)+"";
	  return result;
	 }
	 /**
	  * 取得一段时间内的日期字符串
	  * @param day1
	  * @param day2
	  * @return
	  */
	public static String[] getDays(String day1,String day2){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			Date d1 =  format.parse(day1);
			Date d2 =  format.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24);//计算天数之差
			
			String str = "";	
			for(int i = 0;i<=days;i++){
				Date d = d1;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				calendar.set(Calendar.DATE, calendar.get(calendar.DATE) + i);//日期累加
				d=calendar.getTime();
				if(i==days){
					str += format.format(d);
				}else{
					str += format.format(d)+",";
				}
			}
			return str.split(",");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	
	 /**
	  * 取得一段时间内的日期字符串
	  * @param day1
	  * @param day2
	  * @return
	  */
	public static String[] getDaysHours(String day1,String day2){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		 try {
			Date d1 =  format.parse(day1);
			Date d2 =  format.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60);//计算小时数之差
			
			String str = "";	
			for(int i = 0;i<=days;i++){
				Date d = d1;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				calendar.set(Calendar.HOUR, calendar.get(calendar.HOUR) + i);//小时累加
				d=calendar.getTime();
				if(i==days){
					str += format.format(d);
				}else{
					str += format.format(d)+",";
				}
			}
			return str.split(",");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	/**
	 * 获取月数的差
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String[] getMonth(String day1,String day2){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		 try {
			Date d1 =  format.parse(day1);
			Date d2 =  format.parse(day2);
			/*long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24 );//计算小时数之差
*/		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	        Calendar bef = Calendar.getInstance();
	        Calendar aft = Calendar.getInstance();
	        try {
				bef.setTime(sdf.parse(day2));
				aft.setTime(sdf.parse(day1));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
	        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
	        int days = Math.abs(month + result);
	        
			String str = "";	
			for(int i = 0;i<=days;i++){
				Date d = d1;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				calendar.set(Calendar.MONTH, calendar.get(calendar.MONTH) + i);//小时累加
				d=calendar.getTime();
				if(i==days){
					str += format.format(d);
				}else{
					str += format.format(d)+",";
				}
			}
			return str.split(",");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	
	
	/**
	 * 取得两个日期之间相差的天数
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static int getDiffDays(String day1,String day2){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 =  format.parse(day1);
			Date d2 =  format.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24);//计算天数之差
			return (int)days;
		} catch (ParseException e) {
			return 0;
		}
	}
	/**
	 * 取得任意一天的上周周一
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfLastWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, -1);//时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
		   dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 1);
		return sdf.format(cal.getTime());
	}
	/**
	 * 取得任意一天的上周周日
	 * @param day
	 * @return
	 */
	public static String getLastDayOfLastWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, -1);//时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
		   dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 7);
		return sdf.format(cal.getTime());
	}
	/**
	 * 取得任意一天的下周周一
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfNextWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, 1);//时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
		   dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 1);
		return sdf.format(cal.getTime());
	}
	/**
	 * 取得任意一天的下周周日
	 * @param day
	 * @return
	 */
	public static String getLastDayOfNextWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, 1);//时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
		   dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 7);
		return sdf.format(cal.getTime());
	}
	/**
	 * 取得任意一天的上个月第一天
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfLastMonth(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.MONTH, -1);
	    int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, days);
	    String result = sdf.format(cal.getTime());
	    return result;
	}
	/**
	 * 取得任意一天的上个月最后一天
	 * @param day
	 * @return
	 */
	public static String getLastDayOfLastMonth(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.MONTH, -1);
	    int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, days);
	    String result = sdf.format(cal.getTime());
	    return result;
	}
	/**
	 * 取得任意一天的下个月第一天
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfNextMonth(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.MONTH, 1);
	    int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, days);
	    String result = sdf.format(cal.getTime());
	    return result;
	}
	/**
	 * 取得任意一天的下个月最后一天
	 * @param day
	 * @return
	 */
	public static String getLastDayOfNextMonth(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.MONTH, 1);
	    int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, days);
	    String result = sdf.format(cal.getTime());
	    return result;
	}
	
	/**
	 * 根据时间段，向前取天数相同的时间段
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String[] getPreviousDays(String day1,String day2){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 =  sdf.parse(day1);
			Date d2 =  sdf.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24);//计算天数之差
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d1);
			cal1.set(Calendar.DATE, cal1.get(cal1.DATE) - ((int)days+1));//日期累加
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(d2);
			cal2.set(Calendar.DATE, cal2.get(cal2.DATE) - ((int)days+1));//日期累加
			return new String[]{sdf.format(cal1.getTime()),sdf.format(cal2.getTime())};
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据时间段，向后取天数相同的时间段
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String[] getNextDays(String day1,String day2){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 =  sdf.parse(day1);
			Date d2 =  sdf.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24);//计算天数之差
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d1);
			cal1.set(Calendar.DATE, cal1.get(cal1.DATE) + ((int)days+1));//日期累加
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(d2);
			cal2.set(Calendar.DATE, cal2.get(cal2.DATE) + ((int)days+1));//日期累加
			return new String[]{sdf.format(cal1.getTime()),sdf.format(cal2.getTime())};
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据时日期，取得改日期的前一天
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String getPreviousDay(String day){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d =  sdf.parse(day);

			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.set(Calendar.DATE, cal.get(cal.DATE) - 1);//日期累加
			
			return sdf.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据时日期，取得改日期的后一天
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String getNextDay(String day){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d =  sdf.parse(day);

			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.set(Calendar.DATE, cal.get(cal.DATE) + 1);//日期累加
			
			return sdf.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取得指定时间的前一个小时的时间
	 * @param nowTime
	 * @return
	 */
	public static String getOneHoursAgoTime(String nowTime){
		try {
			String OneHoursAgoTime = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d =  sdf.parse(nowTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.set(Calendar.HOUR,cal.get(cal.HOUR) - 1);
			OneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
			return OneHoursAgoTime;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取得任意一天的当月最后一天
	 * @param day
	 * @return
	 */
	public static String getLastDayOfMonth(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal= Calendar.getInstance();//获得当前日期
		cal.setTime(dt);
	    int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, days);
	    String result = sdf.format(cal.getTime());
	    return result;
	}

}
