package com.xfs.insurance.dto;

import java.util.ArrayList;
import java.util.List;

import com.xfs.insurance.model.BsCiProduct;

/**
 * 商保产品扩展类
 *
 * @author lihon
 * @create 2016/10/26 18:09
 */
public class CiProduct extends BsCiProduct {


    /**
     * 是否在协作平台展示
     */
    private Integer isSale;

    /**
     * 保障方案(json字符串)
     */
    private String schemes;

    /**
     * 商品编码
     */
    private Integer productId;

    private Integer salePrice;

    private Integer spId;

    /**
     * 承保方案列表
     */
    private List<CiScheme> schemeList;

    /**
     * 分类的类型
     */
    private String categoryType;

    /**
     * 保险公司名称
     */
    private String insurerName;

    /**
     * 所属组合编码
     */
    private Integer groupId;

    /**
     * 价格单位
     */
    private String priceUnit;

    private Integer factors;

    public Integer getProductId() {

        return productId;
    }

    public void setProductId(Integer productId) {

        this.productId = productId;
    }

    public String getInsurerName() {

        return insurerName;
    }

    public void setInsurerName(String insurerName) {

        this.insurerName = insurerName;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }


    /**
     * 新增承保方案
     *
     * @param ciScheme 承保方案
     */
    public void addCiScheme(CiScheme ciScheme) {

        if (null == schemeList) {
            schemeList = new ArrayList<>();
        }
        schemeList.add(ciScheme);

    }

    public List<CiScheme> getSchemeList() {

        return schemeList;
    }

    public void setSchemeList(List<CiScheme> schemeList) {

        this.schemeList = schemeList;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPriceUnit() {
        if ("1".equals(super.getChargeWay())) {
            return "人/月";
        } else if ("2".equals(super.getChargeWay())) {
            return "人/年";
        }
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getFactors() {
        if ("1".equals(super.getChargeWay())) {
            return 12;
        } else if ("2".equals(super.getChargeWay())) {
            return 1;
        }
        return factors;
    }

    public void setFactors(Integer factors) {
        this.factors = factors;
    }


    public String getSchemes() {

        return schemes;
    }

    public void setSchemes(String schemes) {

        this.schemes = schemes;
    }

    public String getCategoryType() {

        return categoryType;
    }

    public void setCategoryType(String categoryType) {

        this.categoryType = categoryType;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

}
