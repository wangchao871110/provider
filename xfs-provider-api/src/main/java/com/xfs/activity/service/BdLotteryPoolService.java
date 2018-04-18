package com.xfs.activity.service;

import java.util.List;
import java.util.Map;

import com.xfs.activity.model.BdLotteryPool;
import com.xfs.activity.model.BdLotteryRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

/**
 * 
* @ClassName: BdLotteryPoolService 
* @Description:  奖池接口
* @author duanax@xinfushe.com 
* @date 2016年11月15日 上午11:09:41 
*
 */
public interface BdLotteryPoolService {
	
	public int save(ContextInfo cti, BdLotteryPool vo);
	public int insert(ContextInfo cti, BdLotteryPool vo);
	public int remove(ContextInfo cti, BdLotteryPool vo);
	public int update(ContextInfo cti, BdLotteryPool vo);
	public PageModel findPage(PageInfo pi, BdLotteryPool vo);
	public List<BdLotteryPool> findAll(BdLotteryPool vo);
	
	public BdLotteryPool findByPK(Integer id);
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/14 16:10:55
	
	public PageModel findPool(PageInfo pi, Map map);
	/**
	 * 给活动添加完奖品后更新bd_prize中remain_num数据
	 * @param prizeId
	 * @param remainNum
	 * @return Integer
	 */
	public Integer updatePrizeRem(ContextInfo cti, String prizeId, String remainNum);
	
	/**
	 * 删除活动中奖品后,更新bd_prize表中remainNum和分配数量
	 * @param prizeId
	 * @param remainNum
	 * @return Integer
	 */
	public Integer updatePrizeRemainNum(ContextInfo cti, int prizeId, int remainNum);
	
	/**
	 * 抽奖
	 *
	 * @param record
	 * @param prizes
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result drawLottery(ContextInfo cti, BdLotteryRecord record, List<Map<String, Object>> prizes, Result result) throws Exception;
	
	/**
	 * 
	* @Title: findPrizeBylotteryCode 
	* @Description: 通过活动code获取活动奖品
	* @param  vo
	* @param  lotteryCode
	* @return List<Map>    返回类型 
	* @throws
	 */
	public List<Map> findPrizeBylotteryCode(BdLotteryPool vo, String lotteryCode);
	
	/**
	 * 查询活动的奖品
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findLotteryPirze(BdLotteryPool vo);
}
