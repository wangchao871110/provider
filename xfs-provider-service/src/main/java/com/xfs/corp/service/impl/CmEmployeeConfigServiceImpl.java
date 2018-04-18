package com.xfs.corp.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xfs.base.model.SysMessage;
import com.xfs.base.service.SysMessageService;
import com.xfs.business.dao.BdBstypeDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.business.service.SpsTaskHistoryService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.dao.CmEmployeeConfigDao;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;

@Service
public class CmEmployeeConfigServiceImpl implements CmEmployeeConfigService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmEmployeeConfigServiceImpl.class);
	
	@Autowired
	private CmEmployeeConfigDao cmEmployeeConfigDao;
	@Autowired
	private BdBstypeDao bdBstypeDao;
	@Autowired
    private CmEmployeeService cmEmployeeService;
	@Autowired
    private SpsSchemeService spsSchemeService;
	@Autowired
	private SpsTaskService spsTaskService;
	@Autowired
	private SpsTaskHistoryService spsTaskHistoryService;
	@Autowired
	private SysMessageService sysMessageService;
	
	public int save(ContextInfo cti, CmEmployeeConfig vo ){
		return cmEmployeeConfigDao.save(cti, vo);
	}
	public int insert(ContextInfo cti, CmEmployeeConfig vo ){
		return cmEmployeeConfigDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti, CmEmployeeConfig vo ){
		return cmEmployeeConfigDao.remove(cti, vo);
	}
	public int update(ContextInfo cti, CmEmployeeConfig vo ){
		return cmEmployeeConfigDao.update(cti, vo);
	}
	
	public PageModel findPage(ContextInfo cti,CmEmployeeConfig vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = cmEmployeeConfigDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmEmployeeConfig> datas = cmEmployeeConfigDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmEmployeeConfig> findAll(CmEmployeeConfig vo){
		
		List<CmEmployeeConfig> datas = cmEmployeeConfigDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/06/07 09:54:56
	
	@Override
	public CmEmployeeConfig findFempConfigDetails(ContextInfo cti, CmEmployeeConfig vo) {
		return cmEmployeeConfigDao.findFempConfigDetails(vo);
	}
	
	@Override
	public int saveEmpConfig(ContextInfo cti, CmEmployeeConfig cmEmployeeConfig) {
		if(StringUtils.isNotBlank(cmEmployeeConfig.getOnLineService())){
			if(cmEmployeeConfig.getOnLineService().startsWith(",")){
				cmEmployeeConfig.setOnLineService(cmEmployeeConfig.getOnLineService().substring(1, cmEmployeeConfig.getOnLineService().length()));
			}
		}
		if(StringUtils.isNotBlank(cmEmployeeConfig.getEmpService())){
			if(cmEmployeeConfig.getEmpService().startsWith(",")){
				cmEmployeeConfig.setEmpService(cmEmployeeConfig.getEmpService().substring(1, cmEmployeeConfig.getEmpService().length()));
			}
		}
		if(StringUtils.isNotBlank(cmEmployeeConfig.getAddService())){
			if(cmEmployeeConfig.getAddService().startsWith(",")){
				cmEmployeeConfig.setAddService(cmEmployeeConfig.getAddService().substring(1, cmEmployeeConfig.getAddService().length()));
			}
		}
		CmEmployeeConfig vo = new CmEmployeeConfig();
		vo.setCpId(cti.getOrgId());
    	vo.setAreaId(cmEmployeeConfig.getAreaId());
    	vo = cmEmployeeConfigDao.findFempConfigDetails(vo);
		if(null != vo){
			cmEmployeeConfig.setId(vo.getId());
			return cmEmployeeConfigDao.update(cti, cmEmployeeConfig);
		}
		cmEmployeeConfig.setCpId(cti.getOrgId());
		return cmEmployeeConfigDao.save(cti, cmEmployeeConfig);
	}
	
	@Override
	public List<BdBstype> findEmpServiceByAreaId(Integer areaId,Integer bsType) {
		return bdBstypeDao.findEmpServiceByAreaId(areaId, bsType);
	}
	
	@Override
	public List<BdBstype> findAddServiceByAreaId(Integer areaId) {
		return bdBstypeDao.findAddServiceByAreaId(areaId);
	}

	@Override
	public List<CmEmployeeConfig> findAllCorpOpenCity(Integer cpId) {
		return cmEmployeeConfigDao.findAllCorpOpenCity(cpId);
	}
	
	/**
	 * 获取手机端功能状态
	 *  @param result
	 *  @param emp
	 *  @return 
	 *  @createDate  	: 2017年10月26日 上午9:40:05
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年10月26日 上午9:40:05
	 *  @updateAuthor  	:
	 */
	@Override
	public Result findInJobItem(ContextInfo cti,Result result, CmEmployee emp) {
		result.setDataValue("isInsFundView", true);// 是否显示查询社保和公积金
    	result.setDataValue("isOnLineView", false);// 是否显示线上申请
    	result.setDataValue("isGuideView", false);// 是否显示社保服务指南
    	result.setDataValue("isOtherView", false);// 是否显示社保其他服务
        /**
         *  1、人没有参保地，只显示查社保和查公积金
         *  2、人有参保地，未缴只显示查社保/公积金，社保服务指南，社保其它服务、
         *  3、人有参保地在缴，要根据平台员工端配置判断显示内容
         */
        // 根据 企业id 获取方案信息
    	SpsScheme spsScheme = new SpsScheme();
    	spsScheme.setCpId(emp.getCpId());
    	spsScheme.setDr(0);
        List<SpsScheme> spsSchemes = spsSchemeService.findAll(spsScheme);
        // 企业下是否存在方案
        if (spsSchemes.size() == 0) {
        	result.setDataValue("isOtherView", true);// 是否显示社保其他服务
        	CmEmployee cmEmployee = new CmEmployee();
        	CmEmployeeConfig cmEmployeeConfig = new CmEmployeeConfig();
        	BdBstype obj = new BdBstype();
        	obj.setBsType(2);
        	obj.setInsuranceFundType("INSURANCE");
        	List<BdBstype> bdBstypes = bdBstypeDao.freeFind(obj);
        	String[] addService = new String[bdBstypes.size()];
        	for (int i=0;bdBstypes.size()>i;i++) {
        		addService[i] = bdBstypes.get(i).getBstypeId().toString();
			}
        	cmEmployeeConfig.setAddService(StringUtils.join(addService,","));
        	List<Map<String, Object>> bsTypeResRes = getBsType(cmEmployee,cmEmployeeConfig,"add");
            result.setDataValue("otherList", bsTypeResRes);
            return result;
        }
        // 获取人员信息
        CmEmployee cmEmployee = cmEmployeeService.findByPk(emp.getEmpId());
        // 根据 员工id 获取方案信息
        SpsScheme empSpsScheme = spsSchemeService.findSchemeByEmpId(emp.getEmpId());
        // 当前人员处于封存或者没有方案
        if("DECREASED".equals(cmEmployee.getInsuranceState()) || null == empSpsScheme){
        	result.setDataValue("isOtherView", true);// 是否显示社保其他服务
        	CmEmployee vo = new CmEmployee();
        	CmEmployeeConfig cmEmployeeConfig = new CmEmployeeConfig();
        	BdBstype obj = new BdBstype();
        	obj.setBsType(2);
        	obj.setInsuranceFundType("INSURANCE");
        	List<BdBstype> bdBstypes = bdBstypeDao.freeFind(obj);
        	String[] addService = new String[bdBstypes.size()];
        	for (int i=0;bdBstypes.size()>i;i++) {
        		addService[i] = bdBstypes.get(i).getBstypeId().toString();
			}
        	cmEmployeeConfig.setAddService(StringUtils.join(addService,","));
        	List<Map<String, Object>> bsTypeResRes = getBsType(vo,cmEmployeeConfig,"add");
            result.setDataValue("otherList", bsTypeResRes);
            result.setDataValue("isOnLineView", true);// 是否显示线上申请
            // 是否存在入职任务
            List<SpsTask> spsTasks = spsTaskService.findByEmpId(emp.getEmpId(), BsType.INCREMENT_INSUR_ENTRY.getCode(), "TODO");
            Map<String,Object> map = new HashMap<>();
            map.put("code", BsType.INCREMENT_INSUR_ENTRY.getCode());
        	map.put("name", BsType.INCREMENT_INSUR_ENTRY.getName());
            if(spsTasks.size() > 0){
        		map.put("status", true);
        		map.put("enabled", false);
        		map.put("isPoint", false);
        		map.put("statusName", "处理中");
            	if(spsTasks.size()>0){
            		map.put("taskId", spsTasks.get(0).getTaskId());
            	}
        	}else{
            	map.put("status", false);
            	map.put("enabled", true);
        		map.put("isPoint", true);
        	}
        	List<Map<String,Object>> onLineRes = new ArrayList<>();
        	onLineRes.add(map);
        	result.setDataValue("onLineList", onLineRes);
        	return result;
        }
        // 人员有参保地--获取员工端配置信息
        CmEmployeeConfig vo = new CmEmployeeConfig();
        vo.setCpId(empSpsScheme.getCpId());
        vo.setAreaId(empSpsScheme.getAreaId());
        vo.setDr(0);
        vo.setIsEmp(1);
        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigDao.freeFind(vo);
        // 人员有参保地--未开通移动端
        if (CollectionUtils.isEmpty(cmEmployeeConfigs) || 0 == cmEmployeeConfigs.get(0).getIsEmp()) {
            result.setSuccess(true);
            result.setDataValue("isInsFundView", false);// 是否显示查询社保和公积金
            result.setError("未开通员工端服务!");
            return result;
        }
		CmEmployeeConfig cmEmployeeConfig = cmEmployeeConfigs.get(0);
        // 是否查询社保和公积金 0：未开通，1：开通
        if(0 == cmEmployeeConfig.getIsInsFund()){
        	result.setDataValue("isInsFundView", false);// 是否显示查询社保和公积金
        }
    	// 是否开通线上申请 0：未开通，1：开通
        if(1 == cmEmployeeConfig.getIsOnLineService()){
        	result.setDataValue("isOnLineView", true);// 是否显示线上申请
        	// 如果人员未缴 只显示信息收集
            if(!"ON".equals(cmEmployee.getInsuranceState()) && !"DECREASING".equals(cmEmployee.getInsuranceState())){
            	List<Map<String,Object>> onLineRes = new ArrayList<>();
            	Map<String,Object> map = new HashMap<>();
            	map.put("code", BsType.INCREMENT_INSUR_ENTRY.getCode());
            	map.put("name", BsType.INCREMENT_INSUR_ENTRY.getName());
				// 查询入职任务
				map.put("isPoint", false);
				map.put("status", true);
        		map.put("enabled", false);
        		map.put("statusName", "处理中");
        		boolean flag = true;
				List<SpsTask> spsTasks = spsTaskService.findByEmpId(cmEmployee.getEmpId(), BsType.INCREMENT_INSUR_ENTRY.getCode(), "TODO");
            	if(spsTasks.size() > 0){
                	if(spsTasks.size()>0){
                		map.put("taskId", spsTasks.get(0).getTaskId());
                		flag = false;
                	}
            	}else{
            		SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
                	// 成功任务单
                	List<SpsTask> spsTasksCompleted = spsTaskService.findByEmpId(cmEmployee.getEmpId(), BsType.INCREMENT_INSUR_ENTRY.getCode(), "COMPLETED");
                	if(spsTasksCompleted.size() > 0){
	                	spsTaskHistory.setTaskId(spsTasksCompleted.get(0).getTaskId());
	                	spsTaskHistory.setStateFiledId(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId());
	                	// 根据成功任务单 获取历史数据
	                	List<SpsTaskHistory> spsTaskHistorys = spsTaskHistoryService.findAll(spsTaskHistory);
	                	for(int j=0;j<spsTaskHistorys.size();j++){
	                		// 判断任务单历史数据是否等于成功并且未读
	                		if(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId().equals(spsTaskHistorys.get(j).getStateFiledId()) ){
	                			if("UNREAD".equals(spsTaskHistorys.get(j).getIsRead())){
	                				map.put("statusName", "已完成");
	                				map.put("taskId", spsTasksCompleted.get(0).getTaskId());
	                			}else{
	                				result.setDataValue("isOnLineView", false);// 是否显示线上申请
	                			}
	                			flag = false;
	                        	break;
	                    	}
	                	}
                	}else{
                		// 关闭任务单
                		List<SpsTask> spsTasksClosed = spsTaskService.findByEmpId(cmEmployee.getEmpId(), BsType.INCREMENT_INSUR_ENTRY.getCode(), "CLOSED");
                    	if(spsTasksClosed.size() > 0){
                    		spsTaskHistory = new SpsTaskHistory();
	                    	spsTaskHistory.setTaskId(spsTasksClosed.get(0).getTaskId());
	                    	// 根据关闭任务单ID获取历史数据
	                    	List<SpsTaskHistory> spsTaskHistorys = spsTaskHistoryService.findAll(spsTaskHistory);
	                    	for(int j=0;j<spsTaskHistorys.size();j++){
	                    		// 判断任务单历史数据是否等于关闭并且未读
	                    		if(-1 == spsTaskHistorys.get(j).getStateFiledId()){
	                    			if("UNREAD".equals(spsTaskHistorys.get(j).getIsRead())){
	                    				map.put("statusName", "失败");
	                    				map.put("taskId", spsTasksClosed.get(0).getTaskId());
	                    			}else{
	                    				// 继续提交任务单
	                    				map.put("isPoint", false);
	            						map.put("status", false);
	            	            		map.put("enabled", true);
	                    			}
	                    			flag = false;
	                            	break;
	                        	}
	                    	}
                    	}
                	}
            	}
            	if(flag){
            		// 查询是否有收集信息消息 如果存在显示红点
                	SysMessage sysMessage = new SysMessage();
                	sysMessage.setTodoUserType("EMPLOYEE");
                	sysMessage.setTodoUser(cmEmployee.getEmpId());
                	sysMessage.setTodoOrg(cmEmployee.getCpId());
                	sysMessage.setType("HELPER");
                	sysMessage.setState("TODO");
                	sysMessage.setDataId(BsType.INCREMENT_INSUR_ENTRY.getCode());
                	sysMessage.setSendUserType("CUSTOMER");
                	List<SysMessage> sysMessages = sysMessageService.findAll(sysMessage);
                	map.put("enabled", true);
            		map.put("isPoint", true);
            		map.put("status", false);
                	if (sysMessages.size() == 0) {
                		map.put("statusName", null);
    				}
            	}
            	onLineRes.add(map);
            	result.setDataValue("onLineList", onLineRes);
            }else{
	        	if(StringUtils.isNotBlank(cmEmployeeConfig.getOnLineService())){
	        		List<Map<String, Object>> bsTypeResRes = getBsType(cmEmployee,cmEmployeeConfig,"onLine");
	                result.setDataValue("onLineList", bsTypeResRes);
	        	}else{
	        		result.setDataValue("isOnLineView", false);// 是否显示线上申请
	        	}
            }
        }
        // 是否开通社保服务指南 0：未开通，1：开通
        if(1 == cmEmployeeConfig.getIsEmpService()){
        	result.setDataValue("isGuideView", true);// 不显示社保服务指南
        	if(StringUtils.isBlank(cmEmployeeConfig.getEmpService())){
        		result.setDataValue("isGuideView", false);// 不显示社保服务指南
        	}
        	List<Map<String, Object>> bsTypeResRes = getBsType(cmEmployee,cmEmployeeConfig,"emp");
            result.setDataValue("guideList", bsTypeResRes);
        }
        // 是否开通增值服务 0：未开通，1：开通
        if(1 == cmEmployeeConfig.getIsAddService()){
        	result.setDataValue("isOtherView", true);// 不显示社保其他服务
        	List<Map<String, Object>> bsTypeResRes = getBsType(cmEmployee,cmEmployeeConfig,"add");
            result.setDataValue("otherList", bsTypeResRes);
        }
        return result;
	}
	
	/**
     * 获取业务类型
     *  @param empId
     *  @param bsTypes
     *  @param type
     *  @return 
     *	@return 			: List<Map<String,Object>> 
     *  @createDate  	: 2017年10月23日 下午7:09:15
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月23日 下午7:09:15
     *  @updateAuthor  :
     */
	public List<Map<String, Object>> getBsType(CmEmployee cmEmployee,CmEmployeeConfig cmEmployeeConfig,String type) {
		String[] bsType = null;
		if("onLine".equals(type)){
			bsType = cmEmployeeConfig.getOnLineService().split(",");
			Arrays.sort(bsType,Collections.reverseOrder());
		}else if("emp".equals(type)){
			bsType = cmEmployeeConfig.getEmpService().split(",");
		}else if("add".equals(type)){
			bsType = cmEmployeeConfig.getAddService().split(",");
		}
		System.out.println(bsType.length);
		List<Map<String,Object>> bsTypeRes = new ArrayList<>();
		for (int i=0;i<bsType.length;i++) {
			if(StringUtils.isBlank(bsType[i])){
				continue;
			}
		    Map<String,Object> map = new HashMap<>();
		    map.put("code", Integer.valueOf(bsType[i]));
		    map.put("name", BsType.getNameByCode(Integer.valueOf(bsType[i])));
		    if("onLine".equals(type)){
		    	map.put("status", false);
		    	map.put("taskId", null);
		    	
		    	// 根据人员ID和业务ID查询任务单，状态为TODO或者ERROR时显示办理中
                List<SpsTask> spsTasksTodo = spsTaskService.findByEmpId(cmEmployee.getEmpId(), Integer.valueOf(bsType[i]), "TODO");
                List<SpsTask> spsTasksError = spsTaskService.findByEmpId(cmEmployee.getEmpId(), Integer.valueOf(bsType[i]), "ERROR");
                if(spsTasksTodo.size()>0 || spsTasksError.size()>0){
                	map.put("status", true);
                	map.put("enabled", false);
                	map.put("statusName", "处理中");
                	if(spsTasksTodo.size()>0){
                		map.put("taskId", spsTasksTodo.get(0).getTaskId());
                	}else{
                		map.put("taskId", spsTasksError.get(0).getTaskId());
                	}
                } else {
                	map.put("status", true);
                	map.put("enabled", false);
                	boolean flag = true;
                	SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
                	// 成功任务单
                	List<SpsTask> spsTasksCompleted = spsTaskService.findByEmpId(cmEmployee.getEmpId(), Integer.valueOf(bsType[i]), "COMPLETED");
                	if(spsTasksCompleted.size() > 0){
	                	spsTaskHistory.setTaskId(spsTasksCompleted.get(0).getTaskId());
	                	spsTaskHistory.setStateFiledId(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId());
	                	// 根据成功任务单 获取历史数据
	                	List<SpsTaskHistory> spsTaskHistorys = spsTaskHistoryService.findAll(spsTaskHistory);
	                	for(int j=0;j<spsTaskHistorys.size();j++){
	                		// 判断任务单历史数据是否等于成功并且未读
	                		if(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId().equals(spsTaskHistorys.get(j).getStateFiledId())
	                				&& "UNREAD".equals(spsTaskHistorys.get(j).getIsRead())){
	                        	map.put("statusName", "已完成");
	                        	map.put("taskId", spsTasksCompleted.get(0).getTaskId());
	                        	flag = false;
	                        	break;
	                    	}
	                	}
                	}
                	// 成功数据条件不成立
                	if(flag){
                		// 关闭任务单
                		List<SpsTask> spsTasksClosed = spsTaskService.findByEmpId(cmEmployee.getEmpId(), Integer.valueOf(bsType[i]), "CLOSED");
                    	if(spsTasksClosed.size() > 0){
                    		spsTaskHistory = new SpsTaskHistory();
	                    	spsTaskHistory.setTaskId(spsTasksClosed.get(0).getTaskId());
	                    	// 根据关闭任务单ID获取历史数据
	                    	List<SpsTaskHistory> spsTaskHistorys = spsTaskHistoryService.findAll(spsTaskHistory);
	                    	for(int j=0;j<spsTaskHistorys.size();j++){
	                    		// 判断任务单历史数据是否等于关闭并且未读
	                    		if(-1 == spsTaskHistorys.get(j).getStateFiledId()
	                    				&& "UNREAD".equals(spsTaskHistorys.get(j).getIsRead())){
	                            	map.put("statusName", "已完成");
	                            	map.put("taskId", spsTasksClosed.get(0).getTaskId());
	                            	flag = false;
	                            	break;
	                        	}
	                    	}
                    	}
                	}
                	// 成功任务单和关闭任务单都不成立，可以新建任务
                	if(flag){
                		// 查询是否有收集信息消息 如果存在显示红点
                    	SysMessage sysMessage = new SysMessage();
                    	sysMessage.setTodoUserType("EMPLOYEE");
                    	sysMessage.setTodoUser(cmEmployee.getEmpId());
                    	sysMessage.setTodoOrg(cmEmployee.getCpId());
                    	sysMessage.setType("HELPER");
                    	sysMessage.setState("TODO");
                    	sysMessage.setDataId(Integer.valueOf(bsType[i]));
                    	sysMessage.setSendUserType("CUSTOMER");
                    	List<SysMessage> sysMessages = sysMessageService.findAll(sysMessage);
                    	if (sysMessages.size() > 0) {
                    		map.put("enabled", true);
                    		map.put("isPoint", true);
        				} else {
	                		map.put("status", false);
	                    	map.put("enabled", true);
	                    	map.put("statusName", null);
        				}
                	}
				}
		    }
		    bsTypeRes.add(map);
		}
		return bsTypeRes;
	}

}
