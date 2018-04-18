package com.xfs.cp.dto;

import java.util.HashMap;


/**
 * SAAS回传账单DTO
 * @author		: wangchao
 * @date		: 2016年12月26日 下午2:11:32
 * @version		: V1.0
 */
public class SaaSCpBillDto implements java.io.Serializable {
	
	/**
	 * @Fields serialVersionUID	:   
	 * @since Ver 1.0
	 */
	private static final long serialVersionUID = 721645912123328718L;
	private String billNum;//账单编号
	private Integer aSpId;//派单方服务商ID、接收账单服务商ID
	private Integer bSpId;//接单方服务商ID、出账单服务商ID
	private Double price;//价格
	private Double servicePrice;//服务费
	private Double publicPrice;//官费
	private String fileId;//账单文件ID
	private String remark;//备注说明
	private String attach;//扩展字段
	
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public Integer getASpId() {
	    return this.aSpId;
	}
	public void setASpId(Integer aSpId) {
	    this.aSpId=aSpId;
	}
	public Integer getBSpId() {
	    return this.bSpId;
	}
	public void setBSpId(Integer bSpId) {
	    this.bSpId=bSpId;
	}
	public Double getPrice() {
	    return this.price;
	}
	public void setPrice(Double price) {
	    this.price=price;
	}
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Double getPublicPrice() {
		return publicPrice;
	}
	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}
	public String getFileId() {
	    return this.fileId;
	}
	public void setFileId(String fileId) {
	    this.fileId=fileId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
    public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.aSpId!=null)
			ht.put("aSpId",this.aSpId);
		if(this.bSpId!=null)
			ht.put("bSpId",this.bSpId);
		if(this.price!=null)
			ht.put("price",this.price);
		if(this.fileId!=null)
			ht.put("fileId",this.fileId);
		if(this.remark!=null)
			ht.put("remark",this.remark);
		if(this.attach!=null)
			ht.put("attach",this.attach);
		return ht;
	}
	
}

