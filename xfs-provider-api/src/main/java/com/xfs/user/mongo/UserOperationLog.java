package com.xfs.user.mongo;

import com.xfs.user.model.SysUser;

import java.io.Serializable;
import java.util.Map;

public class UserOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String session;
    private  String _id;

    // cookie
    private Map<String, String> cookie;//cookie
    //异常信息
    private String exceptionMessage;
    // 用户信息
    private SysUser user;
    // log 的路径
    private String path;
    //主机 名
    private String host;
    // 项目 名称
    private String projectName;
    //请求的ip 地址
    private String ip;

    private Integer responseStatus;//返回状态

    //请求 时间
    private String reqTime;
    // user agent
    private String ua;

    private String action ;
    //统一资源定位器
    private String url;

    private long controllerExecTime;   //controller 的执行时间


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getControllerExecTime() {
        return controllerExecTime;
    }

    public void setControllerExecTime(long controllerExecTime) {
        this.controllerExecTime = controllerExecTime;
    }
}
