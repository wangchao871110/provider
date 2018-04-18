package com.xfs.aop.task.cs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dao.BsSysStateReportDao;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysMessage;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.base.service.SysMessageService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.ApplyOnline;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBsareatypeProcess;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeProcessService;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.BdBstypeService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.Config;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.MobileValidator;
import com.xfs.common.util.TimeUtils;
import com.xfs.common.util.UUIDUtil;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysUserRoleService;
import com.xfs.user.service.SysUserService;
import com.xfs.wx.message.dto.NewText;
import com.xfs.wx.message.dto.NewTextMessage;
import com.xfs.wx.message.dto.WxMessage;

/**
 * 基础类，目前 添加 校验功能
 * konglc
 */
public abstract class BaseTaskDataParse implements TaskDataParseInterface,IStaticVarConstant {

    private static Logger log = Logger.getLogger(BaseTaskDataParse.class);
    @Autowired
    TaskAspect taskAspect;
    @Autowired
    BdBusinessfieldService bdBusinessfieldService;
    @Autowired
    SpsTaskService spsTaskService;
    @Autowired
    SysUploadfileService sysUploadfileService;
    @Autowired
    SpsSchemeService spsSchemeService;
    @Autowired
    CmEmployeeService cmEmployeeService;
    @Autowired
    BsAreaService bsAreaService;
    @Autowired
    BdBsareatypeService bdBsareatypeService;
    @Autowired
    private SysDictitemService sysDictitemService;
    @Autowired
    private BdBsareatypeProcessService bdBsareatypeProcessService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BdBstypeService bdBstypeService;
    @Autowired
    private SysMessageService sysMessageService;
    @Autowired
    private BsSysStateReportDao bsSysStateReportDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 注册事件
     */
    @PostConstruct
    public void registEvent() {
        taskAspect.addEventObject(this);
    }

