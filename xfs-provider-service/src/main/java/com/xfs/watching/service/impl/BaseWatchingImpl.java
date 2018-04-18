package com.xfs.watching.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.common.Result;
import com.xfs.common.crawler.HttpUtil;
import com.xfs.common.crawler.ResponseResult;
import com.xfs.common.email.util.MailUtil;
import com.xfs.common.email.vo.Email;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Config;
import com.xfs.watching.service.WatchingService;

/**
 * 监控薪福社网站 基类
 *
 * @author lifq
 *
 * 2017年12月5日 上午10:59:15
 */
public class BaseWatchingImpl implements WatchingService{
	/**
	 * 记录日志
	 */
	private Logger log = Logger.getLogger(BaseWatchingImpl.class);
	
	/**
     * 所有实现类 实例list
     */
	public static List<BaseWatchingImpl> watchingObjList = new ArrayList<>();
    

	/**
	 * 未登录页面 JSONArray
	 */
	protected JSONArray noLoginUrlArray = null;
	
	/**
	 * 需要 登录页面 JSONArray
	 */
	protected JSONArray needLoginUrlArray = null;
	
	/**
     * http响应正常 (200)
     */
    public static final int HTTP_STATUS_OK = 200;
    /**
     * http响应 跳转 (302)
     */
    public static final int HTTP_STATUS_MOVED = 302;
    
    /**
     * 注册实例
     */
    @PostConstruct
    public void registEvent(){
    	watchingObjList.add(this);
    }
    
    /**
     * 读取配置的url初始化到map
     *
     * @author lifq
     *
     * 2017年12月11日 上午10:59:15
     */
    @PostConstruct
    @Override
	public void initUrl() {
    	String simpleClassName = this.getClass().getSimpleName();
    	String configStr =  Config.getProperty(simpleClassName);
    	log.info("simpleClassName="+simpleClassName+",configStr="+configStr);
    	if(StringUtils.isNotBlank(configStr)){
    		JSONObject jsonObj = JSON.parseObject(configStr);
    		//处理未登录map
    		if(null!=jsonObj.getJSONArray("noLoginUrlMap")){
    			noLoginUrlArray = jsonObj.getJSONArray("noLoginUrlMap");
    		}
    		//处理需要登录map
    		if(null!=jsonObj.getJSONArray("needLoginUrlMap")){
    			needLoginUrlArray = jsonObj.getJSONArray("needLoginUrlMap");
    		}
    	}
    	
	}
    
    
    /**
     * 实现了官网登录，其他登录 自己继承此类实现
     *
     * @author lifq
     *
     * 2017年12月7日 上午10:59:15
     */
	@Override
	public synchronized Result login() {
		Result result = Result.createResult().setSuccess(false);
		String xfsLoginUrl = Config.getProperty("watching_xfsLoginUrl");
	    String yonyouLoginUrl = Config.getProperty("watching_yonyouLoginUrl");
	    String userCenterUrl = Config.getProperty("watching_userCenterUrl");
	    String xfsLoginExpectedKeyWord = Config.getProperty("watching_xfsLogin_expectedKeyWord");//xfs登录成功期望关键字
	    
		try {
			String cookie = "";
		    
			ResponseResult responseResultXfs1 = HttpUtil.doGet(xfsLoginUrl, null);
			cookie = responseResultXfs1.getCookies();
			
			List<NameValuePair> paramsList = new ArrayList<>();
			paramsList.add(new BasicNameValuePair("tenantCode", "default"));
			paramsList.add(new BasicNameValuePair("username", "lifuqiang412@163.com"));
			paramsList.add(new BasicNameValuePair("sysid", "xfs"));
			paramsList.add(new BasicNameValuePair("tenantid", "-1"));
			paramsList.add(new BasicNameValuePair("lt", "LT-2-keaMyYygjLPVVU73hsioSirdimkVG5-cas01.example.org"));
			paramsList.add(new BasicNameValuePair("_eventId", "submit"));
			paramsList.add(new BasicNameValuePair("tokeninfo", "null"));
			paramsList.add(new BasicNameValuePair("isAutoLogin", "0"));
			paramsList.add(new BasicNameValuePair("randomvalue", "1494307679324"));
			paramsList.add(new BasicNameValuePair("shaPassword", "510a9343c872614c4dc95741861524d967db7879"));
			paramsList.add(new BasicNameValuePair("md5Password", "0900880084f494273d27b1c44c45a78e"));
			paramsList.add(new BasicNameValuePair("validateCode", ""));
			paramsList.add(new BasicNameValuePair("validateKey", "1494307694000"));
			paramsList.add(new BasicNameValuePair("yourcasurl", xfsLoginUrl));
			ResponseResult responseResult =  HttpUtil.doPost(yonyouLoginUrl, paramsList, null, "UTF-8");
			log.info("responseResult="+responseResult.getResult());
			String ticketUrl = "";
			if(StringUtils.isNotBlank(responseResult.getResult())){
				JSONObject jsonObj = JSON.parseObject(responseResult.getResult());
				ticketUrl = jsonObj.getString("url");
				if(StringUtils.isNotBlank(ticketUrl)){
					Map<String, String> headerMap = new HashMap<>();
					headerMap.put("cookie", cookie);
					ResponseResult responseResultXfs2 = HttpUtil.doGet(ticketUrl, headerMap);
					log.info("responseResultXfs2="+responseResultXfs2);
					ResponseResult responseResultXfs3 = HttpUtil.doGet(userCenterUrl, headerMap);
					log.info("responseResultXfs3="+responseResultXfs3);
					if(responseResultXfs3.getResult().contains(xfsLoginExpectedKeyWord)){
						log.info("官网登录成功！");
						result.setSuccess(true);
						result.setDataValue("cookie", cookie);
						result.setMsg("官网登录成功！");
					}
				}
			}
			if(!result.isSuccess()){
				sendMsg("官网登录报错！",xfsLoginUrl);
			}
		} catch (Exception e) {
			log.error("官网登录报错！",e);
			sendMsg("官网登录报错！" ,xfsLoginUrl);
		}
		return result;
	}


