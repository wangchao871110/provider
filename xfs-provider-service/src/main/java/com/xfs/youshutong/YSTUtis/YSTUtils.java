package com.xfs.youshutong.YSTUtis;/**
 * @author hongjie
 * @date 2017/5/18.11:38
 */

import com.xfs.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author
 * @create 2017-05-18 11:38
 **/
public class YSTUtils {

    public final static String SAASUTILS = "yyxinfushe02";
    public final static String   PROFESSIONALSERVICES = "yyxinfushe01";
    public final static String XINFUSHE = "XINFUSHE";

    public final static String XINFUSHE_PRODUCT_CODE = "Y016";


    public final static String CLIENTSECRET = "48b05o5a2d264312bc7dc01h28a8ccd0";

    public static String getDateString(String date) {
        Date date1 = DateUtil.parseDate(date, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMddHHmmss");
        return yyyyMMdd.format(date1);
    }

    public static String getDateString(Date date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMddHHmmss");
        return yyyyMMdd.format(date);
    }


    public static String getRandomDateString(String date) {
        date = StringUtils.substring(date, 0, 10);
        date = date + getRandomString() + getRandomString();
        return date;
    }


    private static String getRandomString() {
        Random random = new Random();
        int a = random.nextInt(6);
        int b = random.nextInt(10);
        return a + "" + b;
    }



    /**
     * 生成随机时间
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date randomDate(String beginDate, String endDate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date start = format.parse(beginDate);//构造开始日期

            Date end = format.parse(endDate);//构造结束日期

//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

            if (start.getTime() >= end.getTime()) {

                return null;

            }

            long date = random(start.getTime(), end.getTime());

            return new Date(date);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    private static long random(long begin, long end) {

        long rtn = begin + (long) (Math.random() * (end - begin));

//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

        if (rtn == begin || rtn == end) {

            return random(begin, end);

        }

        return rtn;

    }

    public static void main(String[] args) {
        Date randomDate = randomDate("2017-05-31", "2017-06-30");
        System.out.println(DateUtil.getTimeDate(randomDate));
    }

}
