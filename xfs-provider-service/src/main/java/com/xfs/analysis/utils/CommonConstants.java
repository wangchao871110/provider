package com.xfs.analysis.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Title:CommonConstants
 * Description:基础数据类
 * @Create_by:yinsy
 * @Create_date:2016年3月24日
 * @Last_Edit_By:
 * @Edit_Description:
 * @version:hma-framelib 1.0
 */
public class CommonConstants {

	//start 定义资源文件
	
	// COOKIE资源文件
//	private final static Properties COOKIE_PROPS = ConfigUtils.getPropertiesFile("conf/cookie.properties");
	// IMAGEMAGICK资源文件
	private final static Properties IMAGEMAGICK_PROPS = ConfigUtils.getPropertiesFile("conf/image.properties");
	// 异常资源文件
	public final static Properties EXCEPTION_PROPS = ConfigUtils.getPropertiesFile("conf/exception.properties");
	
	//模板资源文件
//	private final static Properties TEMPLATE_PROPS = ConfigUtils.getPropertiesFile("conf/template.properties");

//	//邮件资源文件
//	private final static Properties MAIL_PROPS = ConfigUtils.getPropertiesFile("mail/mail.properties");

	private final static Properties MESSAGE_PROPS = ConfigUtils.getPropertiesFile("conf/messages.properties");
	
	// app版本文件
//	public final static Properties APP_VERSION_PROPS = ConfigUtils.getPropertiesFile("conf/appVersion.properties");
	
	public static String getMessage(String key){
		return MESSAGE_PROPS.getProperty(key);
	}
	
	public final static Properties SYSTEM_PROPS = ConfigUtils.getPropertiesFile("conf/system.properties");
	
	//短信签名
	public static final String SMS_SIGN = SYSTEM_PROPS.getProperty("sms.sign", "【黑马资本】");
	
	//系统环境，生产环境
	public static final String ENVI_PROD = "prod";
	
	//系统环境，测试环境
	public static final String ENVI_TEST = "test";
	
	//系统环境，开发环境
	public static final String ENVI_DEV = "dev";
	
	//系统环境，生产、测试 or 开发，默认生产环境
	public static final String ENVIRONMENT = SYSTEM_PROPS.getProperty("environment", ENVI_PROD);
	
	// 客户头像的路径
	public final static String CUSTOMER_AVATAR_FILE_PATH = "avatar"; 
	
	// 名片的路径
	public final static String BUSICARD_FILE_PATH = "card"; 
	
	//图片根路径
	public final static String IMAGE_BASE_PATH = IMAGEMAGICK_PROPS.getProperty("image.base.path");
	
	/**
	 * 头像 名片后缀类型
	 */
	public static final String IMG_SUFFIX_TYPE = ".jpg";
	
	//图片路径
	public static final String IMAGE_PATH = SYSTEM_PROPS.getProperty("image.path");
	
	
	//头像尺寸
	public final static Map<String,String> IMAGE_CUSTOMER_AVATAR_SIZE_MAP = new HashMap<String, String>();
	
	//名片尺寸
	public final static Map<String,String> IMAGE_BUSICARD_SIZE_MAP = new HashMap<String, String>();
	
	static{
		//key:头像尺寸，value:width*height，width为0则为定高缩放，height为0则定高缩放
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("400400", "400*400");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("200200", "200*200");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("150150", "150*150");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("100100", "100*100");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("8080", "80*80");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("5050", "50*50");
		IMAGE_CUSTOMER_AVATAR_SIZE_MAP.put("3030", "30*30");
		
		//名片尺寸
		IMAGE_BUSICARD_SIZE_MAP.put("400200", "0*200");
	}                                    
	

