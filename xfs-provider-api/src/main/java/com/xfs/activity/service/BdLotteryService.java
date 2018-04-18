package com.xfs.activity.service;

import java.util.List;
import java.util.Map;

import com.xfs.activity.model.BdLottery;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 
* @ClassName: BdLotteryService 
* @Description: 活动类
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:25:09 
*
 */
public interface BdLotteryService {
	
	public int save(ContextInfo cti, BdLottery vo);
	public int insert(ContextInfo cti, BdLottery vo);
	public int remove(ContextInfo cti, BdLottery vo);
	public int update(ContextInfo cti, BdLottery vo);
	public PageModel findPage(PageInfo pi, BdLottery vo);
	public List<BdLottery> findAll(BdLottery vo);
	
	public BdLottery findByPk(Integer id);
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/12 15:55:00
	
	/**
	 * 通过id或者code获取抽奖活动
	 *
	 * @param vo
	 * @return
	 */
	public BdLottery findLottery(BdLottery vo);
	
	/**
	 * 嘉年华专用
	 * @param lottery_code
	 * @param cpid
     * @return
     */
	public Map<String,Object> queryProuductInfo(String lottery_code, Integer cpid);
	
	public List<Map<String, Object>> queryPartnerList();
	
	
	
}
