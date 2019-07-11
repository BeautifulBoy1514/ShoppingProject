package com.igeek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类，封装了日期的常用处理方法
 * @author Jia
 */
public class DateUtil {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * 将Date转换成String
     * @param date
     * @return
     */
    public String parseDateToString(Date date){
        String time = format.format(date);
        return time;
    }
    /**
     * 将String转换成Date
     * @param dateStr
     * @return
     */
    public Date parseStringToDate(String dateStr){
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
