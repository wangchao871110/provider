package com.xfs.bill.dto;

import com.xfs.bill.model.SpsBill;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-06 10:41
 */
public class SpsBillDto extends SpsBill {

    private String billName;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
}
