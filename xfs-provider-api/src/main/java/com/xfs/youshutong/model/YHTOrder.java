package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.11:02
 */

/**
 * 友互通 订单
 *
 * @author
 * @create 2017-05-17 11:02
 **/
public class YHTOrder {
    private String cusCode;//　	是	String	客户编码/用户编码	2C业务为用户编码
    private String cusName;//	　	是	String	客户名称/用户名称	3C业务为用户名称
    private String orderLineID;//	是	String	订单行ID	唯一标识，畅捷通使用订单行号
    private String orderCode;//　	是	String	订单编码	优普填客户服务识别码，唯一标识
    private String orderTime;//	是	Date	订单时间	合法性校验（yyyyMMddHHmmss）
    private String payTime;//	是	Date	支付时间	合法性校验（yyyyMMddHHmmss）
    private String orderStartTime;//	　	　	Date	授权开始时间	　合法性校验（yyyyMMddHHmmss）
    private String orderEndTime;//　	　	Date	授权结束时间	　合法性校验（yyyyMMddHHmmss）
    private int ServerDead;//	Int	服务期限	单位天 数值
    private String orderOrg;//	　	String	订单所属机构	　
    private String orderRegion;//　	　	String	订单所属区域	　
    private String orderIndustry;//　	　	String	订单所属行业	　
    private String productCode;//	是	String	产品编码	　所属机构编码
    private String orderAmount;//	　	是	Double	订单金额	　
    private String orderPaidAmount;//	　	　	Double	付款金额	　
    private String orderRebate;//	String	订单折扣	折扣率
    private String favorableAmount;//		Double	优惠金额	计算的
    private String impowerusercount;//		是	Int	授权用户数
    private String serverCode;//		String	服务商编码	是否有编码
    private String serverDefinition;//		String	服务商名称
    private String customId;//	是	String	客户ID	统一标识友互通的客户信息，与客户信息注册表关联
    private String sysmark;//	是	String	所属系统
    private int orderClass;//		Int	订单状态	0-试用，1-作废
    private String reserveAttr2;//			String	版本
    private String orderType;//	　	是	String	业务类型	两种业务： ;//  1、一般互联网产品/服务订单  2、2B/2C电子商务交易流水
    private String orderDept;//		String	订单所属部门
    private String Saleorg;//		String 	销售机构	标识销售产品的机构
    private String saleDept;//		String	销售部门
    private String signTime;//	是	Date	签约时间	合法性校验（yyyyMMddHHmmss）
    private String payerSqua;//	是	String	付款方	付款的客户ID（易代账：代账公司为付款方）付费活跃客户数
    private String usingSqua;//	是	String	使用方	使用此产品的客户ID（易代账：被代账的企业、账套）
    private String drawerSqua;//	是	String	开票方	开具发票抬头的客户ID
    private String commonStatus;//		String	状态	1-试用、2-正常、3-退款

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(String orderLineID) {
        this.orderLineID = orderLineID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public int getServerDead() {
        return ServerDead;
    }

    public void setServerDead(int serverDead) {
        ServerDead = serverDead;
    }

    public String getOrderOrg() {
        return orderOrg;
    }

    public void setOrderOrg(String orderOrg) {
        this.orderOrg = orderOrg;
    }

    public String getOrderRegion() {
        return orderRegion;
    }

    public void setOrderRegion(String orderRegion) {
        this.orderRegion = orderRegion;
    }

    public String getOrderIndustry() {
        return orderIndustry;
    }

    public void setOrderIndustry(String orderIndustry) {
        this.orderIndustry = orderIndustry;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPaidAmount() {
        return orderPaidAmount;
    }

    public void setOrderPaidAmount(String orderPaidAmount) {
        this.orderPaidAmount = orderPaidAmount;
    }

    public String getOrderRebate() {
        return orderRebate;
    }

    public void setOrderRebate(String orderRebate) {
        this.orderRebate = orderRebate;
    }

    public String getFavorableAmount() {
        return favorableAmount;
    }

    public void setFavorableAmount(String favorableAmount) {
        this.favorableAmount = favorableAmount;
    }

    public String getImpowerusercount() {
        return impowerusercount;
    }

    public void setImpowerusercount(String impowerusercount) {
        this.impowerusercount = impowerusercount;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getServerDefinition() {
        return serverDefinition;
    }

    public void setServerDefinition(String serverDefinition) {
        this.serverDefinition = serverDefinition;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getSysmark() {
        return sysmark;
    }

    public void setSysmark(String sysmark) {
        this.sysmark = sysmark;
    }

    public int getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(int orderClass) {
        this.orderClass = orderClass;
    }

    public String getReserveAttr2() {
        return reserveAttr2;
    }

    public void setReserveAttr2(String reserveAttr2) {
        this.reserveAttr2 = reserveAttr2;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDept() {
        return orderDept;
    }

    public void setOrderDept(String orderDept) {
        this.orderDept = orderDept;
    }

    public String getSaleorg() {
        return Saleorg;
    }

    public void setSaleorg(String saleorg) {
        Saleorg = saleorg;
    }

    public String getSaleDept() {
        return saleDept;
    }

    public void setSaleDept(String saleDept) {
        this.saleDept = saleDept;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getPayerSqua() {
        return payerSqua;
    }

    public void setPayerSqua(String payerSqua) {
        this.payerSqua = payerSqua;
    }

    public String getUsingSqua() {
        return usingSqua;
    }

    public void setUsingSqua(String usingSqua) {
        this.usingSqua = usingSqua;
    }

    public String getDrawerSqua() {
        return drawerSqua;
    }

    public void setDrawerSqua(String drawerSqua) {
        this.drawerSqua = drawerSqua;
    }

    public String getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(String commonStatus) {
        this.commonStatus = commonStatus;
    }
}
