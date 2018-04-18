package com.xfs.aop.task.sps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.model.SpsTask;
import com.xfs.common.Result;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeInsuranceService;

/**
 * 离职、减员
 */
@Service
public class EmplReduceParseImpl extends BaseTaskDataParse {

    /*@Autowired
    SysUploadfileService sysUploadfileService;
    @Autowired
    SpsSchemeService spsSchemeService;
    @Autowired
    CmEmployeeService cmEmployeeService;
    @Autowired
    SpsTaskService spsTaskService;*/
    @Autowired
    CmEmployeeDao cmEmployeeDao;

    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;



    @Override
    public boolean checkBussinessByTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {
//        CmEmployee cmEmployee = new CmEmployee();
//        cmEmployee.setEmpId(spsTask.getEmpId());
//        cmEmployee.setCpId(spsTask.getCpId());
//        cmEmployee.setDr(0);
//        //校验员工是否存在
//        boolean exist = cmEmployeeDao.existsEmp(cmEmployee);
//        if(exist){
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (4 == spsTask.getBstypeId() || 11 ==spsTask.getBstypeId()){
                return true;
            }
        }
//        }
        return false;
    }

    @Override
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, Result result) {
     /*   if(spsTask.getBstypeId() == 4 || spsTask.getBstypeId() == 16){
            return true;
        }*/
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(), ref);
        if (curMap.containsKey("endDate") && StringUtils.isNotBlank(curMap.get("endDate"))) {
            spsTask.setBeginDate(curMap.get("endDate"));
        }

        CmEmployee employee = new CmEmployee();
        employee.setEmpId(spsTask.getEmpId());
        employee = cmEmployeeService.findByPK(employee);
        if (spsTask.getBstypeId() == 4) {
            if (!"ON".equals(employee.getInsuranceState())) {
                result.setSuccess(false).setError("人员社保参保状态必须为在缴中");
                return false;
            }
        }

        if (spsTask.getBstypeId() == 11) {
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
    public void dealBeforeSave(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {

    }

    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        List<Integer> list = new ArrayList<Integer>();
        if(spsTask.getBstypeId() == 4){
            cmEmployee.setInsuranceState("DECREASING");
            //     cmEmployee.setInsurancePeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "INSURANCE", spsTask.getBeginDate(), spsTask.getTaskId());
        }

        if(spsTask.getBstypeId() == 11){
            cmEmployee.setFundState("DECREASING");
            //    cmEmployee.setFundPeriod(spsTask.getBeginDate());
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

    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        // 社保(4=减员) 、
        if(spsTask.getBstypeId() ==4 ){
            cmEmployee.setInsuranceState("DECREASED");
        }
        // 公积金(11=减少)
        if(spsTask.getBstypeId() ==11){
            cmEmployee.setFundState("DECREASED");
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true).setError("完成任务单执行成功");
        }else{
            result.setSuccess(false).setError("完成任务单执行失败！");
        }
    }

    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        dealCompletedTask(info,spsTask,businessParame,result);
    }

    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        List<Integer> list = new ArrayList<Integer>();
        if(spsTask.getBstypeId() == 4){
            cmEmployee.setInsuranceState("DECREASING");
            //    cmEmployee.setInsurancePeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "INSURANCE", spsTask.getBeginDate(), spsTask.getTaskId());
        }

        if(spsTask.getBstypeId() == 11){
            cmEmployee.setFundState("DECREASING");
            list.add(cmEmployee.getEmpId());
            //   cmEmployee.setFundPeriod(spsTask.getBeginDate());
            cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(info,list, "FUND", spsTask.getBeginDate(), spsTask.getTaskId());
        }

        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }
    }
}
