package com.xfs.finance.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by yuanm on 1/3/2017.
 */
public class CheckCreateVo implements Serializable{
    private static final long serialVersionUID = 1462205956718717962L;
    private String buyerName;
    private String mobile;
    private String checkType;
    private String checkMoney;
    private String checkRemark;
    private String ids;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckMoney() {
        return checkMoney;
    }

    public void setCheckMoney(String checkMoney) {
        this.checkMoney = checkMoney;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
