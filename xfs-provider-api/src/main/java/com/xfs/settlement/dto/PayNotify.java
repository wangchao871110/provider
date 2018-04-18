package com.xfs.settlement.dto;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-26 17:27
 */
public class PayNotify extends BasePay {

    private String outerTradeNo;//外部订单号
    private String tradeType;//交易类型 PAY_FEE:支付 RECHARGE_FEE:充值
    private String buyerName;//付款方名称
    private String buyerId;//付款方唯一标识
    private String buyerType;//付款方类型 PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    private String buyerOpenId;//付款方微信支付ID
    private String payeeName;//收款方名称
    private String payeeId;//收款方唯一标识;
    private String payeeType;//收款方类型 PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    private String createBy;//创建人ID
    private String payeeOpenId;//收款方微信支付ID
    private String payType;//支付方式 ALIPAY:支付宝  CHANPAY:畅捷通  WXPAY:微信 OFFLINE:下线支付 OFFSET余额划转;
    private String payId;//支付ID
    private String status;//支付状态 PAYING 支付中 SUCCESS 支付成功  FAIL 支付失败
    private String voucherFile;//线下支付凭证
    private String remark;//备注说明
    private String attach;//扩展字段

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }
    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getVoucherFile() {
        return voucherFile;
    }

    public void setVoucherFile(String voucherFile) {
        this.voucherFile = voucherFile;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(String buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
    }

    public String getPayeeOpenId() {
        return payeeOpenId;
    }

    public void setPayeeOpenId(String payeeOpenId) {
        this.payeeOpenId = payeeOpenId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}
