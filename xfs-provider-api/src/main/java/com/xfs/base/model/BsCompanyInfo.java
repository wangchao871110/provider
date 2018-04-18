package com.xfs.base.model;

import java.util.List;

/**
 * BsCiScheme
 * @author:guopeng
 * @date:2016/09/03 11:44:03	
 */
public class BsCompanyInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private String name;//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String qxid;

	public String getQXId() {
		return qxid;
	}

	public void setQXId(String qxid) {
		this.qxid = qxid;
	}

	private String start_date;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	private String oper_name;

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	private String xfsSearchStatus;

	public String getXfsSearchStatus() {
		return xfsSearchStatus;
	}

	public void setXfsSearchStatus(String xfsSearchStatus) {
		this.xfsSearchStatus = xfsSearchStatus;
	}

	private String contactJson;

	public String getContactJson() {
		return contactJson;
	}

	public void setContactJson(String contactJson) {
		this.contactJson = contactJson;
	}

	private List<BsCompanyInfoDetail> companyInfoDetails;

	public List<BsCompanyInfoDetail> getCompanyInfoDetails() {
		return companyInfoDetails;
	}

	public void setCompanyInfoDetails(List<BsCompanyInfoDetail> companyInfoDetails) {
		this.companyInfoDetails = companyInfoDetails;
	}

	private String creditNo;

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	private String registeredCapital;//

	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	private String registeredGov;//

	public String getRegisteredGov() {
		return this.registeredGov;
	}

	public void setRegisteredGov(String registeredGov) {
		this.registeredGov = registeredGov;
	}

	private String busiScope;//

	public String getBusiScope() {
		return this.busiScope;
	}

	public void setBusiScope(String busiScope) {
		this.busiScope = busiScope;
	}
	
	private String companyType;
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}


	public static final String SearchStatus_Base = "BASE";
	public static final String SearchStatus_Finish = "FINISH";

}
