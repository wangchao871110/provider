package com.xfs.aop.task.sps;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.Result;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.model.CmEmployee;
import com.xfs.sp.dto.HospitalDto;

/**
 * 定点医院
 * Created by yangf on 2016/10/24.
 */
@Service
public class HospitalParseImpl extends BaseTaskDataParse {
    @Autowired
    CmEmployeeDao cmEmployeeDao;
    @Autowired
    private SpsFixedpointhospitalService spsFixedpointhospitalService;
    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        cmEmployee.setDr(0);
        //校验员工是否存在
        boolean exist = cmEmployeeDao.existsEmp(cmEmployee);
        if(exist) {
            if (null != spsTask && null != spsTask.getBstypeId()) {
                if (7 == spsTask.getBstypeId() || 1 == spsTask.getBstypeId()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, Result result) {

        return true;
    }

    @Override
    public void dealBeforeSave(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {

    }

    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    @Override
    public void dealErrorTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {

    }

    /**
     * 校验定点医院
     * @return
     */
    private String verifyHospital(String hospitals,Result result){
        if(StringUtils.isBlank(hospitals)){
            result.setSuccess(false).setError("定点医院不能为空");
            return null;
        }
        TypeReference<List<HospitalDto>> ref = new TypeReference<List<HospitalDto>>(){};
        List<HospitalDto> hospitalList = null;
        try {
            hospitalList = JSON.parseObject(hospitals,ref);
        } catch (Exception e) {
            result.setSuccess(false).setError("定点医院格式错误");
            return null;
        }
        SpsFixedpointhospital query = new SpsFixedpointhospital();
        Integer ddyy = 0;
        Integer sqyy = 0;
        for(HospitalDto hospitalDto: hospitalList){
            if(StringUtils.isBlank(hospitalDto.getCode())){
                result.setSuccess(false).setError("定点医院代码不能为空");
                return null;
            }
            if(StringUtils.isBlank(hospitalDto.getLevel())){
                result.setSuccess(false).setError("定点医院等级不能为空");
                return null;
            }
            query.setHospitalCodeEq(hospitalDto.getCode());
            if(CollectionUtils.isEmpty(spsFixedpointhospitalService.findAll(query))){
                result.setSuccess(false).setError("定点医院["+hospitalDto.getName()+"]不存在");
                return null;
            }
            if(hospitalDto.getLevel().indexOf("社区") > -1){
                sqyy++;
            }else {
                ddyy++;
            }
        }
        if(ddyy > 4){
            result.setSuccess(false).setError("定点医院不能超过4家");
            return null;
        }
        if(ddyy < 2){
            result.setSuccess(false).setError("定点医院不能少于2家");
            return null;
        }
        if(sqyy > 1){
            result.setSuccess(false).setError("社区医院不能超过1家");
            return null;
        }
        return JSON.toJSONString(hospitalList);
    }
}
