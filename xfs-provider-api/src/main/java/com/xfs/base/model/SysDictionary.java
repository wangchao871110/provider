package com.xfs.base.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * SysDictionary
 * @author:zhangyan
 * @date:2015/12/10 17:19:50	
 */
public class SysDictionary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//
	private String nameEq;//
	private String cnname;//
	private String cnnameEq;//
	private String description;//
	private String descriptionEq;//
	private String remark;//
	private String remarkEq;//
	private Integer dictlistType;//   0锛氱郴缁熺骇锛�锛氱鎴风骇
	private String createTime;//
	private String createTimeEq;//
	private Integer createUser;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
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
	public String getCnname() {
	    return this.cnname;
	}
	public void setCnname(String cnname) {
	    this.cnname=cnname;
	}
	public String getCnnameEq() {
	    return this.cnnameEq;
	}
	public void setCnnameEq(String cnnameEq) {
	    this.cnnameEq=cnnameEq;
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
	public String getRemark() {
	    return this.remark;
	}
	public void setRemark(String remark) {
	    this.remark=remark;
	}
	public String getRemarkEq() {
	    return this.remarkEq;
	}
	public void setRemarkEq(String remarkEq) {
	    this.remarkEq=remarkEq;
	}
	public Integer getDictlistType() {
	    return this.dictlistType;
	}
	public void setDictlistType(Integer dictlistType) {
	    this.dictlistType=dictlistType;
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
	public Integer getCreateUser() {
	    return this.createUser;
	}
	public void setCreateUser(Integer createUser) {
	    this.createUser=createUser;
	}
	public void fixup(){
	}
	public HashMap toHashMap() {
		HashMap ht = new HashMap();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.cnname!=null)
			ht.put("cnname",this.cnname);
		if(this.cnnameEq!=null)
			ht.put("cnnameEq",this.cnnameEq);
		if(this.description!=null)
			ht.put("description",this.description);
		if(this.descriptionEq!=null)
			ht.put("descriptionEq",this.descriptionEq);
		if(this.remark!=null)
			ht.put("remark",this.remark);
		if(this.remarkEq!=null)
			ht.put("remarkEq",this.remarkEq);
		if(this.dictlistType!=null)
			ht.put("dictlistType",this.dictlistType);
		if(this.createTime!=null)
			ht.put("createTime",this.createTime);
		if(this.createTimeEq!=null)
			ht.put("createTimeEq",this.createTimeEq);
		if(this.createUser!=null)
			ht.put("createUser",this.createUser);
		return ht; 
	}
	

	/**
	 * new qjh 2015/12/12
	 * 鏂板瀛愰泦瀛楁
	 */
	private ArrayList sysDictitem = new ArrayList();//SysDictitem
	public ArrayList getSysDictitem() {
		return sysDictitem;
	}
	public void setSysDictitem(ArrayList sysDictitem) {
		this.sysDictitem = sysDictitem;
	}
	/**
	 * end qjh 2015/12/12
	 */
	
}

