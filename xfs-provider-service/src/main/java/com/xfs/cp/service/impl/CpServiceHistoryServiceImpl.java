package com.xfs.cp.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.cp.dao.CpServiceHistoryDao;
import com.xfs.cp.model.CpServiceHistory;
import com.xfs.cp.service.CpServiceHistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by zhao on 2017/1/5.
 *
 */
@Service
public class CpServiceHistoryServiceImpl implements CpServiceHistoryService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CpServiceHistoryServiceImpl.class);



    @Autowired
    private CpServiceHistoryDao cpServiceHistoryDao;

    public int save(ContextInfo cti, CpServiceHistory vo ){
        return cpServiceHistoryDao.save(cti, vo);
    }
    public int insert(ContextInfo cti, CpServiceHistory vo ){
        return cpServiceHistoryDao.insert(cti, vo);
    }
    public int remove(ContextInfo cti, CpServiceHistory vo ){
        return cpServiceHistoryDao.remove(cti, vo);
    }
    public int update(ContextInfo cti, CpServiceHistory vo ){
        return cpServiceHistoryDao.update(cti, vo);
    }
    public PageModel findPage(PageInfo pi, CpServiceHistory vo){

        PageModel pm = new PageModel();
        int pageIndex = SystemContext.getOffset();
        int pageSize = SystemContext.getPagesize();
        Integer total = cpServiceHistoryDao.countFreeFind(vo);
        pm.setTotal(total);
        List<CpServiceHistory> datas = cpServiceHistoryDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }
    public List<CpServiceHistory> findAll(CpServiceHistory vo){

        List<CpServiceHistory> datas = cpServiceHistoryDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 14:51:32

    public int backupsForCpService(ContextInfo cti, CpServiceHistory vo ){
		Calendar calendar=Calendar.getInstance();
    	vo.setThisMonth(calendar.get(Calendar.MONTH)+1);
        return cpServiceHistoryDao.backupsForCpService(cti, vo);
    }
    public List<CpServiceHistory> findYearOrMonth(CpServiceHistory vo){

        List<CpServiceHistory> datas = cpServiceHistoryDao.findYearOrMonth(vo);
        return datas;

    }
}
