package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpBusiness;

/**
 * CpBusinessService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:18
 */
public interface CpBusinessService {
	
	public int save(ContextInfo cti, CpBusiness vo);
	public int insert(ContextInfo cti, CpBusiness vo);
	public int remove(ContextInfo cti, CpBusiness vo);
	public int update(ContextInfo cti, CpBusiness vo);
	public PageModel findPage(PageInfo pi, CpBusiness vo);
	public List<CpBusiness> findAll(CpBusiness vo);
	
	/**
	 * 
	 * @method comments: 逻辑删除
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 下午8:19:41 
	 * @param cpBusiness
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午8:19:41 baoyu 创建
	 *
	 */
	public int updateBySpId(ContextInfo cti,CpBusiness cpBusiness);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	
	
	
}
