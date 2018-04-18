package com.xfs.sp.dto;

import com.xfs.sp.model.CmContract;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ContractDto extends CmContract{
    private String contactor;
    private String contactPsn;

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getContactPsn() {
        return contactPsn;
    }

    public void setContactPsn(String contactPsn) {
        this.contactPsn = contactPsn;
    }
}
