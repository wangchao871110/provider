package com.xfs.sp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CmContract
 * @author:wuzhe
 * @date:2016/08/10 10:25:42	
 */
public class CmContract implements java.io.Serializable {
	
	private Integer contractId;//
	public Integer getContractId() {
	    return this.contractId;
	}
	public void setContractId(Integer contractId) {
	    this.contractId=contractId;
	}
	private Integer cpId;//
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	private Integer spId;//
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private Integer fileId;//
	public Integer getFileId() {
	    return this.fileId;
	}
	public void setFileId(Integer fileId) {
	    this.fileId=fileId;
	}
	private String contractName;//合同名称
	public String getContractName() {
	    return this.contractName;
	}
	public void setContractName(String contractName) {
	    this.contractName=contractName;
	}
	private String contractNameEq;//合同名称
	public String getContractNameEq() {
	    return this.contractNameEq;
	}
	public void setContractNameEq(String contractNameEq) {
	    this.contractNameEq=contractNameEq;
	}
	private Integer orderId;//
	public Integer getOrderId() {
	    return this.orderId;
	}
	public void setOrderId(Integer orderId) {
	    this.orderId=orderId;
	}
	public String onlineType; // 线上  线下
	public static final String onlineType_ONLINE = "ONLINE";
	public static final String onlineType_OFFLINE = "OFFLINE";
	public String getOnlineType() {return this.onlineType; }
	public void setOnlineType(String onlineType) {this.onlineType=onlineType; }
	public Integer collaborator; // 客户经理
	public Integer getCollaborator() {return this.collaborator; };
	public void setCollaborator(Integer collaborator) {this.collaborator = collaborator; }
	private String content;//
	public String getContent() {
	    return this.content;
	}
	public void setContent(String content) {
	    this.content=content;
	}
	private String contentEq;//
	public String getContentEq() {
	    return this.contentEq;
	}
	public void setContentEq(String contentEq) {
	    this.contentEq=contentEq;
	}
	private String state;//USE: 有效            STOP:无效
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	private String stateEq;//USE: 有效            STOP:无效
	public String getStateEq() {
	    return this.stateEq;
	}
	public void setStateEq(String stateEq) {
	    this.stateEq=stateEq;
	}
	private String schemeId;//方案id，多个时已英文逗号分隔
	public String getSchemeId() {
	    return this.schemeId;
	}
	public void setSchemeId(String schemeId) {
	    this.schemeId=schemeId;
	}
	private String schemeIdEq;//方案id，多个时已英文逗号分隔
	public String getSchemeIdEq() {
	    return this.schemeIdEq;
	}
	public void setSchemeIdEq(String schemeIdEq) {
	    this.schemeIdEq=schemeIdEq;
	}
	private Integer createBy;//
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date createDt;//
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private String contractNumber;//合同编号
	public String getContractNumber() {
	    return this.contractNumber;
	}
	public void setContractNumber(String contractNumber) {
	    this.contractNumber=contractNumber;
	}
	private String contractNumberEq;//合同编号
	public String getContractNumberEq() {
	    return this.contractNumberEq;
	}
	public void setContractNumberEq(String contractNumberEq) {
	    this.contractNumberEq=contractNumberEq;
	}
	private String contractTitle;//合同标题
	public String getContractTitle() {
	    return this.contractTitle;
	}
	public void setContractTitle(String contractTitle) {
	    this.contractTitle=contractTitle;
	}
	private String contractTitleEq;//合同标题
	public String getContractTitleEq() {
	    return this.contractTitleEq;
	}
	public void setContractTitleEq(String contractTitleEq) {
	    this.contractTitleEq=contractTitleEq;
	}
	private Integer contractState;//待发起：0            待签署；1            已签署：2            已关闭：9
	public Integer getContractState() {
	    return this.contractState;
	}
	public void setContractState(Integer contractState) {
	    this.contractState=contractState;
	}
	private Integer serviceState;//1:待签署2：已签署
	public Integer getServiceState() {
	    return this.serviceState;
	}
	public void setServiceState(Integer serviceState) {
	    this.serviceState=serviceState;
	}
	private Integer companyState;//1:待签署2：已签署
	public Integer getCompanyState() {
	    return this.companyState;
	}
	public void setCompanyState(Integer companyState) {
	    this.companyState=companyState;
	}
	private String downUrl;//
	public String getDownUrl() {
	    return this.downUrl;
	}
	public void setDownUrl(String downUrl) {
	    this.downUrl=downUrl;
	}
	private String downUrlEq;//
	public String getDownUrlEq() {
	    return this.downUrlEq;
	}
	public void setDownUrlEq(String downUrlEq) {
	    this.downUrlEq=downUrlEq;
	}
	private String viewUrl;//
	public String getViewUrl() {
	    return this.viewUrl;
	}
	public void setViewUrl(String viewUrl) {
	    this.viewUrl=viewUrl;
	}
	private String viewUrlEq;//
	public String getViewUrlEq() {
	    return this.viewUrlEq;
	}
	public void setViewUrlEq(String viewUrlEq) {
	    this.viewUrlEq=viewUrlEq;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.contractId!=null)
			ht.put("contractId",this.contractId);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.fileId!=null)
			ht.put("fileId",this.fileId);
		if(this.contractName!=null)
			ht.put("contractName",this.contractName);
		if(this.contractNameEq!=null)
			ht.put("contractNameEq",this.contractNameEq);
		if(this.orderId!=null)
			ht.put("orderId",this.orderId);
		if(this.onlineType!=null)
			ht.put("onlineType",this.onlineType);
		if(this.content!=null)
			ht.put("content",this.content);
		if(this.contentEq!=null)
			ht.put("contentEq",this.contentEq);
		if(this.state!=null)
			ht.put("state",this.state);
		if(this.stateEq!=null)
			ht.put("stateEq",this.stateEq);
		if(this.schemeId!=null)
			ht.put("schemeId",this.schemeId);
		if(this.schemeIdEq!=null)
			ht.put("schemeIdEq",this.schemeIdEq);
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
		if(this.contractNumber!=null)
			ht.put("contractNumber",this.contractNumber);
		if(this.contractNumberEq!=null)
			ht.put("contractNumberEq",this.contractNumberEq);
		if(this.contractTitle!=null)
			ht.put("contractTitle",this.contractTitle);
		if(this.contractTitleEq!=null)
			ht.put("contractTitleEq",this.contractTitleEq);
		if(this.contractState!=null)
			ht.put("contractState",this.contractState);
		if(this.serviceState!=null)
			ht.put("serviceState",this.serviceState);
		if(this.companyState!=null)
			ht.put("companyState",this.companyState);
		if(this.downUrl!=null)
			ht.put("downUrl",this.downUrl);
		if(this.downUrlEq!=null)
			ht.put("downUrlEq",this.downUrlEq);
		if(this.viewUrl!=null)
			ht.put("viewUrl",this.viewUrl);
		if(this.viewUrlEq!=null)
			ht.put("viewUrlEq",this.viewUrlEq);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 10:44:44
	
	// 商品ID
	private Integer productId;
	public Integer getProductId() {
	    return this.productId;
	}
	public void setProductId(Integer productId) {
	    this.productId=productId;
	}
}

