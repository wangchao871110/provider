package com.xfs.settlement.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.settlement.dao.BlPayBusinessrelDao;
import com.xfs.settlement.model.BlPayBusinessrel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


/**
 * 支付渠道与业务关系服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:43
 * @version 	: V1.0
 */
@Service
public class BlPayBusinessrelServiceImpl implements BlPayBusinessrelService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayBusinessrelServiceImpl.class);
	
	@Autowired
	private BlPayBusinessrelDao blPayBusinessrelDao;
	
	public int save(ContextInfo cti, BlPayBusinessrel vo ){
		return blPayBusinessrelDao.save(cti,vo);
	}
	public int insert(ContextInfo cti,BlPayBusinessrel vo ){
		return blPayBusinessrelDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, BlPayBusinessrel vo ){
		return blPayBusinessrelDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, BlPayBusinessrel vo ){
		return blPayBusinessrelDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi, BlPayBusinessrel vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blPayBusinessrelDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayBusinessrel> datas = blPayBusinessrelDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayBusinessrel> findAll(BlPayBusinessrel vo){
		
		List<BlPayBusinessrel> datas = blPayBusinessrelDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:39
	
	
	
}
