package com.xfs.analysis.model;

/**
 * 解析数据对应的列模板信息
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-21 15:55
 */
public class AnalysisTitle implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String name;//编码名称
    private String title;//汉字列头
    private String similar;//近义词库
    private Integer requisite;//0:不必须 1:必须
    private Integer show;
    private Integer busTypeId;
    private Integer rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public Integer getRequisite() {
        return requisite;
    }

    public void setRequisite(Integer requisite) {
        this.requisite = requisite;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public Integer getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(Integer busTypeId) {
        this.busTypeId = busTypeId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object obj) {
        AnalysisTitle title = (AnalysisTitle)obj;
        return this.getTitle().equals(title.getTitle());
    }
}
