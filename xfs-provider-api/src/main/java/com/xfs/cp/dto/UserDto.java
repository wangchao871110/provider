package com.xfs.cp.dto;

/**
 * 
 * @class  comments: 用户注册交互类
 * @project  name  : cooperation
 * @author   name  : wangchao 
 * @create   time  : 2016年9月2日 下午4:11:02  
 * @class    name  : com.xfs.cp.dto.UserDto
 * @modify   list  : 修改时间和内容   
 * 2016年9月2日 下午4:11:02 wangchao 创建
 *
 */
public class UserDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    // 密码
    private String password;
    // 确认密码
    private String confirmPassword;
    // 手机号
    private String mobile;
    // 验证码
    private String valiCode;
    // 服务商名称
    private String spName;
    // 短信验证码
    private String msgCode;
    
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getValiCode() {
		return valiCode;
	}
	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
    
}
