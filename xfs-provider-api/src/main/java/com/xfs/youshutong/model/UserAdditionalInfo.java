package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/16.17:57
 */

/**
 * @author
 * @create 2017-05-16 17:57
 **/
public class UserAdditionalInfo {
    public String getSysmark() {
        return sysmark;
    }

    public void setSysmark(String sysmark) {
        this.sysmark = sysmark;
    }

    private String sysmark;
    private String userid;//用户id	String (<32),Not null
    private String username;//用户名	String(<128),
    private String corporationid;//企业ID	String(<128),
    private String status;//状态	String(<128), 用户状态,默认active
    private String corporationname;//	企业名称	String(<128),
    private String realname;//	真实姓名	String(<128),
    private String identitycard;//身份证号码		String(<128),
    private String mobilephone;//手机号码	String(<12),
    private String email;//邮箱	String(<128),
    private String registerdate;//注册时间	String(15),日期格式：yyyyMMddHHmmss
    private String accountflag;//账户标识	String,用来区分客户与用户，用户（直客和个人）为”2”，客户为“1”,代理商 “3”
    private String accountfrom;//账户来源	Not null,String(<128),业务系统标识，由IDM提供
    private String validateemail;//	邮箱是否校验	String,邮箱是否校验（0/1）
    private String validatephone;//	电话是否校验	String,电话是否校验（0/1）
    private String validateidcard;//是否验证身份证号	String,1是；0否
    private String payuser;//是否付费	String,1 是；0 否
    private String sortkey;//	用户密码加密密钥	String(<128),Not null
    private String userpsw;//用户密码	String(<128),Not null 明文
    private String clientid;//友户通分配给业务系统的sysid	String(<128),Not null 用户授权对象
    private String clientsecret;//	友户通分配给业务系统的appkey	String(<128),Not null 用户授权对象密码
    private String terminalType;//	终端类型	String( 1. PC, 2. IOS,3. Android, 4.WP,0.未知移动设备) 默认1
    private String paytime;//付费时间	String(formate=“yyyyMMddHHmmss”)
    private String payendtime;//	付费截至时间	String(formate= “yyyyMMddHHmmss”)
    private Long version;//	版本号（时间戳）	Long,使用date.gettime获取，默认当前时间的毫秒数
    private Integer additionPaid;//	是否付费用户(附加表)	Integer, 1为付费, 0为未付费
    private Integer additionStatus;//用户状态(附加表)	Integer, 1为正式, 0为无效, 2为试用
    private String region;//地区编码(附加表)	String
    private String industry;//行业编码(附加表)	String

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorporationid() {
        return corporationid;
    }

    public void setCorporationid(String corporationid) {
        this.corporationid = corporationid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCorporationname() {
        return corporationname;
    }

    public void setCorporationname(String corporationname) {
        this.corporationname = corporationname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getAccountflag() {
        return accountflag;
    }

    public void setAccountflag(String accountflag) {
        this.accountflag = accountflag;
    }

    public String getAccountfrom() {
        return accountfrom;
    }

    public void setAccountfrom(String accountfrom) {
        this.accountfrom = accountfrom;
    }

    public String getValidateemail() {
        return validateemail;
    }

    public void setValidateemail(String validateemail) {
        this.validateemail = validateemail;
    }

    public String getValidatephone() {
        return validatephone;
    }

    public void setValidatephone(String validatephone) {
        this.validatephone = validatephone;
    }

    public String getValidateidcard() {
        return validateidcard;
    }

    public void setValidateidcard(String validateidcard) {
        this.validateidcard = validateidcard;
    }

    public String getPayuser() {
        return payuser;
    }

    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    public String getSortkey() {
        return sortkey;
    }

    public void setSortkey(String sortkey) {
        this.sortkey = sortkey;
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPayendtime() {
        return payendtime;
    }

    public void setPayendtime(String payendtime) {
        this.payendtime = payendtime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getAdditionPaid() {
        return additionPaid;
    }

    public void setAdditionPaid(Integer additionPaid) {
        this.additionPaid = additionPaid;
    }

    public Integer getAdditionStatus() {
        return additionStatus;
    }

    public void setAdditionStatus(Integer additionStatus) {
        this.additionStatus = additionStatus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
