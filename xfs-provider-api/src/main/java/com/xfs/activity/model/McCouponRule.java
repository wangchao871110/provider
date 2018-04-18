package com.xfs.activity.model;import java.util.Date;import java.util.HashMap;/** * McCouponRule * @author:wangdx * @date:2016/10/11 15:46:44	 */public class McCouponRule implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer classifyId;//优惠券使用类型，1为平台优惠劵，2为服务商优惠劵	public Integer getClassifyId() {	    return this.classifyId;	}	public void setClassifyId(Integer classifyId) {	    this.classifyId=classifyId;	}	private Integer useClient;//优惠券使用客户端,1-APP;2-PC,3-微信	public Integer getUseClient() {	    return this.useClient;	}	public void setUseClient(Integer useClient) {	    this.useClient=useClient;	}	private Double minMoney;//优惠券使用最低金额	public Double getMinMoney() {	    return this.minMoney;	}	public void setMinMoney(Double minMoney) {	    this.minMoney=minMoney;	}	private Date createdt;//创建时间	public Date getCreatedt() {	    return this.createdt;	}	public void setCreatedt(Date createdt) {	    this.createdt=createdt;	}	private Date createdtFrom;//创建时间	public Date getCreatedtFrom() {	    return this.createdtFrom;	}	public void setCreatedtFrom(Date createdtFrom) {	    this.createdtFrom=createdtFrom;	}	private Date createdtTo;//创建时间	public Date getCreatedtTo() {	    return this.createdtTo;	}	public void setCreatedtTo(Date createdtTo) {	    this.createdtTo=createdtTo;	}	private Double money;//优惠券金额	public Double getMoney() {	    return this.money;	}	public void setMoney(Double money) {	    this.money=money;	}	private Date doleTime;//优惠券开始时间	public Date getDoleTime() {	    return this.doleTime;	}	public void setDoleTime(Date doleTime) {	    this.doleTime=doleTime;	}	private Date doleTimeFrom;//优惠券开始时间	public Date getDoleTimeFrom() {	    return this.doleTimeFrom;	}	public void setDoleTimeFrom(Date doleTimeFrom) {	    this.doleTimeFrom=doleTimeFrom;	}	private Date doleTimeTo;//优惠券开始时间	public Date getDoleTimeTo() {	    return this.doleTimeTo;	}	public void setDoleTimeTo(Date doleTimeTo) {	    this.doleTimeTo=doleTimeTo;	}	private Integer validTime;//优惠券有效时间,单位：天	public Integer getValidTime() {	    return this.validTime;	}	public void setValidTime(Integer validTime) {	    this.validTime=validTime;	}	private Date expiredTime;//优惠券过期时间	public Date getExpiredTime() {	    return this.expiredTime;	}	public void setExpiredTime(Date expiredTime) {	    this.expiredTime=expiredTime;	}	private Date expiredTimeFrom;//优惠券过期时间	public Date getExpiredTimeFrom() {	    return this.expiredTimeFrom;	}	public void setExpiredTimeFrom(Date expiredTimeFrom) {	    this.expiredTimeFrom=expiredTimeFrom;	}	private Date expiredTimeTo;//优惠券过期时间	public Date getExpiredTimeTo() {	    return this.expiredTimeTo;	}	public void setExpiredTimeTo(Date expiredTimeTo) {	    this.expiredTimeTo=expiredTimeTo;	}	private String couponName;//优惠劵名称	public String getCouponName() {	    return this.couponName;	}	public void setCouponName(String couponName) {	    this.couponName=couponName;	}	private String couponNameEq;//优惠劵名称	public String getCouponNameEq() {	    return this.couponNameEq;	}	public void setCouponNameEq(String couponNameEq) {	    this.couponNameEq=couponNameEq;	}	private Integer perCapitaSum;//人均可领取优惠劵	public Integer getPerCapitaSum() {	    return this.perCapitaSum;	}	public void setPerCapitaSum(Integer perCapitaSum) {	    this.perCapitaSum=perCapitaSum;	}	private Integer couponSum;//优惠劵数量	public Integer getCouponSum() {	    return this.couponSum;	}	public void setCouponSum(Integer couponSum) {	    this.couponSum=couponSum;	}	private String typeParam;//类型参数	public String getTypeParam() {	    return this.typeParam;	}	public void setTypeParam(String typeParam) {	    this.typeParam=typeParam;	}	private String typeParamEq;//类型参数	public String getTypeParamEq() {	    return this.typeParamEq;	}	public void setTypeParamEq(String typeParamEq) {	    this.typeParamEq=typeParamEq;	}	private Integer state;//优惠券状态，1为新建未开始，2为进行中，3为关闭	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.classifyId!=null)			ht.put("classifyId",this.classifyId);		if(this.useClient!=null)			ht.put("useClient",this.useClient);		if(this.minMoney!=null)			ht.put("minMoney",this.minMoney);		if(this.createdt!=null)			ht.put("createdt",this.createdt);		if(this.createdtFrom!=null)			ht.put("createdtFrom",this.createdtFrom);		if(this.createdtTo!=null)			ht.put("createdtTo",this.createdtTo);		if(this.money!=null)			ht.put("money",this.money);		if(this.doleTime!=null)			ht.put("doleTime",this.doleTime);		if(this.doleTimeFrom!=null)			ht.put("doleTimeFrom",this.doleTimeFrom);		if(this.doleTimeTo!=null)			ht.put("doleTimeTo",this.doleTimeTo);		if(this.validTime!=null)			ht.put("validTime",this.validTime);		if(this.expiredTime!=null)			ht.put("expiredTime",this.expiredTime);		if(this.expiredTimeFrom!=null)			ht.put("expiredTimeFrom",this.expiredTimeFrom);		if(this.expiredTimeTo!=null)			ht.put("expiredTimeTo",this.expiredTimeTo);		if(this.couponName!=null)			ht.put("couponName",this.couponName);		if(this.couponNameEq!=null)			ht.put("couponNameEq",this.couponNameEq);		if(this.perCapitaSum!=null)			ht.put("perCapitaSum",this.perCapitaSum);		if(this.couponSum!=null)			ht.put("couponSum",this.couponSum);		if(this.typeParam!=null)			ht.put("typeParam",this.typeParam);		if(this.typeParamEq!=null)			ht.put("typeParamEq",this.typeParamEq);		if(this.state!=null)			ht.put("state",this.state);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/10/11 15:46:44		}