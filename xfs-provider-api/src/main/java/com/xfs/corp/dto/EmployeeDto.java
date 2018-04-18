package com.xfs.corp.dto;

import com.xfs.corp.model.CmEmployee;

/**
 * bang向saas导入员工数据dto
 * Created by dasxi on 2016/12/26.
 */
public class EmployeeDto {
    //企业员工class
    private CmEmployee cmEmployee;
    //城市id
    private Integer areaId;
    //参保地
    private String areaName;

    public EmployeeDto() {
    	cmEmployee=new CmEmployee();
	}

    public CmEmployee getCmEmployee() {
        return cmEmployee;
    }

    public void setCmEmployee(CmEmployee cmEmployee) {
        this.cmEmployee = cmEmployee;
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
}
