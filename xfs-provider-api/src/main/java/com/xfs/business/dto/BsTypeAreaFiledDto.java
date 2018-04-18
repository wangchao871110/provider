package com.xfs.business.dto;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-24 09:35
 */
public class BsTypeAreaFiledDto implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String bstypeId;
    private String areaId;
    private String required;
    private String defaultValue;
    private String code;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBstypeId() {
        return bstypeId;
    }

    public void setBstypeId(String bstypeId) {
        this.bstypeId = bstypeId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
