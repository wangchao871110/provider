package com.xfs.activity.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdLotteryPoolDao;
import com.xfs.activity.dao.BdPrizeDao;
import com.xfs.activity.model.BdLotteryPool;
import com.xfs.activity.model.BdLotteryRecord;
import com.xfs.activity.model.BdPrize;
import com.xfs.activity.model.BdUserPrize;
import com.xfs.activity.service.BdLotteryPoolService;
import com.xfs.activity.service.BdLotteryRecordService;
import com.xfs.activity.service.BdPrizeService;
import com.xfs.activity.service.BdUserPrizeService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.CompressStrUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.common.util.UUIDUtil;

@Service
public class BdLotteryPoolServiceImpl implements BdLotteryPoolService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdLotteryPoolServiceImpl.class);
	
	@Autowired
	private BdPrizeDao bdPrizeDao;
	
	@Autowired
	private BdLotteryPoolDao bdLotteryPoolDao;
	
	@Autowired
	private BdUserPrizeService bdUserPrizeService;
	
	@Autowired
	private BdLotteryRecordService bdLotteryRecordService;
	
	@Autowired
	private BdPrizeService bdPrizeService;
	
	public int save(ContextInfo cti, BdLotteryPool vo ){
		return bdLotteryPoolDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdLotteryPool vo ){
		return bdLotteryPoolDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdLotteryPool vo ){
		return bdLotteryPoolDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdLotteryPool vo ){
		return bdLotteryPoolDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdLotteryPool vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdLotteryPoolDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdLotteryPool> datas = bdLotteryPoolDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdLotteryPool> findAll(BdLotteryPool vo){
		
		List<BdLotteryPool> datas = bdLotteryPoolDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public PageModel findPool(PageInfo pi, Map map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdLotteryPoolDao.findPoolCount(map);
		pm.setTotal(total);
		List<Map<String , Object>> datas = bdLotteryPoolDao.findPool(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public BdLotteryPool findByPK(Integer id) {
		return bdLotteryPoolDao.findByPK(id);
	}
	@Override
	public Integer updatePrizeRem(ContextInfo cti, String prizeId, String remainNum) {
		BdPrize obj = new BdPrize();
		obj.setPrizeId(Integer.parseInt(prizeId));
		BdPrize prize = bdPrizeDao.findByPK(Integer.parseInt(prizeId));
		int i = prize.getRemainNum();
		int j = prize.getDistributedNum();
		obj.setDistributedNum(j+Integer.parseInt(remainNum));
		i = i - Integer.parseInt(remainNum);
		obj.setRemainNum(i);
		return bdPrizeDao.update(cti, obj);
	}
	@Override
	public Integer updatePrizeRemainNum(ContextInfo cti, int prizeId, int remainNum) {
		BdPrize obj = new BdPrize();
		obj.setPrizeId(prizeId);
		BdPrize prize = bdPrizeDao.findByPK(prizeId);
		int i = prize.getRemainNum();
		int j = prize.getDistributedNum();
		j = j - remainNum;
		i = i + remainNum;
		obj.setRemainNum(i);
		obj.setDistributedNum(j);
		return bdPrizeDao.update(cti, obj);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/14 16:10:55
	@Override
	public List<Map> findPrizeBylotteryCode(BdLotteryPool vo, String lotteryCode){
		List<Map> datas = bdLotteryPoolDao.FindBD_LOTTERY_POOL_BY_LotteryCode(vo,lotteryCode);
		return datas;
	}

	/**
	 * 抽奖
	 *
	 * @param record
	 * @param prizes
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@Override
	public Result drawLottery(ContextInfo cti, BdLotteryRecord record, List<Map<String, Object>> prizes, Result result) throws Exception {
		// 1、修改抽奖记录状态
		record.setState("DRAWED");
		int r = bdLotteryRecordService.update(cti, record);
		if (r <= 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		// 2、计算概率、抽奖
		Map<String, Object> zeroPrize = null;
		if (prizes == null || prizes.isEmpty() ||
				(prizes.size() == 1 && (zeroPrize = prizes.get(0)) != null && "ZERO".equals(zeroPrize.get("prize_type")))) {
			result.setDataValue("prizeId", 0);
			result.setSuccess(true);
			return result;
		}
		double total = 0.0;
		for (Map<String, Object> prize: prizes) {
			Object pro = prize.get("probability");
			if (pro != null && StringUtils.isNumeric(pro.toString())) {
				total += (Double)pro;
			}
		}
		total *= 10000;
		Random random = new Random();
		int num = random.nextInt((int)total) + 1;
		double p = 0.0;
		Map<String, Object> randomPrize = null;
		for (Map<String, Object> prize: prizes) {
			Object pro = prize.get("probability");
			if (pro != null && StringUtils.isNumeric(pro.toString())) {
				p += (double)pro * 10000;
				if (p >= num) {
					randomPrize = prize;
					break;
				}
			}
		}
		if (randomPrize == null || "ZERO".equals(randomPrize.get("prize_type"))) {
			result.setDataValue("prizeId", 0);
			result.setSuccess(true);
			return result;
		}
		// 3、修改奖品数量
		int prizeId = (int)randomPrize.get("prize_id");
		BdPrize prize = bdPrizeService.findByPK(prizeId);
		if (prize == null || prize.getRemainNum() == null || prize.getRemainNum() == 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		prize.setRemainNum(prize.getRemainNum() - 1);
		prize.setDistributedNum((prize.getDistributedNum() != null ? prize.getDistributedNum() : 0) + 1);
		r = bdPrizeService.update(cti, prize);
		if (r <= 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		int lotteryId = (int)randomPrize.get("lottery_id");
		BdLotteryPool pool = new BdLotteryPool();
		pool.setLotteryId(lotteryId);
		pool.setPrizeId(prizeId);
		List<BdLotteryPool> pools = findAll(pool);
		if (pools == null || pools.size() != 1 || pools.get(0).getRemainNum() == null || pools.get(0).getRemainNum() == 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		pool = pools.get(0);
		pool.setRemainNum(pool.getRemainNum() - 1);
		pool.setDistributedNum((pool.getDistributedNum() != null ? pool.getDistributedNum() : 0) + 1);
		r = update(cti, pool);
		if (r <= 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		// 4、增加用户中奖数据
		BdUserPrize userPrize = new BdUserPrize();
		userPrize.setUserId(record.getUserId());
		userPrize.setUserType(record.getUserType());
		userPrize.setOrgId(record.getOrgId());
		userPrize.setLotteryId(lotteryId);
		userPrize.setPrizeId(prizeId);
		userPrize.setPrizeCode(CompressStrUtil.compress(UUIDUtil.randomUUID())[0]);
		if(randomPrize.get("prize_price") != null){
			BigDecimal price = (BigDecimal) randomPrize.get("prize_price");
			userPrize.setPrizePrice(price);
//			userPrize.setPrizePrice(new BigDecimal((Double) randomPrize.get("prize_price")));
		}
		userPrize.setBegintime((Date)randomPrize.get("begintime"));
		userPrize.setEndtime((Date)randomPrize.get("endtime"));
		userPrize.setState("UNUSE");
		userPrize.setDr(0);
		r = bdUserPrizeService.insert(cti, userPrize);
		if (r <= 0) {
			throw new BusinessException("抽奖失败，请重试");
		}
		int prizeImg = (int)randomPrize.get("prize_img");
		String prizeName = (String)randomPrize.get("prize_name");
		result.setDataValue("prizeImg", prizeImg);
		result.setDataValue("prizeName", prizeName);
		result.setDataValue("prizeId", prizeId);
		result.setSuccess(true);
		return result;
	}
	/**
	 * 查询活动的奖品
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findLotteryPirze(BdLotteryPool vo) {
		return bdLotteryPoolDao.findLotteryPirze(vo);
	}
	
}
