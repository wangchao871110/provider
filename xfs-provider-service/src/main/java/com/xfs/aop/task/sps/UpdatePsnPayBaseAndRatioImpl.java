package com.xfs.aop.task.sps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.service.SysDictitemService;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BsArearatioverService;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.dao.CmEmployeeInsuranceDao;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.service.SpsSchemeEmpService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 调整基数和调整比例
 */
@Service
public class UpdatePsnPayBaseAndRatioImpl extends BaseTaskDataParse {
    private static final Logger log = Logger.getLogger(UpdatePsnPayBaseAndRatioImpl.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;

    @Autowired
    BsArearatioverService bsArearatioverService;

    @Autowired
    CmEmployeeInsuranceDao cmEmployeeInsuranceDao;

    @Autowired
    CmEmployeeDao cmEmployeeDao;
    @Autowired
    private BsAreaDao bsAreaDao;
    @Autowired
    private SpsFixedpointhospitalService spsFixedpointhospitalService;
    @Autowired
    private SysDictitemService sysDictitemService;

    @Autowired
    private SpsSchemeEmpService spsSchemeEmpService;



    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (37 == spsTask.getBstypeId()){
                return true;
            }
        }
        return false;
    }


    /**
     * 校验
     * @param info
     * @param spsTask
     * @param businessParame
     * @param businessFieldMap
     * @param result
     * @return
     */

    @Override
    public boolean beforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, Result result) {
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(spsTask.getEmpId());
        employee = cmEmployeeService.findByPK(employee);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        JSONObject jsonObject = JSONObject.fromObject(spsTask.getJson());
        String insuranceList = jsonObject.get("insuranceList").toString();
        JSONArray jsonArray = JSONArray.parseArray(insuranceList);
        int jsonArrayLength  = jsonArray.size();
        for(int i = 0;i<jsonArrayLength;i++){
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> curMap = JSON.parseObject(jsonArray.get(i).toString(),ref);
            String beginPeriod = curMap.get("beginPeriod").trim();
            String oldBeginData = curMap.get("oldBeginData").trim();
            String oldEndData = curMap.get("oldEndData").trim();
            String  minbeginPeriod= curMap.get("minbeginPeriod").trim();
            String endPeriod = curMap.get("endPeriod").trim();
            String changePeriod = curMap.get("changePeriod").trim();
            String  maxEndPeriod= curMap.get("maxEndPeriod").trim();
            boolean isExtend = ("true").equals(curMap.get("isExtend"));
            String insuranceName = curMap.get("insuranceName");
            String ratioId = curMap.get("ratioId");
            BigDecimal basePsnPay = new BigDecimal(curMap.get("basePsnPay"));
            BigDecimal baseCorpPay =new BigDecimal(curMap.get("baseCorpPay"));

            try {

                boolean isChangeBeginPeriod = false;
                boolean isChangeEndPeriod = false;
                if(StringUtils.isNotEmpty(beginPeriod)){
                    if(StringUtils.isNotEmpty(oldBeginData)&&(format.parse(oldBeginData).compareTo(format.parse(beginPeriod)) == 0)){
                        isChangeBeginPeriod = false;
                    }else{
                        isChangeBeginPeriod = true;
                    }
                }else {
                    if (StringUtils.isEmpty(oldBeginData)) {
                        isChangeBeginPeriod = false;
                    } else {
                        isChangeBeginPeriod = true;
                    }
                }
                if(StringUtils.isNotEmpty(endPeriod)){
                    if(StringUtils.isNotEmpty(oldEndData)&&(format.parse(oldEndData).compareTo(format.parse(endPeriod)) == 0)){
                        isChangeEndPeriod = false;
                    }else{
                        isChangeEndPeriod = true;
                    }
                }else {
                    if (StringUtils.isEmpty(oldEndData)) {
                        isChangeEndPeriod = false;
                    } else {
                        isChangeEndPeriod = true;
                    }
                }
                if(StringUtils.isNotEmpty(changePeriod)&&isChangeEndPeriod)
                {
                    result.setSuccess(false).setError(insuranceName + "不能同时更改缴纳结束月和缴纳变更月");
                    return false;
                }
                if(StringUtils.isNotEmpty(changePeriod)&&isChangeBeginPeriod)
                {
                    result.setSuccess(false).setError(insuranceName + "不能同时更改缴纳起始月和缴纳变更月");
                    return false;
                }
                if(StringUtils.isEmpty(beginPeriod) && StringUtils.isEmpty(endPeriod) &&StringUtils.isEmpty(changePeriod)){
                    result.setSuccess(false).setError(insuranceName + "缴纳起始月，缴纳结束月和缴纳变更月不能同时为空");
                    return false;
                }
                if(StringUtils.isEmpty(beginPeriod) && StringUtils.isNotEmpty(endPeriod)&&StringUtils.isEmpty(changePeriod) ){
                    result.setSuccess(false).setError(insuranceName + "缴纳起始月不为空");
                    return false;
                }

                if(StringUtils.isNotEmpty(beginPeriod)&&StringUtils.isNotEmpty(changePeriod)&&(format.parse(beginPeriod).compareTo(format.parse(changePeriod)) > 0)){
                    result.setSuccess(false).setError(insuranceName + "缴纳起始月大于缴纳变更月");
                    return false;
                }

                if(StringUtils.isNotEmpty(changePeriod) && StringUtils.isNotEmpty(endPeriod) &&format.parse(endPeriod).compareTo(format.parse(changePeriod)) < 0){
                    result.setSuccess(false).setError(insuranceName + "缴纳结束月小于缴纳变更月");
                    return false;
                }
                if(StringUtils.isEmpty(ratioId)){
                    result.setSuccess(false).setError(insuranceName + "险种比例为空");
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
                result.setSuccess(false).setError(insuranceName + "服务器错误");
                return false;
            }
        }

        //完成任务单

        return true;
    }

    /**
     *  NOTODO
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 16:20
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:20
     *  @updateAuthor  :
     */
    @Override
    public void dealBeforeSave(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {

    }

    /**
     *  做任务
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 16:21
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:21
     *  @updateAuthor  :
     */

    @Override
    public void dealToDoTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        Integer empId = spsTask.getEmpId();
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(empId);
        employee = cmEmployeeDao.findByPK(employee);

        List<CmEmployeeInsurance> employeeInsurancesList = new ArrayList<CmEmployeeInsurance>();
        Map<Integer, Integer> insMap = new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(spsTask.getJson());
        String insuranceList = jsonObject.get("insuranceList").toString();
        JSONArray insuranceArray = JSON.parseArray(insuranceList);
        int insuranceLength = insuranceArray.size();
        if (insuranceArray != null && insuranceLength > 0) {
            for (int i = 0; i < insuranceLength; i++) {

                String insuranceString = insuranceArray.getString(i);
                JSONObject insuranceObject = JSONObject.fromObject(insuranceString);
                CmEmployeeInsurance cmEmployeeInsurance = new CmEmployeeInsurance();
                cmEmployeeInsurance.setEmpId(empId);
                cmEmployeeInsurance.setInsuranceId(insuranceObject.optInt("insuranceId", 0));
                cmEmployeeInsurance.setRatioId(insuranceObject.getInt("ratioId"));
                cmEmployeeInsurance.setDr(0);
                cmEmployeeInsurance.setCorpPaybase(com.xfs.common.util.StringUtils
                        .toBigDecimal(insuranceObject.optString("baseCorpPay", "0")));
                cmEmployeeInsurance.setEmpPaybase(com.xfs.common.util.StringUtils
                        .toBigDecimal(insuranceObject.optString("basePsnPay", "0")));

                cmEmployeeInsurance.setBeginPeriod(StringUtils.isEmpty(insuranceObject.optString("beginPeriod"))?null:insuranceObject.optString("beginPeriod"));
                cmEmployeeInsurance.setEndPeriod(StringUtils.isEmpty(insuranceObject.optString("endPeriod"))?null:insuranceObject.optString("endPeriod"));

                cmEmployeeInsurance.setCorpAddition(com.xfs.common.util.StringUtils
                        .toBigDecimal(insuranceObject.optString("corpAddition", "0")).divide(new BigDecimal(100)));

                cmEmployeeInsurance.setPsnAddition(com.xfs.common.util.StringUtils
                        .toBigDecimal(insuranceObject.optString("psnAddition", "0")).divide(new BigDecimal(100)));


                cmEmployeeInsurance.setExtend("true".equals(insuranceObject.optString("isExtend")));
                if(!"true".equals(insuranceObject.optString("isExtend"))){
                    cmEmployeeInsurance.setBeginPeriod(StringUtils.isEmpty(insuranceObject.optString("changePeriod"))?null:insuranceObject.optString("changePeriod"));
                }

                employeeInsurancesList.add(cmEmployeeInsurance);
                insMap.put(cmEmployeeInsurance.getInsuranceId(), cmEmployeeInsurance.getInsuranceId());
            }
            CmEmployee cmEmployee = new CmEmployee();
            cmEmployee.setEmpId(employee.getEmpId());
            employee.setCmEmployeeInsurances(employeeInsurancesList);
            // adjust内部只能增加数据，不能减少，外层删除不用险种
            Result r = cmEmployeeInsuranceService.adjustEmployeeInsurance(info, info.getOrgId(), employee, null, null, spsTask.getTaskId());
            if (!r.isSuccess()) {
                throw new BusinessException(r.getError());
            }
        }
        spsTask.setType("COMPLETED");
        dealCompletedTask(info,spsTask,businessParame,result);
    }

    /**
     *  任务完成
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 16:21
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:21
     *  @updateAuthor  :
     */
    @Override
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        Integer empId = spsTask.getEmpId();
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(empId);
        CmEmployee curr_employee = cmEmployeeService.findByPk(empId);
        List<Integer> empids = new ArrayList<Integer>();
        empids.add(empId);
        List<CmEmployeeInsurance> cmEmployeeInsurances = cmEmployeeInsuranceService.findEmployeeInsuranceByEmployee(empids,null);
        boolean isInsuranceLeave = true;
        //选出是否有公积金,改变公积金的缴纳状态
        for (CmEmployeeInsurance cmice : cmEmployeeInsurances) {
            int insuranceId = cmice.getInsuranceId();
            if (insuranceId == 6) {
                if (StringUtils.isNotEmpty(cmice.getEndPeriod())) {
                    employee.setFundState("DECREASED");
                }else {
                    employee.setFundState("ON");
                }
            }else{
                if (StringUtils.isBlank(cmice.getEndPeriod())) {
                    isInsuranceLeave = false;
                }
            }
        }

        if(isInsuranceLeave && "ON".equals(curr_employee.getInsuranceState())){
            employee.setInsuranceState("DECREASED");
        }else if(!isInsuranceLeave){
            employee.setInsuranceState("ON");
        }
        cmEmployeeService.update(info,employee);
    }

    /**
     *
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 16:21
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:21
     *  @updateAuthor  :
     */
    @Override
    public void dealClosedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }


    /**
     *
     *  @param        * @return    :
     *  @createDate   : 2017/1/18 16:21
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:21
     *  @updateAuthor  :
     */
    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    /**
     *
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 16:21
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 16:21
     *  @updateAuthor  :
     */

    @Override
    public void dealSubmitTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }
}
