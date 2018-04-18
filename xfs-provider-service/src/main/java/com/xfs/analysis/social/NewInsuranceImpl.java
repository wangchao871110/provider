package com.xfs.analysis.social;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.business.enums.BsType;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.StringUtils;
import com.xfs.sp.dto.InsuranceTypeDto;

/**
 * 参加社保业务
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-23 11:53
 */
@Service
public class NewInsuranceImpl extends BaseSocialInsuranceExcelAnalysis{

    private static Logger log = LoggerFactory.getLogger(NewInsuranceImpl.class);

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
        bsTypes.add(BsType.NEW_INSURANCE);
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
        if(BsType.NEW_INSURANCE.code().equals(bsTypeId)){
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
            //社保(2=增员) 、公积金(10=增加)
            if (BsType.NEW_INSURANCE.code().equals(bsTypeId) || bsTypeId.equals(BsType.ADD_INSURANCE.code())){
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
        super.handleBaseFileds(cti,analysisDataMap,context);
        //工资赋值
        if(null == analysisDataMap.get(cache_key_insuranceSalary) && null != analysisDataMap.get(cache_key_fundSalary)){
            analysisDataMap.put(cache_key_insuranceSalary,new AnalysisData(cache_key_insuranceSalary,analysisDataMap.get(cache_key_fundSalary).getAnalysisValue()));
        }
        if(null == analysisDataMap.get(cache_key_fundSalary) && null != analysisDataMap.get(cache_key_insuranceSalary)){
            analysisDataMap.put(cache_key_fundSalary,new AnalysisData(cache_key_fundSalary,analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue()));
        }
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
                analysisDataMap.put(cache_key_fundStateName,new AnalysisData(cache_key_fundStateName,"公积金新参保",false));
            }
            if (BsType.NEW_INSURANCE.getInsurance_fund_type().equals(bsType.getInsurance_fund_type())){
                analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceStateName,"社保新参保",false));
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
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,Integer bsTypeId,List<AnalysisTitle> titles,List<File> notExcelFiles){
        Map<String,Object> taskJson = super.handleBsTypeTaskJson(cti,analysisDataMap,context,bsTypeId,titles,notExcelFiles);
        taskJson.put(cache_key_beginDate,queryBeginPeriodBsTypeTask(cti,bsTypeId,analysisDataMap));
        //方案对应大库的比例
        if(StringUtils.isNotBlank(cti.getLoginXfb()) && cache_key_NOTASK.equals(cti.getLoginXfb()))
            return taskJson;
        List<InsuranceTypeDto> insuranceTypeDtoList = null;
        if(null == analysisDataMap.get(cache_key_insuranceLiveType) || StringUtils.isBlank(analysisDataMap.get(cache_key_insuranceLiveType).getAnalysisValue())){
            log.info("当前险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无对应户口性质");
            return null;
        }
        String insuranceLiveType = analysisDataMap.get(cache_key_insuranceLiveType).getAnalysisValue();
        if (BsType.NEW_INSURANCE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
            if(null != analysisDataMap.get(cache_key_insuranceAccountId) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_insuranceAccountId).getAnalysisValue())){
                String key = BsType.NEW_INSURANCE.getInsurance_fund_type() + "_" + Integer.parseInt(analysisDataMap.get(cache_key_insuranceAccountId).getAnalysisValue()) + "_" + insuranceLiveType;
                String period = analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue();
                Object ratioList = RedisUtil.hget(key,period);
                if(null != ratioList){
                    insuranceTypeDtoList = (List<InsuranceTypeDto>)ratioList;
                }else{
                    insuranceTypeDtoList = cmEmployeeService.queryAccountInsuranceListByAccountId(Integer.parseInt(analysisDataMap.get(cache_key_insuranceAccountId).getAnalysisValue()), insuranceLiveType, analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue(),"Y");
                    RedisUtil.hput(key,period,insuranceTypeDtoList);
                }
                BigDecimal baseLower = BigDecimal.ZERO;
                for(InsuranceTypeDto dto : insuranceTypeDtoList){
                    if(null != analysisDataMap.get(cache_key_insuranceSalary) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue())){
                        dto.setEmpPaybase(new BigDecimal(analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue()));
                        dto.setCorpPaybase(new BigDecimal(analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue()));
                    }
                    dto.setBeginDate(analysisDataMap.get(humpToLine(cache_key_insurancePeriod)).getAnalysisValue());
                    if(baseLower.compareTo(BigDecimal.ZERO) == 0){
                        baseLower = dto.getCorpBaseLower();
                    }else if(baseLower.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
                        baseLower = dto.getCorpBaseLower();
                    }
                }
                if(null == analysisDataMap.get(cache_key_insuranceSalary) || StringUtils.isBlank(analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue())){
                    analysisDataMap.put(cache_key_insuranceSalary,new AnalysisData(cache_key_insuranceSalary,baseLower.toString()));
                }
                taskJson.put(cache_key_insuranceSalary,analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue());
            }else{
                log.info("当前险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无对应比例库信息");
                return null;
            }
        }
        if(BsType.ADD_INSURANCE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
            if(null != analysisDataMap.get(cache_key_fundAccountId) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_fundAccountId).getAnalysisValue())){
                String key = BsType.ADD_INSURANCE.getInsurance_fund_type() + "_" + Integer.parseInt(analysisDataMap.get(cache_key_fundAccountId).getAnalysisValue());
                String period =  analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue();
                Object ratioList = RedisUtil.hget(key,period);
                if(null != ratioList){
                    insuranceTypeDtoList = (List<InsuranceTypeDto>)ratioList;
                }else{
                    insuranceTypeDtoList = cmEmployeeService.queryAccountInsuranceListByAccountId(Integer.parseInt(analysisDataMap.get(cache_key_fundAccountId).getAnalysisValue()), null, analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue(),"Y");
                    RedisUtil.hput(key,period,insuranceTypeDtoList);
                }
                BigDecimal baseLower = BigDecimal.ZERO;
                for(InsuranceTypeDto dto : insuranceTypeDtoList){
                    if(null != analysisDataMap.get(cache_key_fundSalary) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_fundSalary).getAnalysisValue())){
                        dto.setEmpPaybase(new BigDecimal(analysisDataMap.get(cache_key_fundSalary).getAnalysisValue()));
                        dto.setCorpPaybase(new BigDecimal(analysisDataMap.get(cache_key_fundSalary).getAnalysisValue()));
                    }
                    dto.setBeginDate(analysisDataMap.get(humpToLine(cache_key_fundPeriod)).getAnalysisValue());
                    if(baseLower.compareTo(BigDecimal.ZERO) == 0){
                        baseLower = dto.getCorpBaseLower();
                    }else if(baseLower.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
                        baseLower = dto.getCorpBaseLower();
                    }
                }
                if(null == analysisDataMap.get(cache_key_fundSalary) || StringUtils.isBlank(analysisDataMap.get(cache_key_fundSalary).getAnalysisValue())){
                    analysisDataMap.put(cache_key_fundSalary,new AnalysisData(cache_key_fundSalary,baseLower.toString()));
                }
                taskJson.put(cache_key_fundSalary,analysisDataMap.get(cache_key_fundSalary).getAnalysisValue());
            }else {
                log.info("当前险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无对应比例库信息");
                return null;
            }
        }

        if(BsType.ADD_INSURANCE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId)) ){
            if(null == analysisDataMap.get(cache_key_fundSalary) || StringUtils.isBlank(analysisDataMap.get(cache_key_fundSalary).getAnalysisValue())){
                log.info("当前险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无对应基数");
                return null;
            }
        }

        if (BsType.NEW_INSURANCE.getInsurance_fund_type().equals(BsType.getInsType(bsTypeId))){
            if(null == analysisDataMap.get(cache_key_insuranceSalary) || StringUtils.isBlank(analysisDataMap.get(cache_key_insuranceSalary).getAnalysisValue())){
                log.info("当前险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无对应基数");
                return null;
            }
        }
        if(null == insuranceTypeDtoList || insuranceTypeDtoList.isEmpty()){
            log.info("当前时间"+analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue()+"段，险种"+BsType.ADD_INSURANCE.getInsurance_fund_type()+"无比例版本");
            return null;
        }
        taskJson.put("insurance",insuranceTypeDtoList);
        return taskJson;
    }
}
