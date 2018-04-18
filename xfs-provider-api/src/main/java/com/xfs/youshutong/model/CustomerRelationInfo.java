package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/5/17.15:34
 */

/**
 * 客户补充信息
 *
 * @author
 * @create 2017-05-17 15:34
 **/
public class CustomerRelationInfo {
    private String attrid;//	Id	Not null，补充属性id
    private String type;//	类型	Not null,补充属性类型
    private String contractNo;//：合同编号，
    private String orderNo;//:订单号
    private String accountNo;//:开户协议号  注意：attrid+ type作为联合唯一主键
    private String name;//	名称	String(<128)
    private String code;//编码	String(<128)
    private String detail;//	描述	String(<256)
    private String createdate;//创建时间	String,格式” yyyyMMddHHmmss”


    public String getAttrid() {
        return attrid;
    }

    public void setAttrid(String attrid) {
        this.attrid = attrid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
