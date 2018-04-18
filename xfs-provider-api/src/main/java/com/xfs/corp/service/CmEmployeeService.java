package com.xfs.corp.service;

import java.util.List;
import java.util.Map;

import com.xfs.analysis.model.AnalysisData;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.dto.BatchAdjBaseQuery;
import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.BatchReduceEmplQuery;
import com.xfs.corp.dto.CSCmEmployeeDto;
import com.xfs.corp.dto.DynamicFields;
import com.xfs.corp.dto.EmployeeDto;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeOrg;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsScheme;

import net.sf.json.JSONObject;


/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-10 14:10:00
 * @version 	: V1.0
 */
public interface CmEmployeeService {

    public int save(ContextInfo cti, CmEmployee vo);

    public int insert(ContextInfo cti, CmEmployee vo);

    public int remove(ContextInfo cti, CmEmployee vo);

    public int update(ContextInfo cti, CmEmployee vo);

    public PageModel findPage(PageInfo pi, CmEmployee vo);


    public PageModel findEmployeeList(PageInfo pi, CmEmployee vo, String schemeType, String keyWord, Integer spId,ContextInfo cti);

    public List<CmEmployee> findAll(CmEmployee vo);

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/04/08 15:03:51
    public Map<String, Object> findOneByEmpId(CmEmployee vo);

    public Result saveEmployee(ContextInfo cti,CmEmployee vo);

    public Result importUpdateEmpExcel(ContextInfo cti,Integer fileId, Integer cpId, String selectContent)
            throws BusinessException;

    public Result readExcelTitle(Integer fileId,ContextInfo cti) throws Exception;

    /**
     * 根据openId和手机号查询
     *
     * @param vo
     * @return
     * @auth konglc
     */
    public CmEmployee findPersonByOpenIdOrMobile(CmEmployee vo, Integer sp_id);

    /**
     * 获取企业人员社保费用类型 社保代扣代缴/公积金代扣代缴/服务费用
     *
     * @param spid
     * @param cmid
     * @return
     */
    public Map<String, Object> sumCmEmployeeFee(Integer spid, Integer cmid);

    /**
     * 获取企业人员正常缴纳列表
     *
     * @param spid
     * @param cmid
     * @return
     */
    public List<CmEmployee> queryCmEmployeeFeeList(Integer spid, Integer cmid);

    /**
     * 更新 任务单 状态后 处理企业员工信息
     *
     * @author lifq
     *
     *         2016年5月30日 下午3:04:56
     */
    public void dealEmpAfterTask(ContextInfo cti,SpsTask task) throws BusinessException;

    /**
     * 企业有变动 更新员工 cpId 企业ID sys session里 当前登录人信息
     */
//    public int updateEmployee(Integer cpId, SysUser sys);


    /**
     * 定时任务，每月一号凌晨，初始化当月现有员工费用
     */
    public void initCalcFeeMonthly();

    /**
     * 根据库id获取所有库下的员工
     *
     * @param vo
     * @return
     */
    public List<CmEmployee> findEmployeeByAccount(SpsAccount vo);

    /**
     * 获取批量导入文件信息
     * @param cpid
     * @return
     */
    public Result readImportFileInfo(Integer cpid,ContextInfo cti) throws Exception;

    /**
     * 读取导入表映射关系
     * @param fileId
     * @return
     * @throws Exception
     */
    public Result readTemplateRealtion(Integer fileId, String sheetName, String corpExist) throws Exception;

    /**
     * 批量导入员工
     * @param fileId
     * @param cpId
     * @param
     * @param selectContent
     * @param sheetName
     * @param corpExist
     * @return
     * @throws BusinessException
     */
    public Result importEmpExcel(ContextInfo cti,Integer fileId, Integer cpId, String cpName, String selectContent, String sheetName, String corpExist)
            throws BusinessException;

    /**
     * 修改员工
     * @param cmEmployee
     * @param
     * @return
     */
    public Result updateEmployee(ContextInfo cti,CmEmployee cmEmployee);

