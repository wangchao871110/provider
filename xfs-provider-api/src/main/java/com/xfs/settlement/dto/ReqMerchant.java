package com.xfs.settlement.dto;

/**
 * 开通畅捷通子商户请求
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-19 16:06
 */
public class ReqMerchant extends BasePay{

    private Integer owner;//
    public Integer getOwner() {
        return this.owner;
    }
    public void setOwner(Integer owner) {
        this.owner=owner;
    }
    private String ownerType;//SERVICE:服务商              XFS:薪福社            CORP:企业
    public String getOwnerType() {
        return this.ownerType;
    }
    public void setOwnerType(String ownerType) {
        this.ownerType=ownerType;
    }
    private String uid;//账号唯一标识
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid=uid;
    }
    private String memberId;//
    public String getMemberId() {
        return this.memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId=memberId;
    }
    private String partnerId;//
    public String getPartnerId() {
        return this.partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId=partnerId;
    }
    private String login_name;//登录名

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    private String company_nature;//企业性质

    private String enterprise_name;//公司名称
    private String legal_person;//法人姓名
    private String id_card_no;//身份证号

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    private String registered_capital;//注册资本

    public String getRegistered_capital() {
        return registered_capital;
    }

    public void setRegistered_capital(String registered_capital) {
        this.registered_capital = registered_capital;
    }

    private String address;//联系地址
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    private String counterman;//业务联系人姓名
    public String getCounterman() {
        return this.counterman;
    }
    public void setCounterman(String counterman) {
        this.counterman=counterman;
    }
    private String telephone;//
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone=telephone;
    }
    private String email;//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String ext;//
    public String getExt() {
        return this.ext;
    }
    public void setExt(String ext) {
        this.ext=ext;
    }

    public String payType;//支付方式

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public  Integer busId;//

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }
}
