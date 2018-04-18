package com.xfs.corp.dto;

import java.util.HashMap;

import com.xfs.corp.model.CmEmployee;

/**
 * 员工业务类
 * Created by yangf on 2016/7/25.
 */
public class CSCmEmployeeDto extends CmEmployee implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String sex;
    private Integer age;
    private String orgName;
    private String depName;
    private String insuranceStateName;
    private String fundStateName;
    /**
     * 查询使用 in查询
     */
    private String depIn;
    //最新任务状态
    private String taskStatus;
    //户籍地址
    private String cityName;

    public String getInsuranceStateName() {
        return insuranceStateName;
    }

    public void setInsuranceStateName(String insuranceStateName) {
        this.insuranceStateName = insuranceStateName;
    }

    public String getFundStateName() {
        return fundStateName;
    }

    public void setFundStateName(String fundStateName) {
        this.fundStateName = fundStateName;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> ht = super.toHashMap();
        if(this.depIn != null){
            ht.put("depIn",depIn);
        }
        return ht;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDepIn() {
        return depIn;
    }

    public void setDepIn(String depIn) {
        this.depIn = depIn;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
