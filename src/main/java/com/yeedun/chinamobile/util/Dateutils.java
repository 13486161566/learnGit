package com.yeedun.chinamobile.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dateutils {

	/**
	 * 获取某月最后一天
	 * @param datadate
	 * @return
	 * @throws Exception
	 */
	public static String getLastDay(Date date, String formatStr)throws Exception{
        String day_last = null;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);//加一个月
        calendar.set(Calendar.DATE, 1);//设置为该月第一天
        calendar.add(Calendar.DATE, -1);//再减一天即为上个月最后一天
        day_last = format.format(calendar.getTime());
        return day_last;
    }
    
	/**
	 * 获取某月第一天
	 * @param datadate
	 * @return
	 * @throws Exception
	 */
    public static String getFirstDay(Date date, String formatStr)throws Exception{
        String day_first = null;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        day_first = format.format(calendar.getTime());
        return day_first;
    }
}