	/**
	 * 监控网站  ，通过状态码
	 */
	@Override
	public Result checkUrlByStatus(String url,String title,int expectedStatus,Map<String, String> headerMap) {
		log.info("url="+url+",title="+title);
		Result result = Result.createResult().setSuccess(false);
		try {
			ResponseResult responseResult = HttpUtil.doGet(url, headerMap);
			if (expectedStatus == responseResult.getCode()) {
				log.info(title+"正常！");
				result.setMsg(title+"正常！");
				result.setSuccess(true);
			}else{
				log.info("页面html："+responseResult.getResult());
				log.error(title+"异常！错误码：="+responseResult.getCode());
				result.setMsg(title+"异常！");
				sendMsg(title+"异常！",url);
			}
		} catch (Exception e) {
			log.error("BaseWatching checkUrlByStatus报错！",e);
			result.setMsg(title+"异常！");
			sendMsg(title+"异常！",url);
		}
		return result;
	}
	
	/**
	 * 监控网站  ，通过关键字
	 */
	@Override
	public Result checkUrlByKeyWord(String url,String title,String expectedKeyWord,Map<String, String> headerMap) {
		log.info("url="+url+",title="+title);
		Result result = Result.createResult().setSuccess(false);
		try {
			ResponseResult responseResult = HttpUtil.doGet(url, headerMap);
			if (HTTP_STATUS_OK == responseResult.getCode() && responseResult.getResult().contains(expectedKeyWord)) {
				log.info(title+"正常！");
				result.setMsg(title+"正常！");
				result.setSuccess(true);
			}else{
				log.info("页面html："+responseResult.getResult());
				log.error(title+"异常！错误码：="+responseResult.getCode());
				result.setMsg(title+"异常！");
				sendMsg(title+"异常！",url);
			}
		} catch (Exception e) {
			log.error("BaseWatching checkUrlByKeyWord报错！",e);
			result.setMsg(title+"异常！");
			sendMsg(title+"异常！",url);
		}
		return result;
	}
	
	/**
	 * 失败后 发送消息通知
	 *
	 * @author lifq
	 *
	 * 2017年12月5日 下午2:52:50
	 */
	protected Result sendMsg(String message ,String url){
		log.info("开始发送失败消息，message="+message);
		try {
			String mobiles = Config.getProperty("watching_notify_mobile");
			String emails = Config.getProperty("watching_notify_email");
			log.info("mobiles="+mobiles);
			log.info("emails="+emails);
			if(StringUtils.isNotBlank(mobiles)){
				String[] arr = mobiles.split(",");
				for(String mobile :arr){
					if(StringUtils.isNotBlank(mobile)){
						boolean flag = SmsUtil.sendSms(mobile, message);
						if(flag){
							log.info(mobile+"短信发送成功！");
						}
					}
				}
			}
			if(StringUtils.isNotBlank(emails)){
				String[] arr = emails.split(",");
				if(null!=arr && arr.length>0){
					List<String> emailAddressList = new ArrayList<>();
					for(String emailAddress :arr){
						if(StringUtils.isNotBlank(emailAddress)){
							emailAddressList.add(emailAddress);
						}
					}
					Email email = new Email();
					email.setToAddress(emailAddressList);//收件人
					email.setSubject(message);
					String content = message+"url="+url;
					email.setContent(content);
					MailUtil.sendMail(email);
					log.info(emails+"邮件发送成功！");
				}
			}
		} catch (Exception e) {
			log.error("发送失败消息报错！",e);
		}
		return null;
	}

	public JSONArray getNoLoginUrlArray() {
		return noLoginUrlArray;
	}

	public void setNoLoginUrlArray(JSONArray noLoginUrlArray) {
		this.noLoginUrlArray = noLoginUrlArray;
	}

	public JSONArray getNeedLoginUrlArray() {
		return needLoginUrlArray;
	}

	public void setNeedLoginUrlArray(JSONArray needLoginUrlArray) {
		this.needLoginUrlArray = needLoginUrlArray;
	}
	
	
	
}
