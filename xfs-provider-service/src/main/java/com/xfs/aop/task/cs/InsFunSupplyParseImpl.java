package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.bill.service.SpsFeeEmponceService;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.util.DateUtil;
import com.xfs.corp.enums.InsuranceState;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.sp.dto.InsuranceTypeDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 社保 公积金 补缴
 *
 * @author xiyanzhang
 */
@Service("InsFunSupplyParseImpl")
public class InsFunSupplyParseImpl extends BaseTaskDataParse {

    @Autowired
    SpsFeeEmponceService spsFeeEmponceService;

    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.INSUR_BACK.code().equals(spsTask.getBstypeId()) || BsType.FUND_BACK.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee cmEmployee, Result result) {
        if(!super.busDataValidity(info,spsTask,businessParame,businessFieldMap,cmEmployee,result))
            return false;
        if (null != spsTask && null != spsTask.getEmpId() && null != spsTask.getBstypeId() && null != spsTask.getJson()) {
            //缴交业务类型
            if (null != spsTask.getBstypeId() && (BsType.INSUR_BACK.code().equals(spsTask.getBstypeId()) || BsType.FUND_BACK.code().equals(spsTask.getBstypeId()))) {
                Map<String, String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
                //补缴基数
                if (StringUtils.equals(null, jsonMap.get("insurancebase"))) {
                    result.setError("缴费基数不能为空!");
                    result.setSuccess(false);
                    return false;
                }

                //补缴起始日期
                if (StringUtils.equals(null, jsonMap.get("supplementarybegindate"))) {
                    result.setSuccess(false).setError("补缴开始时间不能为空!");
                    return false;
                }
                //补缴结束日期
                if (StringUtils.equals(null, jsonMap.get("supplementaryenddate"))) {
                    result.setError("补缴结束时间不能为空!");
                    result.setSuccess(false);
                    return false;
                }
                String startDate = jsonMap.get("supplementarybegindate");
                String endDate = jsonMap.get("supplementaryenddate");

                if (!valiteDate(startDate, endDate, result)) {
                    result.setSuccess(false);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {
        //新增补缴任务单 调用算费
        try {
            this.insFunSupply(info, spsTask, cmEmployee);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(BsType.INSUR_BACK.getCode().equals(spsTask.getBstypeId())){
            cmEmployee.setInsuranceState(InsuranceState.ON.name());
        }
        if(BsType.FUND_BACK.getCode().equals(spsTask.getBstypeId())){
            cmEmployee.setFundState(InsuranceState.ON.name());
        }
    }

    @Override
    public void dealClosedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {
        //终止移除补缴费用段
        //cmEmployeeInsuranceService.cancelEmployeeInsuranceByBeginTaskId(info,spsTask.getTaskId(),"PATCH");
        if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType())){
            if (BsType.INSUR_BACK.code().equals(spsTask.getBstypeId()) && !InsuranceState.INCREASING.name().equals(cmEmployee.getInsuranceState()))
                cmEmployee.setInsuranceState(InsuranceState.OFF.name());
            if (BsType.FUND_BACK.code().equals(spsTask.getBstypeId()) && !InsuranceState.INCREASING.name().equals(cmEmployee.getFundState()))
                cmEmployee.setFundState(InsuranceState.OFF.name());
        }
    }

    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {
    }

    /**
     * 保存任务单后处理业务
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     * @return
     */
    @Override
    public boolean busAfterSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {
        if("TODO".equals(spsTask.getType()) || "SUBMIT".equals(spsTask.getType()) ){
            if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType())){
                if (BsType.INSUR_BACK.code().equals(spsTask.getBstypeId()) && !InsuranceState.ON.name().equals(cmEmployee.getInsuranceState())){
                    if("TODO".equals(spsTask.getType()))
                        cmEmployee.setInsuranceState(InsuranceState.INCREASING.name());
                    else
                        cmEmployee.setInsuranceState(InsuranceState.ON.name());
                }

                if (BsType.FUND_BACK.code().equals(spsTask.getBstypeId()) && !InsuranceState.ON.name().equals(cmEmployee.getFundState())){
                    if("TODO".equals(spsTask.getType()))
                        cmEmployee.setFundState(InsuranceState.INCREASING.name());
                    else
                        cmEmployee.setFundState(InsuranceState.ON.name());
                }
            }
        }
        return true;
    }

    /**
     * 任务单补缴--单次费用
     *
     * @throws ParseException
     */
    private void insFunSupply(ContextInfo info, SpsTask spsTask) throws ParseException {
        Map<String, String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
        String supplyBase = "";
        String supplyStart = "";
        String supplyEnd = "";
        if (spsTask.getBstypeId() == 29) {
            if (!StringUtils.equals(null, jsonMap.get("insurancebase"))) {
                supplyBase = jsonMap.get("insurancebase");//补缴基数
            }
        }
        if (spsTask.getBstypeId() == 30) {
            if (!StringUtils.equals(null, jsonMap.get("insurancebase"))) {
                supplyBase = jsonMap.get("insurancebase");//补缴基数
            }
        }

        if (!StringUtils.equals(null, jsonMap.get("supplementarybegindate"))) {
            supplyStart = jsonMap.get("supplementarybegindate");//补缴起始日期
        }
        if (!StringUtils.equals(null, jsonMap.get("supplementaryenddate"))) {
            supplyEnd = jsonMap.get("supplementaryenddate");//补缴结束日期
        }
        List<BsRatioCalcInterface> empinsurances = new ArrayList<BsRatioCalcInterface>();
        String jsonArrayStr = jsonMap.get("insurance");
        if (jsonMap.get("insurance") != null) {
            TypeReference<List<InsuranceTypeDto>> ref = new TypeReference<List<InsuranceTypeDto>>(){};
            List<InsuranceTypeDto> insurances = JSON.parseObject(jsonArrayStr,ref);
            for (InsuranceTypeDto dto : insurances) {
                SpsFeeEmponcedetail spsFeeEmponcedetail = new SpsFeeEmponcedetail();
                spsFeeEmponcedetail.setRatioId(dto.getRatioId());
                spsFeeEmponcedetail.setInsuranceId(dto.getInsuranceTypeId());
                empinsurances.add(spsFeeEmponcedetail);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date startDate = sdf.parse(supplyStart);
        Date endDate = sdf.parse(supplyEnd);
        BigDecimal payBase = new BigDecimal(supplyBase);
        spsFeeEmponceService.insFunSupply(info, spsTask.getSpId(), spsTask.getTaskId(), spsTask.getEmpId(), spsTask.getBstypeId(), spsTask.getAreaId(), spsTask.getSchemeId(), payBase, empinsurances, startDate, endDate);
    }

    /**
     * 产生补缴费用段
     * @param info
     * @param spsTask
     * @param employee
     * @throws ParseException
     */
    private void insFunSupply(ContextInfo info, SpsTask spsTask,CmEmployee employee) throws ParseException {
        Map<String, String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
        String supplyStart = "";
        String supplyEnd = "";
        if (!StringUtils.equals(null, jsonMap.get("supplementarybegindate"))) {
            supplyStart = jsonMap.get("supplementarybegindate");//补缴起始日期
        }
        if (!StringUtils.equals(null, jsonMap.get("supplementaryenddate"))) {
            supplyEnd = jsonMap.get("supplementaryenddate");//补缴结束日期
        }
        if (StringUtils.isNotBlank(jsonMap.get("insurance"))) {
            employee.setCmEmployeeInsurances(convertJsonToEmpInsu(spsTask,jsonMap.get("insurance"),supplyStart,supplyEnd));
            cmEmployeeInsuranceService.adjustEmployeeInsurance(info, info.getOrgId(), employee, null , supplyStart, supplyEnd, spsTask.getTaskId(),"PATCH");
        }
    }

    /**
     * 将险种json转化为计算list
     * @param json
     * @return
     */
    private List<CmEmployeeInsurance> convertJsonToEmpInsu(SpsTask spsTask,String json,String beginPeriod,String endPeriod){
        List<CmEmployeeInsurance> list = new ArrayList<>();
        TypeReference<List<InsuranceTypeDto>> ref = new TypeReference<List<InsuranceTypeDto>>(){};
        List<InsuranceTypeDto> insurances = JSON.parseObject(json,ref);
        if(CollectionUtils.isNotEmpty(insurances)){
            /**
             * 计算补缴月份
             */
            List<String> allPatchMonth = new ArrayList<>();
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
                Calendar min = Calendar.getInstance();
                Calendar max = Calendar.getInstance();

                min.setTime(sdf.parse(beginPeriod));
                min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
                max.setTime(sdf.parse(endPeriod));
                max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
                Calendar curr = min;
                while (curr.before(max)) {
                    allPatchMonth.add(sdf.format(curr.getTime()));
                    System.out.println(sdf.format(curr.getTime()));
                    curr.add(Calendar.MONTH, 1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            CmEmployeeInsurance employeeInsurance;
            for(String month : allPatchMonth){
                for(InsuranceTypeDto insu:insurances){
                    employeeInsurance = new CmEmployeeInsurance();
                    employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
                    employeeInsurance.setCorpPaybase(insu.getCorpPaybase());
                    employeeInsurance.setEmpPaybase(insu.getEmpPaybase());
                    employeeInsurance.setRatioId(insu.getRatioId());
                    employeeInsurance.setEmpRatio(insu.getPersonalRatio());
                    employeeInsurance.setCorpRatio(insu.getCompanyRatio());
                    employeeInsurance.setBeginPeriod(insu.getBeginDate());
                    employeeInsurance.setPayType("PATCH");
                    employeeInsurance.setPeriod(spsTask.getExecuteDate());
                    employeeInsurance.setExtend(true);
                    employeeInsurance.setBeginPeriod(month);
                    employeeInsurance.setEndPeriod(month);
                    list.add(employeeInsurance);
                }
            }
        }
        return list;
    }

    /**
     * 验证开始时间 小于结束月份，  并且 结束月份 小于当前月份
     * @param startDate yyyy-MM
     * @param endDate   yyyy-MM
     * @return
     */
    private static boolean valiteDate(String startDate, String endDate, Result result) {
        String prefix1 = StringUtils.substring(startDate, 0, 7);
        String prefix2 = StringUtils.substring(endDate, 0, 7);
        Date start = DateUtil.parseDate(prefix1 + "-00", "yyyy-MM-dd");
        Date end = DateUtil.parseDate(prefix2 + "-00", "yyyy-MM-dd");

        if (start.getTime() > end.getTime()) {
            result.setError("补缴开始时间不能大于补缴结束时间!");
            return false;
        }
        String prefix3 = StringUtils.substring(DateUtil.getCurYearMonthStr(), 0, 7);
        Date now = DateUtil.parseDate(prefix3 + "-00", "yyyy-MM-dd");
        if (end.getTime() > now.getTime()) {
            result.setError("补缴结束时间不能大于当前时间!");
            return false;
        }
        return true;
    }

}
