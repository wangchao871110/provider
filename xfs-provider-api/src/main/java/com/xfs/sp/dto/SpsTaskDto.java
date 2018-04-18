package com.xfs.sp.dto;


import com.xfs.business.model.SpsTask;

/**
 * Created by Administrator on 2016/8/5.
 */
public class SpsTaskDto extends SpsTask {
    private String typeName;
    private String ifType;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }
}
