package com.xfs.corp.enums;

/**
 * Created by quanj on 2017/1/17.
 */
public enum InsuranceEnums {
    PENSION(1,"Pension"),
    MEDICINE(2,"Medicine"),
    INJURY(3,"Injury"),
    UNEMPLOYMENT(4,"Unemployment"),
    MATERNITY(5,"Maternity");

    private int insuranceId;
    private String code;

    InsuranceEnums(int insuranceId, String code){
        this.insuranceId = insuranceId;
        this.code = code;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public String getCode() {
        return code;
    }
}
