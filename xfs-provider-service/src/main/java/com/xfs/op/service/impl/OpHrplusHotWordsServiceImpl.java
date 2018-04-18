package com.xfs.op.service.impl;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import com.xfs.op.dao.OpHrplusHotWordsDao;
import com.xfs.op.model.OpHrplusAdvert;
import com.xfs.op.model.OpHrplusHotWords;
import com.xfs.op.service.OpHrplusHotWordsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

@Service
public class OpHrplusHotWordsServiceImpl implements OpHrplusHotWordsService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpHrplusHotWordsServiceImpl.class);
	
	@Autowired
	private OpHrplusHotWordsDao opHrplusHotWordsDao;
	
	public int save(ContextInfo cti, OpHrplusHotWords vo ){
		return opHrplusHotWordsDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpHrplusHotWords vo ){
		return opHrplusHotWordsDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpHrplusHotWords vo ){
		return opHrplusHotWordsDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpHrplusHotWords vo ){
		return opHrplusHotWordsDao.update(cti,vo);
	}
	public PageModel findPage(OpHrplusHotWords vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opHrplusHotWordsDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpHrplusHotWords> datas = opHrplusHotWordsDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpHrplusHotWords> findAll(OpHrplusHotWords vo){
		
		List<OpHrplusHotWords> datas = opHrplusHotWordsDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:41



    /**
     * 使用主键
     * @param obj
     * @return
     */
    public OpHrplusHotWords findByPK(OpHrplusHotWords obj){
        return opHrplusHotWordsDao.findByPK(obj);
    }

    /**
     *  广告 批量查询
     *  @param   pageInfo, query
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2017/1/12 14:21
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/12 14:21
     *  @updateAuthor  :
     */
    public PageModel findManagePage(PageInfo pageInfo, Map<String, Object> query){
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHrplusHotWordsDao.finManageHotListCount(query);
        pm.setTotal(total);
        pm.setDatas(opHrplusHotWordsDao.finManageHotList(query,pageInfo.getOffset(),pageInfo.getPagesize()));
        return pm;
    }




}
