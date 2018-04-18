package com.xfs.mall.dto;

import java.util.Date;

import com.xfs.mall.customer.model.CsMallCustomerAssess;

/**
 * IM评价DTO
 * @author han
 *
 */
public class CsMallCustomerAssessDTO {

	/** id */
	private Integer id;
	/** spId 查询条件 */
	private Integer spId;
	/** 客服id */
	private Integer customerId;
	/** 客服信息 */
	private String customerInfo;
	/** 用户id */
	private Integer userId;
	/** 用户信息 */
	private String userInfo;
	/** 评价等级 */
	private Integer assessLevel;
	/** 是否解决了问题 */
	private Integer result;
	/** 具体描述 */
	private String description;
	/** 评价时间 */
	private Date createDt;
	/** 评价时间Str */
	private String createDtStr;
	/** get传汉字的编码 */
	private String customerInfoEncode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getAssessLevel() {
		return assessLevel;
	}
	public void setAssessLevel(Integer assessLevel) {
		this.assessLevel = assessLevel;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getCustomerInfoEncode() {
		return customerInfoEncode;
	}
	public void setCustomerInfoEncode(String customerInfoEncode) {
		this.customerInfoEncode = customerInfoEncode;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public String getCreateDtStr() {
		return createDtStr;
	}
	public void setCreateDtStr(String createDtStr) {
		this.createDtStr = createDtStr;
	}

	public CsMallCustomerAssess dtoToModel() {
		// TODO Auto-generated method stub
		CsMallCustomerAssess model = new CsMallCustomerAssess();
		model.setCustomerId(this.customerId);
		model.setUserId(this.userId);
		model.setAssessLevel(this.assessLevel);
		model.setResult(this.result);
		model.setDescription(this.description);
		model.setDr(0);
		return model;
	}
}
