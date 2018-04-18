package com.xfs.base.dto;

import java.awt.geom.Area;
import java.util.List;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-14 17:22
 */
public class AreaTree implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;
    private String areaCode;
    private Integer areaId;
    private Integer parentId;
    private List<AreaTree> childArea;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<AreaTree> getChildArea() {
        return childArea;
    }

    public void setChildArea(List<AreaTree> childArea) {
        this.childArea = childArea;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object obj) {
        AreaTree listInArea = (AreaTree)obj;
        if(this.getParentId().equals(listInArea.getAreaId())){
            listInArea.getChildArea().add(this);
            return true;
        }else if(null != listInArea.getChildArea() && !listInArea.getChildArea().isEmpty()){
            return listInArea.getChildArea().contains(this);
        }
        return false;
    }
}
