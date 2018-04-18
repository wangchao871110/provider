package com.xfs.settlement.dto;

/**
 * 审核下线付款操作
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-25 10:53
 */
public class ReqAuditOffLine extends BasePay {

    private String orderId;//

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private Integer status;//0 未支付 1 已支付 2 支付失败 3 支付中
    private Integer user_id;
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status=status;
    }

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
