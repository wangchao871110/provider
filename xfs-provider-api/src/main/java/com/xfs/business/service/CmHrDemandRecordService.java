package com.xfs.business.service;

import com.xfs.business.model.CmHrDemandRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

import java.util.List;
import java.util.Map;

/**
 * 服务包 需求记录表
 * CmHrDemandRecordService
 * @author:yangfw<yangfw@xinfushe.com>
 * @date:2016/11/30 12:00:46
 */
public interface CmHrDemandRecordService {
	
	public int save(ContextInfo cti, CmHrDemandRecord vo);
	public int insert(ContextInfo cti,CmHrDemandRecord vo);
	public int remove(ContextInfo cti,CmHrDemandRecord vo);
	public int update(ContextInfo cti,CmHrDemandRecord vo);
	public PageModel findPage(PageInfo pi,CmHrDemandRecord vo);
	public List<CmHrDemandRecord> findAll(CmHrDemandRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/30 12:00:46
	
	public PageModel findPageAll(PageInfo pi, Map<String, Object> map);

	public int queryCountByTenantId(ContextInfo cti,String tenantId);
}