    /**
     * 基类-校验数据有效性
     * @param info
     * @param spsTask
     * @param businessParame
     * @param employee
     * @param result
     * @return
     */
    @Override
    public boolean checkDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee employee, Result result){
        SpsScheme scheme = new SpsScheme();
        scheme.setSchemeId(spsTask.getSchemeId());
        scheme = spsSchemeService.findByPK(scheme);
        if (scheme == null) {
            result.setSuccess(false).setError("未找到方案");
            return false;
        }
        employee.setScheme(scheme);

        boolean isok = true;
        Map<String, BusinessField> businessFieldMap = bdBusinessfieldService.queryBusinessFields(scheme.getAreaId(), spsTask.getBstypeId());
        TypeReference<Map<String, String>> ref = new TypeReference<Map<String, String>>() {
        };
        Map<String, String> curMap = JSON.parseObject(spsTask.getJson(), ref);
        //补全字段默认值
        compleFieldDef(curMap, businessFieldMap);

        /**
         * 如果为回调 不校验参数合法性
         */
        if(!spsTask.isCallBack()){
            if (spsTask.getEmpId() == null) {
                result.setSuccess(false).setError("未找到员工");
                return false;
            }
            if (employee == null) {
                result.setSuccess(false).setError("未找到员工");
                return false;
            }
            if (spsTask.getBstypeId() == null) {
                result.setSuccess(false).setError("未找到业务类型");
                return false;
            }

            if (spsTask.getSchemeId() == null) {
                result.setSuccess(false).setError("未找到方案");
                return false;
            }

            if (StringUtils.isBlank(spsTask.getJson())) {
                result.setSuccess(false).setError("业务参数为空");
                return false;
            }

            StringBuffer err = new StringBuffer();
            //固定json字段
            if (StringUtils.isBlank(curMap.get("identity_type"))) {
                curMap.put("identity_type", employee.getIdentityType());
            }
            if (StringUtils.isBlank(curMap.get("identity_code"))) {
                curMap.put("identity_code", employee.getIdentityCode());
            }
            if (StringUtils.isBlank(curMap.get("name"))) {
                curMap.put("name", employee.getName());
            }
            if (StringUtils.isBlank(curMap.get("mobile"))) {
                curMap.put("mobile", employee.getMobile());
            }
            //补全身份证类型name 帅哥用
            SysDictitem queryDict = new SysDictitem();
            queryDict.setDictionary(89);
            List<SysDictitem> dictitems = sysDictitemService.findAll(queryDict);
            if (CollectionUtils.isNotEmpty(dictitems)) {
                for (SysDictitem dictitem : dictitems) {
                    if (dictitem.getCode().equals(employee.getIdentityType())) {
                        curMap.put("identity_type_name", dictitem.getName());
                        break;
                    }
                }
            }
            Map<String, String> json = new HashMap<>();
            if (null != businessFieldMap && !businessFieldMap.isEmpty()) {
                for (Map.Entry<String, BusinessField> entry : businessFieldMap.entrySet()) {
                    String fieldCode = entry.getKey();
                    BusinessField businessField = entry.getValue();
                    String value = curMap.get(fieldCode) == null ? null : curMap.get(fieldCode).trim();
                    //去空格
                    curMap.put(fieldCode, value);
                    if (StringUtils.isBlank(businessField.getType())) {
                        continue;
                    }
                    if ("HIDDEN".equals(businessField.getType())) {
                        continue;
                    }
                    if ("Y".equals(businessField.getRequired()) || StringUtils.isNotBlank(value)) {
                        if ("Y".equals(businessField.getRequired()) && StringUtils.isBlank(value)) {
                            err.append(businessField.getName()).append("为空！");
                            isok = false;
                            break;
                        }
                        if ("DATETIME".equals(businessField.getType())) {
                            if (spsTask.getBstypeId() != 29 && spsTask.getBstypeId() != 30) {
                                try {
                                    Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(value);
                                } catch (ParseException e) {
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】格式错误！");
                                    break;
                                }
                            }

                        } else if ("DATE".equals(businessField.getType())) {
                            if (spsTask.getBstypeId() != 29 && spsTask.getBstypeId() != 30) {
                                //校验 字典信息
                                try {
                                    Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(value);
                                } catch (ParseException e) {
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】格式错误！");
                                    break;
                                }
                            }


                        } else if ("PULL".equals(businessField.getType())) {
                            //校验 字典信息
                            if (null != businessField.getOptions() && !businessField.getOptions().isEmpty()) {
                                boolean rightValue = false;
                                String codeName = "";
                                for (SysDictitem sysDictitem : businessField.getOptions()) {
                                    if (sysDictitem.getCode().equals(value)) {
                                        rightValue = true;
                                        codeName = sysDictitem.getName();
                                    }
                                }
                                if (!rightValue) {
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】内容有误！");
                                    break;
                                } else {
                                    curMap.put(businessField.getCode() + "_name", codeName);
                                }
                            }
                        } else if ("FILE".equals(businessField.getType())) {
                            if("photo".equals(fieldCode)){
                                if(StringUtils.isBlank(value)){
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】不存在！");
                                    break;
                                }
                            }else{
                                SysUploadfile file = new SysUploadfile();
                                try {
                                    file.setId(Integer.parseInt(value));
                                } catch (NumberFormatException e) {
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】不存在！");
                                    break;
                                }
                                if (sysUploadfileService.findByPK(file) == null) {
                                    isok = false;
                                    err.append("【" + businessField.getName() + "】不存在！");
                                    break;
                                }
                            }
                        }
                    }
                    json.put(fieldCode, value);
                }
            }

            //校验 手机号
            if (StringUtils.isNotBlank(curMap.get("mobile"))) {
                if (!MobileValidator.isMobileNO(curMap.get("mobile"))) {
                    err.append("手机号格式错误！");
                    isok = false;
                }
            }

            //校验身份证
            if (StringUtils.isBlank(curMap.get("identity_type"))) {//证件类型 为空时 则 认为是身份证
                if (StringUtils.isNotBlank(curMap.get("identity_code"))) {
                    if (!IdcardValidator.isValidatedAllIdcard(curMap.get("identity_code"))) {
                        err.append("证件号码格式错误！");
                        isok = false;
                    }
                }
            } else {//证件类型为 身份证时候 itemcode 配置为IDCard
                if ("IDCard".equals(curMap.get("identity_type"))) {
                    if (StringUtils.isNotBlank(curMap.get("identity_code"))) {
                        if (!IdcardValidator.isValidatedAllIdcard(curMap.get("identity_code"))) {
                            err.append("证件号码格式错误！");
                            isok = false;
                        }
                    }
                }
            }

            if (!isok) {
                result.setSuccess(isok).setError(err.toString());
                return isok;
            }


            String beginDate = null;
            if (curMap.containsKey("beginDate")) {
                beginDate = curMap.get("beginDate");
                if (StringUtils.isBlank(beginDate)) {
                    isok = false;
                    err.append("【生效时间】不能为空！");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    Date inputDate = null;
                    try {
                        inputDate = sdf.parse(beginDate);
                    } catch (ParseException e) {
                        isok = false;
                        err.append("【生效时间】格式不正确！");
                    }
                    if (inputDate != null) {
                        Calendar calendar = Calendar.getInstance();
                        Calendar inputCal = Calendar.getInstance();
                        inputCal.setTime(inputDate);

                        if (spsTask.getBstypeId() != 29 && spsTask.getBstypeId() != 30) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                            SpsScheme spsScheme = new SpsScheme();
                            spsScheme.setSchemeId(spsTask.getSchemeId());
                            spsScheme = spsSchemeService.findByPK(spsScheme);

                            BsArea bsArea = new BsArea();
                            bsArea.setAreaId(spsScheme.getAreaId());
                            bsArea = bsAreaService.findbyPK(bsArea);


                            String beginDateStr = "";


                            BdBsareatype bdBsareatype = new BdBsareatype();
                            bdBsareatype.setAreaId(bsArea.getBelongCity());
                            bdBsareatype.setBstypeId(spsTask.getBstypeId());
                            List<BdBsareatype> list = bdBsareatypeService.findAll(bdBsareatype);
                            if (list.size() > 0) {
                                bdBsareatype = list.get(0);
                            }
                            if (spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3 || spsTask.getBstypeId() == 10) {
                                Calendar currCalendar = Calendar.getInstance();

                                if ("CURMONTH".equals(bdBsareatype.getEffect())) {
                                    if (StringUtils.isNotBlank(spsTask.getBeginDate())) {
                                        spsTask.setExecuteDate(spsTask.getBeginDate());
                                    }

                                } else if ("NEXTMONTH".equals(bdBsareatype.getEffect())) {
                                    Date date = null;
                                    try {
                                        date = format.parse(beginDate);
                                        currCalendar.setTime(date);
                                    } catch (ParseException e) {
                                    }
                                    currCalendar.setTime(date);
                                    currCalendar.add(Calendar.MONTH, -1);
                                    beginDateStr = format.format(currCalendar.getTime());
                                    if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                                        spsTask.setExecuteDate(beginDateStr);
                                    }

                                }

                            } else if (spsTask.getBstypeId() == 4 || spsTask.getBstypeId() == 11) {

                                Calendar currCalendar = Calendar.getInstance();
                                if ("CURMONTH".equals(bdBsareatype.getEffect())) {
                                    try {
                                        Date date = format.parse(beginDate);
                                        currCalendar.setTime(date);
                                        currCalendar.add(Calendar.MONTH, 1);

                                    } catch (ParseException e) {
                                    }


                                } else if ("NEXTMONTH".equals(bdBsareatype.getEffect())) {
                                    Date date = null;
                                    try {
                                        date = format.parse(beginDate);
                                        currCalendar.setTime(date);
                                    } catch (ParseException e) {
                                    }
                                }

                                beginDateStr = format.format(currCalendar.getTime());
                                if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                                    spsTask.setExecuteDate(beginDateStr);
                                }
                            } else {

                            }
                        } else {
                            //补缴单算时间
                            beginDate = sdf.format(calendar.getTime());
                            //2016-12-10 修改 暂用当前时间作为补缴执行时间
                            spsTask.setExecuteDate(TimeUtils.convertDateToString(new Date(), "yyyy-MM"));
                            curMap.put("beginDate", beginDate);
                        }

                    }
                }
            }
            if (isok) {
                spsTask.setBeginDate(beginDate);
                if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                    spsTask.setExecuteDate(beginDate);
                }
                spsTask.setAreaId(scheme.getAreaId());
                spsTask.setSpId(scheme.getSpId());
                spsTask.setSpId(scheme.getSpId());
                spsTask.setDr(0);
                spsTask.setIdentityCode(employee.getIdentityCode());
                spsTask.setMobile(employee.getMobile());
                spsTask.setJson(JSON.toJSONString(curMap));
                spsTask.setName(employee.getName());
                spsTask.setEmpId(employee.getEmpId());
                isok = busDataValidity(info,spsTask,businessParame,businessFieldMap,employee,result);
            } else {
                result.setSuccess(isok).setError(err.toString());
            }
        }else {
            if(!spsTask.isCallBack()){
                isok = busDataValidity(info,spsTask,businessParame,businessFieldMap,employee,result);
            }
        }
        return isok;
    }

    /**
     * 提供业务自定义数据校验
     * @param info
     * @param spsTask
     * @param businessParame
     * @param businessFieldMap
     * @param cmEmployee
     * @param result
     * @return
     */
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee cmEmployee, Result result){
        //校验任务单重复
        if(null == spsTask.getTaskId()){
            List<SpsTask> tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{spsTask.getBstypeId()},null,new String[]{"TODO","SUBMIT","ERROR"});
            if(CollectionUtils.isNotEmpty(tasks) && spsTask.getType().equals(tasks.get(0).getType())){
                spsTask.setTaskId(tasks.get(0).getTaskId());//存在任务返回ID直接进行网申
                result.setSuccess(false).setError("当前员工存在未处理完成任务单");
                return false;
            }
        }
        return true;
    }


    /**
     * 保存任务单之前调用--处理业务
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     * @return
     */
    @Override
    public boolean beforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result) {
        /**
         * 大状态无变更
         */
        if(null != spsTask.getTaskId()){
            SpsTask currTask = spsTaskService.findByPK(spsTask);
            if(currTask.getType().equals(spsTask.getType()))
                return true;
        }
        boolean isSuccess = busBeforeSaveTask(info, spsTask,businessParame, cmEmployee, result);
        if(isSuccess){
            /**
             * 判断当前任务单类型
             */
            if(cmEmployee.getScheme().getSpId() > 0) {
                spsTask.setCompanyType(CMCORPTYPE_SERVICE);
                spsTask.setStateFiledId(TaskStateFiled.SUBMIT_WAITING_APPLICATION.getStateFiledId());
                spsTask.setStateFiledName(TaskStateFiled.SUBMIT_WAITING_APPLICATION.getStateFiledName());
                spsTask.setType(TaskStateFiled.SUBMIT_WAITING_APPLICATION.getTaskType());
            }else {
                spsTask.setCompanyType(CMCORPTYPE_SELFSERVICE);
            }
            if("SUBMIT".equals(spsTask.getType())){
                dealSubmitTask(info,spsTask,businessParame,cmEmployee,(Result)result);
            }else if("TODO".equals(spsTask.getType())){
                dealToDoTask(info,spsTask,businessParame,cmEmployee,(Result)result);
            }else if("COMPLETED".equals(spsTask.getType())){
                dealCompletedTask(info,spsTask,businessParame,cmEmployee,(Result)result);
            }else if("CLOSED".equals(spsTask.getType())){
                dealClosedTask(info,spsTask,businessParame,cmEmployee,(Result)result);
            }else if("ERROR".equals(spsTask.getType())){
                dealErrorTask(info,spsTask,businessParame,cmEmployee,(Result)result);
            }
        }
        return isSuccess;
    }

    /**
     * 抽象方法--保存任务单之前调用业务自主实现
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     * @return
     */
    public boolean busBeforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result){
        return true;
    }

    /**
     * 处理状态为TODO的任务单
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealToDoTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {

    }

    /**
     * 处理状态为COMPLETED任务单
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {

    }

    /**
     * 处理状态为CLOSE的任务单
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealClosedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {

    }

    /**
     * 处理状态为ERROR的任务单
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {

    }

    /**
     * 处理状态为SUBMIT的任务单
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealSubmitTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {

    }

    /**
     * 保存任务单之后做数据处理
     *  @param info
     *  @param spsTask
     *  @param businessParame
     *  @param result
     *  @createDate  	: 2017年4月24日 下午2:19:39
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月24日 下午2:19:39
     *  @updateAuthor  	:
     */
    @Override
    public void afterSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result){
        /**
         * 消息通知
         */
        createMessage(info,spsTask,businessParame,cmEmployee,result);
        /**
         * 调用T
         */
        if(TaskStateFiled.TODO_ONLINE_APPLICATION.getTaskType().equals(spsTask.getType()) && TaskStateFiled.TODO_ONLINE_APPLICATION.getStateFiledId().equals(spsTask.getStateFiledId())){
            sendTaskToT(info,spsTask);
        }
        /**
         * 业务处理
         */
        busAfterSaveTask(info,spsTask,businessParame,cmEmployee,result);

        /**
         * 保存任务单数据到emp上
         */
        if(TaskStateFiled.COMPLETED_APPLICATION.getTaskType().equals(spsTask.getType()) || TaskStateFiled.SUBMIT_WAITING_APPLICATION.getTaskType().equals(spsTask.getType()) ||  2 == spsTask.getBstypeId() ){
            cmEmployee.setJson(spsTask.getJson());
        }
        cmEmployeeService.saveEmpJson(info, cmEmployee);
    }


    /**
     * 抽象方法--保存任务单之后做数据处理自主实现
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     * @return
     */
    public boolean busAfterSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result){
        return true;
    }

    /**
     * 创建消息
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    private void createMessage(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result){
        if(IStaticVarConstant.CMCORPTYPE_SERVICE.equals(spsTask.getCompanyType()))
            return;
        Object ob = RedisUtil.get(AREA_SUPPORT_TYPE+spsTask.getAreaId() + "_" + spsTask.getBstypeId());
        BdBsareatype bdBsareatype = null;
        if(null == ob){
            bdBsareatype = bdBsareatypeService.findBdBstypeByCityAndType(spsTask.getAreaId(), spsTask.getBstypeId());
            if(bdBsareatype==null){
                result.setSuccess(false);
                result.setError("该城市不支持" + BsType.getNameByCode(spsTask.getBstypeId()));
                return;
            }
            RedisUtil.set(AREA_SUPPORT_TYPE+spsTask.getAreaId() + "_" + spsTask.getBstypeId(),bdBsareatype,3600);
        }else {
            bdBsareatype = (BdBsareatype)ob;
        }

        // 当前业务类型配置流程
        List<BdBsareatypeProcess> list = null;
        Object process = RedisUtil.get(AREA_SUPPORT_TYPE_PROCESS+bdBsareatype.getId());
        if(null != process){
            list = (List<BdBsareatypeProcess>)process;
        }else{
            BdBsareatypeProcess bdBsareatypeProcess = new BdBsareatypeProcess();
            bdBsareatypeProcess.setBdBsareatypeId(bdBsareatype.getId());
            list = bdBsareatypeProcessService.findAll(bdBsareatypeProcess);
            RedisUtil.set(AREA_SUPPORT_TYPE_PROCESS+bdBsareatype.getId(),list,3600);
        }
        if(list.size() > 1){
        	// 发送消息条件 1：当前任务单状态是待信息审核 2：当前任务单是修改手机号或者修改医院并且状态是等待申报
            if(spsTask.getStateFiledId().equals(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId())
            		|| (BsType.MODIFY_PHONE.getCode().equals(spsTask.getBstypeId()) 
            				|| BsType.MODIFY_HOSPITAL.getCode().equals(spsTask.getBstypeId())) 
            			&& spsTask.getStateFiledId().equals(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId())
            	){
                SysUser sysUser = new SysUser();
                sysUser.setUserType("CUSTOMER");
                sysUser.setOrgId(info.getOrgId());
                List<SysUser> sysUsers = sysUserService.findAll(sysUser);
                if(sysUsers.size() > 0){
                    BdBstype bdBstype = bdBstypeService.findByPK(bdBsareatype.getBstypeId());
                    if(!bdBstype.getPatrentTypeId().equals(-1)){
                        bdBstype = bdBstypeService.findByPK(bdBstype.getPatrentTypeId());
                    }
                    sysUser = sysUsers.get(0);
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setTodoUserType(sysUser.getUserType());
                    sysMessage.setTodoUser(sysUser.getUserId());
                    sysMessage.setTodoOrg(sysUser.getOrgId());
                    sysMessage.setTitle("来自「"+cmEmployee.getName()+"」的"+bdBstype.getName());
                    sysMessage.setContent(cmEmployee.getName()+"的"+bdBstype.getName());
                    if(null != businessParame && null != businessParame.get("hr_send") && businessParame.get("hr_send").equals(true)){
                    	 sysMessage.setType("HR");
                    }else{
                    	sysMessage.setType("SPSTASK");
                    }
                    sysMessage.setState("TODO");
                    sysMessage.setSendUser(cmEmployee.getEmpId());
                    sysMessage.setSendUserType(info.getUserType());
                    sysMessage.setSendOrg(info.getOrgId());
                    sysMessage.setSendTime(new Date());
                    sysMessage.setDataId(spsTask.getTaskId());
                    sysMessageService.save(info, sysMessage);
                    // 更新弹出消息状态
                    ContextInfo cti = new ContextInfo(sysUser);
                    BsSysStateReport bsSysStateReport = new BsSysStateReport();
                    bsSysStateReport.setCpId(cti.getOrgId());
                    bsSysStateReport.setAreaId(spsTask.getAreaId());
                    bsSysStateReport.setDr(0);
                    bsSysStateReport.setOwnerType("CUSTOMER");
                    bsSysStateReport.setAttributeName("JIONUSERMESSAGE");
                    bsSysStateReport.setAuthority("ALL");
                    List<BsSysStateReport> stateReports = bsSysStateReportDao.findIsAlert(bsSysStateReport);
                    if (stateReports != null && stateReports.size() > 0) {
                        bsSysStateReport = stateReports.get(0);
                        bsSysStateReport.setAttributeValue("1");
                        bsSysStateReportDao.updateVersionMessage(cti, bsSysStateReport);
                    } else {
                        bsSysStateReport = new BsSysStateReport();
                        bsSysStateReport.setCpId(cti.getOrgId());
                        bsSysStateReport.setAreaId(spsTask.getAreaId());
                        bsSysStateReport.setDr(0);
                        bsSysStateReport.setOwnerType("CUSTOMER");
                        bsSysStateReport.setAttributeName("JIONUSERMESSAGE");
                        bsSysStateReport.setAttributeValue("1");
                        bsSysStateReportDao.insertVersionMessage(cti, bsSysStateReport);
                    }
                }
                List<WxMessage> messagelist = new ArrayList<>();
                NewTextMessage textMessage = null;
                for(SysUser u : sysUsers){
                	List<SysUserRole> sysUserRoles = sysUserRoleService.findUserRole(u.getUserId());
                	boolean isExits = false;
                	for(SysUserRole userRole : sysUserRoles){
                		if(SysRole.CUSTOMER_SUPER_ROLE_ID == userRole.getRoleId() 
                    			|| SysRole.CUSTOMER_BUSSINES_BOSS == userRole.getRoleId() 
                    			|| SysRole.CUSTOMER_BUSSINES_MANAGER == userRole.getRoleId()){
                            isExits = true;
                        }
                	}
                	if(isExits && StringUtils.isNotBlank(u.getCoreUserId())){
	            		// 给HR发信息收集消息
                		textMessage = new NewTextMessage();
                		textMessage.setTouser(u.getCoreUserId());
                		NewText newText = new NewText();
						// 修改手机号
						if(spsTask.getBstypeId() == BsType.MODIFY_PHONE.code()){
							newText.setContent("您有一条新任务，"+spsTask.getName()+"的变更手机号任务，请及时登录社保后台到任务中心进行处理！");
						}
						// 修改定点医院
						if(spsTask.getBstypeId() == BsType.MODIFY_HOSPITAL.code()){
							newText.setContent("您有一条新任务，"+spsTask.getName()+"的变更医院任务，请及时登录社保后台到任务中心进行处理！");
						}
						// 入职参保
						if(spsTask.getBstypeId() == BsType.INCREMENT_INSUR_ENTRY.code()){
							newText.setContent("您有一条新任务，"+spsTask.getName()+"的参保任务，请及时登录社保后台到任务中心进行处理！");
						}
						textMessage.setText(newText);
						messagelist.add(textMessage);
                	}
                }
                // 消息推送
				if(messagelist.size() > 0){
					System.out.println("============="+messagelist.toString());
					String  mString = sysMessageService.sendMessage(messagelist,"xfs_ss");
					System.out.println("============="+mString);
				}
            }
        }
    }

    /**
     * 处理任务，发送任务（调用taskbot Api）
     * @param spsTask
     */
    private void sendTaskToT( ContextInfo info, SpsTask spsTask) {
        /**
         * 对没有产生批次号的任务，产生批次号
         */
        TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
        Map<String,Object> taskJsonMap = JSON.parseObject(spsTask.getJson(),ref);
        taskJsonMap.put("taskSerialNum",null == spsTask.getTaskSerialNum() ? UUIDUtil.randomUUID() : spsTask.getTaskSerialNum());
        spsTask.setJson(JSON.toJSONString(taskJsonMap));

        /**
         * 将要执行的任务放到队列中
         */
        ApplyOnline applyOnline = new ApplyOnline();
        applyOnline.setBsTypeId(String.valueOf(spsTask.getBstypeId()));
        applyOnline.setTaskJson(spsTask.getJson());
        applyOnline.setUserId(String.valueOf(info.getUserId()));
        applyOnline.setUsbCode(spsTask.getRegNum());
        applyOnline.setInsuranceFundType(BsType.getInsType(spsTask.getBstypeId()));
        applyOnline.setAnalysisNum(String.valueOf(taskJsonMap.get("taskSerialNum")));
        applyOnline.setSessionId(String.valueOf(info.getExt().get("sessionId")));
        applyOnline.setTaskNo(spsTask.getTaskNo());
        applyOnline.setAreaId(spsTask.getAreaId());
        RedisUtil.push("TASK_TODO_QUEUE_NAME",applyOnline);
    }


    /**
     * 补全字段默认值
     *
     * @param jsonMap
     * @param businessFieldMap
     */
    public void compleFieldDef(Map<String, String> jsonMap, Map<String, BusinessField> businessFieldMap) {
        if (null != businessFieldMap && !businessFieldMap.isEmpty()) {
            for (Map.Entry<String, BusinessField> entry : businessFieldMap.entrySet()) {
                String value = jsonMap.get(entry.getKey());
                if (StringUtils.isBlank(value) && StringUtils.isNotBlank(entry.getValue().getDefaultValue())) {
                    jsonMap.put(entry.getKey(), entry.getValue().getDefaultValue());
                }
            }
        }
    }

}
