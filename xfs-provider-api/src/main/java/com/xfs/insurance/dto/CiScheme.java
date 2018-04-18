package com.xfs.insurance.dto;

import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xfs.insurance.model.BsCiScheme;

/**
 * @author lihon
 * @create 2016/8/31 21:58
 */
public class CiScheme extends BsCiScheme {
    /**
     * 保障信息
     */
    private List<CiEnsureInfo> insuredInfo;

    /**
     * 保障信息列表
     */
    private List<CiEnsureInfo> ensureInfoList;


    /**
     * 零售价格
     */
    private BigDecimal schemePrice;

    /**
     * 商品编码
     */
    private Integer productId;


    public List<CiEnsureInfo> getInsuredInfo() {

        return insuredInfo;
    }

    public void setInsuredInfo(List<CiEnsureInfo> insuredInfo) {

        this.insuredInfo = insuredInfo;
    }

    public BigDecimal getSchemePrice() {
        return schemePrice;
    }

    public void setSchemePrice(BigDecimal schemePrice) {
        this.schemePrice = schemePrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<CiEnsureInfo> getEnsureInfoList() {
        return ensureInfoList;
    }

    public void setEnsureInfoList(List<CiEnsureInfo> ensureInfoList) {
        this.ensureInfoList = ensureInfoList;
    }

    public List<CiEnsureInfo> getInsureList() {
        return JSON.parseArray(super.getSchemeInsure(), CiEnsureInfo.class);
    }


}