    boolean addEmployeeAndScheme(ContextInfo cti,CmEmployee employee);
    public CmEmployee findByPK(CmEmployee vo);
    public CmEmployee findByPk(Integer empId);

    public CmEmployee findEmpAndJsonByPK(CmEmployee vo);
    public CmEmployee findEmpAndJsonByPK(Integer empId);


    boolean reductionEmployee(ContextInfo cti,CmEmployee employee, String insuranceReductionDate, String fundReductionDate);

    Map<String,Object> FindEmployeeBasicInfo(Integer empId, Integer spId);

    Map<String,Object> findEmployeeInsuranceList(CmEmployee cmEmployee);

    Map<String,Object> findEmployeeInsureInfo(Integer empId);

    public Integer findEmpCount(CmEmployee vo, Integer spId,ContextInfo cti);
    
    /**
     * 根据方案id  查询 员工信息
     *
     * @author lifq
     *
     * 2016年8月9日  下午5:06:17
     */
    public List<Map<String,Object>> findEmpBySchemeId(Integer scheme_id, Integer cp_id);

    public Map<String, Object> findEmployeeInfoByPK(Integer empId);


    Result updateEmInsuranceList(ContextInfo cti,JSONObject paraObj);
    boolean updateEmpInsurance(ContextInfo cti,JSONObject paraObj);


