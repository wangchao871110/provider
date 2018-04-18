package com.xfs.insurance.dto;

/**
 * @author lihon
 * @create 2016/9/1 11:25
 */
public class CiEnsureInfo {

    /**
     * 保障项目
     */
    private String ensureItem;

    /**
     * 保障金额
     */
    private String ensureAmount;

    /**
     * 保障范围
     */
    private String insuredScope;

    public String getEnsureItem() {

        return ensureItem;
    }

    public void setEnsureItem(String ensureItem) {

        this.ensureItem = ensureItem;
    }

    public String getEnsureAmount() {

        return ensureAmount;
    }

    public void setEnsureAmount(String ensureAmount) {

        this.ensureAmount = ensureAmount;
    }

    public String getInsuredScope() {

        return insuredScope;
    }

    public void setInsuredScope(String insuredScope) {

        this.insuredScope = insuredScope;
    }
}
