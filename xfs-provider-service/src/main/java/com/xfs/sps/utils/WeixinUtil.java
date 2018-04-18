package com.xfs.sps.utils;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xfs.base.model.SecWx;
import com.xfs.base.service.SecWxService;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.SpringContextUtil;
import com.xfs.common.util.StringUtils;
//import com.xfs.sps.service.SecWxService;
import com.xfs.sps.util.vo.AccessToken;
import com.xfs.sps.util.vo.MyX509TrustManager;
import com.xfs.sps.util.vo.OAuthInfo;
import com.xfs.sps.util.vo.WeixinException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	private static String ACCESS_TOKEN_STR = "ACCESS_TOKEN";

	private static SecWxService service = null;

	private synchronized static void init() {
		service = SpringContextUtil.getBean(SecWxService.class);
	}

	static {
		init();
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		HttpsURLConnection httpUrlConn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.", ce);
		} catch (Exception e) {
			log.error("https request error:{}", e);
		} finally {
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @param access_token_url
	 *            获取access_token的接口地址（GET） 限200（次/天）
	 * @return
	 * @throws WeixinException
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) throws WeixinException {
		
		
		AccessToken accessToken = null;
		String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		System.out.println();
		System.out.println();
		System.out.println("*************************************appid："+appid);
		System.out.println("*************************************appsecret："+appsecret);
		System.out.println();
		System.out.println();
		System.out.println();
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				throw new WeixinException(jsonObject.getInt("errcode"));
			}
		}
		return accessToken;
	}

	public static String getToken(String wxId) throws WeixinException {
		// 首先从wec_wx表中获取对应的token
		// expiretime，若剩余时间小于3min，则放弃重新获取，然后返回token。若大于3min，则直接返回token。
		SecWx wx = new SecWx();
		wx.setWxId(wxId);
		wx = service.findByPK(wx);
		Date oldDate = wx.getExpiretime();
		if (wx != null && oldDate != null && oldDate.getTime() > new Date().getTime() + 3 * 60 * 1000) { // 三分钟
			return wx.getToken();
		} else {
			AccessToken at = getAccessToken(wx.getAppid(), wx.getAppsecret());
			if (null != at && !StringUtils.isBlank(at.getToken())) {
				Date date = new Date(new Date().getTime() + 2 * 60 * 60 * 1000);// 得到两个小时以后的date
				wx.setToken(at.getToken());
				wx.setExpiretime(date);

				service.update(null, wx);
				return at.getToken();
			}
			return "";
		}
	}

	public static OAuthInfo getOAuthOpenId(String appid, String secret, String code) {
		OAuthInfo oAuthInfo = null;
	    String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		// oAuthInfo是作者自己把那几个属性参数写在一个类里面了。
		// 如果请求成功
		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				oAuthInfo.setAccessToken(jsonObject.getString("access_token"));
				oAuthInfo.setExpiresIn(jsonObject.getInt("expires_in"));
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				oAuthInfo = null;
				// 获取token失败
				log.error("网页授权获取openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return oAuthInfo;
	}
	
	
	
public static String getApiTicket(String appid,String appsecret) throws WeixinException{
		
		AccessToken at = (AccessToken) RedisUtil.get("fastListAccessToken");
		try {
			if(at ==null){
				at = WeixinUtil.getAccessToken(appid, appsecret);
				if(at!=null){
					RedisUtil.set("fastListAccessToken", at, 60*28);
				}else{
					RedisUtil.delete("fastListAccessToken");
				}
			}
		} catch (WeixinException e) {
			log.error("获取token失败");
		}
		System.out.println();
		System.out.println();
		System.out.println("*************************************令牌是什么："+at.getToken());
		System.out.println("*************************************令牌的过期时间是："+at.getExpiresIn());
		System.out.println();
		System.out.println();
    	if (null != at && !StringUtils.isBlank(at.getToken())) {
    		String token = at.getToken();
    		return getApiTicket(token);
		}
		
		return null;
	}
public static String getApiTicket(String accessToken) throws WeixinException{
	String getticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	String requestUrl = getticket.replace("ACCESS_TOKEN", accessToken);
	JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
	if (null != jsonObject) {
		try {
			return jsonObject.getString("ticket");
		}catch (JSONException e) {
			accessToken = null;
			// 获取token失败
			log.error("获取ApiTicket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			throw new WeixinException(jsonObject.getInt("errcode"));
		}
	}
	return null;
}
	

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 * @throws WeixinException
	 */
	public static void createMenu(Menu menu, String wxId) throws WeixinException {
		// 菜单创建（POST） 限100（次/天）
		String menu_create_url = null;//SysTenantParamUtil.getParaValue(Enums.WEIXIN.URL_MENU_CREATE, Enums.SYS);
		// 获取access_token
		String accessToken = getToken(wxId);
		// 拼装创建菜单的url
		String url = menu_create_url.replace(ACCESS_TOKEN_STR, accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				throw new WeixinException(jsonObject.getInt("errcode"));
			}
		}
	}

}
