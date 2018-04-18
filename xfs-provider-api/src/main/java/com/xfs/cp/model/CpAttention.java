package com.xfs.cp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * 
 * @class  comments:   
 * @project  name  : xfs-model
 * @author   name  : wangchao 
 * @create   time  : 2016年9月8日 上午11:04:19  
 * @class    name  : com.xfs.common.model.CpAttention
 * @modify   list  : 修改时间和内容   
 * 2016年9月8日 上午11:04:19 wangchao 创建
 *
 */
public class CpAttention implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer attentionSpidBy;//被关注服务商id	public Integer getAttentionSpidBy() {	    return this.attentionSpidBy;	}	public void setAttentionSpidBy(Integer attentionSpidBy) {	    this.attentionSpidBy=attentionSpidBy;	}	private Integer attentionSpid;//关注服务商ID	public Integer getAttentionSpid() {	    return this.attentionSpid;	}	public void setAttentionSpid(Integer attentionSpid) {	    this.attentionSpid=attentionSpid;	}	private Integer createBy;//创建人	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//创建时间	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//创建时间	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//创建时间	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//更新人	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//更新时间	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//更新时间	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//更新时间	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//逻辑删除标记位0:未删除1:已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.attentionSpidBy!=null)			ht.put("attentionSpidBy",this.attentionSpidBy);		if(this.attentionSpid!=null)			ht.put("attentionSpid",this.attentionSpid);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/09/08 10:55:17		
}

