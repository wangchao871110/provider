package com.xfs.employeeside.service.impl;
/**
 * @author hongjie
 * @date 2017/3/17.9:46
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.aop.task.cs.CorpTaskAspectService;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.model.BsArea;
import com.xfs.business.dao.SpsFixedpointhospitalDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.util.MobileValidator;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmCorpService;
import com.xfs.employeeside.dto.SpsStateDto;
import com.xfs.employeeside.service.CmEmployeeService;
import com.xfs.employeeside.service.InsuredService;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.model.SpsScheme;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 变更服务
 *
 * @author
 * @create 2017-03-17 9:46
 **/

@Service
public class InsuredServiceImpl implements InsuredService {

    @Autowired
    private SpsTaskService spsTaskService;

    @Autowired
    private CmEmployeeService employeeService;

    @Autowired
    private BsAreaDao bsAreaDao;

    @Autowired
    private SpsSchemeDao spsSchemeDao;
    @Autowired
    private SpsFixedpointhospitalDao spsFixedpointhospitalDao;

    @Autowired
    private CmCorpService cmCorpService;


    @Autowired
    private CorpTaskAspectService corpTaskAspectService;

    @Override
    public Result saveHosptial(ContextInfo cti, Integer empId, List<String> hosptialCodes) {
        Result result = Result.createResult().setSuccess(true);
        // 根据 code  获取医院信息
        List<SpsFixedpointhospital> spsFixedpointhospitallist = spsFixedpointhospitalDao.getFixHosptialByHospitalCodes(hosptialCodes);
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(empId);
        cmEmployee = employeeService.findByPK(cmEmployee);
        if (cmEmployee == null) {
            result.setError("员工不存在!");
            return result;
        }

//        if (!isCanDo(result, empId, BsType.MODIFY_HOSPITAL.code()).isSuccess()) {
//            return result;
//        }

        // 判断 医院 选择是否合法
        JSONObject object = new JSONObject();
        object.put("identity_code", cmEmployee.getIdentityCode());
        object.put("identity_type", cmEmployee.getIdentityType());
        object.put("mobile", cmEmployee.getMobile());
        object.put("name", cmEmployee.getName());
        List<JSONObject> hospital = new ArrayList<>();
        for (SpsFixedpointhospital spsFixedpointhospital : spsFixedpointhospitallist) {
            JSONObject hosp = new JSONObject();
            hosp.put("name", spsFixedpointhospital.getFullName());
            hosp.put("code", spsFixedpointhospital.getHospitalCode());
            hosp.put("level", spsFixedpointhospital.getHospitalLevel());
            hosp.put("region", spsFixedpointhospital.getRegion());
            hosp.put("address", spsFixedpointhospital.getHospitalAddress());
            hospital.add(hosp);
        }
        object.put("hospital", hospital);

        SpsTask spsTask = new SpsTask();
        spsTask.setEmpId(empId);
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setSuccess(false).setError("方案不存在，请检查!");
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(cmEmployee.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setSuccess(false).setError("公司不存在，请查看!");
            return result;
        }
        spsTask.setAreaId(spsScheme.getAreaId());
        spsTask.setCpId(cmEmployee.getCpId());
        spsTask.setName(cmEmployee.getName());
        spsTask.setSpId(spsScheme.getSpId());
        spsTask.setSchemeId(spsScheme.getSchemeId());
        spsTask.setBstypeId(BsType.MODIFY_HOSPITAL.code());
        spsTask.setType(TaskStateFiled.TODO_DSH_APPLICATION.getTaskType());//待处理
        spsTask.setCreateDt(new Date());
        spsTask.setMobile(cmEmployee.getMobile());
        spsTask.setIdentityCode(cmEmployee.getIdentityCode());
        spsTask.setDr(0);
        spsTask.setJson(JSON.toJSONString(object));
        List<SpsStateDto> spsStateDtoList = spsTaskService.getAllStateBy(spsTask.getBstypeId(), spsTask.getAreaId(), "SERVICE");
        if (CollectionUtils.isEmpty(spsStateDtoList)) {
            result.setError("该业务类型的状态没有配置，请配置后在进行操作！");
            return result;
        }
        if (spsStateDtoList.size() > 1) {
            spsTask.setStateFiledId(Integer.valueOf(spsStateDtoList.get(spsStateDtoList.size()-3).getSysDicitemId()));
            spsTask.setStateFiledName(spsStateDtoList.get(spsStateDtoList.size()-3).getName());
        } else {
			spsTask.setStateFiledId(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId());
			spsTask.setStateFiledName(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledName());
        }

        corpTaskAspectService.saveTask(cti, spsTask, null, result);

        return result;
    }

