package com.xfs.business.model;

import java.math.BigDecimal;

public interface BsRatioCalcInterface {

	Integer getInsuranceId();

	void setInsuranceId(Integer insuranceId);

	BigDecimal getEmpPayment();

	void setEmpPayment(BigDecimal empPayment);

	BigDecimal getCorpPayment();

	void setCorpPayment(BigDecimal corpPayment);

	BigDecimal getEmpRatio();

	void setEmpRatio(BigDecimal empRatio);

	BigDecimal getCorpRatio();

	void setCorpRatio(BigDecimal corpRatio);

	Integer getRatioId();

	BigDecimal getEmpPaybase();

	void setEmpPaybase(BigDecimal empPaybase);

	BigDecimal getCorpPaybase();

	void setCorpPaybase(BigDecimal corpPaybase);

	BigDecimal getCorpAddition();

	void setCorpAddition(BigDecimal corpAddition);

	BigDecimal getPsnAddition();

	void setPsnAddition(BigDecimal psnAddition);

}