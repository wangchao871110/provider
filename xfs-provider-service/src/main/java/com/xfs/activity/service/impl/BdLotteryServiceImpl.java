package com.xfs.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdLotteryDao;
import com.xfs.activity.model.BdLottery;
import com.xfs.activity.service.BdLotteryService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;

@Service
public class BdLotteryServiceImpl implements BdLotteryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdLotteryServiceImpl.class);
	
	@Autowired
	private BdLotteryDao bdLotteryDao;
	
	public int save(ContextInfo cti, BdLottery vo ){
		return bdLotteryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdLottery vo ){
		return bdLotteryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdLottery vo ){
		return bdLotteryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdLottery vo ){
		return bdLotteryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdLottery vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdLotteryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdLottery> datas = bdLotteryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdLottery> findAll(BdLottery vo){
		
		List<BdLottery> datas = bdLotteryDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public BdLottery findByPk(Integer id) {
		BdLottery bd = bdLotteryDao.findByPK(id);
		return bd;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/12 15:55:00
	
	/**
	 * 通过id或者code获取抽奖活动
	 *
	 * @param vo
	 * @return
	 */
	public BdLottery findLottery(BdLottery vo) {
		if (vo == null || (vo.getLotteryId() == null && StringUtils.isBlank(vo.getLotteryCode()))) {
			return null;
		}
		return bdLotteryDao.findLottery(vo);
	}
	
	@Override
	public Map<String, Object> queryProuductInfo(String lottery_code, Integer cpid) {
		return bdLotteryDao.queryProuductInfo(lottery_code,cpid);
	}

	@Override
	public List<Map<String, Object>> queryPartnerList(){
		return bdLotteryDao.queryPartnerList();
	}
	
}
