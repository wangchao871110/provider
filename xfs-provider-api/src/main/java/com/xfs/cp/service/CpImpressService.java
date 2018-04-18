package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpImpress;

/**
 * CpImpressService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:19
 */
public interface CpImpressService {
	
	public int save(ContextInfo cti, CpImpress vo);
	public int insert(ContextInfo cti, CpImpress vo);
	public int remove(ContextInfo cti, CpImpress vo);
	public int update(ContextInfo cti, CpImpress vo);
	public PageModel findPage(PageInfo pi, CpImpress vo);
	public List<CpImpress> findAll(CpImpress vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:19
	/**
	 * 
	 * @method comments: 添加机构印象
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午4:09:33 
	 * @param sysUser
	 * @param cpImpress
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午4:09:33 wangchao 创建
	 *
	 */
	public Result saveImpress(ContextInfo user, CpImpress cpImpress, Result result);
	
}
