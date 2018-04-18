package com.xfs.business.dto;

import java.util.List;

/* *
* 批量替换人员比例查询类
 * @author 	: yangf
 * @date 	: 2016/11/16 15:01
 * @version 	: V1.0
 */
public class BatchRatioEmpQuery implements java.io.Serializable{
    private Integer spId;
    private Integer cpId;
    private String insuranceType;
    private String statesStr;
    private List<String> states;
    private String insuranceIivingType;
    private Integer ratioId;
    private Integer insuranceId;
    private String beginPeriod;
    private String livingTypesStr;
    private List<String> livingTypes;
    private Integer newRatioId;
    private Integer areaId;
    private Integer dr;
    private String chk_emp_ids;
    // 数据权限
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getNewRatioId() {
        return newRatioId;
    }

    public void setNewRatioId(Integer newRatioId) {
        this.newRatioId = newRatioId;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getStatesStr() {
        return statesStr;
    }

    public void setStatesStr(String statesStr) {
        this.statesStr = statesStr;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getInsuranceIivingType() {
        return insuranceIivingType;
    }

    public void setInsuranceIivingType(String insuranceIivingType) {
        this.insuranceIivingType = insuranceIivingType;
    }

    public Integer getRatioId() {
        return ratioId;
    }

    public void setRatioId(Integer ratioId) {
        this.ratioId = ratioId;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getLivingTypesStr() {
        return livingTypesStr;
    }

    public void setLivingTypesStr(String livingTypesStr) {
        this.livingTypesStr = livingTypesStr;
    }

    public List<String> getLivingTypes() {
        return livingTypes;
    }

    public void setLivingTypes(List<String> livingTypes) {
        this.livingTypes = livingTypes;
    }

	public String getChk_emp_ids() {
		return chk_emp_ids;
	}

	public void setChk_emp_ids(String chk_emp_ids) {
		this.chk_emp_ids = chk_emp_ids;
	}
}
