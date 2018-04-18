package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.model.BdEvaluation;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


/**
 * 评价服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 12:00
 * @version 	: V1.0
 */
public interface BdEvaluationService {
	
	public int save(ContextInfo cti, BdEvaluation vo);
	public int insert(ContextInfo cti, BdEvaluation vo);
	public int remove(ContextInfo cti, BdEvaluation vo);
	public int update(ContextInfo cti, BdEvaluation vo);
	public PageModel findPage(PageInfo pi, BdEvaluation vo);
	public List<BdEvaluation> findAll(BdEvaluation vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/05 10:52:21

	/**
	 *  获取评价信息
	 *  @param   bdEvaluation
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-11 11:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 11:59
	 *  @updateAuthor  :
	 */
	public Map<String, Object> getEvaluationById(BdEvaluation bdEvaluation);
	
}
