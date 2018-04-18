package com.xfs.sp.model;

import java.util.Date;
import java.util.HashMap;

/**
 * SpsAccountinfo
 * @author:wuzhe
 * 
 * @date:2016/04/19 15:34:17
 */
public class SpsAccountinfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer spId;//

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	private String payAccount;// 付款账号

	public String getPayAccount() {
		return this.payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	private String payAccountEq;// 付款账号

	public String getPayAccountEq() {
		return this.payAccountEq;
	}

	public void setPayAccountEq(String payAccountEq) {
		this.payAccountEq = payAccountEq;
	}

	private String payTeller;// 付款账户

	public String getPayTeller() {
		return this.payTeller;
	}

	public void setPayTeller(String payTeller) {
		this.payTeller = payTeller;
	}

	private String payTellerEq;// 付款账户

	public String getPayTellerEq() {
		return this.payTellerEq;
	}

	public void setPayTellerEq(String payTellerEq) {
		this.payTellerEq = payTellerEq;
	}

	private String payBank;// 付款银行

	public String getPayBank() {
		return this.payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	private String payBankEq;// 付款银行

	public String getPayBankEq() {
		return this.payBankEq;
	}

	public void setPayBankEq(String payBankEq) {
		this.payBankEq = payBankEq;
	}

	private Date dealDate;// 交易日期

	public Date getDealDate() {
		return this.dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	private Date dealDateFrom;// 交易日期
	private String timeStar;// 交易日期

	public Date getDealDateFrom() {
		return this.dealDateFrom;
	}

	public void setDealDateFrom(Date dealDateFrom) {
		this.dealDateFrom = dealDateFrom;
	}

	public String getTimeStar() {
		return timeStar;
	}

	public void setTimeStar(String timeStar) {
		this.timeStar = timeStar;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	private Date dealDateTo;// 交易日期
	private String timeEnd;// 交易日期

	public Date getDealDateTo() {
		return this.dealDateTo;
	}

	public void setDealDateTo(Date dealDateTo) {
		this.dealDateTo = dealDateTo;
	}

	private String dealMoney;// 交易金额

	public String getDealMoney() {
		return this.dealMoney;
	}

	public void setDealMoney(String dealMoney) {
		this.dealMoney = dealMoney;
	}

	private String dealMoneyEq;// 交易金额

	public String getDealMoneyEq() {
		return this.dealMoneyEq;
	}

	public void setDealMoneyEq(String dealMoneyEq) {
		this.dealMoneyEq = dealMoneyEq;
	}

	private String memo;// 备注

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	private String memoEq;// 备注

	public String getMemoEq() {
		return this.memoEq;
	}

	public void setMemoEq(String memoEq) {
		this.memoEq = memoEq;
	}

	private Integer receiver;// 认领人

	public Integer getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	private Integer createBy;//

	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	private Date createDt;//

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	private Date createDtFrom;//

	public Date getCreateDtFrom() {
		return this.createDtFrom;
	}

	public void setCreateDtFrom(Date createDtFrom) {
		this.createDtFrom = createDtFrom;
	}

	private Date createDtTo;//

	public Date getCreateDtTo() {
		return this.createDtTo;
	}

	public void setCreateDtTo(Date createDtTo) {
		this.createDtTo = createDtTo;
	}

	private Integer modifyBy;//

	public Integer getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	private Date modifyDt;//

	public Date getModifyDt() {
		return this.modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	private Date modifyDtFrom;//

	public Date getModifyDtFrom() {
		return this.modifyDtFrom;
	}

	public void setModifyDtFrom(Date modifyDtFrom) {
		this.modifyDtFrom = modifyDtFrom;
	}

	private Date modifyDtTo;//

	public Date getModifyDtTo() {
		return this.modifyDtTo;
	}

	public void setModifyDtTo(Date modifyDtTo) {
		this.modifyDtTo = modifyDtTo;
	}

	public void fixup() {
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.id != null)
			ht.put("id", this.id);
		if (this.spId != null)
			ht.put("spId", this.spId);
		if (this.payAccount != null)
			ht.put("payAccount", this.payAccount);
		if (this.payAccountEq != null)
			ht.put("payAccountEq", this.payAccountEq);
		if (this.payTeller != null)
			ht.put("payTeller", this.payTeller);
		if (this.payTellerEq != null)
			ht.put("payTellerEq", this.payTellerEq);
		if (this.payBank != null)
			ht.put("payBank", this.payBank);
		if (this.payBankEq != null)
			ht.put("payBankEq", this.payBankEq);
		if (this.dealDate != null)
			ht.put("dealDate", this.dealDate);
		if (this.dealDateFrom != null)
			ht.put("dealDateFrom", this.dealDateFrom);
		if (this.dealDateTo != null)
			ht.put("dealDateTo", this.dealDateTo);
		if (this.dealMoney != null)
			ht.put("dealMoney", this.dealMoney);
		if (this.dealMoneyEq != null)
			ht.put("dealMoneyEq", this.dealMoneyEq);
		if (this.memo != null)
			ht.put("memo", this.memo);
		if (this.memoEq != null)
			ht.put("memoEq", this.memoEq);
		if (this.receiver != null)
			ht.put("receiver", this.receiver);
		if (this.createBy != null)
			ht.put("createBy", this.createBy);
		if (this.createDt != null)
			ht.put("createDt", this.createDt);
		if (this.createDtFrom != null)
			ht.put("createDtFrom", this.createDtFrom);
		if (this.createDtTo != null)
			ht.put("createDtTo", this.createDtTo);
		if (this.modifyBy != null)
			ht.put("modifyBy", this.modifyBy);
		if (this.modifyDt != null)
			ht.put("modifyDt", this.modifyDt);
		if (this.modifyDtFrom != null)
			ht.put("modifyDtFrom", this.modifyDtFrom);
		if (this.modifyDtTo != null)
			ht.put("modifyDtTo", this.modifyDtTo);
		return ht;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/19 15:34:17

}
