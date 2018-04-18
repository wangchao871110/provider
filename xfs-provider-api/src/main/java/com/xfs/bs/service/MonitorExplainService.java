package com.xfs.bs.service;

import java.util.List;

import com.xfs.base.model.MonitorExplain;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


/**
 * MonitorExplainService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/03/09 15:27:20
 */
public interface MonitorExplainService {
	
	public int save(ContextInfo cti, MonitorExplain vo);
	public int insert(ContextInfo cti, MonitorExplain vo);
	public int remove(ContextInfo cti, MonitorExplain vo);
	public int update(ContextInfo cti, MonitorExplain vo);
	public PageModel findPage(PageInfo pi, MonitorExplain vo);
	public List<MonitorExplain> findAll(MonitorExplain vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/03/09 15:27:20
	
	
	
}
