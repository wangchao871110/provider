package com.xfs.bs.dto;

import java.math.BigDecimal;

/**
 * 城市社保规则返回数据类
 * 
 * @author wangxs
 *
 */

import java.util.Date;


/**
 * AppraiseOrder
 * @author:wangxs
 * @date:2016/06/15 15:22:54
 *
 */
public class AppraiseOrder implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer orderId;//
    public Integer getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId=orderId;
    }

    private String cusomerName;
    public String getCusomerName() {
        return this.cusomerName;
    }
    public void setCusomerName(String cusomerName) {
        this.cusomerName=cusomerName;
    }

    private Integer orderStar;//订单星级
    public Integer getOrderStar() {
        return this.orderStar;
    }
    public void setOrderStar(Integer orderStar) {
        this.orderStar=orderStar;
    }

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

    private String appraiseShow;

    public String getAppraiseShow() {
        return appraiseShow;
    }

    public void setAppraiseShow(String appraiseShow) {
        this.appraiseShow = appraiseShow;
    }
}

