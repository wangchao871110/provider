package com.xfs.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfs.acc.dto.ApplyMessage;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.EmplInsuranceDto;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.sp.dto.SpsTaskDto;
import com.xfs.task.dto.EmpDetailVo;
import com.xfs.task.dto.FlowChartDto;
import com.xfs.task.dto.FlowChartVo;
import com.xfs.task.dto.HandlePersonnelDto;
import com.xfs.task.dto.ItemWarnDto;
import com.xfs.task.dto.ItemWarnVo;
import com.xfs.task.dto.OtherBusinessVo;
import com.xfs.task.dto.TaskCenterDto;
import com.xfs.task.dto.TaskDto;
import com.xfs.task.dto.TaskMapVo;
import com.xfs.task.dto.TaskVo;

/**
 * SpsTaskService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/08 11:21:48
 */
public interface SpsTaskService {

    public int save(ContextInfo cti, SpsTask vo);

    public int insert(ContextInfo cti, SpsTask vo);

    public int remove(ContextInfo cti, SpsTask vo);

    public int update(ContextInfo cti, SpsTask vo);

    public PageModel findPage(PageInfo pi, SpsTask vo, ContextInfo cti);

    public List<SpsTask> findAll(SpsTask vo);
    public List<SpsTask> findAll(SpsTask vo, String orderByColName);

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/08 11:21:48

    /**
     * findByPK
     *
     * @param vo
     * @return
     */
    public SpsTask findByPK(SpsTask vo);

    /**
     * 根据服务商Id查询  企业下 任务单 个数
     *
     * @author lifq
     * <p>
     * 2016年4月8日  下午1:55:34
     */
    public List<Map<String, Object>> findTaskCount(Integer serviceId);

    /**
     * 导入 更新手机号 excel
     *
     * @author lifq
     *
     * 2016年4月9日  下午2:26:05
     */
//	public Result importUpdatePhoneExcel(Integer fileId,Integer empId,SysUser curUser) throws BusinessException;

    /**
     * 保存 fastlist 传入的数据
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午3:50:38
     */
    public void saveTaskFromFastList(ContextInfo cti, Integer userId, Integer empId, Integer spId, Integer bstypeId, String json) throws BusinessException;

    /**
     * 查询企业列表
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:33:26
     */
    public List<Map<String, Object>> findEmpList(String type, Integer spId, Integer bstypeId) throws BusinessException;

    /**
     * 查询所有的企业列表
     *
     * @param type
     * @param spId
     * @return
     * @throws BusinessException
     */
    public List<Map<String, Object>> findOrgList(String type, Integer spId) throws BusinessException;

    /**
     * 查询 企业下 任务单信息
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskList(Integer empId, String type, Integer spId, Integer bstypeId) throws BusinessException;

    /**
     * 查询 企业下 任务单json 字段
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskJsonList(Integer empId, String type, Integer spId, Integer bstypeId) throws BusinessException;

    /**
     * 查询 企业下 任务单 列表 （分页）
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午9:25:12
     */
    public PageModel findEmpPersonPage(PageInfo pi, Integer empId, String type, Integer spId, Integer bstypeId);

    /**
     * 更新 任务表 人员信息
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午10:21:53
     */
    public void updatePsnInfo(ContextInfo cti, String type, Integer imgId, Integer taskId, String mark) throws BusinessException;

    /**
     * 查询 企业下 列表
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午9:25:12
     */
    public PageModel findEmpPage(PageInfo pi, Integer spId);

    /**
     * 批量更新 任务状态
     *
     * @author lifq
     * <p>
     * 2016年4月11日  下午5:09:06
     */
    public void updateTaskStatus(ContextInfo cti, String selectedValue, String type, Integer userId) throws BusinessException;

    /**
     * 将库中的数据导出成excel
     *
     * @param spId
     * @return
     * @throws BusinessException
     */
    public void downLoadExcel(Integer spId, HttpServletRequest request, HttpServletResponse response) throws BusinessException, Exception;

