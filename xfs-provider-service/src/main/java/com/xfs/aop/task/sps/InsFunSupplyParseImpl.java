package com.xfs.aop.task.sps;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dto.BusinessField;
import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.bill.service.SpsFeeEmponceService;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.model.SpsTask;
import com.xfs.common.Result;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.model.CmEmployee;

/**
 *    社保 公积金 补缴
 * @author xiyanzhang
 *
 */
@Service
public class InsFunSupplyParseImpl extends BaseTaskDataParse {

    @Autowired
    SpsFeeEmponceService spsFeeEmponceService;

    @Autowired
    CmEmployeeDao cmEmployeeDao;

    @Override
    public boolean checkBussinessByTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            //29==INSURANCE 补缴	 30==FUND 补缴
            if(29 == spsTask.getBstypeId() || 30 == spsTask.getBstypeId()){
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,
                                  Map<String, BusinessField> businessFieldMap, Result result) {
        if (null != spsTask  && null != spsTask.getEmpId() && null != spsTask.getBstypeId() && null != spsTask.getJson()) {
            //缴交业务类型
            if(null != spsTask.getBstypeId() && (29 == spsTask.getBstypeId() || 30 == spsTask.getBstypeId()))
                return true;
            Map<String,String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
            //补缴基数
            if(!StringUtils.equals(null, jsonMap.get("insurancebase")))
                return true;
            //补缴起始日期
            if (!StringUtils.equals(null, jsonMap.get("supplementarybegindate")))
                return true;
            //补缴结束日期
            if (!StringUtils.equals(null, jsonMap.get("supplementaryenddate")))
                return true;
        }
        return false;
    }

    @Override
    public void dealBeforeSave(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        //新增补缴任务单 调用算费
        Map<String,String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
        try {

            this.insFunSupply(info,spsTask);

        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        if(spsTask.getBstypeId() == 4){
            cmEmployee.setInsurancePeriod(jsonMap.get("supplementarybegindate"));
        }

        if(spsTask.getBstypeId() == 11){
            cmEmployee.setFundPeriod(jsonMap.get("supplementarybegindate"));
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
        //补缴成功不需要回写状态
    }

    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        //	终止补缴 把算费 逻辑删除掉 source=1 task_id  dr改成1 在员工一次性算费 service里写 sps_fee_emponce
        SpsFeeEmponce vo = new SpsFeeEmponce();
        vo.setSourceid(spsTask.getTaskId());
        vo.setSource(1);
        vo.setDr(1);
        result = spsFeeEmponceService.removeInsFunSupply(info,vo);
    }

    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        //新增补缴任务单 调用算费
        Map<String,String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
        try {

            this.insFunSupply(info,spsTask);

        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        if (spsTask.getBstypeId() == 4) {
            cmEmployee.setInsurancePeriod(jsonMap.get("supplementarybegindate"));
        }

        if(spsTask.getBstypeId() == 11){
            cmEmployee.setFundPeriod(jsonMap.get("supplementarybegindate"));
        }

        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups > 0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }

    }

    /**
     * 任务单补缴
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public void insFunSupply(ContextInfo info,SpsTask spsTask) throws ParseException{

        Map<String,String> jsonMap = JSON.parseObject(spsTask.getJson(), Map.class);
        String supplyBase = "";
        String supplyStart = "";
        String supplyEnd = "";
        if(spsTask.getBstypeId() == 29) {
            if(!StringUtils.equals(null, jsonMap.get("insurancebase"))){
                supplyBase = jsonMap.get("insurancebase");//补缴基数
            }
        }

        if(spsTask.getBstypeId() == 30) {
            if(!StringUtils.equals(null, jsonMap.get("fundbase"))){
                supplyBase = jsonMap.get("fundbase");//补缴基数
            }
        }

        if (!StringUtils.equals(null, jsonMap.get("supplementarybegindate"))) {
            supplyStart = jsonMap.get("supplementarybegindate");//补缴起始日期
        }
        if (!StringUtils.equals(null, jsonMap.get("supplementaryenddate"))) {
            supplyEnd = jsonMap.get("supplementaryenddate");//补缴结束日期
        }
        //  JSONArray jsonArray = jsonMap.get("insurance");
        List<BsRatioCalcInterface> empinsurances = new ArrayList<BsRatioCalcInterface>();
        // String jsonArrayStr = JSON.toJSONString(jsonMap.get("insurance"));
        JSONArray jsonArray = JSON.parseArray(jsonMap.get("insurance"));
        if (jsonMap.get("insurance") != null) {
            // JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Integer id = Integer.valueOf(jsonObject.getString("insuranceTypeId"));
                Integer ratioId =  Integer.valueOf(jsonObject.getString("ratioId"));
                SpsFeeEmponcedetail spsFeeEmponcedetail = new SpsFeeEmponcedetail();
                spsFeeEmponcedetail.setRatioId(ratioId);
                spsFeeEmponcedetail.setInsuranceId(id);
                empinsurances.add(spsFeeEmponcedetail);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date startDate = sdf.parse(supplyStart);
        Date endDate = sdf.parse(supplyEnd);
        BigDecimal payBase = new BigDecimal(supplyBase);
        spsFeeEmponceService.insFunSupply(info,spsTask.getSpId(), spsTask.getTaskId(), spsTask.getEmpId(), spsTask.getBstypeId(),spsTask.getAreaId(),spsTask.getSchemeId(), payBase, empinsurances, startDate, endDate);
    }

}
