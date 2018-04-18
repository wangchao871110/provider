package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpCmRelation;

/**
 * @author xiyanzhang
 * @Email: zhangxiyan@xinfushe.com
 * @version 创建时间：2016年11月11日 下午12:05:31
 */
public interface SpCmRelationService {

	public int save(ContextInfo cti, SpCmRelation vo);

	public int insert(ContextInfo cti, SpCmRelation vo);

	public int remove(ContextInfo cti, SpCmRelation vo);

	public int update(ContextInfo cti, SpCmRelation vo);

	public PageModel findPage(PageInfo pi, SpCmRelation vo);

	public List<SpCmRelation> findAll(SpCmRelation vo);

	/**
	 * 根据服务商id和区域编号获取企业列表
	 *
	 * @param spId
	 * @param areaCode
	 * @return
	 */
	List<Map<String, Object>> FindCorpsBySpId(Integer spId, String areaCode);

	public SpCmRelation findBySpIdAndCpId(Integer spid, Integer cpid);

	public List<SpCmRelation> queryAllCorpsByBillDay();

	/**
	 * 通过企业id获取关系数据列表
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> freeFindByCpId(SpCmRelation vo);
}
