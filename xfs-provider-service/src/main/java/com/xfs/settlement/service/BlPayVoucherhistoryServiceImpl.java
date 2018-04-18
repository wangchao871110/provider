package com.xfs.settlement.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dao.BlPayVoucherhistoryDao;
import com.xfs.settlement.model.BlPayVoucherhistory;

/**
 * 支付凭证历史记录服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:18
 * @version 	: V1.0
 */
@Service
public class BlPayVoucherhistoryServiceImpl implements BlPayVoucherhistoryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayVoucherhistoryServiceImpl.class);
	
	@Autowired
	private BlPayVoucherhistoryDao blPayVoucherhistoryDao;
	
	public int save(ContextInfo cti, BlPayVoucherhistory vo ){
		return blPayVoucherhistoryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BlPayVoucherhistory vo ){
		return blPayVoucherhistoryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BlPayVoucherhistory vo ){
		return blPayVoucherhistoryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BlPayVoucherhistory vo ){
		return blPayVoucherhistoryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BlPayVoucherhistory vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blPayVoucherhistoryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayVoucherhistory> datas = blPayVoucherhistoryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayVoucherhistory> findAll(BlPayVoucherhistory vo){
		
		List<BlPayVoucherhistory> datas = blPayVoucherhistoryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:08:04
	
	
	
}
