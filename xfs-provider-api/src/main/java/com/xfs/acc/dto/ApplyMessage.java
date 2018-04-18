package com.xfs.acc.dto;

import java.util.Date;
import java.util.Map;

/**
 * 保存方案请求实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:22:31
 * @version 	: V1.0
 */
public class ApplyMessage{

	private String loginState;//登录状态  LOGIN_NO  LOGIN_FAIL  LOGIN_SUCCESS
	private Integer areaId;// 城市ID
	private Integer schemeId;// 方案ID
	private Integer accId;//当前库ID
	private String name;//帐号名称
	private String areaName;//城市
	private String insuranceFundType;//险种类型
	private String ukeyType;// 申报类型 THREE：三证合一、INSURANCE：社保、FUND：公积金、TAX：个税、NO：无
	private String regNum;//  登记证号
	private String regNumpass;// 登记口令
	private String regUsbkeypass;// 登记证证书密码
	private String usbCode;
	private String corpName;//uk 对应企业名称
	private Date userSignedDate;//到期时间
	private String contaninerName;//uk唯一标识
	private String autoLogin;
	private String fundURL;// 公积金Ukey证书地址
	private String insuranceURL;// 社保Ukey证书地址
	private String remark;//备注
	private String errorMsg;//错误信息
	private Map<String,String> loginParam;//登录所需参数


	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public String getUkeyType() {
		return ukeyType;
	}
	public void setUkeyType(String ukeyType) {
		this.ukeyType = ukeyType;
	}

	public String getInsuranceFundType() {
		return insuranceFundType;
	}

	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getRegNumpass() {
		return regNumpass;
	}

	public void setRegNumpass(String regNumpass) {
		this.regNumpass = regNumpass;
	}

	public String getRegUsbkeypass() {
		return regUsbkeypass;
	}

	public void setRegUsbkeypass(String regUsbkeypass) {
		this.regUsbkeypass = regUsbkeypass;
	}

	public String getUsbCode() {
		return usbCode;
	}

	public void setUsbCode(String usbCode) {
		this.usbCode = usbCode;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public Date getUserSignedDate() {
		return userSignedDate;
	}

	public void setUserSignedDate(Date userSignedDate) {
		this.userSignedDate = userSignedDate;
	}

	public String getContaninerName() {
		return contaninerName;
	}

	public void setContaninerName(String contaninerName) {
		this.contaninerName = contaninerName;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getFundURL() {
		return fundURL;
	}

	public void setFundURL(String fundURL) {
		this.fundURL = fundURL;
	}

	public String getInsuranceURL() {
		return insuranceURL;
	}

	public void setInsuranceURL(String insuranceURL) {
		this.insuranceURL = insuranceURL;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public Map<String, String> getLoginParam() {
		return loginParam;
	}

	public void setLoginParam(Map<String, String> loginParam) {
		this.loginParam = loginParam;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