	//imageMagick 路径
	public static final String IMAGE_MAGICK_PATH = (String) IMAGEMAGICK_PROPS.get("imageMagickPath");
	
//	// 登录时创建cookie的域
//	public static final String LOGIN_COOKIE_DOMAIN = (String) COOKIE_PROPS.get("login.cookie.domain");
//
//	// 登录时创建cookie的名称
//	public static final String LOGIN_COOKIE_NAME = (String) COOKIE_PROPS.get("login.cookie.name");
//
//	// 登录时创建cookie的有效时间（秒）；-1：即不设置有效时间，关闭浏览器后cookie自动失效
//	public static final String LOGIN_COOKIE_MAXAGE = (String) COOKIE_PROPS.get("login.cookie.maxage");
	
//	// 登录时创建cookie的域
//	public static final String MAIL_SERVER = (String) MAIL_PROPS.get("mail.server");
//
//
//	public static final String MAIL_LINK = (String) MAIL_PROPS.get("mail.link");
	
	// 后台用户登录session KEY
	public final static String USER_SESSION = "sessionId";

	// 验证码
	public final static String VALIDATE_CODE_KEY = "_validateCode";

	// 登陆验证码
	public final static String VALIDATE_LOGIN_CODE_KEY = "loginValidateCode";

	// 记住账号的checkbox的name属性
	public final static String CHECKBOX_REMEMBER_USERNAME = "checkbox_phone";

	// 记住密码的checkbox的name属性
	public final static String CHECKBOX_AUTO_LOGIN = "checkbox_autoLogin";

	// 自动登陆在cookie中的标志
	public final static String COOKIE_AUTOLOGIN_MARK = "mark";

	// 自动登陆标志设为String "1"
	public final static String AUTOLOGIN_YES = "1";

	// 不自动登陆标志设为String "0"
	public final static String AUTOLOGIN_NO = "0";

	// 存入cookie中的路径
	public final static String COOKIE_PATH = "/";

	// 存入cookie中的账号名称
	public final static String COOKIE_USERNAME = "username";

	// cookie有效时间
	public final static int COOKIE_MAXAGE = 60 * 60 * 24 * 7;

	// 操作存入Session中的UUID的key
	public static final String SESSION_UPDATE_PASSWORD_KEY = "uuid_key";

	/** 发送邮件内容模版开始
	// 发送邮件内容 demo 模版
	public static final String MAIL_DEMO_TEMPLATE = (String) TEMPLATE_PROPS.get("demo.path");

	// 发送邮件修改密码链接 模版
	public static final String MAIL_UPDATE_PASSWORD_TEMPLATE = (String) TEMPLATE_PROPS.get("updatePassword.path");
	
	//发送邮件修改密码 主题
	public static final String MAIL_UPDATE_PASSWORD_SUBJECT = (String) TEMPLATE_PROPS.get("updatePassword.subject");
	
	//发送验证码的邮件 模版
	public static final String MAIL_VALIDATE_CODE_TEMPLATE = (String) TEMPLATE_PROPS.get("sendValidate.path");
	
	//改善邮件验证码验证身份 主题
	public static final String MAIL_VALIDATE_CODE_SUBJECT = (String) TEMPLATE_PROPS.get("sendValidate.subject");
	
	//修改密保邮箱的邮件 模版
	public static final String MAIL_UPDATE_EMAIL_TEMPLATE = (String) TEMPLATE_PROPS.get("updateEmail.path");
	
	//修改密保邮箱的邮件 主题
	public static final String MAIL_UPDATE_EMAIL_SUBJECT = (String) TEMPLATE_PROPS.get("updateEmail.subject");
	 */
	//客户类型
	public final static String CLIENT_TYPE = "clientType";
		
	//IOS
	public final static String CLIENT_TYPE_IOS = "IOS";
	
	//Android
	public final static String CLIENT_TYPE_ANDROID = "Android";
	
	/** 发送邮件内容模版结束 */

	/** 短信验证码key **/
	public static final String SMS_CODE_KEY = "SMSCodeKey";

	
	// 操作标示 成功
	public final static int RETURN_CODE_SUCCESS = 1;

	// 操作标示 失败
	public final static int RETURN_CODE_FAIL = 0;
	
	
	public final static String CURR_IMG_PATH_FLAG = "<img-path>"; 
}
