package com.xfs.mall.customer.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.dao.CsMallCustomerSessionDao;
import com.xfs.mall.customer.model.CsMallCustomerSession;

@Service
public class CsMallCustomerSessionServiceImpl implements CsMallCustomerSessionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsMallCustomerSessionServiceImpl.class);
	
	@Autowired
	private CsMallCustomerSessionDao csMallCustomerSessionDao;
	
	public int save(ContextInfo cti, CsMallCustomerSession vo ){
		return csMallCustomerSessionDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsMallCustomerSession vo ){
		return csMallCustomerSessionDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsMallCustomerSession vo ){
		return csMallCustomerSessionDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsMallCustomerSession vo ){
		return csMallCustomerSessionDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsMallCustomerSession vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerSessionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsMallCustomerSession> datas = csMallCustomerSessionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsMallCustomerSession> findAll(CsMallCustomerSession vo){
		
		List<CsMallCustomerSession> datas = csMallCustomerSessionDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:38
	public CsMallCustomerSession findByCustomerIdAndUserId(CsMallCustomerSession vo){
		return csMallCustomerSessionDao.findByCustomerIdAndUserId(vo);
	}
	public int batchSaveUsers(ContextInfo cti, List<CsMallCustomerSession> list){
		return csMallCustomerSessionDao.batchInsert(cti, list);
	}

	public List<Map> freeFindTopTen(String customerId){
		return csMallCustomerSessionDao.freeFindTopTen(customerId);
	}

	/**
	 * 客服的历史会话用户列表
	 *
	 * @param vo
	 * @return
	 */
	public PageModel findCustomerSessionUsers(PageInfo pi, CsMallCustomerSession vo, String searchWord) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerSessionDao.findCustomerSessionUserCount(vo, searchWord);
		pm.setTotal(total);
		List<Map<String, Object>> datas = csMallCustomerSessionDao.findCustomerSessionUsers(vo, searchWord, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
}
