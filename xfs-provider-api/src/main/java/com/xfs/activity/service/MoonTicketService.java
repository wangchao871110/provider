package com.xfs.activity.service;

import java.util.List;

import com.xfs.activity.model.MoonTicket;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
/**
 * 
* @ClassName: MoonTicketService 
* @Description: 中秋奖券
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:29:31 
*
 */
public interface MoonTicketService {
	
	public int save(ContextInfo cti, MoonTicket vo);
	public int insert(ContextInfo cti, MoonTicket vo);
	public int remove(ContextInfo cti, MoonTicket vo);
	public int update(ContextInfo cti, MoonTicket vo);
	public PageModel findPage(PageInfo pi, MoonTicket vo);
	public List<MoonTicket> findAll(MoonTicket vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21
	
	
	
}
