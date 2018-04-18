package com.xfs.settlement.service;

import java.util.List;

import com.xfs.settlement.dto.ToReqPay;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dao.BlBalanceDao;
import com.xfs.settlement.model.BlBalance;

/**
 * 余额服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:15
 * @version 	: V1.0
 */
@Service
public class BlBalanceServiceImpl implements BlBalanceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlBalanceServiceImpl.class);
	
	@Autowired
	private BlBalanceDao blBalanceDao;
	
	public int save(ContextInfo cti, BlBalance vo ){
		return blBalanceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BlBalance vo ){
		return blBalanceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BlBalance vo ){
		return blBalanceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BlBalance vo ){
		return blBalanceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BlBalance vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blBalanceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlBalance> datas = blBalanceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlBalance> findAll(BlBalance vo){
		
		List<BlBalance> datas = blBalanceDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:30

	/**
	 *  查询账户余额
	 *  @param   vo
	 *	@return 			: com.xfs.settlement.model.BlBalance
	 *  @createDate  	: 2016-11-10 15:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:15
	 *  @updateAuthor  :
	 */
	@Override
	public BlBalance queryBalance(BlBalance vo) {
		return blBalanceDao.queryBalance(vo);
	}

	/**
	 *  查询账户余额
	 *  @param   toReqPay
	 *	@return 			: com.xfs.settlement.model.BlBalance
	 *  @createDate  	: 2016-11-10 15:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:15
	 *  @updateAuthor  :
	 */
	@Override
	public BlBalance queryBalance(ToReqPay toReqPay){
		BlBalance blBalance = new BlBalance();
		blBalance.setOwnerType(toReqPay.getBuyerType());
		blBalance.setOwner(toReqPay.getBuyerId());
		return queryBalance(blBalance);
	}
}
