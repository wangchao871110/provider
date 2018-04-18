package com.xfs.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.dao.SysFunctionCategoryDao;
import com.xfs.user.model.SysFunctionCategory;
import com.xfs.user.service.SysFunctionCategoryService;


@Service
public class SysFunctionCategoryServiceImpl implements SysFunctionCategoryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysFunctionCategoryServiceImpl.class);
	
	@Autowired
	private SysFunctionCategoryDao sysFunctionCategoryDao;
	
	public int save(ContextInfo cti, SysFunctionCategory vo ){
		return sysFunctionCategoryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SysFunctionCategory vo ){
		return sysFunctionCategoryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SysFunctionCategory vo ){
		return sysFunctionCategoryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SysFunctionCategory vo ){
		return sysFunctionCategoryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysFunctionCategory vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysFunctionCategoryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysFunctionCategory> datas = sysFunctionCategoryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysFunctionCategory> findAll(SysFunctionCategory vo){
		
		List<SysFunctionCategory> datas = sysFunctionCategoryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:20

    /** 查找类别
     *  @param    : sysCode 系统类别
     * @return    :List<SysFunctionCategory>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public List<SysFunctionCategory> findTopMenu(String sysCode) {
        // TODO Auto-generated method stub
        return sysFunctionCategoryDao.findTopMenu(sysCode);
    }
	
	
}
