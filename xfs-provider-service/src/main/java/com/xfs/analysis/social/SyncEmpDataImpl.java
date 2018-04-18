package com.xfs.analysis.social;

import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.business.enums.BsType;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 同步政府网站数据
 * Created by konglc on 2017-09-19.
 */
@Service
public class SyncEmpDataImpl extends BaseSocialInsuranceExcelAnalysis{

    private static Logger log = LoggerFactory.getLogger(SyncEmpDataImpl.class);

    @Override
    public List<BsType> supportBsTypes() {
        List<BsType> bsTypes = new ArrayList<>();
        bsTypes.add(BsType.NEW_INSURANCE);
        bsTypes.add(BsType.ADD_INSURANCE);
        return bsTypes;
    }

    @Override
    public String queryBeginPeriodBsTypeTask(ContextInfo cti, Integer bsTypeId, Map<String, AnalysisData> analysisDataMap) {
        if("188".equals(String.valueOf(bsTypeId))){
            return analysisDataMap.get(humpToLine(cache_key_insurancePeriod)).getAnalysisValue();
        }else if("189".equals(String.valueOf(bsTypeId))){
            return analysisDataMap.get(humpToLine(cache_key_fundPeriod)).getAnalysisValue();
        }
        return DateUtil.getCurYearMonthStr();
    }


    @Override
    public boolean checkBsType(Map<String, AnalysisData> analysisDataMap) {
        if (null != analysisDataMap.get(cache_key_bstypeId) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue())) {
            Integer bsTypeId = Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue());
            //社保(2=增员) 、公积金(10=增加)
            if ("188".equals(String.valueOf(bsTypeId)) || "189".equals(String.valueOf(bsTypeId))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEmpBsState(ContextInfo cti, Map<String, AnalysisData> analysisDataMap, Map<String, Object> context, BsType bsType) {

    }


    @Override
    public void handleBaseFileds(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context){
        super.handleBaseFileds(cti,analysisDataMap,context);

        Integer bsTypeId = Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue());


        if("188".equals(String.valueOf(bsTypeId))){
            analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"社保新参保",false));
            analysisDataMap.put(cache_key_insuranceState,new AnalysisData(cache_key_insuranceState,"ON"));
            bsTypeId = BsType.NEW_INSURANCE.getCode();
        }
        if ("189".equals(String.valueOf(bsTypeId))){
            analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"公积金新参保",false));
            analysisDataMap.put(cache_key_fundState,new AnalysisData(cache_key_fundState,"ON"));
            bsTypeId = BsType.ADD_INSURANCE.getCode();
        }
        super.handleBsTypeTaskJson(cti,analysisDataMap,context,bsTypeId,new ArrayList<AnalysisTitle>(),new ArrayList<File>());
    }

    /**
     * 处理同步过程中的比例信息
     * @param   cti, analysisDataMap, context
     * @param analysisDataMap
     * @param context
     * @param bsTypeId
     * @param titles
     * @param notExcelFiles
     * @return
     */
    @Override
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, Integer bsTypeId, List<AnalysisTitle> titles, List<File> notExcelFiles) {


        return null;
    }

}
