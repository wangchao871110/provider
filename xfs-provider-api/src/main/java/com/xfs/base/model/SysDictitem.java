package com.xfs.base.model;

import java.util.HashMap;

/**
 * SysDictitem
 * @author:zhangyan
 * @date:2015/12/10 17:19:51	
 */
public class SysDictitem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	private String tid;//
	private String tidEq;//
	private Integer dictionary;//
	private String code;//
	private String codeEq;//
	private String name;//
	private String nameEq;//
	private String description;//
	private String descriptionEq;//
	private Integer orderby;//
	private Integer createUser;//
	private String createTime;//
	private String createTimeEq;//

    private Integer areaId;   // add by wangxs  2016.05.25
    private String synonym;
    private String synonymEq;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public String getTid() {
	    return this.tid;
	}
	public void setTid(String tid) {
	    this.tid=tid;
	}
	public String getTidEq() {
	    return this.tidEq;
	}
	public void setTidEq(String tidEq) {
	    this.tidEq=tidEq;
	}
	public Integer getDictionary() {
	    return this.dictionary;
	}
	public void setDictionary(Integer dictionary) {
	    this.dictionary=dictionary;
	}
	public String getCode() {
	    return this.code;
	}
	public void setCode(String code) {
	    this.code=code;
	}
	public String getCodeEq() {
	    return this.codeEq;
	}
	public void setCodeEq(String codeEq) {
	    this.codeEq=codeEq;
	}
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	public String getDescription() {
	    return this.description;
	}
	public void setDescription(String description) {
	    this.description=description;
	}
	public String getDescriptionEq() {
	    return this.descriptionEq;
	}
	public void setDescriptionEq(String descriptionEq) {
	    this.descriptionEq=descriptionEq;
	}
	public Integer getOrderby() {
	    return this.orderby;
	}
	public void setOrderby(Integer orderby) {
	    this.orderby=orderby;
	}
	public Integer getCreateUser() {
	    return this.createUser;
	}
	public void setCreateUser(Integer createUser) {
	    this.createUser=createUser;
	}
	public String getCreateTime() {
	    return this.createTime;
	}
	public void setCreateTime(String createTime) {
	    this.createTime=createTime;
	}
	public String getCreateTimeEq() {
	    return this.createTimeEq;
	}
	public void setCreateTimeEq(String createTimeEq) {
	    this.createTimeEq=createTimeEq;
	}
	public String getSynonym() {
		return synonym;
	}
	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}
	public String getSynonymEq() {
		return synonymEq;
	}
	public void setSynonymEq(String synonymEq) {
		this.synonymEq = synonymEq;
	}

	public void fixup(){
	}
	public HashMap toHashMap() {
		HashMap ht = new HashMap();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.tid!=null)
			ht.put("tid",this.tid);
		if(this.tidEq!=null)
			ht.put("tidEq",this.tidEq);
		if(this.dictionary!=null)
			ht.put("dictionary",this.dictionary);
		if(this.code!=null)
			ht.put("code",this.code);
		if(this.codeEq!=null)
			ht.put("codeEq",this.codeEq);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.description!=null)
			ht.put("description",this.description);
		if(this.descriptionEq!=null)
			ht.put("descriptionEq",this.descriptionEq);
		if(this.orderby!=null)
			ht.put("orderby",this.orderby);
		if(this.createUser!=null)
			ht.put("createUser",this.createUser);
		if(this.createTime!=null)
			ht.put("createTime",this.createTime);
		if(this.createTimeEq!=null)
			ht.put("createTimeEq",this.createTimeEq);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.synonym!=null)
			ht.put("synonym",this.synonym);
		if(this.synonymEq!=null)
			ht.put("synonymEq",this.synonymEq);
		return ht; 
	}
}

