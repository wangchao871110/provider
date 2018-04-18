package com.xfs.activity.service;
import java.util.List;

import com.xfs.activity.model.BdUserPrizeRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BdUserPrizeRecordService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/25 16:49:40
 */
public interface BdUserPrizeRecordService {
	
	public int save(ContextInfo cti, BdUserPrizeRecord vo);
	public int insert(ContextInfo cti, BdUserPrizeRecord vo);
	public int remove(ContextInfo cti, BdUserPrizeRecord vo);
	public int update(ContextInfo cti, BdUserPrizeRecord vo);
	public PageModel findPage(PageInfo pi, BdUserPrizeRecord vo);
	public List<BdUserPrizeRecord> findAll(BdUserPrizeRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:49:40

	/**
	 * 通过订单修改用户奖券使用记录
	 *
	 * @param cti
	 * @param outTradeOrder
	 * @return
	 */
	public int updateByOrder(ContextInfo cti, String outTradeOrder);
	
}
