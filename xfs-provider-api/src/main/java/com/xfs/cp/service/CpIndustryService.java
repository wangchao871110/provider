package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpIndustry;

/**
 * CpIndustryService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:20
 */
public interface CpIndustryService {
	
	public int save(ContextInfo cti, CpIndustry vo);
	public int insert(ContextInfo cti, CpIndustry vo);
	public int remove(ContextInfo cti, CpIndustry vo);
	public int update(ContextInfo cti, CpIndustry vo);
	public PageModel findPage(PageInfo pi, CpIndustry vo);
	public List<CpIndustry> findAll(CpIndustry vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	/**
	 * 
	 * @method comments: 行业认证
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午3:52:51 
	 * @param request
	 * @param cpIndustry
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午3:52:51 wangchao 创建
	 *
	 */
	public Result industry(ContextInfo user, CpIndustry cpIndustry, Result result);
	
}
