package com.xfs.analysis.social;

import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.analysis.utils.DateFormatUtil;
import com.xfs.base.service.BsAreaService;
import com.xfs.business.dto.BsTypeAreaFiledDto;
import com.xfs.business.enums.BsType;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.service.CmCorpService;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 参加社保业务
 * @author : quanjiahua@xinfuseh.com
 * @version : V1.0
 * @date : 2017-05-02 11:53
 */
@Service
public class BackInsuranceImpl extends BaseSocialInsuranceExcelAnalysis{

    @Autowired
    NewInsuranceImpl newInsuranceImpl;

    /**
     *  当前业务类型
     *  @param
     *	@return 			: java.util.List<com.xfs.business.enums.BsType>
     *  @createDate  	: 2017-05-02 17:39
     *  @author         	: quanjiahua@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-02 17:39
     *  @updateAuthor  :
     */
    @Override
    public List<BsType> supportBsTypes() {
        List<BsType> bsTypes = new ArrayList<>();
        bsTypes.add(BsType.INSUR_BACK);
        bsTypes.add(BsType.FUND_BACK);
        return bsTypes;
    }

    /**
     *  获取任务单执行时间
     *  @param   cti, bsTypeId, analysisDataMap
     *	@return 			: com.xfs.business.model.SpsTask
     *  @createDate  	: 2017-05-02 17:43
     *  @author         	: quanjiahua@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-02 17:43
     *  @updateAuthor  :
     */
    @Override
    public String queryBeginPeriodBsTypeTask(ContextInfo cti, Integer bsTypeId,Map<String,AnalysisData> analysisDataMap) {
        if(BsType.INSUR_BACK.code().equals(bsTypeId)){
            return analysisDataMap.get(humpToLine("supplementarybegindate")).getAnalysisValue();
        }else if(BsType.FUND_BACK.code().equals(bsTypeId)){
            return null;//analysisDataMap.get(humpToLine("TODO")).getAnalysisValue();
        }
        return null;
    }
    
