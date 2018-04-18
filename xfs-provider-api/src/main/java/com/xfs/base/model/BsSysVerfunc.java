package com.xfs.base.model;

import java.util.Date;

/**
 *  版本记录表
 * @author wangxs
 * @date:2016/12/14 11:21:28
 */
public class BsSysVerfunc implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
    private Integer verId;     //版本id
    private String funcType;    // 记录类型
    private String funcInfo;    // 记录信息
    private Integer rank; // 排序

    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getFuncInfo() {
        return funcInfo;
    }

    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    private String	createBy; //
	private Date	createDt; //
	private String	modifyBy; //
	private Date	modifyDt; //
	private Integer	dr; //逻辑删除标记位0:未删除 1:已删除
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
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
	
}
