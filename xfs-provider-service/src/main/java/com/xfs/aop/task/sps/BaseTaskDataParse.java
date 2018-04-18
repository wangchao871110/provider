package com.xfs.aop.task.sps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.TimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.Result;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.MobileValidator;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;

/**
 * 基础类，目前 添加 校验功能
 * konglc
 */
public abstract class BaseTaskDataParse implements TaskDataParseInterface {

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


    /**
     * 注册事件
     */
    @PostConstruct
    public void registEvent(){
        taskAspect.addEventObject(this);
    }


    /**
     * 默认校验类
     * @param spsTask
     * @param businessParame
     * @param result
     * @return
     */
    @Override
    public boolean beforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String,Object> businessParame, Result result){
        if(spsTask.getEmpId() == null){
            result.setSuccess(false).setError("未找到员工");
            return false;
        }
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(spsTask.getEmpId());
        employee = cmEmployeeService.findByPK(employee);
        if(employee == null){
            result.setSuccess(false).setError("未找到员工");
            return false;
        }
        if(spsTask.getBstypeId() == null){
            result.setSuccess(false).setError("未找到业务类型");
            return false;
        }

        if(spsTask.getSchemeId() == null){
            result.setSuccess(false).setError("未找到方案");
            return false;
        }
        SpsScheme scheme = new SpsScheme();
        scheme.setSchemeId(spsTask.getSchemeId());
        scheme = spsSchemeService.findByPK(scheme);
        if(scheme == null){
            result.setSuccess(false).setError("未找到方案");
            return false;
        }

        if(StringUtils.isBlank(spsTask.getJson())){
            result.setSuccess(false).setError("业务参数为空");
            return false;
        }
		/*//校验任务单重复
		List<SpsTask> tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{spsTask.getBstypeId()},null,new String[]{"TODO","SUBMIT"});
		if(CollectionUtils.isNotEmpty(tasks)){
			result.setSuccess(false).setError("已存在相同任务");
			return false;
		}*/

        Map<String,BusinessField> businessFieldMap = bdBusinessfieldService.queryBusinessFields(scheme.getAreaId(),spsTask.getBstypeId());
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        StringBuffer err = new StringBuffer();
        boolean isok = true;
        //补全字段默认值
        compleFieldDef(curMap,businessFieldMap);
        //固定json字段
        if(StringUtils.isBlank(curMap.get("identity_type"))){
            curMap.put("identity_type",employee.getIdentityType());
        }
        if(StringUtils.isBlank(curMap.get("identity_code"))){
            curMap.put("identity_code",employee.getIdentityCode());
        }
        if(StringUtils.isBlank(curMap.get("name"))){
            curMap.put("name",employee.getName());
        }
        if(StringUtils.isBlank(curMap.get("mobile"))){
            curMap.put("mobile",employee.getMobile());
        }
        //补全身份证类型name 帅哥用
        SysDictitem queryDict = new SysDictitem();
        queryDict.setDictionary(89);
        List<SysDictitem> dictitems = sysDictitemService.findAll(queryDict);
        if(CollectionUtils.isNotEmpty(dictitems)){
            for(SysDictitem dictitem:dictitems){
                if(dictitem.getCode().equals(employee.getIdentityType())){
                    curMap.put("identity_type_name",dictitem.getName());
                    break;
                }
            }
        }
        Map<String,String> json = new HashMap<>();
        if(null != businessFieldMap && !businessFieldMap.isEmpty()) {
            for (Map.Entry<String, BusinessField> entry : businessFieldMap.entrySet()) {
                String fieldCode = entry.getKey();
                BusinessField businessField = entry.getValue();
                String value = curMap.get(fieldCode) == null ? null: curMap.get(fieldCode).trim();
                //去空格
                curMap.put(fieldCode,value);
                if (StringUtils.isBlank(businessField.getType())) {
                    continue;
                }
                if ("HIDDEN".equals(businessField.getType())) {
//                        if("identity_type".equals(fieldCode)){
//                            curMap.put(fieldCode,employee.getIdentityType());
//                        }else if("identity_code".equals(fieldCode)){
//                            curMap.put(fieldCode,employee.getIdentityCode());
//                        }else if("name".equals(fieldCode)){
//                            curMap.put(fieldCode,employee.getName());
//                        }else{
//                            isok = false;
//                            err.append("【" + businessField.getName() + "】 未匹配到的隐藏字段！");
//                            break;
//                        }
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
                            }else{
                                curMap.put(businessField.getCode()+"_name",codeName);
                            }
                        }
                    } else if ("FILE".equals(businessField.getType())) {
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
                json.put(fieldCode, value);
            }
        }

        //校验 手机号
        if(StringUtils.isNotBlank(curMap.get("mobile"))){
            if(!MobileValidator.isMobileNO(curMap.get("mobile"))){
                err.append("手机号格式错误！");
                isok = false;
            }
        }

        //校验身份证
        if(StringUtils.isBlank(curMap.get("identity_type"))){//证件类型 为空时 则 认为是身份证
            if(StringUtils.isNotBlank(curMap.get("identity_code"))){
                if(!IdcardValidator.isValidatedAllIdcard(curMap.get("identity_code"))){
                    err.append("证件号码格式错误！");
                    isok = false;
                }
            }
        }else{//证件类型为 身份证时候 itemcode 配置为IDCard
            if("IDCard".equals(curMap.get("identity_type"))){
                if(StringUtils.isNotBlank(curMap.get("identity_code"))){
                    if(!IdcardValidator.isValidatedAllIdcard(curMap.get("identity_code"))){
                        err.append("证件号码格式错误！");
                        isok = false;
                    }
                }
            }
        }

        if(!isok) {
            result.setSuccess(isok).setError(err.toString());
            return isok;
        }

        //分包方案
        SpsScheme fbScheme = null;
        if("DEPUTE".equals(scheme.getSchemeType())){
            fbScheme = new SpsScheme();
            fbScheme.setParentId(scheme.getSchemeId());
            List<SpsScheme> list = spsSchemeService.findAll(fbScheme);
            if(CollectionUtils.isNotEmpty(list)){
                fbScheme = list.get(0);
            }else {
                fbScheme = null;
            }
        }

        String beginDate = null;
        if(curMap.containsKey("beginDate")) {
            beginDate = curMap.get("beginDate");
            if(StringUtils.isBlank(beginDate)){
                isok = false;
                err.append("【生效时间】不能为空！");
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                Date inputDate = null;
                try {
                    inputDate = sdf.parse(beginDate);
                } catch (ParseException e) {
                    isok = false;
                    err.append("【生效时间】格式不正确！");
                }
                if(inputDate != null) {
                    Calendar calendar = Calendar.getInstance();
                   /* if (calendar.get(Calendar.DAY_OF_MONTH) > scheme.getEndDate()) {
                        calendar.add(Calendar.MONTH,1);
                    }*/
                    Calendar inputCal = Calendar.getInstance();
                    inputCal.setTime(inputDate);
                    Integer inputMonth = inputCal.get(Calendar.YEAR) * (inputCal.get(Calendar.MONTH)+1);
                    Integer toMonth = calendar.get(Calendar.YEAR) * (calendar.get(Calendar.MONTH)+1);

                    if (spsTask.getBstypeId() != 29 && spsTask.getBstypeId() != 30) {
                       /* if(inputMonth < toMonth){
                            isok = false;
                            err.append("【生效时间】请选择"+sdf.format(calendar.getTime())+"或之后！");
                        } else*/
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

                        Calendar currentCal = Calendar.getInstance();
                        int currentDay = currentCal.get(Calendar.DAY_OF_MONTH);

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
                                currCalendar.add(Calendar.MONTH,-1);
                                beginDateStr =  format.format(currCalendar.getTime());
                                if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                                    spsTask.setExecuteDate(beginDateStr);
                                }

                            }

                        } else if (spsTask.getBstypeId() == 4 || spsTask.getBstypeId() == 11) {

                            Calendar currCalendar = Calendar.getInstance();
                            if ("CURMONTH".equals(bdBsareatype.getEffect())) {
                               /* Calendar currentMoon = Calendar.getInstance();
                                currentMoon.add(Calendar.MONTH, -1);*/
                                //beginDateStr = format.format(currentMoon.getTime());
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

                            beginDateStr =  format.format(currCalendar.getTime());
                            if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                                spsTask.setExecuteDate(beginDateStr);
                            }

//                            if(beginDate.equals(beginDateStr)){
//                                spsTask.setType("TODO");
//                            }else {
//                                spsTask.setType("SUBMIT");
//                            }

                        } else
                        {
//                            if(inputMonth == toMonth){
//                                spsTask.setType("TODO");
//                            }else if(inputMonth > toMonth){
//                                spsTask.setType("SUBMIT");
//                            }
                        }

                    }else{
                        //补缴单算时间
                        beginDate = sdf.format(calendar.getTime());
                        //2016-12-10 修改 暂用当前时间作为补缴执行时间
                        spsTask.setExecuteDate(TimeUtils.convertDateToString(new Date(),"yyyy-MM"));
                        curMap.put("beginDate",beginDate);
                    }

                }
            }
        }

        if(isok){
            spsTask.setBeginDate(beginDate);
            if (StringUtils.isBlank(spsTask.getExecuteDate())) {
                spsTask.setExecuteDate(beginDate);
            }

            spsTask.setAreaId(scheme.getAreaId());
            spsTask.setSpId(scheme.getSpId());
            //分包方案
            if(fbScheme != null){
                spsTask.setParterSpId(scheme.getSpId());
                spsTask.setSchemeId(fbScheme.getSchemeId());
                spsTask.setSpId(fbScheme.getSpId());
                if(businessParame == null){
                    businessParame = new HashMap<>();
                }
                businessParame.put("zbSchemeId",scheme.getSchemeId());
            }else{
                spsTask.setSpId(scheme.getSpId());
            }
            spsTask.setDr(0);
            spsTask.setIdentityCode(employee.getIdentityCode());
            spsTask.setMobile(employee.getMobile());
            spsTask.setJson(JSON.toJSONString(curMap));
            spsTask.setName(employee.getName());
            spsTask.setEmpId(employee.getEmpId());
//			String taskNo = TaskNoNum.makeOrderNum();
//			if(taskNo == null){
//				result.setSuccess(false).setError("未获取到任务单号");
//				return  false;
//			}
//			spsTask.setTaskNo(taskNo);
            isok = beforeSaveTask(info,spsTask,businessParame,businessFieldMap,result);
        }
        else {
            result.setSuccess(isok).setError(err.toString());
        }
        return isok;
    }

    /**
     * 补全字段默认值
     * @param jsonMap
     * @param businessFieldMap
     */
    public void compleFieldDef(Map<String,String> jsonMap,Map<String,BusinessField> businessFieldMap){
        if(null != businessFieldMap && !businessFieldMap.isEmpty()) {
            for (Map.Entry<String, BusinessField> entry : businessFieldMap.entrySet()) {
                String value = jsonMap.get(entry.getKey());
                if(StringUtils.isBlank(value) && StringUtils.isNotBlank(entry.getValue().getDefaultValue())){
                    jsonMap.put(entry.getKey(),entry.getValue().getDefaultValue());
                }
            }
        }
    }
}
