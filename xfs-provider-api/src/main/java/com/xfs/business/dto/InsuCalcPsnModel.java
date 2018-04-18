package com.xfs.business.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.xfs.common.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 社保计算器 人员模型
 * Created by yangf on 2016/12/2.
 */
public class InsuCalcPsnModel implements java.io.Serializable{

    @JSONField(name="_id")
    private String _id;
    private String importNo;
    private Integer orgId;
    //姓名
    private String name;
    //工号
    private String empNo;
    //户口类型
    private String livingType;
    //户口类型名称
    private String livingTypeName;
    //地区
    private Integer areaId;
    //基础工资
    private BigDecimal wages;
    //社保基数
    private BigDecimal insuranceBase;
    //公积金基数
    private BigDecimal fundBase;
    //险种集合
    private Map<String,InsuranceTypeRatio> ratioMap;
    //个人缴扣合计
    private BigDecimal psnCount;
    //个人险种缴扣
    private Map<String,BigDecimal> psnInsurance;
    //实发工资
    private BigDecimal readWages;
    //企业缴扣合计
    private BigDecimal corpCount;
    //企业险种缴扣
    private Map<String,BigDecimal> corpInsurance;
    //五险一金合计
    private BigDecimal insuCount;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getLivingType() {
        return livingType;
    }

    public void setLivingType(String livingType) {
        this.livingType = livingType;
    }

    public String getLivingTypeName() {
        return livingTypeName;
    }

    public void setLivingTypeName(String livingTypeName) {
        this.livingTypeName = livingTypeName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getWages() {
        return wages;
    }

    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }

    public BigDecimal getInsuranceBase() {
        return insuranceBase;
    }

    public void setInsuranceBase(BigDecimal insuranceBase) {
        this.insuranceBase = insuranceBase;
    }

    public BigDecimal getFundBase() {
        return fundBase;
    }

    public void setFundBase(BigDecimal fundBase) {
        this.fundBase = fundBase;
    }

    public Map<String, InsuranceTypeRatio> getRatioMap() {
        return ratioMap;
    }

    public void setRatioMap(Map<String, InsuranceTypeRatio> ratioMap) {
        this.ratioMap = ratioMap;
    }

    public BigDecimal getPsnCount() {
        return psnCount;
    }

    public void setPsnCount(BigDecimal psnCount) {
        this.psnCount = psnCount;
    }

    public Map<String, BigDecimal> getPsnInsurance() {
        return psnInsurance;
    }

    public void setPsnInsurance(Map<String, BigDecimal> psnInsurance) {
        this.psnInsurance = psnInsurance;
    }

    public BigDecimal getReadWages() {
        return readWages;
    }

    public void setReadWages(BigDecimal readWages) {
        this.readWages = readWages;
    }

    public BigDecimal getCorpCount() {
        return corpCount;
    }

    public void setCorpCount(BigDecimal corpCount) {
        this.corpCount = corpCount;
    }

    public Map<String, BigDecimal> getCorpInsurance() {
        return corpInsurance;
    }

    public void setCorpInsurance(Map<String, BigDecimal> corpInsurance) {
        this.corpInsurance = corpInsurance;
    }

    public BigDecimal getInsuCount() {
        return insuCount;
    }

    public void setInsuCount(BigDecimal insuCount) {
        this.insuCount = insuCount;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> _hashedMap = new HashedMap();
        if (StringUtils.isNotBlank(this.getName())) {
            _hashedMap.put("name", this.getName());
        }
        if (StringUtils.isNotBlank(this.getEmpNo())) {
            _hashedMap.put("empNo", this.getEmpNo());
        }
        if (StringUtils.isNotBlank(this.getLivingType())) {
            _hashedMap.put("livingType", this.getLivingType());
        }
        if (StringUtils.isNotBlank(this.getLivingTypeName())) {
            _hashedMap.put("livingTypeName", this.getLivingTypeName());
        }
        if (this.getInsuranceBase() != null) {
            _hashedMap.put("insuranceBase", this.getInsuranceBase());
        }
        if (this.getFundBase() != null) {
            _hashedMap.put("fundBase", this.getFundBase());
        }
        if (this.getPsnCount() != null) {
            _hashedMap.put("psn.psnCount", this.getPsnCount());
        }
        if (this.getPsnInsurance() != null) {
            for (Map.Entry<String, BigDecimal> entry: this.getPsnInsurance().entrySet()) {
                _hashedMap.put("psn." + entry.getKey(), entry.getValue());
            }
        }
        if (this.getCorpCount() != null) {
            _hashedMap.put("cpy.corpCount", this.getCorpCount());
        }
        if (this.getCorpInsurance() != null) {
            for (Map.Entry<String, BigDecimal> entry: this.getCorpInsurance().entrySet()) {
                _hashedMap.put("cpy." + entry.getKey(), entry.getValue());
            }
        }
        if (this.getInsuCount() != null) {
            _hashedMap.put("insuCount", this.getInsuCount());
        }
        return _hashedMap;
    }

}
