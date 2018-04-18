package com.xfs.insurance.dto;

import com.xfs.insurance.model.CpCiOrder;

/**
 * 协作平台订单详细信息类
 *
 * @author lihon
 * @create 2016/10/27 8:35
 */
public class CpCiOrderDetail extends CpCiOrder {

    /**
     * 下单人名称
     */
    private String cpName;

    /**
     * 手机号码
     */
    private String cpMobile;

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpMobile() {
        return cpMobile;
    }

    public void setCpMobile(String cpMobile) {
        this.cpMobile = cpMobile;
    }
}
