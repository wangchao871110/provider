package com.xfs.base.dto;

import com.xfs.base.model.SysDictitem;

import java.util.List;

/**
 * Created by Administrator on 2016-07-25.
 */
public class BusinessField {
    private static final long serialVersionUID = 1L;

    private Integer bstypeId;
    private Integer areaId;
    private String required;
    private Integer field_id;
    private String code;
    private String name;
    private String type;
    private String insuranceFundType;
    private List<SysDictitem> options;//下拉框选项

    public Integer getBstypeId() {
        return bstypeId;
    }

    public void setBstypeId(Integer bstypeId) {
        this.bstypeId = bstypeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Integer getField_id() {
        return field_id;
    }

    public void setField_id(Integer field_id) {
        this.field_id = field_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInsuranceFundType() {
        return insuranceFundType;
    }

    public void setInsuranceFundType(String insuranceFundType) {
        this.insuranceFundType = insuranceFundType;
    }

    public List<SysDictitem> getOptions() {
        return options;
    }

    public void setOptions(List<SysDictitem> options) {
        this.options = options;
    }

    //  2016-10-19 add by wuzhe
    //     pageIsHidden 是否隐藏页面
    //     modelIsHidden  是否隐藏模板
    //     defaultValue 默认值

    private String pageIsHidden;
    private String modelIsHidden;
    private String defaultValue;

    public String getPageIsHidden() {
        return pageIsHidden;
    }

    public void setPageIsHidden(String pageIsHidden) {
        this.pageIsHidden = pageIsHidden;
    }

    public String getModelIsHidden() {
        return modelIsHidden;
    }

    public void setModelIsHidden(String modelIsHidden) {
        this.modelIsHidden = modelIsHidden;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 说明
     */
    private  String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