    @Override
    public Result changeMobile(ContextInfo cti, Integer empId, String mobile) {
        Result result = Result.createResult().setSuccess(true);
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(empId);
        cmEmployee = employeeService.findByPK(cmEmployee);
        if (cmEmployee == null) {
            result.setError("员工账号不存在！");
        }

        if (!MobileValidator.isMobileNO(mobile)) {
            result.setError("电话格式不正确！");
        }
        SpsTask spsTask = new SpsTask();
        spsTask.setEmpId(empId);

//        if (!isCanDo(result, empId, BsType.MODIFY_PHONE.code()).isSuccess()) {
//            return result;
//        }


        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setError("方案不存在，请检查!");
            return result;
        }

        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(cmEmployee.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setSuccess(false).setError("公司不存在，请查看!");
            return result;
        }
        spsTask.setAreaId(spsScheme.getAreaId());
        spsTask.setCpId(cmEmployee.getCpId());
        spsTask.setName(cmEmployee.getName());
        spsTask.setSpId(spsScheme.getSpId());
        spsTask.setBstypeId(BsType.MODIFY_PHONE.code());
        spsTask.setType(TaskStateFiled.TODO_DSH_APPLICATION.getTaskType());//待处理
        spsTask.setCreateDt(new Date());
        spsTask.setSchemeId(spsScheme.getSchemeId());
        spsTask.setMobile(cmEmployee.getMobile());
        spsTask.setIdentityCode(cmEmployee.getIdentityCode());
        List<SpsStateDto> spsStateDtoList = spsTaskService.getAllStateBy(spsTask.getBstypeId(), spsTask.getAreaId(), "SERVICE");
        if (CollectionUtils.isEmpty(spsStateDtoList)) {
            result.setSuccess(false);
            result.setError("该业务类型的状态没有配置，请配置后在进行操作！");
            return result;
        }
        if (spsStateDtoList.size() > 1) {
        	spsTask.setStateFiledId(Integer.valueOf(spsStateDtoList.get(spsStateDtoList.size()-3).getSysDicitemId()));
            spsTask.setStateFiledName(spsStateDtoList.get(spsStateDtoList.size()-3).getName());
        } else {
            spsTask.setStateFiledId(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId());
			spsTask.setStateFiledName(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledName());
        }
        spsTask.setDr(0);
        JSONObject object = new JSONObject();
        object.put("identity_code", cmEmployee.getIdentityCode());
        object.put("mobile", mobile);
        object.put("name", cmEmployee.getName());
        object.put("identity_type", cmEmployee.getIdentityType());
        spsTask.setJson(JSON.toJSONString(object));

        corpTaskAspectService.saveTask(cti, spsTask, null, result);

        return result;
    }


    private Result isCanDo(Result result, Integer empId, Integer bstypeId) {
        SpsTask param = new SpsTask();
        param.setEmpId(empId);
        param.setBstypeId(bstypeId);
        param.setType("TODO");
        param.setCreateDt(new Date());
        SpsTask temp = spsTaskService.getSpsTaskByCondition(param);
        if (null != temp) {
            result.setError("此项业务一个月只能办理一次!");
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    @Override
    public Result getHospitalByAreaId(Integer areaCode) {
        Result result = Result.createResult().setSuccess(true);
        List<SpsFixedpointhospital> spsFixedpointhospitals = spsFixedpointhospitalDao.getFixHosptialByRegionId(areaCode);
        result.setDataValue("spsFixedPointHospital", spsFixedpointhospitals);
        return result;
    }

    @Override
    public Result getRegion(Integer empId) {
        Result result = Result.createResult().setSuccess(true);
        Integer tempAreaId = employeeService.getEmpAreaIdByEmpId(empId);
        if (tempAreaId == null) {
            result.setError("员工方案不存在!");
            result.setSuccess(false);
            return result;
        }
        List<BsArea> bsAreaList = bsAreaDao.getAreaListByCityId(tempAreaId);
        result.setDataValue("bsArea", bsAreaList);

        return result;
    }

    @Override
    public Result getRegionByAreaId(Integer areaId) {
        Result result = Result.createResult().setSuccess(true);
        List<BsArea> bsAreaList = bsAreaDao.getAreaListByCityId(areaId);
        result.setDataValue("bsArea", bsAreaList);
        return result;
    }
}
