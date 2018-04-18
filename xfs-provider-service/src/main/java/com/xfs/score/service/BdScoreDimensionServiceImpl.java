package com.xfs.score.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
//import com.xfs.bs.service.BdScoreDimensionService;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
//import com.xfs.bs.dao.BdScoreDimensionDao;
import com.xfs.score.dao.BdScoreDimensionDao;
import com.xfs.score.model.BdScoreDimension;

@Service
public class BdScoreDimensionServiceImpl implements BdScoreDimensionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdScoreDimensionServiceImpl.class);

	@Autowired
	private BdScoreDimensionDao bdScoreDimensionDao;

	public int save(ContextInfo cti, BdScoreDimension vo) {
		return bdScoreDimensionDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, BdScoreDimension vo) {
		return bdScoreDimensionDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, BdScoreDimension vo) {
		return bdScoreDimensionDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, BdScoreDimension vo) {
		return bdScoreDimensionDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, BdScoreDimension vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdScoreDimensionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdScoreDimension> datas = bdScoreDimensionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<BdScoreDimension> findAll(BdScoreDimension vo) {

		List<BdScoreDimension> datas = bdScoreDimensionDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/09/21 11:40:16

	
	/**
	 * 根据评分类型获取评分维度
	 * 
	 *  @param type
	 *  @return 
	 *  @createDate  	: 2016年11月9日 上午11:36:27
	 *  @author         	:
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月9日 上午11:36:27
	 *  @updateAuthor  	:
	 */
	@Override
	public List<BdScoreDimension> findAllByItemType(String type) {
		return bdScoreDimensionDao.queryBdScoreDimensionListByType(type);
	}

	
	/**
	 * 执行动态sql
	 *  @param sql
	 *  @return 
	 *  @createDate  	: 2016年11月9日 上午11:36:45
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月9日 上午11:36:45
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> executeDynamicSql(String sql) {
		return bdScoreDimensionDao.executeDynamicSql(sql);
	}

	
	/**
	 * 根据ID获取评分维度
	 * 
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2016年11月9日 上午11:37:01
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月9日 上午11:37:01
	 *  @updateAuthor  	:
	 */
	@Override
	public BdScoreDimension findByPk(BdScoreDimension vo) {
		return bdScoreDimensionDao.findByPK(vo);
	}
}
