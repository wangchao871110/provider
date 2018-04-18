package com.xfs.cp.dto;

/**
 * 公司信息注册交互类
 * 
 * @author wangdx
 *
 */
public class CorpInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String confirmPassword;

    /**
     * 企业名称
     */
    private String corpName;

    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String valiCode;

    /**
     * 公司详细地址
     */
    private String cpAddress;

    /**
     * 公司地址编号
     */
    private Integer cpAddressArea;
    
    /**wangchao 添加 sta 2016-07-29**/
    private Integer id;
    /**
	 * 职位 A、HR助理   B、HR专员   C、HR主管   D、HR经理   E、HR总监  G，其他
	 */
	private String position;
	/**
	 * 注册历史ID 为了更新答题问卷时间
	 */
	private Integer registHistoryId;
	
	/**
	 * 统一社会信用代码
	 */
	private String busiLicenseNum;
    /**wangchao 添加 end 2016-07-29***/
	
	private String customerNo;//
	public String getCustomerNo() {
		return this.customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo=customerNo;
	}
	private String channelCode;//
	public String getChannelCode() {
		return this.channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode=channelCode;
	}

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getConfirmPassword() {

        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;
    }

    public String getCorpName() {

        return corpName;
    }

    public void setCorpName(String corpName) {

        this.corpName = corpName;
    }

    public String getContactsName() {

        return contactsName;
    }

    public void setContactsName(String contactsName) {

        this.contactsName = contactsName;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getValiCode() {
        return valiCode;
    }

    public void setValiCode(String valiCode) {
        this.valiCode = valiCode;
    }

    public String getCpAddress() {
        return cpAddress;
    }

    public void setCpAddress(String cpAddress) {
        this.cpAddress = cpAddress;
    }

    public Integer getCpAddressArea() {
        return cpAddressArea;
    }

    public void setCpAddressArea(Integer cpAddressArea) {
        this.cpAddressArea = cpAddressArea;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getRegistHistoryId() {
		return registHistoryId;
	}

	public void setRegistHistoryId(Integer registHistoryId) {
		this.registHistoryId = registHistoryId;
	}

	public String getBusiLicenseNum() {
		return busiLicenseNum;
	}

	public void setBusiLicenseNum(String busiLicenseNum) {
		this.busiLicenseNum = busiLicenseNum;
	}
    
}
