package com.xfs.activity.service;

import java.util.List;

import com.xfs.activity.model.BdLotteryRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 
* @ClassName: BdLotteryRecordService 
* @Description: 奖品记录表
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:16:27 
*
 */
public interface BdLotteryRecordService {
	
	public int save(ContextInfo cti, BdLotteryRecord vo);
	public int insert(ContextInfo cti, BdLotteryRecord vo);
	public int remove(ContextInfo cti, BdLotteryRecord vo);
	public int update(ContextInfo cti, BdLotteryRecord vo);
	public PageModel findPage(PageInfo pi, BdLotteryRecord vo);
	public List<BdLotteryRecord> findAll(BdLotteryRecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/10 20:26:29
	/**
	 * 
	* @Title: findAll 
	* @Description: 根据各字段条件带排序
	* @param  obj
	* @param  orderByColName
	* @param   设定文件 
	* @return List<BdLotteryRecord>    返回类型 
	* @throws
	 */
	public List<BdLotteryRecord> findAll(BdLotteryRecord obj, String orderByColName);
	
	public int countFreeFind(BdLotteryRecord vo);
	/**
	 * 
	* @Title: batchInsert 
	* @Description: 根据活动抽奖次数，批量插入该用户抽奖次数
	* @param  list
	* @param    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int batchInsert(ContextInfo cti, List<BdLotteryRecord> list);
}
