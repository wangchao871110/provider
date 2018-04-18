package com.xfs.corp.dto;

import java.math.BigDecimal;

/**
 * 员工保险情况dto
 * Created by yangf on 2016/7/25.
 */
public class EmplInsuranceDto  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String insuranceName;
    private String type;
    private String startTime;
    private BigDecimal cardinalNum;
    private String cityName;
    private Integer taskId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getCardinalNum() {
        return cardinalNum;
    }

    public void setCardinalNum(BigDecimal cardinalNum) {
        this.cardinalNum = cardinalNum;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
