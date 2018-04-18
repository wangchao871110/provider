package com.xfs.base.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsChannelDao;
import com.xfs.base.dao.BsChannelUserDao;
import com.xfs.base.dao.CsQuestionnaireDao;
import com.xfs.base.dao.CsRegistHistoryDao;
import com.xfs.base.model.BsChannel;
import com.xfs.base.model.BsChannelUser;
import com.xfs.base.model.CsQuestionnaire;
import com.xfs.base.model.CsRegistHistory;
//import com.xfs.bs.dao.CmCorpDao;
import com.xfs.bs.dto.QueryChannelCorpDto;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
//import com.xfs.bs.service.CmCorpService;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.service.CmCorpService;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;

/**
 * BsChannelServiceImpl 渠道服务业务实现类
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-11 15:18
 * @version 	: V1.0
 */
@Service
public class BsChannelServiceImpl implements BsChannelService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsChannelServiceImpl.class);
	
	@Autowired
	private BsChannelDao bsChannelDao;
	@Autowired
	private BsChannelUserDao bsChannelUserDao;
	@Autowired
	private CsRegistHistoryDao registHistoryDao;
	@Autowired
	private CmCorpService cmCorpService;
	@Autowired
	private CmCorpDao cmCorpDao;
	
	@Autowired
	private CsQuestionnaireDao csQuestionnaireDao;
	@Autowired
	private SysUserDao sysUserDAO;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	public int save(ContextInfo cti, BsChannel vo ){
		return bsChannelDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsChannel vo ){
		return bsChannelDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsChannel vo ){
		return bsChannelDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsChannel vo ){
		return bsChannelDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsChannel vo){
		
		PageModel pm = new PageModel(pi);
		Integer total = bsChannelDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsChannel> datas = bsChannelDao.freeFind(vo, pi.getOffset(), pi.getPagesize());
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsChannel> findAll(BsChannel vo){
		
		List<BsChannel> datas = bsChannelDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:54

    /**
     *  根据ID获取数据
     *  @param    ：  channel 渠道实体
     *  @return    :   BsChannel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public BsChannel findByPK(BsChannel channel) {

		return bsChannelDao.findByPK(channel);
	}

    /**
     *  获取区与组
     *  @param    ：  channel 渠道实体
     *  @return    :   BsChannel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public BsChannel findbyid(BsChannel channel){
		return bsChannelDao.findbyid(channel);
	}

    /**
     *  获取所有渠道列表
     *  @param    ：  channel 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public PageModel findAllPage(PageInfo pi, BsChannel channel) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsChannelDao.countFreeFind(channel);
		pm.setTotal(total);
		List<BsChannel> datas = bsChannelDao.freeFind(channel, pageIndex, pageSize," create_dt desc");
		CmCorp cmCorp = new CmCorp();
		cmCorp.setChannelCode(channel.getCode());
		List<CmCorp> corpList1 = cmCorpService.findAll(cmCorp);
		for(int i=0;i<datas.size();i++){
			int channelId = datas.get(i).getId();
			BsChannelUser channelUser = new BsChannelUser();
			channelUser.setChannelId(channelId);
			// 在职
			channelUser.setStatus("1");
			datas.get(i).setStartCount(bsChannelUserDao.freeFind(channelUser).size());
			// 离职
			channelUser.setStatus("0");
			datas.get(i).setStopCount(bsChannelUserDao.freeFind(channelUser).size());
			
			CsRegistHistory registHistory = new CsRegistHistory();
			String channelCode = datas.get(i).getCode();
			registHistory.setChannel(channelCode);
			registHistory.setAnswerDt(null);
			// 注册人数
			datas.get(i).setRegisterCount(registHistoryDao.findRegisterCount(registHistory));
			registHistory.setAnswerDt(new Date());
			// 回答问卷人数
			datas.get(i).setQuestionnaireCount(registHistoryDao.findRegisterCount(registHistory));
			//渠道内公司数
			CmCorp corp = new CmCorp();
			corp.setChannelCode(channelCode);
			List<CmCorp> corpList = cmCorpService.findAll(corp);
			datas.get(i).setOrgNum(corpList.size());
		}
		pm.setDatas(datas);
		return pm;
	}

    /**
     *  根据渠道跟企业查询调查问卷的公司信息
     *  @param    ：  map 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public PageModel findRegitCorpMsg(PageInfo pi, Map map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = registHistoryDao.findRegitCorpMsgCount(map);
		pm.setTotal(total);
		List<Map<String,Object>> datas = registHistoryDao.findRegitCorpMsg(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

    /**
     *  查找答案
     *  @param    ：  vo 答案实体
     *  @return    :   CsQuestionnaire
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public CsQuestionnaire findAnswer(CsQuestionnaire vo) {
		// TODO Auto-generated method stub
		List<CsQuestionnaire> csQuestionnaire = csQuestionnaireDao.freeFind(vo);
		return csQuestionnaire.get(0);
	}

    /**
     *  获取所有渠道列表
     *  @param    ：  channel 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public PageModel findCmCorpList(PageInfo pi, CmCorp corp) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmCorpDao.countAllOrgInChannel(corp);
		pm.setTotal(total);
		List<CmCorp> datas = cmCorpDao.getAllOrgInChannel(corp, pageIndex, pageSize," create_dt desc");

		pm.setDatas(datas);
		return pm;
	}

    /**
     *  创建用户渠道
     *  @param    ：  channel 渠道实体
     *  @return    :   Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Result createChannelUser(ContextInfo cti, BsChannel channel){
		Result result = new Result();
		SysUser sysUser = new SysUser();
		sysUser.setEmail(channel.getEmail());
		sysUser.setUserName(channel.getMobile());
		sysUser.setRealName(channel.getName());
		sysUser.setMobile(channel.getMobile());
		sysUser.setUserType("CHANNEL");
		sysUser.setEnabled(1);
		sysUser.setCreateBy(channel.getCreateBy() != null ? channel.getCreateBy().toString():null);
		sysUser.setCreateDt(new Date());
		sysUser.setOrgId(channel.getId());
		String password = "123456";//StringUtils.getRandomSixString();
		sysUser.setPassword(StringUtils.md5(password));
		sysUserDAO.insert(cti, sysUser);
		if(sysUser.getUserId() == null){
			log.info("自动添加渠道用户失败：" + channel.getMobile() );
			result.setSuccess(false).setError("创建失败");
			return result;
		}
		SysUserRole role = new SysUserRole();
		role.setRoleId(777);
		role.setUserId(sysUser.getUserId());
		sysUserRoleDao.insert(cti, role);
		if(role.getId() == null){
			log.info("自动添加渠道用户角色关联失败：" + channel.getMobile() );
			result.setSuccess(false).setError("自动添加渠道用户角色关联失败");
			return result;
		}
		boolean smsFlag = false;
		try {
			smsFlag = SmsUtil.sendVerificationCode(channel.getMobile(),
                    "【用友薪福社】您已注册渠道用户,用户名：" + channel.getMobile() + "，密码：" + password + ",请妥善保管,如不是本人操作,请忽略！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("自动添加渠道用户并发送短信：" + channel.getMobile() + ",发送状态:" + smsFlag);
		result.setSuccess(true);
		return result;
	}

    /**
     *  查询渠道商下企业
     *  @param    ：  channelCorpDto 渠道企业页面视图dto
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findChannelCorpPage(PageInfo pi, QueryChannelCorpDto channelCorpDto) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		if(StringUtils.isNotBlank(channelCorpDto.getLegalizeStatus())){
			if(channelCorpDto.getLegalizeStatus().equals("1")){
				channelCorpDto.setIsLegalizes(Arrays.asList(new String[]{"2"}));
			}else{
				channelCorpDto.setIsLegalizes(Arrays.asList(new String[]{"0","1","3"}));
			}
		}
		if(StringUtils.isNotBlank(channelCorpDto.getAnswerStatus())){
			if(channelCorpDto.getAnswerStatus().equals("1")){
				channelCorpDto.setIsAnswer(" is not null ");
			}else{
				channelCorpDto.setIsAnswer(" is null ");
			}
		}
		Integer total = bsChannelDao.queryPageByChannelCorpCount(channelCorpDto);
		pm.setTotal(total == null? 0: total);
		List<Map> datas = bsChannelDao.queryPageByChannelCorp(channelCorpDto, pageIndex, pageSize);

		pm.setDatas(datas);
		return pm;
	}

    /**
     *  查询渠道企业详情
     *  @param    ：  orgId 服务商id
     *  @param    ：  cpId   企业id
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Map queryChannelCorpByCpId(Integer orgId,Integer cpId){
		return bsChannelDao.queryChannelCorpByCpId(orgId,cpId);
	}
}
