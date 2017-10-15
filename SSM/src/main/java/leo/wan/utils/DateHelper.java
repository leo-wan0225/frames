package leo.wan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateHelper {
	/**
	 * 将时间转换为要求格式的字符串返回
	 * @param date 要转换的Date对象
	 * @param Pattern 时间字符串格式
	 * @return 时间字符串
	 */
	public static String dateToString(Date date,String Pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(Pattern);  
		return sdf.format(date);
	}
	/**
	 * 字符串转换为对应的Date对象
	 * @param dateStr 字符串
	 * @param Pattern 时间字符串格式
	 * @return Date对象
	 */
	public static Date strToDate(String dateStr,String Pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(Pattern);  
		try {
			return	sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	
	
	/**
	 * 获取现在时间
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
    public static String getStringFullDate() {  
    	return dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");  
    }  
  
    /**
     * 获取现在时间
     * @return 返回字符串格式  yyyyMMddHHmmssSSS
     */
    public static String getDateTimeForFileName() {  
    	return dateToString(new Date(),"yyyyMMddHHmmssSSS");  
    }  
    /** 
     * 获取现在时间 
     * @return 返回短时间字符串格式yyyy-MM-dd 
     */  
    public static String getStringDateShort() {  
     return dateToString(new Date(),"yyyy-MM-dd"); 
    }  
    /**
     * 字符串转换为Date对象
     * @param dateString 待转换的字符串
     * @return Date对象，格式为：yyyy-MM-dd
     */
    public static Date strToDate(String dateString){
    	return strToDate(dateString,"yyyy-MM-dd");
    }
    /**
     * 字符串转换为Date对象
     * @param dateString 待转换的字符串
     * @return Date对象，格式为：yyyy-MM-dd HH：mm:ss
     */
    public static Date strToDateWithTime(String dateString){
    	return strToDate(dateString,"yyyy-MM-dd HH:mm:ss");
    }
  
    /**
     * 获取当前时间
     * @return 返回短时间字符串格式HH:mm:ss
     */
    public static String getTimeShort() {  
     return dateToString(new Date(),"HH:mm:ss");  
    }  
	
	
	/**
	 * 得到某年某月周末是几号
	 * @param year 年份
	 * @param month 月份
	 * @return 周末号数集合
	 */
	public static List getWeekendInMonth(int year, int month) {
        List<Integer> list = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年			
        calendar.set(Calendar.MONTH, month - 1);// 设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
        int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
        for (int i = 0; i < daySize; i++) {
            calendar.add(Calendar.DATE, 1);//在第一天的基础上加1
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {// 1代表周日，7代表周六 判断这是一个星期的第几天从而判断是否是周末
                list.add(calendar.get(Calendar.DAY_OF_MONTH));// 得到当天是一个月的第几天
            }
        }
        return list;
    }
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合ss
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
	    List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	/**
	 * 计算俩个时间相差多少天  ,字符串格式为yyyy-MM-dd，精确到天
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 相差天数
	 * @throws Exception
	 */
    public static double getDistanceDays(String startDate, String endDate) throws Exception{  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return getDistanceDays(sdf.parse(startDate),sdf.parse(endDate));
    }  
	/**
	 * 计算俩个时间相差多少天
	 * @param startDate 开始时间 
	 * @param endDate 结束时间
	 * @return 相差天数
	 * @throws Exception
	 */
    public static double getDistanceDays(Date startDate, Date endDate) throws Exception{  
		double diff =endDate.getTime() -  startDate.getTime(); 
		double days = diff / (1000 * 60 * 60 * 24);  
        return days;  
    }  
    /**
     * 技术俩个时间相差多少天多少小时多少分多少秒 ,字符串格式为 yyyy-MM-dd HH:mm:ss
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 数组：{天, 时, 分, 秒} 
     * @throws ParseException 
     */
    public static long[] getDistanceTimesArray(String startDate, String endDate) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return getDistanceTimesArray(sdf.parse(startDate),sdf.parse(endDate));
    	
    }
    /**
     * 计算俩个日期相差多少天多少小时多少分多少秒,字符串格式为 yyyy-MM-dd HH:mm:ss
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 字符串：xx天xx小时xx分xx秒 
     * @throws ParseException 
     */
    public static String getDistanceTimesString(String startDate, String endDate) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return getDistanceTimesString(sdf.parse(startDate),sdf.parse(endDate));
    }
    /**
     * 技术俩个时间相差多少天多少小时多少分多少秒 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 数组：{天, 时, 分, 秒} 
     */
    public static long[] getDistanceTimesArray(Date startDate, Date endDate) {  
		long end = endDate.getTime();  
		long diff = endDate.getTime() -startDate.getTime() ;  
		long day = diff / (24 * 60 * 60 * 1000);  
		long hour = (diff / (60 * 60 * 1000) - day * 24);  
		long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
		long sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        long[] times = {day, hour, min, sec};  
        return times;  
    }  
    /**
     * 计算俩个日期相差多少天多少小时多少分多少秒
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 字符串：xx天xx小时xx分xx秒 
     */
    public static String getDistanceTimesString(Date startDate, Date endDate) {  
    	long diff = endDate.getTime() -startDate.getTime() ;  
          long  day = diff / (24 * 60 * 60 * 1000);  
        long    hour = (diff / (60 * 60 * 1000) - day * 24);  
        long    min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
          long  sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
       
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";  
    }  
	
	private DateHelper(){
		
	}
}
