package com.xfs.analysis.social;

import com.xfs.analysis.AbstractExcelAnalysis;
import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisResult;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.StringUtils;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;

/**
 * 企业实缴账单
 * Created by konglc on 2017-07-20.
 */
@Service
public class CorpBillSpServiceImpl extends AbstractExcelAnalysis{


    /* 解析类别 -- 子类解析必须确定解析范围 */
    public static final String SOCIAL = "SOCIAL";


    public static final String cache_key_name = "name";

    public static final String cache_key_identityCode = "identityCode";


    @Override
    public String analysisType(){
        return SOCIAL;
    }

    /**
     * 清洗对账数据
     * @param cti
     * @param analysisConfig
     * @param   analysisResult
     * @param notExcelFiles
     * @param ext
     */
    @Override
    public void analysisBusiness(ContextInfo cti, AnalysisConfig analysisConfig, AnalysisResult analysisResult, List<File> notExcelFiles, Map<String, Object> ext) {
        if(null == analysisResult.getDataList() || analysisResult.getDataList().isEmpty())
            return;
        /**
         * 剔除空值
         */
        Iterator<Map<String, AnalysisData>> iterator = analysisResult.getDataList().iterator();
        while (iterator.hasNext()){
            Map<String, AnalysisData> analysisDataMap = iterator.next();
            if(null == analysisDataMap || analysisDataMap.isEmpty()
                    || null == analysisDataMap.get(cache_key_name) || StringUtils.isBlank( analysisDataMap.get(cache_key_name).getAnalysisValue())
                    || null == analysisDataMap.get(cache_key_identityCode) || StringUtils.isBlank(analysisDataMap.get(cache_key_identityCode).getAnalysisValue()))
                iterator.remove();
        }
    }

}
