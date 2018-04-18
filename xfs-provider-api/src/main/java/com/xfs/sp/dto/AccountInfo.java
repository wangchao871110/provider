package com.xfs.sp.dto;

import java.math.BigDecimal;
import java.util.List;

import com.xfs.base.model.BsArea;

public class AccountInfo {
	
	
	private  Integer areaParent1;
	
	private Integer areaParen2;
	
	private List<BsArea> areaChild1;
	
	
	private List<BsArea> areaChild2;
	
	
	private Integer accId1;
	
	
	private Integer accid2;
	
	
	/**
	 * 社保所在地区
	 */
	private  Integer areaId1;
	/**
	 * 社保所在地区 三级下拉
	 */
	private  Integer areaIdSub1;
	
	/**
	 * 社保登记号
	 */
	private String regNum1;
	
	/**
	 * 社保登记口令
	 */
	private String regNumpass1;
	

	/**
	 * 社保登记证书密码
	 */
	private String regUsbkeypass;
	
	
	/**
	 * 社保托收日
	 */
	private String  collectionDay1;
	
	
	/**
	 * 公积金托收日
	 */
	private String collectionDay2;
	
	
	/**
	 * 公积金账号
	 */
	private String regNum2;
	
	
	/**
	 * 公积金密码
	 */
	private String regNumpass2;
	
	
	/**
	 * 公积金所在地区
	 */
	private Integer areaId2;
	/**
	 * 公积金所在地区 三级下拉
	 */
	private Integer areaIdSub2;


	//公积金个人缴存比例
	private BigDecimal empRatio;//
	public BigDecimal getEmpRatio() {
		return this.empRatio;
	}
	public void setEmpRatio(BigDecimal empRatio) {
		this.empRatio=empRatio;
	}

	//公积金单位缴存比例
	private BigDecimal corpRatio;//
	public BigDecimal getCorpRatio() {
		return this.corpRatio;
	}
	public void setCorpRatio(BigDecimal corpRatio) {
		this.corpRatio=corpRatio;
	}

	//企业社保比例
	private BigDecimal inCorpRatio;

	public Integer getAreaIdSub1() {
		return areaIdSub1;
	}

	public void setAreaIdSub1(Integer areaIdSub1) {
		this.areaIdSub1 = areaIdSub1;
	}

	public Integer getAreaIdSub2() {
		return areaIdSub2;
	}

	public void setAreaIdSub2(Integer areaIdSub2) {
		this.areaIdSub2 = areaIdSub2;
	}

	public BigDecimal getInCorpRatio() {
		return inCorpRatio;
	}

	public void setInCorpRatio(BigDecimal inCorpRatio) {
		this.inCorpRatio = inCorpRatio;
	}

	public Integer getAreaId1() {
		return areaId1;
	}


	public void setAreaId1(Integer areaId1) {
		this.areaId1 = areaId1;
	}


	public String getRegNum1() {
		return regNum1;
	}


	public void setRegNum1(String regNum1) {
		this.regNum1 = regNum1;
	}


	public String getRegNumpass1() {
		return regNumpass1;
	}


	public void setRegNumpass1(String regNumpass1) {
		this.regNumpass1 = regNumpass1;
	}


	public String getRegUsbkeypass() {
		return regUsbkeypass;
	}


	public void setRegUsbkeypass(String regUsbkeypass) {
		this.regUsbkeypass = regUsbkeypass;
	}



	public String getRegNum2() {
		return regNum2;
	}


	public void setRegNum2(String regNum2) {
		this.regNum2 = regNum2;
	}


	public String getRegNumpass2() {
		return regNumpass2;
	}


	public void setRegNumpass2(String regNumpass2) {
		this.regNumpass2 = regNumpass2;
	}


	public Integer getAreaId2() {
		return areaId2;
	}


	public void setAreaId2(Integer areaId2) {
		this.areaId2 = areaId2;
	}


	public Integer getAccId1() {
		return accId1;
	}


	public void setAccId1(Integer accId1) {
		this.accId1 = accId1;
	}


	public Integer getAccid2() {
		return accid2;
	}


	public void setAccid2(Integer accid2) {
		this.accid2 = accid2;
	}


	public String getCollectionDay1() {
		return collectionDay1;
	}


	public void setCollectionDay1(String collectionDay1) {
		this.collectionDay1 = collectionDay1;
	}


	public String getCollectionDay2() {
		return collectionDay2;
	}


	public void setCollectionDay2(String collectionDay2) {
		this.collectionDay2 = collectionDay2;
	}


	public Integer getAreaParent1() {
		return areaParent1;
	}


	public void setAreaParent1(Integer areaParent1) {
		this.areaParent1 = areaParent1;
	}


	public Integer getAreaParen2() {
		return areaParen2;
	}


	public void setAreaParen2(Integer areaParen2) {
		this.areaParen2 = areaParen2;
	}


	public List<BsArea> getAreaChild1() {
		return areaChild1;
	}


	public void setAreaChild1(List<BsArea> areaChild1) {
		this.areaChild1 = areaChild1;
	}


	public List<BsArea> getAreaChild2() {
		return areaChild2;
	}


	public void setAreaChild2(List<BsArea> areaChild2) {
		this.areaChild2 = areaChild2;
	}
	
	private BigDecimal unemploymentRate;//失业比例
	public BigDecimal getUnemploymentRate() {
	    return this.unemploymentRate;
	}
	public void setUnemploymentRate(BigDecimal unemploymentRate) {
	    this.unemploymentRate=unemploymentRate;
	}
	
}
