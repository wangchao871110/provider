package com.xfs.business.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * 社保计算器 头部
 * Created by yangf on 2016/12/2.
 */
public class InsuCalcHead implements java.io.Serializable{

    @JSONField(name="_id")
    private String _id;
    //导入编号
    private String importNo;
    //地区id
    private Integer areaId;
    //地区名称
    private String areaName;
    //险种 cede 与名称
    private Map<String,String> insuranceMap;
    //参保类型map  code 名称
    private Map<String,String> livingTypeMap;

    public Map<String, String> getLivingTypeMap() {
        return livingTypeMap;
    }

    public void setLivingTypeMap(Map<String, String> livingTypeMap) {
        this.livingTypeMap = livingTypeMap;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Map<String, String> getInsuranceMap() {
        return insuranceMap;
    }

    public void setInsuranceMap(Map<String, String> insuranceMap) {
        this.insuranceMap = insuranceMap;
    }
}
