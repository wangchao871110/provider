package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpNote;

/**
 * CpNoteService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:18
 */
public interface CpNoteService {
	
	public int save(ContextInfo cti, CpNote vo);
	public int insert(ContextInfo cti, CpNote vo);
	public int remove(ContextInfo cti, CpNote vo);
	public int update(ContextInfo cti, CpNote vo);
	public PageModel findPage(PageInfo pi, CpNote vo);
	public List<CpNote> findAll(CpNote vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	
	/**
	 * 
	 * @method comments: 保存纠错和留言
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午5:14:04 
	 * @param request
	 * @param cpNote
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午5:14:04 wangchao 创建
	 *
	 */
	public Result saveNote(ContextInfo user, CpNote cpNote, Result result);
	
	/**
	 * 
	 * @method comments: 获取留言和纠错
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月25日 下午3:32:26 
	 * @param cpNote
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月25日 下午3:32:26 baoyu 创建
	 *
	 */
	public PageModel queryNotePage(PageInfo pi, CpNote cpNote);
}
