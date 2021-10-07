package com.blue.blueplanserviceapplicationpc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 将时间字符串转换为时间戳
     * @param s 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long timeToStamp(String s){
        Date date =null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return ts;
    }
    /**
     * 将时间戳转换为时间字符串格式
     * @param s
     * @return 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String stampToTime(String s){
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }
    /**
     * 时间转字符串
     * @param date
     * @return 格式 ： yyyy-MM-dd HH:mm:ss
     */
    public static String dateToTime(Date date) {
        return  simpleDateFormat.format(date);
    }
    /**
     * 时间转字符串
     * @param date
     * @return 格式 ： yyyy-MM-dd
     */
    public static String dateToTime1(Date date) {
        return  sdf.format(date);
    }
    /**
     * 字符串转时间
     * @param s 格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date timeToDate(String s) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转时间
     * @param s 格式 yyyy-MM-dd
     * @return
     */
    public static Date timeToDate1(String s) {
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转时间
     * @param s 格式 yyyy-MM-dd
     * @return
     */
    public static Date timeToDate2(String s) throws Exception{
        Date date = null;
        sdf = new SimpleDateFormat("yyyy#MM#dd");
        date = sdf.parse(s);
        return date;
    }


    public static void main(String[] args) {
        System.out.println(timeToStamp("2019-04-10 15:22:59"));
        System.out.println(stampToTime("1554880979285"));

    }
}
