package com.xfs.sp.service;

import java.util.List;

import com.xfs.acc.dto.ApplyMessage;
import com.xfs.acc.dto.SchemeAccountRatio;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsScheme;

import javax.servlet.http.HttpServletRequest;

/**
 * SpsAccountService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/26 17:04:11
 */
public interface SpsAccountService {
	
	public int save(ContextInfo cti, SpsAccount vo);
	public int insert(ContextInfo cti, SpsAccount vo);
	public int remove(ContextInfo cti, SpsAccount vo);
	public int update(ContextInfo cti, SpsAccount vo);
	public PageModel findPage(PageInfo pi, SpsAccount vo);
	public List<SpsAccount> findAll(SpsAccount vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/26 17:04:11
	

	public List<SpsAccount> qrySpsAccountByIds(Integer spid, List<Integer> ids);

	/**
	 * findByPK
	 *
	 * @param vo
	 * @return
	 */
	public SpsAccount findByPK(SpsAccount vo);

	/**
	 * 通过spid查询库列表
	 *
	 * @param vo
	 * @return
	 */
	PageModel findSpsAccountBySpid(PageInfo pi, SpsAccount vo);

	/**
	 * 通过库名称查询 -- 库名称的唯一性
	 *
	 * @param vo
	 * @return
	 */
	int findSpsAccountCountByName(SpsAccount vo);
	public List<SpsAccount> findAllWithParent(SpsAccount vo);
	
	/**
	 * 保存库
	 *
	 * @author lifq
	 *
	 * 2016年9月27日  上午11:08:18
	 */
	public Result saveAccount(ContextInfo cti, Integer spId, SpsAccount account, String account_ratio[]);

	/**
	 * 通过条件查询出spsAccountList(按照GROUP BY area_id,insurance_fund_type分组得出最小的accID的spsAccount)
	 * @return
	 */
	public List<SpsAccount> findSpsAccountListByConditions(SpsAccount spsAccount);

	/**
	 * 根据城市获取自动网上申报信息
	 *  @param areaIds
	 *  @return
	 *	@return 			: List<ApplyMessageVo>
	 *  @createDate  	: 2017年4月6日 下午2:10:14
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月6日 下午2:10:14
	 *  @updateAuthor  :
	 */
	public List<ApplyMessage> findApplyMessage(ContextInfo cti, CmCorp cmCorp , String[] areaIds, String[] taskIds,String[] schemeIds);

	/**
	 * 保存社保账号信息
	 * @param cti
	 * @param applyMessage
	 */
	public void saveAccountInfo(ContextInfo cti,ApplyMessage applyMessage);

	/**
	 * 保存方案
	 *  @param cti
	 *	@return 			: void
	 *  @createDate  	: 2017年4月6日 下午3:38:07
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月6日 下午3:38:07
	 *  @updateAuthor  :
	 */
	public SpsScheme saveAccount(ContextInfo cti, SchemeAccountRatio accountRatio);


	/**
	 * 获取已经登录T的城市列表
	 * @param cti
	 * @param cmCorp
	 * @param taskIds
	 * @param areaIds
	 * @return
	 */
	public List<ApplyMessage> filterNoLoginAreas(ContextInfo cti, CmCorp cmCorp, String[] taskIds, String[] areaIds, String[] schemeIds);

	/**
	 *  创建默认方案
	 *  @param   cti, areaId
	 *	@return 			: com.xfs.sp.model.SpsScheme
	 *  @createDate  	: 2017-05-18 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-18 14:52
	 *  @updateAuthor  :
	 */
	public SpsScheme createDefaultScheme(ContextInfo cti,Integer areaId);
}
