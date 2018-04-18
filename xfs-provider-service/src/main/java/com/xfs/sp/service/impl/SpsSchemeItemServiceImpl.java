package com.xfs.sp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpsSchemeItemDao;
import com.xfs.sp.model.SpsSchemeItem;
import com.xfs.sp.service.SpsSchemeItemService;

@Service
public class SpsSchemeItemServiceImpl implements SpsSchemeItemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsSchemeItemServiceImpl.class);
	
	@Autowired
	private SpsSchemeItemDao spsSchemeItemDao;
	
	public int save(ContextInfo cti, SpsSchemeItem vo ){
		return spsSchemeItemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsSchemeItem vo ){
		return spsSchemeItemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsSchemeItem vo ){
		return spsSchemeItemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsSchemeItem vo ){
		return spsSchemeItemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsSchemeItem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSchemeItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsSchemeItem> datas = spsSchemeItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsSchemeItem> findAll(SpsSchemeItem vo){
		
		List<SpsSchemeItem> datas = spsSchemeItemDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:36
	
	/**
	 * 根据 方案id 查询 方案的项目
	 *
	 * @author lifq
	 *
	 * 2016年8月8日  下午5:11:02
	 */
	public List<Map<String,Object>> findItemsBySchemeId(Integer scheme_id){
		List<Map<String,Object>> datas = spsSchemeItemDao.findItemsBySchemeId(scheme_id);
        return datas;
	}
	
}
