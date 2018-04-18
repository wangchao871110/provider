package com.xfs.business.service.impl;

import com.xfs.business.dao.CmHrDemandRecordDao;
import com.xfs.business.model.CmHrDemandRecord;
import com.xfs.business.service.CmHrDemandRecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CmHrDemandRecordServiceImpl implements CmHrDemandRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmHrDemandRecordServiceImpl.class);
	
	@Autowired
	private CmHrDemandRecordDao cmHrDemandRecordDao;
	
	public int save(ContextInfo cti, CmHrDemandRecord vo ){
		return cmHrDemandRecordDao.save(cti,vo);
	}
	public int insert( ContextInfo cti,CmHrDemandRecord vo ){
		return cmHrDemandRecordDao.insert(cti,vo);
	}
	public int remove( ContextInfo cti,CmHrDemandRecord vo ){
		return cmHrDemandRecordDao.remove(cti,vo);
	}
	public int update( ContextInfo cti,CmHrDemandRecord vo ){
		return cmHrDemandRecordDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi,CmHrDemandRecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmHrDemandRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmHrDemandRecord> datas = cmHrDemandRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmHrDemandRecord> findAll(CmHrDemandRecord vo){
		
		List<CmHrDemandRecord> datas = cmHrDemandRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/30 12:00:46
	
	/**
	 * 2016-12-20  bs电话咨询管理
	 * 电话咨询管理页面
	 * xingzixian
	 */
	public PageModel findPageAll(PageInfo pi, Map<String, Object> map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmHrDemandRecordDao.CountfreeALL(map);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cmHrDemandRecordDao.freeALL(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 根据租户信息查看预约信息
	 * @param cti
	 * @param tenantId
	 * @return
	 */
	public int queryCountByTenantId(ContextInfo cti,String tenantId){
		return cmHrDemandRecordDao.queryCountByTenantId(cti,tenantId);
	}
}
