package com.xfs.base.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xfs.acc.dto.AreaSocialRuleVo;
import com.xfs.acc.dto.FundRatio;
import com.xfs.acc.dto.InsuranceLivingTypeDetailsVo;
import com.xfs.acc.dto.InsuranceLivingTypeVo;
import com.xfs.acc.dto.InsuranceRatio;
import com.xfs.acc.dto.Ratio;
import com.xfs.acc.dto.SchemeAccountRatio;
import com.xfs.aop.task.cs.CorpTaskAspectService;
import com.xfs.base.dao.BsSysStateReportDao;
import com.xfs.base.dao.BsSysVerDao;
import com.xfs.base.dao.BsSysVerFuncDAO;
import com.xfs.base.dao.SysMessageDao;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysMessage;
import com.xfs.base.model.SysUploadfile;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBsareatypeProcess;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeProcessService;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.BdBstypeService;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.Config;
import com.xfs.common.util.Constant;
import com.xfs.common.util.IdentityUtil;
import com.xfs.common.util.UUIDUtil;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.service.CmEmployeeAuditService;
import com.xfs.msg.dto.MessageVo;
import com.xfs.msg.dto.UserBusinessVo;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.model.SpsAccountRatio;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsAccountService;
import com.xfs.sp.service.SpsSchemeEmpService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.user.service.SysFunctionDataService;
import com.xfs.wx.message.dto.WxMessage;

/**
 * @author xiyanzhang
 * 
 * @Email: zhangxiyan@xinfushe.com
 * @version 创建时间：2016年11月10日 下午5:30:28
 */
