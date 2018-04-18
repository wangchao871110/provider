package com.xfs.op.model;

import java.io.Serializable;
import java.util.Date;

public class OpHeadlineShortUrl implements Serializable{

    private static final long serialVersionUID = 1L;

    private String shortId;

    private String shrotUrl;

    private String fullUrl;

    private Date createDt;

    private Date modifyDt;

    private Integer dr;

    private Integer times;

    private String remark;

    private Long useTime;

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId == null ? null : shortId.trim();
    }

    public String getShrotUrl() {
        return shrotUrl;
    }

    public void setShrotUrl(String shrotUrl) {
        this.shrotUrl = shrotUrl == null ? null : shrotUrl.trim();
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl == null ? null : fullUrl.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }
}