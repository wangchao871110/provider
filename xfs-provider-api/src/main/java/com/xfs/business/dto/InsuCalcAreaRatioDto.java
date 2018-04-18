package com.xfs.business.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 社保计算器 地区 参保类型 险种比例记录表
 * Created by yangf on 2016/12/2.
 */
public class InsuCalcAreaRatioDto implements java.io.Serializable {
    //mongo id
    @JSONField(name="_id")
    private String _id;
    //参保类型
    private String livingType;
    //参保类型名称
    private String livingTypeName;
    //地区id
    private Integer areaId;
    //地区名称
    private String areaName;
    //缴纳月份
    private String beginMonth;
    //导入编号
    private String importNo;
    //参保类型险种比例选择 险种code
    private Map<String,InsuranceTypeRatio> typeRatioMap;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        this.beginMonth = beginMonth;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    public Map<String, InsuranceTypeRatio> getTypeRatioMap() {
        return typeRatioMap;
    }

    public void setTypeRatioMap(Map<String, InsuranceTypeRatio> typeRatioMap) {
        this.typeRatioMap = typeRatioMap;
    }
}