    /**
     * 通用 导入excel
     *
     * @author wuzhe
     * <p>
     * 2016年10月12日
     */
    public Result importExcel(ContextInfo info, Integer fileId, Integer empId, Integer spId, String selectContent, Integer bstypeId, String sheetName, Integer areaId, Integer curSchemeId, String month, Result result) throws Exception;

    /**
     * 读取excel 关系
     *
     * @author lifq
     * <p>
     * 2016年5月24日  上午9:32:12
     */
    public Result readTemplateRealtion(Integer curBsTypeId, Integer fileId, String sheetName, Integer areaId) throws Exception;

    /**
     * 读取excel 标题
     *
     * @author lifq
     * <p>
     * 2016年5月24日  上午9:36:12
     */
    public Result readTemplateTitle(Integer curBsTypeId, Integer fileId, Integer areaId) throws Exception;

    /**
     * 根据服务商id和区域id获取企业列表、任务汇总信息
     *
     * @param vo
     * @return
     */
    PageModel findTaskAndCorps(PageInfo pi, SpsTask vo, String searchWord, ContextInfo user);

    /**
     * 条件搜索任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    PageModel findTasksByCondition(PageInfo pi, BdBstype bstype, SpsTask vo, String searchWord);

    /**
     * 条件搜索所有任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    List<Map<String, Object>> findALLTasksByCondition(BdBstype bstype, SpsTask vo, String searchWord);

    /**
     * 查询某个公司下面，公积金或者社保的情况
     *
     * @param cid
     * @param type
     * @return
     */
    public HashMap<String, Object> findProgressData(Integer cid, String type);

    /**
     * 开始某个公司的业务
     *
     * @param scheme_id
     * @param type
     */
    public void startTask(ContextInfo cti, Integer scheme_id, String type, String mobile, Integer spid);


    /**
     * 停止公司的业务
     *
     * @param scheme_id
     * @param type
     */
    public void stopTask(ContextInfo cti, Integer scheme_id, String type, String mobile, Integer spid);

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单号 查询任务单详情
     * zhangxiyan
     */
    public HashMap<String, Object> findDetailByTaskId(SpsTask spsTask);

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 执行完成任务单按钮操作
     * zhangxiyan
     */
    public Result updatetoComplete(ContextInfo cti, SpsTask spsTask, SpsTaskHistory spsTaskHistory);

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 执行终止任务单按钮操作
     * zhangxiyan
     */
    public Result updatetoColsed(ContextInfo cti, SpsTask spsTask, SpsTaskHistory spsTaskHistory);

    /**
     * 2016-12 新需求 实作管理
     * 批量执行实作单操作
     * zhangxiyan
     */
    public Result upDealPl(ContextInfo cti, String reduceJson, String flag);

    PageModel freeFindAllListByEmp(PageInfo pi, SpsTask spsTask);

    Map<String, Object> findSpsTaskByEmp(SpsTask spsTask, String insuranceFondType);

