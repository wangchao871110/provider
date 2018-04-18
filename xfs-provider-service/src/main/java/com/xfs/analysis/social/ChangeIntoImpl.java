package com.xfs.analysis.social;

import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.analysis.utils.DateFormatUtil;
import com.xfs.base.service.BsAreaService;
import com.xfs.business.enums.BsType;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.service.CmCorpService;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 转入业务操作
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-04-21 17:58
 */
@Service
public class ChangeIntoImpl extends BaseSocialInsuranceExcelAnalysis{

    @Autowired
    NewInsuranceImpl newInsuranceImpl;
    /**
     *  当前业务类型
     *  @param
     *	@return 			: java.util.List<com.xfs.business.enums.BsType>
     *  @createDate  	: 2017-04-21 17:39
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-21 17:39
     *  @updateAuthor  :
     */
    @Override
    public List<BsType> supportBsTypes() {
        List<BsType> bsTypes = new ArrayList<>();
        bsTypes.add(BsType.INTO_INSURANCE);
        bsTypes.add(BsType.ADD_INSURANCE);
        return bsTypes;
    }


    /**
     *  获取任务单执行时间
     *  @param   cti, bsTypeId, analysisDataMap
     *	@return 			: com.xfs.business.model.SpsTask
     *  @createDate  	: 2017-04-21 17:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-21 17:43
     *  @updateAuthor  :
     */
    @Override
    public String queryBeginPeriodBsTypeTask(ContextInfo cti, Integer bsTypeId,Map<String,AnalysisData> analysisDataMap) {
        if(BsType.INTO_INSURANCE.code().equals(bsTypeId)){
            return analysisDataMap.get(humpToLine(cache_key_insurancePeriod)).getAnalysisValue();
        }else if(BsType.ADD_INSURANCE.code().equals(bsTypeId)){
            return analysisDataMap.get(humpToLine(cache_key_fundPeriod)).getAnalysisValue();
        }
        return DateUtil.getCurYearMonthStr();
    }

    /**
     *  校验当前操作类型是否属于此业务
     *  @param   analysisDataMap
     *	@return 			: boolean
     *  @createDate  	: 2017-03-23 11:56
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-23 11:56
     *  @updateAuthor  :
     */
    @Override
    public boolean checkBsType(Map<String, AnalysisData> analysisDataMap) {
        if (null != analysisDataMap.get(cache_key_bstypeId) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue())) {
            Integer bsTypeId = Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue());
            //社保(3=转入) 、公积金(10=增加)
            if ( BsType.INTO_INSURANCE.code().equals(bsTypeId) || bsTypeId.equals(BsType.ADD_INSURANCE.code())){
                return true;
            }
        }
        return false;
    }


    /**
     *  处理解析对应的标准模版数据、员工属性数据、任务单共有属性数据
     *  @param   cti, analysisDataMap, context
     *	@return 			: void
     *  @createDate  	: 2017-03-24 09:49
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:49
     *  @updateAuthor  :
     */
    @Override
    public void handleBaseFileds(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context){
        newInsuranceImpl.handleBaseFileds(cti,analysisDataMap,context);
        setEmpBsState(cti,analysisDataMap,context,null);
    }


    /**
     *  设置人员状态
     *  @param   cti, analysisDataMap, context, bsType
     *	@return 			: void
     *  @createDate  	: 2017-05-22 10:20
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-22 10:20
     *  @updateAuthor  :
     */
    @Override
    public void setEmpBsState(ContextInfo cti, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, BsType bsType){
        if(null != bsType){
            if(BsType.ADD_INSURANCE.getInsurance_fund_type().equals(bsType.getInsurance_fund_type())){
                analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"公积金转入",false));
            }
            if (BsType.INTO_INSURANCE.getInsurance_fund_type().equals(bsType.getInsurance_fund_type())){
                analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"社保转入",false));
            }
        }else {
            analysisDataMap.put(cache_key_fundState,new AnalysisData(cache_key_fundState,"OFF"));
            analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"未缴纳"));
            analysisDataMap.put(cache_key_insuranceState,new AnalysisData(cache_key_insuranceState,"OFF"));
            analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"未缴纳"));
        }
    }

    /**
     *  处理任务单对应的taskbot 业务处理JSON
     *  @param   cti, analysisDataMap, context
     *	@return 			: void
     *  @createDate  	: 2017-03-24 09:51
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:51
     *  @updateAuthor  :
     */
    @Override
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, Integer bsTypeId, List<AnalysisTitle> titles, List<File> notExcelFiles){
        Map<String,Object> taskJson =  newInsuranceImpl.handleBsTypeTaskJson(cti,analysisDataMap,context,bsTypeId,titles,notExcelFiles);
        if(null != taskJson)
            taskJson.put(cache_key_beginDate,queryBeginPeriodBsTypeTask(cti,bsTypeId,analysisDataMap));
        return taskJson;
    }
}
