package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpAccount;


/**
 * CpAccountService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/11 21:59:58
 */
public interface CpAccountService {
	
	public int save(ContextInfo cti, CpAccount vo);
	public int insert(ContextInfo cti, CpAccount vo);
	public int remove(ContextInfo cti, CpAccount vo);
	public int update(ContextInfo cti, CpAccount vo);
	public PageModel findPage(PageInfo pi, CpAccount vo);
	public List<CpAccount> findAll(CpAccount vo);
	
	/**
	 * 
	 * @method comments: 获取账户信息
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月11日 下午10:04:22 
	 * @param cpAccount
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午10:04:22 baoyu 创建
	 *
	 */
	public CpAccount getAccountById(CpAccount cpAccount);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/11 21:59:58
	
	
	
}
