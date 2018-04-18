package com.xfs.business.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfs.acc.dto.ApplyMessage;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.Constant;
import com.xfs.common.util.DateUtil;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.employeeside.dto.SpsStateDto;
import com.xfs.sp.dto.SpsTaskDto;
import com.xfs.task.dto.FlowChartDto;
import com.xfs.task.dto.HandlePersonnelDto;
import com.xfs.task.dto.HandlePersonnelListVo;
import com.xfs.task.dto.ItemWarnDto;
import com.xfs.task.dto.ItemWarnVo;
import com.xfs.task.dto.TaskCenterDto;
import com.xfs.task.dto.TaskDto;
import com.xfs.task.dto.TaskMapVo;
import com.xfs.task.dto.TaskVo;
import com.xfs.user.service.SysFunctionDataService;

/**
 * SpsTaskDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/08 11:21:48
 */
@Repository
public class SpsTaskDao extends BaseSqlMapDao {
    @Autowired
    private SysFunctionDataService sysFunctionDataService;

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TASK.CountFindAllSPS_TASK", null);
        return ret.intValue();
    }

    public SpsTask findByPK(SpsTask obj) {
        Object ret = queryForObject("SPS_TASK.FindByPK", obj);
        if (ret != null)
            return (SpsTask) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<SpsTask> freeFind(SpsTask obj) {
        return queryForList("SPS_TASK.FreeFindSPS_TASK", obj);
    }

    public int countFreeFind(SpsTask obj) {
        Integer ret = (Integer) queryForObject("SPS_TASK.CountFreeFindSPS_TASK", obj);
        return ret.intValue();
    }

    public int countFreeFindOld(SpsTask obj) {
        Integer ret = (Integer) queryForObject("SPS_TASK.CountFreeFindSPS_TASK_OLD", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SpsTask> freeFind(SpsTask obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_TASK.FreeFindSPS_TASK", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<SpsTask> freeFind(SpsTask obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsTask) {
            _hashmap = ((SpsTask) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("SPS_TASK.FreeFindSPS_TASKOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<SpsTask> freeFind(SpsTask obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsTask) {
            _hashmap = ((SpsTask) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("SPS_TASK.FreeFindSPS_TASKOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<SpsTask> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<SpsTask> it = objColl.iterator();
            while (it.hasNext()) {
                SpsTask oneObj = (SpsTask) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, SpsTask vo) {
        SpsTask obj = (SpsTask) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, SpsTask obj) {

        obj.fixup();
        return insert(cti, "SPS_TASK.InsertSPS_TASK", obj);
    }

    public int update(ContextInfo cti, SpsTask obj) {

        obj.fixup();
        return update(cti, "SPS_TASK.UpdateSPS_TASK", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, SpsTask obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SPS_TASK.DeleteSPS_TASK", obj);

    }

    public int removeObjectTree(ContextInfo cti, SpsTask obj) {

        obj.fixup();
        SpsTask oldObj = (SpsTask) queryForObject("SPS_TASK.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "SPS_TASK.DeleteSPS_TASK", oldObj);

    }

    public boolean exists(SpsTask vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SpsTask obj = (SpsTask) vo;

        keysFilled = keysFilled && (null != obj.getTaskId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("SPS_TASK.CountSPS_TASK", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/08 11:21:48


    /**
     * 条件搜索任务列表总数
     *
     * @param vo
     * @param searchWord
     * @return
     */
    public Integer findTaskCountByCondition(SpsTask vo, String searchWord) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo != null) {
            _hashmap = vo.toHashMap();
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord.trim());
        }
        Integer ret = (Integer) queryForObject("SPS_TASK.findTaskCountByCondition_CS", _hashmap);
        return ret.intValue();
    }

    /**
     * 条件搜索任务列表
     *
     * @param vo
     * @param searchWord
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findTasksByCondition(SpsTask vo, String searchWord, Integer pageIndex, Integer pageSize) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo != null) {
            _hashmap = vo.toHashMap();
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord.trim());
        }
        return getPaginatedList("SPS_TASK.findTasksByCondition_CS", _hashmap, pageIndex, pageSize);
    }

    /**
     * 查找员工 按多类型查找 按创建时间倒序
     *
     * @param bsType    任务类型
     * @param type      任务状态
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List findTaskInBsType(Integer empId, String bsType, String type, String[] typeIn, Integer pageIndex, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("empId", empId);
        map.put("bstypeId", bsType);
        map.put("type", type);
        map.put("typeIn", typeIn);
        return getPaginatedList("SPS_TASK.findTaskInBsTypeAndType", map, pageIndex, pageSize);
    }

    /**
     * 查找员工 按多类型查找 按创建时间倒序
     *
     * @param bsType    任务类型
     * @param type      任务状态
     * @return
     */
    public List<SpsTask> findTaskInBsType(Integer empId, String bsType, String type, String[] typeIn) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("empId", empId);
        map.put("bstypeId", bsType);
        map.put("type", type);
        map.put("typeIn", typeIn);
        return queryForList("SPS_TASK.findTaskInBsTypeAndType", map);
    }


    public List<Map<String, Object>> findTaskCount(Integer obj) {
        HashMap<String, Object> _hashMap = new HashMap<String, Object>();
        _hashMap.put("sp_id", obj);
        return queryForList("SPS_TASK.findTaskCount", _hashMap);
    }

    /**
     * 查询 企业下 任务单信息
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpList(String type, Integer spId, Integer bstypeId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        hashMap.put("type", type);
        hashMap.put("bstype_id", bstypeId);
        return queryForList("SPS_TASK.findEmpList", hashMap);
    }


    public List<Map<String, Object>> findOrgList(String type, Integer spId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        hashMap.put("type", type);
        return queryForList("SPS_TASK.findOrgList", hashMap);
    }

    /**
     * 查询 企业下 任务单信息
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskList(Integer empId, String type, Integer spId, Integer bstypeId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        hashMap.put("type", type);
        hashMap.put("bstype_id", bstypeId);
        hashMap.put("empId", empId);
        return queryForList("SPS_TASK.findEmpPersonList", hashMap);
    }

    /**
     * 查询 企业下 任务单json 字段
     *
     * @author lifq
     * 2016-08 加入执行年月条件
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskJsonList(Integer scheme_id, String type, Integer spId, Integer bstypeId, String extcuteDate) {
        SpsTask spsTask = new SpsTask();
        spsTask.setSpId(spId);
        spsTask.setType(type);
        spsTask.setExecuteDate(extcuteDate);//Taskbot 调用 查询为处理任务单 月份为当前月
        if (bstypeId != 0) {
            spsTask.setBstypeId(bstypeId);
        }
        spsTask.setSchemeId(scheme_id);
        return queryForList("SPS_TASK.findEmpPersonJsonList", spsTask);
    }

    public List<Map<String, Object>> findCurrMonthTaskJsonList(Integer cpId, String type, Integer spId, Integer bstypeId, String extcuteDate) {
        SpsTask spsTask = new SpsTask();
        spsTask.setSpId(spId);
        spsTask.setType(type);
        spsTask.setExecuteDate(extcuteDate);//Taskbot 调用 查询为处理任务单 月份为当前月
        if (bstypeId != 0) {
            spsTask.setBstypeId(bstypeId);
        }
        spsTask.setCpId(cpId);
        return queryForList("SPS_TASK.findCurrMonthTaskJsonList", spsTask);
    }

    /**
     * 查询 企业下 任务单信息 （分页）
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List findEmpPersonPageData(Integer empId, String type, Integer spId, Integer bstypeId, Integer pageIndex, Integer pageSize) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        hashMap.put("type", type);
        hashMap.put("bstype_id", bstypeId);
        hashMap.put("empId", empId);
        return getPaginatedList("SPS_TASK.findEmpPersonList", hashMap, pageIndex, pageSize);
    }

    /**
     * 查询 记录数
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午9:45:47
     */
    public Integer findEmpPersonPageCount(Integer empId, String type, Integer spId, Integer bstypeId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        hashMap.put("type", type);
        hashMap.put("bstype_id", bstypeId);
        hashMap.put("empId", empId);
        Integer ret = (Integer) queryForObject("SPS_TASK.findEmpPersonCount", hashMap);
        return ret.intValue();
    }

    /**
     * 查询 企业记录数
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午11:02:57
     */
    public Integer findEmpPageCount(Integer spId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        Integer ret = (Integer) queryForObject("SPS_TASK.findEmpPageCount", hashMap);
        return ret.intValue();
    }

    /**
     * 查询 服务商下 企业信息
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午11:07:08
     */
    public List findEmpPageData(Integer spId, Integer pageIndex, Integer pageSize) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sp_id", spId);
        return getPaginatedList("SPS_TASK.findEmpPageData", hashMap, pageIndex, pageSize);
    }

    public List<Map<String, Object>> findSpsTaskBotList(SpsTask spsTask) {
        return queryForList("SPS_TASK.findSpsTaskBotList", spsTask);
    }

    /**
     * 根据服务商id和区域id获取企业列表、任务汇总信息总数
     *
     * @param vo
     * @return
     */
    public Integer findTaskAndCorpCount(SpsTask vo, String searchWord, ContextInfo user) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo instanceof SpsTask) {
            _hashmap = ((SpsTask) vo).toHashMap();
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord);
        }
        // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(user.getUserId(), user.getUserType(), Constant.DATATYPE);
        _hashmap.put("authority", authority);
        Integer ret = (Integer) queryForObject("SPS_TASK.findTaskAndCorpCount", _hashmap);
        return ret.intValue();
    }

    /**
     * 根据服务商id和区域id获取企业列表、任务汇总信息
     *
     * @param vo
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findTaskAndCorps(SpsTask vo, String searchWord
            , Integer pageIndex, Integer pageSize, ContextInfo user) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo instanceof SpsTask) {
            _hashmap = ((SpsTask) vo).toHashMap();
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord);
        }
        // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(user.getUserId(), user.getUserType(), Constant.DATATYPE);
        _hashmap.put("authority", authority);
        return getPaginatedList("SPS_TASK.findTaskAndCorps", _hashmap, pageIndex, pageSize);
    }


    /**
     * 条件搜索任务列表总数
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    public Integer findTaskCountByCondition(BdBstype bstype, SpsTask vo, String searchWord) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo != null) {
            _hashmap = vo.toHashMap();
        }
        if (bstype != null) {
            _hashmap.put("insuranceFundType", bstype.getInsuranceFundType());
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord.trim());
        }
        Integer ret = (Integer) queryForObject("SPS_TASK.findTaskCountByCondition", _hashmap);
        return ret.intValue();
    }

    /**
     * 条件搜索任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findTasksByCondition(BdBstype bstype, SpsTask vo, String searchWord, Integer pageIndex, Integer pageSize) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo != null) {
            _hashmap = vo.toHashMap();
        }
        if (bstype != null) {
            _hashmap.put("insuranceFundType", bstype.getInsuranceFundType());
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord.trim());
        }
        return getPaginatedList("SPS_TASK.findTasksByCondition", _hashmap, pageIndex, pageSize);
    }

    /**
     * 条件搜索所有任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    public List<Map<String, Object>> findALLTasksByCondition(BdBstype bstype, SpsTask vo, String searchWord) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (vo != null) {
            _hashmap = vo.toHashMap();
        }
        if (bstype != null) {
            _hashmap.put("insuranceFundType", bstype.getInsuranceFundType());
        }
        if (searchWord != null && !searchWord.isEmpty()) {
            _hashmap.put("searchWord", searchWord.trim());
        }
        return queryForList("SPS_TASK.findTasksByCondition", _hashmap);
    }

    /**
     * 查询 待处理 任务单
     *
     * @author lifq
     * <p>
     * 2016年5月26日  下午3:34:46
     */
    public List<SpsTask> findTo_Deal_SPS_TASK(SpsTask obj) {
        return queryForList("SPS_TASK.findTo_Deal_SPS_TASK", obj);
    }

    public HashMap<String, Object> findProgressData(Integer scheme_id, String type) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("scheme_id", scheme_id);
        hashMap.put("type", type);
        List<HashMap<String, Object>> list = queryForList("SPS_TASK.findProgressData", hashMap);
        if (list == null || list.size() == 0) {
            return new HashMap<>();
        }
        return list.get(0);
    }

    public HashMap findTaskProgress(Integer scheme_id, String type) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("scheme_id", scheme_id);
        hashMap.put("type", type);
        List<HashMap<String, Object>> list = queryForList("SPS_TASK.findTaskProgress", hashMap);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void insertTaskProgress(ContextInfo cti, Integer scheme_id, String type, String mobile, Integer spid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("scheme_id", scheme_id);
        map.put("type", type);
        map.put("state", "TODO");
        map.put("time", new Date());
        map.put("mobile", mobile);
        map.put("spid", spid);
        map.put("createtime", new Date());
        map.put("updatetime", new Date());
        map.put("progress", 0);
        insert(cti, "SPS_TASK.insertTaskProgress", map);
    }

    public void updateTaskProgress(ContextInfo cti, Integer scheme_id, String type, String sate) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("scheme_id", scheme_id);
        map.put("type", type);
        map.put("state", sate);
        map.put("time", new Date());
//		map.put("createtime", new Date());
        map.put("updatetime", new Date());
        map.put("progress", 0);
        update(cti, "SPS_TASK.updateTaskProgress", map);
    }

    /**
     * 2016-08 任务管理 新需求
     * 任务管理 数据列表
     * zhangxiyan
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> freeFindAllList(SpsTask obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_TASK.FreeFindSPS_TASK_ALL_List", obj, pageIndex, pageSize);
    }

    /**
     * 2016-08 任务管理 新需求
     * 根据任务单ID 查询任务单详情
     * zhangxiyan
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> findDetailByTaskId(SpsTask spsTask) {
        return (HashMap<String, Object>) queryForObject("SPS_TASK.FreeFindSPS_TASK_List", spsTask);
    }

    /**
     * 2016-08 任务管理 新需求
     * 根据任务单ID 查询任务单详情
     * 返回实体 SpsTask
     * zhangxiyan
     */
    @SuppressWarnings("unchecked")
    public SpsTask findTaskDetailByTaskId(SpsTask spsTask) {
        return (SpsTask) queryForObject("SPS_TASK.FreeFindSPS_TASK_Detail_List", spsTask);
    }

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 执行 任务单 状态  完成  终止 按钮操作
     */
    public int updatetoComplete(ContextInfo cti, SpsTask spsTask) {
        return update(cti, "SPS_TASK.UpdateSPS_TASK_TASKID", spsTask);
    }

    public List<Map<String, Object>> freeFindAllListByEmp(SpsTask obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_TASK.FreeFindSPS_TASK_List_By_Emp", obj, pageIndex, pageSize);
    }

    public Integer freeFindAllListCountByEmp(SpsTask spsTask) {
        Integer ret = (Integer) queryForObject("SPS_TASK.FreeFindSPS_TASK_List_Count_By_Emp", spsTask);
        return ret.intValue();
    }

    public Map<String, Object> findSpsTaskByEmp(SpsTask spsTask, String insuranceFundType) {
        HashMap<String, Object> _hashMap = new HashMap<String, Object>();
        _hashMap.put("empId", spsTask.getEmpId());
        _hashMap.put("insuranceFundType", insuranceFundType);
        Map<String, Object> objectMap = (Map<String, Object>) queryForObject("SPS_TASK.FindSPS_TASK_By_Emp", _hashMap);
        return objectMap;
    }

    public List<Map<String, Object>> findSpsTaskListByEmp(SpsTask spsTask, String insuranceFundType) {
        HashMap<String, Object> _hashMap = new HashMap<String, Object>();
        _hashMap.put("empId", spsTask.getEmpId());
        _hashMap.put("insuranceFundType", insuranceFundType);
        List<Map<String, Object>> objectMap = (List<Map<String, Object>>) queryForList("SPS_TASK.FindSPS_TASKLIST_By_Emp", _hashMap);
        return objectMap;
    }


    /**
     * 查找员工 按多类型与状态查找 按创建时间倒序
     *
     * @return
     */
    public List findTaskByBsTypeAndState(Integer empId, Integer[] bstypeId, String state, String[] states) {
        Map<String, Object> param = new HashMap<>();
        param.put("empId", empId);
        param.put("bstypeId", bstypeId);
        param.put("state", state);
        param.put("states", states);
        return queryForList("SPS_TASK.findTaskByBsTypeAndState", param);
    }

    /**
     * \
     *
     * @param obj
     * @return
     */
    public SpsTaskDto findTaskByType(SpsTask obj) {

        Object object = queryForObject("SPS_TASK.findTaskByType", obj);
        if (object != null)
            return (SpsTaskDto) object;
        else
            return null;
    }


    /**
     * 查询diy字段
     *
     * @param spsTask
     * @return
     */
    public List<Map<String, Object>> findDiyFieldMap(SpsTask spsTask) {
        return (List<Map<String, Object>>) queryForList("SPS_TASK.findSpsTaskDIYFIELD", spsTask);
    }

    /**
     * 查询 年度 服务人数
     *
     * @author lifq
     * <p>
     * 2016年8月26日  上午11:33:18
     */
    public List<Map<String, Object>> queryYearPersonNum(HashMap<String, Object> parameterMap) {
        return queryForList("SPS_TASK.queryYearPersonNum", parameterMap);
    }

    public Integer updateUsbport(ContextInfo cti, String schemeId, String usbport, String type) {
        Map<String, Object> param = new HashMap<>();
        param.put("schemeId", schemeId);
        param.put("usbport", usbport);
        int ret = -1;
        if (type.equals("0")) {
            ret = (Integer) update(cti, "SPS_TASK.updateInsu", param);
        }
        if (type.equals("1")) {
            ret = (Integer) update(cti, "SPS_TASK.updateFund", param);
        }

        return ret;
    }

    /**
     * 通过spID和CPID删除方案
     *
     * @param
     * @return
     */
    public Integer removeBySpIdAndCpId(ContextInfo cti, SpsTask obj) {

        return update(cti, "SPS_TASK.deleteTaskBySpIdAndCpId", obj);
    }

    /**
     * 查询没有完成的任务单
     *
     * @param
     * @return
     */
    public Integer countNoCompletedTask(SpsTask obj) {

        return (Integer) queryForObject("SPS_TASK.countNoCompletedTask", obj);
    }

    /**
     * 获取增减补办理进度数据
     *
     * @return : TaskVo
     * @createDate : 2017年3月6日 上午10:25:05
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 上午10:25:05
     * @updateAuthor :
     */
    public TaskVo findInsuranceFundByMonth(TaskDto obj) {
        Object ret = queryForObject("SPS_TASK.findInsuranceFundByMonth", obj);
        if (ret != null)
            return (TaskVo) ret;
        else
            return null;
    }

    /**
     * 员工参保分布数据
     *
     * @return : List<TaskMapVo>
     * @createDate : 2017年3月6日 上午10:25:09
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 上午10:25:09
     * @updateAuthor :
     */
    public List<TaskMapVo> findInsuranceFundMapByCity(TaskDto obj) {
        return (List<TaskMapVo>) queryForList("SPS_TASK.findInsuranceFundMapByCity", obj);
    }

    /**
     * 增员提醒
     *
     * @param obj
     * @return : List<ItemWarnVo>
     * @createDate : 2017年3月6日 下午3:49:29
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 下午3:49:29
     * @updateAuthor :
     */
    public List<ItemWarnVo> findItemWarnEndDateByCpId(ItemWarnDto obj) {
        return (List<ItemWarnVo>) queryForList("SPS_TASK.findItemWarnEndDateByCpId", obj);
    }

    /**
     * 根据企业ID、
     *
     * @return : List<CmEmployeeInsurance>
     * @createDate : 2017年3月7日 上午10:52:35
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月7日 上午10:52:35
     * @updateAuthor :
     */
    public List<CmEmployeeInsurance> findEmployeeFlowChartByCityId(FlowChartDto obj) {
        return (List<CmEmployeeInsurance>) queryForList("SPS_TASK.findEmployeeFlowChartByCityId", obj);
    }

    /**
     * 员工流动分析列表
     * @param cti
     * @param areaId
     * @return
     */
    public List<Map<String, Object>> findEmployeeFlowChartByCityIdList(ContextInfo cti, Integer areaId, String beginPeriod, int pageIndex, int pageSize){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("authority", cti.getAuthority());
        obj.put("beginPeriod", beginPeriod);
        return (List<Map<String,Object>>)getPaginatedList("SPS_TASK.findEmployeeFlowChartByCityIdList", obj, pageIndex, pageSize);
    }

    public Integer findEmployeeFlowChartByCityIdListCount(ContextInfo cti, Integer areaId, String beginPeriod) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("authority", cti.getAuthority());
        obj.put("beginPeriod", beginPeriod);
        Integer ret = (Integer) queryForObject("SPS_TASK.findEmployeeFlowChartByCityIdListCount", obj);
        return ret.intValue();
    }

    /**
     * 获取社保公积金-进度总数
     *
     * @param vo
     * @return : Integer
     * @createDate : 2017年3月7日 下午5:22:26
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月7日 下午5:22:26
     * @updateAuthor :
     */
    public Map<String, Object> findHandlePersonnelListCount(HandlePersonnelDto vo) {
        Map<String, Object> objectMap = (Map<String, Object>) queryForObject("SPS_TASK.findHandlePersonnelListCount", vo);
        return objectMap;
    }

    /**
     * 获取社保公积金-进度列表
     *
     * @param vo
     * @param pageIndex
     * @param pageSize
     * @return : List<SpsBill>
     * @createDate : 2017年3月7日 下午5:22:10
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月7日 下午5:22:10
     * @updateAuthor :
     */
    public List<HandlePersonnelListVo> findHandlePersonnelList(HandlePersonnelDto vo, int pageIndex, int pageSize) {
        if(pageIndex  == -1 && pageSize == -1){
            return queryForList("SPS_TASK.findHandlePersonnelList", vo);
        }else{
            return getPaginatedList("SPS_TASK.findHandlePersonnelList", vo, pageIndex, pageSize);
        }
    }

    /**
     * 获取其他业务办理状态
     *
     * @param vo
     * @return : List<Map<String,Object>>
     * @createDate : 2017年3月15日 下午3:44:45
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月15日 下午3:44:45
     * @updateAuthor :
     */
    public List<Map<String, Object>> findOtherBusinessStateByMonth(TaskDto vo) {
        return (List<Map<String, Object>>) queryForList("SPS_TASK.findOtherBusinessStateByMonth", vo);
    }

    /**
     * 修改任务单根据taskbotID
     *
     * @param spsTask
     * @return : int
     * @createDate : 2017-03-20 10:34
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017-03-20 10:34
     * @updateAuthor :
     */
    public int updateTaskByOutId(SpsTask spsTask) {
        return update(null, "SPS_TASK.UPDATE_TASK_OUT_ID", spsTask);
    }

    /**
     * 根据条件 获取某个业务类型的 所有的状态
     *
     * @param bsTypeId
     * @param areaId
     * @return
     */
    public List<SpsStateDto> getAllStateBy(Integer bsTypeId, Integer areaId, String orgType) {
        Map obj = new HashMap();
        obj.put("bsTypeId", bsTypeId);
        obj.put("areaId", areaId);
        obj.put("orgType", orgType);
        return queryForList("SPS_TASK.getAllStateBy", obj);
    }

    /**
     * 根据条件获取， 获取某个任务
     *
     * @param spsTask
     * @return
     */
    public SpsTask getSpsTaskByCondition(SpsTask spsTask) {
        Map obj = new HashMap();
        obj.put("empId", spsTask.getEmpId());
        obj.put("bsTypeId", spsTask.getBstypeId());
        obj.put("type", spsTask.getType());
        obj.put("createDt", DateUtil.getDateStr(spsTask.getCreateDt(), "yyyy-MM-hh"));

        Object ret = queryForObject("SPS_TASK.getSpsTaskByCondition", obj);
        if (ret != null)
            return (SpsTask) ret;
        else
            return null;
    }

    /**
     * 社保公积金申报进度
     *
     * @return : TaskVo
     * @createDate : 2017年3月31日 下午2:37:34
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月31日 下午2:37:34
     * @updateAuthor :
     */
    public TaskVo findInsuranceFundProgress(TaskDto obj) {
        Object ret = queryForObject("SPS_TASK.findInsuranceFundProgress", obj);
        if (ret != null)
            return (TaskVo) ret;
        else
            return null;
    }


    /**
     * 根据员工id  获取该员工的该员工所有的任务   并进行排序
     *
     * @param vo
     * @return
     */
    public List<SpsTask> getAllSpsTaskAndSort(Map vo) {
        return queryForList("SPS_TASK.getAllSpsTaskAndSort", vo);
    }


    /**
     * 根据员工id  获取该员工的正在进行的最后两个任务
     *
     * @param vo
     * @return
     */
    public List<SpsTask> getTwoTodoAndSort(Map vo) {
        return queryForList("SPS_TASK.getTwoTodoAndSort", vo);
    }

    public int delTaskByEmpIds(Integer cpId,String empIds){
        return delTaskByEmpIds(cpId,empIds,null);
    }


    public int delTaskByEmpIds(Integer cpId,String empIds,Integer bstypeId){
        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cpId);
        map.put("empIds", empIds);
        map.put("bstypeId", bstypeId);
        return update(null, "SPS_TASK.delTaskByEmpIds", map);
    }

    /**
     * @Title: 根据 员工id  获取 员工新参或变更医院的json
     * @param    empId 员工id
     * @return 
     * @createDate 2017/6/9 17:13
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	: 
     * @updateAuthor  :
    */
    public  String getUseHostptialByEmpId(Integer empId){

        Map<String, Object> map = new HashMap<>();
        map.put("empId", empId);

        Object ret = queryForObject("SPS_TASK.getUseHostptialByEmpId", empId);
        if (ret != null)
            return (String) ret;
        else
            return null;
    }

    public List<Map<String, Object>> findTaskByEmpId(SpsTask vo) {
        return queryForList("SPS_TASK.findTaskByEmpId", vo);
    }


    public void insertByBatch(ContextInfo cti,List<SpsTask> spsTasks){
        insert(cti, "SPS_TASK.insertByBatch",spsTasks);
    }

    public void updateByBatch(ContextInfo cti,SpsTask spsTask){
        update(cti,"SPS_TASK.updateByBatch",spsTask);
    }

    public void updateByBatchByTaskNos(ContextInfo cti,SpsTask spsTask){
        update(cti,"SPS_TASK.updateByBatchByTaskNos",spsTask);
    }

    public List<SpsTask> queryByBatch(ContextInfo cti, String[] taskIds, List<ApplyMessage> loginList,Integer stateFiledId){
        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cti.getOrgId());
        map.put("taskIds", taskIds);
        map.put("loginList",loginList);
        map.put("authority", cti.getAuthority());
        map.put("stateFiledId",stateFiledId);
        return queryForList("SPS_TASK.queryByBatch", map);
    }

    /**
     * 根据月份获取任务数量
     *  @param spsTask
     *  @return 
     *	@return 			: Integer 
     *  @createDate  	: 2017年7月17日 上午11:20:25
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月17日 上午11:20:25
     *  @updateAuthor  :
     */
	public Integer findBusinessNumber(SpsTask spsTask) {
		 Integer ret = (Integer) queryForObject("SPS_TASK.findBusinessNumber", spsTask);
	     return ret.intValue();
	}
	/**
	 * 查询 已完成的调基任务单
	 *
	 * @author lifq
	 *
	 * 2017年8月14日  下午4:41:46
	 */
	public List<SpsTask> queryCompletedAdjustTask(SpsTask vo){
		return queryForList("SPS_TASK.queryCompletedAdjustTask", vo);
	}

	public List<SpsTask> findByEmpId(SpsTask vo){
	    return queryForList("SPS_TASK.findByEmpId", vo);
    }

    public void repairTaskReport(SpsTask vo){
	    insert(null,"SPS_TASK.repairTaskReport",vo);
    }

    public List<Map<String,Object>> queryTaskTrend(ContextInfo cti,Integer areaId,List<String> months){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("months",months);
        obj.put("authority", cti.getAuthority());
        return queryForList("SPS_TASK.QUERY_TASK_TREND",obj);
    }

    /**
     * 任务单分析列表
     * @param cti
     * @param areaId
     * @return
     */
    public List<Map<String,Object>> queryTaskTrendList(ContextInfo cti,Integer areaId, int pageIndex, int pageSize){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("authority", cti.getAuthority());
        return getPaginatedList("SPS_TASK.QUERY_TASK_TREND_LIST",obj, pageIndex, pageSize);
    }

    public Integer queryTaskTrendListCount(ContextInfo cti,Integer areaId) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("authority", cti.getAuthority());
        return (Integer) queryForObject("SPS_TASK.QUERY_TASK_TREND_LIST_COUNT", obj);
    }

    /**
     * 获取任务中心数据列表总数
     *  @param vo
     *  @return 
     *	@return 			: Integer 
     *  @createDate  	: 2017年9月19日 下午6:08:50
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月19日 下午6:08:50
     *  @updateAuthor  :
     */
	public Integer findTaskCenterListCount(TaskCenterDto vo) {
		 return (Integer) queryForObject("SPS_TASK.findTaskCenterListCount", vo);
	}

	/**
	 * 获取任务中心数据列表
	 *  @param vo
	 *  @param pageIndex
	 *  @param pageSize
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月19日 下午6:08:54
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月19日 下午6:08:54
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findTaskCenterList(TaskCenterDto vo, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TASK.findTaskCenterList", vo, pageIndex, pageSize);
	}

	/**
	 * 参保城市
	 *  @param orgId
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:37:31
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:37:31
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionAreaData(ContextInfo cti, Integer type) {
		Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("type",type);
        obj.put("authority", cti.getAuthority());
        return queryForList("SPS_TASK.findQueryConditionAreaData",obj);
	}

	/**
	 * 保险类型
	 *  @param orgId
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:37:58
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:37:58
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionInsFundTypeData(ContextInfo cti, Integer type) {
		Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("type",type);
        obj.put("authority", cti.getAuthority());
        return queryForList("SPS_TASK.findQueryConditionInsFundTypeData",obj);
	}

	/**
	 * 业务类型
	 *  @param orgId
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:38:14
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:38:14
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionTaskTypeData(ContextInfo cti, Integer type) {
		Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("type",type);
        obj.put("authority", cti.getAuthority());
        return queryForList("SPS_TASK.findQueryConditionTaskTypeData",obj);
	}

	/**
	 * 业务状态
	 *  @param orgId
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:38:24
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:38:24
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionTaskStatusData(ContextInfo cti, Integer type) {
		Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("type",type);
        obj.put("authority", cti.getAuthority());
        return queryForList("SPS_TASK.findQueryConditionTaskStatusData",obj);
	}

}
