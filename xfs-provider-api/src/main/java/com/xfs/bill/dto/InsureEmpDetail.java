package com.xfs.bill.dto;


/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-06 15:40
 */
public class InsureEmpDetail {

    private Integer id;
    private String insureName;
    private String areaName;
    private String corpPaybase;
    private String corpPayment;
    private String corpRatio;
    private String empPaybase;
    private String empPayment;
    private String empRatio;
    private String totalPayment;
    private String period;
    private String remark;
    private String billId;
    private String billState;
    private String insuranceFundType;//社保、公积金
    private String code;
    private String corpAddition;
    private String empAddition;
    
    
    public String getCorpAddition() {
		return corpAddition;
	}

	public void setCorpAddition(String corpAddition) {
		this.corpAddition = corpAddition;
	}

	public String getEmpAddition() {
		return empAddition;
	}

	public void setEmpAddition(String empAddition) {
		this.empAddition = empAddition;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInsuranceFundType() {
		return insuranceFundType;
	}

	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCorpPaybase() {
        return corpPaybase;
    }

    public void setCorpPaybase(String corpPaybase) {
        this.corpPaybase = corpPaybase;
    }

    public String getCorpPayment() {
        return corpPayment;
    }

    public void setCorpPayment(String corpPayment) {
        this.corpPayment = corpPayment;
    }

    public String getCorpRatio() {
        return corpRatio;
    }

    public void setCorpRatio(String corpRatio) {
        this.corpRatio = corpRatio;
    }

    public String getEmpPaybase() {
        return empPaybase;
    }

    public void setEmpPaybase(String empPaybase) {
        this.empPaybase = empPaybase;
    }

    public String getEmpPayment() {
        return empPayment;
    }

    public void setEmpPayment(String empPayment) {
        this.empPayment = empPayment;
    }

    public String getEmpRatio() {
        return empRatio;
    }

    public void setEmpRatio(String empRatio) {
        this.empRatio = empRatio;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }
}
