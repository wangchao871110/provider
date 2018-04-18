package com.xfs.ss.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.xfs.common.util.CustomizedPropertyPlaceholderConfigurer;

/**
 * 名称：
 * 功能：
 * 作者: 张智  jgszz@163.com
 * 时间: 16-5-16  下午5:56
 */
public class SocketConf {
	/**
	 * if true, then system runs in debug mode.
	 */
	public final static boolean isDEBUG;
	/**
	 * if true, system can run backend jobs. each backend service should check this switch.
	 */
	public final static boolean RUN_JOB;
	private static PropertiesConfiguration configuration =null;
	static{
//		try {
//			configuration = new PropertiesConfiguration();  
//			configuration.setEncoding("utf8");
//			configuration.load("config/all.properties");
//		} catch (ConfigurationException e) {
//			e.printStackTrace();
//		}

		if ("true".equals(CustomizedPropertyPlaceholderConfigurer.getContextProperty("is_debug"))) {
			isDEBUG=true;
		} else {
			isDEBUG=false;
		}
				
		if("true".equals(CustomizedPropertyPlaceholderConfigurer.getContextProperty("run_job"))){
			RUN_JOB=true;
		}else{
			RUN_JOB=false;
		}
	}

	public static String getProperty(String key) {
		return (String) CustomizedPropertyPlaceholderConfigurer.getContextProperty(key);
	}
	
}