    /**
     *  校验当前操作类型是否属于此业务
     *  @param   analysisDataMap
     *	@return 			: boolean
     *  @createDate  	: 2017-05-02 11:56
     *  @author         	: quanjiahua@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-02 11:56
     *  @updateAuthor  :
     */
    @Override
    public boolean checkBsType(Map<String, AnalysisData> analysisDataMap) {
        if (null != analysisDataMap.get(cache_key_bstypeId) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue())) {
            Integer bsTypeId = Integer.parseInt(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue());
            //社保(2=增员) 、公积金(10=增加)
            if (BsType.INSUR_BACK.code().equals(bsTypeId) || bsTypeId.equals(BsType.FUND_BACK.code())){
                return true;
            }
        }
        return false;
    }


    /**
     *  处理解析对应的标准模版数据、员工属性数据、任务单共有属性数据
     *  @param   cti, analysisDataMap, context
     *	@return 			: void
     *  @createDate  	: 2017-05-02 09:49
     *  @author         	: quanjiahua@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-02 09:49
     *  @updateAuthor  :
     */
    @Override
    public void handleBaseFileds(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context){
        super.handleBaseFileds(cti,analysisDataMap,context);
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
            if(BsType.FUND_BACK.getInsurance_fund_type().equals(bsType.getInsurance_fund_type())){
                analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"公积金补缴",false));
                analysisDataMap.put(cache_key_fundState,new AnalysisData(cache_key_fundState,"ON",false));
            }
            if (BsType.INSUR_BACK.getInsurance_fund_type().equals(bsType.getInsurance_fund_type())){
                analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"社保补缴",false));
                analysisDataMap.put(cache_key_insuranceState,new AnalysisData(cache_key_insuranceState,"ON"));
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
     *  @createDate  	: 2017-05-02 09:51
     *  @author         	: quanjiahua@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-02 09:51
     *  @updateAuthor  :
     */
    @Override
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,Integer bsTypeId,List<AnalysisTitle> titles,List<File> notExcelFiles){
        if(null != analysisDataMap.get(cache_key_insurancebase) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_insurancebase).getAnalysisValue())){
            if (BsType.INSUR_BACK.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_insuranceSalary,new AnalysisData(cache_key_insuranceSalary,analysisDataMap.get(cache_key_insurancebase).getAnalysisValue()));
            }
            if(BsType.FUND_BACK.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_fundSalary,new AnalysisData(cache_key_fundSalary,analysisDataMap.get(cache_key_insurancebase).getAnalysisValue()));
            }
        }
        Map<String,Object> taskJson = newInsuranceImpl.handleBsTypeTaskJson(cti,analysisDataMap,context,bsTypeId,titles,notExcelFiles);
        if(null != taskJson && (null == analysisDataMap.get(cache_key_insurancebase) || StringUtils.isBlank(analysisDataMap.get(cache_key_insurancebase).getAnalysisValue()))){
            if (BsType.INSUR_BACK.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_insurancebase,new AnalysisData(cache_key_insurancebase,String.valueOf(taskJson.get(cache_key_insuranceSalary))));
                taskJson.put(cache_key_insurancebase,taskJson.get(cache_key_insuranceSalary));
            }
            if(BsType.FUND_BACK.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
                analysisDataMap.put(cache_key_insurancebase,new AnalysisData(cache_key_insurancebase,String.valueOf(taskJson.get(cache_key_fundSalary))));
                taskJson.put(cache_key_insurancebase,taskJson.get(cache_key_fundSalary));
            }
        }
        if ( BsType.INSUR_BACK.code().equals(bsTypeId)){
            if(null == analysisDataMap.get(cache_key_insurancebase) || StringUtils.isBlank(analysisDataMap.get(cache_key_insurancebase).getAnalysisValue())){
                return null;
            }
        }
        return taskJson;
    }

    @Override
    public void checkDataVerify(ContextInfo cti, AnalysisConfig analysisConfig, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, List<AnalysisTitle> titles){
        super.checkDataVerify(cti, analysisConfig, analysisDataMap, context, titles);
        /**
         * 补缴结束时间
         */
        if(null == analysisDataMap.get(cache_key_insurancebase) || StringUtils.isBlank( analysisDataMap.get(cache_key_insurancebase).getAnalysisValue()))
            analysisDataMap.put(cache_key_insurancebase,new AnalysisData(cache_key_insurancebase,"",true,2));

        /**
         * 补缴开始时间
         */
        if(null == analysisDataMap.get(cache_key_supplementarybegindate) || StringUtils.isBlank( analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue()))
            analysisDataMap.put(cache_key_supplementarybegindate,new AnalysisData(cache_key_supplementarybegindate,"",true,2));
        else{
            if(analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue().length() <= 4){
                analysisDataMap.put(cache_key_supplementarybegindate,new AnalysisData(cache_key_supplementarybegindate,analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue(),false,2));
            }else{
                String period = DateFormatUtil.FormatDate(analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue());
                analysisDataMap.put(cache_key_supplementarybegindate,new AnalysisData(cache_key_supplementarybegindate,period,false));
            }
        }
        /**
         * 补缴结束时间
         */
        if(null == analysisDataMap.get(cache_key_supplementaryenddate) || StringUtils.isBlank( analysisDataMap.get(cache_key_supplementaryenddate).getAnalysisValue()))
            analysisDataMap.put(cache_key_supplementaryenddate,new AnalysisData(cache_key_supplementaryenddate,"",true,2));
        else{
            if(analysisDataMap.get(cache_key_supplementaryenddate).getAnalysisValue().length() <= 4){
                analysisDataMap.put(cache_key_supplementaryenddate,new AnalysisData(cache_key_supplementaryenddate,analysisDataMap.get(cache_key_supplementaryenddate).getAnalysisValue(),false,2));
            }else{
                String period = DateFormatUtil.FormatDate(analysisDataMap.get(cache_key_supplementaryenddate).getAnalysisValue());
                analysisDataMap.put(cache_key_supplementaryenddate,new AnalysisData(cache_key_supplementaryenddate,period,false));
            }
        }

        /**
         * 默认缴纳时间
         */
        if(null != analysisDataMap.get(cache_key_supplementarybegindate) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue())){
            String period = analysisDataMap.get(cache_key_supplementarybegindate).getAnalysisValue();
            analysisDataMap.put(cache_key_insurancePeriod,new AnalysisData(cache_key_insurancePeriod,period,false));
            analysisDataMap.put(cache_key_fundPeriod,new AnalysisData(cache_key_fundPeriod,period,false));
        }
    }
}
