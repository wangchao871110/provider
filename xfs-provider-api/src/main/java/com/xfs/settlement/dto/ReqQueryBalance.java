package com.xfs.settlement.dto;

/**
 * 查询业务用户在薪福社中的余额
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-13 17:08
 */
public class ReqQueryBalance extends BasePay{

    private String owner;//帐号唯一标识
    private String ownerType;//帐号类型

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
}
