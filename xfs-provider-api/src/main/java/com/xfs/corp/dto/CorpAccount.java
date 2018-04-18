package com.xfs.corp.dto;

import java.util.Date;

/**
 * CmCorp
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/01/22 10:12:02
 * 
 * @modifyBy wangxs
 * @date 2016年6月15日下午2:04:39
 */
public class CorpAccount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cpId;// 主键
	private String corpName;// 企业名称
	private Integer status;// 状态
	private String contactPsn;// 联系人
	private String mobile;// 联系手机

	private Date createDt;// 创建时间

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    private Date lastLoginTime;// 最后登录时间

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContactPsn() {
		return contactPsn;
	}

	public void setContactPsn(String contactPsn) {
		this.contactPsn = contactPsn;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	
	
}
