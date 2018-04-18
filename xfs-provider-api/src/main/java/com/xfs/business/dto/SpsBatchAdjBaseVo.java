package com.xfs.business.dto;

import com.xfs.business.model.SpsBatchAdjBase;

import java.util.List;
import java.util.Map;

/**
 * 批量调整基数业务类
 * Created by yangf on 2016/12/28.
 */
public class SpsBatchAdjBaseVo extends SpsBatchAdjBase implements java.io.Serializable{
    private String areaName;
    private String shortName;
    private String createName;
    private Integer adjType;
    private List<Map<String,String>> empJsons;

    public List<Map<String, String>> getEmpJsons() {
        return empJsons;
    }

    public void setEmpJsons(List<Map<String, String>> empJsons) {
        this.empJsons = empJsons;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public Integer getAdjType() {
        return adjType;
    }

    @Override
    public void setAdjType(Integer adjType) {
        this.adjType = adjType;
    }

    // 数据权限
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
