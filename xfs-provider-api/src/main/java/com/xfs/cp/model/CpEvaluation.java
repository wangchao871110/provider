package com.xfs.cp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CpEvaluation
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 15:26:26	
 */
public class CpEvaluation implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer spId;//服务商ID
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private Integer spIdBy;//被评价服务商
	public Integer getSpIdBy() {
	    return this.spIdBy;
	}
	public void setSpIdBy(Integer spIdBy) {
	    this.spIdBy=spIdBy;
	}
	private Integer orderId;//订单ID
	public Integer getOrderId() {
	    return this.orderId;
	}
	public void setOrderId(Integer orderId) {
	    this.orderId=orderId;
	}
	private Double cooperationStar;//合作满意度
	public Double getCooperationStar() {
	    return this.cooperationStar;
	}
	public void setCooperationStar(Double cooperationStar) {
	    this.cooperationStar=cooperationStar;
	}
	private Double professionNum;//专业度
	public Double getProfessionNum() {
	    return this.professionNum;
	}
	public void setProfessionNum(Double professionNum) {
	    this.professionNum=professionNum;
	}
	private Double attitudeNum;//服务态度
	public Double getAttitudeNum() {
	    return this.attitudeNum;
	}
	public void setAttitudeNum(Double attitudeNum) {
	    this.attitudeNum=attitudeNum;
	}
	private Double efficiencyNum;//服务效率
	public Double getEfficiencyNum() {
	    return this.efficiencyNum;
	}
	public void setEfficiencyNum(Double efficiencyNum) {
	    this.efficiencyNum=efficiencyNum;
	}
	private Double powerNum;//服务能力
	public Double getPowerNum() {
	    return this.powerNum;
	}
	public void setPowerNum(Double powerNum) {
	    this.powerNum=powerNum;
	}
	private Integer createBy;//创建人
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date createDt;//创建时间
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//创建时间
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//创建时间
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//更新人
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//更新时间
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//更新时间
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//更新时间
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//逻辑删除标记位0:未删除1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	public Integer stage;

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.spIdBy!=null)
			ht.put("spIdBy",this.spIdBy);
		if(this.orderId!=null)
			ht.put("orderId",this.orderId);
		if(this.cooperationStar!=null)
			ht.put("cooperationStar",this.cooperationStar);
		if(this.professionNum!=null)
			ht.put("professionNum",this.professionNum);
		if(this.attitudeNum!=null)
			ht.put("attitudeNum",this.attitudeNum);
		if(this.efficiencyNum!=null)
			ht.put("efficiencyNum",this.efficiencyNum);
		if(this.powerNum!=null)
			ht.put("powerNum",this.powerNum);
		if(this.createBy!=null)
			ht.put("createBy",this.createBy);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.createDtFrom!=null)
			ht.put("createDtFrom",this.createDtFrom);
		if(this.createDtTo!=null)
			ht.put("createDtTo",this.createDtTo);
		if(this.modifyBy!=null)
			ht.put("modifyBy",this.modifyBy);
		if(this.modifyDt!=null)
			ht.put("modifyDt",this.modifyDt);
		if(this.modifyDtFrom!=null)
			ht.put("modifyDtFrom",this.modifyDtFrom);
		if(this.modifyDtTo!=null)
			ht.put("modifyDtTo",this.modifyDtTo);
		if(this.dr!=null)
			ht.put("dr",this.dr);
		if(this.type!=null)
			ht.put("type",this.type);
		if(this.anonymous!=null)
			ht.put("anonymous",this.anonymous);
		if(this.content!=null)
			ht.put("content",this.content);
		if(this.stage!=null)
			ht.put("stage",this.stage);
		if(this.cporderId!=null)
			ht.put("cporderId",this.cporderId);
		return ht;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 15:26:28
	private Integer type;// 评价类型0：评价；1：投诉
	private String anonymous;// 是否匿名0：是；1：否
	private String content;// 评价内容
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	private String cporderId; //订单号

	public String getCporderId() {
		return cporderId;
	}

	public void setCporderId(String cporderId) {
		this.cporderId = cporderId;
	}
}

