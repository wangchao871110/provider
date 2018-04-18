package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.15:28
 */

/**
 * 客户关联用户信息
 *
 * @author
 * @create 2017-05-17 15:28
 **/
public class CusUserRelation {

    private String relationid;//	关联关系id	Not null，String(<128)业务系统提供关联关系的主键，如果没有使用userid
    private String openid;//	用户唯一标识符	Not null ,String(<40)友户通提供唯一标识符
    private String userid;//用户userid	Not null ,String(<128)业务系统提供用户信息主键
    private String sysmark;//	账户来源	友户通提供业务系统标识
    private String type;//	用户类型	Admin：管理员，user：普通用户，vuser：vip用户
    private String corporationid;//客户id	业务系统提供客户标识符
    private String createdate;//	创建时间	String,格式” yyyyMMddHHmmss”

    public String getRelationid() {
        return relationid;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSysmark() {
        return sysmark;
    }

    public void setSysmark(String sysmark) {
        this.sysmark = sysmark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorporationid() {
        return corporationid;
    }

    public void setCorporationid(String corporationid) {
        this.corporationid = corporationid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
