package com.xfs.analysis.model;

import com.xfs.analysis.dto.SysAnalysisBustype;
import com.hankcs.hanlp.suggest.Suggester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分析配置
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-21 15:49
 */
public class AnalysisConfig implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private List<SysAnalysisBustype> sysAnalysisBustype;//对应业务操作类型
    private Map<String,List<AnalysisTitle>> titles;//列头信息
    private Suggester suggester = new Suggester();
    private Map<String, String> splitWordMap = new HashMap<>();

    public List<SysAnalysisBustype> getSysAnalysisBustype() {
        return sysAnalysisBustype;
    }

    public void setSysAnalysisBustype(List<SysAnalysisBustype> sysAnalysisBustype) {
        this.sysAnalysisBustype = sysAnalysisBustype;
    }

    public Map<String, List<AnalysisTitle>> getTitles() {
        return titles;
    }

    public void setTitles(Map<String, List<AnalysisTitle>> titles) {
        this.titles = titles;
    }

    public Suggester getSuggester() {
        return suggester;
    }

    public void setSuggester(Suggester suggester) {
        this.suggester = suggester;
    }

    public Map<String, String> getSplitWordMap() {
        return splitWordMap;
    }

    public void setSplitWordMap(Map<String, String> splitWordMap) {
        this.splitWordMap = splitWordMap;
    }
}
