package com.xfs.corp.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorpTraceRecord;

/**
 * CmCorpTraceRecordService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/25 16:51:17
 */
public interface CmCorpTraceRecordService {
	
	public int save( ContextInfo cti,CmCorpTraceRecord vo );
	public int insert( ContextInfo cti,CmCorpTraceRecord vo );
	public int remove( ContextInfo cti,CmCorpTraceRecord vo );
	public int update( ContextInfo cti,CmCorpTraceRecord vo );
	public PageModel findPage(PageInfo pi,CmCorpTraceRecord vo);
	public List<CmCorpTraceRecord> findAll(CmCorpTraceRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:51:17
	
	public CmCorpTraceRecord findRecordInfo(CmCorpTraceRecord vo);
	
}
