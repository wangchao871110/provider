package com.xfs.score.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * BdScoreDimensionDetail * @author:konglc * @date:2016/09/21 11:40:20	 */public class BdScoreDimensionDetail implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer scoreTargetId;//	public Integer getScoreTargetId() {	    return this.scoreTargetId;	}	public void setScoreTargetId(Integer scoreTargetId) {	    this.scoreTargetId=scoreTargetId;	}	private String scoreTargetType;//客服评分:CUSTOMER            机构评分:SERVICE	public String getScoreTargetType() {	    return this.scoreTargetType;	}	public void setScoreTargetType(String scoreTargetType) {	    this.scoreTargetType=scoreTargetType;	}	private String scoreTargetTypeEq;//客服评分:CUSTOMER            机构评分:SERVICE	public String getScoreTargetTypeEq() {	    return this.scoreTargetTypeEq;	}	public void setScoreTargetTypeEq(String scoreTargetTypeEq) {	    this.scoreTargetTypeEq=scoreTargetTypeEq;	}	private Integer itemId;//	public Integer getItemId() {	    return this.itemId;	}	public void setItemId(Integer itemId) {	    this.itemId=itemId;	}	private Integer dimId;//	public Integer getDimId() {	    return this.dimId;	}	public void setDimId(Integer dimId) {	    this.dimId=dimId;	}	private String dimensionName;//	public String getDimensionName() {	    return this.dimensionName;	}	public void setDimensionName(String dimensionName) {	    this.dimensionName=dimensionName;	}	private String dimensionNameEq;//	public String getDimensionNameEq() {	    return this.dimensionNameEq;	}	public void setDimensionNameEq(String dimensionNameEq) {	    this.dimensionNameEq=dimensionNameEq;	}	private BigDecimal weight;//N:否  Y：是	public BigDecimal getWeight() {	    return this.weight;	}	public void setWeight(BigDecimal weight) {	    this.weight=weight;	}	private BigDecimal score;//	public BigDecimal getScore() {	    return this.score;	}	public void setScore(BigDecimal score) {	    this.score=score;	}	private String calFormula;//	public String getCalFormula() {	    return this.calFormula;	}	public void setCalFormula(String calFormula) {	    this.calFormula=calFormula;	}	private String calFormulaEq;//	public String getCalFormulaEq() {	    return this.calFormulaEq;	}	public void setCalFormulaEq(String calFormulaEq) {	    this.calFormulaEq=calFormulaEq;	}	private String calType;//SQL，CLASS，QL	public String getCalType() {	    return this.calType;	}	public void setCalType(String calType) {	    this.calType=calType;	}	private String calTypeEq;//SQL，CLASS，QL	public String getCalTypeEq() {	    return this.calTypeEq;	}	public void setCalTypeEq(String calTypeEq) {	    this.calTypeEq=calTypeEq;	}	private String calVersion;//201609210001	public String getCalVersion() {	    return this.calVersion;	}	public void setCalVersion(String calVersion) {	    this.calVersion=calVersion;	}	private String calVersionEq;//201609210001	public String getCalVersionEq() {	    return this.calVersionEq;	}	public void setCalVersionEq(String calVersionEq) {	    this.calVersionEq=calVersionEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//0：否   1：已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.scoreTargetId!=null)			ht.put("scoreTargetId",this.scoreTargetId);		if(this.scoreTargetType!=null)			ht.put("scoreTargetType",this.scoreTargetType);		if(this.scoreTargetTypeEq!=null)			ht.put("scoreTargetTypeEq",this.scoreTargetTypeEq);		if(this.itemId!=null)			ht.put("itemId",this.itemId);		if(this.dimId!=null)			ht.put("dimId",this.dimId);		if(this.dimensionName!=null)			ht.put("dimensionName",this.dimensionName);		if(this.dimensionNameEq!=null)			ht.put("dimensionNameEq",this.dimensionNameEq);		if(this.weight!=null)			ht.put("weight",this.weight);		if(this.score!=null)			ht.put("score",this.score);		if(this.calFormula!=null)			ht.put("calFormula",this.calFormula);		if(this.calFormulaEq!=null)			ht.put("calFormulaEq",this.calFormulaEq);		if(this.calType!=null)			ht.put("calType",this.calType);		if(this.calTypeEq!=null)			ht.put("calTypeEq",this.calTypeEq);		if(this.calVersion!=null)			ht.put("calVersion",this.calVersion);		if(this.calVersionEq!=null)			ht.put("calVersionEq",this.calVersionEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/09/21 11:40:20		}