package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.CsQuestionnaire;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * CsQuestionnaireService
 * @author:wangchao
 * @date:2016/07/28 10:52:51
 */
public interface CsQuestionnaireService {
	
	public int save(ContextInfo cti, CsQuestionnaire vo);
	public int insert(ContextInfo cti, CsQuestionnaire vo);
	public int remove(ContextInfo cti, CsQuestionnaire vo);
	public int update(ContextInfo cti, CsQuestionnaire vo);
	public PageModel findPage(PageInfo pi, CsQuestionnaire vo);
	public List<CsQuestionnaire> findAll(CsQuestionnaire vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/28 10:52:51
	
	
	
}
