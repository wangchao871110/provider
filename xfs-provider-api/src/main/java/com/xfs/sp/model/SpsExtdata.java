package com.xfs.sp.model;

import java.util.Date;
import java.util.HashMap;

/**
 * SpsExtdata
 * @author:wuzhe
 * 
 * @date:2016/05/17 21:34:20
 */
public class SpsExtdata implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer spId;//

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	private String name;//

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String nameEq;//

	public String getNameEq() {
		return this.nameEq;
	}

	public void setNameEq(String nameEq) {
		this.nameEq = nameEq;
	}

	private String remark;//

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String remarkEq;//

	public String getRemarkEq() {
		return this.remarkEq;
	}

	public void setRemarkEq(String remarkEq) {
		this.remarkEq = remarkEq;
	}

	private Integer excelFile;//

	public Integer getExcelFile() {
		return this.excelFile;
	}

	public void setExcelFile(Integer excelFile) {
		this.excelFile = excelFile;
	}

	private Integer createBy;//

	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	private Date createDt;//

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	private Date createDtFrom;//

	public Date getCreateDtFrom() {
		return this.createDtFrom;
	}

	public void setCreateDtFrom(Date createDtFrom) {
		this.createDtFrom = createDtFrom;
	}

	private Date createDtTo;//

	public Date getCreateDtTo() {
		return this.createDtTo;
	}

	public void setCreateDtTo(Date createDtTo) {
		this.createDtTo = createDtTo;
	}

	private Integer modifyBy;//

	public Integer getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	private Date modifyDt;//

	public Date getModifyDt() {
		return this.modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	private Date modifyDtFrom;//

	public Date getModifyDtFrom() {
		return this.modifyDtFrom;
	}

	public void setModifyDtFrom(Date modifyDtFrom) {
		this.modifyDtFrom = modifyDtFrom;
	}

	private Date modifyDtTo;//

	public Date getModifyDtTo() {
		return this.modifyDtTo;
	}

	public void setModifyDtTo(Date modifyDtTo) {
		this.modifyDtTo = modifyDtTo;
	}

	private Integer dr;// 逻辑删除标记位 0:未删除 1:已删除

	public Integer getDr() {
		return this.dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public void fixup() {
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.id != null)
			ht.put("id", this.id);
		if (this.spId != null)
			ht.put("spId", this.spId);
		if (this.name != null)
			ht.put("name", this.name);
		if (this.nameEq != null)
			ht.put("nameEq", this.nameEq);
		if (this.remark != null)
			ht.put("remark", this.remark);
		if (this.remarkEq != null)
			ht.put("remarkEq", this.remarkEq);
		if (this.excelFile != null)
			ht.put("excelFile", this.excelFile);
		if (this.createBy != null)
			ht.put("createBy", this.createBy);
		if (this.createDt != null)
			ht.put("createDt", this.createDt);
		if (this.createDtFrom != null)
			ht.put("createDtFrom", this.createDtFrom);
		if (this.createDtTo != null)
			ht.put("createDtTo", this.createDtTo);
		if (this.modifyBy != null)
			ht.put("modifyBy", this.modifyBy);
		if (this.modifyDt != null)
			ht.put("modifyDt", this.modifyDt);
		if (this.modifyDtFrom != null)
			ht.put("modifyDtFrom", this.modifyDtFrom);
		if (this.modifyDtTo != null)
			ht.put("modifyDtTo", this.modifyDtTo);
		if (this.dr != null)
			ht.put("dr", this.dr);
		return ht;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/17 21:34:20
	private String createByName;//

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
}
