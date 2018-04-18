package com.xfs.insurance.model;import java.math.BigDecimal;import java.util.HashMap;/** * BsCiScheme *  * @author:lihongchun * @date:2016/09/03 11:44:03 */public class BsCiScheme implements java.io.Serializable {    private static final long serialVersionUID = 1L;    private Integer id;// 主键    public Integer getId() {        return this.id;    }    public void setId(Integer id) {        this.id = id;    }    private Integer mallItemId;// 商城服务条目编码    public Integer getMallItemId() {        return this.mallItemId;    }    public void setMallItemId(Integer mallItemId) {        this.mallItemId = mallItemId;    }    private String schemeName;// 方案名称    public String getSchemeName() {        return this.schemeName;    }    public void setSchemeName(String schemeName) {        this.schemeName = schemeName;    }    private String schemeNameEq;// 方案名称    public String getSchemeNameEq() {        return this.schemeNameEq;    }    public void setSchemeNameEq(String schemeNameEq) {        this.schemeNameEq = schemeNameEq;    }    private BigDecimal price;// 净保费    public BigDecimal getPrice() {        return this.price;    }    public void setPrice(BigDecimal price) {        this.price = price;    }    private BigDecimal agentPrice;// 代理价格    public BigDecimal getAgentPrice() {        return this.agentPrice;    }    public void setAgentPrice(BigDecimal agentPrice) {        this.agentPrice = agentPrice;    }    private BigDecimal suggestPrice;// 建议零售价    public BigDecimal getSuggestPrice() {        return this.suggestPrice;    }    public void setSuggestPrice(BigDecimal suggestPrice) {        this.suggestPrice = suggestPrice;    }    private String schemeDesc;// 方案描述信息    public String getSchemeDesc() {        return this.schemeDesc;    }    public void setSchemeDesc(String schemeDesc) {        this.schemeDesc = schemeDesc;    }    private String schemeDescEq;// 方案描述信息    public String getSchemeDescEq() {        return this.schemeDescEq;    }    public void setSchemeDescEq(String schemeDescEq) {        this.schemeDescEq = schemeDescEq;    }    private String schemeInsure;// 保障信息， 暂时以json字符串的方式存储    public String getSchemeInsure() {        return this.schemeInsure;    }    public void setSchemeInsure(String schemeInsure) {        this.schemeInsure = schemeInsure;    }    private String schemeInsureEq;// 保障信息， 暂时以json字符串的方式存储    public String getSchemeInsureEq() {        return this.schemeInsureEq;    }    public void setSchemeInsureEq(String schemeInsureEq) {        this.schemeInsureEq = schemeInsureEq;    }    public void fixup() {    }    public HashMap<String, Object> toHashMap() {        HashMap<String, Object> ht = new HashMap<String, Object>();        if (this.id != null)            ht.put("id", this.id);        if (this.mallItemId != null)            ht.put("mallItemId", this.mallItemId);        if (this.schemeName != null)            ht.put("schemeName", this.schemeName);        if (this.schemeNameEq != null)            ht.put("schemeNameEq", this.schemeNameEq);        if (this.price != null)            ht.put("price", this.price);        if (this.agentPrice != null)            ht.put("agentPrice", this.agentPrice);        if (this.suggestPrice != null)            ht.put("suggestPrice", this.suggestPrice);        if (this.schemeDesc != null)            ht.put("schemeDesc", this.schemeDesc);        if (this.schemeDescEq != null)            ht.put("schemeDescEq", this.schemeDescEq);        if (this.schemeInsure != null)            ht.put("schemeInsure", this.schemeInsure);        if (this.schemeInsureEq != null)            ht.put("schemeInsureEq", this.schemeInsureEq);        return ht;    }    // 温馨提示：    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写    // 2016/09/03 11:44:04}