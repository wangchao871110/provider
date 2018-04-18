package com.xfs.user.model.dto;

import java.io.Serializable;

public class SysUserToken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SysUserToken() {
		
	}
	
	public SysUserToken(Integer userId, String toSys) {
		setUserId(userId);
		setToSys(toSys);
	}
	public SysUserToken(Integer userId, String toSys, boolean isOnce, boolean isForceLogOut) {
		setUserId(userId);
		setToSys(toSys);
		setIsOnce(isOnce);
		setIsForceLogOut(isForceLogOut);
	}

	private Integer userId;
	/*
	 * 获取登陆用户ID
	 */
	/* (non-Javadoc)
	 * @see com.xfs.common.IContextInfo#getUserId()
	 */
	public Integer getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.xfs.common.IContextInfo#setUserId(java.lang.Integer)
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private String toSys;


	public String getToSys() {
		return toSys;
	}

	public void setToSys(String toSys) {
		this.toSys = toSys;
	}
	
	private boolean isOnce;

	public void setIsOnce(boolean isOnce) {
		this.isOnce = isOnce;
		
	}

	public boolean getIsOnce() {
		return isOnce;
	}
	
	/*
	 * 是否强制登陆，踢掉浏览器中已登录用户
	 */
	public boolean getIsForceLogOut() {
		return isForceLogOut;
	}

	public void setIsForceLogOut(boolean isForceLogOut) {
		this.isForceLogOut = isForceLogOut;
	}

	private boolean isForceLogOut;

}
