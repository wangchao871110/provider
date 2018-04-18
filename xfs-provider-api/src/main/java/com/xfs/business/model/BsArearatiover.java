package com.xfs.business.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * BsArearatiover
 * @author:wuzhe
 * @date:2016/09/21 20:04:09	
 */
public class BsArearatiover implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer ratioId;//险种比例主键
	public Integer getRatioId() {
	    return this.ratioId;
	}
	public void setRatioId(Integer ratioId) {
	    this.ratioId=ratioId;
	}
	private String beginPeriod;//起始期间
	public String getBeginPeriod() {
	    return this.beginPeriod;
	}

    private String beginPeriodFrom;
    private String beginPeriodTo;

    public String getBeginPeriodFrom() {
        return beginPeriodFrom;
    }

    public void setBeginPeriodFrom(String beginPeriodFrom) {
        this.beginPeriodFrom = beginPeriodFrom;
    }

    public String getBeginPeriodTo() {
        return beginPeriodTo;
    }

    public void setBeginPeriodTo(String beginPeriodTo) {
        this.beginPeriodTo = beginPeriodTo;
    }

    public void setBeginPeriod(String beginPeriod) {
	    this.beginPeriod=beginPeriod;
	}
	private String beginPeriodEq;//起始期间
	public String getBeginPeriodEq() {
	    return this.beginPeriodEq;
	}
	public void setBeginPeriodEq(String beginPeriodEq) {
	    this.beginPeriodEq=beginPeriodEq;
	}
	private String endPeriod;//结束期间
	public String getEndPeriod() {
	    return this.endPeriod;
	}

    private String endPeriodFrom;
    private String endPeriodTo;

    public String getEndPeriodFrom() {
        return endPeriodFrom;
    }

    public void setEndPeriodFrom(String endPeriodFrom) {
        this.endPeriodFrom = endPeriodFrom;
    }

    public String getEndPeriodTo() {
        return endPeriodTo;
    }

    public void setEndPeriodTo(String endPeriodTo) {
        this.endPeriodTo = endPeriodTo;
    }

    public void setEndPeriod(String endPeriod) {
	    this.endPeriod=endPeriod;
	}
	private String endPeriodEq;//结束期间
	public String getEndPeriodEq() {
	    return this.endPeriodEq;
	}
	public void setEndPeriodEq(String endPeriodEq) {
	    this.endPeriodEq=endPeriodEq;
	}
	private BigDecimal corpBaseLower;//企业基数下限
	public BigDecimal getCorpBaseLower() {
	    return this.corpBaseLower;
	}
	public void setCorpBaseLower(BigDecimal corpBaseLower) {
	    this.corpBaseLower=corpBaseLower;
	}
	private BigDecimal corpBaseUpper;//企业基数上限
	public BigDecimal getCorpBaseUpper() {
	    return this.corpBaseUpper;
	}
	public void setCorpBaseUpper(BigDecimal corpBaseUpper) {
	    this.corpBaseUpper=corpBaseUpper;
	}
	private BigDecimal psnBaseLower;//个人基数下限
	public BigDecimal getPsnBaseLower() {
	    return this.psnBaseLower;
	}
	public void setPsnBaseLower(BigDecimal psnBaseLower) {
	    this.psnBaseLower=psnBaseLower;
	}
	private BigDecimal psnBaseUpper;//个人基数上限
	public BigDecimal getPsnBaseUpper() {
	    return this.psnBaseUpper;
	}
	public void setPsnBaseUpper(BigDecimal psnBaseUpper) {
	    this.psnBaseUpper=psnBaseUpper;
	}
	private BigDecimal corpRatio;//企业比例
	public BigDecimal getCorpRatio() {
	    return this.corpRatio;
	}
	public void setCorpRatio(BigDecimal corpRatio) {
	    this.corpRatio=corpRatio;
	}
	private BigDecimal psnRatio;//个人比例
	public BigDecimal getPsnRatio() {
	    return this.psnRatio;
	}
	public void setPsnRatio(BigDecimal psnRatio) {
	    this.psnRatio=psnRatio;
	}
	private BigDecimal corpAddition;//企业附加金额
	public BigDecimal getCorpAddition() {
	    return this.corpAddition;
	}
	public void setCorpAddition(BigDecimal corpAddition) {
	    this.corpAddition=corpAddition;
	}
	private BigDecimal psnAddition;//个人附加金额
	public BigDecimal getPsnAddition() {
	    return this.psnAddition;
	}
	public void setPsnAddition(BigDecimal psnAddition) {
	    this.psnAddition=psnAddition;
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
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.ratioId!=null)
			ht.put("ratioId",this.ratioId);
		if(this.beginPeriod!=null)
			ht.put("beginPeriod",this.beginPeriod);
		if(this.beginPeriodEq!=null)
			ht.put("beginPeriodEq",this.beginPeriodEq);
		if(this.endPeriod!=null)
			ht.put("endPeriod",this.endPeriod);
		if(this.endPeriodEq!=null)
			ht.put("endPeriodEq",this.endPeriodEq);
		if(this.corpBaseLower!=null)
			ht.put("corpBaseLower",this.corpBaseLower);
		if(this.corpBaseUpper!=null)
			ht.put("corpBaseUpper",this.corpBaseUpper);
		if(this.psnBaseLower!=null)
			ht.put("psnBaseLower",this.psnBaseLower);
		if(this.psnBaseUpper!=null)
			ht.put("psnBaseUpper",this.psnBaseUpper);
		if(this.corpRatio!=null)
			ht.put("corpRatio",this.corpRatio);
		if(this.psnRatio!=null)
			ht.put("psnRatio",this.psnRatio);
		if(this.corpAddition!=null)
			ht.put("corpAddition",this.corpAddition);
		if(this.psnAddition!=null)
			ht.put("psnAddition",this.psnAddition);
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
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:09
	
	
}

