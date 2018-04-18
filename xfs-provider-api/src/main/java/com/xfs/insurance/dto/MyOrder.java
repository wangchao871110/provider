package com.xfs.insurance.dto;

import java.math.BigDecimal;

/**
 * 城市社保规则返回数据类
 * 
 * @author wangxs
 *
 */

import java.util.Date;


/**
 * MyOrder
 * @author:wangxs
 * @date:2016/06/7 15:22:54
 */
public class MyOrder {

    private Integer orderId;//
    public Integer getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId=orderId;
    }

   /* private String cusomerName;//
    public String getCusomerName() {
        return this.cusomerName;
    }
    public void setCusomerName(String cusomerName) {
        this.cusomerName=cusomerName;
    }*/

    private String spName;

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String productName;

    private String photo; // 产品图片

    private String productDes; // 产品描述

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    private String orderNo;//
    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo=orderNo;
    }



    private String orderType;//个人：C   企业：B
    public String getOrderType() {
        return this.orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType=orderType;
    }

    private String orderState;//
    public String getOrderState() {
        return this.orderState;
    }
    public void setOrderState(String orderState) {
        this.orderState=orderState;
    }



    private BigDecimal perMonthPrice;//
    public BigDecimal getPerMonthPrice() {
        return this.perMonthPrice;
    }
    public void setPerMonthPrice(BigDecimal perMonthPrice) {
        this.perMonthPrice=perMonthPrice;
    }




    private BigDecimal oncePrice;//
    public BigDecimal getOncePrice() {
        return this.oncePrice;
    }
    public void setOncePrice(BigDecimal oncePrice) {
        this.oncePrice=oncePrice;
    }


    private Date createDt;//
    public Date getCreateDt() {
        return this.createDt;
    }
    public void setCreateDt(Date createDt) {
        this.createDt=createDt;
    }

    private String appraise;//
    public String getAppraise() {
        return this.appraise;
    }
    public void setAppraise(String appraise) {
        this.appraise=appraise;
    }

    public Date getAppraiseDt() {
        return appraiseDt;
    }

    public void setAppraiseDt(Date appraiseDt) {
        this.appraiseDt = appraiseDt;
    }

    private  Date appraiseDt;

    private String perMonth;

    public String getPerMonth() {
        return perMonth;
    }

    public void setPerMonth(String perMonth) {
        this.perMonth = perMonth;
    }

    private String once;

    public String getOnce() {
        return once;
    }

    public void setOnce(String once) {
        this.once = once;
    }
    //商保订单id
    private Integer ciId;

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String orderOnlineType;

    public Integer fileId;

    public String getOrderOnlineType() {
        return orderOnlineType;
    }

    public void setOrderOnlineType(String orderOnlineType) {
        this.orderOnlineType = orderOnlineType;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}

