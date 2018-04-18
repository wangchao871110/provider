package com.xfs.op.model;import java.util.*;import java.lang.Integer;import java.lang.String;import java.util.Date;/** * HeadLineHydsContent * @author:yangfw@xinfushe.com * @date:2017/05/02 16:19:11	 */public class HeadLineHydsContent implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//id	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private String title;//标题	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	private String titleEq;//标题	public String getTitleEq() {	    return this.titleEq;	}	public void setTitleEq(String titleEq) {	    this.titleEq=titleEq;	}	private Date startDt;//开始时间	public Date getStartDt() {	    return this.startDt;	}	public void setStartDt(Date startDt) {	    this.startDt=startDt;	}	private Date startDtFrom;//开始时间	public Date getStartDtFrom() {	    return this.startDtFrom;	}	public void setStartDtFrom(Date startDtFrom) {	    this.startDtFrom=startDtFrom;	}	private Date startDtTo;//开始时间	public Date getStartDtTo() {	    return this.startDtTo;	}	public void setStartDtTo(Date startDtTo) {	    this.startDtTo=startDtTo;	}	private Date endDt;//结束时间	public Date getEndDt() {	    return this.endDt;	}	public void setEndDt(Date endDt) {	    this.endDt=endDt;	}	private Date endDtFrom;//结束时间	public Date getEndDtFrom() {	    return this.endDtFrom;	}	public void setEndDtFrom(Date endDtFrom) {	    this.endDtFrom=endDtFrom;	}	private Date endDtTo;//结束时间	public Date getEndDtTo() {	    return this.endDtTo;	}	public void setEndDtTo(Date endDtTo) {	    this.endDtTo=endDtTo;	}	private Date createDt;//创建时间	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//创建时间	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//创建时间	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Date modifyDt;//修改时间	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//修改时间	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//修改时间	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private String contentUrl;//跳转连接	public String getContentUrl() {	    return this.contentUrl;	}	public void setContentUrl(String contentUrl) {	    this.contentUrl=contentUrl;	}	private String contentUrlEq;//跳转连接	public String getContentUrlEq() {	    return this.contentUrlEq;	}	public void setContentUrlEq(String contentUrlEq) {	    this.contentUrlEq=contentUrlEq;	}	private Integer dr;//0 正常 1 删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private Integer areaId;//城市ID	public Integer getAreaId() {	    return this.areaId;	}	public void setAreaId(Integer areaId) {	    this.areaId=areaId;	}	private Integer creatBy;//创建人	public Integer getCreatBy() {	    return this.creatBy;	}	public void setCreatBy(Integer creatBy) {	    this.creatBy=creatBy;	}	private Integer midifyBy;//修改人	public Integer getMidifyBy() {	    return this.midifyBy;	}	public void setMidifyBy(Integer midifyBy) {	    this.midifyBy=midifyBy;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.title!=null)			ht.put("title",this.title);		if(this.titleEq!=null)			ht.put("titleEq",this.titleEq);		if(this.startDt!=null)			ht.put("startDt",this.startDt);		if(this.startDtFrom!=null)			ht.put("startDtFrom",this.startDtFrom);		if(this.startDtTo!=null)			ht.put("startDtTo",this.startDtTo);		if(this.endDt!=null)			ht.put("endDt",this.endDt);		if(this.endDtFrom!=null)			ht.put("endDtFrom",this.endDtFrom);		if(this.endDtTo!=null)			ht.put("endDtTo",this.endDtTo);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.contentUrl!=null)			ht.put("contentUrl",this.contentUrl);		if(this.contentUrlEq!=null)			ht.put("contentUrlEq",this.contentUrlEq);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.creatBy!=null)			ht.put("creatBy",this.creatBy);		if(this.midifyBy!=null)			ht.put("midifyBy",this.midifyBy);		if(this.titleImg!=null)			ht.put("titleImg",this.titleImg);		return ht;	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2017/05/02 16:19:12    private String titleImg;    private String createName;    private String modifyName;    private String areaName;    private String address;    private Integer type;    private String status;    public String getAddress() {        return address;    }    public void setAddress(String address) {        this.address = address;    }    public Integer getType() {        return type;    }    public void setType(Integer type) {        this.type = type;    }    public String getStatus() {        return status;    }    public void setStatus(String status) {        this.status = status;    }    public String getAreaName() {        return areaName;    }    public void setAreaName(String areaName) {        this.areaName = areaName;    }    public String getTitleImg() {        return titleImg;    }    public void setTitleImg(String titleImg) {        this.titleImg = titleImg;    }    public String getCreateName() {        return createName;    }    public void setCreateName(String createName) {        this.createName = createName;    }    public String getModifyName() {        return modifyName;    }    public void setModifyName(String modifyName) {        this.modifyName = modifyName;    }}