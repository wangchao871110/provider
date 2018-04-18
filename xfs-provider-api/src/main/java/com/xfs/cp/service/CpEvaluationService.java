package com.xfs.cp.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpEvaluation;

import java.util.List;

/**
 * CpEvaluationService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:21
 */
public interface CpEvaluationService {
	
	public int save(ContextInfo cti, CpEvaluation vo);
	public int insert(ContextInfo cti, CpEvaluation vo);
	public int remove(ContextInfo cti, CpEvaluation vo);
	public int update(ContextInfo cti, CpEvaluation vo);
	public PageModel findPage(PageInfo pi, CpEvaluation vo);
	public List<CpEvaluation> findAll(CpEvaluation vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:21
	/**
	 * 
	 * @method comments: 根据服务商ID获取用户点评和合作满意度
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午3:33:04 
	 * @param cpEvaluation
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午3:33:04 wangchao 创建
	 *
	 */
	public CpEvaluation findEvaluation(CpEvaluation cpEvaluation);
	/**
	 * 
	 * @method comments: 保存评价
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月19日 下午4:15:26 
	 * @param sysUser
	 * @param cpEvaluation
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月19日 下午4:15:26 wangchao 创建
	 *
	 */
	public Result saveEvaluation(ContextInfo sysUser, CpEvaluation cpEvaluation, Result result, String score);

	public PageModel queryVoucherList(PageInfo pi,CpEvaluation vo);

	
}
