package com.xfs.sp.dto;

import java.math.BigDecimal;

import com.xfs.base.model.BsSaasfeestandard;

public class BsSaasfeestandardDto extends BsSaasfeestandard{
	//半年折扣率
	private BigDecimal halfDiscountRate;
	//年折扣率
	private BigDecimal yearDiscountRate;
	//三年折扣率
	private BigDecimal threeDiscountRate;


	public BigDecimal getHalfDiscountRate() {
		return halfDiscountRate;
	}

	public void setHalfDiscountRate(BigDecimal halfDiscountRate) {
		this.halfDiscountRate = halfDiscountRate;
	}

	public BigDecimal getYearDiscountRate() {
		return yearDiscountRate;
	}

	public void setYearDiscountRate(BigDecimal yearDiscountRate) {
		this.yearDiscountRate = yearDiscountRate;
	}

	public BigDecimal getThreeDiscountRate() {
		return threeDiscountRate;
	}

	public void setThreeDiscountRate(BigDecimal threeDiscountRate) {
		this.threeDiscountRate = threeDiscountRate;
	}
}
