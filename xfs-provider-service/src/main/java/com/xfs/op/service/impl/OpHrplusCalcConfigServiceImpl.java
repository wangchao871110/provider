package com.xfs.op.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

import com.xfs.op.service.OpHrplusCalcConfigService;
import com.xfs.op.dao.OpHrplusCalcConfigDao;
import com.xfs.op.model.OpHrplusCalcConfig;

@Service
public class OpHrplusCalcConfigServiceImpl implements OpHrplusCalcConfigService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpHrplusCalcConfigServiceImpl.class);
	
	@Autowired
	private OpHrplusCalcConfigDao opHrplusCalcConfigDao;
	
	public int save(ContextInfo cti, OpHrplusCalcConfig vo ){
		return opHrplusCalcConfigDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpHrplusCalcConfig vo ){
		return opHrplusCalcConfigDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpHrplusCalcConfig vo ){
		return opHrplusCalcConfigDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpHrplusCalcConfig vo ){
		return opHrplusCalcConfigDao.update(cti,vo);
	}
	public PageModel findPage(OpHrplusCalcConfig vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opHrplusCalcConfigDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpHrplusCalcConfig> datas = opHrplusCalcConfigDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpHrplusCalcConfig> findAll(OpHrplusCalcConfig vo){
		
		List<OpHrplusCalcConfig> datas = opHrplusCalcConfigDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/06 16:45:09
	
	
	
}
