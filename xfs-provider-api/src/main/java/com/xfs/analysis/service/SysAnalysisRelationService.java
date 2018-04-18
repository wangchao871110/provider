package com.xfs.analysis.service;
import java.util.List;

import com.xfs.analysis.dto.SysAnalysisRelation;
import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;

/**
 * SysAnalysisRelationService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:31
 */
public interface SysAnalysisRelationService {
	
	public int save(ContextInfo cti, SysAnalysisRelation vo);
	public int insert(ContextInfo cti,SysAnalysisRelation vo);
	public int remove(ContextInfo cti,SysAnalysisRelation vo);
	public int update(ContextInfo cti,SysAnalysisRelation vo);
	public PageModel findPage(SysAnalysisRelation vo);
	public List<SysAnalysisRelation> findAll(SysAnalysisRelation vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:31
	
	
	
}
