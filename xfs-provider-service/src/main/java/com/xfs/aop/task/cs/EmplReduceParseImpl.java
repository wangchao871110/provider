package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 离职、减员
 */
@Service
public class EmplReduceParseImpl extends BaseTaskDataParse {

    @Autowired
    CmEmployeeDao cmEmployeeDao;

    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;

    @Override
    public boolean checkBussinessByTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee cmEmployee, Result result) {
        if(!super.busDataValidity(info,spsTask,businessParame,businessFieldMap,cmEmployee,result))
            return false;
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(), ref);
        if (curMap.containsKey("endDate") && StringUtils.isNotBlank(curMap.get("endDate"))) {
            spsTask.setBeginDate(curMap.get("endDate"));
        }

        CmEmployee employee = new CmEmployee();
        employee.setEmpId(spsTask.getEmpId());
        employee = cmEmployeeService.findByPK(employee);
        if (BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId()) && StringUtils.isBlank(spsTask.getSource()) && null == spsTask.getTaskId()) {
            if (!"ON".equals(employee.getInsuranceState())) {
                result.setSuccess(false).setError("人员社保参保状态必须为在缴中");
                return false;
            }
        }

        if (BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId()) && StringUtils.isBlank(spsTask.getSource()) && null == spsTask.getTaskId()) {
            if (!"ON".equals(employee.getFundState())) {
                result.setSuccess(false).setError("人员公积金参保状态必须为在缴中");
                return false;
            }
        }

        if(!spsTask.getCpId().equals(employee.getCpId())){
            result.setSuccess(false).setError("当前所选企业与人员所在企业不符");
            return false;
        }

        return true;
    }

    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        CmEmployee CmEmployeeOld = cmEmployeeDao.findByPK(cmEmployee);
        if(BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId()) && ("ON".equals(CmEmployeeOld.getInsuranceState()))){
            cmEmployee.setInsuranceState("DECREASING");
         }
        if(BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId()) && ("ON".equals(CmEmployeeOld.getFundState()))){
            cmEmployee.setFundState("DECREASING");
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }
    }

    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        List<Integer> list = new ArrayList<Integer>();
        String endDate = JSON.parseObject(spsTask.getJson()).getString("endDate");
        endDate = getSpecifiedMonth(endDate, -1);
        // 社保(4=减员) 、
        if(BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId())){
            cmEmployee.setInsuranceState("DECREASED");
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "INSURANCE", endDate, spsTask.getTaskId());

        }
        // 公积金(11=减少)
        if(BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId())){
            cmEmployee.setFundState("DECREASED");
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "FUND", endDate, spsTask.getTaskId());

        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true).setError("完成任务单执行成功");
        }else{
            result.setSuccess(false).setError("完成任务单执行失败！");
        }
    }

    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        CmEmployee CmEmployeeOld = cmEmployeeDao.findByPK(cmEmployee);
        if(null != CmEmployeeOld){
            if (BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId()) && ("DECREASING".equals(CmEmployeeOld.getInsuranceState()))) {
                cmEmployee.setInsuranceState("ON");
            }
            if (BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId()) && ("DECREASING".equals(CmEmployeeOld.getFundState()))) {
                cmEmployee.setFundState("ON");
            }
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true).setError("完成任务单执行成功");
        }else{
            result.setSuccess(false).setError("完成任务单执行失败！");
        }
    }

    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {

    }

    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        List<Integer> list = new ArrayList<Integer>();
        if(BsType.REDUCE_INSURANCE.code().equals(spsTask.getBstypeId())){
            cmEmployee.setInsuranceState("DECREASED");
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "INSURANCE", spsTask.getBeginDate(), spsTask.getTaskId());
        }
        if(BsType.QUIT_INSURANCE.code().equals(spsTask.getBstypeId())){
            cmEmployee.setFundState("DECREASED");
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "FUND", spsTask.getBeginDate(), spsTask.getTaskId());
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }
    }

    /**
     * 获得指定日期的后n月
     * @param specifiedDay
     * @param n
     * @return
     */
    private static String getSpecifiedMonth(String specifiedDay, int n){
        String dayAfter;
        try {
            Calendar c = Calendar.getInstance();
            Date date = new SimpleDateFormat("yyyy-MM").parse(specifiedDay);
            c.setTime(date);
            int day=c.get(Calendar.MONTH);
            c.set(Calendar.MONTH, day + n);

            dayAfter = new SimpleDateFormat("yyyy-MM").format(c.getTime());
        } catch (ParseException e) {
            return specifiedDay;
        }
        return dayAfter;
    }
}
