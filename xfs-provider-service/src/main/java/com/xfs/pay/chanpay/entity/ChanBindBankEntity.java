package com.xfs.pay.chanpay.entity;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ChanBindBankEntity extends BaseChanPayEntity {

    private String identity_no;//会员标识号 	商户号
    private String card_attribute;//	对公：B；对私：C;只支持绑定对公或者对私的借记卡
    private String bank_code;// 银行编号
    private String bank_name;//银行名称
    private String bank_branch;//支行名称
    private String branch_no;//银行份支行号
    private String bank_account_no;//银行卡号 密文，使用RSA 加密
    private String account_name;//户名 密文，使用RSA 加密。 对公卡，户名应与公司名称一对待。对私卡，应与法人姓名一致。
    private String id_card_no;//身份证号 密文，使用RSA 加密 对私卡，企业法人身份证号。 对公卡，可空
    private String license_no;//企业营业执照号 密文，使用RSA 加密。对公卡，企业营业执照号。对私卡，可空
    private String province;//省份
    private String city;//城市

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public String getCard_attribute() {
        return card_attribute;
    }

    public void setCard_attribute(String card_attribute) {
        this.card_attribute = card_attribute;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
