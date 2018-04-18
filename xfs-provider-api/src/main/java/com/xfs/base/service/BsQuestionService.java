package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.BsQuestion;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsQuestionService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 14:33:27
 */
public interface BsQuestionService {
	
	public int save(ContextInfo cti, BsQuestion vo);
	public int insert(ContextInfo cti, BsQuestion vo);
	public int remove(ContextInfo cti, BsQuestion vo);
	public int update(ContextInfo cti, BsQuestion vo);
	public PageModel findPage(PageInfo pi, BsQuestion vo);
	public List<BsQuestion> findAll(BsQuestion vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27
	
	public BsQuestion findByPK(BsQuestion vo);
	
}
