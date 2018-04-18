package com.xfs.ss.util;

/**
 * 字符串工具类
 * 
 * @author zhangzhi
 * Time: 2011-4-28 下午04:05:11
 * Email: zhangzhi.bj@gmail.com
 *
 */
public class StrUtil {
	public static String getBTablename(String tablename){
		if(null==tablename || "".endsWith(tablename))return tablename;
		String re = "";
		String ps[] = tablename.toLowerCase().split("_");
		
		for(String p : ps){
			re += getFirstB(p);
		}
		
		return re;
	}
	
	public static String getFirstB(String s){
		if(null==s || "".equals(s))return s;
		
		return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	public static String getFirstBB(String s){
		if(null==s || "".equals(s))return s;
		
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	

}
