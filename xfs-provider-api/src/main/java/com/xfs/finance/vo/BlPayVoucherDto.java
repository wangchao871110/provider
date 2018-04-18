package com.xfs.finance.vo;

import com.xfs.settlement.model.BlPayVoucher;

/**
 * Created by wangxy on 2016/12/29.
 */
public class BlPayVoucherDto extends BlPayVoucher {
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
