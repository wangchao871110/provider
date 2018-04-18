package com.xfs.mall.customer.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.model.CsMallCustomerAssess;
import com.xfs.mall.dto.CsMallCustomerAssessDTO;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 5:38
 * @version  : V1.0
 */
public interface CsMallCustomerAssessService {
	
	public int save(ContextInfo cti, CsMallCustomerAssess vo);
	public int insert(ContextInfo cti, CsMallCustomerAssess vo);
	public int remove(ContextInfo cti, CsMallCustomerAssess vo);
	public int update(ContextInfo cti, CsMallCustomerAssess vo);
	public PageModel findPage(PageInfo pi, CsMallCustomerAssess vo);
	public List<CsMallCustomerAssess> findAll(CsMallCustomerAssess vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:55:57
	
	/**
	 *  以CsMallCustomerAssessDTO为参数查询客服评价列表
	 *  @param   dto
	 * @return    : PageModel
	 *  @createDate   : 2016/11/9 0009 上午 10:59
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 上午 10:59
	 *  @updateAuthor  :
	 */
	public PageModel findPage(PageInfo pi, CsMallCustomerAssessDTO dto);
	
}
