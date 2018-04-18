package com.xfs.watching.service;

import java.util.Map;

import com.xfs.common.Result;
/**
 * 监控接口
 *
 * @author lifq
 *
 * 2017年12月5日 上午11:43:24
 */
public interface WatchingService {
	
	
	/**
     * 初始化 待监控的url
     *
     * @author lifq
     *
     * 2017年12月7日 下午3:48:24
     */
    public void initUrl();
	
	/**
	 * 登录网站
	 *
	 * @author lifq
	 *
	 * 2017年12月5日 上午11:44:28
	 */
    public Result login();
	
	/**
	 * 监控网站  ，通过状态码
	 * 
	 * @para url:网站url
	 * @para title:网站名称
	 * @para expectedStatus:预期状态码
	 *
	 * @author lifq
	 *
	 * 2017年12月5日 上午11:43:59
	 */
    public Result checkUrlByStatus(String url,String title,int expectedStatus,Map<String, String> headerMap);
    
    /**
     * 监控网站  ，通过关键字
     *
     * @author lifq
     * 
     * @para url:网站url
	 * @para title:网站名称
	 * @para expectedKeyWord:预期含有的关键字
	 * 
     * 2017年12月7日 下午4:07:12
     */
    public Result checkUrlByKeyWord(String url,String title,String expectedKeyWord,Map<String, String> headerMap);
    
    
}
