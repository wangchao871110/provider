package com.xfs.activity.service;

import java.util.List;

import com.xfs.activity.model.BdActivityJoinRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
* @ClassName: BdActivityJoinRecordService 
* @Description: 嘉年华活动参加记录接口
* @author duanax@xinfushe.com
* @version  : v1.0
* @date 2016年11月14日 上午11:02:07 
 */
public interface BdActivityJoinRecordService {
	
	public int save(ContextInfo cti, BdActivityJoinRecord vo);
	public int insert(ContextInfo cti, BdActivityJoinRecord vo);
	public int remove(ContextInfo cti, BdActivityJoinRecord vo);
	public int update(ContextInfo cti, BdActivityJoinRecord vo);
	public PageModel findPage(PageInfo pi, BdActivityJoinRecord vo);
	public List<BdActivityJoinRecord> findAll(BdActivityJoinRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/18 16:34:21
	
	
	
}
