package com.xfs.finance.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by yuanm on 1/3/2017.
 */
public class CheckChargeVo implements Serializable{
    private static final long serialVersionUID = -4383341664283447568L;
    private String chargeName;
    private String chargeMoblie;
    private String chargeMoney;
    private String chargeRemark;
    private String ids;
    private String target;

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getChargeMoblie() {
        return chargeMoblie;
    }

    public void setChargeMoblie(String chargeMoblie) {
        this.chargeMoblie = chargeMoblie;
    }

    public String getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getChargeRemark() {
        return chargeRemark;
    }

    public void setChargeRemark(String chargeRemark) {
        this.chargeRemark = chargeRemark;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
