package com.xfs.crawler.model;import java.text.SimpleDateFormat;import java.util.*;import java.lang.Integer;import java.lang.String;import java.util.Date;/** * SysCrawlerRule * @author:quanjiahua<quanjiahua@163.com> * @date:2017/02/20 09:42:24	 */public class SysCrawlerRule implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer id;//	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id=id;	}	private String siteName;//网站名称	public String getSiteName() {		return this.siteName;	}	public void setSiteName(String siteName) {		this.siteName=siteName;	}	private String siteUrl;//网站地址	public String getSiteUrl() {		return this.siteUrl;	}	public void setSiteUrl(String siteUrl) {		this.siteUrl=siteUrl;	}	private Integer areaId;//城市ID	public Integer getAreaId() {		return this.areaId;	}	public void setAreaId(Integer areaId) {		this.areaId=areaId;	}	private String areaName;//	public String getAreaName() {		return this.areaName;	}	public void setAreaName(String areaName) {		this.areaName=areaName;	}	private String siteCategory;//网站栏目分类	public String getSiteCategory() {		return this.siteCategory;	}	public void setSiteCategory(String siteCategory) {		this.siteCategory=siteCategory;	}	private Integer firstCategoryId;//	public Integer getFirstCategoryId() {		return this.firstCategoryId;	}	public void setFirstCategoryId(Integer firstCategoryId) {		this.firstCategoryId=firstCategoryId;	}	private String firstCategoryNames;//	public String getFirstCategoryNames() {		return this.firstCategoryNames;	}	public void setFirstCategoryNames(String firstCategoryNames) {		this.firstCategoryNames=firstCategoryNames;	}	private String secCategoryIds;//	public String getSecCategoryIds() {		return this.secCategoryIds;	}	public void setSecCategoryIds(String secCategoryIds) {		this.secCategoryIds=secCategoryIds;	}	private String secCategoryNames;//	public String getSecCategoryNames() {		return this.secCategoryNames;	}	public void setSecCategoryNames(String secCategoryNames) {		this.secCategoryNames=secCategoryNames;	}	private String siteCode;//网站编码	public String getSiteCode() {		return this.siteCode;	}	public void setSiteCode(String siteCode) {		this.siteCode=siteCode;	}	private Integer siteLevel;//优先级	public Integer getSiteLevel() {		return this.siteLevel;	}	public void setSiteLevel(Integer siteLevel) {		this.siteLevel=siteLevel;	}	private Integer sleepTime;//休眠时间 单位毫秒	public Integer getSleepTime() {		return this.sleepTime;	}	public void setSleepTime(Integer sleepTime) {		this.sleepTime=sleepTime;	}	private String siteListurl;//爬取文章列表页url	public String getSiteListurl() {		return this.siteListurl;	}	public void setSiteListurl(String siteListurl) {		this.siteListurl=siteListurl;	}	private String siteListrule;//列表规则配置获取a标签url	public String getSiteListrule() {		return this.siteListrule;	}	public void setSiteListrule(String siteListrule) {		this.siteListrule=siteListrule;	}	private String siteArule;//	public String getSiteArule() {		return this.siteArule;	}	public void setSiteArule(String siteArule) {		this.siteArule=siteArule;	}	private String siteListimgrule;//	public String getSiteListimgrule() {		return this.siteListimgrule;	}	public void setSiteListimgrule(String siteListimgrule) {		this.siteListimgrule=siteListimgrule;	}	private String sitePageurl;//分页url	public String getSitePageurl() {		return this.sitePageurl;	}	public void setSitePageurl(String sitePageurl) {		this.sitePageurl=sitePageurl;	}	private String sitePagerule;//分页获取url规则	public String getSitePagerule() {		return this.sitePagerule;	}	public void setSitePagerule(String sitePagerule) {		this.sitePagerule=sitePagerule;	}	private String crawlerRules;//	public String getCrawlerRules() {		return this.crawlerRules;	}	public void setCrawlerRules(String crawlerRules) {		this.crawlerRules=crawlerRules;	}	private String crawlerTimer;//定时爬取时间间隔	public String getCrawlerTimer() {		return this.crawlerTimer;	}	public void setCrawlerTimer(String crawlerTimer) {		this.crawlerTimer=crawlerTimer;	}	private String httpMethod;//	public String getHttpMethod() {		return this.httpMethod;	}	public void setHttpMethod(String httpMethod) {		this.httpMethod=httpMethod;	}	private String httpHeader;//	public String getHttpHeader() {		return this.httpHeader;	}	public void setHttpHeader(String httpHeader) {		this.httpHeader=httpHeader;	}	private Integer num;//循环次数	public Integer getNum() {		return this.num;	}	public void setNum(Integer num) {		this.num=num;	}	private Integer status;//状态 0 未启动 1 启动	public Integer getStatus() {		return this.status;	}	public void setStatus(Integer status) {		this.status=status;	}	private Date lastCrawlerTime;//	public Date getLastCrawlerTime() {		return this.lastCrawlerTime;	}	public void setLastCrawlerTime(Date lastCrawlerTime) {		this.lastCrawlerTime=lastCrawlerTime;	}	private Date lastCrawlerTimeFrom;//	public Date getLastCrawlerTimeFrom() {		return this.lastCrawlerTimeFrom;	}	public void setLastCrawlerTimeFrom(Date lastCrawlerTimeFrom) {		this.lastCrawlerTimeFrom=lastCrawlerTimeFrom;	}	private Date lastCrawlerTimeTo;//	public Date getLastCrawlerTimeTo() {		return this.lastCrawlerTimeTo;	}	public void setLastCrawlerTimeTo(Date lastCrawlerTimeTo) {		this.lastCrawlerTimeTo=lastCrawlerTimeTo;	}	private Integer dr;//	public Integer getDr() {		return this.dr;	}	public void setDr(Integer dr) {		this.dr=dr;	}	private Integer createBy;//	public Integer getCreateBy() {		return this.createBy;	}	public void setCreateBy(Integer createBy) {		this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {		return this.createDt;	}	public void setCreateDt(Date createDt) {		this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {		return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {		this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {		return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {		this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {		return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {		this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {		return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {		this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {		return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {		this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {		return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {		this.modifyDtTo=modifyDtTo;	}	public void fixup(){	}	private boolean perview;	public boolean isPerview() {		return perview;	}	public void setPerview(boolean perview) {		this.perview = perview;	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.siteName!=null)			ht.put("siteName",this.siteName);		if(this.siteUrl!=null)			ht.put("siteUrl",this.siteUrl);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.areaName!=null)			ht.put("areaName",this.areaName);		if(this.siteCategory!=null)			ht.put("siteCategory",this.siteCategory);		if(this.firstCategoryId!=null)			ht.put("firstCategoryId",this.firstCategoryId);		if(this.firstCategoryNames!=null)			ht.put("firstCategoryNames",this.firstCategoryNames);		if(this.secCategoryIds!=null)			ht.put("secCategoryIds",this.secCategoryIds);		if(this.secCategoryNames!=null)			ht.put("secCategoryNames",this.secCategoryNames);		if(this.siteCode!=null)			ht.put("siteCode",this.siteCode);		if(this.siteLevel!=null)			ht.put("siteLevel",this.siteLevel);		if(this.sleepTime!=null)			ht.put("sleepTime",this.sleepTime);		if(this.siteListurl!=null)			ht.put("siteListurl",this.siteListurl);		if(this.siteListrule!=null)			ht.put("siteListrule",this.siteListrule);		if(this.siteArule!=null)			ht.put("siteArule",this.siteArule);		if(this.siteListimgrule!=null)			ht.put("siteListimgrule",this.siteListimgrule);		if(this.sitePageurl!=null)			ht.put("sitePageurl",this.sitePageurl);		if(this.sitePagerule!=null)			ht.put("sitePagerule",this.sitePagerule);		if(this.crawlerRules!=null)			ht.put("crawlerRules",this.crawlerRules);		if(this.crawlerTimer!=null)			ht.put("crawlerTimer",this.crawlerTimer);		if(this.httpMethod!=null)			ht.put("httpMethod",this.httpMethod);		if(this.httpHeader!=null)			ht.put("httpHeader",this.httpHeader);		if(this.num!=null)			ht.put("num",this.num);		if(this.status!=null)			ht.put("status",this.status);		if(this.lastCrawlerTime!=null)			ht.put("lastCrawlerTime",this.lastCrawlerTime);		if(this.lastCrawlerTimeFrom!=null)			ht.put("lastCrawlerTimeFrom",this.lastCrawlerTimeFrom);		if(this.lastCrawlerTimeTo!=null)			ht.put("lastCrawlerTimeTo",this.lastCrawlerTimeTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		return ht;	}	public HashMap<String,String> toStringMap() {		SimpleDateFormat sdf_der = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		HashMap<String,String> ht = new HashMap<String,String>();		if(this.siteName!=null)			ht.put("siteName",this.siteName);		if(this.siteUrl!=null)			ht.put("siteUrl",this.siteUrl);		if(this.areaId!=null)			ht.put("areaId",this.areaId+"");		if(this.areaName!=null)			ht.put("areaName",this.areaName);		if(this.siteCategory!=null)			ht.put("siteCategory",this.siteCategory);		if(this.firstCategoryId!=null)			ht.put("firstCategoryId",this.firstCategoryId+"");		if(this.firstCategoryNames!=null)			ht.put("firstCategoryNames",this.firstCategoryNames);		if(this.secCategoryIds!=null)			ht.put("secCategoryIds",this.secCategoryIds);		if(this.secCategoryNames!=null)			ht.put("secCategoryNames",this.secCategoryNames);		if(this.siteListurl!=null)			ht.put("siteListurl",this.siteListurl);		if(this.lastCrawlerTime!=null)			ht.put("lastCrawlerTime",sdf_der.format(this.lastCrawlerTime));//		if(this.id!=null)//			ht.put("id",this.id+"");//		if(this.siteCode!=null)//			ht.put("siteCode",this.siteCode);//		if(this.siteLevel!=null)//			ht.put("siteLevel",this.siteLevel+"");//		if(this.sleepTime!=null)//			ht.put("sleepTime",this.sleepTime+"");//		if(this.siteListrule!=null)//			ht.put("siteListrule",this.siteListrule);//		if(this.sitePageurl!=null)//			ht.put("sitePageurl",this.sitePageurl);//		if(this.sitePagerule!=null)//			ht.put("sitePagerule",this.sitePagerule);//		if(this.crawlerRules!=null)//			ht.put("crawlerRules",this.crawlerRules);//		if(this.crawlerTimer!=null)//			ht.put("crawlerTimer",this.crawlerTimer);//		if(this.num!=null)//			ht.put("num",this.num+"");//		if(this.status!=null)//			ht.put("status",this.status+"");		return ht;	}	//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2017/02/23 17:03:13}