    public void downLoadTemplet(Integer areaId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public List<SpsTask> findTaskByBsTypeAndState(Integer empId, Integer[] bstypeId, String state, String[] states);

    /**
     * EL表达式
     *
     * @param vo
     * @return
     * @throws BusinessException
     */
    public SpsTaskDto findTaskByType(SpsTask vo) throws BusinessException;


    /**
     * 保存任务单
     *
     * @param spsTask
     * @param businessParams
     * @param result
     * @throws BusinessException
     */
    public void savaTask(ContextInfo cti, SpsTask spsTask, Map<String, Object> businessParams, Result result) throws BusinessException;

    /**
     * 2016-08
     * 任务管理详情
     * 任务单历史记录增加
     */
    public Result addSpsTaskHistory(ContextInfo cti, SpsTask spsTask);


    public List<Map<String, Object>> findSpsTaskListByEmp(SpsTask spsTask, String insuranceFondType);

    /**
     * 条件搜索任务列表
     *
     * @param vo
     * @param searchWord
     * @return
     */
    public PageModel findTasksByCondition(PageInfo pi, SpsTask vo, String searchWord) throws BusinessException;

    /**
     * 社保公积金保险情况
     *
     * @param cti
     * @param empId
     * @return
     */
    public List<EmplInsuranceDto> getInsuranceInfo(ContextInfo cti, Integer empId);


    /**
     * 查询 年度 服务人数
     *
     * @author lifq
     * <p>
     * 2016年8月26日  上午11:33:18
     */
    public List<Map<String, Object>> queryYearPersonNum(HashMap<String, Object> parameterMap);

    /**
     * 查询总数
     *
     * @param spsTask
     * @return
     */
    public Integer freeFindCount(SpsTask spsTask);

    /**
     * 更改用户企业的USBport
     *
     * @param schemeId
     * @return
     */
    public Integer updateUsbport(ContextInfo cti, String schemeId, String usbport, String type);

    /**
     * 获取增减补办理进度数据
     *
     * @param param
     * @return : TaskVo
     * @createDate : 2017年3月6日 上午10:15:45
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 上午10:15:45
     * @updateAuthor :
     */
    public TaskVo findInsuranceFundByMonth(ContextInfo cti,TaskDto param);

    /**
     * 员工参保分布数据
     *
     * @param param
     * @return : List<TaskMapVo>
     * @createDate : 2017年3月6日 上午10:22:34
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 上午10:22:34
     * @updateAuthor :
     */
    public List<TaskMapVo> findInsuranceFundMapByCity(TaskDto param);

    /**
     * 增员事项
     *
     * @param param
     * @return : List<ItemWarnEndDateVo>
     * @createDate : 2017年3月6日 下午3:47:38
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月6日 下午3:47:38
     * @updateAuthor :
     */
    public List<ItemWarnVo> findItemWarnByCpId(ContextInfo cti,ItemWarnDto param);

    /**
     * 员工流动趋势图数据
     *
     * @param param
     * @return : FlowChartVo
     * @createDate : 2017年3月7日 上午10:50:14
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月7日 上午10:50:14
     * @updateAuthor :
     */
    public List<FlowChartVo> findEmployeeFlowChartByCityId(FlowChartDto param, String year);

    /**
     * 员工流动分析列表
     * @param cti
     * @param areaId
     * @return
     */
    public PageModel findEmployeeFlowChartByCityIdList(ContextInfo cti,PageInfo pi, Integer areaId, String beginPeriod);

    /**
     * 获取社保公积金-进度
     *
     * @param pageInfo
     * @param param
     * @return : PageModel
     * @createDate : 2017年3月7日 下午5:18:30
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月7日 下午5:18:30
     * @updateAuthor :
     */
    public PageModel findHandlePersonnelList(PageInfo pageInfo, HandlePersonnelDto param);

    /**
     * 获取其他业务办理状态
     *
     * @param param
     * @return : List<OtherBusinessVo>
     * @createDate : 2017年3月15日 下午2:50:15
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月15日 下午2:50:15
     * @updateAuthor :
     */
    public List<OtherBusinessVo> findOtherBusinessStateByMonth(TaskDto param);


    public int updateTaskByOutId(SpsTask spsTask);

    /**
     * 根据 业务类型， 时间， 查看 该月份是否 发生过该类型 业务变更
     *
     * @param spsTask
     * @return
     */
    public SpsTask getSpsTaskByCondition(SpsTask spsTask);

    /**
     * 社保公积金申报进度
     *  @param param
     *  @return 
     *	@return 			: TaskVo 
     *  @createDate  	: 2017年3月31日 下午2:36:10
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年3月31日 下午2:36:10
     *  @updateAuthor  :
     */
	public TaskVo findInsuranceFundProgress(TaskDto param);

    /**
     * 根据条件 获取某个业务类型的 所有的状态
     *
     * @param bsTypeId
     * @param areaId
     * @return
     */
    public List getAllStateBy(Integer bsTypeId, Integer areaId, String orgType);


    public int delTaskByEmpIds(Integer cpId,String empIds);

    public int delTaskByEmpIds(Integer cpId,String empIds,Integer bstypeId);

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
    public  String getUseHostptialByEmpId(Integer empId);

    public List<Map<String, Object>> findTaskByEmpId(SpsTask vo);

    public void insertByBatch(ContextInfo cti,List<SpsTask> spsTasks);

    public void updateByBatch(ContextInfo cti,SpsTask spsTask);

    public void updateByBatchByTaskNos(ContextInfo cti,SpsTask spsTask);

    public List<SpsTask> queryByBatch(ContextInfo cti,String[] taskIds, List<ApplyMessage> loginList,Integer stateFiledId);

    /**
     * 根据月份获取任务数量
     *  @param spsTask
     *  @return 
     *	@return 			: Integer 
     *  @createDate  	: 2017年7月17日 上午11:10:51
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月17日 上午11:10:51
     *  @updateAuthor  :
     */
	public Integer findBusinessNumber(SpsTask spsTask);
	
	/**
	 * 查询 已完成的调基任务单
	 *
	 * @author lifq
	 *
	 * 2017年8月14日  下午4:41:46
	 */
	public List<SpsTask> queryCompletedAdjustTask(SpsTask vo);


    /**
     * 补全任务单历史纪录
     * @param vo
     */
	public void repairTaskReport(SpsTask vo);

    public List<SpsTask> findByEmpId(Integer empId, Integer bstypeId, String type);

    /**
     * 任务单趋势图
     * @param cti
     * @param areaId
     * @param months
     * @return
     */
    public List<Map<String,Object>> queryTaskTrend(ContextInfo cti,Integer areaId,List<String> months);

    /**
     * 任务单分析列表
     * @param cti
     * @param areaId
     * @return
     */
    public PageModel queryTaskTrendList(ContextInfo cti,PageInfo pi, Integer areaId);

    /**
     * 获取任务中心数据
     *  @param pageInfo
     *  @param param
     *  @return 
     *	@return 			: PageModel 
     *  @createDate  	: 2017年9月19日 下午5:44:28
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月19日 下午5:44:28
     *  @updateAuthor  :
     */
	public PageModel findTaskCenterList(ContextInfo cti,PageInfo pageInfo, TaskCenterDto param);

	/**
	 * 获取任务数量
	 *  @param param
	 *  @return 
	 *	@return 			: Map<String,Object> 
	 *  @createDate  	: 2017年9月20日 下午2:10:41
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月20日 下午2:10:41
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findTaskNumber(ContextInfo cti,TaskCenterDto param);

	/**
	 * 任务中心--获取人员详细信息
	 *  @param cti
	 *  @param taskId
	 *  @return 
	 *	@return 			: EmpDetailVo 
	 *  @createDate  	: 2017年9月20日 下午3:31:35
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月20日 下午3:31:35
	 *  @updateAuthor  :
	 */
	public EmpDetailVo findEmpDetail(ContextInfo cti, Integer taskId);

	/**
	 * 参保城市
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:31:56
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:31:56
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionAreaData(ContextInfo cti, Integer type);

	/**
	 * 保险类型
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:32:15
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:32:15
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionInsFundTypeData(ContextInfo cti, Integer type);

	/**
	 * 业务类型
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:32:27
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:32:27
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionTaskTypeData(ContextInfo cti, Integer type);

	/**
	 * 业务状态
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年9月29日 下午2:32:38
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:32:38
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findQueryConditionTaskStatusData(ContextInfo cti, Integer type);

	/**
	 * 菜单是否显示新任务提醒
	 *  @param cti
	 *  @return 
	 *	@return 			: boolean 
	 *  @createDate  	: 2017年11月2日 上午11:45:41
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月2日 上午11:45:41
	 *  @updateAuthor  :
	 */
	public boolean findNewTaskremind(ContextInfo cti);

    /**
     * 查找员工 按多类型查找 按创建时间倒序
     *
     * @param bsType    任务类型
     * @param type      任务状态
     * @return
     */
    public List<SpsTask> findTaskInBsType(Integer empId, String bsType, String type, String[] typeIn);

}
