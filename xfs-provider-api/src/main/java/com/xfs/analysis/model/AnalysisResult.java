package com.xfs.analysis.model;

import com.xfs.analysis.dto.SysAnalysisBustype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-21 14:21
 */
public class AnalysisResult implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    private SysAnalysisBustype opt;//业务操作类型
    private List<Map<String,AnalysisData>> dataList;//解析结果数据列
    private List<AnalysisTitle> titles;//列表字段
    List<Cell> matchHeader = new ArrayList<>(); //匹配到的表头集合
    String extraHeader; //未匹配到的表头集合
    boolean isHeader = false;

    public SysAnalysisBustype getOpt() {
        return opt;
    }

    public void setOpt(SysAnalysisBustype opt) {
        this.opt = opt;
    }

    public List<Map<String, AnalysisData>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, AnalysisData>> dataList) {
        this.dataList = dataList;
    }

    public List<AnalysisTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<AnalysisTitle> titles) {
        this.titles = titles;
    }

    public List<Cell> getMatchHeader() {
        return matchHeader;
    }

    public void setMatchHeader(List<Cell> matchHeader) {
        this.matchHeader = matchHeader;
    }

    public String getExtraHeader() {
        return extraHeader;
    }

    public void setExtraHeader(String extraHeader) {
        this.extraHeader = extraHeader;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
