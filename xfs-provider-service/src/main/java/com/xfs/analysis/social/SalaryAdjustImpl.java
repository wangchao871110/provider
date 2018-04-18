package com.xfs.analysis.social;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.business.enums.BsType;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;

/**
 * 基数调整
 *
 * @author lifq
 *
 * 2017年6月6日  下午2:54:55
 */
@Service
public class SalaryAdjustImpl extends BaseSocialInsuranceExcelAnalysis{

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
        bsTypes.add(BsType.INSUR_PAYBASE);
        bsTypes.add(BsType.FUND_PAYBASE);
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
            //社保调基
            if (BsType.INSUR_PAYBASE.code().equals(bsTypeId) || BsType.FUND_PAYBASE.code().equals(bsTypeId)){
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
            if(BsType.FUND_PAYBASE.getInsurance_fund_type().equals(bsType.getInsurance_fund_type()) && Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue()) == bsType.getCode()){
                analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"公积金调基",false));
                analysisDataMap.put(cache_key_fundSalary,new AnalysisData(cache_key_fundSalary,analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()));
            }
            if (BsType.INSUR_PAYBASE.getInsurance_fund_type().equals(bsType.getInsurance_fund_type()) && Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue()) == bsType.getCode()){
                analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"社保调基",false));
                analysisDataMap.put(cache_key_insuranceSalary,new AnalysisData(cache_key_insuranceSalary,analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()));
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
        if(null == analysisDataMap.get("salary") || StringUtils.isBlank(analysisDataMap.get("salary").getAnalysisValue())){
            analysisDataMap.put("salary",new AnalysisData("salary",analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()));
        }
        if (Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue()) != bsTypeId){
        		return null;
        }

        if(null != analysisDataMap.get(cache_key_beforeSalary) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue())){
            if (BsType.INSUR_PAYBASE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_insuranceSalary,new AnalysisData(cache_key_insuranceSalary,analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()));
            }
            if(BsType.FUND_PAYBASE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_fundSalary,new AnalysisData(cache_key_fundSalary,analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()));
            }
        }

        Map<String,Object> taskJson =  newInsuranceImpl.handleBsTypeTaskJson(cti,analysisDataMap,context,bsTypeId,titles,notExcelFiles);
        if(null != taskJson && (null == analysisDataMap.get(cache_key_beforeSalary) || StringUtils.isBlank(analysisDataMap.get(cache_key_beforeSalary).getAnalysisValue()))){
            if (BsType.INSUR_PAYBASE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_beforeSalary,new AnalysisData(cache_key_beforeSalary,String.valueOf(taskJson.get(cache_key_insuranceSalary))));
                taskJson.put(cache_key_beforeSalary,taskJson.get(cache_key_insuranceSalary));
            }
            if(BsType.FUND_PAYBASE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_beforeSalary,new AnalysisData(cache_key_beforeSalary,String.valueOf(taskJson.get(cache_key_fundSalary))));
                taskJson.put(cache_key_beforeSalary,taskJson.get(cache_key_fundSalary));
            }
            taskJson.put(cache_key_beginDate,queryBeginPeriodBsTypeTask(cti,bsTypeId,analysisDataMap));
        }
        return taskJson;
    }

    @Override
    public void checkDataVerify(ContextInfo cti, AnalysisConfig analysisConfig, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, List<AnalysisTitle> titles){
        super.checkDataVerify(cti, analysisConfig, analysisDataMap, context, titles);

        if(null == analysisDataMap.get(cache_key_name) || StringUtils.isBlank( analysisDataMap.get(cache_key_name).getAnalysisValue())){
        	analysisDataMap.clear();
        }
        if( null == analysisDataMap.get(cache_key_identityCode) || StringUtils.isBlank(analysisDataMap.get(cache_key_identityCode).getAnalysisValue())){
        	analysisDataMap.clear();
        }
          
    }
}
