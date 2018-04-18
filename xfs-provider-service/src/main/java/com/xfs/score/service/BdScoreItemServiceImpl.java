package com.xfs.score.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
//import com.xfs.bs.service.BdScoreDimensionService;
//import com.xfs.bs.service.BdScoreItemService;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
//import com.xfs.bs.dao.BdScoreItemDao;
import com.xfs.score.dao.BdScoreItemDao;
import com.xfs.score.model.BdScoreDimension;
import com.xfs.score.model.BdScoreItem;

@Service("bdScoreItemService")
public class BdScoreItemServiceImpl implements BdScoreItemService, IRedisKeys {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdScoreItemServiceImpl.class);

	private List<BaseCalcScore> calcScoreList = new ArrayList<>();

	@Autowired
	private BdScoreItemDao bdScoreItemDao;

	@Autowired
	private BdScoreDimensionService bdScoreDimensionService;

	@Autowired
	private BdScoreDimensionDetailService bdScoreDimensionDetailService;

	@Autowired
	private BdScoreItemDetailService bdScoreItemDetailService;

	@Autowired
	private BdScoreCollectionService bdScoreCollectionService;

	public int save(ContextInfo cti, BdScoreItem vo) {
		return bdScoreItemDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, BdScoreItem vo) {
		return bdScoreItemDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, BdScoreItem vo) {
		return bdScoreItemDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, BdScoreItem vo) {
		return bdScoreItemDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, BdScoreItem vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdScoreItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdScoreItem> datas = bdScoreItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<BdScoreItem> findAll(BdScoreItem vo) {

		List<BdScoreItem> datas = bdScoreItemDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/09/21 11:40:13

	/**
	 * 根据ID获取评分项信息
	 * 
	 * @param vo
	 * @return
	 * @createDate : 2016年11月9日 上午11:30:16
	 * @author :
	 * @version : v1.0
	 * @updateDate : 2016年11月9日 上午11:30:16
	 * @updateAuthor :
	 */
	@Override
	public BdScoreItem findByPk(BdScoreItem vo) {
		return bdScoreItemDao.findByPK(vo);
	}

	
	/**
	 * 增加监听对象
	 * 
	 * @param baseCalcScore
	 * @createDate : 2016年11月9日 上午11:32:27
	 * @author : 
	 * @version : v1.0
	 * @updateDate : 2016年11月9日 上午11:32:27
	 * @updateAuthor :
	 */
	public void addEventObject(BaseCalcScore baseCalcScore) {
		calcScoreList.add(baseCalcScore);
	}

	
	/**
	 * 核算评分维度方法
	 * 
	 *  @param type 
	 *  @createDate  	: 2016年11月9日 上午11:33:34
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月9日 上午11:33:34
	 *  @updateAuthor  	:
	 */
	@Override
	public void queryCalcScore(String type) {
		/**
		 * 根据核算评分类型，获取评分项，评分维度。
		 */
		List<BdScoreDimension> bdScoreDimensionList = bdScoreDimensionService.findAllByItemType(type);
		if (null != bdScoreDimensionList && !bdScoreDimensionList.isEmpty()) {
			/**
			 * 获取最新核算版本号
			 */
			String calc_version = bdScoreDimensionDetailService.queryCurrCalcVersion(type);
			for (BdScoreDimension bdScoreDimension : bdScoreDimensionList) {
				// 将需要计算的维度放入队列中
				bdScoreDimension.setCalcVersion(calc_version);
				RedisUtil.push(BUSINESS_CALC_SCORE_QUEUE, bdScoreDimension);
			}
		}
	}
	
	/**
	 * 处理队列中的维度核算公式
	 * 
	 *  @param bdScoreDimension 
	 *  @createDate  	: 2016年11月9日 上午11:33:52
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月9日 上午11:33:52
	 *  @updateAuthor  	:
	 */
	@Override
	public void calcScore(BdScoreDimension bdScoreDimension) {
		/**
		 * 根据评分维度计算类型，获取对应实现类
		 */
		try {
			if (null != calcScoreList) {
				BaseCalcScore calcScore = null;
				for (BaseCalcScore curr_calcScore : calcScoreList) {
					if (curr_calcScore.checkCalcScore(bdScoreDimension)) {
						calcScore = curr_calcScore;
						break;
					}
				}
				if (null != calcScore)
					calcScore.calcScore(null, bdScoreDimension);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
