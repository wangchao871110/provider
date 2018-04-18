package com.xfs.employeeside.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.model.SysDictitem;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.SpsTask;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.business.service.SpsTaskHistoryService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.annotation.LoginType;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.SessionUtil;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.employeeside.dao.CmEmployeeDao;
import com.xfs.employeeside.model.CmEmployeeAttach;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.service.CmEmployeeAttachService;
import com.xfs.employeeside.service.CmEmployeeAuditService;
import com.xfs.employeeside.service.CmEmployeeService;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CmEmployeeServiceImpl 服务层接口实现类
 *
 * @date:2017/03/13 14:37:24
 */
@Service
public class CmEmployeeServiceImpl implements CmEmployeeService {

    //@Value("${empId}")
    private Integer demoEmpId;
    //@Value("${cpId}")
    private Integer demoCpId;


    @Autowired
    private CmEmployeeDao cmEmployeeDao;

    @Autowired
    private CmCorpDao cmCorpDao;

    @Autowired
    private CmEmployeeAttachService cmEmployeeAttachService;

    @Autowired
    private CmEmployeeAuditService cmEmployeeAuditService;


    @Autowired
    private SysDictitemDAO sysDictitemDAO;


    @Autowired
    private BsAreaDao bsAreaDao;


    @Autowired
    SpsSchemeDao spsSchemeDao;


    @Autowired
    CmEmployeeConfigService cmEmployeeConfigService;


    @Autowired
    private SpsSchemeService spsSchemeService;

    @Override
    public Result login(Integer cpId, String headimgurl, String name, String lastFourOfIdCard, String openId) {
        return null;
//        Result result = Result.createResult().setSuccess(true);
//        // 先根据openId 查， 员工表， 和待 审核表
//        CmEmployee tempCmemployee = cmEmployeeDao.findByOpenId(openId);
//
//        if (tempCmemployee == null) {
//            // 根据身份证号 查
//            // 使用 name 和 lastFourOfIdCard 进行查询
//            if (cpId != null) {
//                if (lastFourOfIdCard != null && name != null) {
//                    CmEmployee cmEmployee = getEmployeeByParam(cpId, name, lastFourOfIdCard);
//                    if (cmEmployee == null) {
//                        CmEmployeeAudit cmEmployeeAudit = cmEmployeeAuditService.getCmEmployeeAuditByOpenId(openId);
//                        if (cmEmployeeAudit == null) {
//                            // 插入cpID  和idcard 和idtype
//                            setRedisKey(cpId, openId);
//                            result.setDataValue("isExist", 2);
//                            return result;
//                        } else {
//                            if (cmEmployeeAudit.getState() == 4) {
//                                result.setDataValue("isExist", 2);
//                                setRedisKey(cpId, openId);
//                                return result;
//                            }
//                            if (cmEmployeeAudit.getState() == 1 || cmEmployeeAudit.getState() == 2) {
//                                setRedisKey(cpId, openId);
//                                result.setDataValue("isExist", 1);
//                                return result;
//                            }
//                        }
//                    } else {
//                        setRedisKey(cpId, openId);
//                        // 更新openId
//                        updateEmployeeOpenId(cmEmployee, openId);
//                        result.setDataValue("isExist", 0);
//                        getResult(result, cmEmployee);
//                        return result;
//                    }
//                    return result;
//                } else {
//                    setRedisKey(cpId, openId);
//                    result.setDataValue("isExist", 2);
//                    return result;
//                }
//
//            } else {
//                CmEmployeeAudit cmEmployeeAudit = cmEmployeeAuditService.getCmEmployeeAuditByOpenId(openId);
//                if (cmEmployeeAudit == null) {
//                    if (cpId == null) {
//                        // 更新openId
//                        CmEmployee temp = new CmEmployee();
//                        temp.setEmpId(demoEmpId);
//                        temp.setCpId(demoCpId);
//                        //   updateEmployeeOpenId(temp, openId);
//                        result.setDataValue("isExist", 0);
//                        getResult(result, temp);
//                        result.setDataValue("isDemo", 1);
//                        setRedisKey(demoCpId, openId);
//                        return result;
//                    }
//                    setRedisKey(cpId, openId);
//                    // 插入cpID  和idcard 和idtype
//                    result.setDataValue("isExist", 2);
//                    return result;
//                } else {
//                    if (cmEmployeeAudit.getState() == 4) {
//                        setRedisKey(cpId, openId);
//                        result.setDataValue("isExist", 2);
//                        return result;
//                    }
//                    if (cmEmployeeAudit.getState() == 1 || cmEmployeeAudit.getState() == 2) {
//                        setRedisKey(cpId, openId);
//                        result.setDataValue("isExist", 1);
//                        return result;
//                    }
//                }
//            }
//            return result;
//        } else {
//            if (tempCmemployee.getCpId().equals(cpId)) {
//                result.setDataValue("isExist", 0);
//                // 获取公司信息
//                getResult(result, tempCmemployee);
//                setRedisKey(cpId, openId);
//                return result;
//            } else {
//                result.setDataValue("isExist", 2);
//
//                // 获取公司信息
//                getResult(result, tempCmemployee);
//                setRedisKey(cpId, openId);
//                return result;
//            }
//        }
    }

