package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.15:30
 */

/**
 * 客户关联联系人
 *
 * @author
 * @create 2017-05-17 15:30
 **/
public class CusContact {

    private String contanctid;//	联系人id	Not null，String（<128）业务系统提供联系人唯一标识
    private String name;//	联系人姓名	String(<128)
    private String email;//	邮箱	String
    private String mobilephone;//	手机	String
    private String addr;//	通信地址	String(<128)详细地址
    private String createdate;//	创建时间	String,格式” yyyyMMddHHmmss”

    public String getContanctid() {
        return contanctid;
    }

    public void setContanctid(String contanctid) {
        this.contanctid = contanctid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
