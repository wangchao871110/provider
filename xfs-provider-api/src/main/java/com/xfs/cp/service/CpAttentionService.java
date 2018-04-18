package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpAttention;

/**
 * CpAttentionService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:17
 */
public interface CpAttentionService {
	
	public int save(ContextInfo cti, CpAttention vo);
	public int insert(ContextInfo cti, CpAttention vo);
	public int remove(ContextInfo cti, CpAttention vo);
	public int update(ContextInfo cti, CpAttention vo);
	public PageModel findPage(PageInfo pi, CpAttention vo);
	public List<CpAttention> findAll(CpAttention vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:17
	
	/**
	 * 
	 * @method comments: 关注
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午1:34:11 
	 * @param request
	 * @param cpAttention
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午1:34:11 wangchao 创建
	 *
	 */
	public Result attention(ContextInfo user, CpAttention cpAttention, Result result);
	
}
