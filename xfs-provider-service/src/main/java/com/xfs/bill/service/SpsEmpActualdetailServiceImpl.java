package com.xfs.bill.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsEmpActualdetailDao;
import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 实缴账单员工社保服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:09
 * @version 	: V1.0
 */
@Service
public class SpsEmpActualdetailServiceImpl implements SpsEmpActualdetailService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsEmpActualdetailServiceImpl.class);
	
	@Autowired
	private SpsEmpActualdetailDao spsEmpActualdetailDao;
	
	public int save(ContextInfo cti, SpsEmpActualdetail vo ){
		return spsEmpActualdetailDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsEmpActualdetail vo ){
		return spsEmpActualdetailDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsEmpActualdetail vo ){
		return spsEmpActualdetailDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsEmpActualdetail vo ){
		return spsEmpActualdetailDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsEmpActualdetail vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsEmpActualdetailDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsEmpActualdetail> datas = spsEmpActualdetailDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsEmpActualdetail> findAll(SpsEmpActualdetail vo){
		
		List<SpsEmpActualdetail> datas = spsEmpActualdetailDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:28
	
	
	
}
