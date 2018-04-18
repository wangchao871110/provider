package com.xfs.op.model;import java.math.BigDecimal;import java.util.*;import java.lang.Integer;import java.lang.String;import java.util.Date;/** * OpHrplusCalcConfig * @author:yangfw@xinfushe.com * @date:2017/04/06 16:45:09	 */public class OpHrplusCalcConfig implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//id	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private String key;//键	public String getKey() {	    return this.key;	}	public void setKey(String key) {	    this.key=key;	}	private String keyEq;//键	public String getKeyEq() {	    return this.keyEq;	}	public void setKeyEq(String keyEq) {	    this.keyEq=keyEq;	}	private BigDecimal value;//值	public BigDecimal getValue() {	    return this.value;	}	public void setValue(BigDecimal value) {	    this.value=value;	}	private String describe;//描述	public String getDescribe() {	    return this.describe;	}	public void setDescribe(String describe) {	    this.describe=describe;	}	private String describeEq;//描述	public String getDescribeEq() {	    return this.describeEq;	}	public void setDescribeEq(String describeEq) {	    this.describeEq=describeEq;	}	private Date createDt;//创建时间	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//创建时间	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//创建时间	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer createBy;//创建人	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date modifyDt;//修改时间	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//修改时间	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//修改时间	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer modifyBy;//修改人	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Integer dr;//是否删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.key!=null)			ht.put("key",this.key);		if(this.keyEq!=null)			ht.put("keyEq",this.keyEq);		if(this.value!=null)			ht.put("value",this.value);		if(this.describe!=null)			ht.put("describe",this.describe);		if(this.describeEq!=null)			ht.put("describeEq",this.describeEq);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2017/04/06 16:45:10		}