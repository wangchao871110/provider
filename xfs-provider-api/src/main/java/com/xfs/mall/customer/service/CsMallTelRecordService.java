package com.xfs.mall.customer.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.model.CsMallTelRecord;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 5:56
 * @version  : V1.0
 */
public interface CsMallTelRecordService {
	
	public int save(ContextInfo cti, CsMallTelRecord vo);
	public int insert(ContextInfo cti, CsMallTelRecord vo);
	public int remove(ContextInfo cti, CsMallTelRecord vo);
	public int update(ContextInfo cti, CsMallTelRecord vo);
	public PageModel findPage(PageInfo pi, CsMallTelRecord vo);
	public List<CsMallTelRecord> findAll(CsMallTelRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:29:12
	
	
	
}
