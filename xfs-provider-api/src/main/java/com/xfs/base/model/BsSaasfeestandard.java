package com.xfs.base.model;

import java.math.BigDecimal;
import java.util.HashMap;



/**
 * BsSaasfeestandard
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/14 14:00:13	
 */
public class BsSaasfeestandard implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer lower;//
	public Integer getLower() {
	    return this.lower;
	}
	public void setLower(Integer lower) {
	    this.lower=lower;
	}
	private Integer upper;//
	public Integer getUpper() {
	    return this.upper;
	}
	public void setUpper(Integer upper) {
	    this.upper=upper;
	}
	private BigDecimal quarterFee;//
	public BigDecimal getQuarterFee() {
	    return this.quarterFee;
	}
	public void setQuarterFee(BigDecimal quarterFee) {
	    this.quarterFee=quarterFee;
	}
	private BigDecimal halfFee;//
	public BigDecimal getHalfFee() {
	    return this.halfFee;
	}
	public void setHalfFee(BigDecimal halfFee) {
	    this.halfFee=halfFee;
	}
	private BigDecimal yearFee;//
	public BigDecimal getYearFee() {
	    return this.yearFee;
	}
	public void setYearFee(BigDecimal yearFee) {
	    this.yearFee=yearFee;
	}
	private BigDecimal threeFee;//
	public BigDecimal getThreeFee() {
	    return this.threeFee;
	}
	public void setThreeFee(BigDecimal threeFee) {
	    this.threeFee=threeFee;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.lower!=null)
			ht.put("lower",this.lower);
		if(this.upper!=null)
			ht.put("upper",this.upper);
		if(this.quarterFee!=null)
			ht.put("quarterFee",this.quarterFee);
		if(this.halfFee!=null)
			ht.put("halfFee",this.halfFee);
		if(this.yearFee!=null)
			ht.put("yearFee",this.yearFee);
		if(this.threeFee!=null)
			ht.put("threeFee",this.threeFee);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/14 14:00:14
	
	
}

