package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.acc.dto.AreaSocialRuleVo;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsAccountRatio;

/**
 * SpsAccountRatioService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:08:03
 */
public interface SpsAccountRatioService {
	
	public int save(ContextInfo cti, SpsAccountRatio vo);
	public int insert(ContextInfo cti, SpsAccountRatio vo);
	public int remove(ContextInfo cti, SpsAccountRatio vo);
	public int update(ContextInfo cti, SpsAccountRatio vo);
	public PageModel findPage(PageInfo pi, SpsAccountRatio vo);
	public List<SpsAccountRatio> findAll(SpsAccountRatio vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:08:03
	public List<Map<String, Object>> findRatiosByAccountId(Map<String, Object> paraMap);

	public List<SpsAccountRatio> findAllList(SpsAccountRatio vo);
	
	/**
	 * 获取社保规则
	 *  @param cti
	 *  @param areaId
	 *  @param schemeId
	 *  @return 
	 *	@return 			: AreaSocialRuleVo 
	 *  @createDate  	: 2017年4月5日 上午11:24:29
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月5日 上午11:24:29
	 *  @updateAuthor  :
	 */
	public AreaSocialRuleVo findSocialRule(ContextInfo cti, Integer areaId, Integer schemeId);
	
	/**
	 * 根据大库ID删除比例
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2017年4月9日 下午4:50:37
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月9日 下午4:50:37
	 *  @updateAuthor  :
	 */
	public int removeByAccId(ContextInfo cti, SpsAccountRatio vo);

	/**
	 * 更新库的默认比例
	 * @param cti
	 * @param accId
	 * @param insuranceId
	 * @param areaId
	 * @param corpRatio
	 * @param empRatio
	 * @return
	 */
	public int updateAccountDefaultRatio(ContextInfo cti, Integer accId,Integer insuranceId,Integer areaId,String corpRatio,String empRatio);
	
}
