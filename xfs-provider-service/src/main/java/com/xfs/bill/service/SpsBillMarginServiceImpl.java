package com.xfs.bill.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsBillMarginDao;
import com.xfs.bill.model.SpsBillMargin;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 账单差额服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:09
 * @version 	: V1.0
 */
@Service
public class SpsBillMarginServiceImpl implements SpsBillMarginService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBillMarginServiceImpl.class);
	
	@Autowired
	private SpsBillMarginDao spsBillMarginDao;

	public int save(ContextInfo cti, SpsBillMargin vo ){
		return spsBillMarginDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsBillMargin vo ){
		return spsBillMarginDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsBillMargin vo ){
		return spsBillMarginDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsBillMargin vo ){
		return spsBillMarginDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsBillMargin vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillMarginDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBillMargin> datas = spsBillMarginDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsBillMargin> findAll(SpsBillMargin vo){
		
		List<SpsBillMargin> datas = spsBillMarginDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:29



}
