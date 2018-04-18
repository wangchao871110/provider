package com.xfs.op.service.impl;
import java.util.Date;
import java.util.List;

import com.xfs.op.dao.OpHeadlineSearchWordsDao;
import com.xfs.op.model.OpHeadlineSearchWords;
import com.xfs.op.service.OpHeadlineSearchWordsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;


@Service
public class OpHeadlineSearchWordsServiceImpl implements OpHeadlineSearchWordsService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpHeadlineSearchWordsServiceImpl.class);
	
	@Autowired
	private OpHeadlineSearchWordsDao opHeadlineSearchWordsDao;
	
	public int save(ContextInfo cti, OpHeadlineSearchWords vo ){
		return opHeadlineSearchWordsDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpHeadlineSearchWords vo ){
		return opHeadlineSearchWordsDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpHeadlineSearchWords vo ){
		return opHeadlineSearchWordsDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpHeadlineSearchWords vo ){
		return opHeadlineSearchWordsDao.update(cti,vo);
	}
	public PageModel findPage(OpHeadlineSearchWords vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opHeadlineSearchWordsDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpHeadlineSearchWords> datas = opHeadlineSearchWordsDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpHeadlineSearchWords> findAll(OpHeadlineSearchWords vo){
		
		List<OpHeadlineSearchWords> datas = opHeadlineSearchWordsDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/19 16:29:44


    /**
     * 更新搜索词的统计数据
     * @param searchWord
     * @return
     */
    public boolean updateOrAddSearchWord(ContextInfo contextInfo ,String searchWord){
        if(searchWord == null || "".equals(searchWord))
            return false;
        OpHeadlineSearchWords searchWordObj =
                opHeadlineSearchWordsDao.findBySearchWord(searchWord);
        if(searchWordObj != null){
            opHeadlineSearchWordsDao.addSearchWordTimes(contextInfo,searchWordObj.getId());
        }else {
            searchWordObj = new OpHeadlineSearchWords();
            searchWordObj.setTimes(1);
            searchWordObj.setSearhWord(searchWord);
            searchWordObj.setCreatDt(new Date());
            searchWordObj.setDr(0);
            searchWordObj.setModifyDt(new Date());
            opHeadlineSearchWordsDao.save(contextInfo,searchWordObj);
        }
        return true;
    }
	
}
