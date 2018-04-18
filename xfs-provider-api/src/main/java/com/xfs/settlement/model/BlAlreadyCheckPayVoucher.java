package com.xfs.settlement.model;

import java.math.BigDecimal;

/**
 * Created by yuanm on 12/29/2016.
 */
public class BlAlreadyCheckPayVoucher extends BlPayVoucher {
    //todo 待核销金额
    private BigDecimal alreadyCheck;

    public BigDecimal getAlreadyCheck() {
        return alreadyCheck;
    }

    public void setAlreadyCheck(BigDecimal alreadyCheck) {
        this.alreadyCheck = alreadyCheck;
    }
    private BigDecimal oldUseBalace;
    private BigDecimal oldAlreadyCheck;

    public BigDecimal getOldUseBalace() {
        return oldUseBalace;
    }

    public void setOldUseBalace(BigDecimal oldUseBalace) {
        this.oldUseBalace = oldUseBalace;
    }

    public BigDecimal getOldAlreadyCheck() {
        return oldAlreadyCheck;
    }

    public void setOldAlreadyCheck(BigDecimal oldAlreadyCheck) {
        this.oldAlreadyCheck = oldAlreadyCheck;
    }
}
