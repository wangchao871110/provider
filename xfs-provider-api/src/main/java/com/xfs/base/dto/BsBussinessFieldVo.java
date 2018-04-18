package com.xfs.base.dto;

/**
 * Created by yangf on 2016/5/24.
 */
public class BsBussinessFieldVo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Integer fieldId;//
    private String code;//物理字段名，存在于json中
    private String name;//
    private String source;//来源表，或者是一组业务表共同字段                        保险字段            任务字段
    private String required;//是否必填
    private String isEnable;//是否启用
    public Integer getFieldId() {
        return this.fieldId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId=fieldId;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code=code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source=source;
    }
}