@Service
public class SysMessageServiceImpl implements SysMessageService,IStaticVarConstant {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysMessageServiceImpl.class);
	@Autowired
	SysMessageDao sysMessageDao;
    @Autowired
    BsSysStateReportDao bsSysStateReportDao;
    @Autowired
    BsSysVerFuncDAO bsSysVerFuncDao;
    @Autowired
    BsSysVerDao bsSysVerDao;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	@Autowired
	private BdBusinessfieldService bdBusinessfieldService;
	@Autowired
	private BsAreaService bsAreaService;
	@Autowired
	private BdBsareatypeService bdBsareatypeService;
	@Autowired
	SpsSchemeService spsSchemeService;
	@Autowired
	private CmEmployeeService cmEmployeeService;
	@Autowired
	private CmEmployeeAuditService cmEmployeeAuditService;
	@Autowired
	private SpsAccountRatioService spsAccountRatioService;
	@Autowired
	private BsArearatioService bsArearatioService;
	@Autowired
	private BdInsuranceService bdInsuranceService;
	@Autowired
	private SysDictitemService sysDictitemService;
	@Autowired
	private SpsSchemeEmpService spsSchemeEmpService;
	@Autowired
	private CorpTaskAspectService corpTaskAspectService;
	@Autowired
	private SpsAccountService spsAccountService;
	@Autowired
	private SpsTaskService spsTaskService;
	@Autowired
    private BdBsareatypeProcessService bdBsareatypeProcessService;
	@Autowired
    private BdBstypeService bdBstypeService;
	@Autowired
    private SysUploadfileService sysUploadfileService;
	
	/**
	 * zhangxiyan 阅读消息通知
	 * @param vo
	 * @param cti
	 * @param flag
	 * @return
	 * @createDate : 2016年11月14日 下午4:09:25
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:09:25
	 * @updateAuthor :
	 */
	@Override
	public Result readMessageState(ContextInfo cti, SysMessage vo, String flag) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null != cti && null != cti.getUserId()) {
			vo.setTodoUser(cti.getUserId());// 代办人ID
			vo.setTodoOrg(cti.getOrgId());// 代办人组织ID
			vo.setTodoUserType(cti.getUserType());// 代办人类型
			vo.setState("DONE");// 消息通知状态
			vo.setModifyBy(cti.getUserId());
			vo.setModifyDt(date);
			// 全部修改
			if (StringUtils.equals(flag, "ALL")) {
				vo.setMessageId(null);
			}
			Integer ups = sysMessageDao.UpdateMessageState(cti, vo);
			if (0 < ups) {
				result.setSuccess(true).setError("删除成功！");
			} else {
				result.setSuccess(false).setError("删除失败！");
			}
		} else {
			result.setSuccess(false);
			result.setError("无权限访问！请联系管理员！");
			return result;
		}
		return result;
	}

	/**
	 * zhangxiyan 消息通知列表 不带分页
	 * 
	 * @param vo
	 * @param cti
	 * @return
	 * @createDate : 2016年11月14日 下午4:12:52
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:12:52
	 * @updateAuthor :
	 */
	@Override
	public List<HashMap<String, Object>> findShortPage(ContextInfo cti, SysMessage vo) {
		vo.setTodoUser(cti.getUserId());
		vo.setTodoUserType(cti.getUserType());
		vo.setTodoOrg(cti.getOrgId());
		vo.setState("TODO");// 待处理 消息通知
		List<HashMap<String, Object>> datas = sysMessageDao.freeShortFind(vo);
		return datas;
	}

	/**
	 * zhangxiyan 消息通知列表总条数
	 * 
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:14:07
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:14:07
	 * @updateAuthor :
	 */
	@Override
	public Integer messageCount(SysMessage vo) {
		Integer total = sysMessageDao.countFreeShortFind(vo);
		return total;
	}

	/**
	 * @param vo
	 * @param cti
	 * @return
	 * @createDate : 2016年11月14日 下午4:14:19
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:14:19
	 * @updateAuthor :
	 */
	@Override
	public PageModel findPage(PageInfo pi, SysMessage vo, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		vo.setTodoUser(cti.getUserId());
		vo.setTodoUserType(cti.getUserType());
		vo.setState("TODO");// 待处理 消息通知
		Integer total = sysMessageDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysMessage> datas = sysMessageDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:20:07
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:20:07
	 * @updateAuthor :
	 */
	public int save(ContextInfo cti, SysMessage vo) {
		return sysMessageDao.save(cti, vo);
	}

	/**
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:20:07
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:20:07
	 * @updateAuthor :
	 */
	public int insert(ContextInfo cti, SysMessage vo) {
		return sysMessageDao.insert(cti, vo);
	}

	/**
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:20:07
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:20:07
	 * @updateAuthor :
	 */
	public int remove(ContextInfo cti, SysMessage vo) {
		return sysMessageDao.remove(cti, vo);
	}

	/**
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:20:07
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:20:07
	 * @updateAuthor :
	 */
	public int update(ContextInfo cti, SysMessage vo) {
		return sysMessageDao.update(cti, vo);
	}

	/**
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:19:57
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:19:57
	 * @updateAuthor :
	 */
	public List<SysMessage> findAll(SysMessage vo) {

		List<SysMessage> datas = sysMessageDao.freeFind(vo);
		return datas;

	}

	/**
	 * zhangxiyan 待办事项列表
	 * @param cti
	 * @return
	 * @createDate : 2016年11月14日 下午4:19:40
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:19:40
	 * @updateAuthor :
	 */
	@Override
	public HashMap<String, Object> messageList(ContextInfo cti) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String nowDate = sdf.format(date);
		parameterMap.put("spId", cti.getOrgId());
		parameterMap.put("executeDate", nowDate);
		parameterMap.put("type", "TODO");
		// 数据权限
		String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
				Constant.DATATYPE);
		parameterMap.put("authority", authority);
		// 增员业务 社保新参 2、社保转入 3、公积金增加 10
		HashMap<String, Object> insFundAdd = sysMessageDao.insFundAdd(parameterMap);
		// 减员业务 社保减员4 、公积金减少 11
		HashMap<String, Object> insFundReduce = sysMessageDao.insFundReduce(parameterMap);
		// 补缴业务 社保补缴 29、公积金补缴30
		HashMap<String, Object> insFundSupple = sysMessageDao.insFundSupple(parameterMap);
		// 合作伙伴
		HashMap<String, Object> findPartner = sysMessageDao.findPartner(parameterMap);

		Integer messageCount = 0;
		messageCount = Integer.parseInt(String.valueOf(insFundAdd.get("count")))
				+ Integer.parseInt(String.valueOf(insFundReduce.get("count")))
				+ Integer.parseInt(String.valueOf(insFundSupple.get("count")))
				+ Integer.parseInt(String.valueOf(findPartner.get("count")));
		resultMap.put("addCount", insFundAdd.get("count"));// 总数
		resultMap.put("addType", null == insFundAdd.get("type") ? "TODO" : insFundAdd.get("type"));// 业务类型
		resultMap.put("addBsTypeId", "1000"); // 处理类型 1000 对应 2，3，10 新增
		resultMap.put("executeDate", nowDate);

		resultMap.put("reduceCount", insFundReduce.get("count"));
		resultMap.put("reduceType", null == insFundReduce.get("type") ? "TODO" : insFundReduce.get("type"));
		resultMap.put("reduceBsTypeId", "1001");// 处理类型1001 对应 4，11 减员
		resultMap.put("executeDate", nowDate);

		resultMap.put("suppleCount", insFundSupple.get("count"));
		resultMap.put("suppleType", null == insFundSupple.get("type") ? "TODO" : insFundSupple.get("type"));
		resultMap.put("suppleBsTypeId", "1002");// 处理类型 对应 29 30 补缴
		resultMap.put("executeDate", nowDate);

		resultMap.put("partherCount", findPartner.get("count"));
		resultMap.put("partherState", findPartner.get("state"));
		resultMap.put("partherType", findPartner.get("type"));
		resultMap.put("countCount", messageCount);

		return resultMap;
	}

	/**
	 * 增员业务 社保新参 2、社保转入 3、公积金增加 10
	 * 
	 * @param parameterMap
	 * @return
	 * @createDate : 2016年11月14日 下午4:19:23
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:19:23
	 * @updateAuthor :
	 */
	public HashMap<String, Object> insFundAdd(ContextInfo cti, HashMap<String, Object> parameterMap) {
		return sysMessageDao.insFundAdd(parameterMap);
	}

	/**
	 * 减员业务 社保减员4 、公积金减少 11
	 * 
	 * @param parameterMap
	 * @return
	 * @createDate : 2016年11月14日 下午4:18:29
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:18:29
	 * @updateAuthor :
	 */
	public HashMap<String, Object> insFundReduce(ContextInfo cti, HashMap<String, Object> parameterMap) {
		return sysMessageDao.insFundReduce(parameterMap);
	}

	/**
	 * 补缴业务 社保补缴 29、公积金补缴30
	 * 
	 * @param parameterMap
	 * @return
	 * @createDate : 2016年11月14日 下午4:15:51
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:15:51
	 * @updateAuthor :
	 */

	public HashMap<String, Object> insFundSupple(ContextInfo cti, HashMap<String, Object> parameterMap) {
		return sysMessageDao.insFundSupple(parameterMap);
	}

	/**
	 * zhangxiyan 消息通知 增加
	 * 
	 * @param vo
	 * @return
	 * @createDate : 2016年11月14日 下午4:15:13
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:15:13
	 * @updateAuthor :
	 */
	@Override
	public Result addMessage(ContextInfo cti, SysMessage vo) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (StringUtils.equals(vo.getType(), "PAY")) {
			if (StringUtils.equals(vo.getTodoUserType(), "CUSTOMER")) {
				vo.setUrl("/account/index");
			} else if (StringUtils.equals(vo.getTodoUserType(), CMCORPTYPE_SERVICE)) {
				vo.setUrl("/cashier/billCashier");
			}
			List<HashMap<String, Object>> userIdList = sysMessageDao.findUserIdListByUrl(vo);
			if (userIdList.size() > 0) {
				for (int i = 0; i < userIdList.size(); i++) {
					vo.setTodoUser(Integer.parseInt(String.valueOf(userIdList.get(i).get("user_id"))));
					vo.setTodoUserType(String.valueOf(userIdList.get(i).get("user_type")));
					vo.setTodoOrg(Integer.parseInt(String.valueOf(userIdList.get(i).get("org_id"))));
					vo.setState("TODO");// 消息处理状态
					vo.setSendTime(date);
					vo.setDr(0);
					vo.setUrl(vo.getUrl() + "?data_id=" + vo.getDataId());// 改写保存url
					sysMessageDao.insert(cti, vo);
				}
			} else {
				result.setSuccess(false).setError("消息录入失败！您当前无权限！");
			}
		}
		// TASK 任务单推给企业端 企业的用户dataId=taskId--> cpId = OrgId-->userId
		if (StringUtils.equals(vo.getType(), "TASK")) {
			vo.setUrl("/task/queryTaskDetail?data_id=" + vo.getDataId());
			vo.setState("TODO");// 消息处理状态
			vo.setSendTime(date);
			vo.setDr(0);
			// 根据任务单id查询任务单里 CPID 关联sysuser表orgid 查出来 userid 的list
			List<HashMap<String, Object>> userIdList = sysMessageDao.userIdList(vo);
			if (null != userIdList && userIdList.size() > 0) {
				for (HashMap<String, Object> hashMap : userIdList) {
					vo.setTodoUser(Integer.parseInt(String.valueOf(hashMap.get("user_id"))));
					vo.setTodoUserType(String.valueOf(hashMap.get("user_type")));
					vo.setTodoOrg(Integer.parseInt(String.valueOf(hashMap.get("org_id"))));
					sysMessageDao.insert(cti, vo);
				}
			}
		}
		return result;
	}

    @Override
    public Map findByVersionMessage(ContextInfo cti) {
        Integer userId = cti.getUserId();

        Map map = bsSysStateReportDao.findBsSysStateReport(userId);
        // 获取最新版本信息
        List list = bsSysVerDao.freeFindBsSysVer();
        Map versionMap = null;
        Map lastVersion = null;
        if (list != null && list.size() > 0) {
            lastVersion = (Map)list.get(0);
            if (lastVersion.get("create_dt") != null) {
                Date date = (Date)lastVersion.get("create_dt");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                lastVersion.put("create_dt", sdf.format(date));
            }

            //获取新增功能列表
            List verAddList = bsSysVerFuncDao.freeFindByVersionId(Integer.parseInt(lastVersion.get("id").toString()), "add");
            //获取优化功能列表
            List verOpList = bsSysVerFuncDao.freeFindByVersionId(Integer.parseInt(lastVersion.get("id").toString()), "optimize");
            lastVersion.put("verFunList", verAddList);
            lastVersion.put("verOpList", verOpList);
        }

        // 有用户版本信息
        if (map != null) {
            Integer versionId = Integer.parseInt(map.get("attribute_value").toString());
                if (versionId.intValue() != (Integer.parseInt(lastVersion.get("id").toString()))) {
                    if (lastVersion != null) {
                        BsSysStateReport report = new BsSysStateReport();
                        report.setId(Integer.parseInt(map.get("id").toString()));
                        report.setAttributeValue(lastVersion.get("id").toString());
                        int success = bsSysStateReportDao.updateVersionMessage(cti,report);
                        if (success > 0) {
                            versionMap = lastVersion;
                        }
                    }
                }
        } else {
            // 没有用户版本信息更新 增加最新版本信息
            BsSysStateReport report = new BsSysStateReport();
            report.setOwner(userId.toString());
            report.setOwnerType(CMCORPTYPE_SERVICE);
            report.setAttributeName("VERSIONMESSAGE");
            if (lastVersion != null) {
                report.setAttributeValue(lastVersion.get("id").toString());
            }

            int i = bsSysStateReportDao.insertVersionMessage(cti,report);
            if (i > 0) {
                versionMap = lastVersion;
            }
        }

        return versionMap;  //To change body of implemented methods use File | Settings | File Templates.
    }


	/**
	 * 查询薪福加文章消息
	 *  @param   cti, pageInfo, type
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2017/1/10 10:56
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/10 10:56
	 *  @updateAuthor  :
	 */
	@Override
    public PageModel findXfjContentMsgPage(ContextInfo cti,PageInfo pageInfo,String type){
		PageModel pm = new PageModel(pageInfo);
		SysMessage query = new SysMessage();
		query.setType(type);
		query.setTodoUser(cti.getUserId());
		query.setTodoUserType("XINFUJIA");
		Integer total = sysMessageDao.findPageContentMsgCount(query);
		pm.setTotal(total);
		List<Map<String,Object>> datas = sysMessageDao.findPageContentMsg(query,pageInfo.getOffset(),pageInfo.getPagesize());
		pm.setDatas(datas);
		return pm;
	}


	/**
	 * 查询薪福加系统消息
	 *  @param   cti, pageInfo, type
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2017/1/10 10:56
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/10 10:56
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findXfjSysMsgPage(ContextInfo cti,PageInfo pageInfo){
		PageModel pm = new PageModel(pageInfo);
		SysMessage query = new SysMessage();
		query.setType("SYS");
		query.setTodoUser(cti.getUserId());
		query.setTodoUserType("XINFUJIA");
		Integer total = sysMessageDao.findPageContentMsgCount(query);
		pm.setTotal(total);
		List<SysMessage> datas = sysMessageDao.findPageSysMsg(query,pageInfo.getOffset(),pageInfo.getPagesize());
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 获取消息数
	 *  @param   message
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/1/12 15:57
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/12 15:57
	 *  @updateAuthor  :
	 */
	@Override
	public Integer countMsgNum(SysMessage message){
		return sysMessageDao.findPageContentMsgCount(message);
	}

	/**
	 * 查询消息信息列表
	 *  @param pi
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年3月15日 下午5:36:00
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月15日 下午5:36:00
	 *  @updateAuthor  	:
	 */
	@Override
	public PageModel findMessageList(PageInfo pi, SysMessage vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysMessageDao.findMessageListCount(vo);
		pm.setTotal(total);
		List<SysMessage> datas = sysMessageDao.findMessageList(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 获取入职人员信息列表
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年3月16日 下午2:41:24
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月16日 下午2:41:24
	 *  @updateAuthor  	:
	 */
	@Override
	public PageModel messageUserList(ContextInfo cti,PageInfo pi, SysMessage vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysMessageDao.findMessageUserListCount(vo);
		pm.setTotal(total);
		List<MessageVo> datas = sysMessageDao.messageUserList(vo, pageIndex, pageSize);
		for (MessageVo messageVo : datas) {
			Map<String,Object> maps = (Map<String,Object>)JSONObject.parseObject(messageVo.getJson());  
			List<Map<String,Object>> otherParm = new ArrayList<Map<String,Object>>();
			Map<String,Object> parm = null;
			String[] hospitalName = null;
	        for (Object map : maps.entrySet()){
	        	String key = (String) ((Map.Entry)map).getKey();
	        	if(key.equals("name") || key.equals("mobile") || key.equals("empId")
	        		|| key.equals("identity_code") || key.equals("identity_type") || key.equals("identity_type_name") 
	        		|| key.equals("insuranceLiveType_name") || key.equals("insuranceLiveType") 
	        		|| key.equals("insurance") || key.endsWith("_name") || key.equals("nation") 
	        		|| key.equals("countryregion") || key.equals("education") || key.equals("workdate")){
	        		continue;
	        	}
	        	if(key.equals("hospital")){
	        		List<Map<String,Object>> values = null;
        			String valueString = ((Map.Entry)map).getValue().toString();
        			if(StringUtils.isNotEmpty(valueString)){
        				values = (List<Map<String,Object>>)JSONArray.parse(valueString);
        			}
	        		if(null != values){
	        			hospitalName = new String[values.size()];
	        			for (int i = 0; i < values.size(); i++) {
		        			hospitalName[i] = values.get(i).get("name").toString();
						}
	        		}
	        	}else{
					String value = ((Map.Entry)map).getValue().toString();
		        	parm = new HashMap<>();
		        	BdBusinessfield bdBusinessfield = bdBusinessfieldService.findNameByCode(key);
		        	if(null != bdBusinessfield){
		        		if(!"FILE".equals(bdBusinessfield.getType())){
			        		parm.put("name", bdBusinessfield.getName());
			        		if("PULL".equals(bdBusinessfield.getType())){
			        			value = (String) maps.get(key+"_name");
			        		}
				        	parm.put("value", value);
				        	otherParm.add(parm);
		        		}
		        	}
	        	}
	        }
	        
	        messageVo.setSex(IdentityUtil.identityCalaSex(messageVo.getCode()));// 性别
	        messageVo.setNation(null == maps.get("nation_name") ? "--" : maps.get("nation_name").toString());// 民族
	        messageVo.setCountryregion(null == maps.get("countryregion_name") ? "--" : maps.get("countryregion_name").toString());// 国籍
	        messageVo.setEducation(null == maps.get("education_name") ? "--" : maps.get("education_name").toString());// 学历
	        messageVo.setWorkdate(null == maps.get("workdate") ? "--" : maps.get("workdate").toString());// 参加工作日期
	        if(null != hospitalName && hospitalName.length > 0){
	        	parm = new HashMap<>();
	 	        parm.put("name", "医院");
	 	        parm.put("value", com.xfs.common.util.StringUtils.join(hospitalName,","));
	 	        otherParm.add(parm);
	        }
	        messageVo.setOtherParm(otherParm);
	        messageVo.setJson(null);
	        List<Map<String, String>> insuranceFundDate = this.findInsuranceFundDate(cti, messageVo.getCtiyId());
	        messageVo.setInsuranceFundDate(insuranceFundDate);
	        if(null == messageVo.getDefaultInsuranceFundDate()){
	        	messageVo.setDefaultInsuranceFundDate(this.findDefaultDate(cti, messageVo.getCtiyId()));
	        }
	        Map<String,Object> paraMap = new HashMap<>();
	        paraMap.put("area_id", messageVo.getCtiyId());
	        List<Map<String, Object>> map = sysDictitemService.findLiveTypeByAreaId(paraMap);
	        List<SysDictitem> liveType = new ArrayList<>();
	        for (int i = 0; i < map.size(); i++) {
	        	SysDictitem sysDictitem = new SysDictitem();
	        	sysDictitem.setAreaId(Integer.parseInt(String.valueOf(map.get(i).get("area_id"))));
	        	sysDictitem.setCode(String.valueOf(map.get(i).get("code")));
	        	sysDictitem.setName(String.valueOf(map.get(i).get("name")));
	        	liveType.add(sysDictitem);
			}
	        messageVo.setLiveType(liveType);
	        if("UNREAD".equals(messageVo.getIsRead())){
	        	messageVo.setIsRead("READ");
	        }
	        // 获取员工方案
	        CmEmployee cmEmployee = new CmEmployee();
	        cmEmployee.setCpId(cti.getOrgId());
	        cmEmployee.setName(messageVo.getUserName());
	        cmEmployee.setIdentityCode(messageVo.getCode());
	        cmEmployee = cmEmployeeService.findCpEmplByNameAndIdentityCode(cmEmployee);
	        if (null != cmEmployee) {
	        	SpsScheme spsScheme = new SpsScheme();
	        	spsScheme.setSchemeId(cmEmployee.getSchemeId());
	        	spsScheme.setDr(0);
	        	spsScheme = spsSchemeService.findByPK(spsScheme);
	        	if(null != spsScheme){
	        		messageVo.setSchemeId(spsScheme.getSchemeId());
		  	        messageVo.setSchemeName(spsScheme.getName());
		  	        if(-999 != spsScheme.getSpId()){
		  	        	messageVo.setSchemeType(1);
		  	        }
	        	}
	        	if(StringUtils.isNotBlank(cmEmployee.getInsuranceState()) 
	        			&& ("INCREASING".equals(cmEmployee.getInsuranceState()) || "ON".equals(cmEmployee.getInsuranceState()) 
	        					|| "DECREASING".equals(cmEmployee.getInsuranceState()))){
	        		messageVo.setIsIns(1);
	        	}
	        	if(StringUtils.isNotBlank(cmEmployee.getFundState()) 
	        			&& ("INCREASING".equals(cmEmployee.getFundState()) || "ON".equals(cmEmployee.getFundState()) 
	        					|| "DECREASING".equals(cmEmployee.getFundState()))){
	        		messageVo.setIsFund(1);
	        	}
			}
	        // 根据企业ID和城市获取方案  按自服务倒序
	        SpsScheme spsScheme = new SpsScheme();
	        spsScheme.setAuthority(cti.getAuthority());
	        spsScheme.setCpId(cti.getOrgId());
	        spsScheme.setAreaId(messageVo.getCtiyId());
	        List<SpsScheme> spsSchemes = spsSchemeService.findSchemeByCityIdAndCpIdOderBySpId(spsScheme);
	        List<SpsScheme> selfSchemeList = new ArrayList<>();
	        List<SpsScheme> entrustSchemeList = new ArrayList<>();
	        for (int i = 0; i < spsSchemes.size(); i++) {
				if(-999 == spsSchemes.get(i).getSpId()){
					selfSchemeList.add(spsSchemes.get(i));
				}else{
					entrustSchemeList.add(spsSchemes.get(i));
				}
			}
	        messageVo.setSelfScheme(selfSchemeList);
	        messageVo.setEntrustScheme(entrustSchemeList);
		}
		pm.setDatas(datas);
		if(null != vo.getMessageId() && !"".equals(vo.getMessageId())){
			SysMessage sysMessage = new SysMessage();
			sysMessage.setMessageId(vo.getMessageId());
			sysMessage = sysMessageDao.findByPK(sysMessage);
			if("UNREAD".equals(sysMessage.getIsRead())){
				sysMessage.setTodoUser(cti.getUserId());
				sysMessage.setTodoUserType(cti.getUserType());
				sysMessage.setTodoOrg(cti.getOrgId());
				sysMessage.setDataId(vo.getDataId());
				sysMessage.setMessageId(vo.getMessageId());
				sysMessage.setIsRead("READ");
				sysMessage.setAuthority(cti.getAuthority());
				sysMessageDao.updateMessageState(cti,sysMessage);
			}
		}
		return pm;
	}

	/**
	 * 
	 *  @param areaId 
	 *  @createDate  	: 2017年3月17日 上午9:51:27
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 上午9:51:27
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, String>> findInsuranceFundDate(ContextInfo cti,Integer areaId) {
		// 根据企业ID和城市获取最小方案
		SpsScheme scheme = new SpsScheme();
		scheme.setCpId(cti.getOrgId());
		scheme.setAreaId(areaId);
		scheme.setAuthority(cti.getAuthority());
		scheme.setSpId(-999);
		scheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
		
		BsArea bsArea = new BsArea();
        bsArea.setAreaId(areaId);
        bsArea = bsAreaService.findbyPK(bsArea);
        // 新参
        BdBsareatype bdBsareatype2 = new BdBsareatype();
        bdBsareatype2.setAreaId(areaId);
        bdBsareatype2.setBstypeId(2);
        List<BdBsareatype> fList = bdBsareatypeService.findAll(bdBsareatype2);
        if (fList.size() > 0) {
        	bdBsareatype2 = fList.get(0);
        }
        // 增加
        BdBsareatype bdBsareatype10 = new BdBsareatype();
        bdBsareatype10.setAreaId(bsArea.getBelongCity());
        bdBsareatype10.setBstypeId(10);
        List<BdBsareatype> list = bdBsareatypeService.findAll(bdBsareatype10);
        if (list.size() > 0) {
        	bdBsareatype10 = list.get(0);
        }
        return this.findDate(scheme,bdBsareatype2,bdBsareatype10);
	}
	
	/**
	 * 获取公积金社保缴纳日期
	 *  @param spsScheme
	 *  @param bdBsareatype2
	 *  @param bdBsareatype10
	 *  @return 
	 *	@return 			: Map<String,String> 
	 *  @createDate  	: 2017年3月17日 下午2:41:07
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 下午2:41:07
	 *  @updateAuthor  :
	 */
	private List<Map<String, String>> findDate(SpsScheme spsScheme,BdBsareatype bdBsareatype2,BdBsareatype bdBsareatype10){
		List<Map<String, String>> insuranceFundDateMapList = new ArrayList<>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	        Calendar currentMoon = Calendar.getInstance();
	        int currentDay = currentMoon.get(Calendar.DAY_OF_MONTH);// 当前日期
	        // 社保
	        String insuranceDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > spsScheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        insuranceDate = format.format(currentMoon.getTime());
	        // 公积金
	        String fundDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > spsScheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        fundDate = format.format(currentMoon.getTime());
	        Date iDate = format.parse(insuranceDate);
	        Date fDate = format.parse(fundDate);
	        // 获取最小的月份
	        String currentMonth = fundDate;
	        if(iDate.before(fDate)) {
	        	currentMonth = insuranceDate;
	        }
	        Map<String, String> insuranceFundDateMap = new HashMap<String, String>();
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMap.put("insuranceFundDate", currentMonth);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
	        // 延续一个月
	        insuranceFundDateMap = new HashMap<String, String>();
	        currentMoon.setTime(format.parse(currentMonth));
	        currentMoon.add(Calendar.MONTH, 1);
	        String nextOneMoon = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceFundDate", nextOneMoon);
	        
	        currentMoon.setTime(format.parse(insuranceDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        insuranceDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        
	        currentMoon.setTime(format.parse(fundDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        fundDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
	        
	        // 延续两个月
	        insuranceFundDateMap = new HashMap<String, String>();
	        currentMoon.setTime(format.parse(nextOneMoon));
	        currentMoon.add(Calendar.MONTH, 1);
	        String nextTwoMoon = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceFundDate", nextTwoMoon);
	        
	        currentMoon.setTime(format.parse(insuranceDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        insuranceDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        
	        currentMoon.setTime(format.parse(fundDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        fundDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insuranceFundDateMapList;
	}
	
	/**
	 * 获取默认月份
	 *  @param cti
	 *  @param areaId
	 *  @return 
	 *	@return 			: String 
	 *  @createDate  	: 2017年5月12日 下午3:15:09
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月12日 下午3:15:09
	 *  @updateAuthor  :
	 */
	private String findDefaultDate(ContextInfo cti,Integer areaId){
		try {
			SpsScheme scheme = new SpsScheme();
			scheme.setCpId(cti.getOrgId());
			scheme.setAreaId(areaId);
			scheme.setAuthority(cti.getAuthority());
			scheme.setSpId(-999);
			scheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
			
			BsArea bsArea = new BsArea();
	        bsArea.setAreaId(areaId);
	        bsArea = bsAreaService.findbyPK(bsArea);
	        // 新参
	        BdBsareatype bdBsareatype2 = new BdBsareatype();
	        bdBsareatype2.setAreaId(areaId);
	        bdBsareatype2.setBstypeId(2);
	        List<BdBsareatype> fList = bdBsareatypeService.findAll(bdBsareatype2);
	        if (fList.size() > 0) {
	        	bdBsareatype2 = fList.get(0);
	        }
	        // 增加
	        BdBsareatype bdBsareatype10 = new BdBsareatype();
	        bdBsareatype10.setAreaId(bsArea.getBelongCity());
	        bdBsareatype10.setBstypeId(10);
	        List<BdBsareatype> list = bdBsareatypeService.findAll(bdBsareatype10);
	        if (list.size() > 0) {
	        	bdBsareatype10 = list.get(0);
	        }
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	        Calendar currentMoon = Calendar.getInstance();
	        int currentDay = currentMoon.get(Calendar.DAY_OF_MONTH);// 当前日期
	        // 社保
	        String insuranceDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > scheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        insuranceDate = format.format(currentMoon.getTime());
	        // 公积金
	        String fundDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > scheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        fundDate = format.format(currentMoon.getTime());
	        Date iDate = format.parse(insuranceDate);
	        Date fDate = format.parse(fundDate);
	        // 获取最小的月份
	        String currentMonth = fundDate;
	        if(iDate.before(fDate)) {
	        	currentMonth = insuranceDate;
	        }
	        return currentMonth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取入职人员文件ID信息
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年3月17日 下午5:27:27
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 下午5:27:27
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Integer> messageUserDetailsFile(SysMessage vo) {
		List<MessageVo> datas = sysMessageDao.messageUserList(vo, 0, 1);
		List<Integer> fileIds = new ArrayList<Integer>();
		for (MessageVo messageVo : datas) {
			Map<String,Object> maps = (Map<String,Object>)JSONObject.parseObject(messageVo.getJson());  
	        for (Object map : maps.entrySet()){
	        	String key = (String) ((Map.Entry)map).getKey();
	        	String value = (String) ((Map.Entry)map).getValue();
	        	if(key.equals("name") || key.equals("mobile") || key.equals("empId")
	        		|| key.equals("identity_code") || key.equals("identity_type") || key.equals("identity_type_name") 
	        		|| key.equals("insuranceLiveType_name") || key.equals("insuranceLiveType") 
	        		|| key.equals("hospital") || key.equals("insurance") || key.endsWith("_name")){
	        		continue;
	        	}
	        	BdBusinessfield bdBusinessfield = bdBusinessfieldService.findNameByCode(key);
	        	if(null != bdBusinessfield){
	        		if("FILE".equals(bdBusinessfield.getType())){
	        			fileIds.add(Integer.parseInt(value));
	        		}
	        	}
	        }
		}
		return fileIds;
	}

	/**
	 * 保存员工
	 *  @param cti
	 *  @param cmEmployee
	 *  @createDate  	: 2017年3月20日 上午10:29:15
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月20日 上午10:29:15
	 *  @updateAuthor  	:
	 */
	@Override
	public void saveEmp(ContextInfo cti, CmEmployee cmEmployee, SpsTask spsTask, SysMessage sysMessage,String msgType,Integer isIns,Integer isFund) {
		// 判断消息类型,HELPER：新员工入职；SPSTASK：其他任务
		if(msgType.equals("HELPER")){
			Integer oldEmpId = cmEmployee.getEmpId();
			CmEmployeeAudit cmEmployeeAudit = new CmEmployeeAudit();
			cmEmployeeAudit.setEmpId(oldEmpId);
			cmEmployeeAudit = cmEmployeeAuditService.findByPK(cmEmployeeAudit);
			// 根据名称和身份证号查询员工是否存在
			CmEmployee query = new CmEmployee();
			query.setCpId(cti.getOrgId());
			query.setName(cmEmployee.getName());
			query.setIdentityCode(cmEmployee.getIdentityCode());
			query = cmEmployeeService.findCpEmplByNameAndIdentityCode(query);
			if(null != query && null != query.getEmpId()){
				cmEmployee.setEmpId(query.getEmpId());
				cmEmployee.setCpId(cti.getOrgId());
			}else{
				cmEmployee.setEmpId(null);
				cmEmployee.setCpId(cti.getOrgId());
			}
			cmEmployee.setOpenid(cmEmployeeAudit.getOpenId());
			// 根据企业ID和城市获取最小方案
			SpsScheme scheme = new SpsScheme();
			scheme.setSchemeId(cmEmployee.getSchemeId());
			scheme.setDr(0);
//			scheme.setCpId(cti.getOrgId());
//			scheme.setAreaId(cmEmployeeAudit.getAreaId());
//			scheme.setAuthority(cti.getAuthority());
//			scheme.setSpId(-999);
//			scheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
			scheme = spsSchemeService.findByPK(scheme);
			BigDecimal insuranceSalary = BigDecimal.ZERO;
			BigDecimal fundSalary = BigDecimal.ZERO;
			// 如果不存在方案创建默认方案
			if(null == scheme){
				// 社保公积金比例
				AreaSocialRuleVo areaSocialRuleVo = spsAccountRatioService.findSocialRule(cti,cmEmployeeAudit.getAreaId(),null);
	            SchemeAccountRatio applyMessage = new SchemeAccountRatio();
	            applyMessage.setAreaId(cmEmployeeAudit.getAreaId());
	            List<InsuranceRatio> insuranceRatio = new ArrayList<>();
	            List<FundRatio> fundRatio = new ArrayList<>();
	            applyMessage.setInsuranceRatio(insuranceRatio);
	            applyMessage.setFundRatio(fundRatio);
	            // 默认社保比例
	            for(InsuranceLivingTypeVo insurance : areaSocialRuleVo.getInsuranceLivingType()){
	                InsuranceRatio ratio = new InsuranceRatio();
	                ratio.setInsuranceLivingType(insurance.getPeopleTypeCode());
	                ratio.setRatio(new ArrayList<Ratio>());
	                for(InsuranceLivingTypeDetailsVo detailvo : insurance.getInsuranceLivingType()){
	                    Ratio r = new Ratio();
	                    r.setRatioId(detailvo.getRatioId());
	                    ratio.getRatio().add(r);
	                    // 获取基数下限
	                    if(insuranceSalary.compareTo(BigDecimal.ZERO) == 0){
	                    	insuranceSalary = new BigDecimal(detailvo.getLowerNum());
	                    }else{
	                    	if(insuranceSalary.compareTo(new BigDecimal(detailvo.getLowerNum()))  > 0 
	                    			&& new BigDecimal(detailvo.getLowerNum()).compareTo(BigDecimal.ZERO) > 0){
	                    		insuranceSalary = new BigDecimal(detailvo.getLowerNum());
	                    	}
	                    }
	                }
	                insuranceRatio.add(ratio);
	            }
	            // 默认公积金比例
	            for(InsuranceLivingTypeDetailsVo insurance : areaSocialRuleVo.getFundType()){
	                FundRatio ratio = new FundRatio();
	                ratio.setRatioId(insurance.getRatioId());
	                fundRatio.add(ratio);
	                // 获取基数下限
	                if(fundSalary.compareTo(BigDecimal.ZERO) == 0){
	                	fundSalary = new BigDecimal(insurance.getLowerNum());
	                }else{
	                	if(fundSalary.compareTo(new BigDecimal(insurance.getLowerNum()))  > 0 
	                			&& new BigDecimal(insurance.getLowerNum()).compareTo(BigDecimal.ZERO) > 0){
	                		fundSalary = new BigDecimal(insurance.getLowerNum());
	                	}
	                }
	            }
	            scheme = spsAccountService.saveAccount(cti,applyMessage);
			}
			// 是否缴纳社保 0：未缴纳；1：缴纳
			if(isIns.equals(1)){
				// 社保基数为空或者等于0
				if(null == cmEmployee.getInsuranceSalary() 
						|| cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) != 1){
					List<InsuranceTypeDto> insuranceTypeDtos = cmEmployeeService.queryAccountInsuranceListByAccountId(scheme.getInsuranceAccountId(), cmEmployee.getInsuranceLivingType(), spsTask.getBeginDate(), "Y");
		            for(InsuranceTypeDto dto : insuranceTypeDtos){
		                if(insuranceSalary.compareTo(BigDecimal.ZERO) == 0){
		                	insuranceSalary = dto.getCorpBaseLower();
		                }else if(insuranceSalary.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
		                	insuranceSalary = dto.getCorpBaseLower();
		                }
		            }
				}
				if(null != cmEmployee.getInsuranceSalary() 
						&& cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) != 1){
					cmEmployee.setInsuranceSalary(insuranceSalary);
				}
			}
			// 是否缴纳公积金 0：未缴纳；1：缴纳
			if(isFund.equals(1)){
				// 公积金基数为空或者等于0
				if(null == cmEmployee.getFundSalary() || cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) != 1){
					List<InsuranceTypeDto> insuranceTypeDtos = cmEmployeeService.queryAccountInsuranceListByAccountId(scheme.getFundAccountId(), "", spsTask.getBeginDate(), "Y");
					for(InsuranceTypeDto dto : insuranceTypeDtos){
		                if(fundSalary.compareTo(BigDecimal.ZERO) == 0){
		                	fundSalary = dto.getCorpBaseLower();
		                }else if(fundSalary.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
		                	fundSalary = dto.getCorpBaseLower();
		                }
		            }
				}
				if(null != cmEmployee.getFundSalary() 
						&& cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) != 1){
					cmEmployee.setFundSalary(fundSalary);
				}
			}
			cmEmployee.setSchemeId(scheme.getSchemeId());
			cmEmployee.setDr(0);
			cmEmployee.setInsuranceState("OFF");
			cmEmployee.setIsQuit(0);
			cmEmployee.setFundState("OFF");
			cmEmployee.setEntryTime(new Date());
			cmEmployee.setIdentityType(cmEmployeeAudit.getIdentityType());
			SysUploadfile sysUploadfile = new SysUploadfile();
			sysUploadfile.setId(cmEmployeeAudit.getHeadPhoto());
			sysUploadfile = sysUploadfileService.findByPK(sysUploadfile);
			if(null != sysUploadfile){
				cmEmployee.setHeadPhoto(sysUploadfileService.getPreviewUrl(sysUploadfile.getFilename()));
			}
			// 保存员工
			if(null != cmEmployee.getEmpId()){
				cmEmployeeService.update(cti, cmEmployee);
			}else{
				cmEmployeeService.save(cti, cmEmployee);
			}
			SpsSchemeEmp vo = new SpsSchemeEmp();
			vo.setEmpId(cmEmployee.getEmpId());
			vo.setState("USE");
			vo.setDr(0);
			List<SpsSchemeEmp> spsSchemeEmps = spsSchemeEmpService.findAll(vo);
			if(spsSchemeEmps.size() == 0){
				vo.setSchemeId(scheme.getSchemeId());
				// 保存员工与方案的关系
				spsSchemeEmpService.save(cti, vo);
			}else{
				for (int i = 0; i < spsSchemeEmps.size(); i++) {
					SpsSchemeEmp obj = spsSchemeEmps.get(i);
					obj.setSchemeId(scheme.getSchemeId());
					spsSchemeEmpService.update(cti, obj);
				}
			}

			spsTask.setCpId(cti.getOrgId());
			spsTask.setSpId(scheme.getSpId());
			if(0 == cmEmployeeAudit.getIsWorked()){//是否在本地工作过 0为在此地工作过，1为第一份工作
				spsTask.setBstypeId(3);// 转入
			}else{
				spsTask.setBstypeId(2);// 新参
			}
			JSONObject jsonObject = JSONObject.parseObject(cmEmployeeAudit.getJson());
			jsonObject.put("identity_type", "IDCard");
			jsonObject.put("identity_code", cmEmployee.getIdentityCode());
			jsonObject.put("name", cmEmployee.getName());
			jsonObject.put("mobile", cmEmployee.getMobile());
			jsonObject.put("beginDate", spsTask.getBeginDate());
			jsonObject.put("insuranceLiveType", cmEmployee.getInsuranceLivingType());
			jsonObject.put("insuranceSalary", cmEmployee.getInsuranceSalary());
			// 默认等待申报
			spsTask.setType(TaskStateFiled.TODO_WAITING_APPLICATION.getTaskType());
			spsTask.setStateFiledId(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId());
			spsTask.setStateFiledName(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledName());
			spsTask.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			spsTask.setName(cmEmployee.getName());
			spsTask.setMobile(cmEmployee.getMobile());
			spsTask.setIdentityCode(cmEmployee.getIdentityCode());
			spsTask.setDr(0);
			spsTask.setEmpId(cmEmployee.getEmpId());
			spsTask.setSchemeId(scheme.getSchemeId());
			// 是否缴纳社保 0：未缴纳；1：缴纳
			if(isIns.equals(1)){
				// 保存任务单 如果社保基数大于0
				if(cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) == 1 && null != scheme.getInsuranceAccountId()){
					// 社保类型
					List<InsuranceTypeDto> insurance = findInsuranceType(scheme.getInsuranceAccountId(),cmEmployee.getBeginDate(),cmEmployee.getInsuranceLivingType());
					if(insurance.size() > 0){
						jsonObject.put("insurance", insurance);
						spsTask.setJson(jsonObject.toJSONString());
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						corpTaskAspectService.saveTask(cti, spsTask, JSON.parseObject(spsTask.getJson(),ref), new Result());
					}
				}
			}
			// 是否缴纳公积金 0：未缴纳；1：缴纳
			if(isFund.equals(1)){
				// 保存任务单 如果公积金基数大于0
				if(cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) == 1 && null != scheme.getFundAccountId()){
					List<InsuranceTypeDto> insurance = findInsuranceType(scheme.getFundAccountId(),cmEmployee.getBeginDate(),null);
					if(insurance.size() > 0){
						spsTask.setTaskId(null);
						spsTask.setBstypeId(10);
						// 公积金
						jsonObject.put("fundSalary", cmEmployee.getFundSalary());
						jsonObject.put("insurance", insurance);
						jsonObject.remove("insuranceLiveType");
						jsonObject.remove("hospital");
						spsTask.setJson(jsonObject.toJSONString());
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						corpTaskAspectService.saveTask(cti, spsTask, JSON.parseObject(spsTask.getJson(),ref), new Result());
					}
				}
			}
			// 更新临时表为审核通过
			cmEmployeeAudit = new CmEmployeeAudit();
			cmEmployeeAudit.setEmpId(oldEmpId);
			cmEmployeeAudit.setState(3);
			cmEmployeeAudit.setIdentityCode(cmEmployee.getIdentityCode());
			cmEmployeeAudit.setMobile(cmEmployee.getMobile());
			cmEmployeeAudit.setInsuranceLivingType(cmEmployee.getInsuranceLivingType());
			cmEmployeeAudit.setInsuranceFundDate(spsTask.getBeginDate());
			if (isIns.equals(1)) {
				cmEmployeeAudit.setInsuranceSalary(cmEmployee.getInsuranceSalary());
			}
			if (isFund.equals(1)) {
				cmEmployeeAudit.setFundSalary(cmEmployee.getFundSalary());
			}
			cmEmployeeAudit.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			cmEmployeeAuditService.update(cti, cmEmployeeAudit);
		}else{
			spsTask = new SpsTask();
			spsTask.setTaskId(cmEmployee.getEmpId());
			spsTask = spsTaskService.findByPK(spsTask);
			BdBsareatype bdBsareatype = bdBsareatypeService.findBdBstypeByCityAndType(spsTask.getAreaId(), spsTask.getBstypeId());
			// 当前业务类型配置流程
			BdBsareatypeProcess bdBsareatypeProcess = new BdBsareatypeProcess();
			bdBsareatypeProcess.setBdBsareatypeId(bdBsareatype.getId());
			List<BdBsareatypeProcess> list = bdBsareatypeProcessService.findAll(bdBsareatypeProcess);
			for (int i = 0; i < list.size(); i++) {
				if(spsTask.getStateFiledId().equals(list.get(i).getSysDictitemId())){
					if(i < list.size()-1){
						SysDictitem sysDictitem = sysDictitemService.findByPK(list.get(i+1).getSysDictitemId());
						if(sysDictitem.getId().equals(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId())){
							spsTask.setType(TaskStateFiled.COMPLETED_APPLICATION.getTaskType());
						}
						spsTask.setStateFiledId(sysDictitem.getId());
						spsTask.setStateFiledName(sysDictitem.getName());
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						corpTaskAspectService.saveTask(cti, spsTask, JSON.parseObject(spsTask.getJson(),ref), new Result());
					}
					break;
				}
			}
		}
		// 更新消息为已处理
		sysMessage.setState("DONE");
		sysMessageDao.update(cti, sysMessage);
	}
	
	/**
	 * 获取社保公积金类型
	 *  @param accountId
	 *  @return 
	 *	@return 			: List<InsuranceTypeDto> 
	 *  @createDate  	: 2017年3月20日 下午4:13:52
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月20日 下午4:13:52
	 *  @updateAuthor  :
	 */
	private List<InsuranceTypeDto> findInsuranceType(Integer accountId,String beginDate,String insuranceLivingType) {
		List<InsuranceTypeDto> list = new ArrayList<InsuranceTypeDto>();
		InsuranceTypeDto insuranceTypeDto = null;
		SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
		spsAccountRatio.setAccId(accountId);
		spsAccountRatio.setIsdefaultEq("Y");
		spsAccountRatio.setLivingTypeEq(insuranceLivingType);
		List<SpsAccountRatio> spsAccountRatioList = spsAccountRatioService.findAll(spsAccountRatio);
		for(SpsAccountRatio r : spsAccountRatioList){
			insuranceTypeDto = new InsuranceTypeDto();
			BsArearatio ratio = bsArearatioService.findByPK(r.getRatioId());
			if(null != ratio && null != ratio.getBsArearatiovers()){
				BsArearatiover ver = bsArearatioService.getVerByPeriod(beginDate, ratio);
				if(null != ver){
					insuranceTypeDto.setCompanyRatio(ver.getCorpRatio());//公司缴费比例
				    insuranceTypeDto.setInsuranceName(bdInsuranceService.findByPK(ratio.getInsuranceId()).getName());//险种名称
				    insuranceTypeDto.setInsuranceTypeId(ratio.getInsuranceId());//险种ID
				    insuranceTypeDto.setPersonalRatio(ver.getPsnRatio());//个人缴费比例
				    insuranceTypeDto.setRatioId(r.getRatioId());//险种比例
				    insuranceTypeDto.setBeginDate(beginDate);
				    list.add(insuranceTypeDto);
				}
			}
		}
		return list;
	}

	/**
	 * 获取未读消息通知数
	 *  @param cti
	 *  @return 
	 *  @createDate  	: 2017年3月21日 上午10:07:34
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午10:07:34
	 *  @updateAuthor  	:
	 */
	@Override
	public Integer findMessageNumber(ContextInfo cti) {
		SysMessage vo = new SysMessage();
    	vo.setTodoUser(cti.getUserId());
		vo.setTodoUserType(cti.getUserType());
		vo.setTodoOrg(cti.getOrgId());
		vo.setAuthority(cti.getAuthority());
		return sysMessageDao.findMessageNumber(vo);
	}

	/**
	 * 更新消息状态
	 *  @param cti 
	 *  @createDate  	: 2017年3月21日 上午10:26:02
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午10:26:02
	 *  @updateAuthor  	:
	 */
	@Override
	public void updateMessageState(ContextInfo cti) {
		SysMessage vo = new SysMessage();
    	vo.setTodoUser(cti.getUserId());
		vo.setTodoUserType(cti.getUserType());
		vo.setTodoOrg(cti.getOrgId());
		vo.setIsRead("READ");
		vo.setAuthority(cti.getAuthority());
		sysMessageDao.updateMessageState(cti,vo);
	}
	
	/**
	 * 设置一个信息为已读
	 *  @param cti 
	 *  @createDate  	: 2017年3月21日 上午10:26:02
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午10:26:02
	 *  @updateAuthor  	:
	 */
	@Override
	public void updateOneMessageState(ContextInfo cti,Integer msgId) {
		SysMessage vo = new SysMessage();
    	vo.setMessageId(msgId);
		vo.setIsRead("READ");
		vo.setTodoOrg(cti.getOrgId());
		sysMessageDao.update(cti,vo);
	}

	/**
	 * @Title: 更新消息状态， 将该消息之前的同类消息设置为已读
	 * @param
	 * @return
	 * @createDate 2017/6/22 17:07
	 * @Auther:zhanghongjie【hongjievip6688@163.com】
	 * @version        : v1.0
	 * @updateDate    	:
	 * @updateAuthor  :
	*/
	@Override
	public Integer updateMessageState(SysMessage vo, Integer bstypeId,Integer empId) {

		BdBstype bstype = new BdBstype();
		bstype.setBstypeId(bstypeId);
		bstype=bdBstypeService.findBdBstypeByKey(bstype);
		if (bstype!=null&&bstype.getPatrentTypeId()!=-1){
			bstypeId=bstype.getPatrentTypeId();
		}
		return sysMessageDao.updateMessageStateByMessageId(vo,bstypeId,empId);
	}

	@Override
	public List<SysMessage> getMessgeByTodoUser(SysMessage vo) {
		return sysMessageDao.getMessgeByTodoUser(vo);
	}

	/**
	 * 获取系统消息
	 *  @param cti
	 *  @param pi
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年3月21日 上午11:33:26
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午11:33:26
	 *  @updateAuthor  	:
	 */
	@Override
	public PageModel findMessageSysDetails(ContextInfo cti, PageInfo pi, SysMessage vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysMessageDao.findMessageSysDetailsCount(vo);
		pm.setTotal(total);
		List<SysMessage> datas = sysMessageDao.findMessageSysDetailsList(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		if(datas.size() > 0){
			SysMessage sysMessage = new SysMessage();
			sysMessage.setMessageId(datas.get(0).getMessageId());
			sysMessage.setIsRead("READ");
			sysMessageDao.update(cti, sysMessage);
		}
		return pm;
	}

	/**
	 * 查询是否弹出消息框
	 *  @param cti
	 *  @return 
	 *  @createDate  	: 2017年4月17日 上午11:57:40
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月17日 上午11:57:40
	 *  @updateAuthor  	:
	 */
	@Override
	public Integer findIsAlert(ContextInfo cti) {
		Integer isAlert = 0;
		BsSysStateReport bsSysStateReport = new BsSysStateReport();
		bsSysStateReport.setCpId(cti.getOrgId());
		bsSysStateReport.setDr(0);
		bsSysStateReport.setOwnerType("CUSTOMER");
		bsSysStateReport.setAttributeName("JIONUSERMESSAGE");
		bsSysStateReport.setAuthority(cti.getAuthority());
		List<BsSysStateReport> list = bsSysStateReportDao.findIsAlert(bsSysStateReport);
		if(null == list || list.size() == 0){
			return isAlert;
		}
		for(BsSysStateReport stateReport : list){
			if("1".equals(stateReport.getAttributeValue())){
				if(0 == isAlert){
					isAlert = 1;
				}
				// 更新消息已读
				BsSysStateReport obj = new BsSysStateReport();
				obj.setId(stateReport.getId());
				obj.setAttributeValue("0");
				bsSysStateReportDao.updateVersionMessage(cti, obj);
			}
		}
		return isAlert;
	}

	/**
	 * 查询用户办理业务详细信息
	 *  @param cti
	 *  @param vo 
	 *  @createDate  	: 2017年4月25日 上午10:01:36
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月25日 上午10:01:36
	 *  @updateAuthor  	:
	 */
	@Override
	public UserBusinessVo findMessageUserBusinessDetails(ContextInfo cti, SysMessage vo) {
		SpsTask spsTask = new SpsTask();
		spsTask.setTaskId(vo.getDataId());
		// 获取任务单
		spsTask = spsTaskService.findByPK(spsTask);
		UserBusinessVo messageVo = null;
		if(null != spsTask){
			messageVo = new UserBusinessVo();
			vo = sysMessageDao.findByPK(vo);
			messageVo.setState(vo.getState());
			messageVo.setMsgId(vo.getMessageId());
			messageVo.setTitle(vo.getTitle());
			BdBstype bdBstype = bdBstypeService.findByPK(spsTask.getBstypeId());
			messageVo.setBsTypeName(bdBstype.getName());
			// 获取人员信息
			CmEmployee cmEmployee = cmEmployeeService.findByPk(spsTask.getEmpId());
			messageVo.setEmpId(vo.getDataId());
			messageVo.setUserName(cmEmployee.getName());
			messageVo.setCode(cmEmployee.getIdentityCode());
			// 获取城市
			BsArea bsArea = new BsArea();
			bsArea.setAreaId(spsTask.getAreaId());
			bsArea = bsAreaService.findByPK(bsArea);
			bsArea.setAreaId(bsArea.getBelongCity());
			bsArea = bsAreaService.findByPK(bsArea);
			messageVo.setCtiyName(bsArea.getName());
			// 获取户口性质
			SysDictitem sysDictitem = new SysDictitem();
			sysDictitem.setAreaId(bsArea.getAreaId());
			sysDictitem.setCodeEq(cmEmployee.getInsuranceLivingType());
			sysDictitem.setDictionary(93);
			List<SysDictitem> sysDictitems = sysDictitemService.findAll(sysDictitem);
			if(sysDictitems.size() > 0){
				messageVo.setTypeName(sysDictitems.get(0).getName());
			}
			// 获取申请时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(spsTask.getCreateDt());
			messageVo.setModifyDt(dateString);
			Map<String,Object> maps = (Map<String,Object>)JSONObject.parseObject(spsTask.getJson());  
			// 其他数据字段信息
			List<Map<String,Object>> otherParm = new ArrayList<Map<String,Object>>();
			// 文件信息
			List<Map<String,Object>> fileParm = new ArrayList<Map<String,Object>>();
			Map<String,Object> parm = null;
			Map<String,Object> parmFile = null;
			String mobile = "";
	        for (Object map : maps.entrySet()){
	        	String key = (String) ((Map.Entry)map).getKey();
	        	if(key.equals("name") || key.equals("empId")
	        		|| key.equals("identity_code") || key.equals("identity_type") || key.equals("identity_type_name") 
	        		|| key.equals("insuranceLiveType_name") || key.equals("insuranceLiveType") 
	        		|| key.equals("insurance") || (key.endsWith("_name") && !key.equals("occupational_disease_name"))){
	        		continue;
	        	}
	        	if(key.equals("mobile")){
	        		mobile = (String) ((Map.Entry)map).getValue();
	        		continue;
	        	}
	        	if(key.equals("hospital")){
	        		List<Map<String,Object>> values = null;
	        		boolean isString = ((Map.Entry)map).getValue() instanceof String; 
	        		if(isString){
	        			String valueString = (String) ((Map.Entry)map).getValue();
	        			if(StringUtils.isNotEmpty(valueString)){
	        				values = (List<Map<String,Object>>)JSONArray.parse(valueString);
	        			}
	        		}else{
	        			values = (List<Map<String,Object>>) ((Map.Entry)map).getValue();
	        		}
	        		if(null != values){
	        			for (int i = 0; i < values.size(); i++) {
		        			parm = new HashMap<>();
		        			Map<String,Object> v = values.get(i);
		        			parm.put("value", v.get("name").toString());
		        			parm.put("name", "医院"+(i+1));
		        			otherParm.add(parm);
						}
	        		}
	        	}else{
	        		parm = new HashMap<>();
		        	BdBusinessfield bdBusinessfield = bdBusinessfieldService.findNameByCode(key);
		        	if(null != bdBusinessfield){
		        		if(key.equals("insuranceSalary") || key.equals("fundSalary")){
		        			BigDecimal value = new BigDecimal(String.valueOf(((Map.Entry)map).getValue()));;
		        			parm.put("name", bdBusinessfield.getName());
		        			parm.put("value", value);
		        			otherParm.add(parm);
			        	}else{
			        		String value = (String) ((Map.Entry)map).getValue();
			        		if(!"".equals(value) && null != value){
			        			if(!"FILE".equals(bdBusinessfield.getType())){
					        		parm.put("name", bdBusinessfield.getName());
					        		if("PULL".equals(bdBusinessfield.getType())){
					        			value = (String) maps.get(key+"_name");
					        		}
						        	parm.put("value", value);
						        	otherParm.add(parm);
				        		}else{
				        			parmFile = new HashMap<>();
				        			parmFile.put("name", bdBusinessfield.getName());
				        			parmFile.put("value", value);
				        			fileParm.add(parmFile);
				        		}
			        		}
			        	}
		        	}
	        	}
	        }
	        if("".equals(mobile)){
	        	messageVo.setMobile(cmEmployee.getMobile());
	        }else{
	        	messageVo.setMobile(mobile);
	        }
	        messageVo.setOtherParm(otherParm);
	        messageVo.setFile(fileParm);
		}
		if(null != vo.getMessageId() && !"".equals(vo.getMessageId())){
			SysMessage sysMessage = new SysMessage();
			sysMessage.setMessageId(vo.getMessageId());
			sysMessage = sysMessageDao.findByPK(sysMessage);
			if("UNREAD".equals(sysMessage.getIsRead())){
				sysMessage.setTodoUser(cti.getUserId());
				sysMessage.setTodoUserType(cti.getUserType());
				sysMessage.setTodoOrg(cti.getOrgId());
				sysMessage.setDataId(vo.getDataId());
				sysMessage.setMessageId(vo.getMessageId());
				sysMessage.setIsRead("READ");
				sysMessage.setAuthority(cti.getAuthority());
				sysMessageDao.updateMessageState(cti,sysMessage);
			}
		}
		return messageVo;
	}

	/**
	 * 查询消息待办提醒列表
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年8月31日 下午2:26:28
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月31日 下午2:26:28
	 *  @updateAuthor  	:
	 */
	@Override
	public List<SysMessage> findTodoRemindList(SysMessage vo) {
		return sysMessageDao.findTodoRemindList(vo);
	}

	@Override
	public SysMessage findByPk(SysMessage vo) {
		return sysMessageDao.findByPK(vo);
	}

	/**
	 * 消息推送
	 *  @param list
	 *  @param code 社保saas：xfs_ss，工资条：xfs_pay
	 *  @return 
	 *  @createDate  	: 2017年10月11日 下午3:16:55
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年10月11日 下午3:16:55
	 *  @updateAuthor  	:
	 */
	@Override
	public String sendMessage(List<WxMessage> list,String code) {
		String uuid = UUIDUtil.randomUUID();
		RedisUtil.set(uuid, list, 10000);
		Map<String, String> map = new HashMap<>();
		map.put("code", code);
		map.put("token", uuid);
		return HttpClientUtil.doPost(Config.getProperty("xfs_core_host_url")+"/sendMsg", map);
	}
	
}
