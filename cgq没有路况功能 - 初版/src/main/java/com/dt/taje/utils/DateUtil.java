package com.dt.taje.utils;

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
	 * 得到本周周日
	 * 
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
	public static String getLastDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		// System.out.println("一个月最后一天" + result);
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
	public static String getFristDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		// System.out.println("一个月第一天" + result);
		return result;
	}

	/**
	 * 得到本年最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, days);
		String result = format.format(cal.getTime());
		// System.out.println("本年最后一天" + result);
		return result;
	}

	/**
	 * 得到本年第一天
	 * 
	 * @return
	 */
	public static String getFristDateOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, days);
		String result = format.format(cal.getTime());
		// System.out.println("本年第一天" + result);
		return result;
	}

	/**
	 * 得到昨天日期
	 * 
	 * @return
	 */
	public static String getYesterDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DATE, -1);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 得到10天前的日期
	 * 
	 * @return
	 */
	public static String get10Day() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DATE, -9);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 得到当前时间的上一个月是那一个月
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.MONTH, -1);
		String result = cal.get(Calendar.MONTH) + 1 + "";
		return result;
	}

	/**
	 * 得到当前时间的上一个月是那一年
	 * 
	 * @return
	 */
	public static String getLastMonthIsWhichYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.MONTH, -1);
		String result = cal.get(Calendar.YEAR) + "";
		return result;
	}

	/**
	 * 取得一段时间内的日期字符串
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static String[] getDays(String day1, String day2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = format.parse(day1);
			Date d2 = format.parse(day2);
			long milliseconds = d2.getTime() - d1.getTime();
			long days = milliseconds / (long) (1000 * 60 * 60 * 24);// 计算天数之差

			String str = "";
			for (int i = 0; i <= days; i++) {
				Date d = d1;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				calendar.set(Calendar.DATE, calendar.get(calendar.DATE) + i);// 日期累加
				d = calendar.getTime();
				if (i == days) {
					str += format.format(d);
				} else {
					str += format.format(d) + ",";
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
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 * 
	 * @return
	 */
	public static String getCurrentQuarterStartTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = format.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 * 
	 * @return
	 */
	public static String getCurrentQuarterEndTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = format.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return now;
	}

	/**
	 * 得到明天
	 * 
	 * @return
	 */
	public static String getTomorroyDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DATE, 1);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 取得任意一天的上周周一
	 * 
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfLastWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();// 获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, -1);// 时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得任意一天的上周周日
	 * 
	 * @param day
	 * @return
	 */
	public static String getLastDayOfLastWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();// 获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, -1);// 时间设置为上个星期的今天
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		cal.add(Calendar.DATE, -dayofweek + 7);
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得任意一天的上周同期
	 * 
	 * @param day
	 * @return
	 */
	public static String getDayOfLastWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();// 获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.WEEK_OF_MONTH, -1);// 时间设置为上个星期的今天
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得任意一天的上月同期
	 * 
	 * @param day
	 * @return
	 */
	public static String getDayOfLastMonth(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();// 获得当前日期
		cal.setTime(dt);
		cal.add(Calendar.MONTH, -1);// 时间设置为上个星期的今天
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 得到N分钟前的时间
	 */
	public static String getNfenZhong(int N){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		c=Calendar.getInstance();
		c.add(Calendar.MINUTE, -N);//N分钟前
        return sdf.format(c.getTime());
	}
	
	/**
	  * 得到上月第一天
	  * 
	  * @return
	  */
	 public static String getFristDateOfLastMonth()
	 {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = getDate();

	  if (dt == null)
	   return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(dt);
	  cal.add(Calendar.MONTH, -1);
	  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, days);
	  String result = format.format(cal.getTime());
	  //System.out.println("一个月第一天" + result);
	  return result;
	 }
		/**
		 * 得到上月最后一天
		 * 
		 * @return
		 */
		public static String getLastDateOfLastMonth() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = getDate();

			if (dt == null)
				return null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.add(Calendar.MONTH, -1);
			int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, days);
			String result = format.format(cal.getTime());
			// System.out.println("一个月最后一天" + result);
			return result;
		}
		
		/**
		  * 得到任意一天的上月第一天
		  * 
		  * @return
		  */
		 public static String getFristDateOfLastMonth(String day)
		 {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date dt = null;
		  try {
				dt = format.parse(day);
		  } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(dt);
		  cal.add(Calendar.MONTH, -1);
		  int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		  cal.set(Calendar.DAY_OF_MONTH, days);
		  String result = format.format(cal.getTime());
		  //System.out.println("一个月第一天" + result);
		  return result;
		 }
			/**
			 * 得到任意一天的的上月最后一天
			 * 
			 * @return
			 */
			public static String getLastDateOfLastMonth(String day) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				  Date dt = null;
				  try {
						dt = format.parse(day);
				  } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				  }
				Calendar cal = Calendar.getInstance();
				cal.setTime(dt);
				cal.add(Calendar.MONTH, -1);
				int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, days);
				String result = format.format(cal.getTime());
				// System.out.println("一个月最后一天" + result);
				return result;
			}
		
			/**
			 * 得到任意一天的昨天日期
			 * 
			 * @return
			 */
			public static String getDayOfYesterDay(String day) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date dt = null;
			    try {
			  		  dt = format.parse(day);
			    } catch (ParseException e) {
					  // TODO Auto-generated catch block
					  e.printStackTrace();
			    }
				Calendar cal = Calendar.getInstance();
				cal.setTime(dt);
				int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
				cal.add(Calendar.DATE, -1);
				String result = format.format(cal.getTime());
				return result;
			}
			/**
			 * 得到明天
			 * 
			 * @return
			 */
			public static String getTomorroyDay(String day) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = null;
			    try {
			  		  dt = format.parse(day);
			    } catch (ParseException e) {
					  // TODO Auto-generated catch block
					  e.printStackTrace();
			    }
				Calendar cal = Calendar.getInstance();
				cal.setTime(dt);
				int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
				cal.add(Calendar.DATE, 1);
				String result = format.format(cal.getTime());
				return result;
			}

}
