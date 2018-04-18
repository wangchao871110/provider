package com.xfs.bill.model;

import java.util.HashMap;


/**
 * BdBillrule 
 * @author:konglc
 * @date:2016/09/09 11:34:52	
 */
public class BdBillrule implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer areaId;//
	public Integer getAreaId() {
	    return this.areaId;
	}
	public void setAreaId(Integer areaId) {
	    this.areaId=areaId;
	}
	private String insuranceRule;//enum:            当月托收当月,CURMONTH;            当月托收次月,NEXTMONTH;//           当月托收当月,CURMONTH
public static final String insuranceRule_CURMONTH ="CURMONTH";
//            当月托收次月,NEXTMONTH
public static final String insuranceRule_NEXTMONTH ="NEXTMONTH";

	public String getInsuranceRule() {
	    return this.insuranceRule;
	}
	public void setInsuranceRule(String insuranceRule) {
	    this.insuranceRule=insuranceRule;
	}
	private String insuranceRuleEq;//enum:            当月托收当月,CURMONTH;            当月托收次月,NEXTMONTH;//           当月托收当月,CURMONTH
public static final String insuranceRuleEq_CURMONTH ="CURMONTH";
//            当月托收次月,NEXTMONTH
public static final String insuranceRuleEq_NEXTMONTH ="NEXTMONTH";

	public String getInsuranceRuleEq() {
	    return this.insuranceRuleEq;
	}
	public void setInsuranceRuleEq(String insuranceRuleEq) {
	    this.insuranceRuleEq=insuranceRuleEq;
	}
	private String fundRule;//enum:            当月托收当月,CURMONTH;            当月托收次月,NEXTMONTH;//           当月托收当月,CURMONTH
public static final String fundRule_CURMONTH ="CURMONTH";
//            当月托收次月,NEXTMONTH
public static final String fundRule_NEXTMONTH ="NEXTMONTH";

	public String getFundRule() {
	    return this.fundRule;
	}
	public void setFundRule(String fundRule) {
	    this.fundRule=fundRule;
	}
	private String fundRuleEq;//enum:            当月托收当月,CURMONTH;            当月托收次月,NEXTMONTH;//           当月托收当月,CURMONTH
public static final String fundRuleEq_CURMONTH ="CURMONTH";
//            当月托收次月,NEXTMONTH
public static final String fundRuleEq_NEXTMONTH ="NEXTMONTH";

	public String getFundRuleEq() {
	    return this.fundRuleEq;
	}
	public void setFundRuleEq(String fundRuleEq) {
	    this.fundRuleEq=fundRuleEq;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.insuranceRule!=null)
			ht.put("insuranceRule",this.insuranceRule);
		if(this.insuranceRuleEq!=null)
			ht.put("insuranceRuleEq",this.insuranceRuleEq);
		if(this.fundRule!=null)
			ht.put("fundRule",this.fundRule);
		if(this.fundRuleEq!=null)
			ht.put("fundRuleEq",this.fundRuleEq);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/09 11:34:52
	
	
}

