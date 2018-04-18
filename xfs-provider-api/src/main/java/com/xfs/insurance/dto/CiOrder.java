package com.xfs.insurance.dto;

import com.xfs.insurance.model.SpsCiOrder;

/**
 * 商保订单
 *
 * @author lihon
 * @create 2016/9/11 17:17
 */
public class CiOrder extends SpsCiOrder {

    /**
     * 商保类型
     */
    private Integer categoryId;

    /**
     * 商保类型名称
     */
    private String categoryName;

    /**
     * 代理服务机构
     */
    private String spName;

    /**
     * 订单生成时间
     */
    private String createTime;

    /**
     * 商保的保障时间
     */
    private String insTime;

    /**
     * 收费方式
     */
    private String chargeWay;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInsTime() {
        return insTime;
    }

    public void setInsTime(String insTime) {
        this.insTime = insTime;
    }

    public String getChargeWay() {
        if ("1".equals(chargeWay)) {
            return "月";
        } else if ("2".equals(chargeWay)) {
            return "年";
        }
        return chargeWay;
    }

    public void setChargeWay(String chargeWay) {
        this.chargeWay = chargeWay;
    }
}
