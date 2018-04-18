package com.xfs.employeeside.service.impl;/**
 * @author hongjie
 * @date 2017/3/15.17:01
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.service.SysDictitemService;
import com.xfs.business.dao.BdBstypeDao;
import com.xfs.business.dao.SpsTaskDao;
import com.xfs.business.dao.SpsTaskHistoryDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.employeeside.dto.SpsStateDto;
import com.xfs.employeeside.service.BussinessService;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.model.SpsScheme;

import javassist.expr.NewArray;

/**
 * 我的业务服务接口
 *
 * @author
 * @create 2017-03-15 17:01
 **/
@Service
public class BussinessServiceImpl implements BussinessService , IStaticVarConstant{

    @Autowired
    SpsTaskDao spsTaskDao;
    @Autowired
    BdBstypeDao bdBstypeDao;
    @Autowired
    private SpsSchemeDao spsSchemeDao;
    @Autowired
    SpsTaskHistoryDao spsTaskHistoryDao;
    @Autowired
    private CmEmployeeConfigService cmEmployeeConfigService;
    @Autowired
    private BdBusinessfieldDao bdBusinessfieldDao;
    @Autowired
    private SysDictitemService sysDictitemService;

    @Override
    public Result getAllBusiness(Integer empId) {
//        SELECT * FROM sps_task WHERE emp_id=39661 ORDER BY FIELD(type,'TODO','SUBMIT','ERROR','COMPLETED','CLOSED') ,create_dt DESC;
        Result result = Result.createResult().setSuccess(true);

        // 根据 员工id   获取员工所在区域
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setError("员工对应的方案不存在！");
            result.setSuccess(true);
            return result;
        }
        CmEmployeeConfig vo = new CmEmployeeConfig();
        vo.setCpId(spsScheme.getCpId());
        vo.setAreaId(spsScheme.getAreaId());
        vo.setDr(0);
        vo.setIsEmp(1);
        vo.setIsEmpService(1);

        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigService.findAll(vo);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(cmEmployeeConfigs) || cmEmployeeConfigs.size() > 1) {
            result.setSuccess(false);
            result.setError("服务未开通!");
            return result;
        }



        Map map = new HashMap();
        map.put("empId",empId);
        map.put("bstypeId",cmEmployeeConfigs.get(0).getOnLineService());

        List<SpsTask> spsTaskList = spsTaskDao.getAllSpsTaskAndSort(map);
        List spsTask = new ArrayList();
        getSpsTaskList(spsTaskList, spsTask);
        result.setDataValue("spsTask", spsTask);
        return result;
    }



    @Override
    public Result getAllTodo(Integer empId) {
        Result result = Result.createResult().setSuccess(true);
        // 查询所有 type 为正在处理（"TOdO" ）的  任务单
        // 根据 员工id   获取员工所在区域
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setError("员工对应的方案不存在！");
            result.setSuccess(true);
            return result;
        }

        CmEmployeeConfig vo = new CmEmployeeConfig();
        vo.setCpId(spsScheme.getCpId());
        vo.setAreaId(spsScheme.getAreaId());
        vo.setDr(0);
        vo.setIsEmp(1);
        vo.setIsEmpService(1);

        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigService.findAll(vo);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(cmEmployeeConfigs) || cmEmployeeConfigs.size() > 1) {
            result.setSuccess(false);
            result.setError("服务未开通!");
            return result;
        }



        Map map = new HashMap();
        map.put("empId",empId);
        map.put("bstypeId",cmEmployeeConfigs.get(0).getOnLineService());

        List<SpsTask> spsTaskList = spsTaskDao.getTwoTodoAndSort(map);
        if (CollectionUtils.isEmpty(spsTaskList)) {
            result.setDataValue("spsTask", "");
            return result;
        }

        List spsTask = new ArrayList();
        getSpsTaskList(spsTaskList, spsTask);
        result.setDataValue("spsTask", spsTask);
        return result;
    }

    public void sort(List<SpsTask> list) {
        Collections.sort(list, new Comparator<SpsTask>() {
            public int compare(SpsTask o1, SpsTask o2) {
                return o1.getCreateDt().getTime() - o2.getCreateDt().getTime() > 0 ? 1 : -1;
            }
        });
    }


    /**
     * 获取
     *
     * @param spsTaskList
     * @param spsTask
     */
    private void getSpsTaskList(List<SpsTask> spsTaskList, List spsTask) {
        for (SpsTask spsTask1 : spsTaskList) {
            Map map = new HashMap();
            map.put("bsTypeId", spsTask1.getBstypeId());
            BdBstype bstype = new BdBstype();
            bstype.setBstypeId(spsTask1.getBstypeId());
            BdBstype bst = bdBstypeDao.freeFind(bstype) != null ? bdBstypeDao.freeFind(bstype).get(0) : null;
//            if (bst.getName().equals("补缴")) {
//                if (bst.getInsuranceFundType().equals("INSURANCE")) {
//                    map.put("bsTypeName", "社保" + bst.getName());
//                } else {
//                    map.put("bsTypeName", "公积金" + bst.getName());
//                }
//            } else {
//                map.put("bsTypeName", bst.getName());
//            }
            map.put("bsTypeName", bst.getName());
            map.put("cpId", spsTask1.getCpId());
            map.put("createDt", com.xfs.common.util.DateUtil.getTimeDate(spsTask1.getCreateDt()));
            if (spsTask1.getModifyDt() == null) {
                map.put("modifyDt", com.xfs.common.util.DateUtil.getTimeDate(spsTask1.getCreateDt()));
            } else {
                map.put("modifyDt", com.xfs.common.util.DateUtil.getTimeDate(spsTask1.getModifyDt()));
            }

            map.put("errMsg", spsTask1.getErrmsg());
            map.put("onlineStatus", spsTask1.getOnlineStatus());
            map.put("spId", spsTask1.getSpId());

            if (StringUtils.isEmpty(spsTask1.getStateFiledName())) {
                if (spsTask1.getType().equals("TODO")) {
                    map.put("stateFieldId", 7330);
                    map.put("stateFieldName", "提交申请");
                }
//                else if (spsTask1.getType().equals("COMPLETED")) {
//                    map.put("stateFieldId", 7009);
//                    map.put("stateFieldName", "办理成功");
//                } else if (spsTask1.getType().equals("ERROR")) {
//                    map.put("stateFieldId", 7000);
//                    map.put("stateFieldName", "办理异常");
//                } else if (spsTask1.getType().equals("SUBMIT")) {
//                    map.put("stateFieldId", 7143);
//                    map.put("stateFieldName", "网申中");
//                } else {
//                    map.put("stateFieldId", 7330);
//                    map.put("stateFieldName", "提交申请");
//                }
            } else {
                map.put("stateFieldId", spsTask1.getStateFiledId());
                map.put("stateFieldName", spsTask1.getStateFiledName());
            }

            map.put("taskId", spsTask1.getTaskId());
            map.put("type", spsTask1.getType());
            spsTask.add(map);
        }
    }

    @Override
    public Result getBusinessHistoryByTaskId(Integer taskId){
        Result result = Result.createResult().setSuccess(true);
        List<Map<String, Object>> taskInfoList = new ArrayList<Map<String, Object>>();
        SpsTask spsTask = new SpsTask();
        spsTask.setTaskId(taskId);
        spsTask = spsTaskDao.findByPK(spsTask);
        if (spsTask == null) {
            result.setError("您要查询的任务不存在!");
            result.setSuccess(false);
            return result;
        }
        // 获取正在执行的 状态
        // 获取已经执行过的状态
        SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
        spsTaskHistory.setTaskId(spsTask.getTaskId());
        List<Map<String, Object>> resultList = spsTaskHistoryDao.findDetailHistory(spsTaskHistory);
        for (Map<String, Object> map : resultList) {
            Map<String,Object> temp = new HashMap<String,Object>();
            temp.put("code", "");
            temp.put("name", map.get("state_filed_name"));
            temp.put("state", map.get("type"));
            temp.put("time", map.get("create_dt").toString().substring(0, map.get("create_dt").toString().length() - 2));
            temp.put("location", "");
            temp.put("operate_pic", map.get("operate_pic"));
            taskInfoList.add(temp);
        }
        result.setDataValue("taskInfoList", taskInfoList);
        return result;
    }


    @Override
    public Result getBusinessDetailByTaskId(Integer taskId) {

        Result result = Result.createResult().setSuccess(true);
        SpsTask spsTask = new SpsTask();
        spsTask.setTaskId(taskId);
        spsTask = spsTaskDao.findByPK(spsTask);
        if (spsTask == null) {
            result.setError("您要查询的任务不存在!");
            result.setSuccess(false);
            return result;
        }

        Integer cpId = spsTask.getCpId();
        CmCorp vo = new CmCorp();
        vo.setCpId(cpId);

        String companyType = CMCORPTYPE_SERVICE;
        // 查询 所有的 状态
        Integer[] bstypeId = new Integer[1];
        // 获取业务类型
    	JSONObject oldJson = JSON.parseObject(spsTask.getJson());
    	if(null != oldJson.getInteger("bstypeId")
    			&& BsType.INCREMENT_INSUR_ENTRY.getCode().equals(spsTask.getBstypeId())){
    		bstypeId[0]=oldJson.getInteger("bstypeId");
    	}else{
    		bstypeId[0]=spsTask.getBstypeId();
    	}
        List<SpsStateDto> spsStateDtoList = spsTaskDao.getAllStateBy(bstypeId[0], spsTask.getAreaId(), companyType);
        if (CollectionUtils.isEmpty(spsStateDtoList)) {
            result.setError("该业务类型的状态没有配置，请配置后在进行操作！");
            result.setSuccess(false);
            return result;
        }
        // 获取正在执行的 状态
        // 获取已经执行过的状态
        SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
        spsTaskHistory.setTaskId(spsTask.getTaskId());
        List<Map<String, Object>> resultList = spsTaskHistoryDao.findDetailHistory(spsTaskHistory);
        //List<Map<String, Object>> taskInfoList = new ArrayList<Map<String, Object>>();
//        for (SpsStateDto spsStateDto : spsStateDtoList) {
//            Map<String,Object> temp = new HashMap<String,Object>();
//            Map<String, Object> res = isContain(resultList, spsStateDto.getName().toString());
//            if (spsStateDto.getName().equals("办理异常")) {
//                if (res != null) {
//                    temp.put("code", spsStateDto.getCode());
//                    temp.put("name", spsStateDto.getName());
//                    temp.put("state", res.get("type"));
//                    temp.put("time", res.get("create_dt").toString().substring(0, res.get("create_dt").toString().length() - 2));
//                    temp.put("location", "");
//                    temp.put("operate_pic", res.get("operate_pic"));
//                    taskInfoList.add(temp);
//                }
//            } else {
//                if (res != null) {
//                    temp.put("code", spsStateDto.getCode());
//                    temp.put("name", spsStateDto.getName());
//                    temp.put("state", res.get("type"));
//                    temp.put("time", res.get("create_dt").toString().substring(0, res.get("create_dt").toString().length() - 2));
//                    temp.put("location", "");
//                    temp.put("operate_pic", res.get("operate_pic"));
//                } else {
////                    temp.put("code", spsStateDto.getCode());
////                    temp.put("name", spsStateDto.getName());
////                    temp.put("state", "");
////                    temp.put("time", "");
////                    temp.put("location", "");
////                    temp.put("operate_pic", "");
//                }
//                if(temp.size() > 0){
//                	taskInfoList.add(temp);
//                }
//            }
//        }
//        if(spsStateDtoList.size()>1){
//            if (spsTask.getStateFiledName().equals(spsStateDtoList.get(0).getName())
//                    || spsTask.getStateFiledName().equals(spsStateDtoList.get(1).getName())) {
//                result.setDataValue("canUpdate", 1);
//            } else {
//                result.setDataValue("canUpdate", 2);
//            }
//        }else{
//            if (spsTask.getStateFiledName().equals("提交申请") || spsTask.getStateFiledName().equals("信息待审核")) {
//                result.setDataValue("canUpdate", 1);
//            } else {
//                result.setDataValue("canUpdate", 2);
//            }
//        }
        result.setDataValue("taskInfoList", this.isStatus(resultList,spsTask.getTaskId()));
    	
    	List<BusinessField> bdBusinessfieldList = bdBusinessfieldDao.queryBusinessFields(spsTask.getAreaId(), bstypeId);
//		if (bstypeId[0] == BsType.MODIFY_HOSPITAL.code()) {
//            String df = oldJson.get("hospital").toString();
//            JSONArray hospital = JSONObject.parseArray(df);
//            oldJson.remove("hospital");
//            setDefaultValue(bdBusinessfieldList, oldJson,spsTask.getAreaId());
//            for (int i = 0; i < hospital.size(); i++) {
//                for (BusinessField businessField : bdBusinessfieldList) {
//                    if (businessField.getCode().equals("hospital" + (i + 1))) {
//                    	businessField.setName(((JSONObject) hospital.get(i)).get("name").toString());
//                        businessField.setDefaultValue(((JSONObject) hospital.get(i)).get("region").toString());
//                    }
//                }
//            }
//            List<BusinessField> lists = new ArrayList<>();
//            for (BusinessField businessField : bdBusinessfieldList) {
//            	if(StringUtils.isNotBlank(businessField.getDefaultValue())){
//            		lists.add(businessField);
//            	}
//            }
//            result.setDataValue("bsTypeList", lists);
//        } else {
             setDefaultValue(bdBusinessfieldList, oldJson,spsTask.getAreaId());
             result.setDataValue("state", spsTask.getType());
             result.setDataValue("bsTypeList", bdBusinessfieldList);
        //}
        BdBstype bdBstype = new BdBstype();
        bdBstype.setBstypeId(bstypeId[0]);
        bdBstype = bdBstypeDao.findByPK(bdBstype);
        result.setDataValue("bsTypeId", bdBstype.getBstypeId());
        result.setDataValue("bsTypeName", bdBstype.getName());
        return result;
    }
    
    /**
     * 设置默认值
     *  @param bdBusinessfieldList
     *  @param object 
     *	@return 			: void 
     *  @createDate  	: 2017年10月26日 下午5:37:41
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月26日 下午5:37:41
     *  @updateAuthor  :
     */
    private void setDefaultValue(List<BusinessField> bdBusinessfieldList, JSONObject object,Integer areaId) {
        for (BusinessField businessField : bdBusinessfieldList) {
        	if("oldHos".equals(businessField.getCode())
        			|| "newHos".equals(businessField.getCode())){
        		businessField.setPageIsHidden("HIDDEN");
        		continue;
        	}
            if (object.containsKey(businessField.getCode())) {
                if (businessField.getType().equals("PULL")) {
                    if (object.containsKey(businessField.getCode() + "_name")) {
                        businessField.setDefaultValue(object.get(businessField.getCode()).toString());
                    }
                    List<SysDictitem> sysDictitemList = sysDictitemService.findByDictNameAndArea(businessField.getCode() + "_doc", areaId);
                    businessField.setOptions(sysDictitemList);
                } else {
                	businessField.setDefaultValue(object.get(businessField.getCode()).toString());
                }
            }
        }
    }

    /**
     * 判断是否在 完成的状态中
     *
     * @param result
     * @param code
     * @return
     */
    private Map<String, Object> isContain(List<Map<String, Object>> result, String code) {
        for (Map<String, Object> map : result) {
            if (map.containsKey("state_filed_name") && map.get("state_filed_name").toString().equals(code)) {
            	SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
            	spsTaskHistory.setId(Integer.valueOf(map.get("id").toString()));
            	spsTaskHistory.setIsRead("READ");
            	spsTaskHistoryDao.update(null, spsTaskHistory);
                return map;
            }
        }
        return null;
    }

    /**
     * 获取任务状态
     *  @param result
     *  @return 
     *	@return 			: Map<String,Object> 
     *  @createDate  	: 2017年11月27日 下午5:07:51
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年11月27日 下午5:07:51
     *  @updateAuthor  :
     */
    private List<Map<String, Object>> isStatus(List<Map<String, Object>> result,Integer taskId) {
    	List<Map<String, Object>> taskInfoList = new ArrayList<>();
    	Map<String,Object> temp = null;
    	// 判断是否存在待处理状态
    	boolean wflag = true;
        // 判断是否存在处理中状态
    	boolean tflag = true;
        // 判断是否存在已失败状态
    	boolean eflag = true;
    	// 判断是否存在已完成状态
    	boolean cflag = true;
        for (Map<String, Object> map : result) {
            if (cflag && map.get("state_filed_id").equals(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId())) {
            	temp = new HashMap<String,Object>();
                temp.put("name", "已成功");
                temp.put("time", map.get("create_dt").toString().substring(0, map.get("create_dt").toString().length() - 2));
                taskInfoList.add(temp);
                cflag = false;
                continue;
            }
            if (eflag && map.get("state_filed_id").equals(TaskStateFiled.CLOSED_APPLICATION.getStateFiledId())) {
            	temp = new HashMap<String,Object>();
                temp.put("name", "已失败");
                temp.put("time", map.get("create_dt").toString().substring(0, map.get("create_dt").toString().length() - 2));
                taskInfoList.add(temp);
                eflag = false;
                continue;
            }
            if (tflag && (map.get("state_filed_id").equals(TaskStateFiled.TODO_ONLINE_APPLICATION.getStateFiledId())
            				|| map.get("state_filed_id").equals(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId())
            				|| map.get("state_filed_id").equals(TaskStateFiled.ERROR_APPLICATION.getStateFiledId())
            		)) {
            	temp = new HashMap<String,Object>();
                temp.put("name", "处理中");
                temp.put("time", map.get("create_dt").toString().substring(0, map.get("create_dt").toString().length() - 2));
                taskInfoList.add(temp);
                tflag = false;
                continue;
            }
            if (wflag && (map.get("state_filed_id").equals(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId())
    				|| map.get("state_filed_id").equals(TaskStateFiled.TODO_TJSQ_APPLICATION.getStateFiledId()))) {
            	temp = new HashMap<String,Object>();
                temp.put("name", "待处理");
                temp.put("time", map.get("create_dt").toString().substring(0, map.get("create_dt").toString().length() - 2));
                taskInfoList.add(temp);
                wflag = false;
                continue;
            }
        }
        // 更新已读
    	SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
    	spsTaskHistory.setTaskId(taskId);
    	spsTaskHistory.setIsRead("READ");
    	spsTaskHistoryDao.updateByTaskId(null, spsTaskHistory);
        return taskInfoList;
    }
}
