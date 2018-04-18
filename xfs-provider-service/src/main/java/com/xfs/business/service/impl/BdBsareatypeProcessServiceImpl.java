package com.xfs.business.service.impl;

import com.xfs.business.dao.BdBsareatypeProcessDao;
import com.xfs.business.model.BdBsareatypeProcess;
import com.xfs.business.service.BdBsareatypeProcessService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhao on 2017/3/21.
 */
@Service
public class BdBsareatypeProcessServiceImpl implements BdBsareatypeProcessService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BdBsareatypeServiceImpl.class);

    @Autowired
    private BdBsareatypeProcessDao bdBsareatypeProcessDao;


    public int save(ContextInfo cti, BdBsareatypeProcess vo ){
        return bdBsareatypeProcessDao.save(cti, vo);
    }
    public int insert(ContextInfo cti,  BdBsareatypeProcess vo ){
        vo.setId(null);
        return bdBsareatypeProcessDao.insert(cti, vo);
    }
    public int remove(ContextInfo cti,  BdBsareatypeProcess vo ){
        return bdBsareatypeProcessDao.remove(cti, vo);
    }
    public int update(ContextInfo cti,  BdBsareatypeProcess vo ){
        return bdBsareatypeProcessDao.update(cti, vo);
    }
    public PageModel findPage(PageInfo pi, BdBsareatypeProcess vo){

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bdBsareatypeProcessDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BdBsareatypeProcess> datas = bdBsareatypeProcessDao.freeFindVo(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }
    public List<BdBsareatypeProcess> findAll(BdBsareatypeProcess vo){

        List<BdBsareatypeProcess> datas = bdBsareatypeProcessDao.freeFind(vo);
        return datas;

    }

    public List<BdBsareatypeProcess> findAlWithDictitem(BdBsareatypeProcess vo){

        List<BdBsareatypeProcess> datas = bdBsareatypeProcessDao.findAlWithDictitem(vo);
        return datas;

    }

}
