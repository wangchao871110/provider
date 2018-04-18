package com.xfs.ts.model;

import java.util.Date;
import java.util.HashMap;


/**
 * SpsTsAutoPage
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/20 11:03:59	
 */
public class SpsTsAutoPage implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private String name;//	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	private String nameEq;//	public String getNameEq() {	    return this.nameEq;	}	public void setNameEq(String nameEq) {	    this.nameEq=nameEq;	}	private String pageUrl;//	public String getPageUrl() {	    return this.pageUrl;	}	public void setPageUrl(String pageUrl) {	    this.pageUrl=pageUrl;	}	private String pageUrlEq;//	public String getPageUrlEq() {	    return this.pageUrlEq;	}	public void setPageUrlEq(String pageUrlEq) {	    this.pageUrlEq=pageUrlEq;	}	private String city;//	public String getCity() {	    return this.city;	}	public void setCity(String city) {	    this.city=city;	}	private String cityEq;//	public String getCityEq() {	    return this.cityEq;	}	public void setCityEq(String cityEq) {	    this.cityEq=cityEq;	}	private Integer btype;//	public Integer getBtype() {	    return this.btype;	}	public void setBtype(Integer btype) {	    this.btype=btype;	}	private String pageType;//	public String getPageType() {	    return this.pageType;	}	public void setPageType(String pageType) {	    this.pageType=pageType;	}	private String pageTypeEq;//	public String getPageTypeEq() {	    return this.pageTypeEq;	}	public void setPageTypeEq(String pageTypeEq) {	    this.pageTypeEq=pageTypeEq;	}	private String code;//	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	private String codeEq;//	public String getCodeEq() {	    return this.codeEq;	}	public void setCodeEq(String codeEq) {	    this.codeEq=codeEq;	}	private String className;//	public String getClassName() {	    return this.className;	}	public void setClassName(String className) {	    this.className=className;	}	private String classNameEq;//	public String getClassNameEq() {	    return this.classNameEq;	}	public void setClassNameEq(String classNameEq) {	    this.classNameEq=classNameEq;	}	private String classUrl;//	public String getClassUrl() {	    return this.classUrl;	}	public void setClassUrl(String classUrl) {	    this.classUrl=classUrl;	}	private String classUrlEq;//	public String getClassUrlEq() {	    return this.classUrlEq;	}	public void setClassUrlEq(String classUrlEq) {	    this.classUrlEq=classUrlEq;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.name!=null)			ht.put("name",this.name);		if(this.nameEq!=null)			ht.put("nameEq",this.nameEq);		if(this.pageUrl!=null)			ht.put("pageUrl",this.pageUrl);		if(this.pageUrlEq!=null)			ht.put("pageUrlEq",this.pageUrlEq);		if(this.city!=null)			ht.put("city",this.city);		if(this.cityEq!=null)			ht.put("cityEq",this.cityEq);		if(this.btype!=null)			ht.put("btype",this.btype);		if(this.pageType!=null)			ht.put("pageType",this.pageType);		if(this.pageTypeEq!=null)			ht.put("pageTypeEq",this.pageTypeEq);		if(this.code!=null)			ht.put("code",this.code);		if(this.codeEq!=null)			ht.put("codeEq",this.codeEq);		if(this.className!=null)			ht.put("className",this.className);		if(this.classNameEq!=null)			ht.put("classNameEq",this.classNameEq);		if(this.classUrl!=null)			ht.put("classUrl",this.classUrl);		if(this.classUrlEq!=null)			ht.put("classUrlEq",this.classUrlEq);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/05/20 11:04:03		
}

