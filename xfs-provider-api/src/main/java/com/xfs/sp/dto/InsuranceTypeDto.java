package com.xfs.sp.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.BsRatioCalcInterface;

/**
 * 险种
 * Created by yangf on 2016/8/22.
 */
public class InsuranceTypeDto implements BsRatioCalcInterface,java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    //险种名称
    private String insuranceName;
    //公司缴费比例
    private BigDecimal companyRatio;
    //个人缴费比例
    private BigDecimal personalRatio;
    private Boolean isCheck;
    //险种id
    private Integer insuranceTypeId;
    //是否固定比例
    private Boolean finalRatio;

    //基数
    private BigDecimal payBase;

    public BigDecimal getPayBase() {
        return payBase;
    }

    public void setPayBase(BigDecimal payBase) {
        this.payBase = payBase;
    }

    public Boolean getFinalRatio() {
        return finalRatio;
    }

    public void setFinalRatio(Boolean finalRatio) {
        this.finalRatio = finalRatio;
    }

    public Integer getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public void setInsuranceTypeId(Integer insuranceTypeId) {
        this.insuranceTypeId = insuranceTypeId;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean check) {
        isCheck = check;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    public BigDecimal getPersonalRatio() {
        return personalRatio;
    }

    public void setPersonalRatio(BigDecimal personalRatio) {
        this.personalRatio = personalRatio;
    }

    //个人比例
    private Set<BigDecimal> personalRatioSet = new LinkedHashSet<BigDecimal>();

    public Set<BigDecimal> getPersonalRatioSet() {
        return personalRatioSet;
    }

    public void setPersonalRatioSet(Set<BigDecimal> personalRatioSet) {
        this.personalRatioSet = personalRatioSet;
    }

    public Set<BigDecimal> getCompanyRatioSet() {
        return companyRatioSet;
    }

    public void setCompanyRatioSet(Set<BigDecimal> companyRatioSet) {
        this.companyRatioSet = companyRatioSet;
    }

    //公司
    private Set<BigDecimal> companyRatioSet = new LinkedHashSet<BigDecimal>();


    //个人比例List
    private List<BigDecimal> personalRatioList = new ArrayList<BigDecimal>();


    //公司比例List
    private List<BigDecimal> companyRatioList = new ArrayList<BigDecimal>();

    public List<BigDecimal> getCompanyRatioList() {
        return companyRatioList;
    }

    public void setCompanyRatioList(List<BigDecimal> companyRatioList) {
        this.companyRatioList = companyRatioList;
    }

    public List<BigDecimal> getPersonalRatioList() {
        return personalRatioList;
    }

    public void setPersonalRatioList(List<BigDecimal> personalRatioList) {
        this.personalRatioList = personalRatioList;
    }

    public Integer ratioId;

    private Integer insuranceId;
    private BigDecimal empPayment;
    private BigDecimal corpPayment;

    private BigDecimal corpRatio;

    public BigDecimal getCorpRatio() {
        return corpRatio;
    }

    public void setCorpRatio(BigDecimal corpRatio) {
        this.corpRatio = corpRatio;
    }

    public BigDecimal getEmpPaybase() {
        return empPaybase;
    }

    public void setEmpPaybase(BigDecimal empPaybase) {
        this.empPaybase = empPaybase;
    }

    public BigDecimal getCorpPaybase() {
        return corpPaybase;
    }

    public void setCorpPaybase(BigDecimal corpPaybase) {
        this.corpPaybase = corpPaybase;
    }

    private BigDecimal empPaybase;
    private BigDecimal corpPaybase;

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public BigDecimal getEmpPayment() {
        return empPayment;
    }

    public void setEmpPayment(BigDecimal empPayment) {
        this.empPayment = empPayment;
    }

    public BigDecimal getCorpPayment() {
        return corpPayment;
    }

    public void setCorpPayment(BigDecimal corpPayment) {
        this.corpPayment = corpPayment;
    }

    public BigDecimal getEmpRatio() {
        return empRatio;
    }

    public void setEmpRatio(BigDecimal empRatio) {
        this.empRatio = empRatio;
    }



    private BigDecimal empRatio;



    public Integer getRatioId() {
        return ratioId;
    }



    public void setRatioId(Integer ratioId) {
        this.ratioId = ratioId;
    }

    public String ratioName;

    public String getRatioName() {
        return ratioName;
    }

    public void setRatioName(String ratioName) {
        this.ratioName = ratioName;
    }


    private BigDecimal corpAddition;

    private BigDecimal psnAddition;

    public BigDecimal getCorpAddition() {
        return corpAddition;
    }

    public void setCorpAddition(BigDecimal corpAddition) {
        this.corpAddition = corpAddition;
    }

    public BigDecimal getPsnAddition() {
        return psnAddition;
    }

    public void setPsnAddition(BigDecimal psnAddition) {
        this.psnAddition = psnAddition;
    }

    private String beginDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
   public List<BsArearatiover> bsArearatiovers;

    public List<BsArearatiover> getBsArearatiovers() {
        return bsArearatiovers;
    }

    public void setBsArearatiovers(List<BsArearatiover> bsArearatiovers) {
        this.bsArearatiovers = bsArearatiovers;
    }

    public BsArearatiover bsArearatiover;

    public BsArearatiover getBsArearatiover() {
        return bsArearatiover;
    }

    public void setBsArearatiover(BsArearatiover bsArearatiover) {
        this.bsArearatiover = bsArearatiover;
    }

    public String beginPeriod;
    public String endPeriod;

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }
    private BigDecimal corpBaseLower;//企业基数下限
    public BigDecimal getCorpBaseLower() {
        return this.corpBaseLower;
    }
    public void setCorpBaseLower(BigDecimal corpBaseLower) {
        this.corpBaseLower=corpBaseLower;
    }
    private BigDecimal corpBaseUpper;//企业基数上限
    public BigDecimal getCorpBaseUpper() {
        return this.corpBaseUpper;
    }
    public void setCorpBaseUpper(BigDecimal corpBaseUpper) {
        this.corpBaseUpper=corpBaseUpper;
    }
    private BigDecimal psnBaseLower;//个人基数下限
    public BigDecimal getPsnBaseLower() {
        return this.psnBaseLower;
    }
    public void setPsnBaseLower(BigDecimal psnBaseLower) {
        this.psnBaseLower=psnBaseLower;
    }
    private BigDecimal psnBaseUpper;//个人基数上限
    public BigDecimal getPsnBaseUpper() {
        return this.psnBaseUpper;
    }
    public void setPsnBaseUpper(BigDecimal psnBaseUpper) {
        this.psnBaseUpper=psnBaseUpper;
    }

    private String psnBaseType;
    private String corpBaseType;

    public String getPsnBaseType() {
        return psnBaseType;
    }

    public void setPsnBaseType(String psnBaseType) {
        this.psnBaseType = psnBaseType;
    }

    public String getCorpBaseType() {
        return corpBaseType;
    }

    public void setCorpBaseType(String corpBaseType) {
        this.corpBaseType = corpBaseType;
    }
}
