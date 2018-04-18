package com.xfs.analysis.model;

/**
 * 解析数据属性
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-21 15:50
 */
public class AnalysisData implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    public AnalysisData(){}

    public AnalysisData(String name,String analysisValue){
        this.name = name;
        this.analysisValue = analysisValue;
    }

    public AnalysisData(String name,String analysisValue,boolean isDefault){
        this.name = name;
        this.analysisValue = analysisValue;
        this.isDefault = isDefault;
    }

    public AnalysisData(String name,String analysisValue,boolean isDefault,Integer errorLevel){
        this.name = name;
        this.analysisValue = analysisValue;
        this.isDefault = isDefault;
        this.errorLevel = errorLevel;
    }

    private String name;//名称
    private String analysisValue;//解析值
    private boolean isDefault = true;//是否自动填充
    private Integer errorLevel = 1;// 1 : 可以忍受 2 : 不可以忍受

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnalysisValue() {
        return analysisValue;
    }

    public void setAnalysisValue(String analysisValue) {
        this.analysisValue = analysisValue;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Integer getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(Integer errorLevel) {
        this.errorLevel = errorLevel;
    }
}
