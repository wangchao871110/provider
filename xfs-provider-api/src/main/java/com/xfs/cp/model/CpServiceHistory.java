package com.xfs.cp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhao on 2017/1/5.
 */
public class CpServiceHistory  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;//
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id=id;
    }
    private Integer years;//年份
    public Integer getYears() {
        return this.years;
    }
    public void setYears(Integer years) {
        this.years=years;
    }
    private Integer month;//月份
    public Integer getMonth() {
        return this.month;
    }
    public void setMonth(Integer month) {
        this.month=month;
    }
    private Integer cpServiceId;//协作服务商ID
    public Integer getCpServiceId() {
        return this.cpServiceId;
    }
    public void setCpServiceId(Integer cpServiceId) {
        this.cpServiceId=cpServiceId;
    }
    private Integer saasSpId;//主表服务商ID
    public Integer getSaasSpId() {
        return this.saasSpId;
    }
    public void setSaasSpId(Integer saasSpId) {
        this.saasSpId=saasSpId;
    }
    private String name;//服务商名称
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    private String cpAddress;//公司详细地址
    public String getCpAddress() {
        return this.cpAddress;
    }
    public void setCpAddress(String cpAddress) {
        this.cpAddress=cpAddress;
    }
    private String shortName;//服务商简称
    public String getShortName() {
        return this.shortName;
    }
    public void setShortName(String shortName) {
        this.shortName=shortName;
    }
    private Integer empNum;//员工数量
    public Integer getEmpNum() {
        return this.empNum;
    }
    public void setEmpNum(Integer empNum) {
        this.empNum=empNum;
    }
    private Integer serviceNum;//服务人数
    public Integer getServiceNum() {
        return this.serviceNum;
    }
    public void setServiceNum(Integer serviceNum) {
        this.serviceNum=serviceNum;
    }
    private String telephone;//座机号
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone=telephone;
    }
    private String mobile;//联系人手机号
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile=mobile;
    }
    private String email;//邮箱
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    private String fax;//公司传真
    public String getFax() {
        return this.fax;
    }
    public void setFax(String fax) {
        this.fax=fax;
    }
    private Integer isca;//企业是否认证 0：未认证 1：认证
    public Integer getIsca() {
        return this.isca;
    }
    public void setIsca(Integer isca) {
        this.isca=isca;
    }
    private Integer isdeposit;//企业是否交保证金 0：未交 1：已交
    public Integer getIsdeposit() {
        return this.isdeposit;
    }
    public void setIsdeposit(Integer isdeposit) {
        this.isdeposit=isdeposit;
    }
    private Integer allRanking;//全国排名
    public Integer getAllRanking() {
        return this.allRanking;
    }
    public void setAllRanking(Integer allRanking) {
        this.allRanking=allRanking;
    }
    private Integer cityRanking;//市排名
    public Integer getCityRanking() {
        return this.cityRanking;
    }
    public void setCityRanking(Integer cityRanking) {
        this.cityRanking=cityRanking;
    }
    private Double devIndex;//发展指数
    public Double getDevIndex() {
        return this.devIndex;
    }
    public void setDevIndex(Double devIndex) {
        this.devIndex=devIndex;
    }
    private Double devIndexOld;//上一次的发展指数
    public Double getDevIndexOld() {
        return this.devIndexOld;
    }
    public void setDevIndexOld(Double devIndexOld) {
        this.devIndexOld=devIndexOld;
    }
    private Integer browseNum;//浏览次数
    public Integer getBrowseNum() {
        return this.browseNum;
    }
    public void setBrowseNum(Integer browseNum) {
        this.browseNum=browseNum;
    }
    private Integer attentionNum;//关注人数
    public Integer getAttentionNum() {
        return this.attentionNum;
    }
    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum=attentionNum;
    }
    private Integer industryNum;//同业认证数
    public Integer getIndustryNum() {
        return this.industryNum;
    }
    public void setIndustryNum(Integer industryNum) {
        this.industryNum=industryNum;
    }
    private Integer serviceLogo;//服务商logo
    public Integer getServiceLogo() {
        return this.serviceLogo;
    }
    public void setServiceLogo(Integer serviceLogo) {
        this.serviceLogo=serviceLogo;
    }
    private String description;//服务机构介绍
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description=description;
    }
    private String serviceArea;//服务范围ID
    public String getServiceArea() {
        return this.serviceArea;
    }
    public void setServiceArea(String serviceArea) {
        this.serviceArea=serviceArea;
    }
    private String serviceAreaName;//服务范围名称
    public String getServiceAreaName() {
        return this.serviceAreaName;
    }
    public void setServiceAreaName(String serviceAreaName) {
        this.serviceAreaName=serviceAreaName;
    }
    private String serviceAreaNum;//服务城市个数
    public String getServiceAreaNum() {
        return this.serviceAreaNum;
    }
    public void setServiceAreaNum(String serviceAreaNum) {
        this.serviceAreaNum=serviceAreaNum;
    }
    private String advantage;//服务优势
    public String getAdvantage() {
        return this.advantage;
    }
    public void setAdvantage(String advantage) {
        this.advantage=advantage;
    }
    private String customerType;//服务客户类型
    public String getCustomerType() {
        return this.customerType;
    }
    public void setCustomerType(String customerType) {
        this.customerType=customerType;
    }
    private String certificate;//证书ID
    public String getCertificate() {
        return this.certificate;
    }
    public void setCertificate(String certificate) {
        this.certificate=certificate;
    }
    private String ceoName;//总经理名称
    public String getCeoName() {
        return this.ceoName;
    }
    public void setCeoName(String ceoName) {
        this.ceoName=ceoName;
    }
    private Integer ceoLogo;//头像
    public Integer getCeoLogo() {
        return this.ceoLogo;
    }
    public void setCeoLogo(Integer ceoLogo) {
        this.ceoLogo=ceoLogo;
    }
    private String ceoDescription;//简介
    public String getCeoDescription() {
        return this.ceoDescription;
    }
    public void setCeoDescription(String ceoDescription) {
        this.ceoDescription=ceoDescription;
    }
    private String officeImg;//办公环境照片
    public String getOfficeImg() {
        return this.officeImg;
    }
    public void setOfficeImg(String officeImg) {
        this.officeImg=officeImg;
    }
    private Integer createBy;//创建人
    public Integer getCreateBy() {
        return this.createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy=createBy;
    }
    private Date createDt;//创建时间
    public Date getCreateDt() {
        return this.createDt;
    }
    public void setCreateDt(Date createDt) {
        this.createDt=createDt;
    }
    private Date createDtFrom;//创建时间
    public Date getCreateDtFrom() {
        return this.createDtFrom;
    }
    public void setCreateDtFrom(Date createDtFrom) {
        this.createDtFrom=createDtFrom;
    }
    private Date createDtTo;//创建时间
    public Date getCreateDtTo() {
        return this.createDtTo;
    }
    public void setCreateDtTo(Date createDtTo) {
        this.createDtTo=createDtTo;
    }
    private Integer modifyBy;//更新人
    public Integer getModifyBy() {
        return this.modifyBy;
    }
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy=modifyBy;
    }
    private Date modifyDt;//更新时间
    public Date getModifyDt() {
        return this.modifyDt;
    }
    public void setModifyDt(Date modifyDt) {
        this.modifyDt=modifyDt;
    }
    private Date modifyDtFrom;//更新时间
    public Date getModifyDtFrom() {
        return this.modifyDtFrom;
    }
    public void setModifyDtFrom(Date modifyDtFrom) {
        this.modifyDtFrom=modifyDtFrom;
    }
    private Date modifyDtTo;//更新时间
    public Date getModifyDtTo() {
        return this.modifyDtTo;
    }
    public void setModifyDtTo(Date modifyDtTo) {
        this.modifyDtTo=modifyDtTo;
    }
    private Integer dr;//逻辑删除标记位0:未删除1:已删除
    public Integer getDr() {
        return this.dr;
    }
    public void setDr(Integer dr) {
        this.dr=dr;
    }
    private String registeredGov;//登记机关
    public String getRegisteredGov() {
        return this.registeredGov;
    }
    public void setRegisteredGov(String registeredGov) {
        this.registeredGov=registeredGov;
    }
    private Date establishedTime;//成立时间
    public Date getEstablishedTime() {
        return this.establishedTime;
    }
    public void setEstablishedTime(Date establishedTime) {
        this.establishedTime=establishedTime;
    }
    private Date establishedTimeFrom;//成立时间
    public Date getEstablishedTimeFrom() {
        return this.establishedTimeFrom;
    }
    public void setEstablishedTimeFrom(Date establishedTimeFrom) {
        this.establishedTimeFrom=establishedTimeFrom;
    }
    private Date establishedTimeTo;//成立时间
    public Date getEstablishedTimeTo() {
        return this.establishedTimeTo;
    }
    public void setEstablishedTimeTo(Date establishedTimeTo) {
        this.establishedTimeTo=establishedTimeTo;
    }
    private String registeredCapital;//注册资本
    public String getRegisteredCapital() {
        return this.registeredCapital;
    }
    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital=registeredCapital;
    }
    private String busiLicenseNum;//统一社会信用代码
    public String getBusiLicenseNum() {
        return this.busiLicenseNum;
    }
    public void setBusiLicenseNum(String busiLicenseNum) {
        this.busiLicenseNum=busiLicenseNum;
    }
    private String orgCode;//组织机构代码
    public String getOrgCode() {
        return this.orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode=orgCode;
    }
    private String comanyType;//公司类型
    public String getComanyType() {
        return this.comanyType;
    }
    public void setComanyType(String comanyType) {
        this.comanyType=comanyType;
    }
    private String legalPsn;//法定代表人
    public String getLegalPsn() {
        return this.legalPsn;
    }
    public void setLegalPsn(String legalPsn) {
        this.legalPsn=legalPsn;
    }
    private String busiScope;//经营范围
    public String getBusiScope() {
        return this.busiScope;
    }
    public void setBusiScope(String busiScope) {
        this.busiScope=busiScope;
    }
    private String busiStasus;//经营状态
    public String getBusiStasus() {
        return this.busiStasus;
    }
    public void setBusiStasus(String busiStasus) {
        this.busiStasus=busiStasus;
    }
    private String weburl;//公司网址
    public String getWeburl() {
        return this.weburl;
    }
    public void setWeburl(String weburl) {
        this.weburl=weburl;
    }
    private String customerNum;//法大大客户编号
    public String getCustomerNum() {
        return this.customerNum;
    }
    public void setCustomerNum(String customerNum) {
        this.customerNum=customerNum;
    }
    private String partners;//股东结构
    public String getPartners() {
        return this.partners;
    }
    public void setPartners(String partners) {
        this.partners=partners;
    }
    private String addressesRegister;//注册地址
    public String getAddressesRegister() {
        return this.addressesRegister;
    }
    public void setAddressesRegister(String addressesRegister) {
        this.addressesRegister=addressesRegister;
    }
    private String startDate;//成立日期
    public String getStartDate() {
        return this.startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate=startDate;
    }
    private String checkDate;//发照日期
    public String getCheckDate() {
        return this.checkDate;
    }
    public void setCheckDate(String checkDate) {
        this.checkDate=checkDate;
    }
    private String addressDetail;//单位详细联系地址
    public String getAddressDetail() {
        return this.addressDetail;
    }
    public void setAddressDetail(String addressDetail) {
        this.addressDetail=addressDetail;
    }
    private Integer dispatchCa;//劳务派遣证书
    public Integer getDispatchCa() {
        return this.dispatchCa;
    }
    public void setDispatchCa(Integer dispatchCa) {
        this.dispatchCa=dispatchCa;
    }
    private Integer hrCa;//人力资源证书
    public Integer getHrCa() {
        return this.hrCa;
    }
    public void setHrCa(Integer hrCa) {
        this.hrCa=hrCa;
    }
    private Integer busiLicenseFile;//资质证书ID
    public Integer getBusiLicenseFile() {
        return this.busiLicenseFile;
    }
    public void setBusiLicenseFile(Integer busiLicenseFile) {
        this.busiLicenseFile=busiLicenseFile;
    }
    private Integer isfirst;//是否第一次进入个人中心（新手引导）0：还没有进入过个人中心，1：已经进入过个人中心
    public Integer getIsfirst() {
        return this.isfirst;
    }
    public void setIsfirst(Integer isfirst) {
        this.isfirst=isfirst;
    }
    private Integer isuse;//是否可用0：不可用，1：可用
    public Integer getIsuse() {
        return this.isuse;
    }
    public void setIsuse(Integer isuse) {
        this.isuse=isuse;
    }
    private String stopContent;//停用原因
    public String getStopContent() {
        return this.stopContent;
    }
    public void setStopContent(String stopContent) {
        this.stopContent=stopContent;
    }
    private Integer isrob;//是否可抢单0：不可以，1：可以
    public Integer getIsrob() {
        return this.isrob;
    }
    public void setIsrob(Integer isrob) {
        this.isrob=isrob;
    }
    private Double customIndex;//自定义指数
    public Double getCustomIndex() {
        return this.customIndex;
    }
    public void setCustomIndex(Double customIndex) {
        this.customIndex=customIndex;
    }
    public void fixup(){
    }
    public HashMap<String,Object> toHashMap() {
        HashMap<String,Object> ht = new HashMap<String,Object>();
        if(this.id!=null)
            ht.put("id",this.id);
        if(this.years!=null)
            ht.put("years",this.years);
        if(this.month!=null)
            ht.put("month",this.month);
        if(this.cpServiceId!=null)
            ht.put("cpServiceId",this.cpServiceId);
        if(this.saasSpId!=null)
            ht.put("saasSpId",this.saasSpId);
        if(this.name!=null)
            ht.put("name",this.name);
        if(this.cpAddress!=null)
            ht.put("cpAddress",this.cpAddress);
        if(this.shortName!=null)
            ht.put("shortName",this.shortName);
        if(this.empNum!=null)
            ht.put("empNum",this.empNum);
        if(this.serviceNum!=null)
            ht.put("serviceNum",this.serviceNum);
        if(this.telephone!=null)
            ht.put("telephone",this.telephone);
        if(this.mobile!=null)
            ht.put("mobile",this.mobile);
        if(this.email!=null)
            ht.put("email",this.email);
        if(this.fax!=null)
            ht.put("fax",this.fax);
        if(this.isca!=null)
            ht.put("isca",this.isca);
        if(this.isdeposit!=null)
            ht.put("isdeposit",this.isdeposit);
        if(this.allRanking!=null)
            ht.put("allRanking",this.allRanking);
        if(this.cityRanking!=null)
            ht.put("cityRanking",this.cityRanking);
        if(this.devIndex!=null)
            ht.put("devIndex",this.devIndex);
        if(this.devIndexOld!=null)
            ht.put("devIndexOld",this.devIndexOld);
        if(this.browseNum!=null)
            ht.put("browseNum",this.browseNum);
        if(this.attentionNum!=null)
            ht.put("attentionNum",this.attentionNum);
        if(this.industryNum!=null)
            ht.put("industryNum",this.industryNum);
        if(this.serviceLogo!=null)
            ht.put("serviceLogo",this.serviceLogo);
        if(this.description!=null)
            ht.put("description",this.description);
        if(this.serviceArea!=null)
            ht.put("serviceArea",this.serviceArea);
        if(this.serviceAreaName!=null)
            ht.put("serviceAreaName",this.serviceAreaName);
        if(this.serviceAreaNum!=null)
            ht.put("serviceAreaNum",this.serviceAreaNum);
        if(this.advantage!=null)
            ht.put("advantage",this.advantage);
        if(this.customerType!=null)
            ht.put("customerType",this.customerType);
        if(this.certificate!=null)
            ht.put("certificate",this.certificate);
        if(this.ceoName!=null)
            ht.put("ceoName",this.ceoName);
        if(this.ceoLogo!=null)
            ht.put("ceoLogo",this.ceoLogo);
        if(this.ceoDescription!=null)
            ht.put("ceoDescription",this.ceoDescription);
        if(this.officeImg!=null)
            ht.put("officeImg",this.officeImg);
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
        if(this.registeredGov!=null)
            ht.put("registeredGov",this.registeredGov);
        if(this.establishedTime!=null)
            ht.put("establishedTime",this.establishedTime);
        if(this.establishedTimeFrom!=null)
            ht.put("establishedTimeFrom",this.establishedTimeFrom);
        if(this.establishedTimeTo!=null)
            ht.put("establishedTimeTo",this.establishedTimeTo);
        if(this.registeredCapital!=null)
            ht.put("registeredCapital",this.registeredCapital);
        if(this.busiLicenseNum!=null)
            ht.put("busiLicenseNum",this.busiLicenseNum);
        if(this.orgCode!=null)
            ht.put("orgCode",this.orgCode);
        if(this.comanyType!=null)
            ht.put("comanyType",this.comanyType);
        if(this.legalPsn!=null)
            ht.put("legalPsn",this.legalPsn);
        if(this.busiScope!=null)
            ht.put("busiScope",this.busiScope);
        if(this.busiStasus!=null)
            ht.put("busiStasus",this.busiStasus);
        if(this.weburl!=null)
            ht.put("weburl",this.weburl);
        if(this.customerNum!=null)
            ht.put("customerNum",this.customerNum);
        if(this.partners!=null)
            ht.put("partners",this.partners);
        if(this.addressesRegister!=null)
            ht.put("addressesRegister",this.addressesRegister);
        if(this.startDate!=null)
            ht.put("startDate",this.startDate);
        if(this.checkDate!=null)
            ht.put("checkDate",this.checkDate);
        if(this.addressDetail!=null)
            ht.put("addressDetail",this.addressDetail);
        if(this.dispatchCa!=null)
            ht.put("dispatchCa",this.dispatchCa);
        if(this.hrCa!=null)
            ht.put("hrCa",this.hrCa);
        if(this.busiLicenseFile!=null)
            ht.put("busiLicenseFile",this.busiLicenseFile);
        if(this.isfirst!=null)
            ht.put("isfirst",this.isfirst);
        if(this.isuse!=null)
            ht.put("isuse",this.isuse);
        if(this.stopContent!=null)
            ht.put("stopContent",this.stopContent);
        if(this.isrob!=null)
            ht.put("isrob",this.isrob);
        if(this.customIndex!=null)
            ht.put("customIndex",this.customIndex);
        if(this.thisMonth!=null)
            ht.put("thisMonth",this.thisMonth);
        return ht;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 14:51:32



    private String staDate;// 查询开始日期
    private String endDate;// 查询结束日期
    public String getStaDate() {
        return staDate;
    }
    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Double price;// 交易总额
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String categoryId;// 主营业务
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String orderBy;// 排序字段
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    private String checkDateEq;//发照日期
    public String getCheckDateEq() {
        return this.checkDateEq;
    }
    public void setCheckDateEq(String checkDateEq) {
        this.checkDateEq=checkDateEq;
    }
    private String addressDetailEq;//单位详细联系地址
    public String getAddressDetailEq() {
        return this.addressDetailEq;
    }
    public void setAddressDetailEq(String addressDetailEq) {
        this.addressDetailEq=addressDetailEq;
    }

    private String startTime;// 开始时间
    private String endTime;// 结束时间
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    private String categoryPinyin;
    public String getCategoryPinyin() {
        return categoryPinyin;
    }
    public void setCategoryPinyin(String categoryPinyin) {
        this.categoryPinyin = categoryPinyin;
    }
    private Integer orderNumber;//订单数量
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    private String areaPinyin;
    public String getAreaPinyin() {
        return areaPinyin;
    }
    public void setAreaPinyin(String areaPinyin) {
        this.areaPinyin = areaPinyin;
    }

    public String getIsOnsiteAudit() {
        return isOnsiteAudit;
    }

    public void setIsOnsiteAudit(String isOnsiteAudit) {
        this.isOnsiteAudit = isOnsiteAudit;
    }

    public String getIsFundSupervision() {
        return isFundSupervision;
    }

    public void setIsFundSupervision(String isFundSupervision) {
        this.isFundSupervision = isFundSupervision;
    }

    public BigDecimal getRiskMargin() {
        return riskMargin;
    }

    public void setRiskMargin(BigDecimal riskMargin) {
        this.riskMargin = riskMargin;
    }

    public Integer getRiskMarginStar() {
        return riskMarginStar;
    }

    public void setRiskMarginStar(Integer riskMarginStar) {
        this.riskMarginStar = riskMarginStar;
    }

    public String getIsIso() {
        return isIso;
    }

    public void setIsIso(String isIso) {
        this.isIso = isIso;
    }

    public Integer getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(Integer overallEvaluation) {
        this.overallEvaluation = overallEvaluation;
    }

    private String isOnsiteAudit;//是否现场审核
    private String isFundSupervision;//是否资金监管
    private BigDecimal riskMargin;//风险保证金
    private Integer riskMarginStar;//保证金星级
    private String isIso;//是否具有ISO900
    private Integer overallEvaluation;//平台综合评价（分）
    private Integer thisMonth;//当前月分
	public Integer getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(Integer thisMonth) {
		this.thisMonth = thisMonth;
	}
    
}
