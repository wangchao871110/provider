package com.xfs.corp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.xfs.business.model.BsRatioCalcInterface;


/**
 * CmEmployeeInsurance
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/24 09:21:05	
 */
public class CmEmployeeInsurance implements java.io.Serializable, BsRatioCalcInterface {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer empId;//
	public Integer getEmpId() {
	    return this.empId;
	}
	public void setEmpId(Integer empId) {
	    this.empId=empId;
	}
	private Integer insuranceId;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getInsuranceId()
	 */
	@Override
	public Integer getInsuranceId() {
	    return this.insuranceId;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setInsuranceId(java.lang.Integer)
	 */
	@Override
	public void setInsuranceId(Integer insuranceId) {
	    this.insuranceId=insuranceId;
	}
	private BigDecimal empPayment;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getEmpPayment()
	 */
	@Override
	public BigDecimal getEmpPayment() {
	    return this.empPayment;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setEmpPayment(java.math.BigDecimal)
	 */
	@Override
	public void setEmpPayment(BigDecimal empPayment) {
	    this.empPayment=empPayment;
	}
	private BigDecimal corpPayment;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getCorpPayment()
	 */
	@Override
	public BigDecimal getCorpPayment() {
	    return this.corpPayment;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setCorpPayment(java.math.BigDecimal)
	 */
	@Override
	public void setCorpPayment(BigDecimal corpPayment) {
	    this.corpPayment=corpPayment;
	}
	private BigDecimal empRatio;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getEmpRatio()
	 */
	@Override
	public BigDecimal getEmpRatio() {
	    return this.empRatio;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setEmpRatio(java.math.BigDecimal)
	 */
	@Override
	public void setEmpRatio(BigDecimal empRatio) {
	    this.empRatio=empRatio;
	}
	private BigDecimal corpRatio;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getCorpRatio()
	 */
	@Override
	public BigDecimal getCorpRatio() {
	    return this.corpRatio;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setCorpRatio(java.math.BigDecimal)
	 */
	@Override
	public void setCorpRatio(BigDecimal corpRatio) {
	    this.corpRatio=corpRatio;
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
	private Integer dr;//
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private String beginPeriod;//
	public String getBeginPeriod() {
	    return this.beginPeriod;
	}
	public void setBeginPeriod(String beginPeriod) {
		if (beginPeriod != null && beginPeriod.length() != 7)
			throw new RuntimeException("期间必须为7位");
	    this.beginPeriod=beginPeriod;
	}
	private String beginPeriodEq;//
	public String getBeginPeriodEq() {
	    return this.beginPeriodEq;
	}
	public void setBeginPeriodEq(String beginPeriodEq) {
	    this.beginPeriodEq=beginPeriodEq;
	}
	private String endPeriod;//
	public String getEndPeriod() {
	    return this.endPeriod;
	}
	public void setEndPeriod(String endPeriod) {
	    this.endPeriod=endPeriod;
	}
	private String endPeriodEq;//
	public String getEndPeriodEq() {
	    return this.endPeriodEq;
	}
	public void setEndPeriodEq(String endPeriodEq) {
	    this.endPeriodEq=endPeriodEq;
	}
	private Integer beginTask;//
	public Integer getBeginTask() {
	    return this.beginTask;
	}
	public void setBeginTask(Integer beginTask) {
	    this.beginTask=beginTask;
	}
	private Integer endTask;//
	public Integer getEndTask() {
	    return this.endTask;
	}
	public void setEndTask(Integer endTask) {
	    this.endTask=endTask;
	}
	private Integer ratioId;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getRatioId()
	 */
	@Override
	public Integer getRatioId() {
	    return this.ratioId;
	}
	public void setRatioId(Integer ratioId) {
	    this.ratioId=ratioId;
	}
	private BigDecimal empPaybase;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getEmpPaybase()
	 */
	@Override
	public BigDecimal getEmpPaybase() {
	    return this.empPaybase;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setEmpPaybase(java.math.BigDecimal)
	 */
	@Override
	public void setEmpPaybase(BigDecimal empPaybase) {
	    this.empPaybase=empPaybase;
	}
	private BigDecimal corpPaybase;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getCorpPaybase()
	 */
	@Override
	public BigDecimal getCorpPaybase() {
	    return this.corpPaybase;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setCorpPaybase(java.math.BigDecimal)
	 */
	@Override
	public void setCorpPaybase(BigDecimal corpPaybase) {
	    this.corpPaybase=corpPaybase;
	}
	private BigDecimal corpAddition;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getCorpAddition()
	 */
	@Override
	public BigDecimal getCorpAddition() {
	    return this.corpAddition;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setCorpAddition(java.math.BigDecimal)
	 */
	@Override
	public void setCorpAddition(BigDecimal corpAddition) {
	    this.corpAddition=corpAddition;
	}
	private BigDecimal psnAddition;//
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#getPsnAddition()
	 */
	@Override
	public BigDecimal getPsnAddition() {
	    return this.psnAddition;
	}
	/* (non-Javadoc)
	 * @see com.xfs.common.model.BsRatioCalcInterface#setPsnAddition(java.math.BigDecimal)
	 */
	@Override
	public void setPsnAddition(BigDecimal psnAddition) {
	    this.psnAddition=psnAddition;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.empId!=null)
			ht.put("empId",this.empId);
		if(this.insuranceId!=null)
			ht.put("insuranceId",this.insuranceId);
		if(this.empPayment!=null)
			ht.put("empPayment",this.empPayment);
		if(this.corpPayment!=null)
			ht.put("corpPayment",this.corpPayment);
		if(this.empRatio!=null)
			ht.put("empRatio",this.empRatio);
		if(this.corpRatio!=null)
			ht.put("corpRatio",this.corpRatio);
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
		if(this.beginPeriod!=null)
			ht.put("beginPeriod",this.beginPeriod);
		if(this.beginPeriodEq!=null)
			ht.put("beginPeriodEq",this.beginPeriodEq);
		if(this.endPeriod!=null)
			ht.put("endPeriod",this.endPeriod);
		if(this.endPeriodEq!=null)
			ht.put("endPeriodEq",this.endPeriodEq);
		if(this.beginTask!=null)
			ht.put("beginTask",this.beginTask);
		if(this.endTask!=null)
			ht.put("endTask",this.endTask);
		if(this.ratioId!=null)
			ht.put("ratioId",this.ratioId);
		if(this.empPaybase!=null)
			ht.put("empPaybase",this.empPaybase);
		if(this.corpPaybase!=null)
			ht.put("corpPaybase",this.corpPaybase);
		if(this.corpAddition!=null)
			ht.put("corpAddition",this.corpAddition);
		if(this.psnAddition!=null)
			ht.put("psnAddition",this.psnAddition);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/24 09:21:06
	
	private String flow;//是否支持下行数据

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;

	}

	private boolean isExtend = false;//当前是否需要调整起始月份

	public boolean isExtend() {
		return isExtend;
	}

	public void setExtend(boolean extend) {
		isExtend = extend;
	}

	private String minPeriod;

	public String getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(String minPeriod) {
		this.minPeriod = minPeriod;
	}

	private String insuranceFundType;

	public String getInsuranceFundType() {
		return insuranceFundType;
	}

	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}

	private String period;

	private String payType;


	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}

