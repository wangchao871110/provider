package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.BsChannelOrg;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsChannelOrgService
 * @author:wangchao
 * @date:2016/07/29 10:55:28
 */
public interface BsChannelOrgService {
	
	public int save(ContextInfo cti, BsChannelOrg vo);
	public int insert(ContextInfo cti, BsChannelOrg vo);
	public int remove(ContextInfo cti, BsChannelOrg vo);
	public int update(ContextInfo cti, BsChannelOrg vo);
	public PageModel findPage(PageInfo pi, BsChannelOrg vo);
	public List<BsChannelOrg> findAll(BsChannelOrg vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/29 10:55:28
	
	
	
}
