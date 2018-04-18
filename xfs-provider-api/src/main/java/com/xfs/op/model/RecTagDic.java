package com.xfs.op.model;import java.util.*;import java.lang.Integer;import java.lang.String;/** * RecTagDic * @author:yangfw@xinfushe.com * @date:2017/02/22 10:59:03	 */public class RecTagDic implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private String tagName;//用户识别到的标签 即:子标签	public String getTagName() {	    return this.tagName;	}	public void setTagName(String tagName) {	    this.tagName=tagName;	}	private String tagNameEq;//用户识别到的标签 即:子标签	public String getTagNameEq() {	    return this.tagNameEq;	}	public void setTagNameEq(String tagNameEq) {	    this.tagNameEq=tagNameEq;	}	private String type;//HEAD:分类标题,INTEREST:兴趣，MIX:混合	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	private String typeEq;//HEAD:分类标题,INTEREST:兴趣，MIX:混合	public String getTypeEq() {	    return this.typeEq;	}	public void setTypeEq(String typeEq) {	    this.typeEq=typeEq;	}	private Integer parentId;//	public Integer getParentId() {	    return this.parentId;	}	public void setParentId(Integer parentId) {	    this.parentId=parentId;	}	private String parentName;//父级标签名称	public String getParentName() {	    return this.parentName;	}	public void setParentName(String parentName) {	    this.parentName=parentName;	}	private String parentNameEq;//父级标签名称	public String getParentNameEq() {	    return this.parentNameEq;	}	public void setParentNameEq(String parentNameEq) {	    this.parentNameEq=parentNameEq;	}	private String path;//	public String getPath() {	    return this.path;	}	public void setPath(String path) {	    this.path=path;	}	private String pathEq;//	public String getPathEq() {	    return this.pathEq;	}	public void setPathEq(String pathEq) {	    this.pathEq=pathEq;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.tagName!=null)			ht.put("tagName",this.tagName);		if(this.tagNameEq!=null)			ht.put("tagNameEq",this.tagNameEq);		if(this.type!=null)			ht.put("type",this.type);		if(this.typeEq!=null)			ht.put("typeEq",this.typeEq);		if(this.parentId!=null)			ht.put("parentId",this.parentId);		if(this.parentName!=null)			ht.put("parentName",this.parentName);		if(this.parentNameEq!=null)			ht.put("parentNameEq",this.parentNameEq);		if(this.path!=null)			ht.put("path",this.path);		if(this.pathEq!=null)			ht.put("pathEq",this.pathEq);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写    private String isNew ;    public String getIsNew() {        return isNew;    }    public void setIsNew(String isNew) {        this.isNew = isNew;    }    private Integer orderBy ;    public Integer getOrderBy() {        return orderBy;    }    public void setOrderBy(Integer orderBy) {        this.orderBy = orderBy;    }}