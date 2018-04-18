package com.xfs.settlement.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-19 15:48
 */
public class PayVoucherInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;//支付流水号
    public String getOrderId() {
        return this.orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId=orderId;
    }
    private String outerTradeNo;//
    public String getOuterTradeNo() {
        return this.outerTradeNo;
    }
    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo=outerTradeNo;
    }
    private Integer busId;//
    public Integer getBusId() {
        return this.busId;
    }
    public void setBusId(Integer busId) {
        this.busId=busId;
    }
    private String tradeType;//PAY_FEE:支付 RECHARGE_FEE:充值
    public String getTradeType() {
        return this.tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType=tradeType;
    }
    private String buyerName;//
    public String getBuyerName() {
        return this.buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName=buyerName;
    }
    private String payeeName;//
    public String getPayeeName() {
        return this.payeeName;
    }
    public void setPayeeName(String payeeName) {
        this.payeeName=payeeName;
    }
    private BigDecimal price;//
    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(BigDecimal price) {
        this.price=price;
    }
    private BigDecimal discounted;//
    public BigDecimal getDiscounted() {
        return this.discounted;
    }
    public void setDiscounted(BigDecimal discounted) {
        this.discounted=discounted;
    }
    private BigDecimal amount;//
    public BigDecimal getAmount() {
        return this.amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount=amount;
    }
    private BigDecimal channelFee;//
    public BigDecimal getChannelFee() {
        return this.channelFee;
    }
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee=channelFee;
    }
    private BigDecimal useBalance;

    public BigDecimal getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(BigDecimal useBalance) {
        this.useBalance = useBalance;
    }

    private String channelFeeType;//通道费类型  PLATFORM:平台 THIRDPARTY:第三方
    public String getChannelFeeType() {
        return this.channelFeeType;
    }
    public void setChannelFeeType(String channelFeeType) {
        this.channelFeeType=channelFeeType;
    }
    private String payType;//ALIPAY:支付宝  CHANPAY:畅捷通  OFFLINE:下线支付 OFFSET余额划转
    public String getPayType() {
        return this.payType;
    }
    public void setPayType(String payType) {
        this.payType=payType;
    }
    private String accountNo;//银行账号
    public String getAccountNo() {
        return this.accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo=accountNo;
    }
    private Date payTime;//
    public Date getPayTime() {
        return this.payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime=payTime;
    }
    private String buyerId;//
    public String getBuyerId() {
        return this.buyerId;
    }
    public void setBuyerId(String buyerId) {
        this.buyerId=buyerId;
    }
    private String buyerType;//PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    public String getBuyerType() {
        return this.buyerType;
    }
    public void setBuyerType(String buyerType) {
        this.buyerType=buyerType;
    }
    private String payeeId;//
    public String getPayeeId() {
        return this.payeeId;
    }
    public void setPayeeId(String payeeId) {
        this.payeeId=payeeId;
    }
    private String payeeType;//PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    public String getPayeeType() {
        return this.payeeType;
    }
    public void setPayeeType(String payeeType) {
        this.payeeType=payeeType;
    }
    private Integer payId;//
    public Integer getPayId() {
        return this.payId;
    }
    public void setPayId(Integer payId) {
        this.payId=payId;
    }
    private Integer status;//0 未支付 1 已支付 2 支付失败 3 支付中
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status=status;
    }
    private String productIntro;//
    public String getProductIntro() {
        return this.productIntro;
    }
    public void setProductIntro(String productIntro) {
        this.productIntro=productIntro;
    }
    private String productInfo;//
    public String getProductInfo() {
        return this.productInfo;
    }
    public void setProductInfo(String productInfo) {
        this.productInfo=productInfo;
    }
    private String remark;//
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
    private String remarkEq;//
    public String getRemarkEq() {
        return this.remarkEq;
    }
    public void setRemarkEq(String remarkEq) {
        this.remarkEq=remarkEq;
    }
    private String notifyInfo;//
    public String getNotifyInfo() {
        return this.notifyInfo;
    }
    public void setNotifyInfo(String notifyInfo) {
        this.notifyInfo=notifyInfo;
    }
    private String payReqInfo;//
    public String getPayReqInfo() {
        return this.payReqInfo;
    }
    public void setPayReqInfo(String payReqInfo) {
        this.payReqInfo=payReqInfo;
    }
    private Integer voucherFile;//
    public Integer getVoucherFile() {
        return this.voucherFile;
    }
    public void setVoucherFile(Integer voucherFile) {
        this.voucherFile=voucherFile;
    }
    private String notifyUrl;//
    public String getNotifyUrl() {
        return this.notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl=notifyUrl;
    }
    private String returnUrl;//
    public String getReturnUrl() {
        return this.returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl=returnUrl;
    }
    private String isNotify;//Y: 是 N:否
    public String getIsNotify() {
        return this.isNotify;
    }
    public void setIsNotify(String isNotify) {
        this.isNotify=isNotify;
    }

}
