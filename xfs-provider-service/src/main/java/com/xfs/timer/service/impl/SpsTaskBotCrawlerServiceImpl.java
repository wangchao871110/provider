package com.xfs.timer.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xfs.common.redies.RedisUtil;
import com.xfs.sps.util.TaskBotHttpsAndHttpUtil;
import com.xfs.ts.service.SpsTaskBotCrawlerService;


@Service 
public class SpsTaskBotCrawlerServiceImpl implements SpsTaskBotCrawlerService {

	private static final Logger log = Logger.getLogger(SpsTaskBotCrawlerServiceImpl.class);
	
	
	//@Scheduled(cron = "0 0/5 * * * *")
	public void run(){
		try {
			Set<String> keysSet = RedisUtil.keys("taskbot_cid_cookie_*");
   for(String key:keysSet){
			   String cookie = RedisUtil.get(key).toString();
			   String url = RedisUtil.get("taskbot_cid_url_"+key.split("_")[3]).toString() ;
			   if(url.contains("login")){
				   continue;
			   }
			   try {
				validateCookie(cookie,url);
			} catch (Exception e) {
				RedisUtil.delete("taskbot_cid_url_"+key.split("_")[3]) ;
			}
   }
		} catch (Exception e) {
		 
		}
	}
	
	/*
	 * 验证cookie
	 */
	private void validateCookie(String cookie,String url){
		TaskBotHttpsAndHttpUtil.createSSLClientDefault(url, cookie);
	}
	
}
