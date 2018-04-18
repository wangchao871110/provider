package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.business.model.ApplySyncData;
import com.xfs.common.Result;
import com.xfs.common.http.HttpUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.sps.utils.SysTenantParamUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * taskbot Api
 * @Author: quanjiahua@xinfushe.com
 * @Date: Created in 2017/3/16 10:14
 */
public class TaskBotApi {

    private static Logger log = Logger.getLogger(SysTenantParamUtil.class);
    /**
     * SysTenantParamUtil.getParaValue() 为从缓存中获取租户及参数，若不想从缓存中获取则请用下面方法
     * getParaValueByParamCode()
     */
    private final static String NOTIFY_URL = SysTenantParamUtil.getParaValue("sys.taskbot.corp.notifyUrl","sys");//回调url
    private static String HTTP_URL = SysTenantParamUtil.getParaValue("sys.taskbot.corp.taskbotUrl","sys");//taskbot接口url
    private static String APPID = SysTenantParamUtil.getParaValue("sys.taskbot.corp.appId","sys");//taskbot appid
    private static String APPSECRET = SysTenantParamUtil.getParaValue("sys.taskbot.corp.appSecret","sys");//taskbot appsecret

    private final static String TOKEN_URL = HTTP_URL + "api/token";//获取token
    private final static String ADD_AND_EXECUTE_TASK = HTTP_URL + "api/addTaskAndExecute";//添加并执行任务
    private final static String ADD_TASK = HTTP_URL + "api/addOneTask";//添加任务
    private final static String GET_TASK = HTTP_URL + "api/queryOneTaskState";//查询任务
    private final static String BATCH_EXECUTE_TASK = HTTP_URL + "api/batchExecuteTask";//执行任务
    private final static String EXECUTE_TASK = HTTP_URL + "api/noticeClientDealTask";//执行任务
    private final static String UKEY_LIST = HTTP_URL + "api/queryUkey";//获取ukey列表
    private final static String NOTICE_LOGIN = HTTP_URL + "api/noticeLogin";//通知网申客户端登录
    private final static String QUERY_LOGIN_RANDOM = HTTP_URL + "apiXfs/getServerRandom";//获取登录随机数
    private final static String LOGIN= HTTP_URL + "apiXfs/login";//登录
    public final static String BSTYPE_LIST = HTTP_URL + "api/queryFinishedCityBstype";//支持业务城市
    private final static String SYNDATA = HTTP_URL + "apiXfs/synData";//同步政府网站数据
    private final static String REG_CORP = HTTP_URL + "api/regAppKey";//企业注册接口
    private final static String UKEY_QUERY = HTTP_URL + "api/queryOneUkey";//U盾账号查询
    private final static String UKEY_ADD = HTTP_URL + "api/addOneUkey";//U盾账号添加
    private final static String UKEY_DEL = HTTP_URL + "api/delUkey";//U盾账号删除
    private final static String BATCH_CHECK_SATE = HTTP_URL + "api/batchCheckSate";//查看hr网申客户端是否在线


    private final static String Encoding = "UTF-8"; // 默认编码
    private final static int SocketTimeout = 60 * 1000;
    private final static int ConnectTimeout = 60 * 1000;
    private final static int ConnectionRequestTimeout = 60 * 1000;

    public static void main(String[] args) {
        try {
            System.out.println(batchCheckSate(TaskBotApi.getToken(),"李富强","130627198608266818"));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("TaskBot注册接口异常：" + e.getMessage(), e);
        }

    }

