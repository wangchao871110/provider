package com.xfs.op.service.impl;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import com.xfs.op.dao.HeadLineHydsContentDao;
import com.xfs.op.model.HeadLineHydsContent;
import com.xfs.op.service.HeadLineHydsContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;


@Service
public class HeadLineHydsContentServiceImpl implements HeadLineHydsContentService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(HeadLineHydsContentServiceImpl.class);
	
	@Autowired
	private HeadLineHydsContentDao headLineHydsContentDao;
	
	public int save(ContextInfo cti, HeadLineHydsContent vo ){
		return headLineHydsContentDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, HeadLineHydsContent vo ){
		return headLineHydsContentDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, HeadLineHydsContent vo ){
		return headLineHydsContentDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, HeadLineHydsContent vo ){
		return headLineHydsContentDao.update(cti,vo);
	}
	public PageModel findPage(HeadLineHydsContent vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = headLineHydsContentDao.countFreeFind(vo);
		pm.setTotal(total);
		List<HeadLineHydsContent> datas = headLineHydsContentDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<HeadLineHydsContent> findAll(HeadLineHydsContent vo){
		
		List<HeadLineHydsContent> datas = headLineHydsContentDao.freeFind(vo);
		return datas;
		
	}
    public HeadLineHydsContent findByPK(HeadLineHydsContent obj){
      return   headLineHydsContentDao.findByPK(obj);
    }
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/05/02 16:19:11


    /**
     * 分页查询
     * @return
     */
    public PageModel findBySearchQueryMap(PageInfo pageInfo, HeadLineHydsContent query){
        PageModel pm = new PageModel(pageInfo);
        Integer total = headLineHydsContentDao.countBySearchQueryMap(query);
        pm.setTotal(total);
        pm.setDatas(headLineHydsContentDao.findBySearchQueryMap(query, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;    }


}
