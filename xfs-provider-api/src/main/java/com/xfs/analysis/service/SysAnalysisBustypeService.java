package com.xfs.analysis.service;
import java.util.List;

import com.xfs.analysis.dto.SysAnalysisBustype;
import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;

/**
 * SysAnalysisBustypeService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:24
 */
public interface SysAnalysisBustypeService {
	
	public int save(ContextInfo cti, SysAnalysisBustype vo);
	public int insert(ContextInfo cti,SysAnalysisBustype vo);
	public int remove(ContextInfo cti,SysAnalysisBustype vo);
	public int update(ContextInfo cti,SysAnalysisBustype vo);
	public PageModel findPage(SysAnalysisBustype vo);
	public List<SysAnalysisBustype> findAll(SysAnalysisBustype vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:24
	
	
	
}
