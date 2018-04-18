package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.15:32
 */

/**
 * 客户行业信息
 *
 * @author
 * @create 2017-05-17 15:32
 **/
public class CusIndustry {

    public String getIndustryid() {
        return industryid;
    }

    public void setIndustryid(String industryid) {
        this.industryid = industryid;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    private String industryid;//	行业信息id	Not null,String(<128)业务 系统提供行业信息id
    private String industryname;//行业名称	String
    private String detail;//	描述	String（<256）行业简介
    private String attribute1;//	补充属性1	String（<128）
    private String attribute2;//	;//补充属性2	String（<128）
    private String attribute3;//	补充属性3	String（<128）
    private String attribute4;//补充属性4	String（<128）
    private String attribute5;//补充属性5	String（<128）
    private String createdate;//创建时间	String,格式” yyyyMMddHHmmss”

}
