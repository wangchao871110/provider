package com.xfs.cp.model;

import java.util.HashMap;


/**
 * CpArea
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/10 16:19:46	
 */
public class CpArea implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private String id;//ID	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	private String idEq;//ID	public String getIdEq() {	    return this.idEq;	}	public void setIdEq(String idEq) {	    this.idEq=idEq;	}	private String name;//区域名称	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	private String nameEq;//区域名称	public String getNameEq() {	    return this.nameEq;	}	public void setNameEq(String nameEq) {	    this.nameEq=nameEq;	}	private String parentId;//父节点	public String getParentId() {	    return this.parentId;	}	public void setParentId(String parentId) {	    this.parentId=parentId;	}	private String parentIdEq;//父节点	public String getParentIdEq() {	    return this.parentIdEq;	}	public void setParentIdEq(String parentIdEq) {	    this.parentIdEq=parentIdEq;	}	private Integer dr;//逻辑删除标记位0:未删除1:已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.idEq!=null)			ht.put("idEq",this.idEq);		if(this.name!=null)			ht.put("name",this.name);		if(this.nameEq!=null)			ht.put("nameEq",this.nameEq);		if(this.parentId!=null)			ht.put("parentId",this.parentId);		if(this.parentIdEq!=null)			ht.put("parentIdEq",this.parentIdEq);		if(this.dr!=null)			ht.put("dr",this.dr);
		if(this.pinyin!=null)
			ht.put("pinyin",this.pinyin);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/09/10 16:19:47	
	private String pinyin;
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
}

