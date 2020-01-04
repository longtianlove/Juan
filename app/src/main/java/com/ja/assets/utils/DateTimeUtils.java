package com.ja.assets.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 19428 on 2017/5/19.
 */


public class DateTimeUtils {
    public String getNowDateTime(String State) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(State);// 可以方便地修改日期格式
        return dateFormat.format(now);
    }


    public String getBeiJingTime(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    //获取昨天的时间
    public String getYesterDay(String State) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat(State).format(time);

    }
    //将年月日转换成我们自己需要的格式

    public String getUserData(String userData) {
        return userData.substring(0, 4) + "年" + userData.substring(4, 6) + "月" + userData.substring(6, 8) + "日";
    }

    //将当前的日期先转换为年月日格式
    public String getUserNowData(String userData) {
        String month = String.valueOf(Integer.parseInt(userData.substring(4, 6)));
        String day = String.valueOf(Integer.parseInt(userData.substring(6, 8)));
        return userData.substring(0, 4) + "年" + month + "月" + day + "日";
    }


//    //获取农历日期
//    public static String getLunarDate(String gregorianDate) {
//        //获取当前的农历日期
//        int[] lunarDate = LunarCalendar.solarToLunar(Integer.parseInt(gregorianDate.substring(0, 4)), Integer.parseInt(gregorianDate.substring(4, 6)),
//                Integer.parseInt(gregorianDate.substring(6, 8)));
//        return "(" + UppercaseUtil.UppercaseData(lunarDate[1] + "", lunarDate[2] + "") + ")";
//    }


    public String getConversionTime(String beginDate, String State) {
        SimpleDateFormat sdf = new SimpleDateFormat(State);
        return sdf.format(new Date(Long.parseLong(beginDate)));
    }


    /**
     * 获取今天星期几
     *
     * @param datetime
     * @return
     */
    public int dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * 获取本周的初始时间
     *
     * @param date
     * @return
     */
    public String getTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        System.out.println("所在周星期一的日期：" + imptimeBegin);
//        cal.add(Calendar.DATE, 6);
//        String imptimeEnd = sdf.format(cal.getTime());
//        System.out.println("所在周星期日的日期：" + imptimeEnd);
//        return imptimeBegin + "," + imptimeEnd;
        return imptimeBegin;
    }

    /**
     * 获取半月的初始时间
     *
     * @param
     * @return
     */
    public String getHalfMonthStartDate() {
        if (Integer.parseInt(getNowDateTime("dd")) > 15) {
            return getNowDateTime("yyyy-MM") + "-16";
        } else {
            return getNowDateTime("yyyy-MM") + "-01";
        }
    }

    /**
     * 获取本月的初始时间
     *
     * @param
     * @return
     */
    public String getMonthStartDate() {
        return getNowDateTime("yyyy-MM") + "-01";
    }


}
