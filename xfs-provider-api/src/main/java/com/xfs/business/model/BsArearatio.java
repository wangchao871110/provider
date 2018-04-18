package com.xfs.business.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * BsArearatio
 * @author:wuzhe
 * @date:2016/09/21 20:03:17	
 */
public class BsArearatio implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ratioId;//
	public Integer getRatioId() {
	    return this.ratioId;
	}
	public void setRatioId(Integer ratioId) {
	    this.ratioId=ratioId;
	}
	private Integer areaId;//
	public Integer getAreaId() {
	    return this.areaId;
	}
	public void setAreaId(Integer areaId) {
	    this.areaId=areaId;
	}
	private Integer insuranceId;//险种
	public Integer getInsuranceId() {
	    return this.insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
	    this.insuranceId=insuranceId;
	}
	private String name;//
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	private String nameEq;//
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	private Integer createBy;//
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date createDt;//
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//逻辑删除标记位                        0:未删除            1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private String corpCalcMethod;//enum:            四舍五入,ROUND;            向上进位,CEIL;            向下舍位,FLOOR;//           四舍五入,ROUND
public static final String corpCalcMethod_ROUND ="ROUND";
//            向上进位,CEIL
public static final String corpCalcMethod_CEIL ="CEIL";
//            向下舍位,FLOOR
public static final String corpCalcMethod_FLOOR ="FLOOR";

	public String getCorpCalcMethod() {
	    return this.corpCalcMethod;
	}
	public void setCorpCalcMethod(String corpCalcMethod) {
	    this.corpCalcMethod=corpCalcMethod;
	}
	private String corpCalcMethodEq;//enum:            四舍五入,ROUND;            向上进位,CEIL;            向下舍位,FLOOR;
	public String getCorpCalcMethodEq() {
	    return this.corpCalcMethodEq;
	}
	public void setCorpCalcMethodEq(String corpCalcMethodEq) {
	    this.corpCalcMethodEq=corpCalcMethodEq;
	}
	private Integer corpPrecision;//
	public Integer getCorpPrecision() {
	    return this.corpPrecision;
	}
	public void setCorpPrecision(Integer corpPrecision) {
	    this.corpPrecision=corpPrecision;
	}
	private String psnCalcMethod;//enum:            四舍五入,ROUND;            向上进位,CEIL;            向下舍位,FLOOR;//           四舍五入,ROUND
public static final String psnCalcMethod_ROUND ="ROUND";
//            向上进位,CEIL
public static final String psnCalcMethod_CEIL ="CEIL";
//            向下舍位,FLOOR
public static final String psnCalcMethod_FLOOR ="FLOOR";

	public String getPsnCalcMethod() {
	    return this.psnCalcMethod;
	}
	public void setPsnCalcMethod(String psnCalcMethod) {
	    this.psnCalcMethod=psnCalcMethod;
	}
	private String psnCalcMethodEq;//enum:            四舍五入,ROUND;            向上进位,CEIL;            向下舍位,FLOOR;
	public String getPsnCalcMethodEq() {
	    return this.psnCalcMethodEq;
	}
	public void setPsnCalcMethodEq(String psnCalcMethodEq) {
	    this.psnCalcMethodEq=psnCalcMethodEq;
	}
	private Integer psnPrecision;//
	public Integer getPsnPrecision() {
	    return this.psnPrecision;
	}
	public void setPsnPrecision(Integer psnPrecision) {
	    this.psnPrecision=psnPrecision;
	}
	private String billingCycle;//enum:            月缴,MONTH;            年缴不足一年按月,MONTH_ANNUALY            年缴不足一年按年,YEAR_ANNUALY//           月缴,MONTH
public static final String billingCycle_MONTH ="MONTH";
public static final String billingCycle_MONTH_ANNUALY="MONTH_ANNUALY";
public static final String billingCycle_YEAR_ANNUALY="YEAR_ANNUALY";
	public String getBillingCycle() {
	    return this.billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
	    this.billingCycle=billingCycle;
	}
	private String billingCycleEq;//
	public String getBillingCycleEq() {
	    return this.billingCycleEq;
	}
	public void setBillingCycleEq(String billingCycleEq) {
	    this.billingCycleEq=billingCycleEq;
	}
	private Integer payPeriod;//
	public Integer getPayPeriod() {
	    return this.payPeriod;
	}
	public void setPayPeriod(Integer payPeriod) {
	    this.payPeriod=payPeriod;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.ratioId!=null)
			ht.put("ratioId",this.ratioId);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.insuranceId!=null)
			ht.put("insuranceId",this.insuranceId);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
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
		if(this.corpCalcMethod!=null)
			ht.put("corpCalcMethod",this.corpCalcMethod);
		if(this.corpCalcMethodEq!=null)
			ht.put("corpCalcMethodEq",this.corpCalcMethodEq);
		if(this.corpPrecision!=null)
			ht.put("corpPrecision",this.corpPrecision);
		if(this.psnCalcMethod!=null)
			ht.put("psnCalcMethod",this.psnCalcMethod);
		if(this.psnCalcMethodEq!=null)
			ht.put("psnCalcMethodEq",this.psnCalcMethodEq);
		if(this.psnPrecision!=null)
			ht.put("psnPrecision",this.psnPrecision);
		if(this.billingCycle!=null)
			ht.put("billingCycle",this.billingCycle);
		if(this.billingCycleEq!=null)
			ht.put("billingCycleEq",this.billingCycleEq);
		if(this.payPeriod!=null)
			ht.put("payPeriod",this.payPeriod);
		return ht; 
	}
	

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:03:17
	private List<BsArearatiover> bsArearatiovers = null;
	public List<BsArearatiover> getBsArearatiovers() {
		if (null == bsArearatiovers)
			bsArearatiovers = new ArrayList<BsArearatiover>();
		return bsArearatiovers;
	}
	public void setBsArearatiovers(List<BsArearatiover> bsArearatiovers) {
		this.bsArearatiovers = bsArearatiovers;
	}
	
}

