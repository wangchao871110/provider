package com.xfs.business.dto;

/**
 * 模板属性配置业务类
 * Created by yangf on 2016/5/24.
 */
public class BsAreaTypeVo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;//
    private Integer bstypeId;//
    private Integer areaId;//
    private String typeName;
    private String areaName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id=id;
    }
    public Integer getBstypeId() {
        return this.bstypeId;
    }
    public void setBstypeId(Integer bstypeId) {
        this.bstypeId=bstypeId;
    }
    public Integer getAreaId() {
        return this.areaId;
    }
    public void setAreaId(Integer areaId) {
        this.areaId=areaId;
    }
    public void fixup(){
    }
}
