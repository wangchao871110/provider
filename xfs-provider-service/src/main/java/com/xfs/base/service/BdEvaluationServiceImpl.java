package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BdEvaluationDao;
import com.xfs.base.model.BdEvaluation;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 评价服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 12:00
 * @version 	: V1.0
 */
@Service
public class BdEvaluationServiceImpl implements BdEvaluationService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdEvaluationServiceImpl.class);
	
	@Autowired
	private BdEvaluationDao bdEvaluationDao;
	
	public int save(ContextInfo cti, BdEvaluation vo ){
		return bdEvaluationDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdEvaluation vo ){
		return bdEvaluationDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdEvaluation vo ){
		return bdEvaluationDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdEvaluation vo ){
		return bdEvaluationDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdEvaluation vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdEvaluationDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdEvaluation> datas = bdEvaluationDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdEvaluation> findAll(BdEvaluation vo){
		
		List<BdEvaluation> datas = bdEvaluationDao.freeFind(vo);
		return datas;
		
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/05 10:52:21

	/**
	 *  获取评价信息
	 *  @param   bdEvaluation
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-11 11:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 11:59
	 *  @updateAuthor  :
	 */
	@Override
	public Map<String, Object> getEvaluationById(BdEvaluation bdEvaluation) {
		return bdEvaluationDao.getEvaluationById(bdEvaluation);
	}
	
}
