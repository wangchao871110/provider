package com.xfs.business.model;

import java.util.HashMap;

/**
 * Created by zhao on 2017/3/21.
 */
public class BdBsareatypeProcess implements java.io.Serializable{

    private static final long serialVersionUID = 1L;


    private Integer id;

    private Integer bdBsareatypeId;

    private Integer sysDictitemId;

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    private Integer step;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBdBsareatypeId() {
        return bdBsareatypeId;
    }

    public void setBdBsareatypeId(Integer bdBsareatypeId) {
        this.bdBsareatypeId = bdBsareatypeId;
    }

    public Integer getSysDictitemId() {
        return sysDictitemId;
    }

    public void setSysDictitemId(Integer sysDictitemId) {
        this.sysDictitemId = sysDictitemId;
    }


    public void fixup(){
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRoleGroupCode() {
        return roleGroupCode;
    }

    public void setRoleGroupCode(String roleGroupCode) {
        this.roleGroupCode = roleGroupCode;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public HashMap<String,Object> toHashMap() {
        HashMap<String,Object> ht = new HashMap<String,Object>();
        if(this.id!=null)
            ht.put("id",this.id);
        if(this.bdBsareatypeId!=null)
            ht.put("bdBsareatypeId",this.bdBsareatypeId);

        if(this.sysDictitemId!=null)
            ht.put("sysDictitemId",this.sysDictitemId);
        if(this.step!=null)
            ht.put("step",this.step);
        if (this.orgType!=null)
            ht.put("orgType",this.orgType);
        if (this.companyType!=null)
            ht.put("companyType",this.companyType);
        if (this.taskStatus!=null)
            ht.put("taskStatus",this.taskStatus);
        if (this.roleGroupCode!=null)
            ht.put("roleGroupCode",this.roleGroupCode);
        if (this.actionName!=null)
            ht.put("actionName",this.actionName);
        if (this.actionCode!=null)
            ht.put("actionCode",this.actionCode);
        if (this.sysdName!=null)
            ht.put("sysdName",this.sysdName);
        return ht;
    }

    private  Integer orgType;

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    private String taskStatus; //任务单大状态

    private String roleGroupCode; //角色组

    private String actionName; //行为名称

    private String actionCode; //行为编码

    private String companyType; //公司类型

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    private String sysdName;

    public String getSysdName() {
        return sysdName;
    }

    public void setSysdName(String sysdName) {
        this.sysdName = sysdName;
    }
}
