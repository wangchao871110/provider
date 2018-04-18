package com.xfs.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsBatchReduceEmplService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量减员
 * Created by yangf on 2016/12/16.
 */
@Service
public class SpsBatchReduceEmplServiceImpl implements SpsBatchReduceEmplService {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsBatchReduceEmplServiceImpl.class);
    @Autowired
    private CmEmployeeService cmEmployeeService;
    @Autowired
    private TaskAspectService taskAspectService;
    /**
     * 处理批量减员
     *  @param   insuranceFeeMoon, insuranceOpMoon, fundFeeMoon, fundOpMoon, decreasereason, reduceJson
     * @return    : com.xfs.common.Result
     *  @createDate   : 2016/12/16 19:27
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/16 19:27
     *  @updateAuthor  :
     */
    @Override
    public Result handleBatchReduceEmpl(ContextInfo cti,String insuranceFeeMoon, String insuranceOpMoon, String fundFeeMoon, String fundOpMoon, String decreasereason , List<Map<String,String>> empInfo) {
        Result result = new Result();
        CmEmployee empQuery = new CmEmployee();
        List<SpsTask> spsTasks = new ArrayList<SpsTask>();
        //缓存 防止重复查询
        Map<Integer,CmEmployee> empMap = new HashMap<>();
        //设置离职日期用
        List<CmEmployee> insuEmp = new ArrayList<>();
        for(Map<String,String> map: empInfo){
            empQuery.setEmpId(Integer.parseInt(map.get("empId")));
            CmEmployee ExistEmp = null;
            if(empMap.containsKey(empQuery.getEmpId())){
                ExistEmp = empMap.get(empQuery.getEmpId());
            }else {
                ExistEmp = cmEmployeeService.findByPK(empQuery);
                empMap.put(ExistEmp.getEmpId(),ExistEmp);
            }
            if ("INSURANCE".equals(map.get("type"))){

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("endDate", insuranceFeeMoon);
                jsonObject.put("beginDate", insuranceFeeMoon);
                jsonObject.put("decreasereason", decreasereason);
                SpsTask spsTask = new SpsTask();
                spsTask.setCpId(ExistEmp.getCpId());
                spsTask.setEmpId(ExistEmp.getEmpId());
                spsTask.setSpId(cti.getOrgId());
                spsTask.setBstypeId(4);
                spsTask.setBeginDate(insuranceFeeMoon);
                spsTask.setExecuteDate(insuranceOpMoon);
                spsTask.setType("SUBMIT");
                spsTask.setDr(0);
                spsTask.setSchemeId(ExistEmp.getSchemeId());
                spsTask.setJson(jsonObject.toString());
                spsTask.setCreateBy(cti.getUserId());
                spsTasks.add(spsTask);
                insuEmp.add(ExistEmp);
            }else
            if ("FUND".equals(map.get("type"))) {
                SpsTask spsTask = new SpsTask();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("endDate", fundFeeMoon);
                jsonObject.put("beginDate", fundFeeMoon);
                spsTask.setCpId(ExistEmp.getCpId());
                spsTask.setEmpId(ExistEmp.getEmpId());
                spsTask.setSpId(cti.getOrgId());
                spsTask.setBstypeId(11);
                spsTask.setBeginDate(fundFeeMoon);
                spsTask.setExecuteDate(fundOpMoon);
                spsTask.setType("SUBMIT");
                spsTask.setDr(0);
                spsTask.setJson(jsonObject.toString());
                spsTask.setSchemeId(ExistEmp.getSchemeId());
                spsTask.setCreateBy(cti.getUserId());
                spsTasks.add(spsTask);
            }
        }
        boolean isSuccess = true;
        if (null != spsTasks && spsTasks.size() > 0) {
            for (SpsTask spsTask : spsTasks) {
                taskAspectService.saveTask(cti, spsTask, null, result);
                if (!result.isSuccess()) {
                    BusinessException e = new BusinessException("操作失败");
                    log.error("批量操作员工离职失败，spsBatchChangeRatio:"+ JSON.toJSONString(spsTask),e);
                    throw e;
                }
            }
            //离职日期
            for(CmEmployee emp:insuEmp){
                CmEmployee e = new CmEmployee();
                e.setEmpId(emp.getEmpId());
                e.setLeaveTime(emp.getLeaveTime());
                cmEmployeeService.update(cti, e);
            }

        }

        result.setSuccess(true);
        return result;
    }
}
