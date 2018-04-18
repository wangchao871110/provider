package com.xfs.business.service;

import java.util.List;
import java.util.Map;

import com.xfs.business.model.SpsTaskHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

/**
 * SpsTaskHistoryService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:29:21
 */
public interface SpsTaskHistoryService {
	
	public int save(ContextInfo cti, SpsTaskHistory vo);
	public int insert(ContextInfo cti, SpsTaskHistory vo);
	public int remove(ContextInfo cti, SpsTaskHistory vo);
	public int update(ContextInfo cti, SpsTaskHistory vo);
	public PageModel findPage(PageInfo pi, SpsTaskHistory vo);
	public List<SpsTaskHistory> findAll(SpsTaskHistory vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:29:21
	
	/**
	 * 2016-08 任务管理 
	 * 
	 * 任务单详情页面
	 * 
	 * 保存按钮 新增或者修改
	 * 
	 * zhangxiyan
	 */
	public Result detailsave(ContextInfo cti,SpsTaskHistory vo);
	/**
	 * 2016-08 任务管理 
	 * 
	 * 任务单详情页面
	 * 
	 * 查看操作历史记录
	 * 
	 * zhangxiyan
	 */
	public List<Map<String,Object>> seehistory(SpsTaskHistory vo);


	public Integer queryHistoryCount(ContextInfo cti,Integer stateFileId);
	
}
