package com.xfs.user.model;

import com.xfs.common.IContextInfo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * SysUser
 * @author:zhangyan
 */
public class SysUser implements java.io.Serializable,IContextInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	/**
	 * 账号管理处 用户列表里 用于存储查出来每一个用户的UserId
	 */
	private Integer userIdEq;

	private String roleIds;

	private String roleCodes;

	private String roleNames;

	private String code;

	private String loginXfb;//首页登录薪福邦标识

	private String authority;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer roleId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userNameEq;

	public String getUserNameEq() {
		return userNameEq;
	}

	public void setUserNameEq(String userName) {
		this.userNameEq = userName;
	}

	private String realName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	private String realNameEq;

	public String getRealNameEq() {
		return realNameEq;
	}

	public void setRealNameEq(String realName) {
		this.realNameEq = realName;
	}

	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String genderEq;

	public String getGenderEq() {
		return genderEq;
	}

	public void setGenderEq(String gender) {
		this.genderEq = gender;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginXfb() {
		return null;
	}

	public void setLoginXfb(String s) {

	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	private String passwordEq;

	public String getPasswordEq() {
		return passwordEq;
	}

	public void setPasswordEq(String password) {
		this.passwordEq = password;
	}

	private String smallImage;

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	private String smallImageEq;

	public String getSmallImageEq() {
		return smallImageEq;
	}

	public void setSmallImageEq(String smallImage) {
		this.smallImageEq = smallImage;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String emailEq;

	public String getEmailEq() {
		return emailEq;
	}

	public void setEmailEq(String email) {
		this.emailEq = email;
	}

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String phoneEq;

	public String getPhoneEq() {
		return phoneEq;
	}

	public void setPhoneEq(String phone) {
		this.phoneEq = phone;
	}

	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String mobileEq;

	public String getMobileEq() {
		return mobileEq;
	}

	public void setMobileEq(String mobile) {
		this.mobileEq = mobile;
	}

	private String qq;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	private String qqEq;

	public String getQqEq() {
		return qqEq;
	}

	public void setQqEq(String qq) {
		this.qqEq = qq;
	}

	private String msn;

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	private String msnEq;

	public String getMsnEq() {
		return msnEq;
	}

	public void setMsnEq(String msn) {
		this.msnEq = msn;
	}

	private java.util.Date lastLoginDt;

	public java.util.Date getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(java.util.Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	private String accessTokenEq;

	public String getAccessTokenEq() {
		return accessTokenEq;
	}

	public void setAccessTokenEq(String accessToken) {
		this.accessTokenEq = accessToken;
	}

	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	private String userTypeEq;

	public String getUserTypeEq() {
		return userTypeEq;
	}

	public void setUserTypeEq(String userType) {
		this.userTypeEq = userType;
	}

	private Integer enabled;

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	private String createBy;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	private String createByEq;

	public String getCreateByEq() {
		return createByEq;
	}

	public void setCreateByEq(String createBy) {
		this.createByEq = createBy;
	}

	private java.util.Date createDt;

	public java.util.Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}

	private String modifyBy;

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	private String modifyByEq;

	public String getModifyByEq() {
		return modifyByEq;
	}

	public void setModifyByEq(String modifyBy) {
		this.modifyByEq = modifyBy;
	}

	private java.util.Date modifyDt;

	public java.util.Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(java.util.Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	private Integer orgId;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	private ArrayList role = new ArrayList();

	// relation=one-to-one
	public SysRole getRole() {
		SysRole ret = null;
		if (this.role != null && this.role.size() > 0) {
			ret = (SysRole) this.role.get(0);
		}
		return ret;
	}

	public void setRole(SysRole vObj) {
		if (vObj == null)
			return;
		if (this.role == null) {
			this.role = new ArrayList();
		} else {
			this.role.clear();
		}
		this.role.add(vObj);
	}

	private java.util.Date lastLoginDtFrom;

	public java.util.Date getLastLoginDtFrom() {
		return lastLoginDtFrom;
	}

	public void setLastLoginDtFrom(java.util.Date lastLoginDtFrom) {
		this.lastLoginDtFrom = lastLoginDtFrom;
	}

	private java.util.Date lastLoginDtTo;

	public java.util.Date getLastLoginDtTo() {
		return lastLoginDtTo;
	}

	public void setLastLoginDtTo(java.util.Date lastLoginDtTo) {
		this.lastLoginDtTo = lastLoginDtTo;
	}

	private java.util.Date createDtFrom;

	public java.util.Date getCreateDtFrom() {
		return createDtFrom;
	}

	public void setCreateDtFrom(java.util.Date createDtFrom) {
		this.createDtFrom = createDtFrom;
	}

	private java.util.Date createDtTo;

	public java.util.Date getCreateDtTo() {
		return createDtTo;
	}

	public void setCreateDtTo(java.util.Date createDtTo) {
		this.createDtTo = createDtTo;
	}

	private java.util.Date modifyDtFrom;

	public java.util.Date getModifyDtFrom() {
		return modifyDtFrom;
	}

	public void setModifyDtFrom(java.util.Date modifyDtFrom) {
		this.modifyDtFrom = modifyDtFrom;
	}

	private java.util.Date modifyDtTo;

	public java.util.Date getModifyDtTo() {
		return modifyDtTo;
	}

	public void setModifyDtTo(java.util.Date modifyDtTo) {
		this.modifyDtTo = modifyDtTo;
	}
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void fixup() {
		if (this.role != null) {
			for (int i = 0, n = role.size(); i < n; i++) {
				SysRole oneSysRole = (SysRole) role.get(i);
				oneSysRole.setRoleId(this.roleId);
				oneSysRole.fixup();
			}
		}
	}

	// be carefull of the case,key is the original name
	public HashMap toHashMap() {
		HashMap ht = new HashMap();
		if (this.userId != null)
			ht.put("userId", this.userId);
		if (this.roleId != null)
			ht.put("roleId", this.roleId);
		if (this.userName != null)
			ht.put("userName", this.userName);
		if (this.userNameEq != null)
			ht.put("userNameEq", this.userNameEq);
		if (this.realName != null)
			ht.put("realName", this.realName);
		if (this.realNameEq != null)
			ht.put("realNameEq", this.realNameEq);
		if (this.gender != null)
			ht.put("gender", this.gender);
		if (this.genderEq != null)
			ht.put("genderEq", this.genderEq);
		if (this.password != null)
			ht.put("password", this.password);
		if (this.passwordEq != null)
			ht.put("passwordEq", this.passwordEq);
		if (this.smallImage != null)
			ht.put("smallImage", this.smallImage);
		if (this.smallImageEq != null)
			ht.put("smallImageEq", this.smallImageEq);
		if (this.email != null)
			ht.put("email", this.email);
		if (this.emailEq != null)
			ht.put("emailEq", this.emailEq);
		if (this.phone != null)
			ht.put("phone", this.phone);
		if (this.phoneEq != null)
			ht.put("phoneEq", this.phoneEq);
		if (this.mobile != null)
			ht.put("mobile", this.mobile);
		if (this.mobileEq != null)
			ht.put("mobileEq", this.mobileEq);
		if (this.qq != null)
			ht.put("qq", this.qq);
		if (this.qqEq != null)
			ht.put("qqEq", this.qqEq);
		if (this.msn != null)
			ht.put("msn", this.msn);
		if (this.msnEq != null)
			ht.put("msnEq", this.msnEq);
		if (this.lastLoginDt != null)
			ht.put("lastLoginDt", this.lastLoginDt);
		if (this.lastLoginDtFrom != null)
			ht.put("lastLoginDtFrom", this.lastLoginDtFrom);
		if (this.lastLoginDtTo != null)
			ht.put("lastLoginDtTo", this.lastLoginDtTo);
		if (this.accessToken != null)
			ht.put("accessToken", this.accessToken);
		if (this.accessTokenEq != null)
			ht.put("accessTokenEq", this.accessTokenEq);
		if (this.userType != null)
			ht.put("userType", this.userType);
		if (this.userTypeEq != null)
			ht.put("userTypeEq", this.userTypeEq);
		if (this.enabled != null)
			ht.put("enabled", this.enabled);
		if (this.createBy != null)
			ht.put("createBy", this.createBy);
		if (this.createByEq != null)
			ht.put("createByEq", this.createByEq);
		if (this.createDt != null)
			ht.put("createDt", this.createDt);
		if (this.createDtFrom != null)
			ht.put("createDtFrom", this.createDtFrom);
		if (this.createDtTo != null)
			ht.put("createDtTo", this.createDtTo);
		if (this.modifyBy != null)
			ht.put("modifyBy", this.modifyBy);
		if (this.modifyByEq != null)
			ht.put("modifyByEq", this.modifyByEq);
		if (this.modifyDt != null)
			ht.put("modifyDt", this.modifyDt);
		if (this.modifyDtFrom != null)
			ht.put("modifyDtFrom", this.modifyDtFrom);
		if (this.modifyDtTo != null)
			ht.put("modifyDtTo", this.modifyDtTo);
		if (this.orgId != null)
			ht.put("orgId", this.orgId);
		if (this.groupId != null)
			ht.put("groupId", this.groupId);
		if (this.tenantId != null)
			ht.put("tenantId", this.tenantId);
		if (this.dr != null)
			ht.put("dr", this.dr);

		return ht;
	}


    private Integer dr ;


    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	private String newpassword;
	private String repass;

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public Integer getUserIdEq() {
		return userIdEq;
	}

	public void setUserIdEq(Integer userIdEq) {
		this.userIdEq = userIdEq;
	}

	private String yhtUserId;
	private String tenantId;

	public String getYhtUserId() {
		return yhtUserId;
	}

	public void setYhtUserId(String yhtUserId) {
		this.yhtUserId = yhtUserId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	private Integer[] schemes;
	private String schemeIds;

	public Integer[] getSchemes() {
		return schemes;
	}

	public String getSchemeIds() {
		return schemeIds;
	}

	public void setSchemes(Integer[] schemes) {
		this.schemes = schemes;
	}

	public void setSchemeIds(String schemeIds) {
		if (StringUtils.isNotBlank(schemeIds)){
			String[] ids = schemeIds.split(",");
			int length = (ids!=null? ids.length: 0);
			schemes = new Integer[length];
			for (int i = 0; i < length; i++) {
				schemes[i] = Integer.parseInt(ids[i]);
			}
		}
		this.schemeIds = schemeIds;
	}

	private Integer[] spIds;
	private String sps;

    public String getSps() {
        return sps;
    }

    public void setSps(String sps) {
        String newSps = "";
        if (StringUtils.isNotBlank(sps)){
            String[] ids = sps.split(",");
            int length = (ids!=null? ids.length: 0);

            if (length==0) {
                this.sps = "";
                return;
            }

            for (int i = 0; i < length; i++) {
                if(!newSps.contains(ids[i] + ",")){
                    newSps += ids[i] + ",";
                }
            }

            if(StringUtils.isNotBlank(newSps)){
                newSps = newSps.substring(0,newSps.length()-1);
            }
            ids = newSps.split(",");
            length = (ids!=null? ids.length: 0);
            spIds = new Integer[length];
            for (int i = 0; i < length; i++) {
                spIds[i] = Integer.parseInt(ids[i]);
            }
        }
        this.sps = newSps;
    }

    public Integer[] getSpIds() {
		return spIds;
	}

	public void setSpIds(Integer[] spIds) {
		this.spIds = spIds;
	}

	private String coreUserId;

	public String getCoreUserId() {
		return coreUserId;
	}

	public void setCoreUserId(String coreUserId) {
		this.coreUserId = coreUserId;
	}
}