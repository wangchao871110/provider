package com.xfs.corp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangxs on 2016/8/3.
 */



public class CmEmployeeDto {

    private String insuranceAccountName;
    private String fundAccountName;

    private String shortName;
    
    private String areaname;
    
    

    public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getInsuranceAccountName() {
        return insuranceAccountName;
    }

    public void setInsuranceAccountName(String insuranceAccountName) {
        this.insuranceAccountName = insuranceAccountName;
    }

    public String getFundAccountName() {
        return fundAccountName;
    }

    public void setFundAccountName(String fundAccountName) {
        this.fundAccountName = fundAccountName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Integer getQuit() {
        return isQuit;
    }

    public void setQuit(Integer quit) {
        isQuit = quit;
    }

    private String corpName;
    private String schemeType;
    private String spName;

    private Integer empId;//
    public Integer getEmpId() {
        return this.empId;
    }
    public void setEmpId(Integer empId) {
        this.empId=empId;
    }
    private Integer cpId;//
    public Integer getCpId() {
        return this.cpId;
    }
    public void setCpId(Integer cpId) {
        this.cpId=cpId;
    }
    private String name;//
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    private String nameEq;//
    public String getNameEq() {
        return this.nameEq;
    }
    public void setNameEq(String nameEq) {
        this.nameEq=nameEq;
    }
    private String identityType;//身份证：ID
    public String getIdentityType() {
        return this.identityType;
    }
    public void setIdentityType(String identityType) {
        this.identityType=identityType;
    }
    private String identityTypeEq;//身份证：ID
    public String getIdentityTypeEq() {
        return this.identityTypeEq;
    }
    public void setIdentityTypeEq(String identityTypeEq) {
        this.identityTypeEq=identityTypeEq;
    }
    private String identityCode;//
    public String getIdentityCode() {
        return this.identityCode;
    }
    public void setIdentityCode(String identityCode) {
        this.identityCode=identityCode;
    }
    private String identityCodeEq;//
    public String getIdentityCodeEq() {
        return this.identityCodeEq;
    }
    public void setIdentityCodeEq(String identityCodeEq) {
        this.identityCodeEq=identityCodeEq;
    }
    private String job;//
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job=job;
    }
    private String jobEq;//
    public String getJobEq() {
        return this.jobEq;
    }
    public void setJobEq(String jobEq) {
        this.jobEq=jobEq;
    }
    private String socialInsuranceNum;//
    public String getSocialInsuranceNum() {
        return this.socialInsuranceNum;
    }
    public void setSocialInsuranceNum(String socialInsuranceNum) {
        this.socialInsuranceNum=socialInsuranceNum;
    }
    private String socialInsuranceNumEq;//
    public String getSocialInsuranceNumEq() {
        return this.socialInsuranceNumEq;
    }
    public void setSocialInsuranceNumEq(String socialInsuranceNumEq) {
        this.socialInsuranceNumEq=socialInsuranceNumEq;
    }
    private String reserveFundNum;//
    public String getReserveFundNum() {
        return this.reserveFundNum;
    }
    public void setReserveFundNum(String reserveFundNum) {
        this.reserveFundNum=reserveFundNum;
    }
    private String reserveFundNumEq;//
    public String getReserveFundNumEq() {
        return this.reserveFundNumEq;
    }
    public void setReserveFundNumEq(String reserveFundNumEq) {
        this.reserveFundNumEq=reserveFundNumEq;
    }
    private String mobile;//
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile=mobile;
    }
    private String mobileEq;//
    public String getMobileEq() {
        return this.mobileEq;
    }
    public void setMobileEq(String mobileEq) {
        this.mobileEq=mobileEq;
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
    private Integer dr;//逻辑删除标记位                        0:未删除            1:已删除
    public Integer getDr() {
        return this.dr;
    }
    public void setDr(Integer dr) {
        this.dr=dr;
    }
    private String insuranceLivingType;//社保参保类型：insuranceLiveType，按地区获取
    public String getInsuranceLivingType() {
        return this.insuranceLivingType;
    }
    public void setInsuranceLivingType(String insuranceLivingType) {
        this.insuranceLivingType=insuranceLivingType;
    }
    private String insuranceLivingTypeEq;//社保参保类型：insuranceLiveType，按地区获取
    public String getInsuranceLivingTypeEq() {
        return this.insuranceLivingTypeEq;
    }
    public void setInsuranceLivingTypeEq(String insuranceLivingTypeEq) {
        this.insuranceLivingTypeEq=insuranceLivingTypeEq;
    }
    private String insuranceState;//INCREASING            ON            DECREASING            OFF
    public String getInsuranceState() {
        return this.insuranceState;
    }
    public void setInsuranceState(String insuranceState) {
        this.insuranceState=insuranceState;
    }
    private String insuranceStateEq;//INCREASING            ON            DECREASING            OFF
    public String getInsuranceStateEq() {
        return this.insuranceStateEq;
    }
    public void setInsuranceStateEq(String insuranceStateEq) {
        this.insuranceStateEq=insuranceStateEq;
    }
    private Integer isQuit;//
    public Integer getIsQuit() {
        return this.isQuit;
    }
    public void setIsQuit(Integer isQuit) {
        this.isQuit=isQuit;
    }
    private Integer schemeId;//
    public Integer getSchemeId() {
        return this.schemeId;
    }
    public void setSchemeId(Integer schemeId) {
        this.schemeId=schemeId;
    }
    private Integer depId;//
    public Integer getDepId() {
        return this.depId;
    }
    public void setDepId(Integer depId) {
        this.depId=depId;
    }
    private String email;//
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    private String emailEq;//
    public String getEmailEq() {
        return this.emailEq;
    }
    public void setEmailEq(String emailEq) {
        this.emailEq=emailEq;
    }
    private String openid;//
    public String getOpenid() {
        return this.openid;
    }
    public void setOpenid(String openid) {
        this.openid=openid;
    }
    private String openidEq;//
    public String getOpenidEq() {
        return this.openidEq;
    }
    public void setOpenidEq(String openidEq) {
        this.openidEq=openidEq;
    }
    private String fundState;//INCREASING            ON            DECREASING            OFF
    public String getFundState() {
        return this.fundState;
    }
    public void setFundState(String fundState) {
        this.fundState=fundState;
    }
    private String fundStateEq;//INCREASING            ON            DECREASING            OFF
    public String getFundStateEq() {
        return this.fundStateEq;
    }
    public void setFundStateEq(String fundStateEq) {
        this.fundStateEq=fundStateEq;
    }
    private Date entryTime;//
    public Date getEntryTime() {
        return this.entryTime;
    }
    public void setEntryTime(Date entryTime) {
        this.entryTime=entryTime;
    }
    private Date entryTimeFrom;//
    public Date getEntryTimeFrom() {
        return this.entryTimeFrom;
    }
    public void setEntryTimeFrom(Date entryTimeFrom) {
        this.entryTimeFrom=entryTimeFrom;
    }
    private Date entryTimeTo;//
    public Date getEntryTimeTo() {
        return this.entryTimeTo;
    }
    public void setEntryTimeTo(Date entryTimeTo) {
        this.entryTimeTo=entryTimeTo;
    }
    private Date leaveTime;//
    public Date getLeaveTime() {
        return this.leaveTime;
    }
    public void setLeaveTime(Date leaveTime) {
        this.leaveTime=leaveTime;
    }
    private Date leaveTimeFrom;//
    public Date getLeaveTimeFrom() {
        return this.leaveTimeFrom;
    }
    public void setLeaveTimeFrom(Date leaveTimeFrom) {
        this.leaveTimeFrom=leaveTimeFrom;
    }
    private Date leaveTimeTo;//
    public Date getLeaveTimeTo() {
        return this.leaveTimeTo;
    }
    public void setLeaveTimeTo(Date leaveTimeTo) {
        this.leaveTimeTo=leaveTimeTo;
    }
    private BigDecimal insuranceSalary;//
    public BigDecimal getInsuranceSalary() {
        return this.insuranceSalary;
    }
    public void setInsuranceSalary(BigDecimal insuranceSalary) {
        this.insuranceSalary=insuranceSalary;
    }
    private BigDecimal fundSalary;//
    public BigDecimal getFundSalary() {
        return this.fundSalary;
    }
    public void setFundSalary(BigDecimal fundSalary) {
        this.fundSalary=fundSalary;
    }

}