	List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds);


    public CmEmployee queryEmployee(Integer spId, Integer cpId, Integer empId);

    void saveNewInsurance(ContextInfo cti,JSONObject paraObj);

    public Map<String,CmEmployee> queryAllEmpBySpId(Integer spId);
    
    /**
     * 查询 社保、公积金 人数
     *
     * @author lifq
     *
     * 2016年8月16日  下午9:37:53
     */
    public List<Map<String,Object>> queryInsurNumBySpId(Map<String, Object> map);



    public boolean reductionEmployee(ContextInfo cti,List<SpsTask> spsTasks, CmEmployee employee, Result result);
    /**
     * 根据方案id 查询员工实体
     *
     * @author lifq
     *
     * 2016年8月24日  下午4:35:57
     */
    public List<CmEmployee> findEmpEntityBySchemeId(Integer schemeId);

	List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period);

    boolean supplementInsurance(ContextInfo cti,List<SpsTask> spsTasks, CmEmployee employee, Result result);

    /**
     * 4种任务单保存
     * @param tasks
     * @param businessParam
     * @param result
     * @return
     */
    public Result handlerTask(ContextInfo cti,List<SpsTask> tasks, Map businessParam, Result result);

    public boolean recoverEmp(ContextInfo cti,CmEmployee cmEmployee);

	List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period, String insuranceOrFund);

    List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period, String insuranceOrFund,String payType);

    public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType, String beginDate);

    public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType, String beginDate,String isDefault);

    /**
     *  获取该账号的的所有险种信息
     *  @param
     * @return    :
     *  @createDate   : 2017/1/19 11:05
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/19 11:05
     *  @updateAuthor  :
     */
    public List<InsuranceTypeDto> queryAccountInsurancList(Integer accountId, String livingType, String beginDate);

    /**
     * 根据社保公积金库查找库比例
     * @param accountId
     * @param livingType
     * @return
     */
    public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType, Integer insuranceId, String beginDate);
    /**
     * 根据社保公积金库查找库比例（不按照期间）
     * @param accountId
     * @param livingType
     * @return
     */
    public List<InsuranceTypeDto> queryAccountInsuranceList(Integer accountId, String livingType, Integer insuranceId);


    /**
     *  根据方案Id获取各种险种信息
     *  @param
     * @return    :
     *  @createDate   : 2017/1/17 17:50
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/17 17:50
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> queryTotalInsuranceListBySchemceId(Integer accountId,String livingType,Integer schemceId);
    /**
     * 2016-11-1 版本导入员工
     * @param fileId
     * @param
     * @param selectContent
     * @return
     */
    public Result importEmpExcel(ContextInfo cti,Integer fileId,String selectContent) throws BusinessException;

    /**
     * <p>Title: bs询社保在缴公积金在缴总人数</p>
     * <p>Description: bs询社保在缴公积金在缴总人数</p>
     * ${tags}
     */
    public int countFindAllEmployee();/**
     * <p>Title: bs按创建日期查询社保在缴公积金在缴总人数</p>
     * <p>Description: bs按创建日期查询社保在缴公积金在缴总人数</p>
     * ${tags}
     */
    public List<Map<String, Object>> countByDayEmployee(String stringDate);
    /**
     * <p>Title: cs单表删除员工</p>
     * <p>Description: cs单表删除员工</p>
     * ${tags}
     */
    public int removeToCs(ContextInfo cti,CmEmployee vo);

    /**
     * <p>Title: cs分页查询员工</p>
     * <p>Description: cs分页查询员工</p>
     * ${tags}
     */
    public PageModel findPageToCs(PageInfo pi, CSCmEmployeeDto vo);/**
     * <p>Title: cs准备导入excel</p>
     * <p>Description: cs准备导入excel</p>
     * ${tags}
     */
    public Result readImportFileInfoToCs(Integer fileId, ContextInfo cti)  throws Exception;

    /**
     * <p>Title: cs导入excel第二部</p>
     * <p>Description: cs导入excel第二部</p>
     * ${tags}
     */
    public Result readTemplateRealtionToCs(Integer fileId,String sheetName,String corpExist) throws Exception;
    /**
     * <p>Title: cs读取excel列表</p>
     * <p>Description: cs读取excel列表</p>
     * ${tags}
     */
    public Map<String, Object> readExcelTitleToCs(String filePath,ContextInfo cti) throws Exception;
    /**
     * <p>Title: cs导入员工</p>
     * <p>Description: cs导入员工</p>
     * ${tags}
     */
    public Result importEmpExcel(ContextInfo cti,Integer fileId)
            throws BusinessException;
    /**
     * <p>Title: cs新增员工</p>
     * <p>Description: cs新增员工</p>
     * ${tags}
     */
    public Result saveEmployeeToCs(ContextInfo cti,CmEmployee cmEmployee);

    /**
     * <p>Title: cs更新员工</p>
     * <p>Description: cs更新员工</p>
     * ${tags}
     */
    public Result updateEmployeeToCs(ContextInfo cti,CmEmployee cmEmployee);

    /**
     * <p>Title: 根据ID获取员工信息</p>
     * <p>Description: 根据ID获取员工信息</p>
     * ${tags}
     */
    public CmEmployee queryEmployeeById(CmEmployee vo);


    /**
     * <p>Title: cs新增员工</p>
     * <p>Description: cs新增员工</p>
     * ${tags}
     */
    public Result add(ContextInfo cti, CmEmployee employee);

    /**
     * <p>Title: cs查询人员业务类</p>
     * <p>Description: cs查询人员业务类</p>
     * ${tags}
     */
    public CSCmEmployeeDto findEmplInfo(ContextInfo cti, Integer empId);


    /**
     * <p>Title: cs查询子机构id集合 最多从传入id向下查10级</p>
     * <p>Description: cs查询子机构id集合 最多从传入id向下查10级</p>
     * ${tags}
     */
    public PageModel findPage(PageInfo pi, ContextInfo cti, CSCmEmployeeDto vo);

    /**
     * <p>Title: cs更改员工部门</p>
     * <p>Description: cs更改员工部门</p>
     * ${tags}
     */
    public Result updateEmplDepId(ContextInfo cti,String empIds,Integer depId);

    /**
     * <p>Title: cs更新员工头像</p>
     * <p>Description: cs更新员工头像</p>
     * ${tags}
     */
    public Result updateEmplPhoto(ContextInfo cti,CmEmployee cmEmployee);

    /**
     * 通过id查询员工并补全livingTypeName
     * @param vo
     * @return
     */
    public CmEmployee findVoByPK(CmEmployee vo);


    /**
     * 查询方案下类型险种
     * @param schemeId
     * @param insuFundType
     * @return
     */
    public List<InsuranceTypeDto> queryInsuTypeList(Integer schemeId, String insuFundType, String insuranceLivingType);

    /**
     * 根据方案id 查询员工实体 供cs使用
     *
     * @author wangxs
     *
     * 2016年8月24日  下午4:35:57
     */
    public boolean supplementInsuranceToCs(ContextInfo cti,List<SpsTask> spsTasks, CmEmployee employee, SpsScheme spsScheme,Result result);


    /**
     * 任务单保存 4种 cs用
     * @param tasks
     * @param businessParam
     * @param result
     * @return
     */
    public Result handlerTaskToCs(ContextInfo cti,List<SpsTask> tasks,Map businessParam,Result result);

    /**
     * 分页查询批量修改比例员工
     *  @param   ratioEmpQuery 查询条件
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 15:18 2016/11/16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 15:18 2016/11/16
     *  @updateAuthor  :
     */
    public PageModel findBatchRatioEmpPage(ContextInfo cti,PageInfo info,BatchRatioEmpQuery ratioEmpQuery);

    /**
     * 查询满足比例更换条件员工
     *  @param   ratioEmpQuery 查询条件
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 16:15 2016/11/16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16:15 2016/11/16
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findBatchRatioEmp(BatchRatioEmpQuery ratioEmpQuery);
   /**
    *  员工信息 >缴费详情 >服务费
    *  @param employee
    *  @return 
    *	@return 			: List<Map<String,Object>> 
    *  @createDate  	: 2016年11月28日 下午3:28:21
    *  @author         	: xiyanzhang 
    *  @version        	: v1.0
    *  @updateDate    	: 2016年11月28日 下午3:28:21
    *  @updateAuthor  :
    */
    public Map<String,Object> findEmployeeServiceChargeMap(CmEmployee employee);


    /**
     * 分页查询批量减员
     *  @param   info, query]
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2016/12/15 15:32
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/15 15:32
     *  @updateAuthor  :
     */
    public PageModel findBatchReduceEmpPage(PageInfo info,ContextInfo cti, BatchReduceEmplQuery query);

    /**
     * 查询员工所属方案最后受理日期
     *  @param   empIds 员工id
     * @return    : java.lang.Integer
     *  @createDate   : 2016/12/16 11:59
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/16 11:59
     *  @updateAuthor  :
     */
    public Integer findEmplSchemeMaxEndDate(List<Integer> empIds);
    /**
     * 批量基数调整exportData
     *  @param   query
     * @return    : java.util.Map<java.lang.String,java.lang.Object>
     *  @createDate   : 2016/12/26 19:10
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/26 19:10
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findEmplsByBatchAdjBaseExportData(ContextInfo cti,BatchAdjBaseQuery query);

    /**
     *  获取可批量调整最低基数 员工列表
     *  @param   info, query
     *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate  	: 2017-01-09 15:52
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-01-09 15:52
     *  @updateAuthor  :
     */
    public PageModel findEmplsByBatchAdjLowerBaseList(ContextInfo cti,PageInfo info, BatchAdjBaseQuery query);

    /**
     * 通过企业id 证件号码与姓名 查询员工
     *  @param   query
     * @return    : com.xfs.corp.model.CmEmployee
     *  @createDate   : 2016/12/27 15:11
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/27 15:11
     *  @updateAuthor  :
     */
    public CmEmployee findCpEmplByNameAndIdentityCode(CmEmployee query);

    /**
     * 批量导入员工列表(bang-saas)
     * @param cti
     * @param employeeDtos
     * @return
     */
    public Result batchSaveEmployee(ContextInfo cti, List<EmployeeDto> employeeDtos,Integer sendPageSpId,Integer receivePageSpId);
    /**
     * 获取员工的姓名与身份证号列表
     * @param cmEmployee
     * @return
     */
    public List<CmEmployee> findEmpNameAndIdentitycodeList(CmEmployee cmEmployee);


    public List<InsuranceTypeDto> queryAccountInsuranceSingleByAccountId(Integer accountId, String livingType,
                                                                   String beginDate, Integer InsuranceTypeId);

    public List<CmEmployee> findEmpNameList(ContextInfo cti,CmEmployee cmEmployee);

    public Map<String, Object> findEmpTotal(Map<String, Object> map);

    public List<Map<String, Object>> findEmpTotalArea(Map<String, Object> map);

    /**
     * 参保人员列表（带分页）
     * @param cmEmployee
     * @return
     */
    public PageModel findEmpList(PageInfo pi, CmEmployee cmEmployee);

    /**
     * 成本统计分析列表
     * @param pi
     * @param cmEmployee
     * @return
     */
    public PageModel paymentTrendList(PageInfo pi, CmEmployee cmEmployee);

    /**
     * 参保人员分析列表
     * @param pi
     * @param cmEmployee
     * @return
     */
    public PageModel empTrendList(PageInfo pi, CmEmployee cmEmployee);

    /**
     * 参保人员列表（导出用）
     * @param cmEmployee
     * @return
     */
    public List<Map<String,Object>> findEmpList(CmEmployee cmEmployee);

    public Map<String, Object> findEmpInfo(CmEmployee cmEmployee);

    public Result handleUploadEmp(ContextInfo cti,List<Map<String,AnalysisData>> dataList);

    public Result handleUploadEmp(ContextInfo cti,List<Map<String,AnalysisData>> dataList,TaskStateFiled taskStateFiled,TaskExecuteType taskExecuteType);

    /**
     * 下载社保数据
     *  @param cti
     *	@return 			: String
     *  @createDate  	: 2017年4月13日 下午2:51:05
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月13日 下午2:51:05
     *  @updateAuthor  :
     */
	public String downloadInsFundDatas(ContextInfo cti, String period,String searchWords,String areaId);

	/**
	 * 友人才用户同步
	 *  @param empId
	 *  @param empJobId
	 *  @param tenantId
	 *  @param businessType
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年5月16日 下午5:16:44
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月16日 下午5:16:44
	 *  @updateAuthor  :
	 */
	public Result joinEmpYrc(String empId, String empJobId, String tenantId, Integer businessType);

	public Result insertEmp(ContextInfo cti, CmEmployee cmEmployee, Result result, boolean isInsert,Map<String,Object> data) throws BusinessException;

    public Integer deleteEmps(ContextInfo cti,Integer cpId, String empIds);

    /**
     * 修改员工参保信息-缴费详情
     * @param cti
     * @param cmEmployee
     * @param spsScheme
     * @param insType
     * @param result
     */
    public void saveEmpInfo(ContextInfo cti, CmEmployee cmEmployee, SpsScheme spsScheme, String insType, Result result);

    /**
     * 修改员工参保信息-缴费详情
     * @param cti
     * @param cmEmployee
     * @param result
     * @param defBeginDate
     */
    public void saveEmpInfo(ContextInfo cti, CmEmployee cmEmployee, Result result, String defBeginDate);

    /**
     * 查询 调基人员列表
     *
     * @author lifq
     *
     * 2017年6月7日  上午10:36:29
     */
    public PageModel adjustList(PageInfo pi, CmEmployee cmEmployee);

    /**
     * 导出 调基人员列表
     *
     * @author lifq
     *
     * 2017年6月7日  上午10:36:29
     */
    public List<Map<String,Object>> exportAdjust(ContextInfo cti,String empids);

    /**
     * 保存任务数据
     * @param cti
     * @param cmEmployee
     *
     * @author quanjiahua@xinfushe.com
     *
     * @return
     */
    public int saveEmpJson(ContextInfo cti, CmEmployee cmEmployee);
    
    /**
     * 公积金调基列表
     *
     * @author lifq
     *
     * 2017年6月29日  上午11:11:30
     */
    public PageModel fundAdjustList(PageInfo pi, CmEmployee cmEmployee);
    
    /**
     * 导出公积金调基数据
     *
     * @author lifq
     *
     * 2017年6月29日  上午11:44:05
     */
    public List<Map<String,Object>> exportFundAdjust(ContextInfo cti,String empids);
    
    /**
	 * 查询 服务商员工列表
	 *
	 * @author lifq
	 *
	 * 2017年7月21日  上午11:40:59
	 */
	public PageModel findSpEmpListPage(PageInfo pi, Map<String,Object> paraMap);
	/**
	 * 查询 人员信息（不分页查询）
	 *
	 * @author lifq
	 *
	 * 2017年7月21日  下午4:25:28
	 */
	public List<Map<String,Object>> findSpEmpList(Map<String,Object> paraMap);


    /**
     * 获取当前企业下所有员工
     * @param cti
     * @return
     */
	public Map<String,CmEmployee> queryCorpAllEmps(ContextInfo cti);
	/**
	 * 导出 服务商员工列表数据
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午3:10:13
	 */
	public String exportSpEmpList(ContextInfo cti,Map<String,Object> paraMap, CmEmployee cmEmployee);

	/**
     * 获取人员相关的 服务商列表名称
     */
    public List<Map<String, Object>> findSpServiceByEmpCpId(Map<String,Object> paraMap);

    /**
     * 根据当前输入人的信息，查找已经存在数据库的员工
     * @param cmEmployee cmEmployeeOrg
     * @return
     */
    public CmEmployee queryExistEmpByInfo(CmEmployee cmEmployee,CmEmployeeOrg cmEmployeeOrg);

    /**
     * 参保人员趋势图
     * @param cti
     * @param areaId
     * @param months
     * @return
     */
    public List<Map<String,Object>> queryEmpTrend(ContextInfo cti,Integer areaId,List<String> months);

    /**
     * 任务中心--保存人员信息
     *  @param cti
     *  @param cmEmployee
     *  @param isIns
     *  @param isFund
     *  @param msgId
     *  @param taskId
     *  @param auditId 
     *  @param operation
     *  @param otherJson
     *	@return 			: void 
     *  @createDate  	: 2017年9月21日 下午2:37:08
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月21日 下午2:37:08
     *  @updateAuthor  :
     */
	public List<Integer> saveEmpDetail(Result result,ContextInfo cti, CmEmployee cmEmployee, Integer isIns, Integer isFund, Integer msgId,
			Integer taskId, Integer auditId,Integer operation,List<DynamicFields> otherJson);

	/**
	 * 任务中心--获取编辑人员详细信息
	 *  @param cti
	 *  @param taskId
	 *  @return 
	 *	@return 			: List<BusinessField> 
	 *  @createDate  	: 2017年9月26日 上午9:41:40
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月26日 上午9:41:40
	 *  @updateAuthor  :
	 */
	public List<BusinessField> findEditEmpDetail(ContextInfo cti, Integer taskId);

	/**
	 * 企业微信用户同步
	 *  @param text
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年10月10日 下午1:31:23
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年10月10日 下午1:31:23
	 *  @updateAuthor  :
	 */
	public Result joinEmpWx(com.alibaba.fastjson.JSONObject jsonObject);

    /**
     * 根据企业微信ID和企业ID获取人员信息
     *  @param query
     *  @return
     *	@return 			: CmEmployee
     *  @createDate  	: 2017年10月10日 下午3:31:02
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月10日 下午3:31:02
     *  @updateAuthor  :
     */
    public CmEmployee finByWechatId(CmEmployee query);


    /**
     * 获取可以同步的员工信息
     * @param cpId
     * @param synEmpIds
     * @return
     */
    public List<CmEmployee> queryUnRelationEmps(Integer cpId,String[] synEmpIds);

}
