package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/22.10:00
 */

/**
 * 登录用户信息
 *
 * @author
 * @create 2017-05-22 10:00
 **/
public class UserLoginInfo {
    private String systag;//	系统标识	必填，String

    private String userid;//	用户id	String,与corporationid必须填写一个
    private String username;//用户名	必填,String
    private String loginTime;//	登录时间	必填，String,格式为yyyyMMddHHmmss，如20170518101010
    private String mobile;//	登录标识	必填，int， 1PC,  2移动,  0未知
    private String corporationid;//	客户id	String,与userid必须填写一个
    private String corpname;//客户名称	String

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    private String  clientid;

    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret;
    }

    private  String clientsecret;

    public String getSystag() {
        return systag;
    }

    public void setSystag(String systag) {
        this.systag = systag;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCorporationid() {
        return corporationid;
    }

    public void setCorporationid(String corporationid) {
        this.corporationid = corporationid;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }
}
