package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.15:35
 */

import java.util.List;

/**
 * 添加客户信息接口参数实体类
 *
 * @author
 * @create 2017-05-17 15:35
 **/
public class CustomeAddInfo {
    private String Accountfrom;//	账户来源	String，not null业务系统标识，由IDM提供
    private String Clientid;//	友户通分配给业务系统的sysid	not null用户授权对象
    private String Clientsecret;//友户通分配给业务系统的appkey	not null用户授权对象密码
    private String Terminaltype;//	终端类型	not null( 1. PC, 2. IOS,3. Android, 4.WP,0.未知移动设备)不传默认1
    private String Corporationid;//	企业id	String(<128)not null,企业唯一标识
    private String Corporationname;//	企业名称	String（<128）
    private String createdate;//注册时间	not null,String（<128）,格式:yyyyMMddHHmmss
    private String Businesslicenseno;//营业执照号	String（<128）
    private String Organizationno;//	组织机构代码证号	String（<128）
    private String Taxregistryno;//税务登记证号	String（<128）
    private String Unifiedsocialcreditcode;//	统一社会信用代码	String（<128）
    private String Scale;// 企业规模	String（<128）
    private String Location;//	所处省市	String（<128）省，市
    private String Addr;//	通信地址	String（<128）详细地址
    private String Postcode;//	邮编	String（<128）
    private String Usernumber;//	用户数量	String（<128）
    private String Validate;//是否验证	String（<128）
    private String Sysmark;//	客户来源	String（<128）, 业务系统标识，由IDM提供
    private String Customertype;//	客户类型	String(<128),客户类型，例：供应商
    private String attribute1;//是否支付客户	String（<128） 1为付费，0为非付费
    private String attribute2;//扩展属性2	String（<128）
    private String attribute3;//扩展属性3	String（<128） 客户地区
    private String attribute4;//扩展属性4	String（<128） 客户行业
    private String attribute5;//扩展属性5	String（<128）
    private List<CusUserRelation> Userrelations;//关联用户关系	List(size<1000),size>1000请批次处理，填充对象CusUserRelation
    private List<CusContact> Contacts;//联系人	List(size<1000)填充对象 CusContact
    private List<CusIndustry> Industrys;//行业信息	List(size<1000) 填充对象CusIndustry
    private List<CustomerRelationInfo> Customerrelinfos;//补充属性	List(size<1000) 填充对象 CustomerRelationInfo

    public String getAccountfrom() {
        return Accountfrom;
    }

    public void setAccountfrom(String accountfrom) {
        Accountfrom = accountfrom;
    }

    public String getClientid() {
        return Clientid;
    }

    public void setClientid(String clientid) {
        Clientid = clientid;
    }

    public String getClientsecret() {
        return Clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        Clientsecret = clientsecret;
    }

    public String getTerminaltype() {
        return Terminaltype;
    }

    public void setTerminaltype(String terminaltype) {
        Terminaltype = terminaltype;
    }

    public String getCorporationid() {
        return Corporationid;
    }

    public void setCorporationid(String corporationid) {
        Corporationid = corporationid;
    }

    public String getCorporationname() {
        return Corporationname;
    }

    public void setCorporationname(String corporationname) {
        Corporationname = corporationname;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getBusinesslicenseno() {
        return Businesslicenseno;
    }

    public void setBusinesslicenseno(String businesslicenseno) {
        Businesslicenseno = businesslicenseno;
    }

    public String getOrganizationno() {
        return Organizationno;
    }

    public void setOrganizationno(String organizationno) {
        Organizationno = organizationno;
    }

    public String getTaxregistryno() {
        return Taxregistryno;
    }

    public void setTaxregistryno(String taxregistryno) {
        Taxregistryno = taxregistryno;
    }

    public String getUnifiedsocialcreditcode() {
        return Unifiedsocialcreditcode;
    }

    public void setUnifiedsocialcreditcode(String unifiedsocialcreditcode) {
        Unifiedsocialcreditcode = unifiedsocialcreditcode;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getUsernumber() {
        return Usernumber;
    }

    public void setUsernumber(String usernumber) {
        Usernumber = usernumber;
    }

    public String getValidate() {
        return Validate;
    }

    public void setValidate(String validate) {
        Validate = validate;
    }

    public String getSysmark() {
        return Sysmark;
    }

    public void setSysmark(String sysmark) {
        Sysmark = sysmark;
    }

    public String getCustomertype() {
        return Customertype;
    }

    public void setCustomertype(String customertype) {
        Customertype = customertype;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public List<CusUserRelation> getUserrelations() {
        return Userrelations;
    }

    public void setUserrelations(List<CusUserRelation> userrelations) {
        Userrelations = userrelations;
    }

    public List<CusContact> getContacts() {
        return Contacts;
    }

    public void setContacts(List<CusContact> contacts) {
        Contacts = contacts;
    }

    public List<CusIndustry> getIndustrys() {
        return Industrys;
    }

    public void setIndustrys(List<CusIndustry> industrys) {
        Industrys = industrys;
    }

    public List<CustomerRelationInfo> getCustomerrelinfos() {
        return Customerrelinfos;
    }

    public void setCustomerrelinfos(List<CustomerRelationInfo> customerrelinfos) {
        Customerrelinfos = customerrelinfos;
    }
}
