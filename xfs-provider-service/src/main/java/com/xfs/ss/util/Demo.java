package com.xfs.ss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wangxuesong
 * Date: 16-10-26
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
public class Demo {


    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        int end_day = 9;
        String current_end_day =  format.format(new Date()) + "-" + end_day;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(current_end_day);
            System.out.print(sdf.format(date));


            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                end_day--;
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                end_day--;
                end_day--;
            }
        } catch (ParseException e) {



        }


    }






}
