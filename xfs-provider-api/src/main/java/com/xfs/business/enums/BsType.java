package com.xfs.business.enums;

/**
 * 业务类型
 *
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-01-17 15:27
 */
public enum BsType {

    MODIFY_PHONE(1, "手机号修改", "INSURANCE"),
    NEW_INSURANCE(2, "新参", "INSURANCE"),
    INTO_INSURANCE(3, "转入", "INSURANCE"),
    REDUCE_INSURANCE(4, "减员", "INSURANCE"),
    SEARCH_PERSON_INSURANCE(5, "查询个人报表业务", "INSURANCE"),
    SEARCH_CORP_INSURANCE(6, "查询企业报表业务", "INSURANCE"),
    MODIFY_HOSPITAL(7, "定点医院修改", "INSURANCE"),
    ADD_INSURANCE(10, "增加", "FUND"),
    QUIT_INSURANCE(11, "减少", "FUND"),
    HIRE_INSURANCE(12, "招工", "INSURANCE"),
    UNSEALED_INSURANCE(15, "启封", "FUND"),
    SEALED_INSURANCE(16, "封存", "FUND"),
    INSIDE_INSURANCE(17, "内部转移", "FUND"),
    SIGLE_NEW_INSURANCE(22, "单个新参", "INSURANCE"),
    SIGLE_INTO_INSURANCE(23, "单个转入", "INSURANCE"),
    SIGLE_REDUCE_INSURANCE(24, "单个减员", "INSURANCE"),
    PAY_PAYBASE(25, "缴费基数调整", "INSURANCE"),
    INTO_INNER(26, "转入-内部转入", "FUND"),
    INTO_SEALED(27, "转入-封存部转入", "FUND"),
    INTO_UNSEALED(28, "转入启封", "FUND"),
    INSUR_BACK(29, "社保补缴", "INSURANCE"),
    FUND_BACK(30, "公积金补缴", "FUND"),
    INSUR_PAYBASE(31, "社保基数调整", "INSURANCE"),
    FUND_PAYBASE(32, "公积金基数调整", "FUND"),
    RATIO_INSUR(33, "社保调整比例", "INSURANCE"),
    RATIO_FUND(34, "公积金调整比例", "FUND"),
    LOWRE_INSUR_PAYBASE(35, "社保最低基数调整", "INSURANCE"),
    LOWER_FUND_PAYBASE(36, "公积金最低基数调整", "FUND"),
    GRSSBG_INSUR(37, "个人社保变更", "INSURANCE"),
    GRXXBG_INSUR(38, "社保个人信息变更", "INSURANCE"),
    GRXXBG_FUND(39, "公积金个人信息变更", "FUND"),
    INJURY_DECLARATION(42, "工伤申报", "INSURANCE"),
    INJURYD_CHECKUP(43, "工伤鉴定", "INSURANCE"),
    BIRTH_REIMBURSEMENT(44, "生育报销", "INSURANCE"),
    MEDICA_REIMBURSEMENT(45, "医疗报销", "INSURANCE"),
    FUND_EXTRACT(46, "公积金提取", "FUND"),
    FUND_TRANSFER(47, "公积金转移", "FUND"),
    FUND_LIQUIDATION(48, "公积金清算", "FUND"),
    INSUR_TRANSFER(48, "社保转移", "INSURANCE"),
    INSUR_CARD_APPLICATION(50, "社保卡申领", "INSURANCE"),
    NEW_HOUSE_CHAMBER(51, "新房支取", "FUND"),
    SECOND_HOUSE_CHAMBER(52, "二手房支取", "FUND"),
    NO_HOUSE_CHAMBER(53, "无房支取", "FUND"),
    HOSPITALIZATION_REIMBURSEMENT(59, "住院报销", "INSURANCE"),
    OUTPATIENT_REIMBURSEMENT(60, "门诊报销", "INSURANCE"),
    INCREMENT_INTERPROVINCIAL_TURN_IN(81, "增值跨省转入", "INSURANCE"),
    INCREMENT_INTERPROVINCIAL_TURN_OUT(82, "增值跨省转出", "INSURANCE"),
    INCREMENT_REMOTE_MEDICAL_TREATMENT(83, "增值异地就医", "INSURANCE"),
    INCREMENT_INSUR_PAY(84, "增值个人社保代缴", "INSURANCE"),
    INCREMENT_INSUR_BACK(85, "增值个人社保补缴", "INSURANCE"),
    INCREMENT_INSUR_ENTRY(-99, "入职参保", "INSURANCE");


    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String insurance_fund_type;

    public String getInsurance_fund_type() {
        return insurance_fund_type;
    }

    public void setInsurance_fund_type(String insurance_fund_type) {
        this.insurance_fund_type = insurance_fund_type;
    }

    private BsType(Integer _code, String _name, String _insurance_fund_type) {
        this.code = _code;
        this.name = _name;
        this.insurance_fund_type = _insurance_fund_type;
    }

    // 普通方法
    public static Integer getCodeByName(String name) {
        for (BsType c : BsType.values()) {
            if (c.getName().equals(name)) {
                return c.code;
            }
        }
        return null;
    }

    public Integer code() {
        return this.code;
    }


    public static String getInsType(Integer bsTypeId){
        for (BsType c : BsType.values()) {
            if (c.getCode().equals(bsTypeId)) {
                return c.getInsurance_fund_type();
            }
        }
        return null;
    }


    public static String getNameByCode(Integer bsTypeId){
        for (BsType c : BsType.values()) {
            if (c.getCode().equals(bsTypeId)) {
                return c.getName();
            }
        }
        return null;
    }

}
