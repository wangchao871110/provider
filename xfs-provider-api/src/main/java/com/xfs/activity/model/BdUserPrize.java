package com.xfs.activity.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * BdUserPrize * @author:duanax * @date:2016/10/11 19:32:46	 */public class BdUserPrize implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer id;//	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id=id;	}	private Integer userId;//	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	private String userType;//	public String getUserType() {	    return this.userType;	}	public void setUserType(String userType) {	    this.userType=userType;	}	private String userTypeEq;//	public String getUserTypeEq() {	    return this.userTypeEq;	}	public void setUserTypeEq(String userTypeEq) {	    this.userTypeEq=userTypeEq;	}	private Integer orgId;//	public Integer getOrgId() {	    return this.orgId;	}	public void setOrgId(Integer orgId) {	    this.orgId=orgId;	}	private Integer lotteryId;//	public Integer getLotteryId() {	    return this.lotteryId;	}	public void setLotteryId(Integer lotteryId) {	    this.lotteryId=lotteryId;	}	private Integer prizeId;//	public Integer getPrizeId() {	    return this.prizeId;	}	public void setPrizeId(Integer prizeId) {	    this.prizeId=prizeId;	}	private String prizeCode;//	public String getPrizeCode() {	    return this.prizeCode;	}	public void setPrizeCode(String prizeCode) {	    this.prizeCode=prizeCode;	}	private String prizeCodeEq;//	public String getPrizeCodeEq() {	    return this.prizeCodeEq;	}	public void setPrizeCodeEq(String prizeCodeEq) {	    this.prizeCodeEq=prizeCodeEq;	}	private BigDecimal prizePrice;//	public BigDecimal getPrizePrice() {	    return this.prizePrice;	}	public void setPrizePrice(BigDecimal prizePrice) {	    this.prizePrice=prizePrice;	}	private Date begintime;//	public Date getBegintime() {	    return this.begintime;	}	public void setBegintime(Date begintime) {	    this.begintime=begintime;	}	private Date begintimeFrom;//	public Date getBegintimeFrom() {	    return this.begintimeFrom;	}	public void setBegintimeFrom(Date begintimeFrom) {	    this.begintimeFrom=begintimeFrom;	}	private Date begintimeTo;//	public Date getBegintimeTo() {	    return this.begintimeTo;	}	public void setBegintimeTo(Date begintimeTo) {	    this.begintimeTo=begintimeTo;	}	private Date endtime;//	public Date getEndtime() {	    return this.endtime;	}	public void setEndtime(Date endtime) {	    this.endtime=endtime;	}	private Date endtimeFrom;//	public Date getEndtimeFrom() {	    return this.endtimeFrom;	}	public void setEndtimeFrom(Date endtimeFrom) {	    this.endtimeFrom=endtimeFrom;	}	private Date endtimeTo;//	public Date getEndtimeTo() {	    return this.endtimeTo;	}	public void setEndtimeTo(Date endtimeTo) {	    this.endtimeTo=endtimeTo;	}	private String state;//未使用:UNUSE；已使用：USED；已过期:EXPIRED	public static final String state_NOT_ACTIVE ="NOT_ACTIVE";	//            未使用,UNUSE	public static final String state_UNUSE ="UNUSE";	//            已使用,USED	public static final String state_USED ="USED";	//            已过期,EXPIRED	public static final String state_EXPIRED ="EXPIRED";	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	private String stateEq;//未使用:UNUSE；已使用：USED；已过期:EXPIRED	public String getStateEq() {	    return this.stateEq;	}	public void setStateEq(String stateEq) {	    this.stateEq=stateEq;	}	private String userMobile;//	public String getUserMobile() {	    return this.userMobile;	}	public void setUserMobile(String userMobile) {	    this.userMobile=userMobile;	}	private String userMobileEq;//	public String getUserMobileEq() {	    return this.userMobileEq;	}	public void setUserMobileEq(String userMobileEq) {	    this.userMobileEq=userMobileEq;	}	private String userName;//	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	private String userNameEq;//	public String getUserNameEq() {	    return this.userNameEq;	}	public void setUserNameEq(String userNameEq) {	    this.userNameEq=userNameEq;	}	private String userAddress;//	public String getUserAddress() {	    return this.userAddress;	}	public void setUserAddress(String userAddress) {	    this.userAddress=userAddress;	}	private String userAddressEq;//	public String getUserAddressEq() {	    return this.userAddressEq;	}	public void setUserAddressEq(String userAddressEq) {	    this.userAddressEq=userAddressEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.userId!=null)			ht.put("userId",this.userId);		if(this.userType!=null)			ht.put("userType",this.userType);		if(this.userTypeEq!=null)			ht.put("userTypeEq",this.userTypeEq);		if(this.orgId!=null)			ht.put("orgId",this.orgId);		if(this.lotteryId!=null)			ht.put("lotteryId",this.lotteryId);		if(this.prizeId!=null)			ht.put("prizeId",this.prizeId);		if(this.prizeCode!=null)			ht.put("prizeCode",this.prizeCode);		if(this.prizeCodeEq!=null)			ht.put("prizeCodeEq",this.prizeCodeEq);		if(this.prizePrice!=null)			ht.put("prizePrice",this.prizePrice);		if(this.begintime!=null)			ht.put("begintime",this.begintime);		if(this.begintimeFrom!=null)			ht.put("begintimeFrom",this.begintimeFrom);		if(this.begintimeTo!=null)			ht.put("begintimeTo",this.begintimeTo);		if(this.endtime!=null)			ht.put("endtime",this.endtime);		if(this.endtimeFrom!=null)			ht.put("endtimeFrom",this.endtimeFrom);		if(this.endtimeTo!=null)			ht.put("endtimeTo",this.endtimeTo);		if(this.state!=null)			ht.put("state",this.state);		if(this.stateEq!=null)			ht.put("stateEq",this.stateEq);		if(this.userMobile!=null)			ht.put("userMobile",this.userMobile);		if(this.userMobileEq!=null)			ht.put("userMobileEq",this.userMobileEq);		if(this.userName!=null)			ht.put("userName",this.userName);		if(this.userNameEq!=null)			ht.put("userNameEq",this.userNameEq);		if(this.userAddress!=null)			ht.put("userAddress",this.userAddress);		if(this.userAddressEq!=null)			ht.put("userAddressEq",this.userAddressEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/10/11 19:32:47		}