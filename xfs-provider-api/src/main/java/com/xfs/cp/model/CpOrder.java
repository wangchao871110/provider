package com.xfs.cp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * CpOrder
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/11 19:12:26	
 */
public class CpOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	private Integer cpRelationId;//合作关系ID
	public Integer getCpRelationId() {
		return this.cpRelationId;
	}
	public void setCpRelationId(Integer cpRelationId) {
		this.cpRelationId=cpRelationId;
	}
	private Date operationTime;//操作时间
	public Date getOperationTime() {
		return this.operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime=operationTime;
	}
	private Date operationTimeFrom;//操作时间
	public Date getOperationTimeFrom() {
		return this.operationTimeFrom;
	}
	public void setOperationTimeFrom(Date operationTimeFrom) {
		this.operationTimeFrom=operationTimeFrom;
	}
	private Date operationTimeTo;//操作时间
	public Date getOperationTimeTo() {
		return this.operationTimeTo;
	}
	public void setOperationTimeTo(Date operationTimeTo) {
		this.operationTimeTo=operationTimeTo;
	}
	private String orderId;//订单号
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId=orderId;
	}
	private Integer stage;//任务阶段
	public Integer getStage() {
		return this.stage;
	}
	public void setStage(Integer stage) {
		this.stage=stage;
	}
	private Integer aSpId;//派单方服务商ID
	public Integer getASpId() {
		return this.aSpId;
	}
	public void setASpId(Integer aSpId) {
		this.aSpId=aSpId;
	}
	private Integer bSpId;//接单方服务商ID
	public Integer getBSpId() {
		return this.bSpId;
	}
	public void setBSpId(Integer bSpId) {
		this.bSpId=bSpId;
	}
	private Integer personNum;//人数
	public Integer getPersonNum() {
		return this.personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum=personNum;
	}
	private Double price;//价格
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price=price;
	}
	private Integer ispay;//是否付款0:未1:已
	public Integer getIspay() {
		return this.ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay=ispay;
	}
	private Integer createBy;//创建人
	public Integer getCreateBy() {
		return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy=createBy;
	}
	private Date createDt;//创建时间
	public Date getCreateDt() {
		return this.createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt=createDt;
	}
	private Date createDtFrom;//创建时间
	public Date getCreateDtFrom() {
		return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
		this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//创建时间
	public Date getCreateDtTo() {
		return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
		this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//更新人
	public Integer getModifyBy() {
		return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy=modifyBy;
	}
	private Date modifyDt;//更新时间
	public Date getModifyDt() {
		return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//更新时间
	public Date getModifyDtFrom() {
		return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
		this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//更新时间
	public Date getModifyDtTo() {
		return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
		this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//逻辑删除标记位0:未删除1:已删除
	public Integer getDr() {
		return this.dr;
	}
	public void setDr(Integer dr) {
		this.dr=dr;
	}
	public Integer createType;//订单类型1:派单方创建2:接单方创建
	public Integer getCreateType() {
		return createType;
	}
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	private String payType; //付款方式 OFFLINE ALIPAY CHANPAY
	private String payVoucherId; //付款凭证 在线支付存凭证 线下支付存fileId
	private String payInfo; // 在线付款相关信息
	public String getPayType() {return payType;}
	public void setPayType(String payType) {this.payType = payType;}
	public String getPayVoucherId() {return payVoucherId;}
	public void setPayVoucherId(String payVoucherId) {this.payVoucherId = payVoucherId;}
	public String getPayInfo() {return payInfo;}
	public void setPayInfo(String payInfo) {this.payInfo = payInfo;}

	private Integer currentStatus; //当前状态

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}



	public Integer pleaseStatus; //请款状态

	public Integer getPleaseStatus() {
		return pleaseStatus;
	}

	public void setPleaseStatus(Integer pleaseStatus) {
		this.pleaseStatus = pleaseStatus;
	}


	public String pleaseContent; //备注

	public String getPleaseContent() {
		return pleaseContent;
	}

	public void setPleaseContent(String pleaseContent) {
		this.pleaseContent = pleaseContent;
	}

	public void fixup(){
	}

	public Integer pleaseBy; //请款操作人

	public Integer comeinBy; //收款操作人

	public Integer getPleaseBy() {
		return pleaseBy;
	}

	public void setPleaseBy(Integer pleaseBy) {
		this.pleaseBy = pleaseBy;
	}

	public Integer getComeinBy() {
		return comeinBy;
	}

	public void setComeinBy(Integer comeinBy) {
		this.comeinBy = comeinBy;
	}

	public Date comeinDt; //收款日期

	public  Date pleaseDt; //请款日期

	public  Date comeinAuditDt; //收款出纳审核日期

	public Date pleaseAuditDt; //请款出纳审核日期

	public String comeinContent; //收款备注

	public Date getComeinDt() {
		return comeinDt;
	}

	public void setComeinDt(Date comeinDt) {
		this.comeinDt = comeinDt;
	}

	public Date getPleaseDt() {
		return pleaseDt;
	}

	public void setPleaseDt(Date pleaseDt) {
		this.pleaseDt = pleaseDt;
	}

	public Date getComeinAuditDt() {
		return comeinAuditDt;
	}

	public void setComeinAuditDt(Date comeinAuditDt) {
		this.comeinAuditDt = comeinAuditDt;
	}

	public Date getPleaseAuditDt() {
		return pleaseAuditDt;
	}

	public void setPleaseAuditDt(Date pleaseAuditDt) {
		this.pleaseAuditDt = pleaseAuditDt;
	}

	public String getComeinContent() {
		return comeinContent;
	}

	public void setComeinContent(String comeinContent) {
		this.comeinContent = comeinContent;
	}

	public String aSpRead; //派单方服务商是否未关注：0 未关注 1已关注
	public String bSpRead; //接单方服务商是否未关注：0 未关注 1已关注

	public String getASpRead() {
		return aSpRead;
	}

	public void setASpRead(String aSpRead) {
		this.aSpRead = aSpRead;
	}

	public String getBSpRead() {
		return bSpRead;
	}

	public void setBSpRead(String bSpRead) {
		this.bSpRead = bSpRead;
	}

	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.cpRelationId!=null)
			ht.put("cpRelationId",this.cpRelationId);
		if(this.operationTime!=null)
			ht.put("operationTime",this.operationTime);
		if(this.operationTimeFrom!=null)
			ht.put("operationTimeFrom",this.operationTimeFrom);
		if(this.operationTimeTo!=null)
			ht.put("operationTimeTo",this.operationTimeTo);
		if(this.orderId!=null)
			ht.put("orderId",this.orderId);
		if(this.stage!=null)
			ht.put("stage",this.stage);
		if(this.aSpId!=null)
			ht.put("aSpId",this.aSpId);
		if(this.bSpId!=null)
			ht.put("bSpId",this.bSpId);
		if(this.personNum!=null)
			ht.put("personNum",this.personNum);
		if(this.price!=null)
			ht.put("price",this.price);
		if(this.ispay!=null)
			ht.put("ispay",this.ispay);
		if(this.createBy!=null)
			ht.put("createBy",this.createBy);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.createDtFrom!=null)
			ht.put("createDtFrom",this.createDtFrom);
		if(this.createDtTo!=null)
			ht.put("createDtTo",this.createDtTo);
		if(this.modifyBy!=null)
			ht.put("modifyBy",this.modifyBy);
		if(this.modifyDt!=null)
			ht.put("modifyDt",this.modifyDt);
		if(this.modifyDtFrom!=null)
			ht.put("modifyDtFrom",this.modifyDtFrom);
		if(this.modifyDtTo!=null)
			ht.put("modifyDtTo",this.modifyDtTo);
		if(this.dr!=null)
			ht.put("dr",this.dr);
		if(this.createType!=null)
			ht.put("createType",this.createType);
		if(this.currentStatus!=null)
			ht.put("currentStatus",this.currentStatus);
		if(this.pleaseStatus!=null)
			ht.put("pleaseStatus",this.pleaseStatus);
		if(this.pleaseContent!=null)
			ht.put("pleaseContent",this.pleaseContent);
		if(this.pleaseBy!=null)
			ht.put("pleaseBy",this.pleaseBy);
		if(this.comeinBy!=null)
			ht.put("comeinBy",this.comeinBy);
		if(this.comeinDt!=null)
			ht.put("comeinDt",this.comeinDt);
		if(this.pleaseDt!=null)
			ht.put("pleaseDt",this.pleaseDt);
		if(this.comeinAuditDt!=null)
			ht.put("comeinAuditDt",this.comeinAuditDt);
		if(this.pleaseAuditDt!=null)
			ht.put("pleaseAuditDt",this.pleaseAuditDt);
		if(this.comeinContent!=null)
			ht.put("comeinContent",this.comeinContent);
		if(this.csbname!=null)
			ht.put("csbname",this.csbname);
		if(this.cacode!=null)
			ht.put("cacode",this.cacode);
		if(this.cabank!=null)
			ht.put("cabank",this.cabank);
		if(this.csaname!=null)
			ht.put("csaname",this.csaname);
		if(this.caname!=null)
			ht.put("caname",this.caname);
		if(this.cabname!=null)
			ht.put("cabname",this.cabname);
		if(this.cabcode!=null)
			ht.put("cabcode",this.cabcode);
		if(this.cabbank!=null)
			ht.put("cabbank",this.cabbank);
		if(this.amobile!=null)
			ht.put("amobile",this.amobile);
		if(this.bmobile!=null)
			ht.put("bmobile",this.bmobile);
		if(this.isRejected!=null)
			ht.put("isRejected",this.isRejected);
		if(this.isViewRejected!=null)
			ht.put("isViewRejected",this.isViewRejected);
		if(this.billNum!=null)
			ht.put("billNum",this.billNum);
		if(this.servicePrice!=null)
			ht.put("servicePrice",this.servicePrice);
		if(this.publicPrice!=null)
			ht.put("publicPrice",this.publicPrice);
		if(this.voucherStatus!=null)
			ht.put("voucherStatus",this.voucherStatus);
		return ht;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/11 19:12:26

	private Integer tip;// 消息提示

	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}

	public BigDecimal getExpendServiceFee() {
		return BigDecimal.valueOf(price);
	}

	public String csbname;

	public String cacode;

	public String cabank;

	public String csaname;

	public String caname;

	private String accountMobile;

	public String getCabname() {
		return cabname;
	}

	public void setCabname(String cabname) {
		this.cabname = cabname;
	}

	public String getCabcode() {
		return cabcode;
	}

	public void setCabcode(String cabcode) {
		this.cabcode = cabcode;
	}

	public String getCabbank() {
		return cabbank;
	}

	public void setCabbank(String cabbank) {
		this.cabbank = cabbank;
	}

	public String cabname;

	public String cabcode;

	public String cabbank;


	public String getAccountMobile() {
		return accountMobile;
	}

	public void setAccountMobile(String accountMobile) {
		this.accountMobile = accountMobile;
	}

	public String getCaname() {
		return caname;
	}

	public void setCaname(String caname) {
		this.caname = caname;
	}

	public String getCsaname() {
		return csaname;
	}

	public void setCsaname(String csaname) {
		this.csaname = csaname;
	}

	public String getCsbname() {
		return csbname;
	}

	public void setCsbname(String csbname) {
		this.csbname = csbname;
	}

	public String getCacode() {
		return cacode;
	}

	public void setCacode(String cacode) {
		this.cacode = cacode;
	}

	public String getCabank() {
		return cabank;
	}

	public void setCabank(String cabank) {
		this.cabank = cabank;
	}

	public String amobile;

	public String bmobile;
	public String getAmobile() {
		return amobile;
	}
	public void setAmobile(String amobile) {
		this.amobile = amobile;
	}
	public String getBmobile() {
		return bmobile;
	}
	public void setBmobile(String bmobile) {
		this.bmobile = bmobile;
	}

	public String comeinName;//收款操作人
	public String pleaseName;//请款操作人
	public String getComeinName() {
		return comeinName;
	}
	public void setComeinName(String comeinName) {
		this.comeinName = comeinName;
	}
	public String getPleaseName() {
		return pleaseName;
	}
	public void setPleaseName(String pleaseName) {
		this.pleaseName = pleaseName;
	}
	private Integer isRejected;//是否驳回 0：未驳回，1：驳回
	private Integer isViewRejected;//是否查看驳回0：未查看；1：查看
	public Integer getIsRejected() {
		return isRejected;
	}
	public void setIsRejected(Integer isRejected) {
		this.isRejected = isRejected;
	}
	public Integer getIsViewRejected() {
		return isViewRejected;
	}
	public void setIsViewRejected(Integer isViewRejected) {
		this.isViewRejected = isViewRejected;
	}
	public String billNum; //账单来源
	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	private Double servicePrice;//服务费
	private Double publicPrice;//官费
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Double getPublicPrice() {
		return publicPrice;
	}
	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}

	private Double amount;	// 总价
	private Double useBalance;	// 抵消
	private Double alreadyCheck;	// 核销金额
	private Integer voucherStatus; // 支付状态
	private String outerTradeNo;// 订单编号
	private Integer busId;// 业务标识
	private String voucherOrderId;// 支付流水号
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getUseBalance() {
		return useBalance;
	}
	public void setUseBalance(Double useBalance) {
		this.useBalance = useBalance;
	}
	public Double getAlreadyCheck() {
		return alreadyCheck;
	}
	public void setAlreadyCheck(Double alreadyCheck) {
		this.alreadyCheck = alreadyCheck;
	}
	public Integer getVoucherStatus() {
		return voucherStatus;
	}
	public void setVoucherStatus(Integer voucherStatus) {
		this.voucherStatus = voucherStatus;
	}
	public String getOuterTradeNo() {
		return outerTradeNo;
	}
	public void setOuterTradeNo(String outerTradeNo) {
		this.outerTradeNo = outerTradeNo;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public String getVoucherOrderId() {
		return voucherOrderId;
	}
	public void setVoucherOrderId(String voucherOrderId) {
		this.voucherOrderId = voucherOrderId;
	}
	
}