    /**
     * 获取taskbot token
     * @return
     */
    public static String getToken(String appid, String appsecret){

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("appid", appid);
            map.put("appsecret", appsecret);
            String token = HttpUtil.doPost(TOKEN_URL, map);
            JSONObject jb = JSON.parseObject(token);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                token = data.getString("token");
            }
            return token;
        } catch (Exception e) {
            log.error("未获取到Token taskbot API接口：" + e.getMessage() , e);
            return null;
        }

    }
    /**
     * 获取taskbot token
     * @return
     */
    public static String getToken(){

        return getToken(APPID, APPSECRET);
    }

    /**
     * 添加任务
     * @param bstypeId
     * @param json
     * @param ukeyCode
     * @return taskId
     */
    public static String addAndExecuteTask(int bstypeId, String json, String ukeyCode, int userId){

        String token = getToken();
        return addAndExecuteTask(token, bstypeId, json, ukeyCode, userId);

    }

    /**
     * 添加并执行任务
     * @param bstypeId
     * @param json
     * @param ukeyCode
     * @return taskId
     */
    public static String addAndExecuteTask(String token, int bstypeId, String json, String ukeyCode, int userId){
        try {

            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("bstypeId", bstypeId+"");
            map.put("json", json);
            map.put("ukeyCode", ukeyCode);
            map.put("userId", userId+"");
            String taskId = doPostBodyData(ADD_AND_EXECUTE_TASK, JSON.toJSONString(map));

            return taskId;
        } catch (Exception e) {
            log.error("添加并执行任务 taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 添加任务
     * @param bstypeId
     * @param json
     * @param ukeyCode
     * @return taskId
     */
    public static String addTask(String token, int bstypeId, String json, String ukeyCode,int userId){
        try {

            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("bstypeId", bstypeId+"");
            map.put("json", json);
            map.put("ukeyCode", ukeyCode);
            map.put("userId", userId+"");
            String taskId = doPostBodyData(ADD_TASK, JSON.toJSONString(map));

            return taskId;
        } catch (Exception e) {
            log.error("未获取到taskId taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    public static String executeTask(String token){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", StringUtils.isBlank(token) ? getToken() : token);
            String resp = doPostBodyData(EXECUTE_TASK, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("executeTask taskbot API接口：" + e.getMessage() , e);
            return null;
        }

    }

    /**
     *  批量执行任务单
     *  @param   token, tasknos
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-06-14 09:34
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-14 09:34
     *  @updateAuthor  :
     */
    public static String batchExecuteTask(String token,String tasknos,Integer userId){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", StringUtils.isBlank(token) ? getToken() : token);
            map.put("taskids", tasknos);
            map.put("userId", userId+"");
            String resp = doPostBodyData(BATCH_EXECUTE_TASK, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
//                JSONObject data = jb.getJSONObject("data");
//                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("batchExecuteTask taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 查询任务
     * @param taskId
     * @return
     */
    public static String getTask(String taskId){
        return getTask(getToken(), taskId);
    }

    /**
     * 查询任务
     * @param taskId
     * @return
     */
    public static String getTask(String token, String taskId){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("taskId", taskId);
            String resp = doPostBodyData(GET_TASK, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("未获取到taskId taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 支持业务城市接口
     * @return
     */
    public static String getBstypeList(){
        return getBstypeList(getToken());
    }

    /**
     * 支持业务城市接口
     * @return
     */
    public static String getBstypeList(String token){

        log.info("获取支持业务类型城市接口》》》" + HTTP_URL + "  " + APPID + "  " + APPSECRET + "  ");

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            String resp = doPostBodyData(BSTYPE_LIST, JSON.toJSONString(map));

            log.info("获取支持业务类型城市接口resp》》》" + resp);

            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("未获取到支持业务城市 taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 查询单个城市下ukey信息
     * @param type 社保INSURANCE、公积金FUND
     * @param areaId
     * @return
     */
    public static String getUkeyList(String type, Integer areaId){
        return getUkeyList(getToken(), type, areaId);
    }

    /**
     *  @param   appsecret, ukeyCode
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-06-07 10:30
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-07 10:30
     *  @updateAuthor  :
     */
    public static String noticeLogin(String appId,String appsecret,String userId,String ukeyCode,String insuranceFundType){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", getToken(appId,appsecret));
            map.put("ukeyCode", ukeyCode);
            map.put("userId", userId);
            map.put("insuranceFundType", insuranceFundType);
            String resp = doPostBodyData(NOTICE_LOGIN, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            return resp;
        } catch (Exception e) {
            log.error("未获取到通知网申客户端登录 taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 登录前 获取随机数接口
     * @param appId
     * @param appsecret
     * @param areaId
     * @param insuranceFundType
     * @param userId
     * @return
     */
    public static Result getServerRandom(String appId,String appsecret,String areaId,String insuranceFundType,String userId,String accId){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", getToken(appId,appsecret));
            map.put("areaId", areaId);
            map.put("userId", userId);
            map.put("insuranceFundType", insuranceFundType);
            map.put("accId",accId);
            String resp = doPostBodyData(QUERY_LOGIN_RANDOM, JSON.toJSONString(map));
            Result result = JSON.parseObject(resp,Result.class);
            return result;
        } catch (Exception e) {
            log.error("登录前 获取随机数接口 出错：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 登录
     * @param appId
     * @param appsecret
     * @param loginParams
     * @param userId
     * @return
     */
    public static Result login(String appId,String appsecret,String userId,Map<String,String> loginParams){
        try {
            loginParams.put("token", getToken(appId,appsecret));
            loginParams.put("userId", userId);
            String resp = doPostBodyData(LOGIN, JSON.toJSONString(loginParams));
            Result result = JSON.parseObject(resp,Result.class);
            return result;
        } catch (Exception e) {
            log.error("登录接口 出错：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 同步政府网站数据
     * @param appId
     * @param appsecret
     * @param applySyncData
     * @return
     */
    public static Result synData(String appId, String appsecret,String userId, List<ApplySyncData> applySyncData){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", getToken(appId,appsecret));
            map.put("info",applySyncData);
            map.put("userId",userId);
            String resp = doPostBodyData(SYNDATA, JSON.toJSONString(map));
            Result result = JSON.parseObject(resp,Result.class);
            if(result.isSuccess())
                return result;
                //return Integer.parseInt(String.valueOf(result.getData().get("total")));
            else
                log.info("登录前 获取随机数接口 出错：");
        } catch (Exception e) {
            log.error("登录接口 出错：" + e.getMessage() , e);
            return null;
        }
        return null;
    }

    /**
     * 查询单个城市下ukey信息
     * @param token
     * @param type 社保INSURANCE、公积金FUND
     * @param areaId
     * @return
     */
    public static String getUkeyList(String token, String type, Integer areaId){
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", getToken());
            map.put("type", type);
            map.put("areaId", areaId + "");
            String resp = doPostBodyData(UKEY_LIST, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("未获取到UkeyList taskbot API接口：" + e.getMessage() , e);
            return null;
        }
    }

    /**
     * 注册企业
     * @param corpName 企业名称
     * @param notifyUrl 回调url
     * @return 返回appid
     */
    public static String regCorp(String corpName, String notifyUrl) {

        try {
            Map<String, String> map = new HashMap<>();
            map.put("token", getToken());
            map.put("companyName", corpName);
            map.put("notifyUrl", StringUtils.isNotBlank(notifyUrl) ? notifyUrl : NOTIFY_URL);
            String resp = doPostBodyData(REG_CORP, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("注册企业regCorp taskbot API接口：" + e.getMessage() , e);
            return null;
        }

    }

    /**
     * 查询U盾
     * @param regNum
     * @return
     */
    public static String uKeyQuery(String regNum) {
        return uKeyQuery(getToken(), regNum);
    }
    /**
     * 查询U盾
     * @param token
     * @param regNum
     * @return
     */
    public static String uKeyQuery(String token, String regNum) {

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("ukeyCode", regNum);
            String resp = doPostBodyData(UKEY_QUERY, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            if(jb.getBoolean("success")){
                JSONObject data = jb.getJSONObject("data");
                resp = data.toJSONString();
            }
            return resp;
        } catch (Exception e) {
            log.error("未获取到uKeyQuery U盾信息！ taskbot API接口：" + e.getMessage() , e);
            return null;
        }

    }

    /**
     * 默认用户，修改U盾
     * @param areaId
     * @param corpName
     * @param regNum
     * @param regNumPass
     * @param regUsbkeyPass
     * @param type
     * @return
     */
    public static boolean uKeyAdd(int areaId, String corpName, String regNum, String regNumPass, String regUsbkeyPass, String type) {
        return uKeyAdd(getToken(),areaId, corpName, regNum, regNumPass, regUsbkeyPass, type);
    }
    /**
     * 添加U盾
     * @param areaId
     * @param corpName
     * @param regNum
     * @param regNumPass
     * @param regUsbkeyPass
     * @param type
     * @return
     */
    public static boolean uKeyAdd(String token, int areaId, String corpName, String regNum, String regNumPass, String regUsbkeyPass, String type) {

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("companyName", corpName);
            map.put("areaId", areaId+"");
            map.put("ukeyCode", regNum);
            map.put("certificatePassword", regUsbkeyPass);
            map.put("loginPassward", regNumPass);
            map.put("type", type);
            String resp = doPostBodyData(UKEY_ADD, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            return jb.getBoolean("success");
        } catch (Exception e) {
            log.error("添加uKeyAdd U盾信息！ taskbot API接口：" + e.getMessage() , e);
            return false;
        }

    }

    /**
     * 默认用户，删除U盾
     * @param areaId
     * @param regNum
     * @return
     */
    public static boolean uKeyDel(int areaId, String regNum) {
        return uKeyDel(getToken(),areaId, regNum);
    }
    /**
     * 删除U盾
     * @param token
     * @param areaId
     * @param regNum
     * @return
     */
    public static boolean uKeyDel(String token, int areaId, String regNum) {

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("areaId", areaId+"");
            map.put("ukeyCode", regNum);
            String resp = doPostBodyData(UKEY_DEL, JSON.toJSONString(map));
            JSONObject jb = JSON.parseObject(resp);
            return jb.getBoolean("success");
        } catch (Exception e) {
            log.error("删除uKeyDel U盾信息！ taskbot API接口：" + e.getMessage() , e);
            return false;
        }

    }

    /**
     * 6.批量查询人员社保状态
     * @param datalist  人员的json字符串 , 支持多个
     * @return
     */
    public static String batchCheckSate(String datalist){
        return batchCheckSate(getToken(), datalist);
    }

    /**
     * 6.批量查询人员社保状态
     * @param token
     * @param datalist  人员的json字符串 , 支持多个
     * @return
     */
    public static String batchCheckSate(String token, String datalist){

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("datalist", datalist);
            String resp = doPostBodyData(BATCH_CHECK_SATE, JSON.toJSONString(map));

            log.info("6.批量查询人员社保状态，resp：" + resp);
            return resp;
        } catch (Exception e) {
            log.error("6.批量查询人员社保状态：" + e.getMessage() , e);
            return "";
        }

    }

    public static String batchCheckSate(String token, String name, String identityCode){
        String resp = batchCheckSate(token,"[{\"psnname\":\""+name+"\",\"identity_code\":\""+identityCode+"\"}]");
        JSONObject jb = JSON.parseObject(resp);

        if(StringUtils.isNotBlank(resp) && jb.getBoolean("success")){

            JSONObject data = jb.getJSONObject("data");
            JSONArray result = data.getJSONArray("result");
            if(result!=null && result.size()>0){
                return ((JSONObject)result.get(0)).getString("insuranceCode");
            }

        }

        return null;
    }

    /**
     * 构造httpPost请求对象
     * @param url 请求地址
     * @param header 头信息
     * @return HttpPost对象
     */
    private static HttpPost getHttpPost(String url, Map<String, String> header) {

        HttpPost httpPost = new HttpPost(url); // 请求地址
        // 设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SocketTimeout)
                .setConnectTimeout(ConnectTimeout).setConnectionRequestTimeout(ConnectionRequestTimeout).build();
        httpPost.setConfig(requestConfig);
        // 设置请求头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return httpPost;
    }

    private static String getResult(HttpEntity entity, String encoding) {

        StringBuilder sbResult = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), Encoding));
            String line;
            while (null != (line = reader.readLine())) {
                sbResult.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbResult.toString();
    }

    /**
     * post请求 ，请求数据放到body里
     * @author lifq
     * 2017年3月15日  下午3:47:04
     */
    private static String doPostBodyData(String url, String bodyData) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = getHttpPost(url, null); // 请求地址
            httpPost.setEntity(new StringEntity(bodyData,Encoding));
            httpClient = HttpClients.createDefault();
            // 得到返回的response.
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = getResult(entity, Encoding);
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭httpClient
            if (null != httpClient) {
                httpClient.close();
            }
            // 关闭response
            if (null != response) {
                EntityUtils.consume(response.getEntity()); // 会自动释放连接
                response.close();
            }
        }
        log.error("taskbot API接口：" + result);
        return result;
    }



}