    @Override
    public Result loginByOpenId(HttpServletRequest request, HttpServletResponse response, String openId, Integer cpId) {
        Result result = Result.createResult().setSuccess(true);
        result.setDataValue("canUseEmpSide", 0);
        // 先根据openId 查， 员工表， 和待 审核表
        CmEmployee tempCmemployee = null;
        if(StringUtils.isNotBlank(openId)) {
            tempCmemployee = cmEmployeeDao.findByOpenId(openId);
        }
        if (tempCmemployee == null) {

            CmEmployeeAudit cmEmployeeAudit = null;
            if (StringUtils.isNotBlank(openId)) {
                cmEmployeeAudit = cmEmployeeAuditService.getCmEmployeeAuditByOpenId(openId);
            }
            if (cmEmployeeAudit == null) {
                if (cpId == null) {
                    // 更新openId
                    CmEmployee temp = new CmEmployee();
                    temp.setEmpId(demoEmpId);
                    temp.setCpId(demoCpId);
                    result.setDataValue("isExist", 0);
                    getResult(result, temp);
                    result.setDataValue("isDemo", 1);
                    setRedisKey(request,demoCpId, openId);
                    SessionUtil.setObjectToSession(request.getSession(), "cmEmp", temp);

                    ContextInfo info = new ContextInfo();
                    info.setUserId(temp.getEmpId());
                    info.setOrgId(temp.getCpId());
                    info.setUserName(temp.getName());
                    SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);
                    return result;
                }
                // 插入cpID  和idcard 和idtype
                setRedisKey(request,cpId, openId);
                ContextInfo info = new ContextInfo();
                info.setOrgId(cpId);
                SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

                result.setDataValue("isExist", 2);
                return result;
            } else {
                // 改员工存在
                if (cpId != null && !cmEmployeeAudit.getCpId().toString().equals(cpId.toString())) {
                    result.setDataValue("isExist", 2);
                    // 该员工已经不属于 该公司后， 清除 openId
                    updateEmployeeAuditOpenId(cmEmployeeAudit, "");
                    setRedisKey(request,cpId, openId);
                }

                if (cmEmployeeAudit.getState() == 1 || cmEmployeeAudit.getState() == 2) {
                    setRedisKey(request,cpId, openId);
                    SessionUtil.setObjectToSession(request.getSession(), "cmEmp", cmEmployeeAudit);
                    result.setDataValue("isExist", 1);
                } else {
                    result.setDataValue("isExist", 2);
                    setRedisKey(request,cpId, openId);
                }

                ContextInfo info = new ContextInfo();
                info.setUserId(cmEmployeeAudit.getEmpId());
                info.setOrgId(cmEmployeeAudit.getCpId());
                info.setUserName(cmEmployeeAudit.getName());
                SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

                return result;
            }
        } else {
            if (cpId != null && !tempCmemployee.getCpId().equals(cpId)) {
                result.setDataValue("isExist", 2);
                // 该员工已经不属于 该公司后， 清除 openId
                updateEmployeeOpenId(tempCmemployee, "");
                setRedisKey(request,cpId, openId);
            } else {
                // 获取员工 地区
                // 根据 员工id 获取方案信息
                SpsScheme spsScheme = spsSchemeService.findSchemeByEmpId(tempCmemployee.getEmpId());
                Integer areaId = spsScheme.getAreaId();
                result.setDataValue("isExist", 0);
                // 获取公司信息
                getResult(result, tempCmemployee);
                SessionUtil.setObjectToSession(request.getSession(), "cmEmp", tempCmemployee);
                setRedisKey(request,cpId, openId);

            }
            ContextInfo info = new ContextInfo();
            info.setUserId(tempCmemployee.getEmpId());
            info.setOrgId(tempCmemployee.getCpId());
            info.setAvatar(tempCmemployee.getHeadPhoto());
            info.setUserName(tempCmemployee.getName());
            SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

            return result;
        }
    }

    @Override
    public Result loginByYcrToken(HttpServletRequest request, HttpServletResponse response, String ycrToken, Integer cpId) {
        Result result = Result.createResult().setSuccess(true);
        result.setDataValue("canUseEmpSide", 0);
        // 先根据openId 查， 员工表， 和待 审核表
        CmEmployee tempCmemployee = cmEmployeeDao.findByYrcToken(ycrToken);
        if (tempCmemployee == null) {
            // 友人才 用户在本地不存在
            result.setDataValue("isExist", 2);
            result.setDataValue("yrcExit", 1);

            ContextInfo info = new ContextInfo();
            info.setOrgId(cpId);
            SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);
            return result;
        } else {
            // 友人才 用户在本地存在
            result.setDataValue("isExist", 0);
            result.setDataValue("yrcExit", 0);
            // 获取公司信息
            getResult(result, tempCmemployee);
            SessionUtil.setObjectToSession(request.getSession(), "cmEmp", tempCmemployee);


            ContextInfo info = new ContextInfo();
            info.setUserId(tempCmemployee.getEmpId());
            info.setOrgId(tempCmemployee.getCpId());
            info.setAvatar(tempCmemployee.getHeadPhoto());
            info.setUserName(tempCmemployee.getName());
            SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

            setRedisKey(request,cpId, null);
            return result;
        }
    }

    @Autowired
    private SpsTaskService spsTaskService;
    @Autowired
    private SpsTaskHistoryService spsTaskHistoryService;

    @Override
    public Result loginByUserInfo(HttpServletRequest request, HttpServletResponse response, Integer cpId, String name, String lastFourOfIdCard, String openId) {
        Result result = Result.createResult().setSuccess(true);
        result.setDataValue("canUseEmpSide", 0);

        CmEmployee cmEmployee = getEmployeeByParam(cpId, name, lastFourOfIdCard);
        if (cmEmployee == null) {
            CmEmployeeAudit cmEmployeeAudit = getEmployeeAuditByParam(cpId, name, lastFourOfIdCard);
            if (cmEmployeeAudit == null) {

                CmCorp corp = new CmCorp();
                corp.setCpId(cpId);
                corp = cmCorpDao.findByPK(corp);
                if (corp!=null && "THREEPART".equals(corp.getEmployeeSource())){ //第三方来源的企业没有入职功能
                    result.setDataValue("isExist", 3);
                }else{
                    result.setDataValue("isExist", 2);
                }

                setRedisKey(request,cpId, openId);

                ContextInfo info = new ContextInfo();
                info.setOrgId(cpId);
                info.setUserName(name);
                SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);
                return result;
            } else {
                Integer taskId = null;
                // 是否存在处理中的入职任务单
                List<SpsTask> spsTasks = spsTaskService.findByEmpId(cmEmployeeAudit.getEmpId(), BsType.INCREMENT_INSUR_ENTRY.getCode(), "TODO");
                if(spsTasks.size() > 0){
                    taskId = spsTasks.get(0).getTaskId();
                }else{
                    // 获取关闭任务单
                    Map<String, Object> closedMap = findStatus(cmEmployeeAudit.getEmpId(),"CLOSED",0, BsType.INCREMENT_INSUR_ENTRY.getCode());
                    if(null != closedMap){
                        taskId = (Integer) closedMap.get("taskId");
                    }
                }

                result.setDataValue("taskId", taskId);

                ContextInfo info = new ContextInfo();
                info.setUserId(cmEmployeeAudit.getEmpId());
                info.setOrgId(cmEmployeeAudit.getCpId());
                info.setUserName(cmEmployeeAudit.getName());
                SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

                result.setDataValue("isExist", 1);
                setRedisKey(request,cpId, openId);
                return result;
            }
        } else {
            setRedisKey(request,cpId, openId);
            // 更新openId
            updateEmployeeOpenId(cmEmployee, openId);
            result.setDataValue("isExist", 0);
            if (StringUtils.isNotEmpty(cmEmployee.getThreeId())) {
                result.setDataValue("yrcExit", 0);
            }

            ContextInfo info = new ContextInfo();
            info.setUserId(cmEmployee.getEmpId());
            info.setOrgId(cmEmployee.getCpId());
            info.setAvatar(cmEmployee.getHeadPhoto());
            info.setUserName(cmEmployee.getName());
            SessionUtil.setObjectToSession(request.getSession(), LoginType.EMPLOYEE_XFS_SS_LOGIN.getSessinId(), info);

            getResult(result, cmEmployee);
            return result;
        }
    }

    /**
     * 获取人员入职状态
     *  @param empId
     *  @param taskType
     *  @param type 0：未读任务；1：已读任务
     *  @return
     *	@return 			: Map<String,Object>
     *  @createDate  	: 2017年11月10日 下午3:50:18
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年11月10日 下午3:50:18
     *  @updateAuthor  :
     */
    private Map<String, Object> findStatus(Integer empId,String taskType,Integer type,Integer code) {
        Map<String, Object> map = null;
        // 获取任务单
        List<SpsTask> spsTasksCompleted = spsTaskService.findByEmpId(empId, code, taskType);
        if(spsTasksCompleted.size() > 0){
            SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
            spsTaskHistory.setTaskId(spsTasksCompleted.get(0).getTaskId());
            spsTaskHistory.setStateFiledId(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId());
            // 根据成功任务单 获取历史数据
            List<SpsTaskHistory> spsTaskHistorys = spsTaskHistoryService.findAll(spsTaskHistory);
            for(int j=0;j<spsTaskHistorys.size();j++){
                String isRead = "UNREAD";
                if(1 == type){
                    isRead = "READ";
                }
                Integer stateFiledId = TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId();
                if(taskType.equals("CLOSED")){
                    stateFiledId = -1;
                }
                // 判断任务单历史数据
                if(stateFiledId.equals(spsTaskHistorys.get(j).getStateFiledId())
                        && isRead.equals(spsTaskHistorys.get(j).getIsRead())){
                    map = new HashMap<>();
                    map.put("taskId", spsTasksCompleted.get(0).getTaskId());
                    map.put("flag", true);
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 保存一个contextInfo  用做增删改查时候用
     *
     * @param cpId   公司id
     * @param openId 个人 的openId
     */
    private void setRedisKey(HttpServletRequest request, Integer cpId, String openId) {
        ContextInfo info = new ContextInfo();
        info.setOrgId(cpId);
        SessionUtil.setObjectToSession(request.getSession(), "cti", info);
      //  RedisUtil.set(openId, info, 1000 * 60 * 30);
    }

    /**
     * @param cpId
     * @return
     */
    private boolean canUseEmpSide(Integer cpId) {
        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigService.findAllCorpOpenCity(cpId);
        if (CollectionUtils.isEmpty(cmEmployeeConfigs)) {
            return false;
        }
        return true;
    }

    /**
     * @param cpId
     * @return
     */
    private boolean canUseEmpSide(Integer cpId, Integer areaId) {
        CmEmployeeConfig cm = new CmEmployeeConfig();
        cm.setCpId(cpId);
        cm.setAreaId(areaId);
        cm.setDr(0);
        cm.setIsEmp(1);
        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigService.findAll(cm);
        if (CollectionUtils.isEmpty(cmEmployeeConfigs)) {
            return false;
        }
        return true;
    }


    @Override
    public Result logout(Integer empId, String openId) {
        Result result = Result.createResult().setSuccess(true);
        com.xfs.corp.model.CmEmployee cmEmployee = new com.xfs.corp.model.CmEmployee();
        cmEmployee.setEmpId(empId);
        //cmEmployeeDao.update()
        return null;
    }

    /**
     * 根据员工信息， 获取返回结果
     *
     * @param result
     * @param employee
     * @return
     */
    private Result getResult(Result result, CmEmployee employee) {
        // 获取公司信息
        CmCorp tempcmCorp = new CmCorp();
        tempcmCorp.setCpId(employee.getCpId());
        CmCorp corp = cmCorpDao.findByPK(tempcmCorp);
        if (corp == null) {
            // 如果 不存在， 则只返回 提示信息
            result.setDataValue("isExist", 2);
            return result;
        }
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(employee.getEmpId());
        Integer areaId = corp.getAreaId();
        if (spsScheme != null) {
            areaId = spsScheme.getAreaId();
        }


        CmEmployee cmEmployee = cmEmployeeDao.findByPK(employee);
        Map<String, java.io.Serializable> employeeInfo = new HashMap<>();
        if (cmEmployee == null) {
            // 如果 不存在， 则只返回 提示信息
            result.setDataValue("isExist", 2);
            return result;
        }
        employeeInfo.put("areaId", areaId);
        employeeInfo.put("cpId", cmEmployee.getCpId());
        employeeInfo.put("cropName", corp.getCorpName());
        employeeInfo.put("empId", cmEmployee.getEmpId());
        employeeInfo.put("idCardNo", cmEmployee.getIdentityCode());
        employeeInfo.put("name", cmEmployee.getName());
        employeeInfo.put("headPhoto", cmEmployee.getHeadPhoto());
        // 女是 1  男是2
        if (cmEmployee.getIdentityType() != null && cmEmployee.getIdentityCode() != null && cmEmployee.getIdentityType().equals("IDCard")) {
            employeeInfo.put("sex", IdcardValidator.getSex(cmEmployee.getIdentityCode()));
        } else {
            cmEmployee.setSex("3");
        }

        result.setDataValue("employeeInfo", employeeInfo);
        result.setDataValue("isDemo", "2");
        return result;
    }

    /**
     * 更新员工表， openId
     *
     * @param employee
     * @param openId
     * @return
     */
    private boolean updateEmployeeOpenId(CmEmployee employee, String openId) {
        // 更新openId
        employee.setOpenid(openId);
        ContextInfo info = new ContextInfo();
        info.setOrgId(employee.getCpId());
        int res = cmEmployeeDao.update(info, employee);
        if (res == 1) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 更新员工表， openId
     *
     * @param employeeAudit
     * @param openId
     * @return
     */
    private boolean updateEmployeeAuditOpenId(CmEmployeeAudit employeeAudit, String openId) {
        // 更新openId
        employeeAudit.setOpenId(openId);
        ContextInfo info = new ContextInfo();
        info.setOrgId(employeeAudit.getCpId());
        int res = cmEmployeeAuditService.update(info, employeeAudit);
        if (res == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 根据参数查询  员工信息
     *
     * @param cpId
     * @param name
     * @param lastFourOfIdCard
     * @return
     */
    private CmEmployee getEmployeeByParam(Integer cpId, String name, String lastFourOfIdCard) {
        CmEmployee employee = new CmEmployee();
        employee.setCpId(cpId);
        employee.setName(name.trim());
        employee.setIdentityCode(lastFourOfIdCard);
        employee.setIdentityType("IDCard");
        CmEmployee cmEmployee = cmEmployeeDao.findByFree(employee);


        return cmEmployee;
    }


    /**
     * 根据参数查询  员工信息
     *
     * @param cpId
     * @param name
     * @param lastFourOfIdCard
     * @return
     */
    private CmEmployeeAudit getEmployeeAuditByParam(Integer cpId, String name, String lastFourOfIdCard) {
        CmEmployeeAudit employee = new CmEmployeeAudit();
        employee.setCpId(cpId);
        employee.setName(name.trim());
        employee.setIdentityCode(lastFourOfIdCard);
        employee.setIdentityType("IDCard");
        CmEmployeeAudit cmEmployee = cmEmployeeAuditService.getCmEmployeeAuditByLastFour(employee);

        return cmEmployee;
    }


    @Override
    public int countFindAll() {
        return cmEmployeeDao.countFindAll();
    }

    @Override
    public List<CmEmployee> freeFind(CmEmployee obj) {
        return cmEmployeeDao.freeFind(obj);
    }

    @Override
    public int countFreeFind(CmEmployee obj) {
        return cmEmployeeDao.countFreeFind(obj);
    }

    @Override
    public CmEmployee findByPK(CmEmployee obj) {
        return cmEmployeeDao.findByPK(obj);
    }

    @Override
    public int insert(ContextInfo cti, CmEmployee obj) {
        return cmEmployeeDao.insert(cti, obj);
    }

    @Override
    public int update(ContextInfo cti, CmEmployee obj) {
        return cmEmployeeDao.update(cti, obj);
    }

    @Override
    public int remove(ContextInfo cti, CmEmployee obj) {
        return cmEmployeeDao.remove(cti, obj);
    }

    @Override
    public Integer getEmpAreaIdByEmpId(Integer empId) {
        return cmEmployeeDao.getEmpAreaIdByEmpId(empId);
    }

    @Override
    public Result getCmEmployeeAndAttach(Integer empId) {
        Result result = Result.createResult().setSuccess(true);
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(empId);
        cmEmployee = findByPK(cmEmployee);
        if (cmEmployee == null) {
            result.setSuccess(false);
            result.setError("员工不存在");
            return result;
        }

        if (cmEmployee.getMobile() == null) {
            cmEmployee.setMobile("");
        }
        if (cmEmployee.getIdentityCode() == null) {
            cmEmployee.setIdentityCode("");
        }
        if (cmEmployee.getIdentityType() == null) {
            cmEmployee.setIdentityType("");
        }

        CmEmployeeAttach cmEmployeeAttach = new CmEmployeeAttach();
        cmEmployeeAttach.setEmpId(empId);
        List<CmEmployeeAttach> cmEmployeeAttachList = Lists.newArrayList();
        cmEmployeeAttachList = cmEmployeeAttachService.freeFind(cmEmployeeAttach);
        cmEmployee.setAttachs(cmEmployeeAttachList);
        if (cmEmployee.getIdentityType().equals("IDCard") && IdcardValidator.isValidatedAllIdcard(cmEmployee.getIdentityCode())) {
            cmEmployee.setBirthday(getBirthday(cmEmployee.getIdentityCode()));
            cmEmployee.setSex(IdcardValidator.getSex(cmEmployee.getIdentityCode()) + "");
        } else {
            cmEmployee.setBirthday("");
            cmEmployee.setSex("3");
        }
        // 查询 所有的证件类型
        SysDictitem sysDictitem = new SysDictitem();
        sysDictitem.setDictionary(89);
        List<SysDictitem> sysDictitemList = sysDictitemDAO.findSysDicitem(sysDictitem);

        if (CollectionUtils.isNotEmpty(sysDictitemList)) {
            for (SysDictitem sysDictitem1 : sysDictitemList) {
                if (cmEmployee.getIdentityType().equals(sysDictitem1.getCode())) {
                    cmEmployee.setIdentityType(sysDictitem1.getName());
                    break;
                }
            }
        }
        result.setDataValue("cmEmployee", cmEmployee);
        return result;
    }

    private String getBirthday(String idcard) {
        // 获取出生年月日
        String birthday = idcard.substring(6, 14);
        String res = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6, 8);
        return res;

    }

}
