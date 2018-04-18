package com.xfs.corp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * 员工端配置实体
 * @author 	: wangchao
 * @date 	: 2017年6月7日 上午9:57:36
 * @version 	: V1.0
 */
public class CmEmployeeConfig implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer cpId;//服务商ID	public Integer getCpId() {	    return this.cpId;	}	public void setCpId(Integer cpId) {	    this.cpId=cpId;	}	private Integer areaId;//城市ID	public Integer getAreaId() {	    return this.areaId;	}	public void setAreaId(Integer areaId) {	    this.areaId=areaId;	}
	private Integer isEmp;//是否开通员工端 0：未开通，1：开通	public Integer getIsEmp() {	    return this.isEmp;	}	public void setIsEmp(Integer isEmp) {	    this.isEmp=isEmp;	}	private Integer isInsFund;//是否查询社保和公积金 0：未开通，1：开通	public Integer getIsInsFund() {	    return this.isInsFund;	}	public void setIsInsFund(Integer isInsFund) {	    this.isInsFund=isInsFund;	}
	private Integer isOnLineService;//是否开通线上申请 0：未开通，1：开通
	
	public Integer getIsOnLineService() {
		return isOnLineService;
	}
	public void setIsOnLineService(Integer isOnLineService) {
		this.isOnLineService = isOnLineService;
	}
	private String onLineService;//已经开通线上申请服务，多个服务已,号隔开
	public String getOnLineService() {
		return onLineService;
	}
	public void setOnLineService(String onLineService) {
		this.onLineService = onLineService;
	}
	private Integer isEmpService;//是否开通社保服务指南 0：未开通，1：开通	public Integer getIsEmpService() {	    return this.isEmpService;	}	public void setIsEmpService(Integer isEmpService) {	    this.isEmpService=isEmpService;	}	private String empService;//已经开通社保服务指南，多个服务已,号隔开	public String getEmpService() {	    return this.empService;	}	public void setEmpService(String empService) {	    this.empService=empService;	}	private Integer isAddService;//是否开通增值服务 0：未开通，1：开通	public Integer getIsAddService() {	    return this.isAddService;	}	public void setIsAddService(Integer isAddService) {	    this.isAddService=isAddService;	}	private String addService;//已经开通增值服务，多个服务已,号隔开	public String getAddService() {	    return this.addService;	}	public void setAddService(String addService) {	    this.addService=addService;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.cpId!=null)			ht.put("cpId",this.cpId);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.isEmp!=null)			ht.put("isEmp",this.isEmp);		if(this.isInsFund!=null)			ht.put("isInsFund",this.isInsFund);		if(this.isEmpService!=null)			ht.put("isEmpService",this.isEmpService);		if(this.empService!=null)			ht.put("empService",this.empService);		if(this.isAddService!=null)			ht.put("isAddService",this.isAddService);		if(this.addService!=null)			ht.put("addService",this.addService);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2017/06/07 09:54:56		
}

