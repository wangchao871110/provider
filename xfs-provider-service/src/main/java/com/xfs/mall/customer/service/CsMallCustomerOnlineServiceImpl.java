package com.xfs.mall.customer.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.dao.CsMallCustomerOnlineDao;
import com.xfs.mall.customer.model.CsMallCustomerOnline;


@Service
public class CsMallCustomerOnlineServiceImpl implements CsMallCustomerOnlineService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsMallCustomerOnlineServiceImpl.class);
	
	@Autowired
	private CsMallCustomerOnlineDao csMallCustomerOnlineDao;
	
	public int save(ContextInfo cti, CsMallCustomerOnline vo ){
		return csMallCustomerOnlineDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsMallCustomerOnline vo ){
		return csMallCustomerOnlineDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsMallCustomerOnline vo ){
		return csMallCustomerOnlineDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsMallCustomerOnline vo ){
		return csMallCustomerOnlineDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsMallCustomerOnline vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerOnlineDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsMallCustomerOnline> datas = csMallCustomerOnlineDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsMallCustomerOnline> findAll(CsMallCustomerOnline vo){
		
		List<CsMallCustomerOnline> datas = csMallCustomerOnlineDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:10

	@Override
	public PageModel selectImOnlinePage(PageInfo pi, Map map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerOnlineDao.selectImOnlineCount(map);
		pm.setTotal(total);
		List<Map<String, Object>> datas = csMallCustomerOnlineDao.selectImOnline(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;	
	}
	
	/**
	 * 判断客服是否在线
	 *
	 * @param vo
	 * @return
	 */
	public CsMallCustomerOnline findOnlineCustomer(CsMallCustomerOnline vo) {
		return csMallCustomerOnlineDao.findOnlineCustomer(vo);
	}

}
